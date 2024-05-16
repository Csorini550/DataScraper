import org.apache.commons.csv.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {
    private CSVPrinter printer;

    public CSVWriter(String filePath) throws IOException {
        FileWriter out = new FileWriter(filePath);
        this.printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("Column1", "Column2", "Column3"));
    }

    public void writeData(List<String> data) throws IOException {
        printer.printRecord(data);
    }

    public void close() throws IOException {
        printer.close();
    }
}
