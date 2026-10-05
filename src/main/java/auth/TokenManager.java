package auth;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ConfigUtility;

public class TokenManager {

    @Test
    public static String generateToken(){

        Response response = RestAssured.given()
                .baseUri(ConfigUtility.getProp("apiurl"))
                .contentType(ContentType.JSON)
                .body("""
                    {
                      "username": "admin",
                      "password": "password123"
                    }
                    """)
                .when()
                .post("/auth");

        System.out.println("Response is" + response.asPrettyString());
        String tokenName = response.jsonPath().getString("token");

        return tokenName;
    }
}
