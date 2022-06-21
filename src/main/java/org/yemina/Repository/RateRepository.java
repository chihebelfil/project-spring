package org.yemina.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.yemina.Entities.Location;
import org.yemina.Entities.Rate;

import java.util.List;

public interface RateRepository  extends MongoRepository<Rate, String >{
    List<Rate> findRateByShopkeeperId (String id);

}
