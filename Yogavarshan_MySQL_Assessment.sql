USE sql_employees;

-- 1. Retrieve employees with the highest salary

SELECT * 
FROM employee e
WHERE e.employee_id = (SELECT s.employee_id
					 FROM salary s
                     ORDER BY salary_amount DESC
                     LIMIT 1);
                     
                     

-- 2.Retrieve employees who have a salary higher than the average salary

SELECT e.employee_id,e.employee_name
FROM employee e
JOIN salary s
     ON e.employee_id = s.salary_id
WHERE s.salary_amount > (SELECT AVG(salary_amount)
						 FROM salary);
                    
-- 3. Retrieve employees who are managers

SELECT employee_id,employee_name
FROM employee
JOIN department
	 ON employee_id = dept_manager_id;
				
-- 4. Retrieve employees who are not managers

SELECT *
FROM employee
WHERE NOT job_title = 'Manager';                

-- 5. List the employees who lives in the city - Chennai

SELECT e.employee_id,e.employee_name,e.job_title
FROM employee e
JOIN address a
	 ON e.employee_id = a.employee_id
WHERE a.city = 'Chennai';     

-- 6. List the employees in a specific department eg. Engineering

SELECT e.employee_id,e.employee_name,d.dept_name 
FROM employee e
JOIN department d
	 ON e.dept_id = d.dept_id
WHERE d.dept_name = 'Engineering';     

-- 7. List the employees reporting to the manager

SELECT *
FROM employee
WHERE report_to = 'Manager'; 

-- 8. List the employees group by department

SELECT e.employee_name,d.dept_name
FROM employee e
LEFT JOIN department d
     ON e.dept_id = d.dept_id ;    
     
-- 9. List the departments with count of employees hired in last month

SELECT e.employee_name,d.dept_name,e.hired_date
FROM employee e
LEFT JOIN department d
     ON e.dept_id = d.dept_id
WHERE e.hired_date BETWEEN '2023-05-01' AND '2023-05-31'; 

-- 10. List the employees whose salary is more than 4L

SELECT e.employee_name, s.salary_amount
FROM employee e
JOIN salary s    
	 ON e.employee_id = s.employee_id
WHERE s.salary_amount > 400000;

-- 11. List the employees and their department whose salary is more than 4L

SELECT *
FROM employee e
JOIN department d
	 ON e.dept_id = d.dept_id
JOIN salary s    
	 ON e.employee_id = s.employee_id
WHERE s.salary_amount > 400000;   

-- 12. List the employees who are on leave for more than 10 consecutive days

SELECT e.employee_name, (ld.end_date - ld.start_date) AS 'leave days count'
FROM employee e
JOIN leave_details ld
	 ON e.employee_id = ld.employee_id
WHERE (ld.end_date - ld.start_date) > 10;     

USE sql_employees;
-- 13. List of employees in the Sales department who have a salary higher than 3L along with their attendance days.

SELECT e.employee_name, DATEDIFF('2023-05-31','2023-01-01') - DATEDIFF(ld.end_date,ld.start_date) as 'Attendance Days'
FROM employee e
JOIN department d
	 ON e.dept_id = d.dept_id
JOIN salary s
     ON e.employee_id = s.employee_id
JOIN leave_details ld
	 ON e.employee_id = ld.employee_id
WHERE d.dept_name = 'Engineering' AND s.salary_amount > 300000;






-- 14. List the employees with their total salary for the specified year e.g. 2023

SELECT e.employee_name, SUM(s.salary_amount) AS Salary
FROM employee e
JOIN salary s
     ON e.employee_id = s.employee_id
WHERE YEAR(s.start_date) = 2023
GROUP BY e.employee_id, e.employee_name;     