package utilities;

/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 04/12/2022
 */


import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.onwumere.bright.employeemanagementsystem.model.Employee;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RandomEmployee {

    /* This class cannot be instantiated*/
    private RandomEmployee(){}

    /**
     * @return A new random Employee
     */
    public static Employee getRandomEmployee(){
        SecureRandom secureRandom = new SecureRandom();
        Faker faker = new Faker();
        Name name = faker.name();

        int empId = faker.number().numberBetween(100000, 900000);
        String firstName = name.firstName();
        String lastName = name.lastName();
        String email = firstName + "." + lastName + "@gmail.com";
        int year = secureRandom.nextInt(1960,1998);
        int month = secureRandom.nextInt(1,13);
        int dayBound = switch(month){
            case 1, 3, 5, 7, 8, 10, 12 -> 32;
            case 2 -> 29;
            default -> 31;
        };
        int day = secureRandom.nextInt(1,dayBound);
        int salary = secureRandom.nextInt(100000,900001);
        String ssn = faker.idNumber().ssnValid();

        String phoneNo = "+1 " + "(" + faker.number().numberBetween(100, 550) + ") " + faker.number().numberBetween(580, 1000) + "-" + faker.number().numberBetween(1000, 10000);
        /* null would make the database to generate the IDs for us. */
        return new Employee(null, empId, firstName, lastName, email.toLowerCase(), LocalDate.of(year, month, day), new BigDecimal(salary), ssn,  phoneNo);

    }

    public static List<Employee> getRandomEmployees(int num){
        List<Employee> randomEmployees = new ArrayList<>();
        for(int i = 1; i <= num; i++){
            randomEmployees.add(getRandomEmployee());
        }
        return randomEmployees;
    }

}
