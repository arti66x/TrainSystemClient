select id, full_name, extract(year from age(current_date,staff.birthdate)) as age, salary,
       (select name from specialization where id = 1) as specialization
from
    staff
where (staff.specialization_id = 1)
  and (:is_male is null or staff.is_male = :is_male)
  and (:min_age is null or extract(year from age(current_date, staff.birthdate)) > :min_age)
  and (:max_age is null or extract(year from age(current_date, staff.birthdate)) < :max_age)
  and (:min_salary is null or staff.salary > :min_salary)
  and (:max_salary is null or staff.salary < :max_salary)