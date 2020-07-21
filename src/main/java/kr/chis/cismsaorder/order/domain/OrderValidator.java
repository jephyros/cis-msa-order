package kr.chis.cismsaorder.order.domain;

import kr.chis.cismsaorder.shop.domain.Shop;
import kr.chis.cismsaorder.shop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author InSeok
 * Date : 2020/07/18
 * Remark :
 */
@Component
public class OrderValidator {
    private final ShopRepository shopRepository;


    @Autowired
    public OrderValidator(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public void validate(Order order){
        validator(order,getShop(order.getShopId()));
    }

    public void validator(Order order, Shop shop){
        //인자값 Null 체크
        //가게가 영업 중 인가?
        if(!shop.isOpen()){
            throw new IllegalArgumentException("가계가 영엉중이아닙니다.");
        }

        //최소 주문금액을 가지고있는지?
        if(!shop.isValidOrderAmount(order.totalAmount())){
            throw new IllegalStateException(String.format("최소 주문 금액은 %s 원 입니다.",shop.getMinOrderAmt()));
        }

    }
    private Shop getShop(Long shopId){
        Optional<Shop> optionalShop = shopRepository.findById(shopId);
        if (!optionalShop.isPresent()){
            throw new IllegalArgumentException("샵 정보가 존재하지않습니다.");
        }
        return optionalShop.get();

    }

}
