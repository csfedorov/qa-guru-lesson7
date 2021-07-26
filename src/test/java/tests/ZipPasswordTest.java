package tests;

import net.lingala.zip4j.io.inputstream.ZipInputStream;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ZipPasswordTest {

    static final String password = "123qwe";
    static final Charset charset = StandardCharsets.UTF_8;

    @Test
    void zipFileTest() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("zip/text-pass.zip");
        String entryAsString = null;
        try (ZipInputStream zipStream = new ZipInputStream(stream, password.toCharArray(), charset)) {
            while (zipStream.getNextEntry() != null) {
                entryAsString = IOUtils.toString(zipStream, charset);
            }
        }
        assertThat(entryAsString).contains("Vestibulum neque massa, scelerisque sit amet ligula eu, congue molestie mi.");
    }
}
