package tests;

import base.RequestFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.exceptions.CsvException;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import testngdataprovider.CsvDataprovider;
import utilities.ConfigUtility;
import utilities.ReplacePayloadUtility;
import utilities.ValidateUtility;
import utilities.ValidateUtilityMap;

import java.io.IOException;
import java.util.Map;

public class CreateTest {

    public static String getapiUrl(){
       return ConfigUtility.getProp("apiurl");
    }

    public static String getapiEndpoint(){
       return ConfigUtility.getProp("apiendpoint");
    }

    @Test(dataProvider = "CsvData", dataProviderClass = CsvDataprovider.class)
    public void runTest(Object data) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String jsonData = mapper.writeValueAsString(data);
        // Remember, if we donot define getMethod in pojo class, it will not print the jsonString(jsonData)

        String jsonPayload =
                "{\n" +
                "  \"firstname\" : \"{firstname}\",\n" +
                "  \"lastname\" : \"{lastname}\",\n" +
                "  \"totalprice\" : \"{totalprice}\",\n" +
                "  \"depositpaid\" : \"{depositpaid}\",\n" +
                "  \"bookingdates\" : {\n" +
                "    \"checkin\" : \"{checkin}\",\n" +
                "    \"checkout\" : \"{checkout}\"\n" +
                "  },\n" +
                "  \"additionalneeds\" : \"{additionalneeds}\"\n" +
                "}";

       String expectedPayloadData =  ReplacePayloadUtility.replacePlaceHolder(jsonPayload,jsonData);

        Response response = RequestFactory.getSpecification()
                .body(expectedPayloadData)
                .when()
                .post(ConfigUtility.getProp("apiendpoint"));

        String jsonVal = response.asPrettyString();
        System.out.println("Response is.." + response.asPrettyString());
        Map<String,Object> actualPayloadData = mapper.readValue(jsonVal, Map.class);

        ValidateUtilityMap.validateResponseMap(expectedPayloadData, actualPayloadData);


    }

}

