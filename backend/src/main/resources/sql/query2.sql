
SELECT staff.id as id, staff.full_name as fullName, specialization.name as spec,
       sector.name as sector,
       extract(year from age(current_date,staff.birthdate)) as age,
       staff.salary as salary,
       staff.brigade_id as brigade,
       avg_brigade_salary,sum_brigade_salary
FROM
    (select * from
        (select id, full_name, birthdate, salary, staff.brigade_id, specialization_id, sector_id, avg_brigade_salary,sum_brigade_salary
        from
            (select brigade.id as brigade_id, cast(avg(salary) as int) as avg_brigade_salary, sum(salary) as sum_brigade_salary from
                brigade inner join staff on brigade.id = staff.brigade_id
                where (:sector_id is null or brigade.sector_id = :sector_id)
                  and (:train_id is null or
                    brigade.id = (select train.train_brigade_id from train where (train.id = :train_id)) or
                    brigade.id =(select train.repair_brigade_id from train where (train.id = :train_id)))
                group by brigade.id
                having  (:min_avg_salary is null or cast(avg(salary) as int) > :min_avg_salary)
                   and (:max_avg_salary is null or cast(avg(salary) as int) < :max_avg_salary)
                   and (:min_sum_salary is null or sum(salary) > :min_sum_salary)
                   and (:max_sum_salary is null or sum(salary) < :max_sum_salary)
            ) as brigade
            inner join staff on staff.brigade_id = brigade.brigade_id
        ) as staff
        where (:min_age is null or extract(year from age(current_date, staff.birthdate)) > :min_age)
        and (:max_age is null or extract(year from age(current_date, staff.birthdate)) < :max_age)
        and (:min_salary is null or staff.salary > :min_salary)
        and (:max_salary is null or staff.salary < :max_salary)
    ) as staff
    left join specialization on staff.specialization_id = specialization.id
    left join sector on staff.sector_id = sector.id
