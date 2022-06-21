package org.yemina.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.yemina.Entities.Customer;
import org.yemina.Entities.Location;
import org.yemina.Entities.ShopKeeper;

import java.util.List;


public interface LocationRepository  extends MongoRepository<Location, String > {
    List<Location> findLocationByShopkeeperId (String id);
}
