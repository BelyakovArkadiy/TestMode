package ru.netology.test;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.visible;


public class TestModeTest {
    Faker faker = new Faker(Locale.ENGLISH);
    static RegistrationDto user = new RegistrationDto();



    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @BeforeAll
    static void setUpAll() {
        Faker faker = new Faker(Locale.ENGLISH);
        RegistrationDto user = new RegistrationDto();
        String[] validUser = user.userGenerate(faker);
        RestAssured.given()
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(validUser) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(500); // код 200 OK
    }

    @Test
    void shouldValidUser() {
        open("http://localhost:9999");

        $("[type='text']").setValue(user.validLogin(user.userGenerate(faker)));
        $("[type='password']").setValue(user.validPassword(user.userGenerate(faker)));
        $(".button__text").click();
        $(withText("Ошибка")).shouldBe(visible, Duration.ofSeconds(15));


    }

}





