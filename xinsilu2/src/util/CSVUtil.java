package util;

/**
 * Created by jinggu on 19/5/7.
 */


import domain.DownloadAllScore;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作
 */
public class CSVUtil {

    /**
     * 生成为CSV文件
     *
     * @param exportData 源数据List
     * @param fields     源数据的字段 以","为分隔符
     * @param outPutPath 文件路径
     * @param fileName   文件名称
     * @param className  exportData的类名称
     * @return
     */
    @SuppressWarnings("rawtypes")
    private static Logger logger = LogManager.getLogger(CSVUtil.class);

    public static String createCSVFile(List exportData, String fields, String outPutPath, String fileName, String className) {
        File csvFile;
        BufferedWriter csvFileOutputStream = null;
        try {
            File uploadDir = new File(UploadUtil.OutputEnrollDIR + outPutPath);
            if (!uploadDir.exists()) {
                /*
                * createNewFile创建文件，但不创建目录（默认必须try-chatch）
                * mkdir 在当前目录创建一个新的目录，但不能创建多及目录
                * mkdirs 创建多级目录，但无法创建文件
                * */
                uploadDir.mkdirs();
                logger.info("Create CSVFile uploadDir " + uploadDir.getAbsolutePath());
            }

            // 定义文件名格式并创建
            csvFile = new File(uploadDir, fileName + ".csv");
            if (!csvFile.exists()) {
                csvFile.createNewFile();
            }

            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(csvFile), "GBK"), 1024);

            // 写入字段列表
            csvFileOutputStream.write(fields + "\r\n");

            // 写入文件内容

            for (int j = 0; j < exportData.size(); j++) {
                Class Enroll = Class.forName(className);
                Method toCSVStr = Enroll.getMethod("toCSVStr");
                Object obj = toCSVStr.invoke(exportData.get(j));
                String csvStr = String.valueOf(obj);
                csvFileOutputStream.write(csvStr + "\r\n");
            }
            csvFileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvFileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return UploadUtil.WEBOutputEnrollDIR + outPutPath + fileName + ".csv";
    }

    /**
     * 测试数据
     *
     * @param args
     */

//    public static void main(String[] args) {
//        // =======改成list的格式，支持（Arraylist传入实体类的形式），改造的方法============
//        ArrayList<TrainEnroll> list = new ArrayList<TrainEnroll>();
//        TrainEnroll te1 = new TrainEnroll();
//        te1.setUserId("1");
//        te1.setUsername("u1");
//        te1.setPhone("p1手机");
//        te1.setEmail("e1邮箱");
//        TrainEnroll te2 = new TrainEnroll();
//        te2.setUserId("2");
//        te2.setUsername("u2");
//        te2.setPhone("p2手机");
//        te2.setEmail("e2邮箱");
//        list.add(te1);
//        list.add(te2);
//
//        String path = "export";
//        String fileName = "培训报名表";
//        String fields = "工号,用户名,手机,邮箱";
//
//        CSVUtil.createCSVFile(list, fields, path, fileName, "domain.TrainEnroll");
//    }
}
