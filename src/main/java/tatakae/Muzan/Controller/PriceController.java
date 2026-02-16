package tatakae.Muzan.Controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tatakae.Muzan.DTO.PriceRequest;
import tatakae.Muzan.Model.Price;
import tatakae.Muzan.Model.Product;
import tatakae.Muzan.Scraper.MockAmazonScraper;
import tatakae.Muzan.Service.PriceService;
import tatakae.Muzan.repository.PriceRepository;
import tatakae.Muzan.repository.ProductRepository;


@RestController
@RequestMapping("/prices")
public class PriceController {

	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private PriceService priceService;
	
	private MockAmazonScraper scraper;
	
	@PostMapping("/{productId}")
	public Price addPrices(@PathVariable int productId,@Valid @RequestBody PriceRequest request) {
		
		return priceService.addPrice(productId, request.getWebsite(), request.getPrice());
		
	}
	
	@GetMapping("/{productId}")
	public Page<Price> getPrice(@PathVariable int productId,
			@RequestParam(defaultValue="0") int page,
			@RequestParam(defaultValue="5") int size
			){
		
		return priceService.getPrice(productId, page, size);
		
	}
	
	@GetMapping("/{productId}/latest")
	public Price latestPrices(@PathVariable int productId) {
		
		return priceService.getLatestPrice(productId);
		
	}
	
	@GetMapping("/{productId}/cheapest")
	public Price cheapPrice(@PathVariable int productId) {
		
		return priceService.getCheapestPrice(productId);
		
	}
	
	@GetMapping("/{productId}/scrape")
	public Price scrapeAndSave(@PathVariable int productId) {

	    int fetchedPrice = scraper.fetchPrice("dummy-url");

	    return priceService.addPrice(productId, "Amazon", fetchedPrice);
	}

}
