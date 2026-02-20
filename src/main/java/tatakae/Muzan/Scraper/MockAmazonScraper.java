package tatakae.Muzan.Scraper;

import java.util.Random;

import org.springframework.stereotype.Component;

//@Component
public class MockAmazonScraper implements PriceScraper{

	@Override
	public int fetchPrice(String productURL) {
		return 70000 + new Random().nextInt(5000);
	}
	
	@Override
	public String getWebsiteName() {
		return "amazon";
	}
}
