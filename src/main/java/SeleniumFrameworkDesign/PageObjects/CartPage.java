package SeleniumFrameworkDesign.PageObjects;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkDesign.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By productTitlesBy = By.cssSelector(".cartSection h3");

    @FindBy(css = ".cartSection h3")
    List<WebElement> productTitles;

    @FindBy(css = ".subtotal button")
    WebElement checkoutEle;

    public Boolean VerifyProductDisplay(String productName) {
        visibilityOfElementsLocated(productTitlesBy);
        List<WebElement> productTitles = driver.findElements(productTitlesBy);
        Boolean match = productTitles.stream()
                .anyMatch(cartProduct -> cartProduct.getText().trim().equalsIgnoreCase(productName.trim()));
        return match;
    }

    public CheckoutPage goToCheckout() {
        checkoutEle.click();
        return new CheckoutPage(driver);
    }

}
