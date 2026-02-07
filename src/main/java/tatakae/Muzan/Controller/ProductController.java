package tatakae.Muzan.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tatakae.Muzan.Model.Product;
import tatakae.Muzan.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping
	public Product addProduct(@Valid @RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@GetMapping
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}

}
