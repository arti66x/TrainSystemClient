-- Получить перечень и общее число локомотивов, пpошедших плановый
-- техосмотp за определенный пеpиод вpемени, отпpавленных в pемонт
-- в обозначенное вpемя, pемонтиpованных указанное число pаз,
-- по количеству совеpшенных рейсов до pемонта, по возpасту локомотива.

with last_repair as
     (select train_id, max(start_date) as date
      from repair
      where (cast (:min_rep as date) is null or repair.start_date > :min_rep)
        and (cast (:max_rep as date) is null or repair.start_date < :max_rep)
      group by train_id
     )
select t.train_id, t.age, ti.last_inspection, last_repair.date as last_repair, r.trips_count_before_rep, rc.rep_count from
    (select id as train_id, extract(year from age(current_date,train.first_date)) as age
    from train
    where (:min_age is null or extract(year from age(current_date, train.first_date)) > :min_age)
      and (:max_age is null or extract(year from age(current_date, train.first_date)) < :max_age)
    ) as t
    inner join
    (select train_id, max(date) as last_inspection from tech_inspection
    where (cast(:min_tech as date) is null or tech_inspection.date > :min_tech)
      and (cast(:max_tech as date) is null or tech_inspection.date < :max_tech)
    group by train_id
    ) as ti on t.train_id = ti.train_id
    inner join

    (select ts.train_id, count(*) as trips_count_before_rep from
        last_repair inner join train_schedule ts on last_repair.train_id = ts.train_id and ts.time<last_repair.date
    group by ts.train_id
    having (:min_trips is null or count(*) > :min_trips)
       and (:max_trips is null or count(*) < :max_trips)
    ) as r on r.train_id = t.train_id
    inner join last_repair on last_repair.train_id = r.train_id


    inner join
        (select train_id, count(*) as rep_count from
            repair
        group by train_id
        having (:min_rep_count is null or count(*) > :min_rep_count)
           and (:max_rep_count is null or count(*) < :max_rep_count)
    ) as rc on rc.train_id = t.train_id