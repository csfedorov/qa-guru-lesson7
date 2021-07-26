package tests;

import com.codeborne.xlstest.XLS;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class XlsTestWithCodeborn {

    static final String PATH_TO_DLD = "src/test/java/downloads";

    @BeforeAll
    static void setup() {
        startMaximized = true;
        baseUrl = "https://file-examples.com/index.php";
        downloadsFolder = PATH_TO_DLD;
    }

    @AfterAll
    static void releaseFiles() throws IOException {
        FileUtils.cleanDirectory(new File(PATH_TO_DLD));
    }

    @Test
    void xlsFileTest() throws IOException {

        open("/sample-documents-download/sample-xls-download/");
        File downloadedXlsFile = $(byAttribute("download", "file_example_XLS_50.xls"))
                .scrollTo().download();
        XLS xlsFile = new XLS(downloadedXlsFile);
        Row row = xlsFile.excel.getSheetAt(0).getRow(1);
        String firstName = row.getCell(1).getStringCellValue();
        String lastName = row.getCell(2).getStringCellValue();
        String country = row.getCell(4).getStringCellValue();
        assertThat(firstName).isEqualTo("Dulce");
        assertThat(lastName).isEqualTo("Abril");
        assertThat(country).isEqualTo("United States");
    }
}
