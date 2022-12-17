package Commerce.TestComponent;

import Commerce.PageObjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;


    public WebDriver initializedDriver() throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\Commerce\\Resources\\Global.properties");
        properties.load(fis);
        String browserName = System.getProperty("browser")!= null ?  System.getProperty("browser"): properties.getProperty("browser");


        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();

            if (browserName.contains("headless")){
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            //optional help to remove flaky test
            driver.manage().window().setSize(new Dimension(1440,900));


        } else if (browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        }else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        return driver;
    }
    @BeforeMethod (alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = initializedDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }
    @AfterMethod (alwaysRun = true)
    public void tearDown(){
        driver.close();
    }
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts= ((TakesScreenshot) driver);
        File source= ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName +".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir")+"\\reports\\"+testCaseName +".png";
    }
    public List<HashMap<String, String>> getJasonDataToMap(String filepath) throws IOException {
        //read json to String
        String jasonContent=  FileUtils.readFileToString(new File((filepath)),
                StandardCharsets.UTF_8);

        //String to Hashmap Jackson Data Bind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jasonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        //{{map}.{map1}}
        return data;
    }
}
