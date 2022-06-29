package tn.esprit.pidev.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
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
import tn.esprit.pidev.entities.Role;
import tn.esprit.pidev.entities.Roletype;
import tn.esprit.pidev.entities.Sponsor;
import tn.esprit.pidev.services.IEmailService;
import tn.esprit.pidev.services.ISponsorExchangeService;

@RestController
public class SponsorExchangeController {
	@Autowired
	ISponsorExchangeService iSponsorService;
	
	
	@Autowired
	IEmailService iEmailService;
	//creating post mapping that post the advertisement detail in the database  
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@PostMapping(value="/sponsor/exhangeSponsor", consumes = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE
	})  
	private Sponsor adSponsor (@RequestPart("spJson")String spJson,@RequestPart("logo") MultipartFile file
)   
	{  Sponsor sp = new Sponsor() ;
	
	String fileName =  StringUtils.cleanPath(file.getOriginalFilename());
		System.out.println("image name ="+fileName);
		try {
			sp= objectMapper.readValue(spJson, Sponsor.class);
		    
		    System.out.println("url ="+ServletUriComponentsBuilder.fromCurrentContextPath());
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/")
					.path(fileName).toUriString();
			System.out.println("file url =====>"+fileDownloadUri);   
			String fileNameRepo = StringUtils.cleanPath(file.getOriginalFilename());
			String uploadDir = "uploads/sponsor_image";
			sp.setPicture(fileNameRepo.getBytes()); 
			
			FileUploadUtil.saveFile(uploadDir, fileNameRepo, file);

			iSponsorService.sendExchangeSponsor(sp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		return sp;  
	}

	
	//creating a delete mapping that deletes a specified advertisement 
			@DeleteMapping("/sponsor/delete-sponsor/{sponId}")  
			private void deleteSponsor(@PathVariable("sponId") int sponsorId)   
			{  
				iSponsorService.deleteSponsor(sponsorId); 
			}
			
			@PutMapping("/sponsor/acceptExchangeSponsor/{sponId}")  
			private ResponseEntity<?> acceptExchangeSponsor(@PathVariable("sponId") int sponsorId)   
			{  
				if(iSponsorService.acceptExchangeSponsor(sponsorId) == true)  {
					// Email message
						return ResponseEntity.ok(" Sponsor Number : "+sponsorId+ " ,is Accepted with success ");


				}
				else 
					return ResponseEntity.ok("Sponsor not found");
			}	
			
			@PutMapping("/sponsor/rejectExchangeSponsor/{sponId}")  
			private ResponseEntity<?> rejectExchangeSponsor(@PathVariable("sponId") int sponsorId)   
			{  
				if(iSponsorService.rejectExchangeSponsor(sponsorId) == true)
					return ResponseEntity.ok(" Sponsor Number : "+sponsorId+ " ,Exchange rejected ");
				else 
					return ResponseEntity.ok("Sponsor not found");
			}
			
			
			@GetMapping("/sponsor/getAllSponsor")  
			public List<Sponsor> getAllSponsor()   
			{  
				return iSponsorService.getAllSponsors();
			}
			
			@GetMapping("/sponsor/getFindBySponsorId/{id}")  
			public Sponsor getFindBySponsorId(@PathVariable("id")int id)   
			{  
				return iSponsorService.getSponsorById(id);
			}

			@GetMapping("/sponsor/countSponsorAccepted")  
			public int countSponsorAccepted()   
			{  
				return iSponsorService.countAcceptedSponsor();
			}
		

}
