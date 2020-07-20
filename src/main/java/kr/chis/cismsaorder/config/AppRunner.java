package kr.chis.cismsaorder.config;

import kr.chis.cismsaorder.common.ShopStatus;
import kr.chis.cismsaorder.shop.domain.Shop;
import kr.chis.cismsaorder.shop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author InSeok
 * Date : 2020-07-20
 * Remark : 구동시 shop 1개등록
 */
@Component
public class AppRunner implements ApplicationRunner {
    @Autowired
    ShopRepository shopRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //Shop 저장
        Shop s1 = Shop.builder()
                .minOrderAmt(12000L)
                .shopStatus(ShopStatus.OPEN)
                .shopName("양꼬치대장").build();
        shopRepository.save(s1);
    }
}
