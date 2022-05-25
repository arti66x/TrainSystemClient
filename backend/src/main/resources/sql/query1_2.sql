SELECT staff.id as id, staff.full_name as fullName, specialization.name as spec,
       sector.name as sector,
       extract(year from age(current_date,staff.hire_date)) as years_experienceas,
       extract(year from age(current_date,staff.birthdate)) as age,
       staff.salary as salary
FROM
    sector
        inner join staff on sector.administrator_id = staff.id
        left join specialization on staff.specialization_id = specialization.id
where
    (:sect_admin is null or sector.id = :sect_admin)