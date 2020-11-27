import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.io.IOException;

public class UtilityClassTest {

    private SoftAssertions softly = new SoftAssertions();
    private UtilityClass utilityClass = new UtilityClass();
    private String file1 = "src/test/resources/testFile1";
    private String file2 = "src/test/resources/testFile2";
    private String file3 = "src/test/resources/testFile3";
    private String file4 = "src/test/resources/testFile4";

    @Test
    public void comparingTheSameFileThrowsException() throws IOException {

        softly.assertThatThrownBy(() -> utilityClass.compareFiles(file1, file1))
                .isInstanceOf(IllegalArgumentException.class);

        softly.assertAll();
    }

    @Test
    public void compareTwoFilesWithTheSameContent() throws IOException {
        softly.assertThat(utilityClass.compareFiles(file1, file2)).isEqualTo(true);

        softly.assertAll();
    }

    @Test
    public void compareTwoFilesWithDifferentContent() throws IOException {
        softly.assertThat(utilityClass.compareFiles(file1, file4)).isEqualTo(false);

        softly.assertAll();
    }

    @Test
    public void compareTwoFilesWithSameContentToPointButNotEqualNumberOfWords() throws IOException {
        softly.assertThat(utilityClass.compareFiles(file1, file3)).isEqualTo(false);

        softly.assertAll();
    }
}
