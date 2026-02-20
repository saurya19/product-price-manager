package tatakae.Muzan.Scraper;

import java.util.Random;

import org.springframework.stereotype.Component;

//@Component
public class MockFlipkartScraper implements PriceScraper{

	@Override
	public int fetchPrice(String productURL) {
		return 48000 + new Random().nextInt(10000);
	}
	
	@Override
	public String getWebsiteName() {
		return "flipkart";
	}
	
}
