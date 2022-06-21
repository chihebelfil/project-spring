package org.yemina.Configurations;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    /* ------------------------------- NEW SHOPKEEPER ------------------------------- */
    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange("new_shopkeeper_exchange", true, false);
    }

    @Bean
    Queue newShopkeeperFidelityCard() {
        return new Queue("new_shopkeeper1", true, false, false);
    }

    @Bean
    Queue newShopkeeperShopkeeper() {
        return new Queue("new_shopkeeper2", true, false, false);
    }

    @Bean
    Queue newShopkeeperDiscount() {
        return new Queue("new_shopkeeper3", true, false, false);
    }

    @Bean
    Queue newShopkeeperFavoris() {
        return new Queue("new_shopkeeper4", true, false, false);
    }

    @Bean
    Queue newShopkeeperCatalogue() {
        return new Queue("new_shopkeeperCatalogue", true, false, false);
    }

    @Bean
    Binding newShopkeeperBinding(Queue newShopkeeperFidelityCard, FanoutExchange exchange) {
        return BindingBuilder.bind(newShopkeeperFidelityCard).to(exchange);
    }

    @Bean
    Binding newShopkeeperShopkeeperBinding(Queue newShopkeeperShopkeeper, FanoutExchange exchange) {
        return BindingBuilder.bind(newShopkeeperShopkeeper).to(exchange);
    }

    @Bean
    Binding newShopkeeperDiscountBinding(Queue newShopkeeperDiscount, FanoutExchange exchange) {
        return BindingBuilder.bind(newShopkeeperDiscount).to(exchange);
    }

    @Bean
    Binding newShopkeeperFavorisBinding(Queue newShopkeeperFavoris, FanoutExchange exchange) {
        return BindingBuilder.bind(newShopkeeperFavoris).to(exchange);
    }

    @Bean
    Binding newShopkeeperCatalogueBinding(Queue newShopkeeperCatalogue, FanoutExchange exchange) {
        return BindingBuilder.bind(newShopkeeperCatalogue).to(exchange);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /* ---------------------------------------------------------------------------- */

    /* ------------------------------- UPDATE SHOPKEEPER ------------------------------- */

    @Bean
    FanoutExchange exchangeUpdateShopkeeper() {
        return new FanoutExchange("update_shopkeeper_exchange", true, false);
    }

    @Bean
    Queue updateShopkeeperDiscount() {
        return new Queue("update_shopkeeper_discount", true, false, false);
    }

    @Bean
    Binding updateShopkeeperDiscountBinding(Queue updateShopkeeperDiscount, FanoutExchange exchangeUpdateShopkeeper) {
        return BindingBuilder.bind(updateShopkeeperDiscount).to(exchangeUpdateShopkeeper);
    }

    /* ---------------------------------------------------------------------------- */

    /* ------------------------------- NEW CATEGORY ------------------------------- */
    @Bean
    FanoutExchange exchangeCategory() {
        return new FanoutExchange("new_category_exchange", true,false);
    }

    @Bean
    Queue newCategoryDiscount() {
        return new Queue("new_category", true, false, false);
    }

    @Bean
    Binding newCategoryDiscountBinding(Queue newCategoryDiscount, FanoutExchange exchangeCategory) {
        return BindingBuilder.bind(newCategoryDiscount).to(exchangeCategory);
    }

    /* ---------------------------------------------------------------------------- */


    /* ------------------------------- NEW Cu ------------------------------- */

        @Bean
    FanoutExchange exchangeCustomer() {
        return new FanoutExchange("new_customer_exchange", true,false);
    }

    @Bean
    Queue newCustomerFidelityCard() {
        return new Queue("new_customer1", true, false, false);
    }

    @Bean
    Binding newCustomerFidelityCardBinding(Queue newCustomerFidelityCard, FanoutExchange exchangeCustomer) {
        return BindingBuilder.bind(newCustomerFidelityCard).to(exchangeCustomer);
    }

    /* ---------------------------------------------------------------------------- */

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
