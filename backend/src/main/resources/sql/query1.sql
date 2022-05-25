SELECT staff.id as id, staff.full_name as fullName, specialization.name as spec,
       sector.name as sector,
       extract(year from age(current_date,staff.hire_date)) as years_experienceas,
       extract(year from age(current_date,staff.birthdate)) as age,
       staff.salary as salary
FROM
    staff
    left join specialization on staff.specialization_id = specialization.id
    left join sector on staff.sector_id = sector.id

where
    (:spec_id is null or staff.specialization_id = :spec_id)
  and
    (:sector_id is null or staff.sector_id = :sector_id)
  and
    (:years_experience is null or  extract(year from age(current_date,staff.hire_date)) > :years_experience)
  and
    (:is_male is null or staff.is_male = :is_male)
  and
    (:age is null or extract(year from age(current_date, staff.birthdate)) > :age)
  and
    (:min_salary is null or staff.salary > :min_salary)
  and
    (:max_salary is null or staff.salary < :max_salary)