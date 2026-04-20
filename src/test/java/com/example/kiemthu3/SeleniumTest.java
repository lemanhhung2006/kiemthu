package com.example.kiemthu3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@DisplayName("Selenium Test - Alada.vn Login")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SeleniumTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    static void setUp() {
        // Setup ChromeDriver tự động
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        // Uncomment dòng dưới để chạy headless (không có giao diện) trên CI
        // options.addArguments("--headless");
        // options.addArguments("--no-sandbox");
        // options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    @Order(1)
    @DisplayName("Bước 1: Mở trang web alada.vn")
    void testMoTrangWeb() {
        // Mở trang web
        driver.get("https://alada.vn");

        // Xác nhận trang đã mở thành công
        String title = driver.getTitle();
        System.out.println("Title: " + title);
        Assertions.assertTrue(title.contains("Alada"), "Trang web alada.vn đã mở thành công");
    }

    @Test
    @Order(2)
    @DisplayName("Bước 2: Click menu hamburger và đăng nhập")
    void testDangNhap() throws InterruptedException {
        // Click vào nút menu (3 gạch ngang - hamburger icon)
        WebElement menuButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".navbar-toggler"))
        );
        menuButton.click();

        // Chờ một chút để menu mở ra
        Thread.sleep(1000);

        // Click vào "Đăng Nhập" trong Sign In Portal
        WebElement signInLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='dang-nhap']"))
        );
        signInLink.click();

        // Chờ trang đăng nhập load
        Thread.sleep(1000);

        // Nhập username (email)
        WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("signInEmail"))
        );
        usernameField.clear();
        usernameField.sendKeys("sa");

        // Nhập password
        WebElement passwordField = driver.findElement(By.id("signInPassword"));
        passwordField.clear();
        passwordField.sendKeys("sa");

        // Click nút Đăng Nhập
        WebElement loginButton = driver.findElement(By.cssSelector("#signInForm button[type='submit']"));
        loginButton.click();

        // Chờ phản hồi
        Thread.sleep(2000);

        System.out.println("Đã thực hiện đăng nhập với username='sa', password='sa'");
    }

    @Test
    @Order(3)
    @DisplayName("Bước 3: Đóng trang web")
    void testDongTrangWeb() {
        // Đóng trang web
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("Đã đóng trang web thành công");
        }
    }

    @AfterAll
    static void tearDown() {
        // Đảm bảo driver được đóng nếu chưa đóng
        if (driver != null) {
            driver.quit();
        }
    }
}
