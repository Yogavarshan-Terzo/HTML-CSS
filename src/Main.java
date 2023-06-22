

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static Date getDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws SQLException {
        String jdbcUrl = "jdbc:mariadb://localhost:3306/terzo_employees";
        String username = "root";
        String password = "root";
        ResultSet resultSet = null;
        try {
            // Register the MariaDB JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");
            // Establish the database connection
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement stmt = connection.createStatement();
            // Connection successful
            System.out.println("Connected to the database!");
            String query = "SELECT * FROM Employees";
            resultSet = stmt.executeQuery(query);
            // Perform database operations...
            // Close the connection
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Employee> employeeList = new ArrayList<>();

        while(resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int salary = resultSet.getInt(3);
            String department = resultSet.getString(4);
            Date joiningDate = resultSet.getDate(5);
            String mobileNo = resultSet.getString(6);
            Employee tempEmp = new Employee(id, name, salary, department, joiningDate, mobileNo);
            employeeList.add(tempEmp);
        }

        List<Employee> empList = new ArrayList<>();

        //Exercise 1
        System.out.println("1. Filter the Employee with Name starts with \"D\"");
        employeeList.stream()
                .filter(e -> e.name.startsWith("Y"))
                .forEach(System.out::println);

        //Exercise 2
        System.out.println("2. Filter the Employee who did not have mobile number updated in DB\n");
        employeeList.stream()
                .filter(employee -> employee.mobileNo == null)
                .forEach(System.out::println);

        //Exercise 3
        empList = employeeList.stream()
                .filter(employee -> employee.department.equals("QA") && employee.salary > 10000)
                .collect(Collectors.toList());
        System.out.println("3. Obtain a list of employees belongs to category “QA” with salary > 10000" + empList);


        //Exercise 4
        empList = employeeList.stream()
                .filter(e -> e.department.equals("IT"))
                .collect(Collectors.toList());
        System.out.println("4. Obtain a list of employees with products belong to department “IT”\n" + empList);

        //Exercise 5
        empList = employeeList.stream()
                .filter(employee -> employee.department.equals("DEV"))
                .map(employee -> new Employee(employee.id,employee.name,(int) (employee.salary+(0.1*employee.salary)),employee.department,employee.joiningDate,employee.mobileNo))
                .collect(Collectors.toList());
        System.out.println("5. Obtain a list of employees with department = “DEV” and then apply 10% increment in the salary" + empList);
        employeeList.stream().close();

        //Exercise 6
        empList = employeeList.stream()
                .filter(employee -> {
                    Date startDate = getDate("2021-02-01");
                    Date endDate = getDate("2021-04-01");
                    Date joiningDate = employee.joiningDate;
                    return joiningDate.after(startDate) && joiningDate.before(endDate);
                })
                .collect(Collectors.toList());
        System.out.println("6. Obtain a list of employees joined between 01-Feb-2021 and 01-Apr-2021" + empList +"\n");

        //Exercise 7
        System.out.println("7. Get the lowest salary of employee");
        employeeList.stream()
                .mapToInt(Employee::getSalary)
                .min()
                .ifPresent(System.out::println);

        //Exercise 8
        System.out.println("8. Calculate average salary for employee joined on 14-Mar-2021");
        Date date = getDate("2021-02-14");
        employeeList.stream()
                .filter(employee -> employee.joiningDate.equals(date))
                .mapToInt(employee -> employee.salary)
                .average()
                .ifPresent(System.out::println);

        //Exercise 9
        empList = employeeList.stream()
                .sorted(Comparator.comparing(Employee::getJoiningDate)
                .reversed())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("9. Get the 3 most recently joined\n" + empList+"\n");

        //Exercise 10
        Date startDate = getDate("2021-01-31");
        Date endDate = getDate("2021-02-29");
        System.out.println("10. Calculate total  sum of all salary joined in Feb 2021\n" +
                employeeList.stream()
                .filter(employee -> employee.joiningDate.after(startDate)
                        && employee.joiningDate.before(endDate))
                .mapToInt(Employee::getSalary)
                .sum()+"\n");




        //Exercise 11
         Map<Integer,Long> map = new HashMap<>();
        map = employeeList.stream().collect(Collectors.groupingBy(Employee::getSalary,Collectors.counting()));
        System.out.println("11. Obtain a data map with  Salary and employees count" + map +"\n");

        //Exercise 12
        Map<String,List<Employee>> map4 = new HashMap<>();
        map4 = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println("12. Produce a data map with employee records grouped by department"+map4+"\n");


        //Exercise 13
        Map<String,Integer> map3 = new HashMap<>();
        map3 = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.summingInt(Employee::getSalary)));
        System.out.println("13. Produce a data map with department and their salary\n"+map3+"\n");


        //Exercise 14
        System.out.println("14. Get the most highest paid by category\n");
        Map<String,List<Employee>> map2 = new HashMap<>();
        map2 = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        int maxSalary = Integer.MIN_VALUE;
        String dept = "";
        Iterator<Map.Entry<String, List<Employee>>> itr = map2.entrySet().iterator();

        while(itr.hasNext())
        {
            Map.Entry<String, List<Employee>> entry = itr.next();
            List<Employee> empList1 = entry.getValue();
            int temp = empList1.stream()
                    .mapToInt(Employee::getSalary)
                    .sum();
            if(temp > maxSalary){
                maxSalary = temp;
                dept = entry.getKey();
            }
        }
        System.out.println(dept+" "+maxSalary+"\n");

        //Exercise 15
        Map<Integer,Long> map1 = new HashMap<>();

        map1 = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getSalary,Collectors.counting()));

        Iterator<Map.Entry<Integer, Long>> itr1 = map1.entrySet().iterator();
        System.out.println("Obtain a data map with  Salary and employees count\n");
        while (itr1.hasNext())
        {
           Map.Entry<Integer, Long> entry = itr1.next();
           System.out.println(entry.getKey()+" "+entry.getValue());
        }

    }
}

