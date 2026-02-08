# Проект по автоматизации тестирования мобильной версии сайта Kufar.by (Mobile Web) [Kufar](https://www.kufar.by/l) <img src="images/logo/KUFAR.png" width="90" alt="Kufar logo" vertical-align="middle">

<p align="center"><a href="https://www.kufar.by/l"><img src="images/logo/kufar1.png" align="center" width="300" height="150"  alt="Java"/></a></p>

>Куфар (Kufar) — это крупнейшая в Беларуси онлайн-площадка объявлений, где пользователи покупают и продают новые и б/у товары, недвижимость, автомобили, а также предлагают услуги. Ежемесячно сайт посещают более 5 миллионов пользователей.

## 🧾 Содержание

- [Описание проекта](#-описание-проекта)
- [Стек технологий](#-стек-технологий)
- [Архитектура решения](#-архитектура-решения)
- [Реализованные проверки](#-реализованные-проверки)
- [Результаты тестирования](#-результаты-тестирования)

## 📌 Описание проекта

Главная особенность — использование реальных мобильных устройств в облаке **BrowserStack** вместо эмуляторов. Это позволяет проверять поведение сайта в условиях, максимально приближенных к пользовательским.

## 🛠 Стек технологий

<p align="center">
  <a href="https://www.java.com/"><img src="images/logo/java.svg" height="48" alt="Java"/></a> 
  <a href="https://www.jetbrains.com/idea/"><img src="images/logo/IDEA.svg" height="48" alt="IDEA"/></a> 
  <a href="https://github.com/"><img src="images/logo/github.svg" height="48" alt="Github"/></a> 
  <a href="https://gradle.org/"><img src="images/logo/gradle.svg" height="48" alt="Gradle"/></a> 
  <a href="https://github.com/allure-framework"><img src="images/logo/allure.svg" height="48" alt="Allure"/></a> 
  <a href="https://www.jenkins.io/"><img src="images/logo/jenkins.svg" height="48" alt="Jenkins"/></a>  
  <a href="https://junit.org/junit5/"><img src="images/logo/JUnit5.svg" height="65"  alt="JUnit 5"/></a> 
  <a href="https://www.browserstack.com/"><img src="images/logo/browserstack.png" height="48"  alt="Selenide"/></a> 
  <a href="https://web.telegram.org/k/"><img src="images/logo/telegram.svg" height="48" alt="Telegram"/></a> 
</p>

-  **Язык:** Java  
-  **Фреймворк:** Selenide
-  **Тестовый запуск:** JUnit 5 
-  **Для работы с BrowserStack:** RemoteWebDriver 
-  **CI/CD:** Jenkins  
-  **Отчеты:** Allure Report  
-  **Уведомления:** Telegram Bot  

## 🏗 Архитектура решения

В проекте реализован кастомный драйвер `BrowserstackDriver` (интерфейс `WebDriverProvider`). Это позволило гибко настроить сессию:
-  **Устройство:** Google Pixel 7
-  **ОС:** Android 13.0
-  **Браузер:** Chrome (Mobile Web)

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

## ✅ Реализованные проверки

-  Поиск товара.
-  Обработка некорректных результатов.
-  Мобильная навигация.
-  Проверка видимости логотипа.

## 📊 Результаты тестирования

### 1. Отчетность в Allure
   Для каждого теста в Allure формируется подробный отчет с описанием шагов. Благодаря Selenide, в отчет автоматически прикрепляются скриншоты и исходный код страницы в случае падения.
<p align="center">
<img src="images/screenshots/allure_mob.png" width="850">
</p>   

<p align="center">
<img src="images/screenshots/allure_mob2.png" width="850">
</p> 

### 2. Запуск в BrowserStack
   Все тесты проходят в облаке на реальном устройстве. В панели BrowserStack Automate сохраняется видеозапись каждого прогона, логи устройства и сетевые логи.

<p align="center">
<img src="images/screenshots/browserstack.png" width="850">
</p> 

<p align="center">
<img src="images/screenshots/browserstack.gif" width="450">
</p> 

### 3. CI/CD в Jenkins
   Сборка проекта автоматизирована в Jenkins. Параметры доступа к облаку передаются через среду окружения, обеспечивая безопасность данных.
<p align="center">
<img src="images/screenshots/jenkins_mob.png" width="850">
</p> 

### 4. Уведомления в Telegram
   После завершения тестов Jenkins автоматически отправляет отчет в Telegram-бот. Сообщение содержит краткую статистику (кол-во тестов, результат) и ссылку на подробный Allure-отчет.

<p align="center">
<img src="images/screenshots/tg_mob.png" width="450">
</p> 
