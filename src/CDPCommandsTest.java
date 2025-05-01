import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import java.util.HashMap;
import java.util.Map;

public class CDPCommandsTest {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "D:\\Documents\\Career\\My Projects\\Selenium Projects\\01-Introduction-Project\\selenium webdriver\\ChromeDriver\\chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();

        // Start DevTools session to enable Chrome DevTools Protocol commands
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Define device metrics to simulate a mobile view
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 600);
        deviceMetrics.put("height", 1000);
        deviceMetrics.put("deviceScaleFactor", 50);
        deviceMetrics.put("mobile", true);

        // Execute CDP command to emulate mobile device environment
        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        driver.get("https://rahulshettyacademy.com/angularAppdemo");

        // Interact with the mobile menu
        driver.findElement(By.cssSelector(".navbar-toggler")).click();
        Thread.sleep(3000);

        // Navigate to 'Library' page
        driver.findElement(By.linkText("Library")).click();
    }
}
