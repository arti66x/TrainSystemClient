select staff.id, full_name,extract(year from age(current_date,staff.birthdate)) as age, salary, specialization, cast((count(medical_exam.staff_id) > 0)as varchar) as is_passed from
    (select staff.id, full_name, birthdate, salary,
            (select name from specialization where id = 1) as specialization
    from
         staff
        where (staff.specialization_id = 1)
          and (:is_male is null or staff.is_male = :is_male)
          and (:min_age is null or extract(year from age(current_date, staff.birthdate)) > :min_age)
          and (:max_age is null or extract(year from age(current_date, staff.birthdate)) < :max_age)
          and (:min_salary is null or staff.salary > :min_salary)
          and (:max_salary is null or staff.salary < :max_salary)
    ) as staff
    left join medical_exam on (staff.id = medical_exam.staff_id) and (extract(year from medical_exam.date) = :year)
    group by staff.id, full_name, birthdate, salary, specialization
    having (count(medical_exam.staff_id) > 0 and :is_passed = true) or
        (count(medical_exam.staff_id) = 0 and :is_passed != true)