package org.yemina.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yemina.Entities.Location;
import org.yemina.Entities.Rate;
import org.yemina.Entities.ShopKeeper;
import org.yemina.InterfacesMetier.LocationInterface;
import org.yemina.Repository.LocationRepository;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class LocationService implements LocationInterface {
    @Autowired
    LocationRepository locationRepository;

    @Override
    public Collection<Location> getAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location getId(String id) {
        return locationRepository.findById(id).get();
    }

    @Override
    public void Delete(String id) {
        locationRepository.deleteById(id);
    }

    @Override
    public Location Add(Location c) {
        return locationRepository.save(c);
    }

    @Override
    public Collection <Location> findLocationByShopkeeperId(String id) {
        return locationRepository.findLocationByShopkeeperId(id);
    }
    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }
    @Override
    public Collection<Location> findLocationByDistance(Double lat, Double lang, int dist) {
        List<Location> listLocation = new LinkedList<>();
        List<Location> listLocation_proxy = new LinkedList<>();
        listLocation = locationRepository.findAll();
        final int R = 6371;
        for(int i=0;i<listLocation.size();i++){
            Double latDistance = toRad(((listLocation.get(i).getLat())-lat));
            Double lonDistance = toRad(((listLocation.get(i).getLng())-lang));
            Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                    Math.cos(toRad(lat)) * Math.cos(toRad((double)listLocation.get(i).getLat())) *
                            Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
            Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
            Double distance = R * c;
            int IntValue = (int) Math.round(distance);
        

            if (IntValue<=dist){
                listLocation_proxy.add(listLocation.get(i));
            }
        }

        return listLocation_proxy;
    }

    @Override
    public Location Update(Location location) {
        return null;
    }
}
