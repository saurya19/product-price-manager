package tatakae.Muzan.Scraper;

public interface PriceScraper {

	int fetchPrice(String productURL);
	String getWebsiteName();
	
}
