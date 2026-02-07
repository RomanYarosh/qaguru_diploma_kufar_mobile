package utils;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class BrowserstackUtils {
    public static void setSessionStatus(String status, String reason) {
        try {
            executeJavaScript(
                    String.format("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"%s\", \"reason\": \"%s\"}}",
                            status, reason)
            );
        } catch (Exception e) {
        }
    }
}