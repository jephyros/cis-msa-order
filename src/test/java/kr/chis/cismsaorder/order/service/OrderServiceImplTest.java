package kr.chis.cismsaorder.order.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author InSeok
 * Date : 2020-07-16
 * Remark :
 */
@SpringBootTest
@ActiveProfiles("test")
class OrderServiceImplTest {
    @Test
    public void save_Test(){
        assertThat("d").describedAs("test").isEqualTo("d");
    }
}