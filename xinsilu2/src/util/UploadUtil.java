package util;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by jinggu on 18/5/29.
 * 存放一些常量 后面需要改为配置文件.xml
 */
public class UploadUtil {
    // 每一页显示的列表元素个数
    public static final int NUM = 8;
    public static final String DISKDIR = ConfUtil.getDiskDir();
    public static final String WEBDIR = ConfUtil.getWebDir();

    // 新闻附件目录
    public static final String FilesDIR = DISKDIR + "files/";
    public static final String WEBFilesDIR = WEBDIR + "files/";

    // admin: 申报表导出目录
    public static final String OutputEnrollDIR = DISKDIR + "output-enroll/";
    public static final String WEBOutputEnrollDIR = WEBDIR + "output-enroll/";

    // user: 课题申报表目录
    public static final String ResearchEnrollDIR = DISKDIR + "researchEnroll/";
    public static final String WEBResearchEnrollFilesDIR = WEBDIR + "researchEnroll/";

    // user: 项目申请表目录
    public static final String ProjectEnrollDIR = DISKDIR + "projectEnroll/";
    public static final String WEBProjectEnrollFilesDIR = WEBDIR + "projectEnroll/";

    private static Logger logger = LogManager.getLogger(CSVUtil.class);

    public static String uploadProjectEnrollFile(String fileName, File userFile) {
        try {
            Calendar date = Calendar.getInstance();
            /*数据存储按照提交年份存储*/
            String year = String.valueOf(date.get(Calendar.YEAR));
            String dir = ProjectEnrollDIR + year + "/";
            File uploadDir = new File(dir);

            if (!isValidFileName(fileName)) {
                throw new IOException("upload project enroll dir not exist");
            }

            //目录不存在
            if (!uploadDir.exists()) {
                logger.info("upload project enroll dir not exist, path: " + uploadDir.getAbsolutePath());
                throw new IOException("upload project enroll dir not exist");
            }

            File uploadFile = new File(uploadDir, fileName);
            if (uploadFile.exists()) {
                logger.info("project enroll file is existed. delete first. " + uploadFile.getAbsolutePath());
                uploadFile.renameTo(new File(uploadFile.getAbsolutePath() + ".bak"));
//                uploadFile.delete();
            }
            FileUtils.copyFile(userFile, uploadFile);
            logger.info("upload project enroll successfully. " + uploadFile.getAbsolutePath());
            return WEBProjectEnrollFilesDIR + year + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }

    public static String uploadResearchEnrollFile(String fileName, File userFile) {
        try {
            Calendar date = Calendar.getInstance();
            String year = String.valueOf(date.get(Calendar.YEAR));
            String dir = ResearchEnrollDIR + year + "/";
            File uploadDir = new File(dir);

            if (!isValidFileName(fileName)) {
                throw new IOException("upload project enroll dir not exist");
            }

            //目录不存在
            if (!uploadDir.exists()) {
                //logger.info("create upload research enroll dir" + uploadDir.getAbsolutePath());
                //throw new IOException("upload research enroll dir not exist");
                uploadDir.mkdirs();
            }

            File uploadFile = new File(uploadDir, fileName);
            if (uploadFile.exists()) {
                logger.info("research enroll file is existed. delete first. " + uploadFile.getAbsolutePath());
                uploadFile.renameTo(new File(uploadFile.getAbsolutePath() + ".bak"));
            }
            FileUtils.copyFile(userFile, uploadFile);
            logger.info("upload research enroll successfully. " + uploadFile.getAbsolutePath());
            return WEBResearchEnrollFilesDIR + year + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void deleteOldFile(String dirName, String reportName){
        try {
            Calendar cal = Calendar.getInstance();
            int currentYear = cal.get(Calendar.YEAR);
            int currentMonth = cal.get(Calendar.MONTH);
            //项目申报提交时间为前一年12月和今年1月
            if(reportName.contains("项目申报") && currentMonth == 11){
                currentYear = currentYear + 1;
            }
            String year = String.valueOf(currentYear);
            //String year = "2022";
            String dir = ResearchEnrollDIR + year + "/" + reportName + "/" + dirName + "/";
            File uploadDir = new File(dir);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

            if (uploadDir.exists() && uploadDir.isDirectory() && uploadDir.listFiles()!=null) {
                File[] children = uploadDir.listFiles();
                //递归删除目录中文件
                for(File file : children){
                    if(file.isFile()){
                        file.delete();
                    }
                }
                logger.info("delete file " + uploadDir.getAbsolutePath());
                //uploadDir.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 删除原来的文件，但保留指定的文件名对应的文件
    public static void deleteOldFileWithReserve(String dirName, String reportName, HashSet<String> reserveNames){
        try {
            Calendar cal = Calendar.getInstance();
            int currentYear = cal.get(Calendar.YEAR);
            int currentMonth = cal.get(Calendar.MONTH);
            //项目申报提交时间为前一年12月和今年1月
            if(reportName.contains("项目申报") && currentMonth == 11){
                currentYear = currentYear + 1;
            }
            String year = String.valueOf(currentYear);
            //String year = "2022";
            String dir = ResearchEnrollDIR + year + "/" + reportName + "/" + dirName + "/";
            File uploadDir = new File(dir);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

            if (uploadDir.exists() && uploadDir.isDirectory() && uploadDir.listFiles()!=null) {
                File[] children = uploadDir.listFiles();
                //递归删除目录中文件，保留指定文件名
                for(File file : children){
                    if(file.isFile()&&!reserveNames.contains(file.getName())){
                        file.delete();
                    }
                }
                logger.info("delete file " + uploadDir.getAbsolutePath());
                //uploadDir.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //项目申报上传PPT时删除重复文件
    public static void deleteApplyPreOldFile(String dirName, String reportName){
        try {
            Calendar cal = Calendar.getInstance();
            int currentYear = cal.get(Calendar.YEAR);
            int currentMonth = cal.get(Calendar.MONTH);
            //项目申报提交时间为前一年12月和今年1月
            if(reportName.contains("项目申报") && currentMonth == 11){
                currentYear = currentYear + 1;
            }
            String year = String.valueOf(currentYear);
            String dir = ResearchEnrollDIR + year + "/" + reportName + "/" + dirName + "/" + "申报汇报" + "/";
            File uploadDir = new File(dir);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

            if (uploadDir.exists() && uploadDir.isDirectory() && uploadDir.listFiles()!=null) {
                File[] children = uploadDir.listFiles();
                //递归删除目录中文件
                for(File file : children){
                    if(file.isFile()){
                        file.delete();
                    }
                }
                logger.info("delete file " + uploadDir.getAbsolutePath());
                //uploadDir.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //创新大赛上传PPT时删除重复文件
    public static void deleteInnoPPTOldFile(String dirName, String reportName){
        try {
            Calendar date = Calendar.getInstance();
            String year = String.valueOf(date.get(Calendar.YEAR));
            String dir = ResearchEnrollDIR + year + "/" + reportName + "/" + dirName + "/" + "大赛汇报" + "/";
            File uploadDir = new File(dir);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

            if (uploadDir.exists() && uploadDir.isDirectory() && uploadDir.listFiles()!=null) {
                File[] children = uploadDir.listFiles();
                //递归删除目录中文件
                for(File file : children){
                    if(file.isFile()){
                        file.delete();
                    }
                }
                logger.info("delete file " + uploadDir.getAbsolutePath());
                //uploadDir.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //结题汇报上传PPT时删除重复上传文件
    public static void deleteFinalePreOldFile(String dirName, String reportName){
        try {
            Calendar date = Calendar.getInstance();
            String year = String.valueOf(date.get(Calendar.YEAR));
            String dir = ResearchEnrollDIR + year + "/" + reportName + "/" + dirName + "/" + "结题汇报" + "/";
            File uploadDir = new File(dir);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

            if (uploadDir.exists() && uploadDir.isDirectory() && uploadDir.listFiles()!=null) {
                File[] children = uploadDir.listFiles();
                //递归删除目录中文件
                for(File file : children){
                    if(file.isFile()){
                        file.delete();
                    }
                }
                logger.info("delete file " + uploadDir.getAbsolutePath());
                //uploadDir.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String uploadResearchFile(String fileName, String dirname, File userFile, String reportName) {
        try {
            Calendar cal = Calendar.getInstance();
            int currentYear = cal.get(Calendar.YEAR);
            int currentMonth = cal.get(Calendar.MONTH);
            //项目申报提交时间为前一年12月和今年1月
            if(reportName.contains("项目申报") && currentMonth == 11){
                currentYear = currentYear + 1;
            }
            String year = String.valueOf(currentYear);

            //String year = "2022";
            String dir = ResearchEnrollDIR + year + "/" + reportName + "/" + dirname + "/";
            File uploadDir = new File(dir);
            if (!isValidFileName(fileName)) {
                throw new IOException("upload project enroll dir not exist");
            }

            //目录不存在
            if (!uploadDir.exists()) {
//                logger.info("create upload research enroll dir" + uploadDir.getAbsolutePath());
//                throw new IOException("upload research enroll dir not exist");
                uploadDir.mkdir();
            }

            File uploadFile = new File(uploadDir, fileName);
            if (uploadFile.exists()) {
                logger.info("research enroll file is existed. delete first. " + uploadFile.getAbsolutePath());
                uploadFile.renameTo(new File(uploadFile.getAbsolutePath() + ".bak"));
            }
            FileUtils.copyFile(userFile, uploadFile);
            logger.info("upload research enroll successfully. " + uploadFile.getAbsolutePath());
            return WEBResearchEnrollFilesDIR + year + "/" + reportName + "/" + dirname + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    //上传项目申请现场汇报PPT
    public static String uploadResearchAppFile(String fileName, String dirname, File userFile, String reportName) {
        try {
            Calendar cal = Calendar.getInstance();
            int currentYear = cal.get(Calendar.YEAR);
            int currentMonth = cal.get(Calendar.MONTH);
            //项目申报提交时间为前一年12月和今年1月
            if(reportName.contains("项目申报") && currentMonth == 11){
                currentYear = currentYear + 1;
            }
            String year = String.valueOf(currentYear);
            String dir = ResearchEnrollDIR + year + "/" + reportName + "/" + dirname + "/" + "申报汇报" + "/";
            File uploadDir = new File(dir);
            if (!isValidFileName(fileName)) {
                throw new IOException("upload project enroll dir not exist");
            }

            //目录不存在
            if (!uploadDir.exists()) {
//                logger.info("create upload research enroll dir" + uploadDir.getAbsolutePath());
//                throw new IOException("upload research enroll dir not exist");
                uploadDir.mkdir();
            }

            File uploadFile = new File(uploadDir, fileName);
            if (uploadFile.exists()) {
                logger.info("research enroll file is existed. delete first. " + uploadFile.getAbsolutePath());
                uploadFile.renameTo(new File(uploadFile.getAbsolutePath() + ".bak"));
            }
            FileUtils.copyFile(userFile, uploadFile);
            logger.info("upload research enroll successfully. " + uploadFile.getAbsolutePath());
            return WEBResearchEnrollFilesDIR + year + "/" + reportName + "/" + dirname + "/" + "申报汇报/" +fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    //上传创新大赛现场汇报PPT
    public static String uploadResearchInnoPreFile(String fileName, String dirname, File userFile, String reportName) {
        try {
            Calendar date = Calendar.getInstance();
            String year = String.valueOf(date.get(Calendar.YEAR));
            String dir = ResearchEnrollDIR + year + "/" + reportName + "/" + dirname + "/" + "大赛汇报" + "/";
            File uploadDir = new File(dir);
            if (!isValidFileName(fileName)) {
                throw new IOException("upload project enroll dir not exist");
            }

            //目录不存在
            if (!uploadDir.exists()) {
//                logger.info("create upload research enroll dir" + uploadDir.getAbsolutePath());
//                throw new IOException("upload research enroll dir not exist");
                uploadDir.mkdir();
            }

            File uploadFile = new File(uploadDir, fileName);
            if (uploadFile.exists()) {
                logger.info("research enroll file is existed. delete first. " + uploadFile.getAbsolutePath());
                uploadFile.renameTo(new File(uploadFile.getAbsolutePath() + ".bak"));
            }
            FileUtils.copyFile(userFile, uploadFile);
            logger.info("upload research enroll successfully. " + uploadFile.getAbsolutePath());
            return WEBResearchEnrollFilesDIR + year + "/" + reportName + "/" + dirname + "/" + "大赛汇报/" +fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    //上传结题现场汇报PPT
    public static String uploadResearchPreFile(String fileName, String dirname, File userFile, String reportName) {
        try {
            Calendar date = Calendar.getInstance();
            String year = String.valueOf(date.get(Calendar.YEAR));
            String dir = ResearchEnrollDIR + year + "/" + reportName + "/" + dirname + "/" + "结题汇报" + "/";
            File uploadDir = new File(dir);
            if (!isValidFileName(fileName)) {
                throw new IOException("upload project enroll dir not exist");
            }

            //目录不存在
            if (!uploadDir.exists()) {
//                logger.info("create upload research enroll dir" + uploadDir.getAbsolutePath());
//                throw new IOException("upload research enroll dir not exist");
                uploadDir.mkdir();
            }

            File uploadFile = new File(uploadDir, fileName);
            if (uploadFile.exists()) {
                logger.info("research enroll file is existed. delete first. " + uploadFile.getAbsolutePath());
                uploadFile.renameTo(new File(uploadFile.getAbsolutePath() + ".bak"));
            }
            FileUtils.copyFile(userFile, uploadFile);
            logger.info("upload research enroll successfully. " + uploadFile.getAbsolutePath());
            return WEBResearchEnrollFilesDIR + year + "/" + reportName + "/" + dirname + "/" + "结题汇报/" +fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void deleteDirFile(String dirPath){
        try {

            File fileDir = new File(dirPath);
            if (fileDir.exists() && fileDir.isDirectory() && fileDir.listFiles()!=null) {
                File[] children = fileDir.listFiles();
                //递归删除目录中文件
                for(File file : children){
                    file.delete();
                }
                logger.info("delete file " + fileDir.getAbsolutePath());
                fileDir.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String compressFile(String userId, String username, String title, String reportName){
        String idUserName = userId + "-" + username;
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        //项目申报提交时间为前一年12月和今年1月
        if(reportName.contains("项目申报") && currentMonth == 11){
            currentYear = currentYear + 1;
        }
        String year = String.valueOf(currentYear);

        String sourceFilePath = ResearchEnrollDIR + year + "/" + reportName + "/" + idUserName + "/";
        String zipFilePath = ResearchEnrollDIR + year + "/" + "outputZipFile" + "/" + reportName + "/" + idUserName + "/";
//        用来调试代码
//        System.out.println("sourceFilePath:" + sourceFilePath);
//        System.out.println("zipFileDir:" + zipFilePath);
        boolean flag = false;
        File sourceFile = new File(sourceFilePath);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;

        if(sourceFile.exists() == false){
            System.out.println("带压缩的文件目录：" + sourceFilePath + "不存在**************************");
        }else{
            try{
                File zipFileDir = new File(zipFilePath);
                if(!zipFileDir.exists()){
                    Path currpath = Paths.get(zipFileDir.toString());
                    //  2023.10.20 change  创建目录   .mkDir() Windows 报错，故改称下面版本   [jiang000]
                    //  zipFileDir.mkdir();
                    try {
                        Files.createDirectories(currpath);
                        System.out.println("目录创建成功");
                    } catch (IOException e) {
                        System.err.println("目录创建失败: " + e.getMessage());
                    }
                }
                File zipFileTemp = new File(zipFilePath + title + ".zip");
                if(zipFileTemp.exists()){
                    zipFileTemp.delete();
                }
                File zipFile = new File(zipFilePath + title + ".zip");
                if(!zipFile.exists()){
                    try{
                        zipFile.createNewFile();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                File[] sourceFiles = sourceFile.listFiles();

                if(sourceFiles == null || sourceFiles.length < 1){
                    System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩**********");
                }else{
                    fos = new FileOutputStream(zipFile);
                    zos = new ZipOutputStream(new BufferedOutputStream(fos));

                    byte[] bufs = new byte[1024*10];
                    System.out.println("467");

                    for(int i = 0; i < sourceFiles.length; i++){
                        //创建ZIP实体，并添加进压缩包
                        ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                        zos.putNextEntry(zipEntry);
                        //读取待压缩的文件并写进压缩包里
                        if(sourceFiles[i].isDirectory()) continue;  // 跳过文件夹
                        fis = new FileInputStream(sourceFiles[i]);
                        bis = new BufferedInputStream(fis, 1024*10);
                        int read = 0;
                        while((read=bis.read(bufs, 0, 1024*10)) != -1){
                            zos.write(bufs, 0, read);
                        }
                    }
                    flag = true;
                }

            }catch (FileNotFoundException e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }catch (IOException e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }finally {
                //关闭流
                try{
                    if(bis != null) bis.close();
                    if(zos != null) zos.close();
                }catch (IOException e){
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
        return WEBResearchEnrollFilesDIR + year + "/" + "outputZipFile" + "/" + reportName + "/" + idUserName + "/" + title + ".zip";
    }

    //提交项目申报现场汇报时压缩文件
    public static String compressApplyPreFile(String userId, String username, String title,String reportName){
        String idUserName = userId + "-" + username;

        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        //项目申报提交时间为前一年12月和今年1月
        if(reportName.contains("项目申报") && currentMonth == 11){
            currentYear = currentYear + 1;
        }
        String year = String.valueOf(currentYear);

        String sourceFilePath = ResearchEnrollDIR + year + "/" + reportName + "/" + idUserName + "/";
        String zipFilePath = ResearchEnrollDIR + year + "/" + "outputZipFile" + "/" + reportName + "/" + idUserName + "/";

        boolean flag = false;
        File sourceFile = new File(sourceFilePath);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;

        if(sourceFile.exists() == false){
            System.out.println("带压缩的文件目录：" + sourceFilePath + "不存在**************************");
        }else{
            try{
                File zipFileDir = new File(zipFilePath);
                if(!zipFileDir.exists()){
                    zipFileDir.mkdir();
                }
                File zipFileTemp = new File(zipFilePath + title + ".zip");
                if(zipFileTemp.exists()){
                    zipFileTemp.delete();
                }
                File zipFile = new File(zipFilePath + title + ".zip");
                if(!zipFile.exists()){
                    try{
                        zipFile.createNewFile();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                File[] sourceFiles = sourceFile.listFiles();

                if(sourceFiles == null || sourceFiles.length < 1){
                    System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩**********");
                }else{
                    fos = new FileOutputStream(zipFile);
                    zos = new ZipOutputStream(new BufferedOutputStream(fos));

                    byte[] bufs = new byte[1024*10];

                    for(int i = 0; i < sourceFiles.length; i++){
                        if(sourceFiles[i].isDirectory()){
                            File[] finalPreFiles = sourceFiles[i].listFiles();
                            for(int j = 0; j < finalPreFiles.length; j++){
                                //创建ZIP实体，并添加进压缩包
                                ZipEntry zipEntry = new ZipEntry(finalPreFiles[j].getName());
                                zos.putNextEntry(zipEntry);
                                //读取待压缩的文件并写进压缩包里
                                fis = new FileInputStream(finalPreFiles[j]);
                                bis = new BufferedInputStream(fis, 1024*10);
                                int read = 0;
                                while((read=bis.read(bufs, 0, 1024*10)) != -1){
                                    zos.write(bufs, 0, read);
                                }
                            }
                        }else{
                            //创建ZIP实体，并添加进压缩包
                            ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                            zos.putNextEntry(zipEntry);
                            //读取待压缩的文件并写进压缩包里
                            fis = new FileInputStream(sourceFiles[i]);
                            bis = new BufferedInputStream(fis, 1024*10);
                            int read = 0;
                            while((read=bis.read(bufs, 0, 1024*10)) != -1){
                                zos.write(bufs, 0, read);
                            }
                        }

                    }
                    flag = true;
                }

            }catch (FileNotFoundException e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }catch (IOException e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }finally {
                //关闭流
                try{
                    if(bis != null) bis.close();
                    if(zos != null) zos.close();
                }catch (IOException e){
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }

        return WEBResearchEnrollFilesDIR + year + "/" + "outputZipFile" + "/" + reportName + "/" + idUserName + "/"+ title + ".zip";
    }

    //提交创新大赛现场汇报时压缩文件
    public static String compressInnoPreFile(String userId, String username, String title,String reportName){
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        String idUserName = userId + "-" + username;
        String sourceFilePath = ResearchEnrollDIR + year + "/" + reportName + "/" + idUserName + "/";
        String zipFilePath = ResearchEnrollDIR + year + "/" + "outputZipFile" + "/" + reportName + "/" + idUserName + "/";

        boolean flag = false;
        File sourceFile = new File(sourceFilePath);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;

        if(sourceFile.exists() == false){
            System.out.println("带压缩的文件目录：" + sourceFilePath + "不存在**************************");
        }else{
            try{
                File zipFileDir = new File(zipFilePath);
                if(!zipFileDir.exists()){
                    zipFileDir.mkdir();
                }
                File zipFileTemp = new File(zipFilePath + title + ".zip");
                if(zipFileTemp.exists()){
                    zipFileTemp.delete();
                }
                File zipFile = new File(zipFilePath + title + ".zip");
                if(!zipFile.exists()){
                    try{
                        zipFile.createNewFile();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                File[] sourceFiles = sourceFile.listFiles();

                if(sourceFiles == null || sourceFiles.length < 1){
                    System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩**********");
                }else{
                    fos = new FileOutputStream(zipFile);
                    zos = new ZipOutputStream(new BufferedOutputStream(fos));

                    byte[] bufs = new byte[1024*10];

                    for(int i = 0; i < sourceFiles.length; i++){
                        if(sourceFiles[i].isDirectory()){
                            File[] finalPreFiles = sourceFiles[i].listFiles();
                            for(int j = 0; j < finalPreFiles.length; j++){
                                //创建ZIP实体，并添加进压缩包
                                ZipEntry zipEntry = new ZipEntry(finalPreFiles[j].getName());
                                zos.putNextEntry(zipEntry);
                                //读取待压缩的文件并写进压缩包里
                                fis = new FileInputStream(finalPreFiles[j]);
                                bis = new BufferedInputStream(fis, 1024*10);
                                int read = 0;
                                while((read=bis.read(bufs, 0, 1024*10)) != -1){
                                    zos.write(bufs, 0, read);
                                }
                            }
                        }else{
                            //创建ZIP实体，并添加进压缩包
                            ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                            zos.putNextEntry(zipEntry);
                            //读取待压缩的文件并写进压缩包里
                            fis = new FileInputStream(sourceFiles[i]);
                            bis = new BufferedInputStream(fis, 1024*10);
                            int read = 0;
                            while((read=bis.read(bufs, 0, 1024*10)) != -1){
                                zos.write(bufs, 0, read);
                            }
                        }

                    }
                    flag = true;
                }

            }catch (FileNotFoundException e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }catch (IOException e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }finally {
                //关闭流
                try{
                    if(bis != null) bis.close();
                    if(zos != null) zos.close();
                }catch (IOException e){
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }

        return WEBResearchEnrollFilesDIR + year + "/" + "outputZipFile" + "/" + reportName + "/" + idUserName + "/"+ title + ".zip";
    }

    public static String compressFinalePreFile(String userId, String username, String year,String title,String reportName){
        String idUserName = userId + "-" + username;
        String sourceFilePath = ResearchEnrollDIR + year + "/" + reportName + "/" + idUserName + "/";
        String zipFilePath = ResearchEnrollDIR + year + "/" + "outputZipFile" + "/" + reportName + "/" + idUserName + "/";

        boolean flag = false;
        File sourceFile = new File(sourceFilePath);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;

        if(sourceFile.exists() == false){
            System.out.println("带压缩的文件目录：" + sourceFilePath + "不存在**************************");
        }else{
            try{
                File zipFileDir = new File(zipFilePath);
                if(!zipFileDir.exists()){
                    zipFileDir.mkdir();
                }
                File zipFileTemp = new File(zipFilePath + title + ".zip");
                if(zipFileTemp.exists()){
                    zipFileTemp.delete();
                }
                File zipFile = new File(zipFilePath + title + ".zip");
                if(!zipFile.exists()){
                    try{
                        zipFile.createNewFile();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                File[] sourceFiles = sourceFile.listFiles();

                if(sourceFiles == null || sourceFiles.length < 1){
                    System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩**********");
                }else{
                    fos = new FileOutputStream(zipFile);
                    zos = new ZipOutputStream(new BufferedOutputStream(fos));

                    byte[] bufs = new byte[1024*10];

                    for(int i = 0; i < sourceFiles.length; i++){
                        if(sourceFiles[i].isDirectory()){
                            File[] finalPreFiles = sourceFiles[i].listFiles();
                            for(int j = 0; j < finalPreFiles.length; j++){
                                //创建ZIP实体，并添加进压缩包
                                ZipEntry zipEntry = new ZipEntry(finalPreFiles[j].getName());
                                zos.putNextEntry(zipEntry);
                                //读取待压缩的文件并写进压缩包里
                                fis = new FileInputStream(finalPreFiles[j]);
                                bis = new BufferedInputStream(fis, 1024*10);
                                int read = 0;
                                while((read=bis.read(bufs, 0, 1024*10)) != -1){
                                    zos.write(bufs, 0, read);
                                }
                            }
                        }else{
                            //创建ZIP实体，并添加进压缩包
                            ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                            zos.putNextEntry(zipEntry);
                            //读取待压缩的文件并写进压缩包里
                            fis = new FileInputStream(sourceFiles[i]);
                            bis = new BufferedInputStream(fis, 1024*10);
                            int read = 0;
                            while((read=bis.read(bufs, 0, 1024*10)) != -1){
                                zos.write(bufs, 0, read);
                            }
                        }

                    }
                    flag = true;
                }

            }catch (FileNotFoundException e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }catch (IOException e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }finally {
                //关闭流
                try{
                    if(bis != null) bis.close();
                    if(zos != null) zos.close();
                }catch (IOException e){
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }

        return WEBResearchEnrollFilesDIR + year + "/" + "outputZipFile" + "/" + reportName + "/" + idUserName + "/" + title + ".zip";
    }

    public static String uploadInnovationFile(String fileName, String dirname, File userFile) {
        try {
            Calendar date = Calendar.getInstance();
            String year = String.valueOf(date.get(Calendar.YEAR));
            String dir = ResearchEnrollDIR + year + "/" + "创新大赛/" + dirname + "/";
            File uploadDir = new File(dir);
            if (!isValidFileName(fileName)) {
                throw new IOException("upload project enroll dir not exist");
            }


            //目录不存在
            if (!uploadDir.exists()) {
//                logger.info("create upload research enroll dir" + uploadDir.getAbsolutePath());
//                throw new IOException("upload research enroll dir not exist");
                uploadDir.mkdir();
            }

            File uploadFile = new File(uploadDir, fileName);
            if (uploadFile.exists()) {
                logger.info("research enroll file is existed. delete first. " + uploadFile.getAbsolutePath());
                uploadFile.renameTo(new File(uploadFile.getAbsolutePath() + ".bak"));
            }
            FileUtils.copyFile(userFile, uploadFile);
            logger.info("upload research enroll successfully. " + uploadFile.getAbsolutePath());
            return WEBResearchEnrollFilesDIR + year + "/" + "创新大赛/" + dirname + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String uploadFile(String fileName, File userFile) {
        try {
            File uploadDir = new File(FilesDIR);

            if (!isValidFileName(fileName)) {
                throw new IOException("upload project enroll dir not exist");
            }

            //目录不存在
            if (!uploadDir.exists()) {
                logger.info("create upload file dir" + uploadDir.getAbsolutePath());
                throw new IOException("upload dir not exist");
            }

            File uploadFile = new File(uploadDir, fileName);
            if (uploadFile.exists()) {
                logger.info("file is existed. delete first. " + uploadFile.getAbsolutePath());
                uploadFile.delete();
            }
            FileUtils.copyFile(userFile, uploadFile);
            logger.info("upload file successfully. " + uploadFile.getAbsolutePath());
            return WEBFilesDIR + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            logger.info("delete file " + file.getAbsolutePath());
            file.delete();
        }
    }

    public static boolean isValidFileName(String fileName) {
        if (fileName == null || fileName.length() > 255 || fileName == "") {
            return false;
        }
        return fileName.matches("[^\\s\\\\/:\\*\\?\\\"<>\\|](\\x20|[^\\s\\\\/:\\*\\?\\\"<>\\|])*[^\\s\\\\/:\\*\\?\\\"<>\\|\\.]$");
    }

    public static String fetchFileByNamePath(String filePath, String fileName) {
        String virtualPath = ConfUtil.getFileVirtualPath();
        if(fileName.length()==0) return "";
        return virtualPath+filePath+fileName;
    }

    public static String getCompletePath(String[] fileNameList, String dirname, String reportName){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        String year = String.valueOf(currentYear);

        String path = "";
        for (String fileName: fileNameList){
            if (fileName == null || fileName.equals(""))
                continue;
//            logger.info("filePath: " + (WEBResearchEnrollFilesDIR + year + "/" + reportName + "/" + dirname + "/" + fileName + ";"));
            path += (WEBResearchEnrollFilesDIR + year + "/" + reportName + "/" + dirname + "/" + fileName + ";");
        }
        return path;
    }
}
