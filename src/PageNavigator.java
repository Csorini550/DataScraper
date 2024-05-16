import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PageNavigator {
    private WebDriver driver;

    public PageNavigator(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> fetchRowsFromPage(String url) {
        driver.get(url);
        return driver.findElements(By.cssSelector("table#data-table tr"));
    }
}