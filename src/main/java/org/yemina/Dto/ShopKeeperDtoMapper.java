package org.yemina.Dto;

import org.springframework.stereotype.Component;
import org.yemina.Entities.Category;
import org.yemina.Entities.ShopKeeper;

import java.util.Collection;
import java.util.stream.Collectors;


@Component
public class ShopKeeperDtoMapper {

    public ShopKeeperDto entityToDto (ShopKeeper shopKeeper){
        ShopKeeperDto shopKeeperDto = new ShopKeeperDto();
        shopKeeperDto.setId_ShopKeeper(shopKeeper.getId());
        shopKeeperDto.setBrandName(shopKeeper.getBrandName());
        shopKeeperDto.setLogo(shopKeeper.getLogo());
        shopKeeperDto.setPhoneNumber(shopKeeper.getPhoneNumber());
        shopKeeperDto.setTaxIdentificationNumber(shopKeeper.getTaxIdentificationNumber());
        return shopKeeperDto;
    }

    public ShopKeeper DtoToEntity (ShopKeeperDto shopKeeperDto){
        ShopKeeper shopKeeper =new ShopKeeper();
        shopKeeper.setId(shopKeeperDto.getId_ShopKeeper());
        shopKeeper.setBrandName(shopKeeperDto.getBrandName());
        shopKeeper.setLogo(shopKeeperDto.getLogo());
        shopKeeper.setPhoneNumber(shopKeeperDto.getPhoneNumber());
        shopKeeper.setTaxIdentificationNumber(shopKeeperDto.getTaxIdentificationNumber());
        return shopKeeper;
    }

    public Collection<ShopKeeperDto> entityToDtoList(Collection<ShopKeeper> shopKeepers) {
        return
                shopKeepers.stream().map((x) -> entityToDto(x)).collect(Collectors.toList());
    }

    public Collection<ShopKeeper> DtoToEntityList(Collection<ShopKeeperDto> shopKeeperDtos) {
        return
                shopKeeperDtos.stream().map((x) -> DtoToEntity(x)).collect(Collectors.toList());

    }
}
