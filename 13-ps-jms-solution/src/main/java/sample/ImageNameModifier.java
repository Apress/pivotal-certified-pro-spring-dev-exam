package sample;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by iuliana.cosmina on 10/31/16.
 */
public class ImageNameModifier {

    public static void main(String[] args) {
        File f = new File("/Volumes/storage/Users/iuliana.grajdeanu/apress/book-spring-core/9781484208120_Chapter_07/images/");
        File[] images = f.listFiles();
        List<File> fileList = new ArrayList<>();

        boolean found = false;

        for (File image:  images) {
            if (image.getName().equalsIgnoreCase("9781484208120_Figure_07-09.png")){
                found = true;
            }
            if(found){
                fileList.add(image);
            }
        }
        for (ListIterator<File> li = fileList.listIterator(fileList.size()); li.hasPrevious(); ) {
            File entry = li.previous();
            System.out.println(entry.getAbsolutePath());

            String name = entry.getName();
            System.out.println(name);
            String oldNoString = name.substring(name.indexOf("-")+1, name.indexOf("."));
            System.out.println(oldNoString);
            int oldNo =  Integer.parseInt(oldNoString);
            int newNo = oldNo+1;
            String newNoString = ((newNo+"").length() ==1) ? "0" + newNo : newNo +"";
            System.out.println(newNoString);
            String newname = name.replace("-" + oldNoString +".", "-" + newNoString + ".");
            System.out.println(newname);
            File output =  new File(entry.getAbsolutePath().replace(name, newname));
            entry.renameTo(output);
        }

    }
}
