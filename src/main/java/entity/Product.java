package entity;

public class Product {

	private Integer id;
	private Integer productId;
	private Integer categoryId;
	private String productName;
	private Integer price;
	private String imagePath;
	private String description;
	
	private String categoryName;
	
	
	
	public Product() {
		
	}

	public Product(Integer productId, String productName, Integer price, String categoryName) {
	    this.productId = productId;
	    this.productName = productName;
	    this.price = price;
	    this.categoryName = categoryName;
	}
	
	public Product(Integer productId, String productName, Integer price, Integer categoryId, String imagePath, String description) {
		this.productId = productId;
	    this.productName = productName;
	    this.price = price;
	    this.categoryId = categoryId;
	    this.imagePath = imagePath;
	    this.description = description;
	}
	
	public Product(Integer id, Integer productId, String productName, Integer price, Integer categoryId, String imagePath, String description) {
		this.id = id;
		this.productId = productId;
	    this.productName = productName;
	    this.price = price;
	    this.categoryId = categoryId;
	    this.imagePath = imagePath;
	    this.description = description;
	}
	
	public Product(Integer id, Integer productId, String productName, Integer price, Integer categoryId, String categoryName, String imagePath, String description) {
		this.id = id;
		this.productId = productId;
	    this.productName = productName;
	    this.price = price;
	    this.categoryId = categoryId;
	    this.categoryName = categoryName;
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
	
	public String getCategoryName() {
	    return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
	    this.categoryName = categoryName;
	}

}
