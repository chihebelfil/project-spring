package org.yemina.InterfacesMetier;


import org.yemina.Entities.Location;
import org.yemina.Entities.Rate;
import org.yemina.Entities.ShopKeeper;

import java.util.Collection;

public interface LocationInterface {
    public Collection<Location> getAll();
    public Location getId(String id );
    public void Delete(String id);
    public Location Add(Location c);
    public Location Update(Location location);
    Collection <Location> findLocationByShopkeeperId(String id);
    Collection <Location> findLocationByDistance(Double lat ,Double lang,int dist);
}
