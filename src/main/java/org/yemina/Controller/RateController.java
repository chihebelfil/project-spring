package org.yemina.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yemina.Entities.Rate;
import org.yemina.InterfacesMetier.RateInterface;
import org.yemina.Services.RateService;

import java.util.Collection;

@CrossOrigin("*")
@RestController
@RequestMapping("Rate")
public class RateController {
    @Autowired
    private RateService rateService;
    @Autowired
    private RateInterface rateInterface;
    @PostMapping("/add")
    Rate ajoutRate(@RequestBody Rate rate) {
        if (rate != null) {
            return rateService.Add(rate);
        }
        return null;
    }
    @GetMapping("/get")
    Collection<Rate> getAll() {
        return rateService.getAll();
    }
    @GetMapping("/findRateByShopkeeperId/{id}")
    Collection <Rate> findRateByShopkeeperId(@PathVariable String id){
        return rateService.findRateByShopkeeperId(id);
    }

    @PutMapping("/update")
    public Rate Modifierate(@RequestBody Rate rate){
        if (rate != null) {
            return rateService.Update(rate);
        }
        return null;
    }
    @GetMapping("/averageAge/{id}")
    public Double getAveregrate(@PathVariable String id) {
        return rateService.Averegrate(id);
    }
}
