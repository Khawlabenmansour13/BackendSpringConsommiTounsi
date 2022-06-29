package tn.esprit.pidev.services;
import java.util.List;
import tn.esprit.pidev.entities.Category;
public interface ICategoryService {
	public int  addCategory(Category c);
	public void deleteCategory(int idCategory);
	public void updateCategory(Category c,int idCategory);
	public List<Category> getAllCategories();
	public Category findCategoryById(int id);
	public Category findCategoryByName(String name);
	public String affecterCategoryProduct(int idp,int idc);
}
