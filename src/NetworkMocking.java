import java.util.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v134.fetch.Fetch;
import org.testng.Assert;

public class NetworkMocking {

    public static void main(String[] args) throws InterruptedException {

        // Set path to ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "D:\\Documents\\Career\\My Projects\\Selenium Projects\\01-Introduction-Project\\selenium webdriver\\ChromeDriver\\chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();

        // Start DevTools session to enable CDP features
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Enable Fetch domain to allow request interception and mocking
        devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));
        
        // Intercept network requests and modify if condition matches
        devTools.addListener(Fetch.requestPaused(), request -> {
            if (request.getRequest().getUrl().contains("shetty")) {
                // Print original and mocked URLs for debugging
                System.out.println("Original URL: " + request.getRequest().getUrl());
                String mockedUrl = request.getRequest().getUrl().replace("=shetty", "=BadGuy");
                System.out.println("New URL: " + mockedUrl);

                // Continue request with the mocked URL
                devTools.send(Fetch.continueRequest(
                        request.getRequestId(), 
                        Optional.of(mockedUrl), 
                        Optional.of(request.getRequest().getMethod()), 
                        Optional.empty(), Optional.empty(), Optional.empty()));
            } else {
                // Allow other requests to continue unchanged
                devTools.send(Fetch.continueRequest(
                        request.getRequestId(), 
                        Optional.of(request.getRequest().getUrl()), 
                        Optional.of(request.getRequest().getMethod()), 
                        Optional.empty(), Optional.empty(), Optional.empty()));
            }
        });

        // Launch the application
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
        Thread.sleep(3000);

        // Verify if the mocked data triggers the expected message
        String actualMessage = driver.findElement(By.cssSelector("p")).getText();
        Assert.assertEquals("Oops only 1 Book available", actualMessage);
        System.out.println("Displaying Message : " + actualMessage);

        driver.close();
    }
}
