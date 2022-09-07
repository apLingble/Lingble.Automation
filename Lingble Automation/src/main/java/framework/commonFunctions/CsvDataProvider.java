package framework.commonFunctions;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import framework.environment.BaseTestCase;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Iterator;


/**
 * test case data reader
 * Please do not modify or if needed kindly communicate with the author
 * @author alexander.v.pangilinan
 */
public class CsvDataProvider implements Iterator {

    protected static final Logger LOGGER = BaseTestCase.getlog();


    public static Iterator getDataProvider(Method method) throws IOException {
        return getDataProvider(method.getDeclaringClass(), method);
    }

    public static Iterator getDataProvider(Class cls, Method method) throws IOException {
        String className = cls.getName();
        String dirPlusPrefix = className.replace('.', '/');
        String fileName = method.getName() + ".csv";
        String filePath = dirPlusPrefix + "." + fileName;

        return new CsvDataProvider(method);
    }

    private CSVReader reader;
    private String[] last;
    private Class[] parameterTypes;
    private Converter[] parameterConverters;
    private String[] currentTestData;

    public CsvDataProvider(Method method) throws IOException {
        String filePath = getCaseDataFilePath(method);
        File file = new File(filePath);
        InputStream is = FileUtils.openInputStream(file);
        InputStreamReader isr = new InputStreamReader(is);
        reader = new CSVReader(isr);
        parameterTypes = method.getParameterTypes();
        int len = parameterTypes.length;
        parameterConverters = new Converter[len];
        for (int i = 0; i < len; i++) {
            parameterConverters[i] = ConvertUtils.lookup(parameterTypes[i]);
        }
    }

    private String getCaseDataFilePath(final Method method) {
        return System.getProperty("user.dir") + "/src/test/java/" +
                method.getDeclaringClass().getName().replace(".", "/") +
                "." + method.getName() + ".csv";
    }


    public boolean hasNext() {
    if (this.reader == null) {
        return false;
    }
    if (this.currentTestData == null) {
        this.currentTestData = getNextLine();
    }
    return this.currentTestData != null;
}

    public Object[] next() {
        String[] next;
        if (this.currentTestData != null) {
            next = this.currentTestData;
        } else {
            next = this.getNextLine();
        }
        this.currentTestData = null;
        Object[] args = this.parseLine(next);
        return args;
    }

    private String[] getNextLine() {
        if (this.currentTestData == null) {
            try {
                this.currentTestData = this.reader.readNext();
            } catch (IOException | CsvValidationException var2) {
                LOGGER.error("Get next line from test data file error!");
                throw new RuntimeException(var2);
            }
        }
        return this.currentTestData;
    }

    private Object[] parseLine(String[] svals) {
        if (svals.length != this.parameterTypes.length) {
            LOGGER.error("The amount of test data column [" + svals.length + "] doesn't equals to input parameter [" + this.parameterTypes.length + "], " + svals[0]);
            return null;
        } else {
            int len = svals.length;
            Object[] result = new Object[len];

            for(int i = 0; i < len; ++i) {
                String curSval = svals[i];
                result[i] = this.parameterConverters[i].convert(this.parameterTypes[i], curSval);
                LOGGER.debug(result[i].toString());
            }

            return result;
        }
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

}