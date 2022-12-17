package Commerce.StepDefinition;

import Commerce.PageObjects.*;
import Commerce.TestComponent.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class StepDefinitionImpl extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public CartPage cartPage;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page () throws IOException {
        landingPage= launchApplication();
    }
    @Given ("^Logged in username (.+) and (.+)$")
    public void logged_in_name_and_password(String usename, String password){
        productCatalogue= landingPage.loginApplication(usename, password);
    }

    @When("^I add the product (.+) to Cart$")
    public void i_add_the_product_to_cart(String productName){
        productCatalogue.addProductToCart(productName);
    }
    @And("^Checkout (.+) and submit the order$")
    public void checkout_productName_and_submit_the_order(String productName){
        cartPage= productCatalogue.goToCardPage();

        Boolean match=  cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage= cartPage.goToCheckOut();

        checkoutPage.selectCountry("india");
        confirmationPage=  checkoutPage.submitOrder();
    }
    @Then("{string} verify if message is displayed on ConfirmationPage")
    public void verify_if_message_is_displayed_on_Confirmation_page (String string){
        String confirmationMessage = confirmationPage.verifyConfirmationMessage();
        Assert.assertEquals(confirmationMessage,string);
        driver.close();
    }
    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable {
        Assert.assertEquals(strArg1,landingPage.getTheErrorMessage());
        driver.close();
    }


}
