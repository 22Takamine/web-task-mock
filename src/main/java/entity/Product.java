package entity;

public class Product {

	private Integer id;
	private Integer productId;
	private Integer categoryId;
	private String productName;
	private Integer price;
	private String imagePath;
	private String description;
	
	public Product() {
		
	}

	public Product(Integer productId, String productName, Integer price, Integer categoryId) {
	    this.productId = productId;
	    this.productName = productName;
	    this.price = price;
	    this.categoryId = categoryId;
	}
	
	public Product(Integer productId, String productName, Integer price, Integer categoryId, String imagePath, String description) {
		this.productId = productId;
	    this.productName = productName;
	    this.price = price;
	    this.categoryId = categoryId;
	    this.imagePath = imagePath;
	    this.description = description;
	}

	
	
	public Integer getId() {
	    return this.id;
	}

	public void setId(Integer Id) {
	    this.id = Id;
	}
	public Integer getProductId() {
	    return this.productId;
	}

	public void setProductId(Integer productId) {
	    this.productId = productId;
	}
	
	public Integer getCategoryId() {
	    return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
	    this.categoryId = categoryId;
	}

	public String getProductName() {
	    return this.productName;
	}

	public void setProductName(String productName) {
	    this.productName = productName;
	}

	public Integer getPrice() {
	    return this.price;
	}

	public void setPrice(Integer price) {
	    this.price = price;
	}
	
	public String getImagePath() {
	    return this.imagePath;
	}

	public void setImagePath(String imagePath) {
	    this.imagePath = imagePath;
	}
	
	public String getDescription() {
	    return this.description;
	}

	public void setDescription(String description) {
	    this.description = description;
	}

}
