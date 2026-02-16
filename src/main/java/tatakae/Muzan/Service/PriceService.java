package tatakae.Muzan.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tatakae.Muzan.DTO.PriceRequest;
import tatakae.Muzan.Model.Price;
import tatakae.Muzan.Model.Product;
import tatakae.Muzan.repository.PriceRepository;
import tatakae.Muzan.repository.ProductRepository;

@Service
public class PriceService {
	
	@Autowired
	private ProductRepository productRepo; 
	@Autowired
	private PriceRepository priceRepo;

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
	
}
