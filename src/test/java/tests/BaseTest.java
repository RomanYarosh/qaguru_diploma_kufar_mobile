package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackDriver;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import utils.BrowserstackUtils;

public class BaseTest {

    @RegisterExtension
    TestWatcher watcher = new TestWatcher() {
        @Override
        public void testSuccessful(ExtensionContext context) {
            BrowserstackUtils.setSessionStatus("passed", "Test passed successfully");
            Selenide.closeWebDriver();
        }

        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            BrowserstackUtils.setSessionStatus("failed", cause.getMessage());
            Selenide.closeWebDriver();
        }

        @Override
        public void testAborted(ExtensionContext context, Throwable cause) {
            Selenide.closeWebDriver();
        }

        @Override
        public void testDisabled(ExtensionContext context, java.util.Optional<String> reason) {
        }
    };

    @BeforeAll
    static void setup() {
        Configuration.browser = BrowserstackDriver.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = 20000;

        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

}