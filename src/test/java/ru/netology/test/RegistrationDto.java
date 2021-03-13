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



    public static String newLogin(Faker faker) {
        return faker.name().firstName();
    }
    public static String newPassword(Faker faker){
        return faker.internet().password();
    }
    public static String getStatus(){
        String[] status = {"active","blocked"};
        int currentStatus = new Random().nextInt(status.length);
        return status[currentStatus];
    }

}



