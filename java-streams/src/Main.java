

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

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

        //Exercise 1
        employeeList.stream()
                .filter(e -> e.name.startsWith("Y"))
                .forEach(System.out::println);

        //Exercise 2
//        employeeList.stream()
//                .filter(employee -> employee.mobileNo == null)
//                .forEach(System.out::println);

        //Exercise 3
//        List<Employee> empList = new ArrayList<>();
//        empList = employeeList.stream()
//                .filter(employee -> employee.department.equals("QA") && employee.salary > 10000)
//                .collect(Collectors.toList());


        //Exercise 4
//        List<Employee> empList = new ArrayList<>();
//        empList = employeeList.stream()
//                .filter(e -> e.department.equals("IT"))
//                .collect(Collectors.toList());

        //Exercise 5
//        List<Employee> empList = new ArrayList<>();
//        empList = employeeList.stream()
//                .filter(employee -> employee.department.equals("DEV"))
//                .map(employee -> new Employee(employee.id,employee.name,(int) (employee.salary+(0.1*employee.salary)),employee.department,employee.joiningDate,employee.mobileNo))
//                .collect(Collectors.toList());


        //Exercise 6
//        List<Employee> empList = new ArrayList<>();
//        empList = employeeList.stream()
//                .filter(employee -> employee.joiningDate.after(new java.util.Date(2021-02-01)) && employee.joiningDate.before(new java.util.Date("2021-04-01")) )
//                .collect(Collectors.toList());

        //Exercise 7
//        System.out.println(employeeList.stream()
//                .map(employee -> employee.salary)
//                .min(Integer::compare));

        //Exercise 8
//        employeeList.stream()
//                .filter(employee -> employee.joiningDate.equals(new java.util.Date("2021-02-14")))
//                .mapToInt(employee -> employee.salary)
//                .average()
//                .ifPresent(System.out::println);

        //Exercise 9
//        List<Employee> empList = new ArrayList<>();
//        empList = employeeList.stream()
//                .sorted(Comparator.comparing(Employee::getJoiningDate)
//                .reversed())
//                .limit(3)
//                .collect(Collectors.toList());

        //Exercise 10
//        employeeList.stream()
//                .filter(employee -> employee.joiningDate.after(new java.util.Date("2021-01-31"))
//                        && employee.joiningDate.before(new java.util.Date("2021-02-29")))
//                .mapToInt(Employee::getSalary)
//                .sum();

        //Exercise 11
//        Map<String,Long> map = new HashMap<>();
//        map = employeeList.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));

        //Exercise 12
//        Map<String,List<Employee>> map = new HashMap<>();
//        map = employeeList.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment));


        //Exercise 13
//        Map<String,Integer> map = new HashMap<>();
//        map = employeeList.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.summingInt(Employee::getSalary)));

        //Exercise 14
//        Map<String,List<Employee>> map = new HashMap<>();
//        map = employeeList.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment));
//
//
//        int maxSalary = Integer.MIN_VALUE;
//        String dept = "";
//        Iterator<Map.Entry<String, List<Employee>>> itr = map.entrySet().iterator();
//
//        while(itr.hasNext())
//        {
//            Map.Entry<String, List<Employee>> entry = itr.next();
//            List<Employee> empList = entry.getValue();
//            int temp = empList.stream()
//                    .mapToInt(Employee::getSalary)
//                    .sum();
//            if(temp > maxSalary){
//                maxSalary = temp;
//                dept = entry.getKey();
//            }
//        }

        //Exercise 15
        Map<Integer,Long> map = new HashMap<>();

        map = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getSalary,Collectors.counting()));

        Iterator<Map.Entry<Integer, Long>> itr = map.entrySet().iterator();
        while (itr.hasNext())
        {
           Map.Entry<Integer, Long> entry = itr.next();
           System.out.println(entry.getKey()+" "+entry.getValue());
        }


    }
}

