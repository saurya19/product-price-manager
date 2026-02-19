package tatakae.Muzan.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import tatakae.Muzan.Model.Product;
import tatakae.Muzan.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	
	public Product addingProduct(Product product) {
		
		return productRepo.save(product);
	}
	
	public Page<Product> gettingAllProduct(int page, int size){
		
		Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
		return productRepo.findAll(pageable);
		
	}
	
	public List<Product> GettingByCategory(String category){
		
		return productRepo.findByCategory(category);
		
	}
	
}
