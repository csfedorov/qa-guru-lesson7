package tests.codeborn;

import com.codeborne.pdftest.PDF;
import org.apache.commons.io.FileUtils;
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

public class PdfTest {

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
    void pdfFileTest() throws IOException {

        open("/sample-documents-download/sample-pdf-download/");
        File downloadedPdfFile = $(byAttribute("download", "file-sample_150kB.pdf"))
                .scrollTo().download();
        PDF parsedPdf = new PDF(downloadedPdfFile);
        assertThat(parsedPdf.text).contains("Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing \r\nelit. Nunc ac faucibus odio.");
    }
}
