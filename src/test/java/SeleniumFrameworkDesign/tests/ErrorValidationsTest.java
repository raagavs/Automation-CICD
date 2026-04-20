package SeleniumFrameworkDesign.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import SeleniumFrameworkDesign.PageObjects.CartPage;
import SeleniumFrameworkDesign.PageObjects.LandingPage;
import SeleniumFrameworkDesign.PageObjects.ProductCatalogue;
import SeleniumFrameworkDesign.TestComponents.BaseTest;
import SeleniumFrameworkDesign.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws IOException, InterruptedException {
        lp1.loginApplication("BlackICE@2gmail.com", "Raagav@7247");
        lp1.getErrorMessage();
        AssertJUnit.assertEquals("Incorrect email or password.", lp1.getErrorMessage());
    }

    @Test
    public void ProductErrorValidation() throws IOException, InterruptedException {
        String productName = "ZARA COAT 3";
        LandingPage lp1 = launchApplication();
        ProductCatalogue pc1 = lp1.loginApplication("BlackICE@gmail.com", "Raagav@747");
        pc1.getProductList();
        pc1.addProductToCart(productName);
        CartPage cp = pc1.goToCart();
        Boolean match = cp.VerifyProductDisplay("ZARA COAT 3");
        Assert.assertTrue(match);

    }

}
