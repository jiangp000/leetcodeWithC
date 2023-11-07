package util;

/**
 * Created by jinggu on 21/7/14.
 */


import domain.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import org.apache.struts2.ServletActionContext;
import service.ResearchEnrollService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * 文件操作
 */
public class XLSXUtil {

    /**
     * 生成为XLS文件
     *
     * @param exportData 源数据List
     * @param fields     源数据的字段 以","为分隔符
     * @param outPutPath 文件路径
     * @param fileName   文件名称
     * @param className  exportData的类名称
     * @return
     */
    @SuppressWarnings("rawtypes")
    private static Logger logger = LogManager.getLogger(XLSXUtil.class);
    private static ResearchEnrollService researchEnrollService = new ResearchEnrollService();

    // 导出含有多个sheet的xls文件
    public static void createSelfXLSFileWithSheets(ArrayList<ArrayList<SelfResearchScore>> exportData,
                                                   String fields, String outPutPath,
                                                   String fileName, String className) throws IOException {
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
        // 共有多少个阶段  论文、结题 等等
        int num1 = exportData.size();
        for(int j = 0 ; j < num1 ; j++){

            HSSFFont font = wb.createFont();
            font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
            HSSFCellStyle style = wb.createCellStyle();
            style.setFont(font);

            HSSFFont font2 = wb.createFont();
            font2.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
            HSSFCellStyle style2 = wb.createCellStyle();
            style2.setFont(font2);

            ArrayList<SelfResearchScore> temp = exportData.get(j);
            HSSFSheet sheet1 = wb.createSheet(temp.get(0).getResearchType());
            HSSFRow row1 = sheet1.createRow(0);
            String[] sheetTitle = fields.split(",");

            for (int i = 0 ; i < sheetTitle.length; i++) {
                Cell cell = row1.createCell(i);
                cell.setCellValue(sheetTitle[i]);
                if(i == 9 || i == 14)
                    cell.setCellStyle(style);
                if(i == 15)
                    cell.setCellStyle(style2);
            }

            int num2 = temp.size();
            for(int i = 0;i < num2;++i){
                row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
                SelfResearchScore selfResearchScore = temp.get(i);
                row1.createCell(0).setCellValue(selfResearchScore.getProjectNo());
                row1.createCell(1).setCellValue(selfResearchScore.getUserId());
                row1.createCell(2).setCellValue(selfResearchScore.getUsername());
                row1.createCell(3).setCellValue(selfResearchScore.getTitle());
                row1.createCell(4).setCellValue(selfResearchScore.getVideoScore1());
                row1.createCell(5).setCellValue(selfResearchScore.getVideoScore2());
                row1.createCell(6).setCellValue(selfResearchScore.getVideoScore3());
                row1.createCell(7).setCellValue(selfResearchScore.getVideoScore4());
                row1.createCell(8).setCellValue(selfResearchScore.getVideoScore5());
                row1.createCell(9).setCellValue(selfResearchScore.getVideoScore());
                row1.createCell(10).setCellValue(selfResearchScore.getDocumentScore1());
                row1.createCell(11).setCellValue(selfResearchScore.getDocumentScore2());
                row1.createCell(12).setCellValue(selfResearchScore.getDocumentScore3());
                row1.createCell(13).setCellValue(selfResearchScore.getDocumentScore4());
                row1.createCell(14).setCellValue(selfResearchScore.getDocumentScore());
                row1.createCell(15).setCellValue(selfResearchScore.getScore());
                row1.createCell(16).setCellValue(selfResearchScore.getAssessment());
                HSSFCell cell1 = sheet1.getRow(i+1).getCell(9);
                cell1.setCellStyle(style);
                HSSFCell cell2 = sheet1.getRow(i+1).getCell(14);
                cell2.setCellStyle(style);
                HSSFCell cell3 = sheet1.getRow(i+1).getCell(15);
                cell3.setCellStyle(style2);
            }
        }

        // 设置文件名
        String title = fileName + ".xls";
        title = new String(title.getBytes(), "ISO8859-1");

        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream os = response.getOutputStream();



        response.setHeader("Content-disposition",
                "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1") + ".xls");
        // 定义输出类型
        response.setContentType("application/msexcel");
        wb.write(os);
        os.close();
    }




    // 导出含有多个sheet的xls文件
    public static void createXLSFileWithSheets(ArrayList<ArrayList<DownloadAllScore>> exportData, String fields, String fileName) throws IOException {
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
        // 共有多少个评委
        int num1 = exportData.size();
        for(int j = 0 ; j < num1 ; j++){

            HSSFFont font = wb.createFont();
            font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
            HSSFCellStyle style = wb.createCellStyle();
            style.setFont(font);

            HSSFFont font2 = wb.createFont();
            font2.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
            HSSFCellStyle style2 = wb.createCellStyle();
            style2.setFont(font2);

            ArrayList<DownloadAllScore> temp = exportData.get(j);
            HSSFSheet sheet1 = wb.createSheet(temp.get(0).getJudgeId() + temp.get(0).getJudgename());
            HSSFRow row1 = sheet1.createRow(0);
            String[] sheetTitle = fields.split(",");
            for (int i = 0 ; i < sheetTitle.length; i++) {
                Cell cell = row1.createCell(i);
                cell.setCellValue(sheetTitle[i]);
                if(i == 11 || i == 16)
                    cell.setCellStyle(style);
                if(i == 17)
                    cell.setCellStyle(style2);
            }

            int num2 = temp.size();
            for(int i = 0;i < num2;++i){
                row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
                DownloadAllScore downloadAllScore = temp.get(i);
                row1.createCell(0).setCellValue(downloadAllScore.getJudgeId());
                row1.createCell(1).setCellValue(downloadAllScore.getJudgename());
                row1.createCell(2).setCellValue(downloadAllScore.getGroup());
                row1.createCell(3).setCellValue(downloadAllScore.getUserId());
                row1.createCell(4).setCellValue(downloadAllScore.getUsername());
                row1.createCell(5).setCellValue(downloadAllScore.getTitle());
                row1.createCell(6).setCellValue(downloadAllScore.getVideoScore1());
                row1.createCell(7).setCellValue(downloadAllScore.getVideoScore2());
                row1.createCell(8).setCellValue(downloadAllScore.getVideoScore3());
                row1.createCell(9).setCellValue(downloadAllScore.getVideoScore4());
                row1.createCell(10).setCellValue(downloadAllScore.getVideoScore5());
                row1.createCell(11).setCellValue(downloadAllScore.getVideoScore());
                row1.createCell(12).setCellValue(downloadAllScore.getDocumentScore1());
                row1.createCell(13).setCellValue(downloadAllScore.getDocumentScore2());
                row1.createCell(14).setCellValue(downloadAllScore.getDocumentScore3());
                row1.createCell(15).setCellValue(downloadAllScore.getDocumentScore4());
                row1.createCell(16).setCellValue(downloadAllScore.getDocumentScore());
                row1.createCell(17).setCellValue(downloadAllScore.getScore());
                row1.createCell(18).setCellValue(downloadAllScore.getAssessment());
                HSSFCell cell1 = sheet1.getRow(i+1).getCell(11);
                cell1.setCellStyle(style);
                HSSFCell cell2 = sheet1.getRow(i+1).getCell(16);
                cell2.setCellStyle(style);
                HSSFCell cell3 = sheet1.getRow(i+1).getCell(17);
                cell3.setCellStyle(style2);
            }
        }
        // 设置文件名
        String title = fileName + ".xls";
        title = new String(title.getBytes(), "ISO8859-1");

        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream os = response.getOutputStream();

        response.setHeader("Content-disposition",
                "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1") + ".xls");
        // 定义输出类型
        response.setContentType("application/msexcel");
        wb.write(os);
        os.close();
    }

    // 导出含有多个sheet的xls文件，申报平均分+每个评委小分
    public static void createApplyScoreXLSFileWithSheets(ArrayList<ArrayList<DownloadApplyAllScore>> exportData,
                                                         ArrayList<DownloadApplyResultScore> applyResultScores,
                                                         String fieldApply, String fieldResult, String fileName,
                                                         String isPre, String userType) throws IOException {
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);

        HSSFFont font2 = wb.createFont();
        font2.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);

        String firstSheetName = "";
        if(isPre.equals("apply")){
            firstSheetName = year + "项目申报网评分数";
        }else{
            firstSheetName = year + "项目申报全部分数";
        }
        HSSFSheet sheet1 = wb.createSheet(firstSheetName);
        HSSFRow row1 = sheet1.createRow(0);
        String[] sheetTitle = fieldResult.split(",");
        for (int i = 0 ; i < sheetTitle.length; i++) {
            Cell cell = row1.createCell(i);
            cell.setCellValue(sheetTitle[i]);
            if(i == 27)
                cell.setCellStyle(style2);
        }

        int numRes = applyResultScores.size();
        for(int i = 0;i < numRes;++i){
            row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
            DownloadApplyResultScore downloadApplyResultScore = applyResultScores.get(i);
            row1.createCell(0).setCellValue(downloadApplyResultScore.getApplyId());
            row1.createCell(1).setCellValue(downloadApplyResultScore.getUserId());
            row1.createCell(2).setCellValue(downloadApplyResultScore.getUserName());
            row1.createCell(3).setCellValue(downloadApplyResultScore.getDeptName());
            row1.createCell(4).setCellValue(downloadApplyResultScore.getTitle());
            row1.createCell(5).setCellValue(downloadApplyResultScore.getIsPass());
            row1.createCell(6).setCellValue(downloadApplyResultScore.getApplyPreComment());
            row1.createCell(7).setCellValue(downloadApplyResultScore.getProjectApplySort());
            row1.createCell(8).setCellValue(downloadApplyResultScore.getProjectEthicSort());
            row1.createCell(9).setCellValue(downloadApplyResultScore.getCaseSort());
            row1.createCell(10).setCellValue(downloadApplyResultScore.getTeamMember());
            row1.createCell(11).setCellValue(downloadApplyResultScore.getTeamNumber());
            row1.createCell(12).setCellValue(downloadApplyResultScore.getUserCv());
            row1.createCell(13).setCellValue(downloadApplyResultScore.getJudgeScore1());
            row1.createCell(14).setCellValue(downloadApplyResultScore.getJudgeScore2());
            row1.createCell(15).setCellValue(downloadApplyResultScore.getJudgeScore3());
            row1.createCell(16).setCellValue(downloadApplyResultScore.getJudgeScore4());
            row1.createCell(17).setCellValue(downloadApplyResultScore.getJudgeScore5());
            row1.createCell(18).setCellValue(downloadApplyResultScore.getJudgeScore6());
            row1.createCell(19).setCellValue(downloadApplyResultScore.getJudgeScore7());
            row1.createCell(20).setCellValue(downloadApplyResultScore.getJudgeScore8());
            row1.createCell(21).setCellValue(downloadApplyResultScore.getJudgeScore9());
            row1.createCell(22).setCellValue(downloadApplyResultScore.getJudgeScore10());
            row1.createCell(23).setCellValue(downloadApplyResultScore.getJudgeScore11());
            row1.createCell(24).setCellValue(downloadApplyResultScore.getOriginalScoreSum());
            row1.createCell(25).setCellValue(downloadApplyResultScore.getOriginalScoreAverage());
            row1.createCell(26).setCellValue(downloadApplyResultScore.getWeightedScoreSum());
            row1.createCell(27).setCellValue(downloadApplyResultScore.getWeightedScoreAverage());
            row1.createCell(28).setCellValue(i+1);
            row1.createCell(29).setCellValue(downloadApplyResultScore.getTimes());
            Double[] judgeScores = new Double[12];
            judgeScores[0] = downloadApplyResultScore.getJudgeScore1();
            judgeScores[1] = downloadApplyResultScore.getJudgeScore2();
            judgeScores[2] = downloadApplyResultScore.getJudgeScore3();
            judgeScores[3] = downloadApplyResultScore.getJudgeScore4();
            judgeScores[4] = downloadApplyResultScore.getJudgeScore5();
            judgeScores[5] = downloadApplyResultScore.getJudgeScore6();
            judgeScores[6] = downloadApplyResultScore.getJudgeScore7();
            judgeScores[7] = downloadApplyResultScore.getJudgeScore8();
            judgeScores[8] = downloadApplyResultScore.getJudgeScore9();
            judgeScores[9] = downloadApplyResultScore.getJudgeScore10();
            judgeScores[10] = downloadApplyResultScore.getJudgeScore11();
            double minScore = downloadApplyResultScore.getJudgeScore1();
            double maxScore = downloadApplyResultScore.getJudgeScore1();
            int minScoreIndex = 0;
            int maxScoreIndex = 0;
            double temp = 0;
            for(int m = 0; m < 11; m++){
                if(judgeScores[m] < minScore){
                    minScore = judgeScores[m];
                    minScoreIndex = m;
                }
                if(judgeScores[m] > maxScore){
                    maxScore = judgeScores[m];
                    maxScoreIndex = m;
                }
            }
            HSSFCell cell3 = sheet1.getRow(i+1).getCell(minScoreIndex + 13);
            HSSFCell cell4 = sheet1.getRow(i+1).getCell(maxScoreIndex + 13);
            HSSFCell cell5 = sheet1.getRow(i+1).getCell(27);
            cell3.setCellStyle(style);
            cell4.setCellStyle(style2);
            cell5.setCellStyle(style2);
        }


        // 共有多少个评委
        int num1 = exportData.size();
        int p = 1;
        for(int j = 0 ; j < num1 ; j++){
            ArrayList<DownloadApplyAllScore> temp = exportData.get(j);
            //sheet1 = wb.createSheet(temp.get(0).getJudgeId() + temp.get(0).getJudgeName());
            if(userType.equals("admin")){
                sheet1 = wb.createSheet(temp.get(0).getJudgeId() + temp.get(0).getJudgeName());
            }else{
                sheet1 = wb.createSheet("评委" + p);
                p++;
            }
            row1 = sheet1.createRow(0);
            sheetTitle = fieldApply.split(",");
            for (int i = 0 ; i < sheetTitle.length; i++) {
                Cell cell = row1.createCell(i);
                cell.setCellValue(sheetTitle[i]);
                if(i == 20)
                    cell.setCellStyle(style);
                if(i == 22)
                    cell.setCellStyle(style2);
            }

            int num2 = temp.size();
            for(int i = 0;i < num2;++i){
                row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
                DownloadApplyAllScore downloadApplyAllScore = temp.get(i);
                //row1.createCell(0).setCellValue(downloadFinalAllScore.getJudgeId());
                //row1.createCell(1).setCellValue(downloadFinalAllScore.getJudgeName());
                row1.createCell(0).setCellValue(downloadApplyAllScore.getApplyId());
                row1.createCell(1).setCellValue(downloadApplyAllScore.getUserId());
                row1.createCell(2).setCellValue(downloadApplyAllScore.getUserName());
                row1.createCell(3).setCellValue(downloadApplyAllScore.getDeptName());
                row1.createCell(4).setCellValue(downloadApplyAllScore.getTitle());
                row1.createCell(5).setCellValue(downloadApplyAllScore.getIsPass());
                row1.createCell(6).setCellValue(downloadApplyAllScore.getApplyPreComment());
                row1.createCell(7).setCellValue(downloadApplyAllScore.getProjectApplySort());
                row1.createCell(8).setCellValue(downloadApplyAllScore.getProjectEthicSort());
                row1.createCell(9).setCellValue(downloadApplyAllScore.getCaseSort());
                row1.createCell(10).setCellValue(downloadApplyAllScore.getTeamMember());
                row1.createCell(11).setCellValue(downloadApplyAllScore.getUserCv());
                row1.createCell(12).setCellValue(downloadApplyAllScore.getJudgeScore1());
                row1.createCell(13).setCellValue(downloadApplyAllScore.getJudgeScore2());
                row1.createCell(14).setCellValue(downloadApplyAllScore.getJudgeScore3());
                row1.createCell(15).setCellValue(downloadApplyAllScore.getJudgeScore4());
                row1.createCell(16).setCellValue(downloadApplyAllScore.getJudgeScore5());
                row1.createCell(17).setCellValue(downloadApplyAllScore.getJudgeScore6());
                row1.createCell(18).setCellValue(downloadApplyAllScore.getJudgeScore7());
                row1.createCell(19).setCellValue(downloadApplyAllScore.getJudgeScore8());
                row1.createCell(20).setCellValue(downloadApplyAllScore.getPreJudgeScore());
                row1.createCell(21).setCellValue(downloadApplyAllScore.getJudgeScore10());
                row1.createCell(22).setCellValue(downloadApplyAllScore.getScore());
                row1.createCell(23).setCellValue(downloadApplyAllScore.getAssessment());
                HSSFCell cell3 = sheet1.getRow(i+1).getCell(20);
                HSSFCell cell4 = sheet1.getRow(i+1).getCell(22);
                cell3.setCellStyle(style);
                cell4.setCellStyle(style2);
            }
        }
        // 设置文件名
        String title = fileName + ".xls";
        title = new String(title.getBytes(), "ISO8859-1");

        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream os = response.getOutputStream();

        response.setHeader("Content-disposition",
                "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1") + ".xls");
        // 定义输出类型
        response.setContentType("application/msexcel");
        wb.write(os);
        os.close();
    }

    // 导出含有多个sheet的xls文件，创新大赛  网评阶段  平均分+每个评委小分
    public static void createInnoPreScoreXLSFileWithSheets(ArrayList<ArrayList<DownloadInnoAllScore>> exportData,
                                                        ArrayList<DownloadInnoResultScore> innoResultScores,
                                                        String innoPreFields, String innoPPTFields,
                                                        String fieldResult, String fileName,
                                                        String userType) throws IOException {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);

        HSSFFont font2 = wb.createFont();
        font2.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);

        HSSFSheet sheet1 = wb.createSheet(year+"创新大赛网评分数");
        HSSFRow row1 = sheet1.createRow(0);
        String[] sheetTitle = fieldResult.split(",");
        for (int i = 0 ; i < sheetTitle.length; i++) {
            Cell cell = row1.createCell(i);
            cell.setCellValue(sheetTitle[i]);

            if(i == 28)
                cell.setCellStyle(style);
        }

        int numRes = innoResultScores.size();
        int ws = 1, lg = 1, yx = 1, zh = 1;
        for(int i = 0;i < numRes;++i){
            row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
            DownloadInnoResultScore downloadInnoResultScore = innoResultScores.get(i);
            row1.createCell(0).setCellValue(downloadInnoResultScore.getProjectNo());
            row1.createCell(1).setCellValue(downloadInnoResultScore.getProjectApplySort());
            row1.createCell(2).setCellValue(downloadInnoResultScore.getCaseSort());
            row1.createCell(3).setCellValue(downloadInnoResultScore.getProjectSort());
            row1.createCell(4).setCellValue(downloadInnoResultScore.getUserId());
            row1.createCell(5).setCellValue(downloadInnoResultScore.getUserName());
            row1.createCell(6).setCellValue(downloadInnoResultScore.getTitle());
            row1.createCell(7).setCellValue(downloadInnoResultScore.getDepartment());
            row1.createCell(8).setCellValue(downloadInnoResultScore.getTeamMember());
            row1.createCell(9).setCellValue(downloadInnoResultScore.getTeamNumber());
            row1.createCell(10).setCellValue(downloadInnoResultScore.getUserCv());
            row1.createCell(11).setCellValue(downloadInnoResultScore.getApplyPreComment());

            row1.createCell(12).setCellValue(downloadInnoResultScore.getPreJudgeScore1());
            row1.createCell(13).setCellValue(downloadInnoResultScore.getPreJudgeScore2());
            row1.createCell(14).setCellValue(downloadInnoResultScore.getPreJudgeScore3());
            row1.createCell(15).setCellValue(downloadInnoResultScore.getPreJudgeScore4());
            row1.createCell(16).setCellValue(downloadInnoResultScore.getPreJudgeScore5());
            row1.createCell(17).setCellValue(downloadInnoResultScore.getPreJudgeScore6());
            row1.createCell(18).setCellValue(downloadInnoResultScore.getPreJudgeScore7());
            row1.createCell(19).setCellValue(downloadInnoResultScore.getPreJudgeScore8());
            row1.createCell(20).setCellValue(downloadInnoResultScore.getPreJudgeScore9());
            row1.createCell(21).setCellValue(downloadInnoResultScore.getPreJudgeScore10());
            row1.createCell(22).setCellValue(downloadInnoResultScore.getPreJudgeScore11());
            row1.createCell(23).setCellValue(downloadInnoResultScore.getPreJudgeScore12());
            row1.createCell(24).setCellValue(downloadInnoResultScore.getPreJudgeScore13());
            row1.createCell(25).setCellValue(downloadInnoResultScore.getPreOriginalScoreSum());
            row1.createCell(26).setCellValue(downloadInnoResultScore.getPreOriginalScoreAverage());
            row1.createCell(27).setCellValue(downloadInnoResultScore.getPreWeightedScoreSum());
            row1.createCell(28).setCellValue(downloadInnoResultScore.getPreWeightedScoreAverage());
            row1.createCell(29).setCellValue(downloadInnoResultScore.getPreTimes());

            String applyId = downloadInnoResultScore.getProjectNo().substring(4,6);
            if(applyId.equals("WS")){
                row1.createCell(30).setCellValue(ws);
                ws++;
            }else if(applyId.equals("LG")){
                row1.createCell(30).setCellValue(lg);
                lg++;
            }else if(applyId.equals("YX")){
                row1.createCell(30).setCellValue(yx);
                yx++;
            }else if(applyId.equals("ZH")){
                row1.createCell(30).setCellValue(zh);
                zh++;
            }

            Double[] preJudgeScores = new Double[13];
            preJudgeScores[0] = downloadInnoResultScore.getPreJudgeScore1();
            preJudgeScores[1] = downloadInnoResultScore.getPreJudgeScore2();
            preJudgeScores[2] = downloadInnoResultScore.getPreJudgeScore3();
            preJudgeScores[3] = downloadInnoResultScore.getPreJudgeScore4();
            preJudgeScores[4] = downloadInnoResultScore.getPreJudgeScore5();
            preJudgeScores[5] = downloadInnoResultScore.getPreJudgeScore6();
            preJudgeScores[6] = downloadInnoResultScore.getPreJudgeScore7();
            preJudgeScores[7] = downloadInnoResultScore.getPreJudgeScore8();
            preJudgeScores[8] = downloadInnoResultScore.getPreJudgeScore9();
            preJudgeScores[9] = downloadInnoResultScore.getPreJudgeScore10();
            preJudgeScores[10] = downloadInnoResultScore.getPreJudgeScore11();
            preJudgeScores[11] = downloadInnoResultScore.getPreJudgeScore12();
            preJudgeScores[12] = downloadInnoResultScore.getPreJudgeScore13();
            double minPreScore = downloadInnoResultScore.getPreJudgeScore1();
            double maxPreScore = downloadInnoResultScore.getPreJudgeScore1();
            int minPreScoreIndex = 0;
            int maxPreScoreIndex = 0;
            for(int m = 0; m < 13; m++){
                if(preJudgeScores[m] < minPreScore){
                    minPreScore = preJudgeScores[m];
                    minPreScoreIndex = m;
                }
                if(preJudgeScores[m] > maxPreScore){
                    maxPreScore = preJudgeScores[m];
                    maxPreScoreIndex = m;
                }
            }

            HSSFCell cell3 = sheet1.getRow(i+1).getCell(minPreScoreIndex + 12);
            HSSFCell cell4 = sheet1.getRow(i+1).getCell(maxPreScoreIndex + 12);
            HSSFCell cell5 = sheet1.getRow(i+1).getCell(28);

            cell3.setCellStyle(style);
            cell4.setCellStyle(style2);
            cell5.setCellStyle(style);

        }

        // 共有多少个评委

        int num1 = exportData.size();
        int p = 1, q = 1;
        for(int j = 0 ; j < num1 ; j++){

            /*HSSFFont font = wb.createFont();
            font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
            HSSFCellStyle style = wb.createCellStyle();
            style.setFont(font);

            HSSFFont font2 = wb.createFont();
            font2.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
            HSSFCellStyle style2 = wb.createCellStyle();
            style2.setFont(font2);*/
            ArrayList<DownloadInnoAllScore> temp = exportData.get(j);

            //sheet1 = wb.createSheet(temp.get(0).getJudgeId() + temp.get(0).getJudgeName());
            if(temp.get(0).getScorePhase().equals("网评评委")){
                if(userType.equals("admin")){
                    sheet1 = wb.createSheet(temp.get(0).getJudgeId() + temp.get(0).getJudgeName() + "网评");
                }else{
                    sheet1 = wb.createSheet("网评评委" + p);
                    p++;
                }
                row1 = sheet1.createRow(0);
                sheetTitle = innoPreFields.split(",");
                for (int i = 0 ; i < sheetTitle.length; i++) {
                    Cell cell = row1.createCell(i);
                    cell.setCellValue(sheetTitle[i]);
                    if(i == 14)
                        cell.setCellStyle(style);
                    if(i == 19)
                        cell.setCellStyle(style);
                    if(i == 20)
                        cell.setCellStyle(style2);
                }

                int num2 = temp.size();
                for(int i = 0;i < num2;++i){
                    row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
                    DownloadInnoAllScore downloadInnoAllScore = temp.get(i);
                    //row1.createCell(0).setCellValue(downloadInnoAllScore.getJudgeId());
                    //row1.createCell(1).setCellValue(downloadInnoAllScore.getJudgeName());
                    row1.createCell(0).setCellValue(downloadInnoAllScore.getProjectNo());
                    row1.createCell(1).setCellValue(downloadInnoAllScore.getProjectApplySort());
                    row1.createCell(2).setCellValue(downloadInnoAllScore.getCaseSort());
                    row1.createCell(3).setCellValue(downloadInnoAllScore.getProjectSort());
                    row1.createCell(4).setCellValue(downloadInnoAllScore.getUserId());
                    row1.createCell(5).setCellValue(downloadInnoAllScore.getUsername());
                    row1.createCell(6).setCellValue(downloadInnoAllScore.getTitle());
                    row1.createCell(7).setCellValue(downloadInnoAllScore.getDepartment());
                    row1.createCell(8).setCellValue(downloadInnoAllScore.getTeamMember());
                    row1.createCell(9).setCellValue(downloadInnoAllScore.getJudgeScore1());
                    row1.createCell(10).setCellValue(downloadInnoAllScore.getJudgeScore2());
                    row1.createCell(11).setCellValue(downloadInnoAllScore.getJudgeScore3());
                    row1.createCell(12).setCellValue(downloadInnoAllScore.getJudgeScore4());
                    row1.createCell(13).setCellValue(downloadInnoAllScore.getJudgeScore5());
                    row1.createCell(14).setCellValue(downloadInnoAllScore.getVideoScore());
                    row1.createCell(15).setCellValue(downloadInnoAllScore.getJudgeScore6());
                    row1.createCell(16).setCellValue(downloadInnoAllScore.getJudgeScore7());
                    row1.createCell(17).setCellValue(downloadInnoAllScore.getJudgeScore8());
                    row1.createCell(18).setCellValue(downloadInnoAllScore.getJudgeScore9());
                    row1.createCell(19).setCellValue(downloadInnoAllScore.getDocumentScore());
                    row1.createCell(20).setCellValue(downloadInnoAllScore.getPreScore());
                    row1.createCell(21).setCellValue(downloadInnoAllScore.getAssessment());
                    HSSFCell cell2 = sheet1.getRow(i+1).getCell(14);
                    HSSFCell cell3 = sheet1.getRow(i+1).getCell(19);
                    HSSFCell cell4 = sheet1.getRow(i+1).getCell(20);
                    cell2.setCellStyle(style);
                    cell3.setCellStyle(style);
                    cell4.setCellStyle(style2);
                }
            }

        }



        // 设置文件名
        String title = fileName + ".xls";

        title = new String(title.getBytes(), "ISO8859-1");

        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream os = response.getOutputStream();

        response.setHeader("Content-disposition",
                "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1") + ".xls");
        // 定义输出类型
        response.setContentType("application/msexcel");
        wb.write(os);
        os.close();
    }

    // 导出含有多个sheet的xls文件，创新大赛阶段 网评 + 现评  平均分+每个评委小分
    public static void createInnoScoreXLSFileWithSheets(ArrayList<ArrayList<DownloadInnoAllScore>> exportData,
                                                         ArrayList<DownloadInnoResultScore> innoResultScores,
                                                         String innoPreFields, String innoPPTFields,
                                                        String fieldResult, String fileName,
                                                        String userType) throws IOException {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);

        HSSFFont font2 = wb.createFont();
        font2.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);

        HSSFSheet sheet1 = wb.createSheet(year+"创新大赛分数表");
        HSSFRow row1 = sheet1.createRow(0);
        String[] sheetTitle = fieldResult.split(",");
        for (int i = 0 ; i < sheetTitle.length; i++) {
            Cell cell = row1.createCell(i);
            cell.setCellValue(sheetTitle[i]);

            if(i == 28)
                cell.setCellStyle(style);
            if(i == 40)
                cell.setCellStyle(style);
            if(i == 45)
                cell.setCellStyle(style2);
        }

        int numRes = innoResultScores.size();
        int ws = 1, lg = 1, yx = 1, zh = 1;
        for(int i = 0;i < numRes;++i){
            row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
            DownloadInnoResultScore downloadInnoResultScore = innoResultScores.get(i);
            row1.createCell(0).setCellValue(downloadInnoResultScore.getProjectNo());
            row1.createCell(1).setCellValue(downloadInnoResultScore.getProjectApplySort());
            row1.createCell(2).setCellValue(downloadInnoResultScore.getCaseSort());
            row1.createCell(3).setCellValue(downloadInnoResultScore.getProjectSort());
            row1.createCell(4).setCellValue(downloadInnoResultScore.getUserId());
            row1.createCell(5).setCellValue(downloadInnoResultScore.getUserName());
            row1.createCell(6).setCellValue(downloadInnoResultScore.getTitle());
            row1.createCell(7).setCellValue(downloadInnoResultScore.getDepartment());
            row1.createCell(8).setCellValue(downloadInnoResultScore.getTeamMember());
            row1.createCell(9).setCellValue(downloadInnoResultScore.getTeamNumber());
            row1.createCell(10).setCellValue(downloadInnoResultScore.getUserCv());
            row1.createCell(11).setCellValue(downloadInnoResultScore.getApplyPreComment());

            row1.createCell(12).setCellValue(downloadInnoResultScore.getPreJudgeScore1());
            row1.createCell(13).setCellValue(downloadInnoResultScore.getPreJudgeScore2());
            row1.createCell(14).setCellValue(downloadInnoResultScore.getPreJudgeScore3());
            row1.createCell(15).setCellValue(downloadInnoResultScore.getPreJudgeScore4());
            row1.createCell(16).setCellValue(downloadInnoResultScore.getPreJudgeScore5());
            row1.createCell(17).setCellValue(downloadInnoResultScore.getPreJudgeScore6());
            row1.createCell(18).setCellValue(downloadInnoResultScore.getPreJudgeScore7());
            row1.createCell(19).setCellValue(downloadInnoResultScore.getPreJudgeScore8());
            row1.createCell(20).setCellValue(downloadInnoResultScore.getPreJudgeScore9());
            row1.createCell(21).setCellValue(downloadInnoResultScore.getPreJudgeScore10());
            row1.createCell(22).setCellValue(downloadInnoResultScore.getPreJudgeScore11());
            row1.createCell(23).setCellValue(downloadInnoResultScore.getPreJudgeScore12());
            row1.createCell(24).setCellValue(downloadInnoResultScore.getPreJudgeScore13());
            row1.createCell(25).setCellValue(downloadInnoResultScore.getPreOriginalScoreSum());
            row1.createCell(26).setCellValue(downloadInnoResultScore.getPreOriginalScoreAverage());
            row1.createCell(27).setCellValue(downloadInnoResultScore.getPreWeightedScoreSum());
            row1.createCell(28).setCellValue(downloadInnoResultScore.getPreWeightedScoreAverage());
            row1.createCell(29).setCellValue(downloadInnoResultScore.getPreTimes());

            row1.createCell(30).setCellValue(downloadInnoResultScore.getPptJudgeScore1());
            row1.createCell(31).setCellValue(downloadInnoResultScore.getPptJudgeScore2());
            row1.createCell(32).setCellValue(downloadInnoResultScore.getPptJudgeScore3());
            row1.createCell(33).setCellValue(downloadInnoResultScore.getPptJudgeScore4());
            row1.createCell(34).setCellValue(downloadInnoResultScore.getPptJudgeScore5());
            row1.createCell(35).setCellValue(downloadInnoResultScore.getPptJudgeScore6());
            row1.createCell(36).setCellValue(downloadInnoResultScore.getPptJudgeScore7());
            row1.createCell(37).setCellValue(downloadInnoResultScore.getPptOriginalScoreSum());
            row1.createCell(38).setCellValue(downloadInnoResultScore.getPptOriginalScoreAverage());
            row1.createCell(39).setCellValue(downloadInnoResultScore.getPptWeightedScoreSum());
            row1.createCell(40).setCellValue(downloadInnoResultScore.getPptWeightedScoreAverage());
            row1.createCell(41).setCellValue(downloadInnoResultScore.getPptTimes());

            row1.createCell(42).setCellValue(downloadInnoResultScore.getOriginalScoreSum());
            row1.createCell(43).setCellValue(downloadInnoResultScore.getOriginalScoreAverage());
            row1.createCell(44).setCellValue(downloadInnoResultScore.getWeightedScoreSum());
            row1.createCell(45).setCellValue(downloadInnoResultScore.getWeightedScoreAverage());

            String applyId = downloadInnoResultScore.getProjectNo().substring(4,6);
            if(applyId.equals("WS")){
                row1.createCell(46).setCellValue(ws);
                ws++;
            }else if(applyId.equals("LG")){
                row1.createCell(46).setCellValue(lg);
                lg++;
            }else if(applyId.equals("YX")){
                row1.createCell(46).setCellValue(yx);
                yx++;
            }else if(applyId.equals("ZH")){
                row1.createCell(46).setCellValue(zh);
                zh++;
            }

            Double[] preJudgeScores = new Double[13];
            preJudgeScores[0] = downloadInnoResultScore.getPreJudgeScore1();
            preJudgeScores[1] = downloadInnoResultScore.getPreJudgeScore2();
            preJudgeScores[2] = downloadInnoResultScore.getPreJudgeScore3();
            preJudgeScores[3] = downloadInnoResultScore.getPreJudgeScore4();
            preJudgeScores[4] = downloadInnoResultScore.getPreJudgeScore5();
            preJudgeScores[5] = downloadInnoResultScore.getPreJudgeScore6();
            preJudgeScores[6] = downloadInnoResultScore.getPreJudgeScore7();
            preJudgeScores[7] = downloadInnoResultScore.getPreJudgeScore8();
            preJudgeScores[8] = downloadInnoResultScore.getPreJudgeScore9();
            preJudgeScores[9] = downloadInnoResultScore.getPreJudgeScore10();
            preJudgeScores[10] = downloadInnoResultScore.getPreJudgeScore11();
            preJudgeScores[11] = downloadInnoResultScore.getPreJudgeScore12();
            preJudgeScores[12] = downloadInnoResultScore.getPreJudgeScore13();
            double minPreScore = downloadInnoResultScore.getPreJudgeScore1();
            double maxPreScore = downloadInnoResultScore.getPreJudgeScore1();
            int minPreScoreIndex = 0;
            int maxPreScoreIndex = 0;
            for(int m = 0; m < 13; m++){
                if(preJudgeScores[m] < minPreScore){
                    minPreScore = preJudgeScores[m];
                    minPreScoreIndex = m;
                }
                if(preJudgeScores[m] > maxPreScore){
                    maxPreScore = preJudgeScores[m];
                    maxPreScoreIndex = m;
                }
            }
            Double[] pptJudgeScores = new Double[7];
            pptJudgeScores[0] = downloadInnoResultScore.getPptJudgeScore1();
            pptJudgeScores[1] = downloadInnoResultScore.getPptJudgeScore2();
            pptJudgeScores[2] = downloadInnoResultScore.getPptJudgeScore3();
            pptJudgeScores[3] = downloadInnoResultScore.getPptJudgeScore4();
            pptJudgeScores[4] = downloadInnoResultScore.getPptJudgeScore5();
            pptJudgeScores[5] = downloadInnoResultScore.getPptJudgeScore6();
            pptJudgeScores[6] = downloadInnoResultScore.getPptJudgeScore7();
            double minPPTScore = downloadInnoResultScore.getPptJudgeScore1();
            double maxPPTScore = downloadInnoResultScore.getPptJudgeScore1();
            int minPPTScoreIndex = 0;
            int maxPPTScoreIndex = 0;
            for(int m = 0; m < 7; m++){
                if(pptJudgeScores[m] < minPPTScore){
                    minPPTScore = pptJudgeScores[m];
                    minPPTScoreIndex = m;
                }
                if(pptJudgeScores[m] > maxPPTScore){
                    maxPPTScore = pptJudgeScores[m];
                    maxPPTScoreIndex = m;
                }
            }
            HSSFCell cell3 = sheet1.getRow(i+1).getCell(minPreScoreIndex + 12);
            HSSFCell cell4 = sheet1.getRow(i+1).getCell(maxPreScoreIndex + 12);
            HSSFCell cell5 = sheet1.getRow(i+1).getCell(28);

            HSSFCell cell6 = sheet1.getRow(i+1).getCell(minPPTScoreIndex + 30);
            HSSFCell cell7 = sheet1.getRow(i+1).getCell(maxPPTScoreIndex + 30);
            HSSFCell cell8 = sheet1.getRow(i+1).getCell(40);

            HSSFCell cell9 = sheet1.getRow(i+1).getCell(45);
            cell3.setCellStyle(style);
            cell4.setCellStyle(style2);
            cell5.setCellStyle(style);

            cell6.setCellStyle(style);
            cell7.setCellStyle(style2);
            cell8.setCellStyle(style);

            cell9.setCellStyle(style2);
        }


        // 共有多少个评委

        int num1 = exportData.size();
            int p = 1, q = 1;
            for(int j = 0 ; j < num1 ; j++){

            /*HSSFFont font = wb.createFont();
            font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
            HSSFCellStyle style = wb.createCellStyle();
            style.setFont(font);

            HSSFFont font2 = wb.createFont();
            font2.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
            HSSFCellStyle style2 = wb.createCellStyle();
            style2.setFont(font2);*/
                ArrayList<DownloadInnoAllScore> temp = exportData.get(j);
                logger.info("XLSXUtil: " + j);

                //sheet1 = wb.createSheet(temp.get(0).getJudgeId() + temp.get(0).getJudgeName());
                if(temp.get(0).getScorePhase().equals("网评评委")){
                    if(userType.equals("admin")){
                        sheet1 = wb.createSheet(temp.get(0).getJudgeId() + temp.get(0).getJudgeName() + "网评");
                    }else{
                        sheet1 = wb.createSheet("网评评委" + p);
                        p++;
                    }
                    row1 = sheet1.createRow(0);
                    sheetTitle = innoPreFields.split(",");
                    for (int i = 0 ; i < sheetTitle.length; i++) {
                        Cell cell = row1.createCell(i);
                        cell.setCellValue(sheetTitle[i]);
                        if(i == 14)
                            cell.setCellStyle(style);
                        if(i == 19)
                            cell.setCellStyle(style);
                        if(i == 20)
                            cell.setCellStyle(style2);
                    }

                    int num2 = temp.size();
                    for(int i = 0;i < num2;++i){
                        row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
                        DownloadInnoAllScore downloadInnoAllScore = temp.get(i);
                        //row1.createCell(0).setCellValue(downloadInnoAllScore.getJudgeId());
                        //row1.createCell(1).setCellValue(downloadInnoAllScore.getJudgeName());
                        row1.createCell(0).setCellValue(downloadInnoAllScore.getProjectNo());
                        row1.createCell(1).setCellValue(downloadInnoAllScore.getProjectApplySort());
                        row1.createCell(2).setCellValue(downloadInnoAllScore.getCaseSort());
                        row1.createCell(3).setCellValue(downloadInnoAllScore.getProjectSort());
                        row1.createCell(4).setCellValue(downloadInnoAllScore.getUserId());
                        row1.createCell(5).setCellValue(downloadInnoAllScore.getUsername());
                        row1.createCell(6).setCellValue(downloadInnoAllScore.getTitle());
                        row1.createCell(7).setCellValue(downloadInnoAllScore.getDepartment());
                        row1.createCell(8).setCellValue(downloadInnoAllScore.getTeamMember());
                        row1.createCell(9).setCellValue(downloadInnoAllScore.getJudgeScore1());
                        row1.createCell(10).setCellValue(downloadInnoAllScore.getJudgeScore2());
                        row1.createCell(11).setCellValue(downloadInnoAllScore.getJudgeScore3());
                        row1.createCell(12).setCellValue(downloadInnoAllScore.getJudgeScore4());
                        row1.createCell(13).setCellValue(downloadInnoAllScore.getJudgeScore5());
                        row1.createCell(14).setCellValue(downloadInnoAllScore.getVideoScore());
                        row1.createCell(15).setCellValue(downloadInnoAllScore.getJudgeScore6());
                        row1.createCell(16).setCellValue(downloadInnoAllScore.getJudgeScore7());
                        row1.createCell(17).setCellValue(downloadInnoAllScore.getJudgeScore8());
                        row1.createCell(18).setCellValue(downloadInnoAllScore.getJudgeScore9());
                        row1.createCell(19).setCellValue(downloadInnoAllScore.getDocumentScore());
                        row1.createCell(20).setCellValue(downloadInnoAllScore.getPreScore());
                        row1.createCell(21).setCellValue(downloadInnoAllScore.getAssessment());
                        HSSFCell cell2 = sheet1.getRow(i+1).getCell(14);
                        HSSFCell cell3 = sheet1.getRow(i+1).getCell(19);
                        HSSFCell cell4 = sheet1.getRow(i+1).getCell(20);
                        cell2.setCellStyle(style);
                        cell3.setCellStyle(style);
                        cell4.setCellStyle(style2);
                    }
                }else{
                    if(userType.equals("admin")){
                        sheet1 = wb.createSheet(temp.get(0).getJudgeId() + temp.get(0).getJudgeName() + "-现评");
                    }else{
                        sheet1 = wb.createSheet("现评评委" + q);
                        q++;
                    }
                    row1 = sheet1.createRow(0);
                    sheetTitle = innoPPTFields.split(",");
                    for (int i = 0 ; i < sheetTitle.length; i++) {
                        Cell cell = row1.createCell(i);
                        cell.setCellValue(sheetTitle[i]);
                        if(i == 14)
                            cell.setCellStyle(style);
                    }

                    int num2 = temp.size();
                    for(int i = 0;i < num2;++i){
                        row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
                        DownloadInnoAllScore downloadInnoAllScore = temp.get(i);
                        //row1.createCell(0).setCellValue(downloadInnoAllScore.getJudgeId());
                        //row1.createCell(1).setCellValue(downloadInnoAllScore.getJudgeName());
                        row1.createCell(0).setCellValue(downloadInnoAllScore.getProjectNo());
                        row1.createCell(1).setCellValue(downloadInnoAllScore.getProjectApplySort());
                        row1.createCell(2).setCellValue(downloadInnoAllScore.getCaseSort());
                        row1.createCell(3).setCellValue(downloadInnoAllScore.getProjectSort());
                        row1.createCell(4).setCellValue(downloadInnoAllScore.getUserId());
                        row1.createCell(5).setCellValue(downloadInnoAllScore.getUsername());
                        row1.createCell(6).setCellValue(downloadInnoAllScore.getTitle());
                        row1.createCell(7).setCellValue(downloadInnoAllScore.getDepartment());
                        row1.createCell(8).setCellValue(downloadInnoAllScore.getTeamMember());
                        row1.createCell(9).setCellValue(downloadInnoAllScore.getJudgeScore10());
                        row1.createCell(10).setCellValue(downloadInnoAllScore.getJudgeScore11());
                        row1.createCell(11).setCellValue(downloadInnoAllScore.getJudgeScore12());
                        row1.createCell(12).setCellValue(downloadInnoAllScore.getJudgeScore13());
                        row1.createCell(13).setCellValue(downloadInnoAllScore.getJudgeScore14());
                        row1.createCell(14).setCellValue(downloadInnoAllScore.getPptScore());
                        row1.createCell(15).setCellValue(downloadInnoAllScore.getAssessment());
                        HSSFCell cell2 = sheet1.getRow(i+1).getCell(14);
                        cell2.setCellStyle(style);
                    }
                }

            }



        // 设置文件名
        String title = fileName + ".xls";

        title = new String(title.getBytes(), "ISO8859-1");

        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream os = response.getOutputStream();

        response.setHeader("Content-disposition",
                "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1") + ".xls");
        // 定义输出类型
        response.setContentType("application/msexcel");
        wb.write(os);
        os.close();
    }
    // 导出含有一个sheet的xls文件，创新大赛平均分，没有评委小分
    public static void createInnoScoreXLSFileWithSheet(ArrayList<ArrayList<DownloadInnoAllScore>> exportData,
                                                        ArrayList<DownloadInnoResultScore> innoResultScores,
                                                        String fieldInno, String fieldResult, String fileName,
                                                        String userType) throws IOException {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);

        HSSFFont font2 = wb.createFont();
        font2.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);

        HSSFSheet sheet1 = wb.createSheet(year+"创新大赛网评分数");
        HSSFRow row1 = sheet1.createRow(0);
        String[] sheetTitle = fieldResult.split(",");
        for (int i = 0 ; i < sheetTitle.length; i++) {
            Cell cell = row1.createCell(i);
            cell.setCellValue(sheetTitle[i]);

            if(i == 28)
                cell.setCellStyle(style);
            if(i == 40)
                cell.setCellStyle(style);
            if(i == 45)
                cell.setCellStyle(style2);
        }

        int numRes = innoResultScores.size();
        int ws = 1, lg = 1, yx = 1, zh = 1;
        for(int i = 0;i < numRes;++i){
            row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
            DownloadInnoResultScore downloadInnoResultScore = innoResultScores.get(i);
            row1.createCell(0).setCellValue(downloadInnoResultScore.getProjectNo());
            row1.createCell(1).setCellValue(downloadInnoResultScore.getProjectApplySort());
            row1.createCell(2).setCellValue(downloadInnoResultScore.getCaseSort());
            row1.createCell(3).setCellValue(downloadInnoResultScore.getProjectSort());
            row1.createCell(4).setCellValue(downloadInnoResultScore.getUserId());
            row1.createCell(5).setCellValue(downloadInnoResultScore.getUserName());
            row1.createCell(6).setCellValue(downloadInnoResultScore.getTitle());
            row1.createCell(7).setCellValue(downloadInnoResultScore.getDepartment());
            row1.createCell(8).setCellValue(downloadInnoResultScore.getTeamMember());
            row1.createCell(9).setCellValue(downloadInnoResultScore.getTeamNumber());
            row1.createCell(10).setCellValue(downloadInnoResultScore.getUserCv());
            row1.createCell(11).setCellValue(downloadInnoResultScore.getApplyPreComment());

            row1.createCell(12).setCellValue(downloadInnoResultScore.getPreJudgeScore1());
            row1.createCell(13).setCellValue(downloadInnoResultScore.getPreJudgeScore2());
            row1.createCell(14).setCellValue(downloadInnoResultScore.getPreJudgeScore3());
            row1.createCell(15).setCellValue(downloadInnoResultScore.getPreJudgeScore4());
            row1.createCell(16).setCellValue(downloadInnoResultScore.getPreJudgeScore5());
            row1.createCell(17).setCellValue(downloadInnoResultScore.getPreJudgeScore6());
            row1.createCell(18).setCellValue(downloadInnoResultScore.getPreJudgeScore7());
            row1.createCell(19).setCellValue(downloadInnoResultScore.getPreJudgeScore8());
            row1.createCell(20).setCellValue(downloadInnoResultScore.getPreJudgeScore9());
            row1.createCell(21).setCellValue(downloadInnoResultScore.getPreJudgeScore10());
            row1.createCell(22).setCellValue(downloadInnoResultScore.getPreJudgeScore11());
            row1.createCell(23).setCellValue(downloadInnoResultScore.getPreJudgeScore12());
            row1.createCell(24).setCellValue(downloadInnoResultScore.getPreJudgeScore13());
            row1.createCell(25).setCellValue(downloadInnoResultScore.getPreOriginalScoreSum());
            row1.createCell(26).setCellValue(downloadInnoResultScore.getPreOriginalScoreAverage());
            row1.createCell(27).setCellValue(downloadInnoResultScore.getPreWeightedScoreSum());
            row1.createCell(28).setCellValue(downloadInnoResultScore.getPreWeightedScoreAverage());
            row1.createCell(29).setCellValue(downloadInnoResultScore.getPreTimes());

            row1.createCell(30).setCellValue(downloadInnoResultScore.getPptJudgeScore1());
            row1.createCell(31).setCellValue(downloadInnoResultScore.getPptJudgeScore2());
            row1.createCell(32).setCellValue(downloadInnoResultScore.getPptJudgeScore3());
            row1.createCell(33).setCellValue(downloadInnoResultScore.getPptJudgeScore4());
            row1.createCell(34).setCellValue(downloadInnoResultScore.getPptJudgeScore5());
            row1.createCell(35).setCellValue(downloadInnoResultScore.getPptJudgeScore6());
            row1.createCell(36).setCellValue(downloadInnoResultScore.getPptJudgeScore7());
            row1.createCell(37).setCellValue(downloadInnoResultScore.getPptOriginalScoreSum());
            row1.createCell(38).setCellValue(downloadInnoResultScore.getPptOriginalScoreAverage());
            row1.createCell(39).setCellValue(downloadInnoResultScore.getPptWeightedScoreSum());
            row1.createCell(40).setCellValue(downloadInnoResultScore.getPptWeightedScoreAverage());
            row1.createCell(41).setCellValue(downloadInnoResultScore.getPptTimes());

            row1.createCell(42).setCellValue(downloadInnoResultScore.getOriginalScoreSum());
            row1.createCell(43).setCellValue(downloadInnoResultScore.getOriginalScoreAverage());
            row1.createCell(44).setCellValue(downloadInnoResultScore.getWeightedScoreSum());
            row1.createCell(45).setCellValue(downloadInnoResultScore.getWeightedScoreAverage());

            String applyId = downloadInnoResultScore.getProjectNo().substring(4,6);
            if(applyId.equals("WS")){
                row1.createCell(46).setCellValue(ws);
                ws++;
            }else if(applyId.equals("LG")){
                row1.createCell(46).setCellValue(lg);
                lg++;
            }else if(applyId.equals("YX")){
                row1.createCell(46).setCellValue(yx);
                yx++;
            }else if(applyId.equals("ZH")){
                row1.createCell(46).setCellValue(zh);
                zh++;
            }

            Double[] preJudgeScores = new Double[13];
            preJudgeScores[0] = downloadInnoResultScore.getPreJudgeScore1();
            preJudgeScores[1] = downloadInnoResultScore.getPreJudgeScore2();
            preJudgeScores[2] = downloadInnoResultScore.getPreJudgeScore3();
            preJudgeScores[3] = downloadInnoResultScore.getPreJudgeScore4();
            preJudgeScores[4] = downloadInnoResultScore.getPreJudgeScore5();
            preJudgeScores[5] = downloadInnoResultScore.getPreJudgeScore6();
            preJudgeScores[6] = downloadInnoResultScore.getPreJudgeScore7();
            preJudgeScores[7] = downloadInnoResultScore.getPreJudgeScore8();
            preJudgeScores[8] = downloadInnoResultScore.getPreJudgeScore9();
            preJudgeScores[9] = downloadInnoResultScore.getPreJudgeScore10();
            preJudgeScores[10] = downloadInnoResultScore.getPreJudgeScore11();
            preJudgeScores[11] = downloadInnoResultScore.getPreJudgeScore12();
            preJudgeScores[12] = downloadInnoResultScore.getPreJudgeScore13();
            double minPreScore = downloadInnoResultScore.getPreJudgeScore1();
            double maxPreScore = downloadInnoResultScore.getPreJudgeScore1();
            int minPreScoreIndex = 0;
            int maxPreScoreIndex = 0;
            for(int m = 0; m < 13; m++){
                if(preJudgeScores[m] < minPreScore){
                    minPreScore = preJudgeScores[m];
                    minPreScoreIndex = m;
                }
                if(preJudgeScores[m] > maxPreScore){
                    maxPreScore = preJudgeScores[m];
                    maxPreScoreIndex = m;
                }
            }
            Double[] pptJudgeScores = new Double[7];
            pptJudgeScores[0] = downloadInnoResultScore.getPptJudgeScore1();
            pptJudgeScores[1] = downloadInnoResultScore.getPptJudgeScore2();
            pptJudgeScores[2] = downloadInnoResultScore.getPptJudgeScore3();
            pptJudgeScores[3] = downloadInnoResultScore.getPptJudgeScore4();
            pptJudgeScores[4] = downloadInnoResultScore.getPptJudgeScore5();
            pptJudgeScores[5] = downloadInnoResultScore.getPptJudgeScore6();
            pptJudgeScores[6] = downloadInnoResultScore.getPptJudgeScore7();
            double minPPTScore = downloadInnoResultScore.getPptJudgeScore1();
            double maxPPTScore = downloadInnoResultScore.getPptJudgeScore1();
            int minPPTScoreIndex = 0;
            int maxPPTScoreIndex = 0;
            for(int m = 0; m < 7; m++){
                if(pptJudgeScores[m] < minPPTScore){
                    minPPTScore = pptJudgeScores[m];
                    minPPTScoreIndex = m;
                }
                if(pptJudgeScores[m] > maxPPTScore){
                    maxPPTScore = pptJudgeScores[m];
                    maxPPTScoreIndex = m;
                }
            }
            HSSFCell cell3 = sheet1.getRow(i+1).getCell(minPreScoreIndex + 12);
            HSSFCell cell4 = sheet1.getRow(i+1).getCell(maxPreScoreIndex + 12);
            HSSFCell cell5 = sheet1.getRow(i+1).getCell(28);

            HSSFCell cell6 = sheet1.getRow(i+1).getCell(minPPTScoreIndex + 30);
            HSSFCell cell7 = sheet1.getRow(i+1).getCell(maxPPTScoreIndex + 30);
            HSSFCell cell8 = sheet1.getRow(i+1).getCell(40);

            HSSFCell cell9 = sheet1.getRow(i+1).getCell(45);
            cell3.setCellStyle(style);
            cell4.setCellStyle(style2);
            cell5.setCellStyle(style);

            cell6.setCellStyle(style);
            cell7.setCellStyle(style2);
            cell8.setCellStyle(style);

            cell9.setCellStyle(style2);
        }

        // 设置文件名
        String title = fileName + ".xls";
        title = new String(title.getBytes(), "ISO8859-1");

        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream os = response.getOutputStream();

        response.setHeader("Content-disposition",
                "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1") + ".xls");
        // 定义输出类型
        response.setContentType("application/msexcel");
        wb.write(os);
        os.close();
    }

    // 导出含有多个sheet的xls文件，论文平均分+每个评委小分
    public static void createPaperScoreXLSFileWithSheets(ArrayList<ArrayList<DownloadPaperAllScore>> exportData,
                                                         ArrayList<DownloadPaperResultScore> paperResultScores,
                                                         String fieldPaper, String fieldResult, String fileName,
                                                         String userType) throws IOException {
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);

        HSSFFont font2 = wb.createFont();
        font2.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);

        HSSFSheet sheet1 = wb.createSheet(year + "教研论文网评分数");
        HSSFRow row1 = sheet1.createRow(0);
        String[] sheetTitle = fieldResult.split(",");
        for (int i = 0 ; i < sheetTitle.length; i++) {
            Cell cell = row1.createCell(i);
            cell.setCellValue(sheetTitle[i]);
            if(i == 18)
                cell.setCellStyle(style2);
        }

        int numRes = paperResultScores.size();
        for(int i = 0;i < numRes;++i){
            row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
            DownloadPaperResultScore downloadPaperResultScore = paperResultScores.get(i);
            row1.createCell(0).setCellValue(downloadPaperResultScore.getProjectNo());
            row1.createCell(1).setCellValue(downloadPaperResultScore.getUserId());
            row1.createCell(2).setCellValue(downloadPaperResultScore.getUserName());
            row1.createCell(3).setCellValue(downloadPaperResultScore.getDeptName());
            row1.createCell(4).setCellValue(downloadPaperResultScore.getTitle());
            row1.createCell(5).setCellValue(downloadPaperResultScore.getJudgeScore1());
            row1.createCell(6).setCellValue(downloadPaperResultScore.getJudgeScore2());
            row1.createCell(7).setCellValue(downloadPaperResultScore.getJudgeScore3());
            row1.createCell(8).setCellValue(downloadPaperResultScore.getJudgeScore4());
            row1.createCell(9).setCellValue(downloadPaperResultScore.getJudgeScore5());
            row1.createCell(10).setCellValue(downloadPaperResultScore.getJudgeScore6());
            row1.createCell(11).setCellValue(downloadPaperResultScore.getJudgeScore7());
            row1.createCell(12).setCellValue(downloadPaperResultScore.getJudgeScore8());
            row1.createCell(13).setCellValue(downloadPaperResultScore.getJudgeScore9());
            row1.createCell(14).setCellValue(downloadPaperResultScore.getJudgeScore10());
            row1.createCell(15).setCellValue(downloadPaperResultScore.getOriginalScoreSum());
            row1.createCell(16).setCellValue(downloadPaperResultScore.getOriginalScoreAverage());
            row1.createCell(17).setCellValue(downloadPaperResultScore.getWeightedScoreSum());
            row1.createCell(18).setCellValue(downloadPaperResultScore.getWeightedScoreAverage());
            row1.createCell(19).setCellValue(i+1);
            row1.createCell(20).setCellValue(downloadPaperResultScore.getTimes());
            Double[] judgeScores = new Double[10];
            judgeScores[0] = downloadPaperResultScore.getJudgeScore1();
            judgeScores[1] = downloadPaperResultScore.getJudgeScore2();
            judgeScores[2] = downloadPaperResultScore.getJudgeScore3();
            judgeScores[3] = downloadPaperResultScore.getJudgeScore4();
            judgeScores[4] = downloadPaperResultScore.getJudgeScore5();
            judgeScores[5] = downloadPaperResultScore.getJudgeScore6();
            judgeScores[6] = downloadPaperResultScore.getJudgeScore7();
            judgeScores[7] = downloadPaperResultScore.getJudgeScore8();
            judgeScores[8] = downloadPaperResultScore.getJudgeScore9();
            judgeScores[9] = downloadPaperResultScore.getJudgeScore10();
            double minScore = downloadPaperResultScore.getJudgeScore1();
            double maxScore = downloadPaperResultScore.getJudgeScore1();
            int minScoreIndex = 0;
            int maxScoreIndex = 0;
            double temp = 0;
            for(int m = 0; m < 10; m++){
                if(judgeScores[m] < minScore){
                    minScore = judgeScores[m];
                    minScoreIndex = m;
                }
                if(judgeScores[m] > maxScore){
                    maxScore = judgeScores[m];
                    maxScoreIndex = m;
                }
            }
            HSSFCell cell3 = sheet1.getRow(i+1).getCell(minScoreIndex + 5);
            HSSFCell cell4 = sheet1.getRow(i+1).getCell(maxScoreIndex + 5);
            HSSFCell cell5 = sheet1.getRow(i+1).getCell(18);
            cell3.setCellStyle(style);
            cell4.setCellStyle(style2);
            cell5.setCellStyle(style2);
        }


        // 共有多少个评委
        int num1 = exportData.size();
        int p = 1;
        for(int j = 0 ; j < num1 ; j++){

            /*HSSFFont font = wb.createFont();
            font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
            HSSFCellStyle style = wb.createCellStyle();
            style.setFont(font);

            HSSFFont font2 = wb.createFont();
            font2.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
            HSSFCellStyle style2 = wb.createCellStyle();
            style2.setFont(font2);*/

            ArrayList<DownloadPaperAllScore> temp = exportData.get(j);
            //sheet1 = wb.createSheet(temp.get(0).getJudgeId() + temp.get(0).getJudgeName());
            if(userType.equals("admin")){
                sheet1 = wb.createSheet(temp.get(0).getJudgeId() + temp.get(0).getJudgeName());
            }else{
                sheet1 = wb.createSheet("评委" + p);
                p++;
            }
            row1 = sheet1.createRow(0);
            sheetTitle = fieldPaper.split(",");
            for (int i = 0 ; i < sheetTitle.length; i++) {
                Cell cell = row1.createCell(i);
                cell.setCellValue(sheetTitle[i]);
                if(i == 15)
                    cell.setCellStyle(style2);
            }

            int num2 = temp.size();
            for(int i = 0;i < num2;++i){
                row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
                DownloadPaperAllScore downloadPaperAllScore = temp.get(i);
                //row1.createCell(0).setCellValue(downloadPaperAllScore.getJudgeId());
                //row1.createCell(1).setCellValue(downloadPaperAllScore.getJudgeName());
                row1.createCell(0).setCellValue(downloadPaperAllScore.getProjectNo());
                row1.createCell(1).setCellValue(downloadPaperAllScore.getUserId());
                row1.createCell(2).setCellValue(downloadPaperAllScore.getUserName());
                row1.createCell(3).setCellValue(downloadPaperAllScore.getDeptName());
                row1.createCell(4).setCellValue(downloadPaperAllScore.getTitle());
                row1.createCell(5).setCellValue(downloadPaperAllScore.getJudgeScore1());
                row1.createCell(6).setCellValue(downloadPaperAllScore.getJudgeScore2());
                row1.createCell(7).setCellValue(downloadPaperAllScore.getJudgeScore3());
                row1.createCell(8).setCellValue(downloadPaperAllScore.getJudgeScore4());
                row1.createCell(9).setCellValue(downloadPaperAllScore.getJudgeScore5());
                row1.createCell(10).setCellValue(downloadPaperAllScore.getJudgeScore6());
                row1.createCell(11).setCellValue(downloadPaperAllScore.getJudgeScore7());
                row1.createCell(12).setCellValue(downloadPaperAllScore.getJudgeScore8());
                row1.createCell(13).setCellValue(downloadPaperAllScore.getJudgeScore9());
                row1.createCell(14).setCellValue(downloadPaperAllScore.getJudgeScore10());
                row1.createCell(15).setCellValue(downloadPaperAllScore.getScore());
                row1.createCell(16).setCellValue(downloadPaperAllScore.getAssessment());
                HSSFCell cell3 = sheet1.getRow(i+1).getCell(15);
                cell3.setCellStyle(style2);
            }
        }
        // 设置文件名
        String title = fileName + ".xls";
        title = new String(title.getBytes(), "ISO8859-1");

        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream os = response.getOutputStream();

        response.setHeader("Content-disposition",
                "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1") + ".xls");
        // 定义输出类型
        response.setContentType("application/msexcel");
        wb.write(os);
        os.close();
    }

    // 导出含有多个sheet的xls文件，结题平均分+每个评委小分
    public static void createFinalScoreXLSFileWithSheets(ArrayList<ArrayList<DownloadFinalAllScore>> exportData,
                                                         ArrayList<DownloadFinalResultScore> finalResultScores,
                                                         String fieldFinal, String fieldResult, String fileName,
                                                         String isPre, String userType) throws IOException {
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);

        HSSFFont font2 = wb.createFont();
        font2.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);

        String firstSheetName = "";
        if(isPre.equals("final")){
            firstSheetName = year + "结题报告网评分数";
        }else{
            firstSheetName = year + "结题报告全部分数";
        }
        HSSFSheet sheet1 = wb.createSheet(firstSheetName);
        HSSFRow row1 = sheet1.createRow(0);
        String[] sheetTitle = fieldResult.split(",");
        for (int i = 0 ; i < sheetTitle.length; i++) {
            Cell cell = row1.createCell(i);
            cell.setCellValue(sheetTitle[i]);
            if(i == 19)
                cell.setCellStyle(style2);
        }

        int numRes = finalResultScores.size();
        for(int i = 0;i < numRes;++i){
            row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
            DownloadFinalResultScore downloadFinalResultScore = finalResultScores.get(i);
            row1.createCell(0).setCellValue(downloadFinalResultScore.getProjectNo());
            row1.createCell(1).setCellValue(downloadFinalResultScore.getUserId());
            row1.createCell(2).setCellValue(downloadFinalResultScore.getUserName());
            row1.createCell(3).setCellValue(downloadFinalResultScore.getDeptName());
            row1.createCell(4).setCellValue(downloadFinalResultScore.getTitle());
            row1.createCell(5).setCellValue(downloadFinalResultScore.getJudgeScore1());
            row1.createCell(6).setCellValue(downloadFinalResultScore.getJudgeScore2());
            row1.createCell(7).setCellValue(downloadFinalResultScore.getJudgeScore3());
            row1.createCell(8).setCellValue(downloadFinalResultScore.getJudgeScore4());
            row1.createCell(9).setCellValue(downloadFinalResultScore.getJudgeScore5());
            row1.createCell(10).setCellValue(downloadFinalResultScore.getJudgeScore6());
            row1.createCell(11).setCellValue(downloadFinalResultScore.getJudgeScore7());
            row1.createCell(12).setCellValue(downloadFinalResultScore.getJudgeScore8());
            row1.createCell(13).setCellValue(downloadFinalResultScore.getJudgeScore9());
            row1.createCell(14).setCellValue(downloadFinalResultScore.getJudgeScore10());
            row1.createCell(15).setCellValue(downloadFinalResultScore.getJudgeScore11());
            row1.createCell(16).setCellValue(downloadFinalResultScore.getOriginalScoreSum());
            row1.createCell(17).setCellValue(downloadFinalResultScore.getOriginalScoreAverage());
            row1.createCell(18).setCellValue(downloadFinalResultScore.getWeightedScoreSum());
            row1.createCell(19).setCellValue(downloadFinalResultScore.getWeightedScoreAverage());
            row1.createCell(20).setCellValue(i+1);
            row1.createCell(21).setCellValue(downloadFinalResultScore.getTimes());
            Double[] judgeScores = new Double[12];
            judgeScores[0] = downloadFinalResultScore.getJudgeScore1();
            judgeScores[1] = downloadFinalResultScore.getJudgeScore2();
            judgeScores[2] = downloadFinalResultScore.getJudgeScore3();
            judgeScores[3] = downloadFinalResultScore.getJudgeScore4();
            judgeScores[4] = downloadFinalResultScore.getJudgeScore5();
            judgeScores[5] = downloadFinalResultScore.getJudgeScore6();
            judgeScores[6] = downloadFinalResultScore.getJudgeScore7();
            judgeScores[7] = downloadFinalResultScore.getJudgeScore8();
            judgeScores[8] = downloadFinalResultScore.getJudgeScore9();
            judgeScores[9] = downloadFinalResultScore.getJudgeScore10();
            judgeScores[10] = downloadFinalResultScore.getJudgeScore11();
            double minScore = downloadFinalResultScore.getJudgeScore1();
            double maxScore = downloadFinalResultScore.getJudgeScore1();
            int minScoreIndex = 0;
            int maxScoreIndex = 0;
            double temp = 0;
            for(int m = 0; m < 11; m++){
                if(judgeScores[m] < minScore){
                    minScore = judgeScores[m];
                    minScoreIndex = m;
                }
                if(judgeScores[m] > maxScore){
                    maxScore = judgeScores[m];
                    maxScoreIndex = m;
                }
            }
            HSSFCell cell3 = sheet1.getRow(i+1).getCell(minScoreIndex + 5);
            HSSFCell cell4 = sheet1.getRow(i+1).getCell(maxScoreIndex + 5);
            HSSFCell cell5 = sheet1.getRow(i+1).getCell(19);
            cell3.setCellStyle(style);
            cell4.setCellStyle(style2);
            cell5.setCellStyle(style2);
        }


        // 共有多少个评委
        int num1 = exportData.size();
        int p = 1;
        for(int j = 0 ; j < num1 ; j++){
            ArrayList<DownloadFinalAllScore> temp = exportData.get(j);
            //sheet1 = wb.createSheet(temp.get(0).getJudgeId() + temp.get(0).getJudgeName());
            if(userType.equals("admin")){
                sheet1 = wb.createSheet(temp.get(0).getJudgeId() + temp.get(0).getJudgeName());
            }else{
                sheet1 = wb.createSheet("评委" + p);
                p++;
            }
            row1 = sheet1.createRow(0);
            sheetTitle = fieldFinal.split(",");
            for (int i = 0 ; i < sheetTitle.length; i++) {
                Cell cell = row1.createCell(i);
                cell.setCellValue(sheetTitle[i]);
                if(i == 14)
                    cell.setCellStyle(style);
                if(i == 16)
                    cell.setCellStyle(style2);
            }

            int num2 = temp.size();
            for(int i = 0;i < num2;++i){
                row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
                DownloadFinalAllScore downloadFinalAllScore = temp.get(i);
                //row1.createCell(0).setCellValue(downloadFinalAllScore.getJudgeId());
                //row1.createCell(1).setCellValue(downloadFinalAllScore.getJudgeName());
                row1.createCell(0).setCellValue(downloadFinalAllScore.getProjectNo());
                row1.createCell(1).setCellValue(downloadFinalAllScore.getUserId());
                row1.createCell(2).setCellValue(downloadFinalAllScore.getUserName());
                row1.createCell(3).setCellValue(downloadFinalAllScore.getDeptName());
                row1.createCell(4).setCellValue(downloadFinalAllScore.getTitle());
                row1.createCell(5).setCellValue(downloadFinalAllScore.getJudgeScore1());
                row1.createCell(6).setCellValue(downloadFinalAllScore.getJudgeScore2());
                row1.createCell(7).setCellValue(downloadFinalAllScore.getJudgeScore3());
                row1.createCell(8).setCellValue(downloadFinalAllScore.getJudgeScore4());
                row1.createCell(9).setCellValue(downloadFinalAllScore.getJudgeScore5());
                row1.createCell(10).setCellValue(downloadFinalAllScore.getJudgeScore6());
                row1.createCell(11).setCellValue(downloadFinalAllScore.getJudgeScore7());
                row1.createCell(12).setCellValue(downloadFinalAllScore.getJudgeScore8());
                row1.createCell(13).setCellValue(downloadFinalAllScore.getJudgeScore9());
                row1.createCell(14).setCellValue(downloadFinalAllScore.getPreJudgeScore());
                row1.createCell(15).setCellValue(downloadFinalAllScore.getJudgeScore10());
                row1.createCell(16).setCellValue(downloadFinalAllScore.getScore());
                row1.createCell(17).setCellValue(downloadFinalAllScore.getAssessment());
                HSSFCell cell3 = sheet1.getRow(i+1).getCell(14);
                HSSFCell cell4 = sheet1.getRow(i+1).getCell(16);
                cell3.setCellStyle(style);
                cell4.setCellStyle(style2);
            }
        }
        // 设置文件名
        String title = fileName + ".xls";
        title = new String(title.getBytes(), "ISO8859-1");

        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream os = response.getOutputStream();

        response.setHeader("Content-disposition",
                "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1") + ".xls");
        // 定义输出类型
        response.setContentType("application/msexcel");
        wb.write(os);
        os.close();
    }

    // 导出最后评分含有多个sheet的xls文件
    public static void createPaperFinalXLSFileWithSheets(ArrayList<ArrayList<ResultScore>> exportData, String fields, String fileName) throws IOException {
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
        // 共有多少个评委
        int num1 = exportData.size();
        for(int j = 0 ; j < num1 ; j++){

            HSSFFont font = wb.createFont();
            font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
            HSSFCellStyle style = wb.createCellStyle();
            style.setFont(font);

            HSSFFont font2 = wb.createFont();
            font2.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
            HSSFCellStyle style2 = wb.createCellStyle();
            style2.setFont(font2);

            ArrayList<ResultScore> temp = exportData.get(j);
            HSSFSheet sheet1 = wb.createSheet(temp.get(0).getResearchType());
            HSSFRow row1 = sheet1.createRow(0);
            String[] sheetTitle = fields.split(",");
            for (int i = 0 ; i < sheetTitle.length; i++) {
                Cell cell = row1.createCell(i);
                cell.setCellValue(sheetTitle[i]);
                /*if(i == 5)
                    cell.setCellStyle(style2);*/
            }

            int num2 = temp.size();
            for(int i = 0;i < num2;++i){
                row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
                ResultScore resultScore = temp.get(i);

                row1.createCell(0).setCellValue(resultScore.getProjectNo());
                row1.createCell(1).setCellValue(resultScore.getUserId());
                row1.createCell(2).setCellValue(resultScore.getUsername());
                row1.createCell(3).setCellValue(resultScore.getTitle());
                row1.createCell(4).setCellValue(resultScore.getDepartment());
                row1.createCell(5).setCellValue(resultScore.getScore());
                row1.createCell(6).setCellValue(resultScore.getTimes());
                /*HSSFCell cell3 = sheet1.getRow(i+1).getCell(5);
                cell3.setCellStyle(style2);*/
            }
        }
        // 设置文件名
        String title = fileName + ".xls";
        title = new String(title.getBytes(), "ISO8859-1");

        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream os = response.getOutputStream();

        response.setHeader("Content-disposition",
                "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1") + ".xls");
        // 定义输出类型
        response.setContentType("application/msexcel");
        wb.write(os);
        os.close();
    }

    // 导出含有多个sheet的xls文件
    public static void createXLSFileWithOneSheet(ArrayList<DownloadScore> exportData, String fields, String fileName) throws IOException {
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();

        // 这是字体颜色
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);

        HSSFFont font2 = wb.createFont();
        font2.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);

        ArrayList<DownloadScore> temp = exportData;
        HSSFSheet sheet1 = wb.createSheet("sheet1");
        HSSFRow row1 = sheet1.createRow(0);
        String[] sheetTitle = fields.split(",");
        for (int i = 0 ; i < sheetTitle.length; i++) {
            Cell cell = row1.createCell(i);
            cell.setCellValue(sheetTitle[i]);
            if(i == 9 || i == 14)
                cell.setCellStyle(style);
            if(i == 15)
                cell.setCellStyle(style2);
        }

        int num2 = temp.size();
        for(int i = 0;i < num2;++i){
            row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
            DownloadScore downloadScore = temp.get(i);
            row1.createCell(0).setCellValue(downloadScore.getGroup());
            row1.createCell(1).setCellValue(downloadScore.getUserId());
            row1.createCell(2).setCellValue(downloadScore.getUsername());
            row1.createCell(3).setCellValue(downloadScore.getTitle());
            row1.createCell(4).setCellValue(downloadScore.getVideoScore1());
            row1.createCell(5).setCellValue(downloadScore.getVideoScore2());
            row1.createCell(6).setCellValue(downloadScore.getVideoScore3());
            row1.createCell(7).setCellValue(downloadScore.getVideoScore4());
            row1.createCell(8).setCellValue(downloadScore.getVideoScore5());
            row1.createCell(9).setCellValue(downloadScore.getVideoScore());
            row1.createCell(10).setCellValue(downloadScore.getDocumentScore1());
            row1.createCell(11).setCellValue(downloadScore.getDocumentScore2());
            row1.createCell(12).setCellValue(downloadScore.getDocumentScore3());
            row1.createCell(13).setCellValue(downloadScore.getDocumentScore4());
            row1.createCell(14).setCellValue(downloadScore.getDocumentScore());
            row1.createCell(15).setCellValue(downloadScore.getScore());
            row1.createCell(16).setCellValue(downloadScore.getAssessment());
            HSSFCell cell1 = sheet1.getRow(i+1).getCell(9);
            cell1.setCellStyle(style);
            HSSFCell cell2 = sheet1.getRow(i+1).getCell(14);
            cell2.setCellStyle(style);
            HSSFCell cell3 = sheet1.getRow(i+1).getCell(15);
            cell3.setCellStyle(style2);
        }

        // 设置文件名
        String title = fileName + ".xls";
        title = new String(title.getBytes(), "ISO8859-1");

        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream os = response.getOutputStream();

        response.setHeader("Content-disposition",
                "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1") + ".xls");
        // 定义输出类型
        response.setContentType("application/msexcel");
        wb.write(os);
        os.close();
    }

    // 导出课题研究申报汇总表的一个sheet的xls文件
    public static void createInnoInfoXLSFileWithOneSheet(ArrayList<ResearchEnroll> exportData, String fields, String fileName) throws IOException {
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();

        // 这是字体颜色
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);

        HSSFFont font2 = wb.createFont();
        font2.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);

        ArrayList<ResearchEnroll> temp = exportData;
        HSSFSheet sheet1 = wb.createSheet("sheet1");
        HSSFRow row1 = sheet1.createRow(0);
        String[] sheetTitle = fields.split(",");
        for (int i = 0 ; i < sheetTitle.length; i++) {
            Cell cell = row1.createCell(i);
            cell.setCellValue(sheetTitle[i]);
            /*if(i == 16)
                cell.setCellStyle(style2);*/
        }

        int num2 = temp.size();
        for(int i = 0;i < num2;++i){
            row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
            ResearchEnroll researchEnroll = temp.get(i);
            row1.createCell(0).setCellValue(researchEnroll.getProjectNo());
            row1.createCell(1).setCellValue(researchEnroll.getProjectApplySort());
            row1.createCell(2).setCellValue(researchEnroll.getProjectSort());
            row1.createCell(3).setCellValue(researchEnroll.getIsPass());
            row1.createCell(4).setCellValue(researchEnroll.getUserId());
            row1.createCell(5).setCellValue(researchEnroll.getUsername());
            row1.createCell(6).setCellValue(researchEnroll.getDeptName());
            row1.createCell(7).setCellValue(researchEnroll.getTitle());
            String teamMember = researchEnroll.getTeamMember();
            ArrayList<String> memberResult = new ArrayList<>();

            if(teamMember == null || teamMember.equals("")){
                for(int m = 0; m < 8; m++){
                    memberResult.add("无");
                }
            }else{
                String[] teamMembers = teamMember.split(";");
                for(int j = 0; j < teamMembers.length; j++){

                    if(teamMembers[j].equals(",,")){
                        memberResult.add("无");
                        memberResult.add("无");
                    }else{
                        String[] tempMember = teamMembers[j].split(",");
                        for(int m = 0; m < 2; m++){
                            if(tempMember[m].equals("")){
                                memberResult.add("无");
                            }else{
                                memberResult.add(tempMember[m]);
                            }
                        }
                    }
                }
            }

            for(int k = 0; k < memberResult.size(); k++){
                row1.createCell(8 + k).setCellValue(memberResult.get(k));
            }
            row1.createCell(16).setCellValue(researchEnroll.getUserCV());
            row1.createCell(17).setCellValue(researchEnroll.getApplyPreComment());
            row1.createCell(18).setCellValue(researchEnroll.getResearchType());

        }

        // 设置文件名
        String title = fileName + ".xls";
        title = new String(title.getBytes(), "ISO8859-1");

        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream os = response.getOutputStream();

        response.setHeader("Content-disposition",
                "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1") + ".xls");
        // 定义输出类型
        response.setContentType("application/msexcel");
        wb.write(os);
        os.close();
    }

    // 导出 教研论文 课题研究申报汇总表的一个sheet的xls文件
    public static void createPaperInfoXLSFileWithOneSheet(ArrayList<ResearchEnroll> exportData, String fields, String fileName) throws IOException {
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();

        // 这是字体颜色
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);

        HSSFFont font2 = wb.createFont();
        font2.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);

        ArrayList<ResearchEnroll> temp = exportData;
        HSSFSheet sheet1 = wb.createSheet("sheet1");
        HSSFRow row1 = sheet1.createRow(0);
        String[] sheetTitle = fields.split(",");
        for (int i = 0 ; i < sheetTitle.length; i++) {
            Cell cell = row1.createCell(i);
            cell.setCellValue(sheetTitle[i]);
            /*if(i == 16)
                cell.setCellStyle(style2);*/
        }

        int num2 = temp.size();
        for(int i = 0;i < num2;++i){
            row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
            ResearchEnroll researchEnroll = temp.get(i);
            row1.createCell(0).setCellValue(researchEnroll.getProjectNo());
            row1.createCell(1).setCellValue(researchEnroll.getProjectSort());
            row1.createCell(2).setCellValue(researchEnroll.getUserId());
            row1.createCell(3).setCellValue(researchEnroll.getUsername());
            row1.createCell(4).setCellValue(researchEnroll.getDeptName());
            row1.createCell(5).setCellValue(researchEnroll.getUserCV());
            row1.createCell(6).setCellValue(researchEnroll.getTitle());
            String teamMember = researchEnroll.getTeamMember();
            ArrayList<String> memberResult = new ArrayList<>();
            String correspondingAuthor = "无";

            if(teamMember == null || teamMember.equals("")){
                for(int m = 0; m < 10; m++){
                    memberResult.add("无");
                }
            }else{
                String[] teamMembers = teamMember.split(";");
                for(int j = 0; j < teamMembers.length; j++){
                    if(teamMembers[j].split(",")[3].equals("通讯作者")){
                        correspondingAuthor = teamMembers[j].split(",")[1];
                    }

                    if(teamMembers[j].equals(",,院系,非通讯作者")){
                        memberResult.add("无");
                        memberResult.add("无");
                    }else{
                        String[] tempMember = teamMembers[j].split(",");
                        for(int m = 0; m < 2; m++){
                            if(tempMember[m].equals("")){
                                memberResult.add("无");
                            }else{
                                memberResult.add(tempMember[m]);
                            }
                        }
                    }
                }
            }

            for(int k = 0; k < memberResult.size(); k++){
                row1.createCell(7 + k).setCellValue(memberResult.get(k));
            }
            row1.createCell(17).setCellValue(correspondingAuthor);
            row1.createCell(18).setCellValue(researchEnroll.getPaperUserid());
            row1.createCell(19).setCellValue(researchEnroll.getPaperUsername());
            row1.createCell(20).setCellValue(researchEnroll.getPaperUserdept());
            row1.createCell(21).setCellValue(researchEnroll.getUserTitle());
            row1.createCell(22).setCellValue(researchEnroll.getUserPhone());
            row1.createCell(23).setCellValue(researchEnroll.getUserMail());
            row1.createCell(24).setCellValue(researchEnroll.getPaperAbstract());
            row1.createCell(25).setCellValue(researchEnroll.getResearchType());

        }

        // 设置文件名
        String title = fileName + ".xls";
        title = new String(title.getBytes(), "ISO8859-1");

        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream os = response.getOutputStream();

        response.setHeader("Content-disposition",
                "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1") + ".xls");
        // 定义输出类型
        response.setContentType("application/msexcel");
        wb.write(os);
        os.close();
    }

    // 导出创新大赛评委评分的一个sheet的xls文件
    public static void createInnoXLSFileWithOneSheet(ArrayList<DownloadInnoAllScore> exportData, String fields, String fileName) throws IOException {
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();

        // 这是字体颜色
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);

        HSSFFont font2 = wb.createFont();
        font2.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);

        ArrayList<DownloadInnoAllScore> temp = exportData;
        HSSFSheet sheet1 = wb.createSheet("sheet1");
        HSSFRow row1 = sheet1.createRow(0);
        String[] sheetTitle = fields.split(",");
        for (int i = 0 ; i < sheetTitle.length; i++) {
            Cell cell = row1.createCell(i);
            cell.setCellValue(sheetTitle[i]);
            if(i == 14)
                cell.setCellStyle(style);
            if(i == 19)
                cell.setCellStyle(style);
            if(i == 20)
                cell.setCellStyle(style2);
            if(i == 26)
                cell.setCellStyle(style2);
            if(i == 27)
                cell.setCellStyle(style2);
        }

        int num2 = temp.size();
        for(int i = 0;i < num2;++i){
            row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
            DownloadInnoAllScore downloadInnoAllScore = temp.get(i);
            row1.createCell(0).setCellValue(downloadInnoAllScore.getProjectNo());
            row1.createCell(1).setCellValue(downloadInnoAllScore.getIsPass());
            row1.createCell(2).setCellValue(downloadInnoAllScore.getProjectApplySort());
            row1.createCell(3).setCellValue(downloadInnoAllScore.getProjectSort());
            row1.createCell(4).setCellValue(downloadInnoAllScore.getUserId());
            row1.createCell(5).setCellValue(downloadInnoAllScore.getUsername());
            row1.createCell(6).setCellValue(downloadInnoAllScore.getDepartment());
            row1.createCell(7).setCellValue(downloadInnoAllScore.getTitle());
            row1.createCell(8).setCellValue(downloadInnoAllScore.getTeamMember());
            row1.createCell(9).setCellValue(downloadInnoAllScore.getJudgeScore1());
            row1.createCell(10).setCellValue(downloadInnoAllScore.getJudgeScore2());
            row1.createCell(11).setCellValue(downloadInnoAllScore.getJudgeScore3());
            row1.createCell(12).setCellValue(downloadInnoAllScore.getJudgeScore4());
            row1.createCell(13).setCellValue(downloadInnoAllScore.getJudgeScore5());
            row1.createCell(14).setCellValue(downloadInnoAllScore.getVideoScore());
            row1.createCell(15).setCellValue(downloadInnoAllScore.getJudgeScore6());
            row1.createCell(16).setCellValue(downloadInnoAllScore.getJudgeScore7());
            row1.createCell(17).setCellValue(downloadInnoAllScore.getJudgeScore8());
            row1.createCell(18).setCellValue(downloadInnoAllScore.getJudgeScore9());
            row1.createCell(19).setCellValue(downloadInnoAllScore.getDocumentScore());
            row1.createCell(20).setCellValue(downloadInnoAllScore.getPreScore());
            row1.createCell(21).setCellValue(downloadInnoAllScore.getJudgeScore10());
            row1.createCell(22).setCellValue(downloadInnoAllScore.getJudgeScore11());
            row1.createCell(23).setCellValue(downloadInnoAllScore.getJudgeScore12());
            row1.createCell(24).setCellValue(downloadInnoAllScore.getJudgeScore13());
            row1.createCell(25).setCellValue(downloadInnoAllScore.getJudgeScore14());
            row1.createCell(26).setCellValue(downloadInnoAllScore.getPptScore());
            row1.createCell(27).setCellValue(downloadInnoAllScore.getScore());
            row1.createCell(28).setCellValue(downloadInnoAllScore.getApplyPreComment());
            row1.createCell(29).setCellValue(downloadInnoAllScore.getAssessment());
            HSSFCell cell2 = sheet1.getRow(i+1).getCell(14);
            HSSFCell cell3 = sheet1.getRow(i+1).getCell(19);
            HSSFCell cell4 = sheet1.getRow(i+1).getCell(20);
            HSSFCell cell5 = sheet1.getRow(i+1).getCell(26);
            HSSFCell cell6 = sheet1.getRow(i+1).getCell(27);
            cell2.setCellStyle(style);
            cell3.setCellStyle(style);
            cell4.setCellStyle(style2);
            cell5.setCellStyle(style2);
            cell6.setCellStyle(style2);
        }

        // 设置文件名
        String title = fileName + ".xls";
        title = new String(title.getBytes(), "ISO8859-1");

        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream os = response.getOutputStream();

        response.setHeader("Content-disposition",
                "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1") + ".xls");
        // 定义输出类型
        response.setContentType("application/msexcel");
        wb.write(os);
        os.close();
    }

    // 导出创新大赛评委评分的一个sheet的xls文件
    public static void createInnoPreXLSFileWithOneSheet(ArrayList<DownloadInnoAllScore> exportData, String fields, String fileName) throws IOException {
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();

        // 这是字体颜色
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);

        HSSFFont font2 = wb.createFont();
        font2.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);

        ArrayList<DownloadInnoAllScore> temp = exportData;
        HSSFSheet sheet1 = wb.createSheet("sheet1");
        HSSFRow row1 = sheet1.createRow(0);
        String[] sheetTitle = fields.split(",");
        for (int i = 0 ; i < sheetTitle.length; i++) {
            Cell cell = row1.createCell(i);
            cell.setCellValue(sheetTitle[i]);
            if(i == 15)
                cell.setCellStyle(style);
            if(i == 20)
                cell.setCellStyle(style);
            if(i == 21)
                cell.setCellStyle(style2);
        }

        int num2 = temp.size();
        for(int i = 0;i < num2;++i){
            row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
            DownloadInnoAllScore downloadInnoAllScore = temp.get(i);
            row1.createCell(0).setCellValue(downloadInnoAllScore.getProjectNo());
            row1.createCell(1).setCellValue(downloadInnoAllScore.getIsPass());
            row1.createCell(2).setCellValue(downloadInnoAllScore.getProjectApplySort());
            row1.createCell(3).setCellValue(downloadInnoAllScore.getCaseSort());
            row1.createCell(4).setCellValue(downloadInnoAllScore.getProjectSort());
            row1.createCell(5).setCellValue(downloadInnoAllScore.getUserId());
            row1.createCell(6).setCellValue(downloadInnoAllScore.getUsername());
            row1.createCell(7).setCellValue(downloadInnoAllScore.getDepartment());
            row1.createCell(8).setCellValue(downloadInnoAllScore.getTitle());
            row1.createCell(9).setCellValue(downloadInnoAllScore.getTeamMember());
            row1.createCell(10).setCellValue(downloadInnoAllScore.getJudgeScore1());
            row1.createCell(11).setCellValue(downloadInnoAllScore.getJudgeScore2());
            row1.createCell(12).setCellValue(downloadInnoAllScore.getJudgeScore3());
            row1.createCell(13).setCellValue(downloadInnoAllScore.getJudgeScore4());
            row1.createCell(14).setCellValue(downloadInnoAllScore.getJudgeScore5());
            row1.createCell(15).setCellValue(downloadInnoAllScore.getVideoScore());
            row1.createCell(16).setCellValue(downloadInnoAllScore.getJudgeScore6());
            row1.createCell(17).setCellValue(downloadInnoAllScore.getJudgeScore7());
            row1.createCell(18).setCellValue(downloadInnoAllScore.getJudgeScore8());
            row1.createCell(19).setCellValue(downloadInnoAllScore.getJudgeScore9());
            row1.createCell(20).setCellValue(downloadInnoAllScore.getDocumentScore());
            row1.createCell(21).setCellValue(downloadInnoAllScore.getPreScore());
            row1.createCell(22).setCellValue(downloadInnoAllScore.getApplyPreComment());
            row1.createCell(23).setCellValue(downloadInnoAllScore.getAssessment());
            HSSFCell cell2 = sheet1.getRow(i+1).getCell(15);
            HSSFCell cell3 = sheet1.getRow(i+1).getCell(20);
            HSSFCell cell4 = sheet1.getRow(i+1).getCell(21);
            cell2.setCellStyle(style);
            cell3.setCellStyle(style);
            cell4.setCellStyle(style2);
        }

        // 设置文件名
        String title = fileName + ".xls";
        title = new String(title.getBytes(), "ISO8859-1");

        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream os = response.getOutputStream();

        response.setHeader("Content-disposition",
                "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1") + ".xls");
        // 定义输出类型
        response.setContentType("application/msexcel");
        wb.write(os);
        os.close();
    }

    // 导出创新大赛评委评分的一个sheet的xls文件
    public static void createInnoPPTXLSFileWithOneSheet(ArrayList<DownloadInnoAllScore> exportData, String fields, String fileName) throws IOException {
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();

        // 这是字体颜色
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);

        HSSFFont font2 = wb.createFont();
        font2.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);

        ArrayList<DownloadInnoAllScore> temp = exportData;
        HSSFSheet sheet1 = wb.createSheet("sheet1");
        HSSFRow row1 = sheet1.createRow(0);
        String[] sheetTitle = fields.split(",");
        for (int i = 0 ; i < sheetTitle.length; i++) {
            Cell cell = row1.createCell(i);
            cell.setCellValue(sheetTitle[i]);
            if(i == 15)
                cell.setCellStyle(style);
        }

        int num2 = temp.size();
        for(int i = 0;i < num2;++i){
            row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
            DownloadInnoAllScore downloadInnoAllScore = temp.get(i);
            row1.createCell(0).setCellValue(downloadInnoAllScore.getProjectNo());
            row1.createCell(1).setCellValue(downloadInnoAllScore.getIsPass());
            row1.createCell(2).setCellValue(downloadInnoAllScore.getProjectApplySort());
            row1.createCell(3).setCellValue(downloadInnoAllScore.getCaseSort());
            row1.createCell(4).setCellValue(downloadInnoAllScore.getProjectSort());
            row1.createCell(5).setCellValue(downloadInnoAllScore.getUserId());
            row1.createCell(6).setCellValue(downloadInnoAllScore.getUsername());
            row1.createCell(7).setCellValue(downloadInnoAllScore.getDepartment());
            row1.createCell(8).setCellValue(downloadInnoAllScore.getTitle());
            row1.createCell(9).setCellValue(downloadInnoAllScore.getTeamMember());
            row1.createCell(10).setCellValue(downloadInnoAllScore.getJudgeScore10());
            row1.createCell(11).setCellValue(downloadInnoAllScore.getJudgeScore11());
            row1.createCell(12).setCellValue(downloadInnoAllScore.getJudgeScore12());
            row1.createCell(13).setCellValue(downloadInnoAllScore.getJudgeScore13());
            row1.createCell(14).setCellValue(downloadInnoAllScore.getJudgeScore14());
            row1.createCell(15).setCellValue(downloadInnoAllScore.getPptScore());
            row1.createCell(16).setCellValue(downloadInnoAllScore.getApplyPreComment());
            row1.createCell(17).setCellValue(downloadInnoAllScore.getAssessment());
            HSSFCell cell2 = sheet1.getRow(i+1).getCell(15);
            cell2.setCellStyle(style);
        }

        // 设置文件名
        String title = fileName + ".xls";
        title = new String(title.getBytes(), "ISO8859-1");

        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream os = response.getOutputStream();

        response.setHeader("Content-disposition",
                "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1") + ".xls");
        // 定义输出类型
        response.setContentType("application/msexcel");
        wb.write(os);
        os.close();
    }


    // 导出老师自己已评和未评的一个sheet的xlsx文件(包含五个阶段)
    public static void createXLSXWithOneSheet(ArrayList<DownloadInnoAllScore> exportData, String researchType, String fields, String fileName) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        HashMap<String,XSSFCellStyle> styleMap = prepareCellStyle(wb);
        String[] styleNames = {"scoredHL","unscoredHL","unlockedScored","unlockedUnscored","lockedScored","lockedUnscored","header"};
        XSSFCellStyle scoredHL = styleMap.get(styleNames[0]);
        XSSFCellStyle unscoredHL = styleMap.get(styleNames[1]);
        XSSFCellStyle unlockedScored = styleMap.get(styleNames[2]);
        XSSFCellStyle unlockedUnscored = styleMap.get(styleNames[3]);
        XSSFCellStyle lockedScored = styleMap.get(styleNames[4]);
        XSSFCellStyle lockedUnscored = styleMap.get(styleNames[5]);
        XSSFCellStyle header = styleMap.get(styleNames[6]);

        ArrayList<DownloadInnoAllScore> temp = exportData;
        XSSFSheet sheet1 = wb.createSheet("sheet1");
        Row row1 = sheet1.createRow(0);
        row1.createCell(0).setCellValue("评分状态");
        row1.getCell(0).setCellStyle(header);

        // 设置表头
        String[] sheetTitle = fields.split(",");
        sheet1.setColumnHidden(sheetTitle.length+1,true);
        ArrayList<Integer> unlockedColumn = getUnlockedColumn(researchType);
        ArrayList<Integer> totalScoreColumn = getTotalScoreColumn(researchType);
        for (int i = 1 ; i < sheetTitle.length+1; i++) {
            Cell cell = row1.createCell(i);
            cell.setCellValue(sheetTitle[i-1]);
            cell.setCellStyle(header);
        }
        for(Integer column:totalScoreColumn){
            row1.getCell(column+1).setCellStyle(unscoredHL);
        }

        // 设置表内容
        int num2 = temp.size();
        for(int j = 0;j < num2; ++j) {
            row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
            DownloadInnoAllScore downloadInnoAllScore = temp.get(j);
            boolean flag = downloadInnoAllScore.isScored();
            if (!flag) {
                row1.createCell(0).setCellValue("尚未评分");
                row1.getCell(0).setCellStyle(unscoredHL);
            } else {
                row1.createCell(0).setCellValue("已评分");
                row1.getCell(0).setCellStyle(scoredHL);
            }
            // 根据类型填充内容
            switch(researchType){
                case "结题报告":{
                    fillFinal(downloadInnoAllScore,row1,j);
                    break;
                }
                case "中期报告":{
                    fillMiddle(downloadInnoAllScore,row1,j);
                    break;
                }
                case "项目申报":{
                    fillApply(downloadInnoAllScore,row1,j);
                    break;
                }
                case "教研论文":{
                    fillPaper(downloadInnoAllScore,row1,j);
                    break;
                }
                case "创新大赛_网评":{
                    fillInnoPre(downloadInnoAllScore,row1,j);
                    break;
                }
                case "创新大赛_现评":{
                    fillInnoPPT(downloadInnoAllScore,row1,j);
                    break;
                }
                default:{
                    System.out.println(researchType);
                    System.out.println("什么也没匹配到");
                }
            }

            // 设置样式
            for(int i = 1; i < sheetTitle.length+1; i++){
                if(!flag){
                    row1.getCell(i).setCellStyle(lockedUnscored);
                }else{
                    row1.getCell(i).setCellStyle(lockedScored);
                }
            }
            for(Integer column:unlockedColumn){
                if(!flag){
                    row1.getCell(column+1).setCellStyle(unlockedUnscored);
                }else{
                    row1.getCell(column+1).setCellStyle(unlockedScored);
                }
            }
            for(Integer column:totalScoreColumn){
                if(!flag){
                    row1.getCell(column+1).setCellStyle(unscoredHL);
                }else{
                    row1.getCell(column+1).setCellStyle(scoredHL);
                }
            }
        }


        for (int i = 0 ; i < sheetTitle.length+1; i++) {
            sheet1.autoSizeColumn(i, true);
            sheet1.setColumnWidth(i,sheet1.getColumnWidth(i)*3/2);
        }

        // 设置文件名
        String title = new String(fileName.getBytes("GB2312"), "ISO8859_1") + ".xlsx";

        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream os = response.getOutputStream();

        response.setHeader("Content-disposition",
                "attachment; filename=" + title);
        // 定义输出类型
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        sheet1.protectSheet("123456");
        wb.write(os);
        os.close();
    }


    // 导出创新大赛  教师界面  网评评分的一个sheet的xls文件
    public static void createSelfInnoPreXLSFileWithOneSheet(ArrayList<TeacherInnoAllScore> exportData, String fields, String fileName) throws IOException {
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();

        // 这是字体颜色
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);

        HSSFFont font2 = wb.createFont();
        font2.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);

        ArrayList<TeacherInnoAllScore> temp = exportData;
        HSSFSheet sheet1 = wb.createSheet("sheet1");
        HSSFRow row1 = sheet1.createRow(0);
        String[] sheetTitle = fields.split(",");
        for (int i = 0 ; i < sheetTitle.length; i++) {
            Cell cell = row1.createCell(i);
            cell.setCellValue(sheetTitle[i]);
            if(i == 16)
                cell.setCellStyle(style);
            if(i == 21)
                cell.setCellStyle(style);
            if(i == 22)
                cell.setCellStyle(style2);
        }

        int num2 = temp.size();
        for(int i = 0;i < num2;++i){
            row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
            TeacherInnoAllScore teacherInnoAllScore = temp.get(i);
            row1.createCell(0).setCellValue("评委"+(i+1));
            row1.createCell(1).setCellValue(teacherInnoAllScore.getProjectNo());
            row1.createCell(2).setCellValue(teacherInnoAllScore.getIsPass());
            row1.createCell(3).setCellValue(teacherInnoAllScore.getProjectApplySort());
            row1.createCell(4).setCellValue(teacherInnoAllScore.getCaseSort());
            row1.createCell(5).setCellValue(teacherInnoAllScore.getProjectSort());
            row1.createCell(6).setCellValue(teacherInnoAllScore.getUserId());
            row1.createCell(7).setCellValue(teacherInnoAllScore.getUsername());
            row1.createCell(8).setCellValue(teacherInnoAllScore.getDepartment());
            row1.createCell(9).setCellValue(teacherInnoAllScore.getTitle());
            row1.createCell(10).setCellValue(teacherInnoAllScore.getTeamMember());
            row1.createCell(11).setCellValue(teacherInnoAllScore.getJudgeScore1());
            row1.createCell(12).setCellValue(teacherInnoAllScore.getJudgeScore2());
            row1.createCell(13).setCellValue(teacherInnoAllScore.getJudgeScore3());
            row1.createCell(14).setCellValue(teacherInnoAllScore.getJudgeScore4());
            row1.createCell(15).setCellValue(teacherInnoAllScore.getJudgeScore5());
            row1.createCell(16).setCellValue(teacherInnoAllScore.getVideoScore());
            row1.createCell(17).setCellValue(teacherInnoAllScore.getJudgeScore6());
            row1.createCell(18).setCellValue(teacherInnoAllScore.getJudgeScore7());
            row1.createCell(19).setCellValue(teacherInnoAllScore.getJudgeScore8());
            row1.createCell(20).setCellValue(teacherInnoAllScore.getJudgeScore9());
            row1.createCell(21).setCellValue(teacherInnoAllScore.getDocumentScore());
            row1.createCell(22).setCellValue(teacherInnoAllScore.getPreScore());
            row1.createCell(23).setCellValue(teacherInnoAllScore.getApplyPreComment());
            row1.createCell(24).setCellValue(teacherInnoAllScore.getAssessment());
            HSSFCell cell2 = sheet1.getRow(i+1).getCell(16);
            HSSFCell cell3 = sheet1.getRow(i+1).getCell(21);
            HSSFCell cell4 = sheet1.getRow(i+1).getCell(22);
            cell2.setCellStyle(style);
            cell3.setCellStyle(style);
            cell4.setCellStyle(style2);
        }

        // 设置文件名
        String title = fileName + ".xls";
        title = new String(title.getBytes(), "ISO8859-1");

        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream os = response.getOutputStream();

        response.setHeader("Content-disposition",
                "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1") + ".xls");
        // 定义输出类型
        response.setContentType("application/msexcel");
        wb.write(os);
        os.close();
    }

    // 导出创新大赛  教师界面  现评评分的一个sheet的xls文件
    public static void createSelfInnoPPTXLSFileWithOneSheet(ArrayList<TeacherInnoAllScore> exportData, String fields, String fileName) throws IOException {
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();

        // 这是字体颜色
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);

        HSSFFont font2 = wb.createFont();
        font2.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);

        ArrayList<TeacherInnoAllScore> temp = exportData;
        HSSFSheet sheet1 = wb.createSheet("sheet1");
        HSSFRow row1 = sheet1.createRow(0);
        String[] sheetTitle = fields.split(",");
        for (int i = 0 ; i < sheetTitle.length; i++) {
            Cell cell = row1.createCell(i);
            cell.setCellValue(sheetTitle[i]);
            if(i == 16)
                cell.setCellStyle(style);
        }

        int num2 = temp.size();
        for(int i = 0;i < num2;++i){
            row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
            TeacherInnoAllScore teachetrInnoAllScore = temp.get(i);
            row1.createCell(0).setCellValue("评委"+i+1);
            row1.createCell(1).setCellValue(teachetrInnoAllScore.getProjectNo());
            row1.createCell(2).setCellValue(teachetrInnoAllScore.getIsPass());
            row1.createCell(3).setCellValue(teachetrInnoAllScore.getProjectApplySort());
            row1.createCell(4).setCellValue(teachetrInnoAllScore.getCaseSort());
            row1.createCell(5).setCellValue(teachetrInnoAllScore.getProjectSort());
            row1.createCell(6).setCellValue(teachetrInnoAllScore.getUserId());
            row1.createCell(7).setCellValue(teachetrInnoAllScore.getUsername());
            row1.createCell(8).setCellValue(teachetrInnoAllScore.getDepartment());
            row1.createCell(9).setCellValue(teachetrInnoAllScore.getTitle());
            row1.createCell(10).setCellValue(teachetrInnoAllScore.getTeamMember());
            row1.createCell(11).setCellValue(teachetrInnoAllScore.getJudgeScore10());
            row1.createCell(12).setCellValue(teachetrInnoAllScore.getJudgeScore11());
            row1.createCell(13).setCellValue(teachetrInnoAllScore.getJudgeScore12());
            row1.createCell(14).setCellValue(teachetrInnoAllScore.getJudgeScore13());
            row1.createCell(15).setCellValue(teachetrInnoAllScore.getJudgeScore14());
            row1.createCell(16).setCellValue(teachetrInnoAllScore.getPptScore());
            row1.createCell(17).setCellValue(teachetrInnoAllScore.getApplyPreComment());
            row1.createCell(18).setCellValue(teachetrInnoAllScore.getAssessment());
            HSSFCell cell2 = sheet1.getRow(i+1).getCell(16);
            cell2.setCellStyle(style);
        }

        // 设置文件名
        String title = fileName + ".xls";
        title = new String(title.getBytes(), "ISO8859-1");

        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream os = response.getOutputStream();

        response.setHeader("Content-disposition",
                "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1") + ".xls");
        // 定义输出类型
        response.setContentType("application/msexcel");
        wb.write(os);
        os.close();
    }

    // 导出教研论文  教师界面  评分的一个sheet的xls文件
    public static void createSelfPaperXLSFileWithOneSheet(ArrayList<SelfPaperScore> exportData, String fields, String fileName) throws IOException {
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();

        // 这是字体颜色
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);

        HSSFFont font2 = wb.createFont();
        font2.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);

        ArrayList<SelfPaperScore> temp = exportData;
        HSSFSheet sheet1 = wb.createSheet("sheet1");
        HSSFRow row1 = sheet1.createRow(0);
        String[] sheetTitle = fields.split(",");
        for (int i = 0 ; i < sheetTitle.length; i++) {
            Cell cell = row1.createCell(i);
            cell.setCellValue(sheetTitle[i]);
            if(i == 17)
                cell.setCellStyle(style2);
        }

        int num2 = temp.size();
        for(int i = 0;i < num2;++i){
            row1 = sheet1.createRow(sheet1.getLastRowNum() + 1); //获取当前行下一行
            SelfPaperScore selfPaperScore = temp.get(i);
            row1.createCell(0).setCellValue("评委"+String.valueOf(i+1));
            row1.createCell(1).setCellValue(selfPaperScore.getProjectNo());
            row1.createCell(2).setCellValue(selfPaperScore.getUserId());
            row1.createCell(3).setCellValue(selfPaperScore.getUsername());
            row1.createCell(4).setCellValue(selfPaperScore.getDeptName());
            row1.createCell(5).setCellValue(selfPaperScore.getTeamMember());
            row1.createCell(6).setCellValue(selfPaperScore.getTitle());
            row1.createCell(7).setCellValue(selfPaperScore.getJudgeScore1());
            row1.createCell(8).setCellValue(selfPaperScore.getJudgeScore2());
            row1.createCell(9).setCellValue(selfPaperScore.getJudgeScore3());
            row1.createCell(10).setCellValue(selfPaperScore.getJudgeScore4());
            row1.createCell(11).setCellValue(selfPaperScore.getJudgeScore5());
            row1.createCell(12).setCellValue(selfPaperScore.getJudgeScore6());
            row1.createCell(13).setCellValue(selfPaperScore.getJudgeScore7());
            row1.createCell(14).setCellValue(selfPaperScore.getJudgeScore8());
            row1.createCell(15).setCellValue(selfPaperScore.getJudgeScore9());
            row1.createCell(16).setCellValue(selfPaperScore.getJudgeScore10());
            row1.createCell(17).setCellValue(selfPaperScore.getScore());
            row1.createCell(18).setCellValue(selfPaperScore.getAssessment());

            HSSFCell cell2 = sheet1.getRow(i+1).getCell(17);
            cell2.setCellStyle(style2);
        }

        // 设置文件名
        String title = fileName + ".xls";
        title = new String(title.getBytes(), "ISO8859-1");

        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream os = response.getOutputStream();

        response.setHeader("Content-disposition",
                "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1") + ".xls");
        // 定义输出类型
        response.setContentType("application/msexcel");
        wb.write(os);
        os.close();
    }

    // 工具函数系列
    private static void addStyleWithBorder(HashMap<String, XSSFCellStyle> styles){
        styles.forEach((key,val)->{
            val.setBorderBottom(BorderStyle.THIN);
            val.setBorderLeft(BorderStyle.THIN);
            val.setBorderRight(BorderStyle.THIN);
            val.setBorderTop(BorderStyle.THIN);
        });
    }

    // 初始化单元格样式，未评分的行：锁定的列底色为灰色，总分列字体为红色；已评分的行底色为黄色
    private static HashMap<String, XSSFCellStyle> prepareCellStyle(XSSFWorkbook wb){
        HashMap<String,XSSFCellStyle> res = new HashMap<>();
        IndexedColorMap colorMap = wb.getStylesSource().getIndexedColors();
        XSSFColor shadowGray = new XSSFColor(new java.awt.Color(180,180,180),colorMap);
        XSSFColor yellow = new XSSFColor(new java.awt.Color(255, 255,0, 128),colorMap);
        XSSFColor red = new XSSFColor(new java.awt.Color(255,0,0),colorMap);
        XSSFColor gray = new XSSFColor(new java.awt.Color(200, 200, 200),colorMap);
        XSSFColor green = new XSSFColor(new java.awt.Color(0,255,0),colorMap);
        XSSFFont redFont = wb.createFont();
        redFont.setColor(red);

        XSSFCellStyle scoredHighlight = wb.createCellStyle();
        scoredHighlight.setFont(redFont);
        scoredHighlight.setLocked(true);
        scoredHighlight.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        scoredHighlight.setFillForegroundColor(yellow);
        res.put("scoredHL",scoredHighlight);

        XSSFCellStyle unscoredHighlight = wb.createCellStyle();
        unscoredHighlight.setFont(redFont);
        unscoredHighlight.setLocked(true);
        unscoredHighlight.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        unscoredHighlight.setFillForegroundColor(shadowGray);
        res.put("unscoredHL",unscoredHighlight);

        XSSFCellStyle unlockedScored = wb.createCellStyle();
        unlockedScored.setLocked(false);
//        unlockedScored.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        unlockedScored.setFillForegroundColor(yellow);
        res.put("unlockedScored",unlockedScored);

        XSSFCellStyle unlockedUnscored = wb.createCellStyle();
        unlockedUnscored.setLocked(false);
        res.put("unlockedUnscored",unlockedUnscored);

        XSSFCellStyle lockedScored = wb.createCellStyle();
        lockedScored.setLocked(true);
        lockedScored.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        lockedScored.setFillForegroundColor(yellow);
        res.put("lockedScored",lockedScored);

        XSSFCellStyle lockedUnscored = wb.createCellStyle();
        lockedUnscored.setLocked(true);
        lockedUnscored.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        lockedUnscored.setFillForegroundColor(gray);
        res.put("lockedUnscored",lockedUnscored);

        XSSFCellStyle header = wb.createCellStyle();
        header.setLocked(true);
        header.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        header.setFillForegroundColor(shadowGray);
        res.put("header",header);

        addStyleWithBorder(res);
        return res;
    }

    private static ArrayList<Integer> getUnlockedColumn(String researchType){
        String scoreStage = researchEnrollService.getScoreStage(researchType);
        ArrayList<Integer> res = new ArrayList<>();
        if(scoreStage.equals("0")) return res;  // 此阶段不允许提交评分 只能浏览
        switch(researchType){
            case "项目申报":{
                if(scoreStage.equals("1")||scoreStage.equals("2")){
                    for(int i = 9; i < 14; i++){
                        res.add(i);
                    }
                    res.add(17);
                }else{
                    res.add(15);
                    res.add(17);
                }
                break;
            }
            case "创新大赛_网评":{
                for(int i = 10; i < 15; i++){
                    res.add(i);
                }
                for(int i = 16; i < 20; i++){
                    res.add(i);
                }
                res.add(23);
                break;
            }
            case "创新大赛_现评":{
                for(int i = 10; i < 15; i++){
                    res.add(i);
                }
                res.add(17);
            }
            case "中期报告":{
                if(scoreStage.equals("1")||scoreStage.equals("2")){
                    for(int i = 6; i < 14; i++){
                        res.add(i);
                    }
                    res.add(17);
                }else{
                    res.add(15);
                    res.add(17);
                }
                break;
            }
            case "教研论文":{
                if(scoreStage.equals("1")||scoreStage.equals("2")){
                    for(int i = 6; i < 16; i++){
                        res.add(i);
                    }
                    res.add(17);
                }else{
                    res.add(17);
                }
                break;
            }
            case "结题报告":{
                if(scoreStage.equals("1")||scoreStage.equals("2")){
                    for(int i = 9; i < 15; i++){
                        res.add(i);
                    }
                    res.add(18);
                }else{
                    res.add(16);
                    res.add(18);
                }
                break;
            }
        }
        return res;
    }

    private static ArrayList<Integer> getTotalScoreColumn(String researchType){
        ArrayList<Integer> res = new ArrayList<>();
        switch(researchType){
            case "项目申报":
            {
                res.add(14);
                res.add(16);
                break;
            }
            case "创新大赛_网评":
            {
                res.add(15);
                res.add(20);
                res.add(21);
                break;
            }
            case "创新大赛_现评":{
                res.add(15);
                break;
            }
            case "中期报告":
            {
                res.add(14);
                res.add(16);
                break;
            }
            case "教研论文":
            {
                res.add(16);
                break;
            }
            case "结题报告":
            {
                res.add(15);
                res.add(17);
                break;
            }
        }
        return res;
    }

    private static void fillFinal(DownloadInnoAllScore downloadJudgeScore, Row row1, int index){
        double preScore = 0;
        row1.createCell(1).setCellValue(downloadJudgeScore.getProjectNo());
        row1.createCell(2).setCellValue(downloadJudgeScore.getUserId());
        row1.createCell(3).setCellValue(downloadJudgeScore.getUsername());
        row1.createCell(4).setCellValue(downloadJudgeScore.getDepartment());
        row1.createCell(5).setCellValue(downloadJudgeScore.getTitle());
        row1.createCell(6).setCellValue(downloadJudgeScore.getTeamMember());
        row1.createCell(7).setCellValue(downloadJudgeScore.getJudgeScore1());
        row1.createCell(8).setCellValue(downloadJudgeScore.getJudgeScore2());
        row1.createCell(9).setCellValue(downloadJudgeScore.getJudgeScore3());
        row1.createCell(10).setCellValue(downloadJudgeScore.getJudgeScore4());
        row1.createCell(11).setCellValue(downloadJudgeScore.getJudgeScore5());
        row1.createCell(12).setCellValue(downloadJudgeScore.getJudgeScore6());
        row1.createCell(13).setCellValue(downloadJudgeScore.getJudgeScore7());
        row1.createCell(14).setCellValue(downloadJudgeScore.getJudgeScore8());
        row1.createCell(15).setCellValue(downloadJudgeScore.getJudgeScore9());
        preScore = downloadJudgeScore.getJudgeScore1() + downloadJudgeScore.getJudgeScore2() +
                downloadJudgeScore.getJudgeScore3() + downloadJudgeScore.getJudgeScore4() +
                downloadJudgeScore.getJudgeScore5() + downloadJudgeScore.getJudgeScore6() +
                downloadJudgeScore.getJudgeScore7() + downloadJudgeScore.getJudgeScore8() +
                downloadJudgeScore.getJudgeScore9();
        String headCell = "H"+ (index + 2);
        String tailCell = "P"+ (index + 2);
        String formula = "SUM("+headCell+":"+tailCell+")";
        String formula2 = "Q"+(index+2)+"+R"+(index+2);
        row1.createCell(16).setCellValue(preScore);
        row1.getCell(16).setCellFormula(formula);
        row1.createCell(17).setCellValue(downloadJudgeScore.getJudgeScore10());
        row1.createCell(18).setCellValue(downloadJudgeScore.getScore());
        row1.getCell(18).setCellFormula(formula2);
        row1.createCell(19).setCellValue(downloadJudgeScore.getAssessment());
        row1.createCell(20).setCellValue(downloadJudgeScore.getProjectID()); // 隐藏列，记录数据库里面该项目的id
    }

    private static void fillApply(DownloadInnoAllScore downloadJudgeScore, Row row1, int index){
        double preScore = 0;
        // 项目申报只有9个小分数，忽略score9
        row1.createCell(1).setCellValue(downloadJudgeScore.getProjectNo());
        row1.createCell(2).setCellValue(downloadJudgeScore.getUserId());
        row1.createCell(3).setCellValue(downloadJudgeScore.getUsername());
        row1.createCell(4).setCellValue(downloadJudgeScore.getDepartment());
        row1.createCell(5).setCellValue(downloadJudgeScore.getTitle());
        row1.createCell(6).setCellValue(downloadJudgeScore.getTeamMember());
        row1.createCell(7).setCellValue(downloadJudgeScore.getJudgeScore1());
        row1.createCell(8).setCellValue(downloadJudgeScore.getJudgeScore2());
        row1.createCell(9).setCellValue(downloadJudgeScore.getJudgeScore3());
        row1.createCell(10).setCellValue(downloadJudgeScore.getJudgeScore4());
        row1.createCell(11).setCellValue(downloadJudgeScore.getJudgeScore5());
        row1.createCell(12).setCellValue(downloadJudgeScore.getJudgeScore6());
        row1.createCell(13).setCellValue(downloadJudgeScore.getJudgeScore7());
        row1.createCell(14).setCellValue(downloadJudgeScore.getJudgeScore8());
        preScore = downloadJudgeScore.getJudgeScore1() + downloadJudgeScore.getJudgeScore2() +
                downloadJudgeScore.getJudgeScore3() + downloadJudgeScore.getJudgeScore4() +
                downloadJudgeScore.getJudgeScore5() + downloadJudgeScore.getJudgeScore6() +
                downloadJudgeScore.getJudgeScore7() + downloadJudgeScore.getJudgeScore8();
        String headCell = "H"+ (index + 2);
        String tailCell = "O"+ (index + 2);
        String formula = "SUM("+headCell+":"+tailCell+")";
        String formula2 = "P"+(index+2)+"+Q"+(index+2);
        row1.createCell(15).setCellValue(preScore);
        row1.getCell(15).setCellFormula(formula);
        row1.createCell(16).setCellValue(downloadJudgeScore.getJudgeScore10());
        row1.createCell(17).setCellValue(downloadJudgeScore.getScore());
        row1.getCell(17).setCellFormula(formula2);
        row1.createCell(18).setCellValue(downloadJudgeScore.getAssessment());
        row1.createCell(19).setCellValue(downloadJudgeScore.getProjectID()); // 隐藏列，记录数据库里面该项目的id
    }

    private static void fillMiddle(DownloadInnoAllScore downloadJudgeScore, Row row1, int index){
        double preScore = 0;
        // 中期报告只有9个小分数，忽略score9
        row1.createCell(1).setCellValue(downloadJudgeScore.getProjectNo());
        row1.createCell(2).setCellValue(downloadJudgeScore.getUserId());
        row1.createCell(3).setCellValue(downloadJudgeScore.getUsername());
        row1.createCell(4).setCellValue(downloadJudgeScore.getDepartment());
        row1.createCell(5).setCellValue(downloadJudgeScore.getTitle());
        row1.createCell(6).setCellValue(downloadJudgeScore.getTeamMember());
        row1.createCell(7).setCellValue(downloadJudgeScore.getJudgeScore1());
        row1.createCell(8).setCellValue(downloadJudgeScore.getJudgeScore2());
        row1.createCell(9).setCellValue(downloadJudgeScore.getJudgeScore3());
        row1.createCell(10).setCellValue(downloadJudgeScore.getJudgeScore4());
        row1.createCell(11).setCellValue(downloadJudgeScore.getJudgeScore5());
        row1.createCell(12).setCellValue(downloadJudgeScore.getJudgeScore6());
        row1.createCell(13).setCellValue(downloadJudgeScore.getJudgeScore7());
        row1.createCell(14).setCellValue(downloadJudgeScore.getJudgeScore8());
        preScore = downloadJudgeScore.getJudgeScore1() + downloadJudgeScore.getJudgeScore2() +
                downloadJudgeScore.getJudgeScore3() + downloadJudgeScore.getJudgeScore4() +
                downloadJudgeScore.getJudgeScore5() + downloadJudgeScore.getJudgeScore6() +
                downloadJudgeScore.getJudgeScore7() + downloadJudgeScore.getJudgeScore8();
        String headCell = "H"+ (index + 2);
        String tailCell = "O"+ (index + 2);
        String formula = "SUM("+headCell+":"+tailCell+")";
        String formula2 = "P"+(index+2)+"+Q"+(index+2);
        row1.createCell(15).setCellValue(preScore);
        row1.getCell(15).setCellFormula(formula);
        row1.createCell(16).setCellValue(downloadJudgeScore.getJudgeScore10());
        row1.createCell(17).setCellValue(downloadJudgeScore.getScore());
        row1.getCell(17).setCellFormula(formula2);
        row1.createCell(18).setCellValue(downloadJudgeScore.getAssessment());
        row1.createCell(19).setCellValue(downloadJudgeScore.getProjectID()); // 隐藏列，记录数据库里面该项目的id
    }

    private static void fillPaper(DownloadInnoAllScore downloadJudgeScore, Row row1, int index){
        row1.createCell(1).setCellValue(downloadJudgeScore.getProjectNo());
        row1.createCell(2).setCellValue(downloadJudgeScore.getUserId());
        row1.createCell(3).setCellValue(downloadJudgeScore.getUsername());
        row1.createCell(4).setCellValue(downloadJudgeScore.getDepartment());
        row1.createCell(5).setCellValue(downloadJudgeScore.getTitle());
        row1.createCell(6).setCellValue(downloadJudgeScore.getTeamMember());
        row1.createCell(7).setCellValue(downloadJudgeScore.getJudgeScore1());
        row1.createCell(8).setCellValue(downloadJudgeScore.getJudgeScore2());
        row1.createCell(9).setCellValue(downloadJudgeScore.getJudgeScore3());
        row1.createCell(10).setCellValue(downloadJudgeScore.getJudgeScore4());
        row1.createCell(11).setCellValue(downloadJudgeScore.getJudgeScore5());
        row1.createCell(12).setCellValue(downloadJudgeScore.getJudgeScore6());
        row1.createCell(13).setCellValue(downloadJudgeScore.getJudgeScore7());
        row1.createCell(14).setCellValue(downloadJudgeScore.getJudgeScore8());
        row1.createCell(15).setCellValue(downloadJudgeScore.getJudgeScore9());
        row1.createCell(16).setCellValue(downloadJudgeScore.getJudgeScore10());
        String headCell = "H"+ (index + 2);
        String tailCell = "Q"+ (index + 2);
        String formula = "SUM("+headCell+":"+tailCell+")";
        row1.createCell(17).setCellValue(downloadJudgeScore.getScore());
        row1.getCell(17).setCellFormula(formula);
        row1.createCell(18).setCellValue(downloadJudgeScore.getAssessment());
        row1.createCell(19).setCellValue(downloadJudgeScore.getProjectID()); // 隐藏列，记录数据库里面该项目的id
    }

    private static void fillInnoPre(DownloadInnoAllScore downloadInnoAllScore, Row row1, int index){
        row1.createCell(1).setCellValue(downloadInnoAllScore.getProjectNo());
        row1.createCell(2).setCellValue(downloadInnoAllScore.getIsPass());
        row1.createCell(3).setCellValue(downloadInnoAllScore.getProjectApplySort());
        row1.createCell(4).setCellValue(downloadInnoAllScore.getCaseSort());
        row1.createCell(5).setCellValue(downloadInnoAllScore.getProjectSort());
        row1.createCell(6).setCellValue(downloadInnoAllScore.getUserId());
        row1.createCell(7).setCellValue(downloadInnoAllScore.getUsername());
        row1.createCell(8).setCellValue(downloadInnoAllScore.getDepartment());
        row1.createCell(9).setCellValue(downloadInnoAllScore.getTitle());
        row1.createCell(10).setCellValue(downloadInnoAllScore.getTeamMember());
        row1.createCell(11).setCellValue(downloadInnoAllScore.getJudgeScore1());
        row1.createCell(12).setCellValue(downloadInnoAllScore.getJudgeScore2());
        row1.createCell(13).setCellValue(downloadInnoAllScore.getJudgeScore3());
        row1.createCell(14).setCellValue(downloadInnoAllScore.getJudgeScore4());
        row1.createCell(15).setCellValue(downloadInnoAllScore.getJudgeScore5());
        row1.createCell(16).setCellValue(downloadInnoAllScore.getVideoScore());
        row1.createCell(17).setCellValue(downloadInnoAllScore.getJudgeScore6());
        row1.createCell(18).setCellValue(downloadInnoAllScore.getJudgeScore7());
        row1.createCell(19).setCellValue(downloadInnoAllScore.getJudgeScore8());
        row1.createCell(20).setCellValue(downloadInnoAllScore.getJudgeScore9());
        row1.createCell(21).setCellValue(downloadInnoAllScore.getDocumentScore());
        row1.createCell(22).setCellValue(downloadInnoAllScore.getPreScore());
        row1.createCell(23).setCellValue(downloadInnoAllScore.getApplyPreComment());
        row1.createCell(24).setCellValue(downloadInnoAllScore.getAssessment());
        String headCell = "L"+ (index + 2);
        String tailCell = "P"+ (index + 2);
        String formula = "SUM("+headCell+":"+tailCell+")";
        row1.getCell(16).setCellFormula(formula);
        headCell = "R"+ (index + 2);
        tailCell = "U"+ (index + 2);
        String formula2 = "SUM("+headCell+":"+tailCell+")";
        row1.getCell(21).setCellFormula(formula2);
        String formula3 = "Q"+(index+2)+"+V"+(index+2);
        row1.getCell(22).setCellFormula(formula3);
        row1.createCell(25).setCellValue(downloadInnoAllScore.getProjectID()); // 隐藏列，记录数据库里面该项目的id
    }

    private static void fillInnoPPT(DownloadInnoAllScore downloadInnoAllScore, Row row1, int index){
        row1.createCell(1).setCellValue(downloadInnoAllScore.getProjectNo());
        row1.createCell(2).setCellValue(downloadInnoAllScore.getIsPass());
        row1.createCell(3).setCellValue(downloadInnoAllScore.getProjectApplySort());
        row1.createCell(4).setCellValue(downloadInnoAllScore.getCaseSort());
        row1.createCell(5).setCellValue(downloadInnoAllScore.getProjectSort());
        row1.createCell(6).setCellValue(downloadInnoAllScore.getUserId());
        row1.createCell(7).setCellValue(downloadInnoAllScore.getUsername());
        row1.createCell(8).setCellValue(downloadInnoAllScore.getDepartment());
        row1.createCell(9).setCellValue(downloadInnoAllScore.getTitle());
        row1.createCell(10).setCellValue(downloadInnoAllScore.getTeamMember());
        row1.createCell(11).setCellValue(downloadInnoAllScore.getJudgeScore10());
        row1.createCell(12).setCellValue(downloadInnoAllScore.getJudgeScore11());
        row1.createCell(13).setCellValue(downloadInnoAllScore.getJudgeScore12());
        row1.createCell(14).setCellValue(downloadInnoAllScore.getJudgeScore13());
        row1.createCell(15).setCellValue(downloadInnoAllScore.getJudgeScore14());
        row1.createCell(16).setCellValue(downloadInnoAllScore.getPptScore());
        row1.createCell(17).setCellValue(downloadInnoAllScore.getApplyPreComment());
        row1.createCell(18).setCellValue(downloadInnoAllScore.getAssessment());
        String headCell = "L"+ (index + 2);
        String tailCell = "P"+ (index + 2);
        String formula = "SUM("+headCell+":"+tailCell+")";
        row1.getCell(16).setCellFormula(formula);
        row1.createCell(19).setCellValue(downloadInnoAllScore.getProjectID()); // 隐藏列，记录数据库里面该项目的id
    }

}
