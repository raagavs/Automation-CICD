//Note- pageObjects should not hold any data, this is important
//PageObjects are only for Elements and Actions.

package SeleniumFrameworkDesign.PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkDesign.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

    WebDriver driver;// driver belong to local class

    public LandingPage(WebDriver driver) {
        // Initialization
        super(driver);
        this.driver = driver;// assigning driver from test to local
        PageFactory.initElements(driver, this);// Takes driver + current class reference (this).
        // you are settingup driver here
        // Triggers construction for all annotated elements on object creation.

    }

    // PageFactory
    @FindBy(id = "userEmail") // WebElement userEmail = driver.findElement(By.id(""));
    WebElement userEMail; // at runtime it will be converted like above comment

    @FindBy(id = "userPassword") // Still ID locator
    WebElement passwordEle;

    @FindBy(id = "login") // Descriptive name: submitButton
    WebElement submit;

    @FindBy(css = "[class*='flyInOut']") // Descriptive name: submitButton
    WebElement errMessage;

    public ProductCatalogue loginApplication(String email, String password) {
        userEMail.sendKeys(email);
        passwordEle.sendKeys(password);
        waitForElementToBeClickable(submit);
        try {
            submit.click();
        } catch (Exception e) {
            // Backup for Headless/Jenkins intercepts
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
