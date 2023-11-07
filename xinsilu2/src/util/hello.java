package util;

import java.io.File;

/**
 * @PACKAGE_NAME: util
 * @NAME: hello
 * @USER: jiang000
 * @DATE: 2023/10/20
 **/
public class hello {
    public static void main(String[] args) {
        File file = new File("D:\\uploadFiles\\researchEnroll\\2023\\教研论文\\-\\");
        if (file.exists() && file.isDirectory()) {
            System.out.println("地址有效，且是一个目录。");
        } else {
            System.out.println("地址无效或不是一个目录。");
        }
    }
}
