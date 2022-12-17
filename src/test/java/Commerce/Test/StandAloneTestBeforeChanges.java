package Commerce.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StandAloneTestBeforeChanges {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");

        driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
        driver.findElement(By.id("login")).click();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        String productName = "zara coat 3";
        List<WebElement> products =  driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod=  products.stream()
                .filter(product-> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);

        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();


        //waiting to texBox with info of added product
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        //waiting of invisibility ng-animating
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

       List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
      Boolean match = cartProducts.stream().allMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector(".totalRow button")).click();

        driver.findElement(By.cssSelector("[placeholder *='Country']")).sendKeys("india");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class *='ta-item']")));

        List<WebElement> dropDown = driver.findElements(By.cssSelector("[class *='ta-item']"));
        dropDown.stream().filter(s->s.getText().equalsIgnoreCase("india")).limit(1).forEach(s->s.click());

        //Thread.sleep(1000);
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[class *='ta-item']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='btnn action']"))).click();
        //driver.findElement(By.cssSelector("[class*='btnn action']")).click();

        String confirmation = driver.findElement(By.cssSelector(".hero-primary")).getText();
        org.testng.Assert.assertEquals(confirmation,"THANKYOU FOR THE ORDER.");
    }
}
