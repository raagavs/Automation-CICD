package frameworkDesign.AbstractComponents;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFrameworkDesign.PageObjects.CartPage;
import SeleniumFrameworkDesign.PageObjects.OrderPage;

public class AbstractComponent {
    WebDriver driver;

    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    WebElement cartHeader;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement orderHeader;

    @FindBy(id = "login")
    WebElement submit;

    By productTtitlesBy = By.cssSelector(".cartSection h3");

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForElementToAppear(By findBy)

    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

    }

    public void waitForWebelementToAppear(WebElement findBy)

    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(findBy));

    }

    public void visibilityOfElementsLocated(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));

    }

    public void waitForElementToDisappear(By ele) throws InterruptedException

    {
        // Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(ele));

    }

    public void waitForElementToBeClickable(WebElement submit) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(submit));
    }

    public CartPage goToCart() {
        waitForWebelementToAppear(cartHeader);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", cartHeader);
        try {
            cartHeader.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", cartHeader);
        }
        CartPage cp = new CartPage(driver);
        return cp;
    }

    public OrderPage goToOrdersPage() {
        try {
            orderHeader.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", orderHeader);
        }
        OrderPage op = new OrderPage(driver);
        return op;
    }

}
