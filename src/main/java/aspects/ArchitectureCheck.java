package aspects;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


public class ArchitectureCheck {

    private static Logger log = Logger.getLogger(ArchitectureCheck.class);

    public static Boolean existsSystemOutPrintLnInModel(String proyectPath){

        Boolean existsPrintln = false;

        File path = new File(new File("").getAbsolutePath() + proyectPath);
        SuffixFileFilter extFilter = new SuffixFileFilter("java");
        Collection filesListUtil = FileUtils.listFiles(path, extFilter, TrueFileFilter.INSTANCE);
        ArrayList<File> filesList = new ArrayList<File>();

        try {
            for (Object currentF : filesListUtil) {
                filesList.add((File) currentF);
            }

            for (File f : filesList) {
                String s = FileUtils.readFileToString(f);
                if (s.contains("System.out.println")) {
                    existsPrintln = true;
                    break;
                }
            }
        }catch (IOException ioe){
            log.error(ioe.getMessage());
        }

        return existsPrintln;
    }
}
