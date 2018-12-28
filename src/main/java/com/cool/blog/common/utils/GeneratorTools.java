package com.cool.blog.common.utils;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by luxiaotao on 2018/10/23
 * 使用方法：
 * 1.在resources文件夹下，配置generator/generatorConfig.xml文件参数
 * 2.运行GeneratorPlugin类的main方法即可
 */
public class GeneratorTools {

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();
        String configPath = Objects.requireNonNull(GeneratorTools.class.getClassLoader().getResource("generator/generatorConfig.xml")).getFile();
        Configuration config = new ConfigurationParser(warnings).parseConfiguration(new File(configPath));
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

}
