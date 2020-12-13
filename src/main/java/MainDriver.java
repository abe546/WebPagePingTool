import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class MainDriver {

    private static Logger logger = Logger.getLogger(MainDriver.class);

    private static final String directory = "src/main/resources";
    private static final String fileName1 = "src/main/resources/file1";
    private static final String fileName2 = "src/main/resources/file2";
    private static File dir = new File(directory);
    private static UtilityClass utilityClass = new UtilityClass();
    private static String tmpFileName = null;
    private static final String website = System.getenv().getOrDefault(
            "WEBSITE_URL",
            "https://affil.walmart.com/cart/addToCart?items=363472942,493824815,443574645,606518560"
    );
    private static File[] files = new File[2];

    public static void main(String[] args) throws IOException {

    }
}
