package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import testCases.BaseTest;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportingExtent extends TestListenerAdapter {
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest extentLogger;
    BaseTest baseTest = new BaseTest();

    public ReportingExtent() throws AWTException {
    }

    public void onStart(ITestContext testContext) {
        //BaseTest.logger.info(testContext.getName() +  "--->  Started");

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
        String repName = "Test-Report-" + timeStamp + ".html";

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Extent_Report/" + repName);//specify location of the report
        htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Reports Name", "API Integration Automation Report");
        extent.setSystemInfo("Environment", "Windows");
        extent.setSystemInfo("Test", "IQ Soft Integration API Test");
        extent.setSystemInfo("QA Engineer", "Gor Babloyan");

        htmlReporter.config().setDocumentTitle("IQ Soft Integration"); // Title of report
        htmlReporter.config().setReportName("API Integration Automation Report"); // name of the report

        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
        htmlReporter.config().setTheme(Theme.DARK);
    }

    public void onTestSuccess(ITestResult tr) {
        extentLogger = extent.createTest(tr.getName());      // create new entry in th report
        extentLogger.pass(tr.getName() + " Passed");    // log test passed
        extentLogger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); // send the passed information to the report with GREEN color highlighted
//        extentLogger.pass(baseTest.authorizationRequestBody);
//        extentLogger.pass(baseTest.authorizationResponseBody);
//        BaseTest.logger.info(tr.getName() + "--------------------------------------------------------->  Success");
    }

    public void onTestFailure(ITestResult tr) {
        extentLogger = extent.createTest(tr.getName()); // create new entry in th report
        extentLogger.fail(tr.getName() + " Failed");    // log test failed
        extentLogger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED)); // send the failed information to the report with Red color highlighted
//        extentLogger.fail(baseTest.authorizationRequestBody);
//        try {
//            extentLogger.fail(baseTest.authorizationAPI());
//        } catch (UnirestException e) {
//            throw new RuntimeException(e);
//        }
//        BaseTest.logger.error(tr.getName() + "--------------------------------------------------------->   Fail" );
    }

    public void onTestSkipped(ITestResult tr) {
        extentLogger = extent.createTest(tr.getName()); // create new entry in th report
        extentLogger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
//        extentLogger.skip(baseTest.authorizationRequestBody);
//        extentLogger.skip(baseTest.authorizationResponseBody);
//        BaseTest.logger.warn(tr.getName() + "--------------------------------------------------------->  Skipped");
    }

    public void onFinish(ITestContext testContext) {
        //logger = extent.createTest("Test execution finished"); // create new entry in th report
        extent.flush();
        //BaseTest.logger.info(testContext.getName() +  "---> finished");
    }
}
