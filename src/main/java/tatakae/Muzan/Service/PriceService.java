package tatakae.Muzan.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tatakae.Muzan.DTO.PriceRequest;
import tatakae.Muzan.Model.Price;
import tatakae.Muzan.Model.Product;
import tatakae.Muzan.Scraper.PriceScraper;
import tatakae.Muzan.repository.PriceRepository;
import tatakae.Muzan.repository.ProductRepository;

@Service
public class PriceService {
	
	@Autowired
	private ProductRepository productRepo; 
	@Autowired
	private PriceRepository priceRepo;
	@Autowired
	private List<PriceScraper> scrapers;

	public Price addPrice(int productId, String website, int priceVal) {
		Product product = productRepo.findById(productId).orElseThrow();
		Price price = new Price();
		price.setWebsite(website);
		price.setPrice(priceVal);
		price.setDate(LocalDateTime.now());
		price.setProduct(product);
		return priceRepo.save(price);
	}
	
	public Price getCheapestPrice(int productId) {
		Product product = productRepo.findById(productId).orElseThrow();
		return priceRepo.findTopByProductOrderByPriceAsc(product);
		
	}
	
	public Price getLatestPrice(int productId) {
		Product product = productRepo.findById(productId).orElseThrow();
		return priceRepo.findTopByProductOrderByDateDesc(product);
		
	}
	
	public Page<Price> getPrice(int productId, int page, int size){
		
		Product product = productRepo.findById(productId).orElseThrow();
		Pageable pageable = PageRequest.of(page, size);
		return priceRepo.findByProductOrderByDateDesc(product, pageable);
		
	}
	
	public void addScraperPrice(int productId) {

	    Product product = productRepo.findById(productId).orElseThrow();

	    for (PriceScraper scraper : scrapers) {

	        int fetchedPrice = scraper.fetchPrice(product.getUrl());

	        addPrice(productId, scraper.getWebsiteName(), fetchedPrice);
	    }
	}

	@Scheduled(fixedRateString = "${price.scrape.interval}")
	public void autoScrapePrice() {

	    List<Product> products = productRepo.findAll();

	    for (Product product : products) {

	        try {
	            addScraperPrice(product.getId());
	            System.out.println("Price updated for product ID: " + product.getId());

	        } catch (Exception e) {

	            System.out.println("Failed to update product ID: " 
	                    + product.getId() + " Reason: " + e.getMessage());
	        }
	    }

	    System.out.println("Auto scrape cycle completed at " + LocalDateTime.now());
	}

	
}
