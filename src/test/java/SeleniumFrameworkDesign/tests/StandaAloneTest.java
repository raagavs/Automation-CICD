package SeleniumFrameworkDesign.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaAloneTest {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client/");
        driver.findElement(By.id("userEmail")).sendKeys("BlackICE@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Raagav@747");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".col-lg-4")));
        String productName = "ZARA COAT 3";
        List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
        WebElement prod = products.stream()
                .filter(product -> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst()
                .orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        // .ng-animating
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector(".subtotal button")).click();
        String countrySelected = "India";
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        List<WebElement> countries = driver.findElements(By.cssSelector(".ta-results button"));
        countries.stream().filter(country -> country.getText().equalsIgnoreCase(countrySelected)).findFirst()
                .ifPresent(WebElement::click);
        driver.findElement(By.cssSelector(".btnn")).click();
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        // Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();
        /*
         * Actions a = new Actions(driver);
         * a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']"
         * )), "India") .build().perform();
         * wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
         * ".ta-results"))); 
         * Option 1: CSS Selector (nth-of-type)
         * driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click(); Option
         * Option 2: XPath with Index
         * driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).
         * click(); Both approaches reliably select the second item (India).
         *
         */
//         * Easiest Approach:
//         * List<WebElement> countries = driver.findElements(By.cssSelector(".ta-results button"));
//         * for (WebElement country : countries)
//         * {if(country.getText().equalsIgnoreCase(countrySelected))
//         * {
//         * country.click();
//         * break;
//         * }
//         * }
//

    }

}
