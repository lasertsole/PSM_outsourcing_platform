package com.psm.utils;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.Properties;

/*用于赋值环境变量给static属性的变量，
    解决springboot按对象管理而不是按类管理，
    导致的无法给static变量赋值环境变量的问题*/
@Component
public class YmlUtil {
    private static Properties properties;

    static {
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("config/application.yml"));
        properties = yaml.getObject();
    }

    public static String getValue(String str) {
        return properties.getProperty(str);
    }
}
