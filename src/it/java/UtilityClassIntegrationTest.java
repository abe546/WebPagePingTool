import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class UtilityClassIntegrationTest {

    private SoftAssertions softly = new SoftAssertions();
    private String website = "https://www.google.com";
    private String dir = "src/it/resources/";
    private String fileName = dir + "testfile_";


    @Test
    public void getGoogleWebPageAndSaveToFile() throws IOException {

        fileName += Instant.now().truncatedTo(ChronoUnit.SECONDS).toString().replaceAll(":", "-");
        UtilityClass getWebPageAndSaveToFile = new UtilityClass();

        getWebPageAndSaveToFile.getWebPageAndSaveToFile(website, fileName, false);

        File file = new File(fileName);

        softly.assertThat(fileName).isEqualTo(dir + file.getName());

        softly.assertAll();
    }

}
