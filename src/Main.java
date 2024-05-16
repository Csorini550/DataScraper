import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            AppController app = new AppController("path/to/chromedriver", "output.csv");
            app.runApplication("https://www.acehardware.com/login", "your_username", "your_password", "https://www.acehardware.com/specific-page");
            System.out.println("Data written to CSV");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}