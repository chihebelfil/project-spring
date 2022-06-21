package org.yemina.Services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yemina.Entities.Category;
import org.yemina.Entities.ShopKeeper;
import org.yemina.InterfacesMetier.CategoryInterface;
import org.yemina.Repository.CategoryRepository;
import org.yemina.Repository.ShopKeeperRepository;

@Service
public class CategoryService implements CategoryInterface{
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ShopKeeperRepository shopKeeperRepository;

	@Override
	public Collection<Category> getAll() {
		return categoryRepository.findAll();
	}


	@Override
	public Category getId(String id) {
		return categoryRepository.findById(id).get();
    }
	@Override
	public void Delete(String id) {
		categoryRepository.deleteById(id);
	
	}

	@Override
	public Category Add(Category c) {
		return categoryRepository.save(c);
	
	}

	@Override
	public Collection<Category> getC(String c) {
		return null;
	}

	@Override
	public Category Update(Category category) {
		Category updated = categoryRepository.findById(category.getId()).get();
		updated.setCategoryName(category.getCategoryName());
	    return categoryRepository.save(updated);
	}

	@Override
	public Optional<Category> findById(String id) {
		return categoryRepository.findById(id);
	}

	@Override
	public Collection<ShopKeeper> findShopKeeper() {
		return null;
	}

	@Override
	public void deleteAll() {
		categoryRepository.deleteAll();
	}


}
