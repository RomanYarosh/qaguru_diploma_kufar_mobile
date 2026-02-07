package drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.PropertyReader;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class BrowserstackDriver implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("browserName", "chrome");

        HashMap<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("userName", PropertyReader.getProperty("bs.user"));
        bstackOptions.put("accessKey", PropertyReader.getProperty("bs.key"));

        bstackOptions.put("deviceName", "Google Pixel 7");
        bstackOptions.put("osVersion", "13.0");
        bstackOptions.put("realMobile", "true");

        bstackOptions.put("projectName", "Kufar Mobile Project");
        bstackOptions.put("buildName", "Mobile-Web-Final");
        bstackOptions.put("sessionName", "Kufar Search Test");

        caps.setCapability("bstack:options", bstackOptions);

        try {
            return new RemoteWebDriver(new URL("https://hub-cloud.browserstack.com/wd/hub"), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Ошибка в URL BrowserStack", e);
        }
    }
}