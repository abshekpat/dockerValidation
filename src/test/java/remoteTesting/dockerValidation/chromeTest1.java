package remoteTesting.dockerValidation;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class chromeTest1 {

    @BeforeTest
    public void startDockerscale() throws IOException, InterruptedException
    {
        startDocker s = new startDocker();
        s.startFile();
    }

    @AfterTest
    public void stopDockerDeletefile() throws IOException, InterruptedException
    {
        stopDocker s1 = new stopDocker();
        s1.stopFile();
    }

    @Test
    public void test1() throws MalformedURLException
    {
        // TODO Auto-generated method stub
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        URL u = new URL("http://localhost:4444/wd/hub");
        RemoteWebDriver driver = new RemoteWebDriver(u, cap);
        driver.get("http://google.com");
        System.out.println(driver.getTitle());

    }

}
