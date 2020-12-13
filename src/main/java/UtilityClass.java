import org.apache.log4j.Logger;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

//Get a provided webpage, download it to the directory
public class UtilityClass {

    private static Logger logger = Logger.getLogger(UtilityClass.class);

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

    public boolean pingSite(File dir, String website, String fileName1, String fileName2, String directory, String alertFile) throws IOException {
        //Check to see if two files exist in resources, if two exist delete the oldest and replace it
        //with the latest site change
        boolean result = true;
        int size = dir.list().length;

        if (size == 0) {
            logger.info("There are less than two files in the resources directory");
            getWebPageAndSaveToFile(website, fileName1, false);
        } else if (size == 1) {
            getWebPageAndSaveToFile(website, fileName2, false);
        }

        if (size > 2) {
            logger.info("There is more than 2 files in the resource directory, this is unexpected");
            throw new IllegalArgumentException();
        }

        if (size == 2) {
            //We have two files, now we will get the oldest file, delete it, and replace its contents with
            //the latest information
            File[] files = new File[size];
            files = dir.listFiles();
            Arrays.sort(files, new FileDateComparator());

            String tmpFileName = files[0].getName();

            getWebPageAndSaveToFile(
                    website, String.format("%s/%s",directory,tmpFileName), false);

            //Because will will only ever be using the class defined filenames, we can reference them here
            //Without dynamically getting them
            result = compareFiles(fileName1, fileName2);

            if(result == false)
            {
                //The contents of these files are not the same. Alert the
                //parties that the page has changed

                 alertParties(alertFile, website);
            }

        }

        return result;
    }

    /**
     * Provided a list of emails (separated by new linw) send an email to each alerting them
     * that the provided website has had a recent change
     * @param alertFile
     * @param website
     */
    private void alertParties(String alertFile, String website)
    {
        File file = new File(alertFile);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){

            while ((line = bufferedReader.readLine()) != null) {
                //Now we want to email signifying the page has changed

            }

        } catch (IOException exception)
        {

        }

    }


    /**
     * Reach out to website and download it to a file
     *
     * @param website
     * @param fileName
     * @return
     * @throws IOException
     */
    public String getWebPageAndSaveToFile(String website, String fileName, boolean append) throws IOException {
        try {
            url = new URL(website);
            inputStream = url.openStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = bufferedReader.readLine()) != null) {
                //Now write to the filename line by line

                fileWriter = new FileWriter(fileName, append);
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
