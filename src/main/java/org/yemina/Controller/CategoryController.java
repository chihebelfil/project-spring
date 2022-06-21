package org.yemina.Controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yemina.Dto.CategoryDto;
import org.yemina.Dto.CategoryDtoMapper;
import org.yemina.Entities.Category;
import org.yemina.InterfacesMetier.CategoryInterface;
import org.yemina.Services.StorageService;
import org.yemina.payload.rabbitmq.Producer;
import org.yemina.payload.response.MessageResponse;

import javax.servlet.ServletContext;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryInterface categoryInterface;

	@Autowired
	ServletContext context;
	@Autowired
	CategoryDtoMapper categoryDtoMapper;

	@Autowired
	Producer producer;

	@Autowired
	private StorageService storageService;

	@PostMapping ("/add")
	Category ajoutcategory(@RequestBody Category category) {
		if (category != null) {
			return categoryInterface.Add(category);
		}
		return null;
	}


	@GetMapping ("/get")
	Collection<CategoryDto> getAll() {
		System.out.println("Categories");
		Collection<Category>getAll= categoryInterface.getAll();

		return categoryDtoMapper.entityToDtoList(getAll);
	}


	@DeleteMapping("/suup/{id}")
    public void deleteCategory(@PathVariable String id){
		categoryInterface.Delete(id);
    }


	@PutMapping("/update")
	public Category Modifcategory(@RequestBody Category category){
		if (category != null) {
			return categoryInterface.Update(category);
		}
		return null;
	}



	@GetMapping("/get/{id}")
	Category getcategory(@PathVariable  String id) {
		return categoryInterface.getId(id);
	}
/*
	@GetMapping(path="/image/{id}")
	public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
		Category category   = categoryInterface.findById(id).get();
		return Files.readAllBytes(Paths.get(context.getRealPath("/WEB-INF/images/")+category.getImageUrl()));
	}

*/
	@PostMapping("/save")
	public ResponseEntity<MessageResponse> createArticle (@RequestParam("file") MultipartFile file,
														  @RequestParam("category") String category) throws JsonParseException, JsonMappingException, Exception
	{
		System.out.println("Ok .............");
		Category arti = new ObjectMapper().readValue(category, Category.class);
		//boolean isExit = new File(context.getRealPath("/WEB-INF/images/")).exists();
		/*if (!isExit)
		{
			new File (context.getRealPath("/WEB-INF/images/")).mkdir();
			System.out.println("mk dir.............");
		}*/
		Date date= new Date();
		String filename = file.getOriginalFilename();
		String newFileName = FilenameUtils.getBaseName(filename)+date+"."+FilenameUtils.getExtension(filename);
		//File serverFile = new File (context.getRealPath("/WEB-INF/images/"+File.separator+newFileName));
		newFileName = newFileName.replaceAll("\\s", "");
		try
		{
			System.out.println("Image");
			//FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
			 storageService.uploadFile(file, newFileName);
		}catch(Exception e) {
			e.printStackTrace();
		}

		//newFileName = newFileName + "-" +date	+"."+FilenameUtils.getExtension(filename);

		arti.setImageUrl(newFileName);
		Category art = categoryInterface.Add(arti);
		this.producer.produceNewCategory(art);
		if (art != null)
		{
			return new ResponseEntity<MessageResponse>(new MessageResponse ("Done"), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<MessageResponse>(new MessageResponse ("Article not saved"),HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("delete-all")
	public void deleteAllCategories(){
		categoryInterface.deleteAll();
	}
}
