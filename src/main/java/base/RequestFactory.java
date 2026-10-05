package base;

import auth.TokenManager;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigUtility;

public class RequestFactory {

    public static RequestSpecification getSpecification(){

        String tokenVal = TokenManager.generateToken();

        System.out.println("Token Value is.." + tokenVal);
        System.out.println("Token Value1 is.." + tokenVal);

        return RestAssured.given()
                .baseUri(ConfigUtility.getProp("apiurl"))
                .header("Accept", "application/json")
                .header("content-type", "application/json")
                .cookie("token", tokenVal);

    }

}
