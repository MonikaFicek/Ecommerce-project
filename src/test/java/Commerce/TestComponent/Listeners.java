package Commerce.TestComponent;
import Commerce.Resources.ExtendReportNg;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener{

 ThreadLocal <ExtentTest> extentTest = new ThreadLocal<>(); //Thread safe
 ExtentTest test;
 ExtentReports extent=  ExtendReportNg.getReportObject();
 @Override
 public void onTestStart(ITestResult iTestResult) {

  test= extent.createTest(iTestResult.getMethod().getMethodName());
  extentTest.set(test); //unique thread ID
 }
 @Override
 public void onTestSuccess(ITestResult iTestResult) {
  extentTest.get().log(Status.PASS,"Tested passed");
 }
 @Override
 public void onTestFailure(ITestResult iTestResult) {
//Zaciągamy informacje o błędzie
  extentTest.get().fail(iTestResult.getThrowable());
  //Chceme wiedzied dlaczego zfailował, a nie że po prostu zfailował
  // test.log(Status.FAIL,"Tested failed");

  //Screenshot załączony do Extend Report
  String filePath;
  try {
   driver = (WebDriver) iTestResult.getTestClass().getRealClass().getField("driver").get(iTestResult.getInstance());
  } catch (Exception e) {
   throw new RuntimeException(e);

  }

  try {
   filePath = getScreenshot(iTestResult.getMethod().getMethodName(),driver);
  } catch (IOException e) {
   throw new RuntimeException(e);
  }
  extentTest.get().addScreenCaptureFromPath(filePath,iTestResult.getMethod().getMethodName());
 }

 @Override
 public void onTestSkipped(ITestResult iTestResult) {

 }

 @Override
 public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

 }

 @Override
 public void onStart(ITestContext iTestContext) {

 }

 @Override
 public void onFinish(ITestContext iTestContext) {
 extent.flush();

 }
}
