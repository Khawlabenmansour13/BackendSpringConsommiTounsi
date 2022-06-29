package tn.esprit.pidev.controllers;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import tn.esprit.pidev.config.FileUploadUtil;
import tn.esprit.pidev.entities.Product;
import tn.esprit.pidev.services.IProductService;
import tn.esprit.pidev.services.ProductServiceImpl;

@RestController
public class ProductController {
	 @Autowired
	 IProductService iProductService;

	 
	//creating post mapping method that insert product into database
		ObjectMapper objectMapper = new ObjectMapper();

	//creating post mapping that post the event detail in the database  
	@PostMapping(value="/product/add-product", consumes = {
				MediaType.APPLICATION_JSON_VALUE,
				MediaType.MULTIPART_FORM_DATA_VALUE
	})  
	private String addProduct(@RequestPart("prJson")String prJson,@RequestPart("picture") MultipartFile file){
	
		Product product  = new Product();
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			System.out.println("image name ="+fileName);
			
			try{
				
				product= objectMapper.readValue(prJson, Product.class);
		    if(iProductService.addProduct(product).equals(null)) {
		    	return "Product missing 619";
		    }
		    
		    System.out.println("url ="+ServletUriComponentsBuilder.fromCurrentContextPath());
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/")
					.path(fileName).toUriString();
			System.out.println("file url =====>"+fileDownloadUri);
			String fileNameRepo = StringUtils.cleanPath(file.getOriginalFilename());
			String uploadDir = "uploads/product_image";
 
				product.setPicture(fileNameRepo.getBytes());
		         
			FileUploadUtil.saveFile(uploadDir, fileNameRepo, file);

            iProductService.addProduct(product);  
		}catch(Exception e) {
			return "Duplicated barecode product or exception="+e.getMessage();
		}
		     
    		JSONArray productJsonArray = new JSONArray();

			try {
		    		JSONObject productJson = new JSONObject("{}");
			     	 productJsonArray.put(productJson);

				productJson.put("Response","Product added successfully!!");
				productJson.put("Product id",product.getIdProduct());
				productJson.put("Product name",product.getProductName());

				productJson.put("Added by","Admin");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     	 return productJsonArray.toString();
		     
			
		}
	 
	//creating put mapping that updates the product detail  
	 @PutMapping("/product/update-product/{idProduct}")
	 @ResponseBody
		public ResponseEntity<String> updateProduct(
			@RequestBody Product product,@PathVariable("idProduct")int idProduct) {
		    iProductService.updateProduct(product,idProduct);
		    return new ResponseEntity<String>("Product updated successfully",HttpStatus.OK);
			
		}
	 
	  //creating a delete mapping that delete data from database
		@DeleteMapping("/product/delete-product/{idProduct}")
		@ResponseBody
		public ResponseEntity<String>  deleteProduct(
			@RequestBody Product product,@PathVariable("idProduct")int idProduct) {
			iProductService.deleteProduct(idProduct);
		    return new ResponseEntity<String>("Category deleted successfully",HttpStatus.ACCEPTED);
			
		}
		
		//creating a get mapping that retrieves all the product detail from the database   
		@GetMapping("/product/get-all-product")
		@ResponseBody
		public List<Product>  getAllProduct() {
			List<Product> product = new ArrayList<>();
			for(Product p : iProductService.getAllProducts()) {
				product.add(p);
			}
			return product;
		}
		
		//creating a get mapping that retrieves a specific product
		@GetMapping("/product/get-productbyId/{idp}")
		@ResponseBody
		public Product getProductById(@PathVariable("idp")int idp) {
			
			return iProductService.getProductById(idp);
		}
		
		//creating a get mapping that retrieves a specific product
		@GetMapping("/product/get-productbyCategory/{category}")
		@ResponseBody
		public List<Product> getProductById(@PathVariable("category")String categoryName) {
			
			return iProductService.getProductsByCategory(categoryName);
		}
		//creating get mapping that getProductByName   
        @GetMapping("/product/retrieve-Product-ByName/{name}")
		public Product findProductByName(@PathVariable String name) {
			Product product= iProductService.findProductByName(name);
			return product;
			}
        
        //Search product 
        @PutMapping("/product/searchProducts/{prod}/{cat}")
		@ResponseBody
		public List<Product>searchProducts(@PathVariable("prod")String prod,@PathVariable("cat")String cat) {
        	List<Product>products = iProductService.searchProducts(prod, cat);
        	return products;
        }
		//creating a get mapping that retrieves a specific productByBareCode
		@GetMapping("/product/getProductbyBarCode/{barCode}")
		@ResponseBody
		public Product getProductByBarCode(@PathVariable("barCode")String barCode) {
			
			return iProductService.findProductByBarCode(barCode);
		}
		//Verify 
		/*@GetMapping("/product/get-productbyBarCode/{barCode}")
		@ResponseBody
		public ResponseEntity<String> verifyProductByBarCode(@PathVariable("barCode")String barrecode) {
			if(iProductService.verifyProduct(barrecode)== true) 
			    return new ResponseEntity<String>("Product valid",HttpStatus.ACCEPTED);
			else 
				return new ResponseEntity<String>("Product invalid",HttpStatus.NOT_ACCEPTABLE);
		}
		*/
		private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode.png";
        @GetMapping(value = "/genrateAndDownloadQRCode/{codeText}/{width}/{height}")
			public void download(
					@PathVariable("codeText") String codeText,
					@PathVariable("width") Integer width,
					@PathVariable("height") Integer height)
				    throws Exception {
				        ProductServiceImpl.generateQRCodeImage(codeText, width, height, QR_CODE_IMAGE_PATH);
				    }

	    @GetMapping(value = "/genrateQRCode/{codeText}/{width}/{height}")
	   	public ResponseEntity<byte[]> generateQRCode(
	   			@PathVariable("codeText") String codeText,
	   			@PathVariable("width") Integer width,
	   			@PathVariable("height") Integer height)
	   		    throws Exception {
	   		        return ResponseEntity.status(HttpStatus.OK).body(ProductServiceImpl.getQRCodeImage(codeText, width, height));
	   		    }
		
		
}
