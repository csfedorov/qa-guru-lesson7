package tests;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DocxTestWithPoi {

    @Test
    void docxFileTest() throws Exception {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("myDoc.docx");
        assert stream != null;
        XWPFDocument docxFile = new XWPFDocument(stream);
        List<XWPFParagraph> paragraphs = docxFile.getParagraphs();
        assertThat(paragraphs.get(2).getText())
                .contains("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac faucibus odio.");
    }
}
