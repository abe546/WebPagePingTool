import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

//Get a provided webpage, download it to the directory
public class UtilityClass {

    private URL url = null;
    private InputStream inputStream = null;
    private BufferedReader bufferedReader = null;
    private String line;
    private FileWriter fileWriter;
    private BufferedReader bufferedReader1 = null;
    private BufferedReader bufferedReader2 = null;
    private String readLine1 = null;
    private String readLine2 = null;
    private FileReader fileReader1;
    private FileReader fileReader2;

    /**
     * Reach out to website and download it to a file
     *
     * @param website
     * @param fileName
     * @return
     * @throws IOException
     */
    public String getWebPageAndSaveToFile(String website, String fileName) throws IOException {
        try {
            url = new URL(website);
            inputStream = url.openStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = bufferedReader.readLine()) != null) {
                //Now write to the filename line by line

                fileWriter = new FileWriter(fileName, true);
                fileWriter.write(line);
            }

            fileWriter.close();

        } catch (MalformedURLException e) {
            System.err.println("URL MALFORMED");
            e.printStackTrace();
        }

        return fileName;
    }

    /**
     * Compare the contents of two files, if they are the same return true
     *
     * @param fileName1
     * @param fileName2
     * @return
     * @throws IOException
     */
    public boolean compareFiles(String fileName1, String fileName2) throws IOException {
        //If the fileName is the same for each, return false
        //Only want to compare different files and see if they contain the same content
        if (fileName1.equals(fileName2)) {
            throw new IllegalArgumentException();
        }

        fileReader1 = new FileReader(fileName1);
        fileReader2 = new FileReader((fileName2));

        bufferedReader1 = new BufferedReader(fileReader1);
        bufferedReader2 = new BufferedReader(fileReader2);

        while ((readLine1 = bufferedReader1.readLine()) != null & (readLine2 = bufferedReader2.readLine()) != null) {
            if (readLine1.length() != readLine2.length()) {
                return false;
            }

            if (readLine1.hashCode() != readLine2.hashCode()) {
                return false;
            }

            if (!readLine1.equals(readLine2)) {
                return false;
            }
        }

        //If by the end of the for loop one item is NOT null, that means the files aren't considered equal
        //Return false;
        if (readLine1 != null || readLine2 != null) {
            return false;
        }

        return true;
    }
}
