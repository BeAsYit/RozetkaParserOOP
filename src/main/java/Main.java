
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Parser page = new Parser();
        page.product_info(page.parse_page());
    }


}