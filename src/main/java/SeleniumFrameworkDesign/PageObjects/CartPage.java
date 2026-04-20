package SeleniumFrameworkDesign.PageObjects;

import java.util.List;

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

    @FindBy(css = ".cartSection h3")
    List<WebElement> productTitles;

    @FindBy(css = ".subtotal button")
    WebElement checkoutEle;

    // List<WebElement> cartProducts =
    // driver.findElements(By.cssSelector(".cartSection h3"));
    // Boolean match = cartProducts.stream().anyMatch(cartProduct ->
    // cartProduct.getText().equals(productName));
    // Assert.assertTrue(match);
    // driver.findElement(By.cssSelector(".subtotal button")).click();

    public Boolean VerifyProductDisplay(String productName) {

        Boolean match = productTitles.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
        return match;
    }

    public CheckoutPage goToCheckout() {
        checkoutEle.click();
        return new CheckoutPage(driver);
    }

}
