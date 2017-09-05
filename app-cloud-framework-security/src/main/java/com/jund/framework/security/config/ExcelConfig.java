package com.jund.framework.security.config;

import com.jund.framework.core.annotation.BeanNameGenerator;
import org.jeecgframework.poi.excel.view.JeecgMapExcelView;
import org.jeecgframework.poi.excel.view.JeecgSingleExcelView;
import org.jeecgframework.poi.excel.view.JeecgTemplateExcelView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by killer_duan on 2015/5/27.
 */
@Configuration
@ComponentScan(basePackages = "org.jeecgframework.poi.excel.view", nameGenerator = BeanNameGenerator.class)
public class ExcelConfig {

    @Bean
    public JeecgTemplateExcelView jeecgTemplateExcelView() {
        return new JeecgTemplateExcelView();
    }

    @Bean
    public JeecgMapExcelView jeecgMapExcelView() {
        return new JeecgMapExcelView();
    }

    @Bean
    public JeecgSingleExcelView jeecgExcelView() {
        return new JeecgSingleExcelView();
    }
}
