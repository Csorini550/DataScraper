import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AppController {
    private LoginManager loginManager;
    private PageNavigator navigator;
    private CSVWriter writer;

    public AppController(String driverPath, String filePath) throws IOException {
        this.loginManager = new LoginManager(driverPath);
        this.navigator = new PageNavigator(loginManager.getDriver());
        this.writer = new CSVWriter(filePath);
    }

    public void runApplication(String loginUrl, String username, String password, String dataUrl) {
        try {
            loginManager.login(loginUrl, username, password);
            List<WebElement> rows = navigator.fetchRowsFromPage(dataUrl);

            for (WebElement row : rows) {
                List<String> data = row.findElements(By.tagName("td")).stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());
                writer.writeData(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            loginManager.close();
        }
    }
}