package tatakae.Muzan.Controller;

import java.util.List;
import org.springframework.data.domain.Sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.validation.Valid;
import tatakae.Muzan.Model.Product;
import tatakae.Muzan.Service.ProductService;
import tatakae.Muzan.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public Product addProduct(@Valid @RequestBody Product product) {
		return productService.addingProduct(product);
	}
	
	@GetMapping
	public Page<Product> getAllProduct(
			@RequestParam(defaultValue="0") int page,
			@RequestParam(defaultValue="5") int size
			){
		
		return productService.gettingAllProduct(page, size);
	}
	
	@GetMapping("category/{category}")
	public List<Product> GetByCategory(@PathVariable String category){
		
		return productService.GettingByCategory(category);
		
	}

}
