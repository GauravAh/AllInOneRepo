package testngdataprovider;

import csvpojo.PojoClass;
import org.apache.poi.ss.formula.functions.T;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import utilities.CsvReaderPojo;

import java.io.FileNotFoundException;
import java.util.List;

public class CsvDataprovider {

    @DataProvider(name = "CsvData")
    public Object[][] readCsvDataProvider(ITestContext context) throws ClassNotFoundException, FileNotFoundException {

        String csvFilePath = context.getCurrentXmlTest().getParameter("filePath");
        Class<?> getPojoClass = Class.forName(context.getCurrentXmlTest().getParameter("csvPojo"));

        List<?> readCsvData = CsvReaderPojo.readCsv(System.getProperty("user.dir") + csvFilePath, getPojoClass);
        Object[][] ob = new Object[readCsvData.size()][1];
        for(int i =0; i<readCsvData.size(); i++){
            ob[i][0] = readCsvData.get(i);
        }
        return ob;
    }

}
