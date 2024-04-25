package com.phonebook.testsRA;

import com.phonebook.dto.AuthRequestDto;
import com.phonebook.dto.ContactDto;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpDataContactTests extends TestBase{

     String id;

    @BeforeMethod
    public void precondition(){
        ContactDto contactDto = ContactDto.builder()
                .name("Karlo")
                .lastName("Olgr")
                .email("lkki56@gm.com")
                .phone("12345678960")
                .address("Berlin")
                .description("jko")
                .build();

        String message =given()
                .header(AUTH,TOKEN)
                .body(contactDto)
                .contentType(ContentType.JSON)
                .post("contacts")
                .then()
                .assertThat().statusCode(200)
                .extract().path("message");

//        System.out.println(message);Contact was added! ID: 97dc4438-e052-4f48-973d-273f18a56ca4

        String[] split = message.split(": ");
        id = split[1];

    }

    @Test
    public void UpDataContactWithValidDataTests(){
        ContactDto contactDtoNew = ContactDto.builder()
                .id(id)
                .name("Karl")
                .lastName("Olg")
                .email("lkki56@gm.com")
                .phone("12345678960")
                .address("Rom")
                .description("jko")
                .build();

         given()
                .header(AUTH, TOKEN)
                .body(contactDtoNew)
                .contentType(ContentType.JSON)
                .put("contacts/")
                .then()
                .assertThat().statusCode(200)
                 .assertThat().body("message", equalTo("Contact was updated"));


    }

    @Test
    public void UpDataContactUnauthorizedTests(){
        ContactDto contactDtoNew = ContactDto.builder()
                .id(id)
                .name("Karl")
                .lastName("Olg")
                .email("lkki56@gm.com")
                .phone("12345678960")
                .address("Rom")
                .description("jko")
                .build();

        given()
                .header(AUTH, TOKEN_BR)
                .body(contactDtoNew)
                .contentType(ContentType.JSON)
                .put("contacts/")
                .then()
                .assertThat().statusCode(401);
//                .assertThat().body("message", equalTo("Contact was updated"));

    }

    @Test
    public void UpDataContactWithoutNameTests(){
        ContactDto contactDtoNew = ContactDto.builder()
                .id(id)

                .lastName("Olg")
                .email("lkki56@gm.com")
                .phone("12345678960")
                .address("Rom")
                .description("jko")
                .build();

        given()
                .header(AUTH, TOKEN)
                .body(contactDtoNew)
                .contentType(ContentType.JSON)
                .put("contacts/")
                .then()
                .assertThat().statusCode(400);
//                .assertThat().body("message", equalTo("Contact was updated"));

    }

}
