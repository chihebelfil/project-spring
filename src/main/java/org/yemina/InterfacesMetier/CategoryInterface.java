package org.yemina.InterfacesMetier;

import java.util.Collection;
import java.util.Optional;

import org.yemina.Entities.Category;
import org.yemina.Entities.ShopKeeper;

public interface CategoryInterface {
	public Collection <Category> getAll();
	public Category getId(String id );
	public void Delete(String id);
	public Category Add(Category c);
	public Collection <Category> getC(String c);
	public Category Update(Category category);
	public Optional<Category> findById(String id);

	public Collection<ShopKeeper> findShopKeeper();
	public void deleteAll();




}
