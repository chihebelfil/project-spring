package org.yemina.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.yemina.Entities.Account;
import org.yemina.Entities.Category;
import org.yemina.Entities.ShopKeeper;

import java.util.Collection;
import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, String > {

}
