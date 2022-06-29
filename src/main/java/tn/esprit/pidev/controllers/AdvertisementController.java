package tn.esprit.pidev.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.pidev.config.FileUploadUtil;
import tn.esprit.pidev.entities.Advertisement;
import tn.esprit.pidev.entities.Product;
import tn.esprit.pidev.services.IAdvertisementService;
@RestController
public class AdvertisementController {
	@Autowired
	IAdvertisementService iAdvertisementService;
	//creating post mapping that post the advertisement detail in the database  
	 
	//creating post mapping method that insert product into database
			ObjectMapper objectMapper = new ObjectMapper();

		//creating post mapping that post the event detail in the database  
		@PostMapping(value="/advertisement/add-advertisement", consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.MULTIPART_FORM_DATA_VALUE
		})  
		private String addAdvertisement(@RequestPart("advJson")String advJson,@RequestPart("picture") MultipartFile file){
			Advertisement advertisement  = new Advertisement();
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());
				System.out.println("image name ="+fileName);
				
				try{
					
					advertisement= objectMapper.readValue(advJson, Advertisement.class);

			    
			    
			    System.out.println("url ="+ServletUriComponentsBuilder.fromCurrentContextPath());
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/")
						.path(fileName).toUriString();
				System.out.println("file url =====>"+fileDownloadUri);
				 
				String fileNameRepo = StringUtils.cleanPath(file.getOriginalFilename());
				String uploadDir = "uploads/advertisement_image";
	 
				advertisement.setImage(fileNameRepo.getBytes());
			         
				FileUploadUtil.saveFile(uploadDir, fileNameRepo, file);
				
				return iAdvertisementService.addAdvertisement(advertisement);

			}catch(Exception e) {
				 e.printStackTrace();
			}
			    return "error found !!!!";
				
			}
	//creating a delete mapping that deletes a specified advertisement 
			@DeleteMapping("/advertisement/delete-advertisement/{advId}")  
			private void deleteAdvertisement(@PathVariable("advId") int advId)   
			{  
				iAdvertisementService.deleteAdvertisement(advId); 
			}
    //creating put mapping that updates the advertisement detail   
			@PutMapping("/advertisement/update-advertisement/{idAd}")  
			private Advertisement updateAdvertisement(@RequestBody Advertisement adv, @PathVariable("idAd")int idAd)   
			{  
				iAdvertisementService.updateAdvertisement(adv,idAd); 
				return adv;
			}
	//creating a get mapping that retrieves all the advertisement detail from the database   
			@GetMapping("/advertisement/get-all-advs")  
			private List<Advertisement> getAllAdvs()   
			{  
				return iAdvertisementService.getAllAdvertisements(); 
			}
	//creating a get mapping that retrieves the detail of a specific advertisement 
			@GetMapping("/advertisement/detail-advertisement/{idAd}")  
			private Advertisement getAdvertisement(@PathVariable("idAd") int idAd)   
			{  
				return iAdvertisementService.getAdvertisementById(idAd);
			} 
			//////////////
	//creating get mapping that getAdvertisementByName   
	        @GetMapping("/advertisement/retrieve-Advertisement-ByName/{name}")
			public Advertisement getAdvertisementByName(@PathVariable String name) {
	        	Advertisement adv = iAdvertisementService.findAdvertisementByName(name);
				return adv;
				}
    //creating put mapping that updates the event detail   
			@PutMapping("/advertisement/affecter-Ad-Typ/{typeAd}/{idadve}")  
			 private String affecterTypeAdEvent(@PathVariable("typeAd")String typeAd,@PathVariable("idadve")int idadv)   
			  {  
				return iAdvertisementService.affecterAdCategoryAdvertisement(typeAd, idadv);
			  }
	
}
