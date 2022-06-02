select
       cast(direction as char),
       train_id, route.name as route,
       to_char(time_arrive, 'YYYY-MM-DD HH:MI') as time_arrive,
       station.city as station
from
    (select ts.id as sch_id, ts.train_id, ts.direction, ts.time,
           ts.route_id, rs.station_id, rs.route_time,
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
        (select id, route_id, station_id, route_time, time_stop from route_station
        where station_id = :station_id ) as rs
        inner join train_schedule ts on rs.route_id = ts.route_id
        left join route_station end_st on (ts.direction = false) and
                                      (ts.route_id = end_st.route_id) and
                                      (end_st.time_stop is null) and
                                      (end_st.route_time!=0)
        where not ((rs.route_time = end_st.route_time and direction = false) or (rs.route_time = 0 and direction = true))
    ) as ss
    inner join station on ss.station_id = station.id
    inner join route on ss.route_id = route.id
where (cast(:min_time as timestamp) is null or ss.time_arrive > :min_time)
  and (cast(:max_time as timestamp) is null or ss.time_arrive < :max_time)
