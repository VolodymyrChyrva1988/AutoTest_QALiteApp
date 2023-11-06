package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

import java.sql.SQLException;

public class HomePage extends ParentPage {



    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    @Step
    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!headerElement.isButtonSignOutDisplayed()) {
            loginPage.fillingLoginFormWithValidCred();
        }
        checkIsRedirectToHomePage();
        return this;
    }
    @Step
    public HomePage checkIsRedirectToHomePage() {
        checkURL();
        waitChatToBeHide();
        Assert.assertTrue("HomePage is not loaded", headerElement.isButtonSignOutDisplayed());
        return this;
    }


    public HomePage openHomePageDB() throws SQLException, ClassNotFoundException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!headerElement.isButtonSignOutDisplayed()) {
            loginPage.fillingLoginFormWithValidCredBD();
        }
        checkIsRedirectToHomePage();
        return new HomePage(webDriver);
    }


}