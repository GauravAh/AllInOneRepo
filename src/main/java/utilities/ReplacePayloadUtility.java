package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Iterator;
import java.util.Map;

public class ReplacePayloadUtility {

    public static String replacePlaceHolder(String jsonPayload, String csvPayloadData) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode nodes = mapper.readTree(csvPayloadData);

        Iterator<Map.Entry<String, JsonNode>> getFields = nodes.fields();
        while (getFields.hasNext()){
            Map.Entry<String, JsonNode> field = getFields.next();
             String keyName = field.getKey();
             JsonNode valueName =  field.getValue();
             if(valueName.isObject()){
                 replacePlaceHolder(jsonPayload,csvPayloadData);
             }else {
                 String placeHolder = "\"{" + keyName + "}\"";
                 String jsonValue = valueName.toString();
                 jsonPayload = jsonPayload.replace(placeHolder,jsonValue);
             }
        }
        return jsonPayload;
    }

}
