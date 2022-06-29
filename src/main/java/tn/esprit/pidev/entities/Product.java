package tn.esprit.pidev.entities;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProduct;
	private String productName;
	@Lob
	private byte[] picture;
	//private String picture;
	private String description;
	private float price;
	private boolean newProduct;
    @Column(name="barCode", unique=true)
	private String barCode;
	@Temporal(TemporalType.DATE)
	private Date createdAt;
	private int mostViewed;
	private int tva;
	private int weigth;
    private boolean promotionEtat;
	@JsonIgnore
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
	private List<Promotion>promotions;
	@ManyToOne
	@JsonIgnore
	private Category category;
	@ManyToMany(mappedBy = "products")
	private List<CommandProduct> commandproducts;
	@ManyToOne
	private Radius radius;
	@ManyToMany(mappedBy = "products")
	private List<Cart>carts;
	@JsonIgnore
	@ManyToMany(mappedBy = "products")
	private List<Stock>stocks;
	
	@JsonIgnore
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "product")
	private Set<Evaluation> evaluations;

	
	
	public Product(String productName, String description,float price,boolean newProduct, String barCode,int mostViewed,int tva,int weigth,boolean promotionEtat,Category category) {
		super();
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.category = category;
		this.newProduct = newProduct;
		this.barCode = barCode;
		this.mostViewed = mostViewed;
		this.promotionEtat = promotionEtat;
		this.mostViewed = tva;
		this.mostViewed = weigth;


	}

	public Product(int idProduct, String productName, byte[] picture, String description, float price,
			float sellPrice, boolean newProduct, String barCode, Date createdAt, int mostViewed, int tva, int weigth,
			int quantity, User user, List<Promotion> promotions, Category category,
			List<CommandProduct> commandproducts, Radius radius, List<Cart> carts, List<Stock> stocks) {
		super();
		this.idProduct = idProduct;
		this.productName = productName;
		this.picture = picture;
		this.description = description;
		this.price = price;
		this.newProduct = newProduct;
		this.barCode = barCode;
		this.createdAt = createdAt;
		this.mostViewed = mostViewed;
		this.tva = tva;
		this.weigth = weigth;
		this.promotions = promotions;
		this.category = category;
		this.commandproducts = commandproducts;
		this.radius = radius;
		this.carts = carts;
		this.stocks = stocks;
	}

	public Product() {
		super();
	}
  
	



	public boolean isPromotionEtat() {
		return promotionEtat;
	}

	public void setPromotionEtat(boolean promotionEtat) {
		this.promotionEtat = promotionEtat;
	}

	public Product(int idProduct, String productName, byte[] picture, String description, float price,
			boolean newProduct, String barCode, Date createdAt, int mostViewed, int tva, int weigth,
			List<Promotion> promotions, Category category, List<CommandProduct> commandproducts, Radius radius,
			List<Cart> carts, List<Stock> stocks) {
		super();
		this.idProduct = idProduct;
		this.productName = productName;
		this.picture = picture;
		this.description = description;
		this.price = price;
		this.newProduct = newProduct;
		this.barCode = barCode;
		this.createdAt = createdAt;
		this.mostViewed = mostViewed;
		this.tva = tva;
		this.weigth = weigth;
		this.promotions = promotions;
		this.category = category;
		this.commandproducts = commandproducts;
		this.radius = radius;
		this.carts = carts;
		this.stocks = stocks;
	}

	public Product(String productName, byte[] picture, String description, float price, boolean newProduct,
			 String barCode, Date createdAt, int mostViewed, int tva, int weigth,
			List<Promotion> promotions, Category category, List<CommandProduct> commandproducts, Radius radius,
			List<Cart> carts, List<Stock> stocks) {
		super();
		this.productName = productName;
		this.picture = picture;
		this.description = description;
		this.price = price;
		this.newProduct = newProduct;
		this.barCode = barCode;
		this.createdAt = createdAt;
		this.mostViewed = mostViewed;
		this.tva = tva;
		this.weigth = weigth;
		this.promotions = promotions;
		this.category = category;
		this.commandproducts = commandproducts;
		this.radius = radius;
		this.carts = carts;
		this.stocks = stocks;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public boolean isNewProduct() {
		return newProduct;
	}

	public void setNewProduct(boolean newProduct) {
		this.newProduct = newProduct;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getMostViewed() {
		return mostViewed;
	}

	public void setMostViewed(int mostViewed) {
		this.mostViewed = mostViewed;
	}

	public int getTva() {
		return tva;
	}

	public void setTva(int tva) {
		this.tva = tva;
	}

	public int getWeigth() {
		return weigth;
	}

	public void setWeigth(int weigth) {
		this.weigth = weigth;
	}



	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<CommandProduct> getCommandproducts() {
		return commandproducts;
	}

	public void setCommandproducts(List<CommandProduct> commandproducts) {
		this.commandproducts = commandproducts;
	}

	public Radius getRadius() {
		return radius;
	}

	public void setRadius(Radius radius) {
		this.radius = radius;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}





	
	
	
}
