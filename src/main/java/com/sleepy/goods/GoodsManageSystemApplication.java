package com.sleepy.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.sleepy.goods", "com.sleepy.jpql"})
@EnableSwagger2
public class GoodsManageSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsManageSystemApplication.class, args);
    }

}
