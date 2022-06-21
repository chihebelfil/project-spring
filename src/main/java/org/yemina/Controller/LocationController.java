package org.yemina.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yemina.Entities.Location;
import org.yemina.Entities.ShopKeeper;
import org.yemina.InterfacesMetier.ShopKeeperInterface;
import org.yemina.Services.LocationService;
import org.yemina.Services.ShopkeeperService;
import org.yemina.InterfacesMetier.LocationInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("Location")
public class LocationController {

    @Autowired
    private LocationService locationService;
    @Autowired
    private ShopkeeperService shopkeeperService;

    public LocationController() {
    }

    @GetMapping("/get")
    Collection<Location> getAll() {
        return locationService.getAll();
    }

    @PostMapping("/add/{id}")
    Location ajoutlocation(@RequestBody Collection<Location> locations, @PathVariable String id) {
        System.out.println(this.shopkeeperService.getId(id));
        locations.forEach(location -> {
            location.setShopKeeper(this.shopkeeperService.getId(id));
            locationService.Add(location);

        });

        return null;
    }
    @GetMapping("/getone/{id}")
    Location getOne(@PathVariable String id){
        return locationService.getId(id);
    }

    @GetMapping("/getLocationbyShopkeeper/{id}")
    Collection <Location> findLocationByShopkeeperId(@PathVariable String id){
        return locationService.findLocationByShopkeeperId(id);
    }
    @GetMapping("/getLocationbydistance/{lat}/{lang}/{dist}")
    Collection <Location> findLocat(@PathVariable double lat,@PathVariable double lang,@PathVariable int dist){
        Collection<Location> locationByDistance = locationService.findLocationByDistance(lat, lang, dist);
        return locationByDistance;
    }
    }
