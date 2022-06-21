package org.yemina.InterfacesMetier;

import org.yemina.Entities.Location;
import org.yemina.Entities.Rate;

import java.util.Collection;

public interface RateInterface {
    public Collection<Rate> getAll();
    public Rate getId(String id );
    public void Delete(String id);
    public Rate Add(Rate r);
    public Rate Update(Rate rate);
    Collection <Rate> findRateByShopkeeperId(String id);
    double  Averegrate(String id);

}
