package Utilites;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.spotify.oauth2.reports.ExtentManager;

import io.restassured.RestAssured;

import com.aventstack.extentreports.markuputils.Markup;

public class baseTest {

	public static ExtentReports report;
	public static ExtentTest test;
	public static final String baseUrl = "https://petstore.swagger.io";

	@BeforeSuite
	public static void startTest() {
		report = new ExtentReports(System.getProperty("user.dir") + "ExtentReportResults.html", true);
		test = report.startTest("liftlabReport");
		reporter.config().setReportName("Sample Extent Report");
		extentReports.attachReporter(report);
		extentReports.setSystemInfo("Author", "Haribabu");
		extent.setSystemInfo("Organization", "LiftLab");
		return extentReports;
	}

	@AfterSuite
	public static void endTest() {
		report.endTest(test);
		report.flush();
	}

	public static void pass(String message) {
		ExtentManager.getExtentTest().pass(message);
	}

	public static void pass(Markup message) {
		ExtentManager.getExtentTest().pass(message);
	}

	public static void fail(String message) {
		ExtentManager.getExtentTest().fail(message);
	}

	public static void fail(Markup message) {
		ExtentManager.getExtentTest().fail(message);
	}

	public static void skip(String message) {
		ExtentManager.getExtentTest().skip(message);
	}

	public static void skip(Markup message) {
		ExtentManager.getExtentTest().skip(message);
	}

	public static void info(Markup message) {
		ExtentManager.getExtentTest().info(message);
	}

	public static void info(String message) {
		ExtentManager.getExtentTest().info(message);
	}
}
