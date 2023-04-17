
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestCase {

    public static void main(String[] args) {

        RestAssured.baseURI= "https://petstore.swagger.io";

        int petId = 1;

        //Insert Pet
        given().header("Content-Type", "application/json").body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"doggie\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 1,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}").
                log().all().when().post("v2/pet/").then().assertThat().statusCode(200);

        //Get and Assert Pet
        given().log().all().when().get("v2/pet/1")
                .then().log().all().assertThat().statusCode(200).
                body("name",equalTo("doggie"));



        //Update Pet
        given().log().all().header("Content-Type", "application/json").body("{\n" +
                "  \"id\": 1,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"newpuppy\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}").
                log().all().when().put("v2/pet/").then().assertThat().statusCode(200);


        //Get with new name and Assertion
        given().log().all().when().get("v2/pet/1")
                .then().log().all().assertThat().statusCode(200).
                body("name",equalTo("newpuppy"));


        //Delete Pet
        given().log().all().when().delete("v2/pet/1")
                .then().log().all().assertThat().statusCode(200);


    }
}
