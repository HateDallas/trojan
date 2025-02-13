package com.team.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Указываем путь до GeckoDriver
        System.setProperty("webdriver.gecko.driver", "C:/Users/elemo/Downloads/Telegram Desktop/geckodriver-v0.35.0-win64/geckodriver.exe");

        FirefoxOptions options = new FirefoxOptions();
        // options.setHeadless(true);  // Для работы без GUI

        driver = new FirefoxDriver(options);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testHomePage() {
        driver.get("https://rf4game.ru/forum/index.php?/forum/5-технические-вопросы/");
        // Ожидаем появления части текста на странице
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "Русская Рыбалка 4"));

        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("Русская Рыбалка 4"));
        assertTrue(pageSource.contains("долгожданное продолжение"));
        assertTrue(pageSource.contains("игровой мир"));
    }

    @Test
    public void testNewsPage() {
        driver.get("https://rf4game.ru/news/");

        // Ожидаем появления элемента с новостью
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Ищем первый элемент с новостью, например, используя тег 'article' или класс, который содержит новость
        WebElement firstNewsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("article h2")));  // Здесь нужно заменить на точный селектор новостей

        // Проверяем, что на странице есть новость
        String newsText = firstNewsElement.getText();
        assertTrue(newsText.length() > 0, "Новая новость не найдена!");

        // Можно добавить дополнительную проверку на актуальность новости, например, проверив наличие слов "обновление" или даты
        assertTrue(newsText.contains("обновление"), "Обновление не найдено в новости!");
    }
}