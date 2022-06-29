package tn.esprit.pidev.services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.entities.Category;
import tn.esprit.pidev.entities.Product;
import tn.esprit.pidev.entities.Promotion;
import tn.esprit.pidev.entities.Stock;
import tn.esprit.pidev.repositories.IProductRepository;
import tn.esprit.pidev.repositories.IPromotionRepository;
import tn.esprit.pidev.repositories.IStockRepository;
@Service
public class PromotionServiceImpl implements IPromotionService{
	@Autowired
	IPromotionRepository iPromotionRepository;
    @Autowired
    IProductRepository iProductRepository;
    
    
    @Autowired
    private IStockRepository iStockRepository;
    /******************Creating add method that insert Promotion into database*************/
	@Override
	public int addPromos(Promotion p) {
		
		if(p.getStartDate().after(p.getEndDate())) {
			return 0;
		}
			iPromotionRepository.save(p);
		
		return p.getIdPromotion();
	}
	
	/******************Creating update method that upgrade promotion from database*************/
	
	@Override
	public void updatePromos(Promotion p, int idPromotion) {
		Promotion promos = iPromotionRepository.findById(idPromotion).get();
		promos.setLibelle(p.getLibelle());
		promos.setDescription(p.getDescription());
		promos.setStartDate(p.getStartDate());
		promos.setEndDate(p.getEndDate());
		promos.setPercentage(p.getPercentage());
		iPromotionRepository.save(promos);
		
	}
	/******************Creating deleting method that remove promotion by id from database*************/

	@Override
	public int deletePromos(int idPromotion) {
		Promotion promos = iPromotionRepository.findById(idPromotion).get();
		iPromotionRepository.delete(promos);
		return idPromotion;
	}
	
	/***************Creating getByid method that retrieve promotions detail from database***********/
    @Override
	public Promotion getPromosById(int idPromotion) {
		return iPromotionRepository.findById(idPromotion).get();
	}
  /*******************creating getAll method that retrieve all promotions  from database*************/
	@Override
	public List<Promotion> getAllPromos() {
		List<Promotion>promotions = new ArrayList<>();
		iPromotionRepository.findAll().forEach(p -> promotions.add(p));
		return promotions;
	}
	/***************Creating getByDate method that retrieve promotions detail from database***********/
    @Override
	public List<Promotion> getPromosByDate() {
		List<Promotion> promotions = iPromotionRepository.getCurrentPromotions();
		List<Promotion>currentPromotions = new ArrayList<>();
	    for(Promotion p : promotions){
			
			currentPromotions.add(p);
		}
		return currentPromotions;
	}
    /********************************Affected ProductPromotion *****************************************/
	@Override
	public void affecterProductPromotion(int idproduct,int idpromotion) {
		Product product = iProductRepository.findById(idproduct).get();
		Promotion promotion = iPromotionRepository.findById(idpromotion).get();
	        promotion.setProduct(product);
	        iPromotionRepository.save(promotion);

	}
	/***********************************************Promotion*********************************************/
@Override
	@Scheduled(fixedDelay = 1000000)
	public String proposePromos(Promotion promotion,int ids) {
	JSONArray productJsonArray = new JSONArray();
		Stock s = iStockRepository.findById(ids).get();
		
		List<Integer> products = iPromotionRepository.getRedZonProduct(22,s.getDateExp());// product that expire in 30 day
        //Count Achat //
		List<Product>results = new ArrayList<Product>();//list
       //get only non duplicated values from list product getRedZoneProduct
		Set<Integer>filtreProduct = new HashSet<>(products);
        List<Product>productsList = new ArrayList<>();//list 
		Map<Category,List<Product>> promosByCategory= new HashMap<>(); //kol cat endo liste des products lybch tsirlihom promo
		List<Product> l=new ArrayList<Product>();//liste
		List<Promotion> resP = new ArrayList<>();
		resP = iPromotionRepository.getAllPromotion();
		Map<Integer,Integer> h = new HashMap<>();
		int k =0;
		List<Integer>copyOfSet = new ArrayList<>();
			iProductRepository.findAllById(filtreProduct).forEach(prod ->results.add(prod));//bch njib les products selon les ids wn7outhom fi liste esmha result	
			copyOfSet.addAll(filtreProduct);
			System.out.println("sizeee=====>"+copyOfSet.size());
			for(int index=0 ; index<copyOfSet.size();index++) {
						for(int  i =index; i <products.size();i++) {
							if(copyOfSet.get(index)== products.get(i)) {
								k++;
									h.put(copyOfSet.get(index),k);
									
							}		
						}
			k =0;
			System.out.println(h.size());
			System.out.println("hashmap =="+h);

			}
			//int c = 0;
			 for (Map.Entry<Integer, Integer> entry : h.entrySet()) {
				 System.out.println("okok"+entry.getValue());
					if(entry.getValue() < 15 && iProductRepository.findById(entry.getKey()).get().getMostViewed() <=4) {
						   productsList.add(iProductRepository.findById(entry.getKey()).get());
							promosByCategory.put(iProductRepository.findById(entry.getKey()).get().getCategory(),new ArrayList<Product>());
							l= promosByCategory.get(iProductRepository.findById(entry.getKey()).get().getCategory());
							l.add(iProductRepository.findById(entry.getKey()).get());
							promosByCategory.put(iProductRepository.findById(entry.getKey()).get().getCategory(), l);
                            System.out.println(iProductRepository.findById(entry.getKey()));		
                           //promotion.get(c).setProduct(iProductRepository.findById(entry.getKey()).get());
						    System.out.println("size=========="+productsList.size());
				 			}
							}
				iPromotionRepository.deleteAll();
	    		
				
				//List<Product>currentPrice =  (List<Product>) iProductRepository.findAllById(products);
			    for(int i = 0 ; i < productsList.size();i++) {
		    		try {
			    		JSONObject productJson = new JSONObject("{}");
				     	 productJson.put("Response : ","Promotion added successfully for prouct  which seen by 500 people with  3 sells ");
							productJson.put("Product id",productsList.get(i).getIdProduct());
							productJson.put("Product name",productsList.get(i).getProductName());

					productJson.put("Status product price before promotion",productsList.get(i).getPrice());

                    promotion.setProduct(iProductRepository.findById(productsList.get(i).getIdProduct()).get());
                    iPromotionRepository.addCustomPromotion(promotion.getLibelle(),promotion.getStartDate(),promotion.getEndDate(),promotion.getPercentage(),promotion.getDescription(),promotion.getProduct());
						if(promotion.getProduct().isPromotionEtat() == false ) {
							{
								float discPercent =(100f-promotion.getPercentage())/100f; 
                               float newPrice = promotion.getProduct().getPrice()* discPercent;
								promotion.getProduct().setPrice(newPrice);
								promotion.getProduct().setPromotionEtat(true);
								iProductRepository.save(promotion.getProduct());
								productJson.put("Status product price after promotion",promotion.getProduct().getPrice());

							}
							}
						
					productJson.put("Added by","Admin");
					productJsonArray.put(productJson);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    	
			    	
			    	
			    	
			    	
			 }
		    	return productJsonArray.toString();
}
@Override
	public String discountProductPromotion(int id ) {
		return "No product founded for promotion";
		
	}	
			 	
				
	}
	

			 
