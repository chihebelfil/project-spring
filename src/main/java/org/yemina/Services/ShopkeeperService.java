package org.yemina.Services;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yemina.Entities.Account;
import org.yemina.Entities.Rate;
import org.yemina.Entities.ShopKeeper;
import org.yemina.InterfacesMetier.ShopKeeperInterface;
import org.yemina.Repository.AccountRepository;
import org.yemina.Repository.ShopKeeperRepository;
@Service
public class ShopkeeperService implements ShopKeeperInterface {
	@Autowired
	ShopKeeperRepository shopKeeperRepository;


	@Override
	public Collection<ShopKeeper> getAll() {
		// TODO Auto-generated method stub
		return shopKeeperRepository.findAll();}

	@Override
	public ShopKeeper getId(String id) {
		// TODO Auto-generated method stub
		return shopKeeperRepository.findById(id).get();

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		shopKeeperRepository.deleteById(id);
	}

	@Override
	public ShopKeeper add(ShopKeeper c) {
		// TODO Auto-generated method stub
		return shopKeeperRepository.save(c);
	}

	@Override
	public Optional<ShopKeeper> findById(String id) {
		return shopKeeperRepository.findById(id);
	}

	@Override
	public ShopKeeper update(ShopKeeper shopKeeper) {
		// TODO Auto-generated method stub
		ShopKeeper updated = shopKeeperRepository.findById(shopKeeper.getId()).get();
		updated.setAccount(shopKeeper.getAccount());
		updated.setAddress(shopKeeper.getAddress());
		updated.setBrandName(shopKeeper.getBrandName());
		updated.setCategory(shopKeeper.getCategory());
		updated.setCity(shopKeeper.getCity());
		updated.setContry(shopKeeper.getContry());
		//updated.setFidelityCard_Shopkeeper(shopKeeper.getFidelityCard_Shopkeeper());
		updated.setLogo(shopKeeper.getLogo());
		updated.setPhoneNumber(shopKeeper.getPhoneNumber());
		//updated.setProduct(shopKeeper.getProduct());
		updated.setTaxIdentificationNumber(shopKeeper.getTaxIdentificationNumber());
		updated.setTva(shopKeeper.getTva());
		updated.setIs_Activate(shopKeeper.isIs_Activate());
		updated.setTauxConversion(shopKeeper.getTauxConversion());
		updated.setTauxRemise(shopKeeper.getTauxRemise());
		return shopKeeperRepository.save(updated);
	}

	@Override
	public ShopKeeper findShopKeeperByAccount_Id(String id) {
		System.out.println("String ="+id);
		System.out.println(shopKeeperRepository.findShopKeeperByAccount_Id(id));
		return shopKeeperRepository.findShopKeeperByAccount_Id(id);
	}

	@Override
	public void deleteAll() {
		shopKeeperRepository.deleteAll();
	}

	@Override
	public Collection <ShopKeeper> findByBrandNameContainingIgnoreCase(String brandName) {
		return shopKeeperRepository.findByBrandNameContainingIgnoreCase(brandName);
	}

	@Override
	public List<ShopKeeper> getActive() {
		List<ShopKeeper> listShopkeepr = new LinkedList<>();
		List<ShopKeeper> listShopkeepr_active = new LinkedList<>();
		listShopkeepr= shopKeeperRepository.findAll();
		for(int i=0;i<listShopkeepr.size();i++){
			if( listShopkeepr.get(i).isIs_Activate()==true){
				listShopkeepr_active.add(listShopkeepr.get(i));
			}
		}
		return listShopkeepr_active;
	}
}

