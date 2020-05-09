package cn.cloud.ego.base.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FilePathUtils {

    public static String getFilePath(){

        Date now = new Date();

        String year=new SimpleDateFormat("yyyy").format(now);
        String month=new SimpleDateFormat("MM").format(now);
        String day=new SimpleDateFormat("dd").format(now);

        String filePath="/"+year+"/"+month+"/"+day+"/";
        return filePath;
    }

}
