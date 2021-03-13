package ru.netology.test;


import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Locale;
import java.util.Random;

@Data
@Value
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    Faker faker = new Faker(new Locale("en"));

    public static String[] userGenerate(Faker faker) {
        String  login = faker.name().firstName();
        String password = faker.internet().password();
        String userStatus = RegistrationDto.getStatus();
        String[] user = {login, password, userStatus};
        return user;
    }

    public static String validLogin(String[] user){
        String validUser = user[0];
        return validUser;

    }
    public static String validPassword(String[] user){
            String validPassword = user[1];
            return validPassword;
        }
    public static String validStatus(String[] user){
        String validStatus = user[2];
        return validStatus;
    }

    /*public static String newLogin(Faker faker) {
        return faker.name().firstName();
    }
*/
    /*public static String newPassword(Faker faker) {
        return faker.internet().password();
    }*/

    public static String getStatus() {
        String[] status = {"active", "blocked"};
        int currentStatus = new Random().nextInt(status.length);
        return status[currentStatus];
    }


}



