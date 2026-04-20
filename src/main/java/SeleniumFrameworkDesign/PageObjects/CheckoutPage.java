package SeleniumFrameworkDesign.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import frameworkDesign.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

    }

    By results = By.cssSelector(".ta-results");

    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;

    @FindBy(css = ".ta-item:nth-of-type(2)")
    WebElement selectCountry;

    @FindBy(css = ".btnn")
    WebElement submit;

    public void selectCountry(String countrySelected) {
        Actions a = new Actions(driver);
        a.sendKeys(country, countrySelected).build().perform();
        waitForElementToAppear(results);
        selectCountry.click();

    }

    public ConfirmationPage submitOrder() {
        submit.click();
        return new ConfirmationPage(driver);
    }

}
