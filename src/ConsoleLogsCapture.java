import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class ConsoleLogsCapture {

    public static void main(String[] args) {

        // Set path to ChromeDriver executable
        System.setProperty("webdriver.chrome.driver","D:\\Documents\\Career\\My Projects\\Selenium Projects\\01-Introduction-Project\\selenium webdriver\\ChromeDriver\\chromedriver.exe");

        // Launch browser
        ChromeDriver driver = new ChromeDriver();

        // Step-by-step user interactions
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.xpath("//a[text()='Browse Products']")).click();
        driver.findElement(By.linkText("Selenium")).click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        driver.findElement(By.linkText("Cart")).click();
        driver.findElement(By.id("exampleInputEmail1")).clear();
        driver.findElement(By.id("exampleInputEmail1")).sendKeys("2");

        // Capture browser console logs
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        List<LogEntry> logs = logEntries.getAll();

        // Print each log with index
        int i = 1;
        for (LogEntry entry : logs) {
            System.out.println("Browser Log " + i + " : " + entry.getMessage());
            i++;
        }

        // Clean up
        driver.quit();
    }
}
