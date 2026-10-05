package utilities;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvReader {

    public static List<String[]> csvReadFile(String filename) throws IOException, CsvException {

        FileReader fileReader = new FileReader(filename);
        CSVReader reader = new CSVReader(fileReader);

        List<String[]> data = reader.readAll();
        data.remove(0);
        return data;

    }

}
