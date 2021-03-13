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
    RegistrationDto user = new RegistrationDto();
    Faker faker = new Faker(Locale.ENGLISH);
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @BeforeAll
    static void setUpAll() {
        RestAssured.given()
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new RegistrationDto()) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

    @Test
    void shouldValidUser() {
        open("http://localhost:9999");
        $("[type='text']").setValue(user.newLogin(faker));
        $("[type='password']").setValue(user.newPassword(faker));
        $(".button__text").click();
        $(withText("Личный кабинет")).shouldBe(visible, Duration.ofSeconds(15));


    }

}





