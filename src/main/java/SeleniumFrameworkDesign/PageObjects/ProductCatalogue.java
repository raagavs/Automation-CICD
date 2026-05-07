package SeleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkDesign.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

    WebDriver driver;
    ProductCatalogue pc1;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = ".col-lg-4") // WebElement Products
    List<WebElement> products;

    @FindBy(css = ".ng-animating") // WebElement spinner - wait
    WebElement spinner1;

    By spinner = By.cssSelector(".ng-animating");

    By productsBy = By.cssSelector(".col-lg-4");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toast = By.id("toast-container");

    public List<WebElement> getProductList() {// We are getting the productsList here
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName) {
        WebElement prod = getProductList().stream()
                .filter(product -> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst()
                .orElse(null);
        return prod;
    }

    public void addProductToCart(String productName) throws InterruptedException {
        WebElement prod = getProductByName(productName);
        WebElement addToCartButton = prod.findElement(addToCart);

        // 1. Scroll the element into view so it's not at a negative coordinate
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", addToCartButton);

        // 2. Wait for it to be clickable after scroll
        waitForElementToBeClickable(addToCartButton);
        // waitForElementToAppear(addToCart);

        try {
            addToCartButton.click();
        } catch (Exception e) {
            // 3. Backup: If something is overlapping, force the click
            js.executeScript("arguments[0].click();", addToCartButton);
        }

        waitForElementToAppear(toast);
        waitForElementToDisappear(spinner);
        System.out.println("Clicked Add to Cart for: " + productName);

    }
}
