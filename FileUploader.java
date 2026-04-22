import io.opentelemetry.api.GlobalOpenTelemetry;
import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;



public class FileUploader {



    protected WebDriver driver;


    private String FileUploadUrl = "https://the-internet.herokuapp.com/login";

    private By chooseFileButton = By.id()
    private By passwordBy = By.name("password");

    private By submitBy = By.xpath("//*[@id='login']/button/i");
}
