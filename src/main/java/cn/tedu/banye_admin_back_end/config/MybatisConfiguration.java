package cn.tedu.banye_admin_back_end.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@MapperScan("cn.tedu.banye_admin_back_end.mapper")
public class MybatisConfiguration {

    public MybatisConfiguration() {
        log.info("创建配置类对象：MybatisConfiguration");
    }
}
