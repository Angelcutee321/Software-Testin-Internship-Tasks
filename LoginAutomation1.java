package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginAutomation1 {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // ===== VALID LOGIN TEST =====
        driver.get("https://the-internet.herokuapp.com/login");

        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        Thread.sleep(5000);

        String successMsg = driver.findElement(By.id("flash")).getText();

        if(successMsg.contains("You logged into a secure area!")) {
            System.out.println("Valid Login Test Passed");
        } else {
            System.out.println("Valid Login Test Failed");
        }

        // Logout before next test
        driver.findElement(By.linkText("Logout")).click();
        Thread.sleep(2000);


        // ===== INVALID LOGIN TEST =====
        driver.findElement(By.id("username")).sendKeys("wronguser");
        driver.findElement(By.id("password")).sendKeys("wrongpass");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        Thread.sleep(5000);

        String errorMsg = driver.findElement(By.id("flash")).getText();

        if(errorMsg.contains("Your username is invalid!")) {
            System.out.println("Invalid Login Test Passed");
        } else {
            System.out.println("Invalid Login Test Failed");
        }

        Thread.sleep(5000);
        driver.quit();
    }
}
