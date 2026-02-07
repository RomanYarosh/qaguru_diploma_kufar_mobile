package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import pages.MobileMainPage;
import pages.MobileSearchResultPage;
import utils.PropertyReader;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class KufarMobileTests extends BaseTest {

    MobileMainPage mainPage = new MobileMainPage();
    MobileSearchResultPage resultsPage = new MobileSearchResultPage();

    @Test
    @Order(1)
    @DisplayName("1. Поиск iPhone 15 и проверка заголовка")
    void searchIphoneTest() {
        String query = PropertyReader.getProperty("search.query");
        mainPage.openPage();
        mainPage.search(query);

        resultsPage.getHeading()
                .shouldHave(Condition.text("Объявления по запросу «" + query + "»"), Duration.ofSeconds(20));
    }

    @Test
    @Order(2)
    @DisplayName("2. Поиск некорректных данных")
    void invalidSearchTest() {
        mainPage.openPage();
        mainPage.search("asdfghjkl12345");

        resultsPage.getListings().shouldHave(Condition.text("Мы это не нашли"), Duration.ofSeconds(15));
    }

    @Test
    @Order(3)
    @DisplayName("3. Переход в профиль через нижнее меню")
    void profileMenuTest() {
        mainPage.openPage();
        mainPage.clickProfileTab();

        mainPage.getLoginButton()
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.attribute("type", "button"));
    }

    @Test
    @Order(4)
    @DisplayName("4. Проверка видимости логотипа в хедере")
    void logoVisibilityTest() {
        mainPage.openPage();

        mainPage.getLogo().shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}