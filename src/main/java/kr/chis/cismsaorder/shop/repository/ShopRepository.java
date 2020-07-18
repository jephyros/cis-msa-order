package kr.chis.cismsaorder.shop.repository;

import kr.chis.cismsaorder.shop.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author InSeok
 * Date : 2020/07/18
 * Remark :
 */
public interface ShopRepository extends JpaRepository<Shop,Long> {
    Optional<Shop> findByShopName(String s);
}
