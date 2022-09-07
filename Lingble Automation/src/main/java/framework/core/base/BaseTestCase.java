package framework.core.base;

import framework.core.Starter;
import framework.core.base.dataprovider.BaseCsvDataProvider;
import framework.core.listener.ProjectProcessListener;
import framework.core.listener.ProjectReportListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

@Listeners({ProjectReportListener.class, ProjectProcessListener.class})
//@TestExecutionListeners({ ServletTestExecutionListener.class,
//        DependencyInjectionTestExecutionListener.class,
//        DirtiesContextTestExecutionListener.class})
@SpringJUnitConfig(classes = Starter.class)
public class BaseTestCase extends AbstractTestNGSpringContextTests {

    public static final Logger LOGGER = LoggerFactory.getLogger(BaseTestCase.class);
    private static final String LOG_DATE_FORMAT = "yyyyMMdd";
    private static final String LOG_DATE_KEY_IN_PROP = "LOG_DATE";
    private static final String LOG_DATE_TIME_FORMAT = "yyyyMMdd_HHmmss";
    private static final String LOG_DATE_TIME_KEY_IN_PROP = "LOG_DATETIME";
    private static final String TASK_ID = "TASK_ID";
    public ITestContext iTestContext;
    private static Properties loadedProp;

    static {
        try {
            getConfiguration();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get global and env config from config files and save into context.
     *
     * @throws Exception
     */
    private static void getConfiguration() throws Exception {
        Properties globalConfig = loadProp("config/config.properties");
        System.getProperties().putAll(globalConfig);
        setLoadedProp(globalConfig);
        setLogFileNameToProp();
        LOGGER.info("Put all configrations in env <" + globalConfig + "> successfully!");
    }
    private static void setLoadedProp(Properties loadedProp) {
        BaseTestCase.loadedProp = loadedProp;
    }
    private static Properties loadProp(String propFileName) {
        Properties prop = new Properties();
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        URL globalConfigUrl = currentClassLoader.getResource(propFileName);

        try {
            prop.load(globalConfigUrl.openStream());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return prop;
    }

    private static void setLogFileNameToProp() {
        BaseTestCase.loadedProp.put(
                LOG_DATE_KEY_IN_PROP,
                new SimpleDateFormat(LOG_DATE_FORMAT).format(Calendar.getInstance().getTime())
        );
        BaseTestCase.loadedProp.put(
                LOG_DATE_TIME_KEY_IN_PROP,
                new SimpleDateFormat(LOG_DATE_TIME_FORMAT).format(Calendar.getInstance().getTime())
        );
        BaseTestCase.loadedProp.put(
                TASK_ID,
                new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())
        );
    }
    public String getCurrentFilename(){
        final StackTraceElement e = Thread.currentThread().getStackTrace()[2];
        final String s = e.getClassName();
        return s.substring(s.lastIndexOf('.') + 1, s.length()) + "." + e.getMethodName();
    }
    @DataProvider(name = "DefaultProvider")
    public Iterator<Object[]> getDataProvider(ITestContext iTestContext, Method method) throws IOException {
        this.iTestContext = iTestContext;
        return new BaseCsvDataProvider(method);
    }

}