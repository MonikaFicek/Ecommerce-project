package Commerce.Test;

import Commerce.PageObjects.CartPage;
import Commerce.PageObjects.ProductCatalogue;
import Commerce.TestComponent.BaseTest;
import Commerce.TestComponent.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorValidationTest extends BaseTest {

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void loginErrorValidation() throws IOException {
        String productName = "zara coat 3";
        landingPage.loginApplication("anshika@gmail.com","Imking@000");
       Assert.assertEquals("Incorrect email or password.",landingPage.getTheErrorMessage());

    }

    @Test
    public void verifyProductDisplay() throws IOException {
        String productName = "zara coat 3";

        ProductCatalogue productCatalogue= landingPage.loginApplication("anshika@gmail.com","Iamking@000");

        productCatalogue.addProductToCart(productName);
        CartPage cartPage= productCatalogue.goToCardPage();

        Boolean match=  cartPage.VerifyProductDisplay("Zara coat 33");
        Assert.assertFalse(match);


    }
}
