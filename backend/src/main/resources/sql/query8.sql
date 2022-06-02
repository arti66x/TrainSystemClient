-- Получить перечень и общее число задеpжанных pейсов полностью,
-- по указанной пpичине, по указанному маpшpуту, и количество сданных билетов за вpемя задеpжки.

select route.name as route, to_char(ts.time, 'YYYY-MM-DD HH:MI') as schedule_time, rc.returned_count from
    (select
        ss.sch_id as schedule_id, count(*) as returned_count
    from
        (select * from ticket where status = 1) as t
        inner join
        -- расписание времени прибытия на станции
        (select ts.id as sch_id, ts.train_id, ts.direction, ts.time,
                ts.route_id, rs.id as route_station_id,
                rs.station_id, rs.route_time,
                end_st.route_time as max_time,
                case when ts.direction
                         then ts.time + interval '1 minute' * rs.route_time
                     else case when (rs.time_stop is null)
                                   then ts.time+interval '1 minute' *(end_st.route_time-rs.route_time)
                               else ts.time+interval '1 minute' *(end_st.route_time-rs.route_time-rs.time_stop)
                         end
                    end as time_arrive,
                ts.time  as time_s
        from
             route_station rs
             inner join train_schedule ts on rs.route_id = ts.route_id
             left join route_station end_st on (ts.direction = false) and
                                               (ts.route_id = end_st.route_id) and
                                               (end_st.time_stop is null) and
                                               (end_st.route_time!=0)
        where
             (:route is null or ts.route_id = :route)
        ) as ss
        on ss.route_station_id = t.start_station_id
               and ss.sch_id = t.train_schedule_id
               and t.time>ss.time_arrive
    group by ss.sch_id
    ) as rc
    inner join train_schedule ts on ts.id = rc.schedule_id
    inner join route on ts.route_id = route.id
