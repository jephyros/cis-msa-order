package kr.chis.cismsaorder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.Random;

/**
 * @author InSeok
 * Date : 2020/08/03
 * Remark :
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration implements AuditorAware<String> {

    @Override
    @Bean
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable("이름" + new Random().nextInt(100));
    }

}
