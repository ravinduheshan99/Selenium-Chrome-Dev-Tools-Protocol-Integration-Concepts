import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public class SetGeoLocation {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "D:\\Documents\\Career\\My Projects\\Selenium Projects\\01-Introduction-Project\\selenium webdriver\\ChromeDriver\\chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();

        // Start DevTools session to enable CDP commands
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Set geolocation coordinates (Madrid-Spain from Google Earth)
        Map<String, Object> corrdinates = new HashMap<String, Object>();
        corrdinates.put("latitude", 40);
        corrdinates.put("longitude", 3);
        corrdinates.put("accuracy", 1);

        // Override browser location using CDP command
        driver.executeCdpCommand("Emulation.setGeolocationOverride", corrdinates);

        driver.get("https://google.com");
        driver.findElement(By.name("q")).sendKeys("netflix", Keys.ENTER);
        driver.findElement(By.cssSelector(".LC20lb")).click();

        // Attempt to retrieve and print a specific element's text from Netflix landing page
        String title = driver.findElement(By.cssSelector(".default-ltr-cache-l1j3pp-StyledContainer.euy28770")).getText();
        System.out.println(title);
    }
}
