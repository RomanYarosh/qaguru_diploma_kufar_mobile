package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MobileMainPage {

    private final SelenideElement searchInput = $("[data-testid='searchbar-input']");
    private final SelenideElement searchButton = $("[data-testid='searchbar-search-button']");
    private final SelenideElement cookieButton = $(byText("Принять"));
    private final SelenideElement modalCloseButton = $(byTagAndText("button", "Закрыть"));
    private final SelenideElement bottomBar = $("#bottom-bar");
    private final SelenideElement loginButton = $(byTagAndText("button", "Войти или зарегистрироваться"));

    private final SelenideElement logo = $("#header-wrapper").$("[class*='styles_logo']");

    public void openPage() {
        open("https://www.kufar.by/l");
        acceptCookies();
    }

    private void acceptCookies() {
        if (cookieButton.is(Condition.visible)) {
            cookieButton.click();
        }
    }

    public void search(String text) {
        searchInput.shouldBe(Condition.visible, Duration.ofSeconds(20)).setValue(text);
        searchButton.shouldBe(Condition.enabled).click();
        handleModal();
    }

    private void handleModal() {
        if (modalCloseButton.shouldBe(Condition.exist, Duration.ofSeconds(10)).is(Condition.visible)) {
            modalCloseButton.click();
            modalCloseButton.shouldNotBe(Condition.visible);
        }
    }

    public void clickProfileTab() {
        bottomBar.shouldBe(Condition.visible, Duration.ofSeconds(15))
                .$(byText("Профиль"))
                .click();
    }

    public SelenideElement getLoginButton() {
        return loginButton;
    }

    public SelenideElement getLogo() {
        return logo;
    }
}