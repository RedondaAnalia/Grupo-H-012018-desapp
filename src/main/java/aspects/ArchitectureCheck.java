package aspects;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.aspectj.lang.annotation.Aspect;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

@Aspect
public class ArchitectureCheck {

    private Collection<File> files;

    public Boolean checkSystemOutPrintLnInModel(){
/*
        File rFile = new File(jfc.getSelectedFile().getAbsolutePath());
        SuffixFileFilter extFilter = new SuffixFileFilter("java");
        Collection filesListUtil = FileUtils.listFiles(rFile, extFilter, TrueFileFilter.INSTANCE);
        ArrayList<File> filesList = new ArrayList<File>();
        ArrayList<File> dirsList = new ArrayList<File>();


        for(java.util.Iterator fileIter = filesListUtil.iterator(); fileIter.hasNext();){
            File currentF = (File) fileIter.next();
            if(currentF.isDirectory()){
                dirsList.add(currentF);
            }else{
                filesList.add(currentF);;
            }
        }

        for(File d: dirsList){

*/ return null;
        }

}
