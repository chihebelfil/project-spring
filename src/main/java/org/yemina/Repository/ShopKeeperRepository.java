package org.yemina.Repository;


import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.yemina.Entities.Account;
import org.yemina.Entities.ShopKeeper;

import java.util.List;
import java.util.Optional;

public interface ShopKeeperRepository  extends MongoRepository<ShopKeeper, String > {
    ShopKeeper findShopKeeperByAccount_Id(String id);
    List<ShopKeeper> findByBrandNameContainingIgnoreCase (String brandName);


}
