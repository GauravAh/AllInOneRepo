package utilities;

import constants.ConstantClass;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Properties;

public class ConfigUtility {

    static Properties properties = new Properties();

    static{
        try {
            loadProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadProperties() throws IOException {

        //load base properties
        loadFile(ConstantClass.basePath);

        //load endpoint properties
        loadFile(ConstantClass.endpointPath);

        //get the environment
        String environment =  properties.getProperty("environment");
        if(environment.isEmpty() || environment ==null){
            throw new RuntimeException("environment missing");
        }
        loadFile("config/" + environment + "/system.properties");

    }

    private static void loadFile(String fileName) throws IOException {
        InputStream input = ConfigUtility.class.getClassLoader().getResourceAsStream(fileName);
        if(input == null){
            throw new RuntimeException("File Missing in Resources folder" + fileName);
        }
        properties.load(input);
    }

    public static String getProp(String key){
        return properties.getProperty(key);
    }

}
