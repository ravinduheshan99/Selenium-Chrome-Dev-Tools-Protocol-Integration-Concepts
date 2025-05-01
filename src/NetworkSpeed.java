import java.util.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v134.network.Network;
import org.openqa.selenium.devtools.v134.network.model.ConnectionType;

public class NetworkSpeed {

    public static void main(String[] args) throws InterruptedException {

        // Set path to ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "D:\\Documents\\Career\\My Projects\\Selenium Projects\\01-Introduction-Project\\selenium webdriver\\ChromeDriver\\chromedriver.exe");

        // Launch Chrome browser
        ChromeDriver driver = new ChromeDriver();

        // Start DevTools session to use Chrome DevTools Protocol (CDP)
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Enable the network domain to use network commands via CDP
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Emulate slow network conditions (simulate real-world network issues)
        // Parameters: offline=false, latency=3000ms, downloadThroughput=20kbps, uploadThroughput=100kbps, type=ETHERNET
        // Purpose: Test how your site behaves under slow/unstable network conditions
        devTools.send(Network.emulateNetworkConditions(false, 3000, 20000, 100000, Optional.of(ConnectionType.ETHERNET), Optional.empty(), Optional.empty(), Optional.empty()));
        //devTools.send(Network.emulateNetworkConditions(true, 3000, 20000, 100000, Optional.of(ConnectionType.ETHERNET), Optional.empty(), Optional.empty(), Optional.empty()));
        
        // Listen for any network loading failures and log error details
        devTools.addListener(Network.loadingFailed(), loadingFailed -> {
            System.out.println("Error Text : " + loadingFailed.getErrorText());
            System.out.println("Error Timestamp : " + loadingFailed.getTimestamp());
        });

        // Measure the page load time and actions performed
        long startTime = System.currentTimeMillis();

        // Launch the application
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();

        long endTime = System.currentTimeMillis();

        // Display how long the entire operation took
        long executionTime = endTime - startTime;
        System.out.println("Execution Time : " + executionTime + "ms");
        
    }
}
