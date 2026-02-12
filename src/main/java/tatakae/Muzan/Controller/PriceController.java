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
import tatakae.Muzan.Model.Price;
import tatakae.Muzan.Model.Product;
import tatakae.Muzan.repository.PriceRepository;
import tatakae.Muzan.repository.ProductRepository;


@RestController
@RequestMapping("/prices")
public class PriceController {

	@Autowired
	private PriceRepository priceRepo;
	@Autowired
	private ProductRepository productRepo;
	
	@PostMapping("/{productId}")
	public Price addPrices(@PathVariable int productId,@Valid @RequestBody Price price) {
		
		Product product = productRepo.findById(productId).orElseThrow();
		price.setProduct(product);
		price.setDate(LocalDateTime.now());
		return priceRepo.save(price);
		
	}
	
	@GetMapping("/{productId}")
	public Page<Price> getPrice(@PathVariable int productId,
			@RequestParam(defaultValue="0") int price,
			@RequestParam(defaultValue="5") int size
			){
		
		
		
		Product product = productRepo.findById(productId).orElseThrow();
		Pageable pageable = PageRequest.of(price, size);
		return priceRepo.findByProductOrderByDateDesc(product, pageable);
	}
	
	@GetMapping("/{productId}/latest")
	public Price latestPrice(@PathVariable int productId) {
		
		Product product = productRepo.findById(productId).orElseThrow();
		return priceRepo.findTopByProductOrderByDateDesc(product);
		
	}
	
	@GetMapping("/{productId}/cheapest")
	public Price cheapPrice(@PathVariable int productId) {
		
		Product product = productRepo.findById(productId).orElseThrow();
		return priceRepo.findTopByProductOrderByPriceAsc(product);
		
	}
}
