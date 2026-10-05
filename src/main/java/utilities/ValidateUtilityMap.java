package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class ValidateUtilityMap {

    public static void validateResponseMap(String expectedPayload, Map<String,Object> actualMap) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> expectedMap = mapper.readValue(expectedPayload,Map.class);

        compareMaps(expectedMap,actualMap,"");

    }

    private static void compareMaps(Map<String,Object> expectedMap, Map<String,Object> actualMap,String path){

        for(Map.Entry<String,Object> entry : expectedMap.entrySet()){
            String key = entry.getKey();
            Object expectedValue = entry.getValue();
            Object actualValue = actualMap.get(key);
            String currentPath =
                    path.isEmpty()
                            ? key
                            : path + "." + key;

            if(expectedValue instanceof Map){
                compareMaps(
                        (Map<String, Object>) expectedValue,
                        (Map<String, Object>) actualValue,
                        currentPath
                );
            }else{
                if (!expectedValue.equals(actualValue)) {
                    throw new AssertionError(
                            "Value mismatch at: " + currentPath
                                    + "\nExpected: " + expectedValue
                                    + "\nActual: " + actualValue
                    );
                }
            }
        }
    }
}
