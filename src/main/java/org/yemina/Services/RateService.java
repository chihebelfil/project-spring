package org.yemina.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yemina.Entities.Rate;
import org.yemina.InterfacesMetier.RateInterface;
import org.yemina.Repository.RateRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


@Service
public class RateService implements RateInterface {

    @Autowired
    RateRepository rateRepository;

    @Override
    public Collection<Rate> getAll() {
        return rateRepository.findAll();
    }

    @Override
    public Rate getId(String id) {
        return rateRepository.findById(id).get();
    }

    @Override
    public void Delete(String id) {
        rateRepository.deleteById(id);
    }

    @Override
    public Rate Add(Rate r) {
        return rateRepository.save(r);
    }

    @Override
    public Rate Update(Rate rate) {
        Rate updated = rateRepository.findById(rate.getId()).get();
        //updated.setCreationDate(account.getCreationDate());
        updated.setCustomer(rate.getCustomer());
        updated.setShopkeeper(rate.getShopkeeper());
        updated.setRateNumber(rate.getRateNumber());
        return rateRepository.save(updated);
    }

    @Override
    public Collection<Rate> findRateByShopkeeperId(String id) {
        return rateRepository.findRateByShopkeeperId(id);
    }


   @Override
    public double Averegrate(String id) {
        double avreg_rate=0;
       List<Rate> listRate = new LinkedList<>();
       listRate = rateRepository.findRateByShopkeeperId(id);
       for(int i=0;i<listRate.size();i++){
           avreg_rate += listRate.get(i).getRateNumber();
       }
        return avreg_rate/listRate.size();
    }
}
