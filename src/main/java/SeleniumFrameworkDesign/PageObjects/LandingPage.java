package SeleniumFrameworkDesign.PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkDesign.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement userEMail;

    @FindBy(id = "userPassword")
    WebElement passwordEle;

    @FindBy(id = "login")
    WebElement submit;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errMessage;

    public ProductCatalogue loginApplication(String email, String password) {
        userEMail.sendKeys(email);
        passwordEle.sendKeys(password);
        waitForElementToBeClickable(submit);
        try {
            submit.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", submit);
        }
        ProductCatalogue pc1 = new ProductCatalogue(driver);
        return pc1;
    }

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client/");
    }

    public String getErrorMessage() {
        waitForWebelementToAppear(errMessage);
        String message = errMessage.getText();
        return message;

    }

}
