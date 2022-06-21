package org.yemina.Dto;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;


@Component
public class DiscountDtoMappper {

    /*public DiscountDto entityToDto (Discount discount){
        DiscountDto discountDto = new DiscountDto();
        discountDto.setId(discount.getId());
        discountDto.setPercentage(discount.getPercentage());
        discountDto.setDateCreation(discount.getDateCreation());
        discountDto.setDateExpiration(discount.getDateExpiration());
        discountDto.setDescription(discount.getDescription());
        discountDto.setNumberOfCoupons(discount.getNumberOfCoupons());
        discountDto.setProduct(discount.getProduct());
        return discountDto;
    }

    public Discount DtoToEntity (DiscountDto discountDto){
        Discount discount=new Discount();
        discount.setId(discountDto.getId());
        discount.setPercentage(discountDto.getPercentage());
        discount.setDateCreation(discountDto.getDateCreation());
        discount.setDateExpiration(discountDto.getDateExpiration());
        discount.setDescription(discountDto.getDescription());
        discount.setNumberOfCoupons(discountDto.getNumberOfCoupons());
        discount.setProduct(discountDto.getProduct());
        return discount;
    }

    public Collection<DiscountDto> entityToDtoList(Collection<Discount> discounts) {
        return
                discounts.stream().map((x) -> entityToDto(x)).collect(Collectors.toList());
    }

    public Collection<Discount> DtoToEntityList(Collection<DiscountDto> discountDtos) {
        return
                discountDtos.stream().map((x) -> DtoToEntity(x)).collect(Collectors.toList());

    }*/

}
