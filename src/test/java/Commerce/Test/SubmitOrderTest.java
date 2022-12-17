package Commerce.Test;

import Commerce.PageObjects.*;
import Commerce.TestComponent.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "zara coat 3";
    @Test (dataProvider ="getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String,String> input) throws IOException {


        ProductCatalogue productCatalogue= landingPage.loginApplication(input.get("email"),input.get("password"));

        productCatalogue.addProductToCart(input.get("productName"));
        CartPage cartPage= productCatalogue.goToCardPage();

        Boolean match=  cartPage.VerifyProductDisplay(input.get("productName"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage= cartPage.goToCheckOut();

        checkoutPage.selectCountry("india");
        ConfirmationPage confirmationPage=  checkoutPage.submitOrder();
        //Assert.assertEquals(confirmationPage.verifyConfirmationMessage(),"THANKYOU FOR THE ORDER.");
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest(){

        ProductCatalogue productCatalogue= landingPage.loginApplication("anshika@gmail.com","Iamking@000");
        OrderPage orderPage= productCatalogue.goToOrderPage();
        Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));

    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String >> data = getJasonDataToMap(System.getProperty("user.dir") +"\\src\\test\\java\\Commerce\\Data\\PurchaseOrder.json");

        return new Object[][]{{data.get(0)},{data.get(1)}};


//        HashMap<String,String> map = new HashMap<>();
//        map.put("email","anshika@gmail.com");
//        map.put("password","Iamking@000");
//        map.put("productName","zara coat 3");
//
//        HashMap<String,String> map1 = new HashMap<>();
//        map1.put("email","shetty@gmail.com");
//        map1.put("password","Iamking@000");
//        map1.put("productName","adidas original");
        // return new Object[][]{{map},{map1}};


    }
}
