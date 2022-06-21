package org.yemina.InterfacesMetier;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.yemina.Entities.Account;
import org.yemina.Entities.Category;
import org.yemina.Entities.ShopKeeper;

public interface ShopKeeperInterface {
	public Collection <ShopKeeper> getAll();
	public ShopKeeper getId(String id );
	public void delete(String id);
	public ShopKeeper add(ShopKeeper c);
	Optional<ShopKeeper> findById(String id);
	public ShopKeeper update(ShopKeeper shopKeeper);
	public ShopKeeper findShopKeeperByAccount_Id(String id);
	public void deleteAll();
	Collection <ShopKeeper> findByBrandNameContainingIgnoreCase(String brandName);
	List<ShopKeeper> getActive();

}
