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
        System.setProperty("webdriver.gecko.driver", "C:/Users/ya322/Downloads/geckodriver-v0.35.0-win64/geckodriver.exe");

        FirefoxOptions options = new FirefoxOptions();
        
        // Можно добавить дополнительные параметры, если нужно
        // options.setHeadless(true);  // Для работы без GUI

        driver = new FirefoxDriver(options);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testHomePage() {
        driver.get("https://rf4game.ru/forum/");
        
        // Ожидаем появления части текста на странице
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        
        // Здесь ищем фразу, которая должна появиться в теле страницы
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "Русская Рыбалка 4"));
        
        // Также проверяем, что текст действительно есть в исходном коде страницы
        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("Русская Рыбалка 4"));
        
        // Можно также добавить проверку других фраз из текста, например:
        assertTrue(pageSource.contains("долгожданное продолжение"));
        assertTrue(pageSource.contains("игровой мир"));
    }
}
