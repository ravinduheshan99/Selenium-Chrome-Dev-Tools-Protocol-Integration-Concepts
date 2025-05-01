import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v134.network.Network;
import org.openqa.selenium.devtools.v134.network.model.Request;
import org.openqa.selenium.devtools.v134.network.model.Response;

public class NetworkLogActivity {

    public static void main(String[] args) throws InterruptedException {

        // Set path to ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "D:\\Documents\\Career\\My Projects\\Selenium Projects\\01-Introduction-Project\\selenium webdriver\\ChromeDriver\\chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();

        // Start DevTools session to enable CDP features
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Enable network tracking to monitor request/response activity
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        
        // Listen to all outgoing network requests
        devTools.addListener(Network.requestWillBeSent(), request -> {
            Request req = request.getRequest();
            System.out.println("Request URL is : "+req.getUrl()); // Print requested URL to console

            // System.out.println(req.getHeaders()); 
            // Commented out: This line would print headers of the request, 
            // which may not be necessary unless debugging headers specifically.
        });
        
        // Listen to all incoming responses
        devTools.addListener(Network.responseReceived(), response -> {
            Response res = response.getResponse();
            System.out.println("Response Status is : "+res.getStatus()); // Print response status code

            // System.out.println(res.getUrl()); 
            // Commented out: Optional line to print response URL. Useful only if URL tracing is required.

            // Print failed API calls with 4xx status
            if (res.getStatus().toString().startsWith("4")) {
                System.out.println(res.getUrl() + " is failing with status code " + res.getStatus());
            }
        });

        // Launch the test application and navigate to Library page
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
    }
}
