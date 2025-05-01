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

        // Launch Chrome browser
        ChromeDriver driver = new ChromeDriver();

        // Start DevTools session to access Chrome DevTools Protocol (CDP)
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Create a request pattern to match any URL containing "GetBook"
        // This pattern is used to intercept specific network requests
        RequestPattern requestPattern = new RequestPattern(Optional.of("*GetBook*"), Optional.empty(), Optional.empty());
        Optional<List<RequestPattern>> patterns = Optional.of(Arrays.asList(requestPattern));

        // Enable Fetch domain with the defined request pattern
        devTools.send(Fetch.enable(patterns, Optional.empty()));

        // Intercept the matching request and simulate a network failure
        devTools.addListener(Fetch.requestPaused(), request -> {
            // Fail the request using the error reason "FAILED"
            devTools.send(Fetch.failRequest(request.getRequestId(), ErrorReason.FAILED));
        });

        // Open the application under test
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");

        // Attempt to navigate to the library page which triggers a 'GetBook' API call
        // That call will fail due to our mocking above
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();

        // Pause to allow the UI to react to the failed request
        Thread.sleep(3000);
    }
}
