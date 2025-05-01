import java.util.function.Predicate;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;
import java.net.URI;


public class BasicAuthentication {

    public static void main(String[] args) throws InterruptedException {

        //Set path to ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "D:\\Documents\\Career\\My Projects\\Selenium Projects\\01-Introduction-Project\\selenium webdriver\\ChromeDriver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        //Create a predicate to match the authentication request URL
        Predicate<URI> uriPredicate = uri -> uri.getHost().contains("httpbin.org");

        //Register username and password for the matched URI (Basic Auth)
        ((HasAuthentication) driver).register(uriPredicate, UsernameAndPassword.of("foo", "bar"));

        //Navigate to the basic auth protected page
        driver.get("http://httpbin.org/basic-auth/foo/bar");

        //(Optional) Add wait or verification if needed
        //Thread.sleep(2000); // Just for observation

        //Close the browser (good practice)
        driver.quit();
        
    }
}
