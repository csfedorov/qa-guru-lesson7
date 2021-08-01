package tests.apache;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class XlsxTest {

    @Test
    void xlsxFileTest() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("doc/xlsSample.xlsx");
        assert stream != null;
        XSSFWorkbook xlsFile = new XSSFWorkbook(stream);
        Row row = xlsFile.getSheetAt(0).getRow(1);
        String firstName = row.getCell(1).getStringCellValue();
        String lastName = row.getCell(2).getStringCellValue();
        String country = row.getCell(4).getStringCellValue();
        assertThat(firstName).isEqualTo("Dulce");
        assertThat(lastName).isEqualTo("Abril");
        assertThat(country).isEqualTo("United States");
    }
}
