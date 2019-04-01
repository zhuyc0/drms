package com.why.drms;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * <p>
 * mysql 代码生成器
 * </p>
 *
 * @author zhuyc
 * @since 2019-03-20
 */
public class CodeGenerator {

    /**
     * RUN THIS
     */
    public static void main(String[] args) {
//      代码生成器
        AutoGenerator mpg = new AutoGenerator();

//      全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("C://drms//src//main//java");
        gc.setFileOverride(true);
        gc.setAuthor("why");
//        gc.setEnableCache(true);
        gc.setOpen(false);
//        gc.setSwagger2(true);
        gc.setEntityName("%sEntity");
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);

//      数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl("jdbc:mysql://localhost:3306/db_drms?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

//      策略配置
        StrategyConfig sc = new StrategyConfig();
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        sc.setEntityLombokModel(true);
        sc.setRestControllerStyle(true);
        sc.setEntityTableFieldAnnotationEnable(true);
//        sc.setInclude("user","t_area");
//        sc.setTablePrefix("t_");
        mpg.setStrategy(sc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.why.drms");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");
        pc.setMapper("mapper");
        pc.setXml("xml");
        mpg.setPackageInfo(pc);

        // 自定义配置


        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！

        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
