package tn.esprit.pidev.entities;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategory;
	private String name;
	@OneToMany(mappedBy = "category")
	private List<Product>products;
    public Category() {
		super();
	}

	public Category(int idCategory, String name, List<Product> products) {
		super();
		this.idCategory = idCategory;
		this.name = name;
		this.products = products;
	}
	public Category(String name, List<Product> products) {
		super();
		this.name = name;
		this.products = products;
	}
   public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Category [idCategory=" + idCategory + ", name=" + name + "]";
	}
	
	
}
