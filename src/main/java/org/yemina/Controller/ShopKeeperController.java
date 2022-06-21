package org.yemina.Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yemina.Entities.Account;
import org.yemina.Entities.Category;
import org.yemina.Entities.ShopKeeper;
import org.yemina.InterfacesMetier.AccountInterface;
import org.yemina.InterfacesMetier.CategoryInterface;
import org.yemina.InterfacesMetier.ShopKeeperInterface;
import org.yemina.Services.ShopkeeperService;
import org.yemina.payload.rabbitmq.Producer;
import org.yemina.payload.response.MessageResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/shopkeeper")
public class ShopKeeperController {
	@Autowired
	private ShopkeeperService shopkeeperService;
	@Autowired
	ServletContext context;
	@Autowired
	private ShopKeeperInterface shopKeeperInterface;

	private AccountInterface accountInterface;

	@Autowired
	private CategoryInterface categoryInterface;

	@Autowired
	Producer producer;

	@PostMapping ("/add")
	ShopKeeper ajoutShopkeeper(@RequestBody ShopKeeper shopkeeper) {
	if (shopkeeper != null) {
		return shopKeeperInterface.add(shopkeeper);
	}
        return null;
	}

	@GetMapping ("/get")
	 Collection<ShopKeeper> getAll() {
		return shopKeeperInterface.getAll();
	}
	@GetMapping("/get_activate")
	List<ShopKeeper> getActive(){
		return shopkeeperService.getActive();
	}
	@GetMapping ("/getAcc")
	Collection<Account> getAllAcc() {
		return accountInterface.getAll();
	}

	@GetMapping("/get/{id}")
	ShopKeeper getShopKeeper(@PathVariable String id){
		return shopKeeperInterface.getId(id);
	}

	@PutMapping("/update")
    public ShopKeeper ModifierShopkeeper(@RequestBody ShopKeeper shopkeeper){
        if (shopkeeper != null) {
			ShopKeeper shop = new ShopKeeper();
        	shop = shopKeeperInterface.update(shopkeeper);
			producer.produceUpdateShopkeeper(shop);
        return shop;
        }
        return null;
    }
	@PutMapping("/active")
    public ShopKeeper activateShopkeeper(@RequestBody ShopKeeper shopkeeper){
        if (shopkeeper != null) {
        	shopkeeper.setIs_Activate(true);
        return shopKeeperInterface.update(shopkeeper);
        }
        return null;
    }
	
	@DeleteMapping("/suup/{id}")
    public void deleteshopkeeper(@PathVariable String id){
		shopKeeperInterface.delete(id);
    }


	@DeleteMapping("/supAcc/{id}")
	public void deleteAcc(@PathVariable String id){
		accountInterface.delete(id);
	}


	@PostMapping("/save")
	public ResponseEntity<MessageResponse> createArticle (@RequestParam("file") MultipartFile file,
														  @RequestParam("shopkeeper") String shopkeeper) throws JsonParseException, JsonMappingException, Exception
	{
		System.out.println("Ok .............");
		ShopKeeper arti = new ObjectMapper().readValue(shopkeeper, ShopKeeper.class);
		boolean isExit = new File(context.getRealPath("/WEB-INF/images/")).exists();
		if (!isExit) {
			new File (context.getRealPath("/WEB-INF/images/")).mkdir();
			System.out.println("mk dir.............");
		}

		String filename = file.getOriginalFilename();
		String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
		File serverFile = new File (context.getRealPath("/WEB-INF/images/"+File.separator+newFileName));
		try {
			System.out.println("Image");
			FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
		}catch(Exception e) {
			e.printStackTrace();
		}

		java.util.Collection<Category> listCategory = new ArrayList();
		for (Category c : arti.getCategory() ) {
			Category cat = categoryInterface.getId(c.getId());
			cat.getShopKeepers().add(arti);
		}

		arti.setCategory(listCategory);
		System.out.println(arti.toString());
		arti.setLogo(newFileName);
		ShopKeeper art = shopKeeperInterface.add(arti);
		if (art != null)
		{
			return new ResponseEntity<MessageResponse>(new MessageResponse ("Done"), HttpStatus.OK);
		} else
		{
			return new ResponseEntity<MessageResponse>(new MessageResponse ("Article not saved"),HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/get-shopkeeper-id/{id}")
    public ShopKeeper getShopkeeperByUsername(@PathVariable String id){
        System.out.println("String ="+id);
		//ShopKeeper customer = shopKeeperInterface.findShopKeeperByAccount_Id(id);
        //System.out.println("ShopKeeper = "+customer.toString());
        return shopKeeperInterface.findShopKeeperByAccount_Id(id);
    }

    @DeleteMapping("/delete-all")
	public void deleteAllShopkeepers(){
		shopKeeperInterface.deleteAll();
	}

	@GetMapping("/getShopkeeperByBrandName/{brandName}")
	Collection <ShopKeeper> getShopkeeperByBrandName(@PathVariable String brandName){
			//System.out.println("SHopkeeper BY brandName  ="+brandName);
			return shopKeeperInterface.findByBrandNameContainingIgnoreCase(brandName);
	}

}
