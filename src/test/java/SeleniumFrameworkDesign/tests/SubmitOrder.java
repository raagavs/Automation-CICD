package SeleniumFrameworkDesign.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.PageObjects.CartPage;
import SeleniumFrameworkDesign.PageObjects.CheckoutPage;
import SeleniumFrameworkDesign.PageObjects.ConfirmationPage;
import SeleniumFrameworkDesign.PageObjects.OrderPage;
import SeleniumFrameworkDesign.PageObjects.ProductCatalogue;
import SeleniumFrameworkDesign.TestComponents.BaseTest;

public class SubmitOrder extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = "purchase")
    public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

        ProductCatalogue pc1 = getLandingPage().loginApplication(input.get("email"), input.get("password"));
        pc1.getProductList();
        pc1.addProductToCart(input.get("product"));
        CartPage cp = pc1.goToCart();
        Boolean match = cp.VerifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage cop = cp.goToCheckout();
        String countrySelected = "India";
        cop.selectCountry(countrySelected);
        ConfirmationPage confirmPage = cop.submitOrder();
        String confirmMessage = confirmPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }

    @Test(dependsOnMethods = { "submitOrder" })
    public void orderHistoryTest() {
        ProductCatalogue pc1 = getLandingPage().loginApplication("BlackICE@gmail.com", "Raagav@747");
        OrderPage op = pc1.goToOrdersPage();
        Assert.assertTrue(op.VerifyOrderDisplay(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(
                System.getProperty("user.dir") + "\\src\\test\\java\\frameworkDesign\\data\\PurchaseOrder.json");
        return new Object[][] { { data.get(0) }, { data.get(1) } };
    }
}