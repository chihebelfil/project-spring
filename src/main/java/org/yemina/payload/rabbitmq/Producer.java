package org.yemina.payload.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.yemina.Entities.Category;
import org.yemina.Entities.ShopKeeper;
import org.yemina.Entities.Customer;

@Component
public class Producer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void produceNewShopkeeper(ShopKeeper company){
        amqpTemplate.convertAndSend("new_shopkeeper_exchange","", company);
        System.out.println("Send msg = " + company);
    }

    public void produceUpdateShopkeeper(ShopKeeper company){
        amqpTemplate.convertAndSend("update_shopkeeper_exchange","", company);
        System.out.println("Send msg = " + company);
    }

    public void produceNewCategory(Category category){
        amqpTemplate.convertAndSend("new_category_exchange","", category);
        System.out.println("Send msg = " + category);
    }

    public void produceNewCustomer(Customer customer){
        amqpTemplate.convertAndSend("new_customer_exchange","", customer);
        System.out.println("Send msg = " + customer);
    }


}
