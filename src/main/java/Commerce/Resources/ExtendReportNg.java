package Commerce.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReportNg {

    public static ExtentReports getReportObject(){
        String path = System.getProperty("user.dir") +"\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation results");
        reporter.config().setDocumentTitle("Test results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Monika Ficek");

       return extent;
    }
}
