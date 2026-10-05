package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Iterator;
import java.util.Map;

public class ValidateUtility {

    public static void validateResponse(String expectedPayload, Map<String,Object> actualPayload) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        // Convert expected JSON String -> JsonNode
        JsonNode expectedNode = mapper.readTree(expectedPayload);

        // Convert actual Map -> JsonNode
        JsonNode actualNode = mapper.valueToTree(actualPayload);

        compareJson(expectedNode,actualNode,"");

    }

    private static void compareJson(JsonNode expected, JsonNode actual, String path){

        Iterator<Map.Entry<String, JsonNode>> fields = expected.fields();
        while (fields.hasNext()){
            Map.Entry<String, JsonNode> field = fields.next();
            String fieldName = field.getKey();
            JsonNode expectedValue = field.getValue();
            JsonNode actualValue =  actual.get(fieldName);

            if(expectedValue.isObject()){
                compareJson(expected,actual,path);
            }else {
            if(!expectedValue.equals(actualValue)){
                throw new AssertionError("Value Mismatch");
            }
            }
        }
    }
}
