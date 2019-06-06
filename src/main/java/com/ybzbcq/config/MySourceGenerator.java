package com.ybzbcq.config;

import org.mybatis.generator.api.ShellRunner;

import java.io.IOException;

//利用mybatis.generator自动生成代码  打包的时候main方法需要注释掉
public class MySourceGenerator {

    public static void main(String[] args) throws IOException {
        args = new String[] { "-configfile", "src\\main\\resources\\config\\generatorConfig.xml", "-overwrite" };
        ShellRunner.main(args);
    }

}
