package org.triathlongirls.doranssam;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        String profile = System.getProperty("spring.profiles.active");
        if(StringUtils.isBlank(profile)) {
            profile = "local";
        }
        System.setProperty("spring.profiles.active", profile);
        SpringApplication.run(Application.class, args);
    }
}
