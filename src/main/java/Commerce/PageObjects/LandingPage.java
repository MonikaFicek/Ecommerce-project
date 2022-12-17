package Commerce.PageObjects;

import Commerce.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    private WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    //WebElement userEmail = driver.findElement(By.id("userEmail")).sendKeys;
    //PageFactory
    @FindBy(id="userEmail")
    private WebElement userEmail;

    @FindBy(id="userPassword")
    private   WebElement userPassword;

    @FindBy(id="login")
    private   WebElement login;

    @FindBy (css = "[class*='flyInOut']")
    private    WebElement errorMessage;
    public ProductCatalogue loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        login.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }

    public String getTheErrorMessage(){
        waitForTheElementToAppear(errorMessage);
        return errorMessage.getText();
    }
    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }
}
