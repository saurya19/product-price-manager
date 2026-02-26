package tatakae.Muzan.DTO;

public class ProductResponse {
	
	private int productId;
	private String name;
	private String brand;
	private String Category;
	
	public ProductResponse(int productId, String name, String brand, String category) {
		super();
		this.productId = productId;
		this.name = name;
		this.brand = brand;
		Category = category;
	}

	public int getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public String getBrand() {
		return brand;
	}

	public String getCategory() {
		return Category;
	}
	

}
