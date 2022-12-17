package Commerce.PageObjects;

import Commerce.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;

    @FindBy(css = "[placeholder *='Country']")
    private WebElement country;

    @FindBy (css = "[class*='btnn action']")
    private WebElement submit;

    @FindBy (css = "[class *='ta-item']")
    private   List <WebElement> dropDown;

    private By result = By.cssSelector("[class *='ta-item']");
    private By submit1 = By.cssSelector("[class*='btnn action']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void selectCountry(String countryName){
        country.sendKeys(countryName);
        waitForTheElementToAppear(result);
        dropDown.stream().filter(s->s.getText().equalsIgnoreCase(countryName)).limit(1).forEach(s->s.click());
    }

    public ConfirmationPage submitOrder(){
        waitForTheElementToAppear(submit1);
        submit.click();
        return new ConfirmationPage(driver);
    }
}
