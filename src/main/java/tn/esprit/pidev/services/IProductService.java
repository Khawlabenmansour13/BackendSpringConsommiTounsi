package tn.esprit.pidev.services;
import java.util.List;
import tn.esprit.pidev.entities.Product;
public interface IProductService  {
	public Product addProduct(Product product);
    public void updateProduct (Product product, int idProduct);
    public int deleteProduct(int idProduct);
    public List<Product> getAllProducts();
    public Product getProductById(int id);
    public Product findProductByName(String name);
    public List<Product> getProductsByCategory(String categoryName);
    public List<Product> searchProducts(String productName,String categoryName);
    public boolean verifyProduct(String barCode);
    public Product findProductByBarCode(String barCode);

  }
