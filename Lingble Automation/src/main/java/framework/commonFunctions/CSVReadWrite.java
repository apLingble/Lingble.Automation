package framework.commonFunctions;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import framework.environment.BaseTestCase;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.io.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * CSV reader
 * Please do not modify or if needed kindly communicate with the author
 @author alexander.v.pangilinan
 */

public class CSVReadWrite
{
    protected static final Logger log = BaseTestCase.getlog();
    private String filenamepath;

    public  String findME(String name){
        if(!name.toLowerCase().endsWith(".csv")){
            name +=".csv";
        }
        String directory = System.getProperty("user.dir")+"/src/";//scan.next();
        findFile(name, new File(directory));
        return filenamepath +"/"+name;
    }
    public void findFile(String name, File file)
    {
        try {
            File[] list = file.listFiles();
            if(list!=null)
                for (File fil : list)
                {
                    if (fil.isDirectory())
                    {
                        findFile(name,fil);
                    }
                    else if (name.equalsIgnoreCase(fil.getName()))
                    {
                        this.filenamepath = fil.getParentFile().toString();
                    }
                }
        }catch (Exception e) {
            log.error("File " + name + " was not found.\n" + e.getStackTrace().toString());
        }
    }
    public int getColSize(File filename) {
        int result;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            List<String> lines = new ArrayList<>();
            String line = null;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            String RowData = lines.get(0);
            String[] arrr = RowData.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            result = arrr.length;
            // Read existing file
            reader.close();
        }catch (FileNotFoundException e) {
            throw new RuntimeException("File " + filename + " was not found.\n" + e.getStackTrace().toString());
        }catch (IOException e) {
            throw new RuntimeException("Could not read " + filename + " file.\n" + e.getStackTrace().toString());
        }
        return result;
    }
    public int getColNum(File filename, String columnName) {
        int result;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            List<String> lines = new ArrayList<>();
            String line = null;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            String RowData = lines.get(0);
            result = Integer.parseInt(mapHeader(RowData,columnName));
            // Read existing file
            reader.close();
        }catch (FileNotFoundException e) {
            throw new RuntimeException("File " + filename + " was not found.\n" + e.getStackTrace().toString());
        }catch (IOException e) {
            throw new RuntimeException("Could not read " + filename + " file.\n" + e.getStackTrace().toString());
        }
        return result;
    }

    public int getRowNum(File filename, String rowName) {
        int result =0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            List<String> lines = new ArrayList<>();
            String line = null;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            for (int c=1; c<lines.size(); c++){
                String RowData = lines.get(c);
                String v = mapHeader(RowData,rowName);
                if (v != null){
                    result = c;
                    break;
                }
            }
            // Read existing file
            reader.close();

        }catch (FileNotFoundException e) {
            throw new RuntimeException("File " + filename + " was not found.\n" + e.getStackTrace().toString());
        }catch (IOException e) {
            throw new RuntimeException("Could not read " + filename + " file.\n" + e.getStackTrace().toString());
        }
        return result;
    }
    public int getRowSize(File filename) {
        int result =0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            List<String> lines = new ArrayList<>();
            String line = null;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            result = lines.size();
            // Read existing file
            reader.close();

        }catch (FileNotFoundException e) {
            throw new RuntimeException("File " + filename + " was not found.\n" + e.getStackTrace().toString());
        }catch (IOException e) {
            throw new RuntimeException("Could not read " + filename + " file.\n" + e.getStackTrace().toString());
        }
        return result;
    }
    public String mapHeader(String mapValue, String mapKey){
        String[] arrr = mapValue.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        HashMap<String, String> arrCol = new HashMap<>();
        for (int y=0; y<arrr.length; y++){
            arrCol.put(arrr[y].replace("\"", "").toLowerCase().trim(), String.valueOf(y));
        }
        return arrCol.get(mapKey.toLowerCase().trim());
    }

    public String getCellValue(String Rowvalue, String colKey){
        String[] arrr = Rowvalue.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        HashMap<String, String> arrCol = new HashMap<>();
        for (int y=0; y<arrr.length; y++){
            arrCol.put(String.valueOf(y),arrr[y].replace("\"", ""));
        }
        return arrCol.get(colKey);
    }
    public void updateTestData(String filename, String columnName, String rowName,String strValue) {
        String filePath = findME(filename);
        File file = new File(filePath);
        int row = getRowNum(file,rowName);
        int col = getColNum(file, columnName);
        if (row != 0) {
            try {

                CSVReader reader = new CSVReader(new FileReader(file));
                List<String[]> csvBody = reader.readAll();
// get CSV row column  and replace with by using row and column
                csvBody.get(row)[col] = strValue;
                reader.close();

// Write to CSV file which is open
                CSVWriter writer = new CSVWriter(new FileWriter(file));
                writer.writeAll(csvBody);
                writer.flush();
                writer.close();

            } catch (FileNotFoundException e) {
                throw new RuntimeException("File " + filename + " was not found.\n" + e.getStackTrace().toString());
            } catch (IOException e) {
                throw new RuntimeException("Could not read " + filename + " file.\n" + e.getStackTrace().toString());
            } catch (CsvException e) {
                e.printStackTrace();
            }
        }else{
            log.error( "Row name "+rowName+" is not available on this file "+filename);
        }
    }
    public String getTestData(String filename, String columnName, String rowName) {
        String filePath = findME(filename);
        String result = "";
        File file = new File(filePath);
        int row = getRowNum(file,rowName);
        int col = getColNum(file, columnName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            List<String> lines = new ArrayList<>();
            String line = null;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            String RowData = lines.get(row);
            result = (getCellValue(RowData,Integer.toString(col)));
//            System.out.println(result);
            // Read existing file
            reader.close();

        }catch (FileNotFoundException e) {
            throw new RuntimeException("File " + filename + " was not found.\n" + e.getStackTrace().toString());
        }catch (IOException e) {
            throw new RuntimeException("Could not read " + filename + " file.\n" + e.getStackTrace().toString());
        }
        return result;
    }
    public String generateRequestID(String requestID){
        String result = "";
        result = requestID + DateTimeFormatter.ofPattern("ddMMYYhhmmssSSS").format(ZonedDateTime.now());
        return result;
    }
    public String generateReqTime(){
        String result = "";
        result = DateTimeFormatter.ofPattern("YYYY-MM-dd'T'hh:mm:ss+08:00").format(ZonedDateTime.now());
        return result;
    }
    public float calculateBalance(float currentBalance, float deductedAmount){
        float newBalance;
            newBalance =currentBalance-deductedAmount;
        return newBalance;
    }
    public void assertResult(float expectedBalance, float actualBalance){
        Assert.assertEquals(expectedBalance,actualBalance);
        log.info("Expected balance: "+expectedBalance);
        log.info("Actual balance: "+actualBalance);
    }
    public String getDBNumber(String orderID){
        String[] splitarr = orderID.split("PH");
        String DBNum = splitarr[1].substring(2,4);
        return DBNum;
    }
    public String getTableNumber(String orderID){
        String[] splitarr = orderID.split("PH");
        String TableNum = splitarr[1].substring(2,5);
        return TableNum;
    }
    public String getAmountInPeso(String amountInCents){
        String amountInPeso = amountInCents.substring(0, amountInCents.length()-2);
        return amountInPeso;
    }
    public void addTestData(String filename, String columnName, String strValue) {
        String filePath = findME(filename);
        File file = new File(filePath);
        int colSize = getColSize(file);
        int colNumber = getColNum(file,columnName);
        try {

            CSVReader reader = new CSVReader(new FileReader(file));
            List<String[]> csvBody = reader.readAll();
            String[] value =new String[colSize];
            value[colNumber] = strValue;
            csvBody.add(value);
            reader.close();

// Write to CSV file which is open
            CSVWriter writer = new CSVWriter(new FileWriter(file));
            writer.writeAll(csvBody);
            writer.flush();
            writer.close();


        }catch (FileNotFoundException e) {
            throw new RuntimeException("File " + filename + " was not found.\n" + e.getStackTrace().toString());
        }catch (IOException e) {
            throw new RuntimeException("Could not read " + filename + " file.\n" + e.getStackTrace().toString());
        } catch (CsvException e) {
            e.printStackTrace();
        }

    }
    public void clearTestData(String filename) {
        String filePath = findME(filename);
        File file = new File(filePath);
        int rowSize = getRowSize(file);
        try {

            CSVReader reader = new CSVReader(new FileReader(file));
            List<String[]> csvBody = reader.readAll();
            for (int i=1; i<rowSize; i++){
                csvBody.remove(i);
            }
            reader.close();

// Write to CSV file which is open
            CSVWriter writer = new CSVWriter(new FileWriter(file));
            writer.writeAll(csvBody);
            writer.flush();
            writer.close();


        }catch (FileNotFoundException e) {
            throw new RuntimeException("File " + filename + " was not found.\n" + e.getStackTrace().toString());
        }catch (IOException e) {
            throw new RuntimeException("Could not read " + filename + " file.\n" + e.getStackTrace().toString());
        } catch (CsvException e) {
            e.printStackTrace();
        }

    }
    public void addTestData(String filename, String[] strValue) {
        String filePath = findME(filename);
        File file = new File(filePath);
        try {
            CSVReader reader = new CSVReader(new FileReader(file));
            List<String[]> csvBody = reader.readAll();
            csvBody.add(strValue);
            reader.close();

// Write to CSV file which is open
            CSVWriter writer = new CSVWriter(new FileWriter(file));
            writer.writeAll(csvBody);
            writer.flush();
            writer.close();


        }catch (FileNotFoundException e) {
            throw new RuntimeException("File " + filename + " was not found.\n" + e.getStackTrace().toString());
        }catch (IOException e) {
            throw new RuntimeException("Could not read " + filename + " file.\n" + e.getStackTrace().toString());
        } catch (CsvException e) {
            e.printStackTrace();
        }

    }

}