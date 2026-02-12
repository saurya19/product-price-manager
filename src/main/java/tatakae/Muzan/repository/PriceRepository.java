package tatakae.Muzan.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import tatakae.Muzan.Model.Price;
import tatakae.Muzan.Model.Product;

public interface PriceRepository extends JpaRepository<Price, Integer>{

	Page<Price> findByProductOrderByDateDesc(Product product, Pageable pageable);
	Price findTopByProductOrderByDateDesc(Product product);
	Price findTopByProductOrderByPriceAsc(Product product);

	
}
