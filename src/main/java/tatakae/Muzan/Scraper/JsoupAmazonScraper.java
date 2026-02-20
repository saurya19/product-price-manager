package tatakae.Muzan.Scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class JsoupAmazonScraper implements PriceScraper {

    @Override
    public int fetchPrice(String productUrl) {

        try {
        	Document doc = Jsoup.connect(productUrl)
        		    .userAgent("Mozilla/5.0")
        		    .get();
        		Element priceElement = doc.select(".price_color").first();
        		String priceText = priceElement.text().replace("£", "");
        		return (int) Double.parseDouble(priceText);
        } 
        catch (Exception e) {
            throw new RuntimeException("Failed to scrape Amazon price", e);
        }
    }

    @Override
    public String getWebsiteName() {
        return "Amazon";
    }
}

