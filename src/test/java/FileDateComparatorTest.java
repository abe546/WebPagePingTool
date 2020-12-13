import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class FileDateComparatorTest {

    private SoftAssertions softly = new SoftAssertions();
    private FileDateComparator fileDateComparator = new FileDateComparator();
    private String dir =  "src/test/resources/fileDateTest";
    private File directory = new File(dir);
    private String testData1 = "test1";
    private String testData2 = "test2";
    private String testData3 = "test3";
    private FileWriter fileWriter;
    private File[] files = new File[3];

    @Before
    public void before() throws IOException, InterruptedException {
        //Create several files in resource directory

        fileWriter = new FileWriter(String.format("%s/%s",dir,testData1), false);
        fileWriter.write(testData1);

        Thread.sleep(500);

        fileWriter = new FileWriter(String.format("%s/%s",dir,testData2), false);
        fileWriter.write(testData2);

        Thread.sleep(500);

        fileWriter = new FileWriter(String.format("%s/%s",dir,testData3), false);
        fileWriter.write(testData3);

        Thread.sleep(500);
    }

    @Test
    public void sortingFilesOfDirectoryShouldBeDoneFromOldestToNewest()
    {
        files = directory.listFiles();

        Arrays.sort(files, new FileDateComparator());

        for(int i=0; i<files.length-1; i++)
        {
            File item = files[i];
            File item2 = files[i+1];

            softly.assertThat(item.lastModified() > item2.lastModified()).isEqualTo(true);
        }

        softly.assertAll();
    }
}
