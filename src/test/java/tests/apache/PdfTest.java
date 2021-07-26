package tests.apache;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PdfTest {

    @Test
    void pdfFileTest() throws IOException {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("doc/pdfSample.pdf");
        PDDocument pdfFile = PDDocument.load(stream);
        String pdfText = new PDFTextStripper().getText(pdfFile);
        assertThat(pdfText).contains("Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing \r\nelit. Nunc ac faucibus odio.");
    }
}
