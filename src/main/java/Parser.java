import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

class Parser{
    Elements parse_page() throws IOException{
            Document document = Jsoup.connect("https://bt.rozetka.com.ua/air_conditioners/c80133/").get();
            return document.select("div.g-i-tile-i-title.clearfix");
    }

    void product_info(Elements promos) throws IOException {
        int a = 0;
        try {
            for (Element promo : promos) {
                Document document1 = Jsoup.connect(promo.select("a").attr("href")).get();
                String title = document1.select("h1.detail-title").text(); //Name of the current product
                Document document2 = Jsoup.connect(document1.select("div.detail-review-more").select("a").attr("href")).get();
                Elements authors = document2.select("span.pp-review-author-name");  // All commentators
                Elements comments = document2.select("div.pp-review-text-i"); //All comments
                System.out.println(authors.size() + " reviews for " + title);
                for (Element comment : comments) {
                    String com_size = String.valueOf(comment);
                    int size = com_size.length() - 41;
                    if (size > a) {
                        a = size;
                    }
                }
            }
            System.out.println("The longest comment was "+a+" characters long");
        }catch (java.lang.IllegalArgumentException strange) {
            System.out.println("We get an error, so you can't see results of my research. Say thanks to Rozetka`s devs :)");
        }
    }
}
