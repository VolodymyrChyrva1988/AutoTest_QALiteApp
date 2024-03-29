package loginTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    private WebDriver webDriver;//interface

    @Test
    public void validLogin() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver(); //implement interface
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        System.out.println("browser was open");
        webDriver.get("https://qa-complexapp.onrender.com/");
        System.out.println("site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("Amberbellami");
        System.out.println("login was inputted");
        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("2020Nissan-altima");
        System.out.println("password was inputted");

        WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        buttonSignIn.click();
        System.out.println("Button was clicked");

        Assert.assertTrue("Button is not displayed",isButtonSignOutDisplayed());


        webDriver.quit();
        System.out.println("browes was closed");


    }

    private boolean isButtonSignOutDisplayed(){
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e){
            return false;
        }

    }
    //--------------------------------
    @Test
    public void invalidLogin(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        System.out.println("browser was open");
        webDriver.get("https://qa-complexapp.onrender.com/");
        System.out.println("site was opened");
        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("Amberbellami");
        System.out.println("login was inputted");
        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("2020Nissan-altim");
        System.out.println("password was inputted");

        WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
        buttonSignIn.click();
        System.out.println("Button was clicked");




        Assert.assertTrue("Button is displayed",isButtonSignInDisplayed());




        webDriver.quit();
        System.out.println("browser was closed");


    }

    public boolean isButtonSignInDisplayed(){
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign In']")).isDisplayed();
        }catch (Exception e){
            return false;
        }

    }

    }




