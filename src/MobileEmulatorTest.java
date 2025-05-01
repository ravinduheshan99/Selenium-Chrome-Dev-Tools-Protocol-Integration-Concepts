import java.util.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v134.emulation.Emulation;

public class MobileEmulatorTest {

    public static void main(String[] args) throws InterruptedException {

        // Set path to ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "D:\\Documents\\Career\\My Projects\\Selenium Projects\\01-Introduction-Project\\selenium webdriver\\ChromeDriver\\chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();

        // Initialize Chrome DevTools session
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Enable mobile emulation with custom screen dimensions and device scale
        devTools.send(Emulation.setDeviceMetricsOverride(600, 1000, 50, true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
        driver.get("https://rahulshettyacademy.com/angularAppdemo");
        
        // Interact with mobile hamburger menu
        driver.findElement(By.cssSelector(".navbar-toggler")).click();
        Thread.sleep(3000);

        // Navigate to 'Library' page from mobile menu
        driver.findElement(By.linkText("Library")).click();

    }
}
