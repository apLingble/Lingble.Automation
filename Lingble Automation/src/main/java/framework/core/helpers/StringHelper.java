package framework.core.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class StringHelper {

    protected static final Logger LOGGER = LoggerFactory.getLogger(StringHelper.class);
    private static final String NUMERIC_PATTERN = "[0-9]*";

    public static String readToString(String fileName) {

        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];

        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isNumeric(String str){
        if (null == str) {
            return false;
        }
        Pattern pattern = Pattern.compile(NUMERIC_PATTERN);
        return pattern.matcher(str).matches();
    }
    public static List convertJSONArrayToList(String str){
        String replace = str.replaceAll("\"","")
                .replaceAll("\\[","")
                .replaceAll("\\]","")
                .trim();
        ArrayList lists = new ArrayList();
        String[] splitStr = replace.split(",");
        for (int i=0; i<splitStr.length; i++){
         lists.add(splitStr[i]);
        }
        return lists;
    }
}