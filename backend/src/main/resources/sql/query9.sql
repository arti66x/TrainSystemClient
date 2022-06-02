-- Получить перечень и сpеднее количество пpоданных билетов за указанный интервал
-- времени на опpеделенные маpшpуты, по длительности маршрута, по цене билета.

select ticket.id as ticket_id, r.name as route,
        (end_st.route_time - start_st.route_time) as durarion,
        r.price as price,
       to_char(ticket.time, 'YYYY-MM-DD HH:MI') as sell_time
from
    ticket
    inner join train_schedule ts on ts.id = ticket.train_schedule_id
    inner join route_station end_st on ticket.end_station_id = end_st.id
    inner join route_station start_st on ticket.start_station_id = start_st.id
    inner join route r on ts.route_id = r.id
where (:route is null or ts.route_id = :route)
    and (:min_duration is null or (end_st.route_time - start_st.route_time) > :min_duration)
    and (:max_duration is null or (end_st.route_time - start_st.route_time) < :max_duration)
    and (:min_cost is null or r.price > :min_cost)
    and (:max_cost is null or r.price < :max_cost)
    and (cast(:min_time as timestamp) is null or ticket.time > :min_time)
    and (cast(:max_time as timestamp) is null or ticket.time < :max_time)

