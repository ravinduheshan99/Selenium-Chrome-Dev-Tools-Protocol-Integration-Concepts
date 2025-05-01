import java.util.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v134.network.Network;
import com.google.common.collect.ImmutableList;

public class BlockNetworkRequests {

    public static void main(String[] args) throws InterruptedException {

        // Set path to ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "D:\\Documents\\Career\\My Projects\\Selenium Projects\\01-Introduction-Project\\selenium webdriver\\ChromeDriver\\chromedriver.exe");

        // Launch Chrome browser
        ChromeDriver driver = new ChromeDriver();

        // Start Chrome DevTools Protocol session to access low-level browser control
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        
        // Enable network tracking to use network commands
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Block all requests ending in .jpg and .css (images and stylesheets)
        // Purpose: Speed up testing by avoiding loading unnecessary assets
        devTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg", "*.css")));
        
        // Record start time to measure performance impact
        long startTime = System.currentTimeMillis();

        // Navigate and perform actions on the web app
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.xpath("//a[text()='Browse Products']")).click();
        driver.findElement(By.linkText("Selenium")).click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        
        // Record end time after user actions are completed
        long endTime = System.currentTimeMillis();
        
        // Print confirmation message from page (e.g., success or cart update)
        System.out.println("Message : " + driver.findElement(By.cssSelector("p")).getText());
        
        // Calculate and print execution time in milliseconds
        long executionTime = endTime - startTime;
        System.out.println("Execution Time : " + executionTime + "ms");
    }
}
