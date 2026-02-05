package tatakae.Muzan.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public Price addPrices(@PathVariable int productId, @RequestBody Price price) {
		
		Product product = productRepo.findById(productId).orElseThrow();
		price.setProduct(product);
		return priceRepo.save(price);
		
	}
	
	@GetMapping("/{productId}")
	public List<Price> getPrice(@PathVariable int productId){
		
		Product product = productRepo.findById(productId).orElseThrow();
		return product.getPrices();
	}
	
}
