package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

public abstract class AbstractTest implements TestExecutionExceptionHandler {

    protected WebDriver driver;
    protected Properties envProperties;

    @BeforeEach
    public void setUp() {
        configureDriver();
        envProperties = loadProperties(".env-test");
    }

    private Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while loading properties", e);
        }
        return properties;
    }

    private void configureDriver() {
        Properties testProperties = loadProperties("src/test/resources/test.properties");

        String browser = testProperties.getProperty("browser", "chrome").toLowerCase();
        switch (browser) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            case "internetexplorer" -> {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            }
            default -> { // Default to Chrome
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
        }
        setupBrowserWithUrl(testProperties.getProperty("url"));
    }

    private void setupBrowserWithUrl(String url) {
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        takeScreenshot(context.getDisplayName());
        throw throwable;
    }

    private void takeScreenshot(String testName) {
        if (driver instanceof TakesScreenshot) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.move(screenshot.toPath(), Paths.get("resources/screenshots/" + testName + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}