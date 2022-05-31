select tsc.train_id as train_id, station.city as station, tsc.trips_count from
    train
    inner join
    (select train_id, count(*) as trips_count from
    train_schedule
    group by train_id
    ) as tsc on train.id = tsc.train_id
    inner join station on train.station_id = station.id
where
    (:station is null or station_id = :station)
    and
    (:max_trips is null or tsc.trips_count < :max_trips)
    and
    (:min_trips is null or tsc.trips_count > :min_trips)