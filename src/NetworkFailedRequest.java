import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v134.fetch.Fetch;
import org.openqa.selenium.devtools.v134.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v134.network.model.ErrorReason;

public class NetworkFailedRequest {

    public static void main(String[] args) throws InterruptedException {

        // Set path to ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "D:\\Documents\\Career\\My Projects\\Selenium Projects\\01-Introduction-Project\\selenium webdriver\\ChromeDriver\\chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();

        // Start DevTools session to enable CDP features
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        RequestPattern requestPattern = new RequestPattern(Optional.of("*GetBook*"), Optional.empty(), Optional.empty());
        Optional<List<RequestPattern>> patterns = Optional.of(Arrays.asList(requestPattern));
        
        devTools.send(Fetch.enable(patterns, Optional.empty()));
        
        devTools.addListener(Fetch.requestPaused(), request->{
        	devTools.send(Fetch.failRequest(request.getRequestId(), ErrorReason.FAILED));
        });
        
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
        Thread.sleep(3000);
        
    }
}
