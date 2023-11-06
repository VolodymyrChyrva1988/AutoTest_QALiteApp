package pages;

import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class CommonActionsWithElements {


    @FindBy(xpath = ".//select[@name=\"select1\"]")
    private WebElement dropDown;
    @FindBy(xpath = ".//*[@value=\"One Person\"]")
    private WebElement dropDownValue;


    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    WebDriverWait webDriverWait10, webDriverWait15;
    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_HIGHT()));

    }

    protected void enterTextInToElement(WebElement webElement, String text) {
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted in to element " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            String name = getElementName(webElement);
            webElement.click();
            logger.info("Element was clicked");

        } catch (Exception e) {
            printErrorAndStopTest(e);
        }

    }

    protected void clickOnElement(String xpath) {
        try {
            clickOnElement(webDriver.findElement(By.xpath(xpath)));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }

    }

    protected boolean checkBoxIsSelected(WebElement checkbox) {
        return checkbox.isSelected();
    }

    protected void checkCheckBox(WebElement checkbox) {
        if (!checkBoxIsSelected(checkbox)) {
            clickOnElement(checkbox);
            logger.info("CheckBox is selected");
        } else
            logger.info("CheckBox was selected already");
    }

    protected void uncheckCheckBox(WebElement checkbox) {
        if (checkBoxIsSelected(checkbox)) {
            clickOnElement(checkbox);
            logger.info("CheckBox is not selected");
        } else
            logger.info("CheckBox was deselected already");
    }

    protected void changeCheckBoxStatus(WebElement checkbox, String desiredStatus) {
        if (desiredStatus.equalsIgnoreCase("check")) {
            checkCheckBox(checkbox);
        } else {
            if (desiredStatus.equalsIgnoreCase("uncheck")) {
                uncheckCheckBox(checkbox);
            } else
                logger.info("Unknown status, select check or uncheck");
            Assert.fail();
        }
    }


    protected void selectTextInDropDown(WebElement dropDown, String visibleText) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(visibleText);
            logger.info(visibleText + "was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in DropDown");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

    protected void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element" + e);
        Assert.fail("Can not work with element" + e);
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {

            boolean state = webElement.isDisplayed();
            String message;
            if (state) {
                message = getElementName(webElement) + "Element is displayed";
            } else {
                message = getElementName(webElement) + "Element is not displayed";
            }
            logger.info(message);
            return state;

        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }


    }

    protected boolean isElementDisplayed(String xpath) {
        try {

            boolean state = webDriver.findElement(By.xpath(xpath)).isDisplayed();
            WebElement element = webDriver.findElement(By.xpath(xpath));
            isElementDisplayed(element);
            return state;

        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;

        }


    }


    public void selectTextInDropDownByUI() {
        try {
            dropDown.click();
            clickOnElement(dropDownValue);
            logger.info(dropDownValue + "was selected in DropDown");

        } catch (Exception e) {
            printErrorAndStopTest(e);
        }


    }


    public void usersPressesKeyEnterTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.ENTER).build().perform();
        }
    }

    public void usersPressesKeyTabTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.TAB).build().perform();
        }

    }

    public void usersPressesKeyTime(Keys keys, int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(keys).build().perform();
        }

    }

    public void userOpensNewTab() {
        ((JavascriptExecutor) webDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
    }
}