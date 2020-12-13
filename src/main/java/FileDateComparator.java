import java.io.File;
import java.util.Comparator;

public class FileDateComparator implements Comparator {

    @Override
    /**
     * Given a file o1 and o2, give precedence to the oldest file (max sort)
     */
    public int compare(Object o1, Object o2) {
        File a = (File) o1;
        File b = (File) o2;

        if (a.lastModified() > b.lastModified()) {
            return -1;
        } else if (a.lastModified() < b.lastModified()) {
            return 1;
        }
        return 0;
    }
}
