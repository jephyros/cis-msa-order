package kr.chis.cismsaorder.order.domain;

import kr.chis.cismsaorder.common.ShopStatus;
import kr.chis.cismsaorder.shop.domain.Shop;
import kr.chis.cismsaorder.shop.repository.ShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static kr.chis.cismsaorder.TestDataProduct.anOrder;
import static kr.chis.cismsaorder.TestDataProduct.anShop;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

/**
 * @author InSeok
 * Date : 2020-07-21
 * Remark :
 */
class OrderValidatorTest {
    private OrderValidator orderValidator;



    @BeforeEach
    public void setUp() {
        orderValidator = new OrderValidator(mock(ShopRepository.class));

    }

    @Test
    public void 오더_최소오더금액초과_체크(){
        Order order = anOrder().orderAmoumt(15000L).build();
        Shop shop = anShop().minOrderAmt(20000L).build();
        assertThrows(IllegalStateException.class,()->orderValidator.validator(order,shop));

    }

    @Test
    public void 오더_SHOP_오픈여부_체크(){
        Order order = anOrder().orderAmoumt(15000L).build();
        Shop shop = anShop().shopStatus(ShopStatus.CLOSE).build();
        assertThrows(IllegalArgumentException.class,()->orderValidator.validator(order,shop));

    }

}