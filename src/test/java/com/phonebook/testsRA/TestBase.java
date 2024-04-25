package com.phonebook.testsRA;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    public static final String TOKEN= "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoidGVzdDIwMjRAZ20uY29tIiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE3MTQ2Mzg3ODEsImlhdCI6MTcxNDAzODc4MX0.5JdHYUGbokZ1dAsjagR_DQE2ev1cSL1kgLTIEQrtCRA";
    public static final String AUTH = "Authorization";
    public static final String TOKEN_BR = "789";

    @BeforeMethod
    public void init(){

        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";

    }



}
