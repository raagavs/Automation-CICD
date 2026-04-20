package SeleniumFrameworkDesign.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports getReportObject() {
        String path = System.getProperty("user.dir") + "//reports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        // using reporter object to set properties for the extent report
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        // Main class for creating tests and flushing results.
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);// attach reporter object
        extent.setSystemInfo("Tester", "Raagav");
        return extent;

    }

}

//ExtentTest test = extent.createTest("initial Demo");
// mandatory step/method, so test will have knowledge to
//extent.flush();
// Mandatory step/method to add at the end of test/test suite execution.
