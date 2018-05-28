package ar.com.fiuba.tpprof.hubin.util;

public class DocumentUtil {

    public static boolean isAJPG(String originalName) {
        String[] filenameSplit = originalName.split("\\.");
        String extension = filenameSplit[filenameSplit.length - 1];
        if (extension.equals("jpg") || extension.equals("JPG") || extension.equals("jpeg") || extension.equals("JPEG")) {
            return true;
        }
        return false;
    }

}
