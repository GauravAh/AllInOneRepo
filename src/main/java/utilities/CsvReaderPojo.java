package utilities;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class CsvReaderPojo {

    public static <T> List<T> readCsv(String fileName, Class<T> pojoClass) throws FileNotFoundException {

        FileReader reader = new FileReader(fileName);
        CsvToBean csvToBean = new CsvToBeanBuilder<T>(reader)
                .withType(pojoClass)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        return csvToBean.parse();
    }
}
