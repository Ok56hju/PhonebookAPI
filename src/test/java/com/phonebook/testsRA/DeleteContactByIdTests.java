package com.phonebook.testsRA;

import com.phonebook.dto.ContactDto;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteContactByIdTests extends TestBase {
    String id;

    @BeforeMethod
    public void precondition(){
        ContactDto contactDto = ContactDto.builder()
                .name("Karl")
                .lastName("Olg")
                .email("lki56@gm.com")
                .phone("12345678967")
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
    public void deleteContactByIdSuccessTest(){
        given()
                .header(AUTH, TOKEN)
                .delete("contacts/" + id)
                .then()
                .assertThat().statusCode(200)
//                .extract().path("message");
                .assertThat().body("message", equalTo("Contact was deleted!"));

//        System.out.println(message);

    }

    @Test
    public void deleteContactUnauthorizedTest(){
        given()
                .header(AUTH, TOKEN_BR)
                .delete("contacts/" + id)
                .then()
                .assertThat().statusCode(401);
//                .extract().path("message");
//                .assertThat().body("message", equalTo("Contact was deleted!"));

//        System.out.println(message);

    }
}
