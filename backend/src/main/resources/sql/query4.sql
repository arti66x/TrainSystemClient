-- Получить перечень и общее число локомотивов,
-- приписанных к железнодорожной станции, находящихся на ней в указанное вpемя,
-- по вpемени прибытия на станции, по количеству совеpшенных маршрутов.
select train_id, route,
       to_char(time_arrive, 'YYYY-MM-DD HH24:MI') as time_arrive,
       to_char(time_leave, 'YYYY-MM-DD HH24:MI') as time_leave from
(select ts.train_id, r.name as route,
           case when ts.direction
               then ts.time + interval '1 minute' * ts.route_time
               else case when (ts.time_stop is null)
                   then ts.time+interval '1 minute' *(rs.route_time-ts.route_time)
                   else ts.time+interval '1 minute' *(rs.route_time-ts.route_time-ts.time_stop)
                   end
                end as time_arrive,
           case when (ts.time_stop is null)
               then null
               else case when ts.direction
                        then ts.time + interval '1 minute' *(ts.route_time+ts.time_stop)
                        else ts.time+ interval '1 minute' *(rs.route_time-ts.route_time)
                        end
               end time_leave
    from

    (select ts.id as ts_id, ts.train_id,ts.time,ts.route_id, ts.direction,rs.route_time, rs.time_stop from
        -- последняя поездка каждого поезда перед указанной датой
        train_schedule ts
        inner join
            (select train_id, max(time) as time
            from train_schedule
            where time < :time
            group by train_id
            ) tsm
        on ts.train_id = tsm.train_id and ts.time = tsm.time
        -- для указанной станции относительное временя прибытия и время на останвку
        inner join
            (select * from route_station
             where station_id = :station_id
            ) rs
        on ts.route_id = rs.route_id
        -- исключаем точку отправления (100% не подходит)
        where not ((ts.direction = true and  rs.route_time = 0)
            or (ts.direction = false and  rs.route_time != 0 and rs.time_stop is null))
    ) as ts
        --если направление обратное нужно знать полное время маршрута (время прибытия на последнюю станцию)
    left join route_station rs on (ts.direction = false) and
                                  (ts.route_id = rs.route_id) and
                                  (rs.time_stop is null) and
                                  ( rs.route_time!=0)
    inner join route r on ts.route_id = r.id
    ) as ss
where (:time > time_arrive) and
    (time_leave is null or :time < time_leave)