import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {
            String folderPath = FileSystemService.createFolderBasedOnDate(LocalDate.now());
            LoginService loginService = new LoginService();
            loginService.loginToWebsite();

            DataScraper scraper = new DataScraper();
            int page = 1;
            boolean morePages = true;

            while (morePages) {
                String url = "https://www.acehardware.com/somepage?page=" + page;
                String data = scraper.scrapeData(url);
                
                // Check if data is empty or some end condition is met
                if (data == null || data.isEmpty()) {
                    morePages = false;
                } else {
                    CSVWriterService.writeDataToCSV(folderPath, "data_page_" + page + ".csv", data);
                    page++; // increment to fetch the next page
                }
            }
            
            loginService.closeBrowser();
        } catch (Exception e) {
            System.out.println("Error during execution: " + e.getMessage());
            e.printStackTrace();
        }
    }
}