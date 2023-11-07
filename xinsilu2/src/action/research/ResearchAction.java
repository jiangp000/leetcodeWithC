package action.research;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import domain.ResearchEnroll;
import net.sf.json.JSONObject;
import service.ApplyFilesService;
import service.ResearchEnrollService;
import util.UploadUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.text.AbstractDocument;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;
import java.util.logging.Formatter;

public class ResearchAction extends ActionSupport {
    private ResearchEnroll researchEnroll;
    private String userId;
    private String researchType;
    private File[] uploadFile;
    private String[] fileDeleted;
    private String filesNewName;
    private String[] uploadFileFileName;
    private ResearchEnrollService researchEnrollService;
    private String submitStageControl;
    private String projectNo;
    private String projectEthicSort;
    private ArrayList<String> projectEthicSorts;
    private String projectNoSort;
    private ArrayList<String> projectNoSorts;

    private String delaySort;

    private ArrayList<String> delaySorts;
    private ArrayList<String> deptNames;
    private ArrayList<String> stuDeptNames1;
    private ArrayList<String> stuDeptNames2;
    private ArrayList<String> stuDeptNames3;
    private ArrayList<String> stuDeptNames4;
    private ArrayList<String> stuDeptNames5;
    private ArrayList<String> projectApplySorts;
    private ArrayList<String> caseSorts;
    private ArrayList<String> projectSorts;
    private ArrayList<String> paperSorts;
    private ArrayList<String> correspondingAuthorSorts;
    private ArrayList<String> authorSorts1;
    private ArrayList<String> authorSorts2;
    private ArrayList<String> authorSorts3;
    private ArrayList<String> authorSorts4;
    private ArrayList<String> authorSorts5;
    private ArrayList<String> paperUserdepts;
    private String paperUserdept;


    private String deptName;
    private String projectApplySort;
    private String caseSort;
    private String paperAbstract;
    private String projectSort;
    private String submitSave;

    private String u_id1;
    private String u_name1;
    private String u_dept1;
    private String u_author1;
    private String u_id2;
    private String u_name2;
    private String u_dept2;
    private String u_author2;
    private String u_id3;
    private String u_name3;
    private String u_dept3;
    private String u_author3;
    private String u_id4;
    private String u_name4;
    private String u_dept4;
    private String u_author4;
    private String u_id5;
    private String u_name5;
    private String u_dept5;
    private String u_author5;


    private ActionContext context;
    private Map<String, Object> session;
    private static Logger logger = Logger.getLogger("researchAction");

    private static final int INNO_FILE_NUM = 17;

    public ResearchAction() {
        this.context = ActionContext.getContext();
        this.session = context.getSession();
        researchEnrollService = new ResearchEnrollService();

        researchEnroll = new ResearchEnroll();
        // todo 改回来
        this.userId = (String)session.get("teacher");
//        this.userId = "2201210350";

        projectEthicSorts = new ArrayList<>();
        projectNoSorts = new ArrayList<>();
        delaySorts = new ArrayList<>();
        deptNames = new ArrayList<>();
        paperUserdepts = new ArrayList<>();
        stuDeptNames1 = new ArrayList<>();
        stuDeptNames2 = new ArrayList<>();
        stuDeptNames3 = new ArrayList<>();
        stuDeptNames4 = new ArrayList<>();
        stuDeptNames5 = new ArrayList<>();
        projectApplySorts = new ArrayList<>();
        caseSorts = new ArrayList<>();
        projectSorts = new ArrayList<>();
        paperSorts = new ArrayList<>();
        correspondingAuthorSorts = new ArrayList<>();
        authorSorts1 = new ArrayList<>();
        authorSorts2 = new ArrayList<>();
        authorSorts3 = new ArrayList<>();
        authorSorts4 = new ArrayList<>();
        authorSorts5 = new ArrayList<>();


        //getSaveData();
        initializeSaveData();
    }
    public void initializeSaveData(){
        List<String> depts = Arrays.asList("院系","无","数学科学学院","物理学院","化学与分子工程学院","生命科学学院","城市与环境学院","地球与空间科学学院", "心理与认知科学学院","建筑与景观设计学院",
                "信息科学技术学院","电子学院","计算机学院","集成电路学院","智能学院","工学院","王选计算机研究所","软件与微电子学院", "环境科学与工程学院","软件工程国家工程研究中心","材料科学与工程学院","未来技术学院",
                "图书馆",
                "中国语言文学系","历史学系","考古文博学院","哲学系（宗教学系）", "外国语学院","艺术学院","对外汉语教育学院","歌剧研究院",
                "国际关系学院","法学院","信息管理系","社会学系", "政府管理学院","马克思主义学院","教育学院","新闻与传播学院","体育教研部",
                "经济学院","光华管理学院","人口研究所","国家发展研究院",
                "元培学院","北京国际数学研究中心","前沿交叉学科研究院","科维理天文与天体物理研究所","核科学与技术研究院","燕京学堂","现代农学院","人工智能研究院",
                "继续教育学院","首都发展研究院","研究生院","继续教育部","人事部","国际合作部","教务部","网络安全和信息化委员会办公室","计算中心","教师教学发展中心","工会","团委",
                "基础医学院","药学院","公共卫生学院","护理学院","医学人文学院","医学继续教育学院","第一医院","人民医院","第三医院","口腔医院","北京肿瘤医院","第六医院",
                "深圳研究生院","信息工程学院","化学生物学与生物技术学院","环境与能源学院","城市规划与设计学院","新材料学院","汇丰商学院","国际法学院","人文社会科学学院");
        List<String> projectGroup = Arrays.asList("文社组","理工组","医学组","综合组");
        List<String> caseTypes = Arrays.asList("课程案例","综合案例");
        List<String> projectTypes = Arrays.asList("重点","优先","一般","其他", "无");
        List<String> paperTypes = Arrays.asList("教育信息化创新发展研究","融合/智能教学系统创新构建",
                "成果导向网课建设与应用","线上线下教学实质等效研究","信息技术与课堂智慧教学","教育大数据分析与教学指导",
                "在线混合课程的创新实践","教师数字素养与能力评价等","学习科学与教学反思改进","数字化转型与未来教育发展");
        List<String> correspondingAuthorTypes = Arrays.asList("非通讯作者", "通讯作者");
        List<String> projectEthicSortTypes = Arrays.asList("否","是");
        List<String> projectNoSortTypes = Arrays.asList("否","是");
        List<String> finalDelaySortTypes = Arrays.asList("否","是");
        projectEthicSorts.addAll(projectEthicSortTypes);
        projectNoSorts.addAll(projectNoSortTypes);
        delaySorts.addAll(finalDelaySortTypes);
        deptNames.addAll(depts);
        paperUserdepts.addAll(depts);
        stuDeptNames1.addAll(depts);
        stuDeptNames2.addAll(depts);
        stuDeptNames3.addAll(depts);
        stuDeptNames4.addAll(depts);
        stuDeptNames5.addAll(depts);
        projectApplySorts.addAll(projectTypes);
        caseSorts.addAll(caseTypes);
        projectSorts.addAll(projectGroup);
        paperSorts.addAll(paperTypes);
        correspondingAuthorSorts.addAll(correspondingAuthorTypes);
        authorSorts1.addAll(correspondingAuthorTypes);
        authorSorts2.addAll(correspondingAuthorTypes);
        authorSorts3.addAll(correspondingAuthorTypes);
        authorSorts4.addAll(correspondingAuthorTypes);
        authorSorts5.addAll(correspondingAuthorTypes);
        submitSave = "0";
        submitStageControl = "0";

    }
    //从teamMember中获得每个团队成员信息
    public void assignmentTeamMember(String teamMemberTemp){
        if(!teamMemberTemp.equals(",,;,,;,,;,,") && !teamMemberTemp.equals(",,院系;,,院系;,,院系;,,院系")
                && teamMemberTemp.split(";").length >= 4){
            for(int i = 0; i < 4; i++){
                if(!teamMemberTemp.split(";")[i] .equals(",,")){
                    String[] members = teamMemberTemp.split(";")[i].split(",");
                    if(members.length == 2){
                        if(i == 0){
                            u_id1 = members[0];
                            u_name1 = members[1];
                        }else if(i == 1){
                            u_id2 = members[0];
                            u_name2 = members[1];
                        }else if(i == 2){
                            u_id3 = members[0];
                            u_name3 = members[1];
                        }else if(i == 3){
                            u_id4 = members[0];
                            u_name4 = members[1];
                        }
                    }else if(members.length == 3){
                        if(i == 0){
                            u_id1 = members[0];
                            u_name1 = members[1];
                            u_dept1 = members[2];
                            stuDeptNames1.remove("院系");
                            stuDeptNames1.remove(u_dept1);
                            stuDeptNames1.add(0,u_dept1);
                        }else if(i == 1){
                            u_id2 = members[0];
                            u_name2 = members[1];
                            u_dept2 = members[2];
                            stuDeptNames2.remove("院系");
                            stuDeptNames2.remove(u_dept2);
                            stuDeptNames2.add(0,u_dept2);
                        }else if(i == 2){
                            u_id3 = members[0];
                            u_name3 = members[1];
                            u_dept3 = members[2];
                            stuDeptNames3.remove("院系");
                            stuDeptNames3.remove(u_dept3);
                            stuDeptNames3.add(0,u_dept3);
                        }else if(i == 3){
                            u_id4 = members[0];
                            u_name4 = members[1];
                            u_dept4 = members[2];
                            stuDeptNames4.remove("院系");
                            stuDeptNames4.remove(u_dept4);
                            stuDeptNames4.add(0,u_dept4);
                        }
                    }
                }
            }
        }
    }


    // 查询数据库获取当前用户第index个文件的路径
    public static String getFilePathByIndex(int index, String fileType){
        if(fileType.equals("项目申报")&&(index<0||index>=10)) return "";
        if(fileType.equals("创新大赛")&&(index<0||index>=INNO_FILE_NUM)) return "";

        ActionContext context1 = ActionContext.getContext();
        // todo 改回来
         String UID = (String) context1.getSession().get("teacher");
//        String UID = "2201210350";
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        //项目申报提交时间为前一年12月和今年1月
        if(currentMonth == 11){
            year = year + 1;
        }
        ApplyFilesService applyService = new ApplyFilesService();
        System.out.println(UID);
        ArrayList<String> res = applyService.getFilePaths(UID,fileType,year);
        if(res.size()==0) return "";
        String fileRootPath = res.get(1);
        String targetFileName = res.get(index+2);
        String truePath = UploadUtil.fetchFileByNamePath(fileRootPath,targetFileName);
        return truePath;
    }

    public String getApplySaveData(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        //项目申报提交时间为前一年12月和今年1月
        if(currentMonth == 11){
            year = year + 1;
        }
        researchType = "项目申报";
        submitStageControl = researchEnrollService.getSubmitStage(String.valueOf(year), researchType);

        if(researchEnroll.getUserId() != null){
            userId = researchEnroll.getUserId();
        }
        researchEnroll = researchEnrollService.getResearchEnrollById(userId,researchType);
        if(researchEnroll.getUserId() != null){
            if(researchEnroll.getIsPass() == null || researchEnroll.getIsPass().equals("")){
                researchEnroll.setIsPass("尚未初审");
            }
            String deptNameTemp = researchEnroll.getDeptName();
            String projectApplySortTemp = researchEnroll.getProjectApplySort();
            String caseSortTemp = researchEnroll.getCaseSort();
            String projectSortTemp = researchEnroll.getProjectSort();
            String teamMemberTemp = researchEnroll.getTeamMember();
            String projectEthicTemp = "否";
            if(researchEnroll.getProjectEthicSort() != null &&
                    !researchEnroll.getProjectEthicSort().equals("")){
                projectEthicTemp = researchEnroll.getProjectEthicSort();
            }
            submitSave = researchEnroll.getSubmitSave();

            assignmentTeamMember(teamMemberTemp);

            delaySort = researchEnroll.getDelaySort();
            deptNames.remove("院系");
            deptNames.remove(deptNameTemp);
            deptNames.add(0,deptNameTemp);
            projectApplySorts.remove(projectApplySortTemp);
            projectApplySorts.add(0,projectApplySortTemp);
            caseSorts.remove(caseSortTemp);
            caseSorts.add(0,caseSortTemp);
            projectSorts.remove(projectSortTemp);
            projectSorts.add(0,projectSortTemp);

            caseSort = researchEnroll.getCaseSort();
            // projectSort = researchEnroll.getProjectSort();
            logger.info("projectSort: " + projectSort);
            projectEthicSorts.remove(projectEthicTemp);
            projectEthicSorts.add(0, projectEthicTemp);
        }

        return SUCCESS;
    }
    public String getInnoSaveData(){

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        researchType = "创新大赛";
        submitStageControl = researchEnrollService.getSubmitStage(String.valueOf(year), researchType);
        // todo delete
//        userId = "2201210350";
        if(researchEnroll.getUserId() != null){
            userId = researchEnroll.getUserId();
        }
        researchEnroll = researchEnrollService.getResearchEnrollById(userId,researchType);
        if(researchEnroll.getUserId() != null){
            if(researchEnroll.getIsPass() == null || researchEnroll.getIsPass().equals("")){
                researchEnroll.setIsPass("尚未初审");
            }
            String deptNameTemp = researchEnroll.getDeptName();
            String projectApplySortTemp = researchEnroll.getProjectApplySort();
            String caseSortTemp = researchEnroll.getCaseSort();
            String projectSortTemp = researchEnroll.getProjectSort();
            String teamMemberTemp = researchEnroll.getTeamMember();
            submitSave = researchEnroll.getSubmitSave();
            if (researchEnroll.getProjectTitle() == null || researchEnroll.getProjectTitle().equals(""))
                researchEnroll.setProjectTitle("无");
            if (researchEnroll.getProjectNo() == null || researchEnroll.getProjectNo().equals(""))
                researchEnroll.setProjectNo("无");
            assignmentTeamMember(teamMemberTemp);

            delaySort = researchEnroll.getDelaySort();
            logger.info("init delaySort: " + delaySort);
            deptNames.remove("院系");
            deptNames.remove(deptNameTemp);
            deptNames.add(0,deptNameTemp);
            projectApplySorts.remove(projectApplySortTemp);
            projectApplySorts.add(0,projectApplySortTemp);
            caseSorts.remove(caseSortTemp);
            caseSorts.add(0,caseSortTemp);
            projectSorts.remove(projectSortTemp);
            projectSorts.add(0,projectSortTemp);
        }

        return SUCCESS;
    }
    public String getMidSaveData(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        //userId = "2101210152";
        researchType = "中期报告";
        submitStageControl = researchEnrollService.getSubmitStage(String.valueOf(year), researchType);

        if(researchEnroll.getUserId() != null){
            userId = researchEnroll.getUserId();
        }
        researchEnroll = researchEnrollService.getResearchEnrollById(userId,researchType);
        if(researchEnroll.getUserId() != null){
            if(researchEnroll.getIsPass() == null || researchEnroll.getIsPass().equals("")){
                researchEnroll.setIsPass("尚未初审");
            }
            String deptNameTemp = researchEnroll.getDeptName();
            String projectApplySortTemp = researchEnroll.getProjectApplySort();
            String caseSortTemp = researchEnroll.getCaseSort();
            String projectSortTemp = researchEnroll.getProjectSort();
            String teamMemberTemp = researchEnroll.getTeamMember();
            submitSave = researchEnroll.getSubmitSave();

            assignmentTeamMember(teamMemberTemp);

            delaySort = researchEnroll.getDelaySort();
            deptNames.remove("院系");
            deptNames.remove(deptNameTemp);
            deptNames.add(0,deptNameTemp);
            projectApplySorts.remove(projectApplySortTemp);
            projectApplySorts.add(0,projectApplySortTemp);
            caseSorts.remove(caseSortTemp);
            caseSorts.add(0,caseSortTemp);
            projectSorts.remove(projectSortTemp);
            projectSorts.add(0,projectSortTemp);
        }
        return SUCCESS;
    }
    public String getPaperSaveData(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        researchType = "教研论文";
        submitStageControl = researchEnrollService.getSubmitStage(String.valueOf(year), researchType);

        if(researchEnroll.getUserId() != null){
            userId = researchEnroll.getUserId();
        }
//        userId = "2201210350";
        researchEnroll = researchEnrollService.getResearchEnrollById(userId,researchType);
        if(researchEnroll.getUserId() != null){
            if(researchEnroll.getIsPass() == null || researchEnroll.getIsPass().equals("")){
                researchEnroll.setIsPass("尚未初审");
            }

            delaySort = researchEnroll.getDelaySort();
            String paperUserDeptTemp = researchEnroll.getPaperUserdept();
            String deptNameTemp = researchEnroll.getDeptName();
            String paperSortTemp = researchEnroll.getProjectSort();
            String correspondingAuthorTemp = researchEnroll.getCaseSort();
            String teamMemberTemp = researchEnroll.getTeamMember();
            String projectNoTemp = researchEnroll.getProjectNo();
            submitSave = researchEnroll.getSubmitSave();

            if(!teamMemberTemp.equals(",,;,,;,,;,,")
                    && !teamMemberTemp.equals(",,院系;,,院系;,,院系;,,院系")
                    && teamMemberTemp.split(";").length >= 5
                    && !teamMemberTemp.equals(",,院系,非通讯作者;,,院系,非通讯作者;,,院系,非通讯作者;,,院系,非通讯作者")){
                for(int i = 0; i < 5; i++){
                    if(!teamMemberTemp.split(";")[i] .equals(",,院系,")
                            && !teamMemberTemp.split(";")[i] .equals(",,院系,非通讯作者")){
                        String[] members = teamMemberTemp.split(";")[i].split(",");
                        if(i == 0){
                            u_id1 = members[0];
                            u_name1 = members[1];
                            u_dept1 = members[2];
                            stuDeptNames1.remove("院系");
                            stuDeptNames1.remove(u_dept1);
                            stuDeptNames1.add(0,u_dept1);
                            u_author1 = members[3];
                            authorSorts1.remove(u_author1);
                            authorSorts1.add(0, u_author1);
                        }else if(i == 1){
                            u_id2 = members[0];
                            u_name2 = members[1];
                            u_dept2 = members[2];
                            stuDeptNames2.remove("院系");
                            stuDeptNames2.remove(u_dept2);
                            stuDeptNames2.add(0,u_dept2);
                            u_author2 = members[3];
                            authorSorts2.remove(u_author2);
                            authorSorts2.add(0, u_author2);
                        }else if(i == 2){
                            u_id3 = members[0];
                            u_name3 = members[1];
                            u_dept3 = members[2];
                            stuDeptNames3.remove("院系");
                            stuDeptNames3.remove(u_dept3);
                            stuDeptNames3.add(0,u_dept3);
                            u_author3 = members[3];
                            authorSorts3.remove(u_author3);
                            authorSorts3.add(0, u_author3);
                        }else if(i == 3){
                            u_id4 = members[0];
                            u_name4 = members[1];
                            u_dept4 = members[2];
                            stuDeptNames4.remove("院系");
                            stuDeptNames4.remove(u_dept4);
                            stuDeptNames4.add(0,u_dept4);
                            u_author4 = members[3];
                            authorSorts4.remove(u_author4);
                            authorSorts4.add(0, u_author4);
                        }else if(i == 4){
                            u_id5 = members[0];
                            u_name5 = members[1];
                            u_dept5 = members[2];
                            stuDeptNames5.remove("院系");
                            stuDeptNames5.remove(u_dept5);
                            stuDeptNames5.add(0,u_dept5);
                            u_author5 = members[3];
                            authorSorts5.remove(u_author5);
                            authorSorts5.add(0, u_author5);
                        }
                    }
                }
            }

            deptNames.remove("院系");
            deptNames.remove(deptNameTemp);
            deptNames.add(0,deptNameTemp);

            paperUserdepts.remove("院系");
            paperUserdepts.remove(paperUserDeptTemp);
            paperUserdepts.add(0, paperUserDeptTemp);

            paperSorts.remove(paperSortTemp);
            paperSorts.add(0,paperSortTemp);
            correspondingAuthorSorts.remove(correspondingAuthorTemp);
            correspondingAuthorSorts.add(0, correspondingAuthorTemp);

            if (projectNoTemp == "无"){
                projectNoTemp = "否";
            } else {
                projectNoTemp = "是";
            }
            projectNoSort = projectNoTemp;
//            projectNoSorts.remove(projectNoTemp);
//            projectNoSorts.add(0, projectNoTemp);

        }

        return SUCCESS;
    }
    public String getFinalSaveData(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        researchType = "结题报告";
        submitStageControl = researchEnrollService.getSubmitStage(String.valueOf(year), researchType);

        //userId = "2101210152";
        if(researchEnroll.getUserId() != null){
            userId = researchEnroll.getUserId();
        }
        researchEnroll = researchEnrollService.getResearchEnrollById(userId,researchType);
        if(researchEnroll.getUserId() != null){
            if(researchEnroll.getIsPass() == null || researchEnroll.getIsPass().equals("")){
                researchEnroll.setIsPass("尚未初审");
            }

            String deptNameTemp = researchEnroll.getDeptName();
            String projectSortTemp = researchEnroll.getProjectSort();
            String teamMemberTemp = researchEnroll.getTeamMember();
            submitSave = researchEnroll.getSubmitSave();

            assignmentTeamMember(teamMemberTemp);

            delaySort = researchEnroll.getDelaySort();
            deptNames.remove("院系");
            deptNames.remove(deptNameTemp);
            deptNames.add(0,deptNameTemp);
            projectSorts.remove(projectSortTemp);
            projectSorts.add(0,projectSortTemp);
        }
        return SUCCESS;
    }


    public String submitApp() {
        return submitResearchApply("项目申报");
    }

    public String submitInno() {
        return submitResearchInno("创新大赛");
    }

    public String submitMid() {
        return submitresearch("中期报告");
    }

    public String submitPap() {
        return submitresearch("教研论文");
    }

    public String submitFin() {
        if(submitStageControl.equals("1")){
            return submitresearch("结题报告");
        }else if(submitStageControl.equals("2")){
            return submitFinpre("结题报告");
        }else{
            return submitresearch("结题报告");
        }
    }


    // 项目申报阶段文件的format名称
    private static String[] applyFileNameFormat = {"项目申报书_%s_%s","项目申报书_%s_%s","申报支撑1_%s_%s","申报支撑2_%s_%s","申报支撑3_%s_%s","申报支撑4_%s_%s","申报现场汇报_%s_%s","申报现场汇报_%s_%s","伦理与科研诚信证书_%s_%s","伦理审核证明_%s_%s"};

    // 创新大赛阶段文件的format名称
    private static String[] innoFileNameFormat = {"%s_%s_申报书","%s_%s_申报书","%s_%s_创新报告","%s_%s_创新成果支撑材料目录","%s_%s_案例视频1","%s_%s_案例视频2","%s_%s_视频信息表","%s_%s_案例设计","%s_%s_案例课件","%s_%s_案例课件","%s_%s_教学大纲","%s_%s_相关材料1","%s_%s_相关材料2","%s_%s_相关材料3","%s_%s_现场汇报","%s_%s_现场汇报", "%s_%s_延期申请"};
    
    private String submitresearch(String researchType) {

        if (researchType.equals("")) {
            researchType = "报告";
        }
        if(researchType.equals("教研论文")){
            if(paperUserdept.equals("院系")){
                paperUserdept = "";
            }
            researchEnroll.setPaperUserdept(paperUserdept);
        }else{
            researchEnroll.setPaperUserdept("");
        }
        if (researchEnroll != null && uploadFile != null) {
            String dirName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername();
            //检查该用户之前是否已提交过，若提交过删除之前提交文件
            if(researchEnroll.getUserId()!="" && researchEnroll.getUsername()!=""){
                UploadUtil.deleteOldFile(dirName, researchType);
            }
            String filePath = "";
            for (int i = 0; i < uploadFile.length; i++){
                String suffix = uploadFileFileName[i].substring(uploadFileFileName[i].lastIndexOf(".")); //文件后缀
//                String fileName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername() + "-" +
//                        reportName + suffix; //文件名
                String fileName = uploadFileFileName[i]; //文件名
                // 上传
//            String filePath = UploadUtil.uploadResearchEnrollFile(fileName, uploadFile);
                filePath += UploadUtil.uploadResearchFile(fileName, dirName, uploadFile[i], researchType) + ";";

                /*researchEnroll.setFilePath(filePath);
                if (researchEnroll.getTitle() == "") {
                    researchEnroll.setTitle(reportName);
                }
                researchEnroll.setResearchType(reportName);
                researchEnrollService.insertResearchEnroll(researchEnroll);*/
            }
            Calendar date = Calendar.getInstance();
            String year = String.valueOf(date.get(Calendar.YEAR));
            String zipFilePathName = UploadUtil.compressFile(researchEnroll.getUserId(),
                    researchEnroll.getUsername(),researchEnroll.getTitle(),researchType);
            researchEnroll.setFilePath(filePath);
            researchEnroll.setOutputZipFilePath(zipFilePathName);
            if (researchEnroll.getTitle() == "") {
                researchEnroll.setTitle(researchType);
            }

            researchEnroll.setDeptName(deptName);
            researchEnroll.setCaseSort(caseSort);
            researchEnroll.setProjectSort(projectSort);
            researchEnroll.setDelaySort(delaySort);
            logger.info("delaySort: " + delaySort);
            researchEnroll.setProjectApplySort(projectApplySort);
            researchEnroll.setResearchType(researchType);
            if(researchType.equals("教研论文") && projectNoSort.equals("否")){
                researchEnroll.setProjectNo("否");
            }

            /*if(researchEnroll.getResearchType().equals("创新大赛")){
                if(researchEnroll.getProjectApplySort().equals("")){
                    researchEnroll.setProjectApplySort("无");
                }
            }else{
                if(researchEnroll.getProjectNo().equals("")){
                    researchEnroll.setProjectNo("无");
                }
            }*/
            researchEnrollService.insertResearchEnrollData(researchEnroll);

            return SUCCESS;
        }
        return INPUT;
    }



    //项目申请，最初开始阶段没有项目编号
    private String submitResearchApply(String reportName) {
        researchType = "项目申报";
        String userID = researchEnroll.getUserId();
        String userName = researchEnroll.getUsername();
        int submitId = researchEnrollService.getResearchEnrollIdById(userID,researchType);
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        submitStageControl = researchEnrollService.getSubmitStage(String.valueOf(year),researchType);
        if (reportName.equals("")) {
            reportName = "报告";
        }
        String reportNameEng = "researchEnroll";

        if (researchEnroll.getTitle().equals("")) {
            researchEnroll.setTitle(reportName);
        }
            /*if(researchEnroll.getProjectNo().equals("")){
                researchEnroll.setProjectNo("无");
            }*/
        researchEnroll.setResearchType(reportName);
        researchEnroll.setDeptName(deptName);
        researchEnroll.setProjectApplySort(projectApplySort);
        researchEnroll.setCaseSort(caseSort);
        researchEnroll.setProjectEthicSort(projectEthicSort);
        if (researchEnroll != null) {
            int fileLength = 0;
            if(uploadFile!=null) fileLength = uploadFile.length;
            // 构造原始文件名的顺序字典
            HashMap<String,Integer> filesOrder = new HashMap<>();
            JSONObject obj = JSONObject.fromObject(filesNewName);
            Set set = obj.keySet();
            for(Object s:set){
                Object val = obj.get(s);
                JSONObject innerObj = JSONObject.fromObject(val);
                Integer fileOrder = Integer.valueOf((String) s);
                String fileName = (String) innerObj.get("name");
                filesOrder.put(fileName,fileOrder);
            }
            // 将上传的文件规范命名后按照顺序写入数据库中
            // 姓名_院系_xxx
            String[] fileNameList = new String[10];
            for(int i = 0; i < 10; i++){
                fileNameList[i] = "";
            }
            for(int i = 0; i < fileLength; i++) {
                String fileName = uploadFileFileName[i];
                Integer fileOrder = filesOrder.get(fileName);
                if(fileOrder!=null){
                    String formatStr = applyFileNameFormat[fileOrder];
                    String suffix = fileName.substring(fileName.lastIndexOf("."));
                    String newName = String.format(formatStr,userID,userName)+suffix;
                    fileNameList[fileOrder] = newName;
                }
            }
            // 记录哪些文件是被删除的，当且仅当该项被标记删除且没有新上传的文件时为true
            ArrayList<Boolean> fileStatus = new ArrayList<>();
            for(int i = 2; i < 10; i++){
                if(Objects.equals(fileDeleted[i], "yes") && Objects.equals(fileNameList[i], "")){
                    fileStatus.add(true);
                }else{
                    fileStatus.add(false);
                }
            }
            for(int i = 0; i < 2; i++){
                if(Objects.equals(fileDeleted[i], "yes") && Objects.equals(fileNameList[i], "")){
                    fileStatus.add(true);
                }else{
                    fileStatus.add(false);
                }
            }

            // int UID = Integer.parseInt(userID);
            String UID = userID;
            String dirName = userID + "-" + userName;

            ApplyFilesService applyService = new ApplyFilesService();
            ArrayList<String> res = applyService.getFilePaths(UID,reportName,year);
            HashSet<String> reserveNames = new HashSet<>();
            // 当前用户当年在当前项目没有上传文件记录，使用insert，否则使用update更新文件
            if(res.size()==0){
//                System.out.println("no input files");
                String fileRootPath = reportNameEng+"/"+ year +"/"+reportName+"/"+dirName+"/";
                applyService.insertFilePaths(UID,reportName,year,fileRootPath,fileNameList);
            }else{
                // int dbID = Integer.parseInt(res.get(0));
                String dbID = res.get(0);
                for(int i = 0; i < 10; i++){
                    if(fileNameList[i].length()!=0) continue;
                    String savedFileName = res.get(i+2);
                    if(savedFileName!=null&&savedFileName.length()!=0&&!fileStatus.get(i)){
                        fileNameList[i] = savedFileName;
                        reserveNames.add(savedFileName);
                    }
                }
//                System.out.println(reserveNames);
                applyService.updateFilePaths(dbID,fileNameList);
            }


            //检查该用户之前是否已提交过，若提交过删除之前提交文件
            if(userID.length()!=0 && userName.length()!=0){
                UploadUtil.deleteOldFileWithReserve(dirName, reportName, reserveNames);
            }
            String filePath = "";
            String projectEthicPath = "";
            if(projectEthicSort!=null&&projectEthicSort.equals("否")){
                for (int i = 0; i < fileLength; i++){
                    String fileName = fileNameList[filesOrder.get(uploadFileFileName[i])];
                    // 上传
                    filePath += UploadUtil.uploadResearchFile(fileName, dirName, uploadFile[i], reportName) + ";";
                }
            }else{
                if(fileLength!=0){
                    for (int i = 2; i < fileLength; i++){
                        String fileName = fileNameList[filesOrder.get(uploadFileFileName[i])];
                        filePath += UploadUtil.uploadResearchFile(fileName, dirName, uploadFile[i], reportName) + ";";
                    }
                    for (int i = 0; i < 2; i++){
                        String fileName = fileNameList[filesOrder.get(uploadFileFileName[i])];
                        projectEthicPath += UploadUtil.uploadResearchFile(fileName, dirName, uploadFile[i], reportName) + ";";
                    }
                }
            }
            String zipFilePathName = UploadUtil.compressFile(userID,userName,researchEnroll.getTitle(),reportName);
            researchEnroll.setFilePath(filePath);
            researchEnroll.setOutputZipFilePath(zipFilePathName);
            researchEnroll.setProjectEthicPath(projectEthicPath);
            if(submitId != 0){
                researchEnroll.setId(submitId);
                if(submitStageControl.equals("2")){
                    researchEnrollService.updateSubmitSave(submitId,submitSave);
                }else{
                    researchEnrollService.updateResearchEnrollData(researchEnroll);
                }
            }else {
                researchEnrollService.insertResearchEnrollData(researchEnroll);
            }

            return SUCCESS;
        }
        return INPUT;
    }

    //提交项目申报现场汇报PPT和PDF
    private String submitAppPre(String reportName) {
        researchType = "项目申报";
        if (reportName.equals("")) {
            reportName = "报告";
        }
        userId = researchEnroll.getUserId();
        //System.out.println(researchEnroll.getUserId()+"***" + researchEnroll.getUsername());
        researchEnroll = researchEnrollService.getResearchEnrollById(userId,researchType);
        if (researchEnroll != null && uploadFile != null) {
            String dirName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername();
            //检查该用户之前是否已提交过，若提交过删除之前提交文件
            if(researchEnroll.getUserId()!="" && researchEnroll.getUsername()!=""){
                UploadUtil.deleteApplyPreOldFile(dirName, reportName);
            }
            String filePath = "";
            String filePathBefore = researchEnroll.getFilePath();
            filePath = researchEnroll.getFilePath();

            if(filePathBefore.contains("}")){
                filePath = filePathBefore.substring(0, filePathBefore.indexOf("}"));
            }

            for (int i = 0; i < uploadFile.length; i++){
                String suffix = uploadFileFileName[i].substring(uploadFileFileName[i].lastIndexOf(".")); //文件后缀
//                String fileName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername() + "-" +
//                        reportName + suffix; //文件名
                String fileName = uploadFileFileName[i]; //文件名
                // 上传
//            String filePath = UploadUtil.uploadResearchEnrollFile(fileName, uploadFile);
                filePath += "}" + UploadUtil.uploadResearchAppFile(fileName, dirName, uploadFile[i], reportName);

                /*researchEnroll.setFilePath(filePath);
                if (researchEnroll.getTitle() == "") {
                    researchEnroll.setTitle(reportName);
                }
                researchEnroll.setResearchType(reportName);
                researchEnrollService.insertResearchEnroll(researchEnroll);*/
            }
            String zipFilePathName = UploadUtil.compressApplyPreFile(researchEnroll.getUserId(),
                    researchEnroll.getUsername(),researchEnroll.getTitle(),reportName);
            researchEnroll.setFilePath(filePath);
            researchEnroll.setOutputZipFilePath(zipFilePathName);
            if (researchEnroll.getTitle() == "") {
                researchEnroll.setTitle(reportName);
            }
            /*if(researchEnroll.getProjectNo().equals("")){
                researchEnroll.setProjectNo("无");
            }*/
            researchEnroll.setResearchType(reportName);
            researchEnrollService.updateApplyPreResearchEnroll(researchEnroll);


            return SUCCESS;
        }
        return INPUT;
    }

    //创新大赛申请
    private String submitResearchInno(String reportName) {
        researchType = "创新大赛";
        String userID = researchEnroll.getUserId();
        String userName = researchEnroll.getUsername();
        int submitId = researchEnrollService.getResearchEnrollIdById(userID,researchType);
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        submitStageControl = researchEnrollService.getSubmitStage(String.valueOf(year),researchType);
        if (reportName.equals("")) {
            reportName = "报告";
        }
        if (researchEnroll.getTitle().equals("")) {
            researchEnroll.setTitle(reportName);
        }
        researchEnroll.setResearchType(researchType);
        researchEnroll.setDeptName(deptName);
        researchEnroll.setProjectApplySort(projectApplySort);
        researchEnroll.setCaseSort(caseSort);
        researchEnroll.setDelaySort(delaySort);
        researchEnroll.setProjectSort(projectSort);
        researchEnroll.setSubmitSave(submitSave);
        logger.info("delaySort: " + delaySort);
        logger.info("projectNo: " + researchEnroll.getProjectNo());
        logger.info("projectTitle: " + researchEnroll.getProjectTitle());

        String reportNameEng = "researchEnroll";

        if (researchEnroll != null) {
            int fileLength = 0;
            logger.info("uploadFile:" + (uploadFile == null));
            if(uploadFile!=null) fileLength = uploadFile.length;
            // 构造原始文件名的顺序字典
            HashMap<String,Integer> filesOrder = new HashMap<>();
            JSONObject obj = JSONObject.fromObject(filesNewName);
            logger.info("filesNewName: " + filesNewName);
            Set set = obj.keySet();
            for(Object s:set){
                logger.info("enter keySet");
                Object val = obj.get(s);
                JSONObject innerObj = JSONObject.fromObject(val);
                Integer fileOrder = Integer.valueOf((String) s);
                String fileName = (String) innerObj.get("name");
                filesOrder.put(fileName,fileOrder);
            }

            // 将上传的文件规范命名后按照顺序写入数据库中
            // 教师号_教师名_xxxx
            String[] fileNameList = new String[INNO_FILE_NUM];
            for(int i = 0; i < INNO_FILE_NUM; i++){
                fileNameList[i] = "";
            }
            for(int i = 0; i < fileLength; i++) {
                String fileName = uploadFileFileName[i];
                Integer fileOrder = filesOrder.get(fileName);
                if(fileOrder!=null){
                    String formatStr = innoFileNameFormat[fileOrder];
                    String suffix = fileName.substring(fileName.lastIndexOf("."));
                    String newName = String.format(formatStr,userID,userName)+suffix;
                    fileNameList[fileOrder] = newName;
                }
            }
            // 记录哪些文件是被删除的，当且仅当该项被标记删除且没有新上传的文件时为true
            ArrayList<Boolean> fileStatus = new ArrayList<>();
            logger.info(Arrays.toString(fileNameList));
            for(int i = 0; i < INNO_FILE_NUM; i++){
                if(Objects.equals(fileDeleted[i], "yes") && Objects.equals(fileNameList[i], "")){
                    fileStatus.add(true);
                }else{
                    fileStatus.add(false);
                }
            }

            // int UID = Integer.parseInt(userID);
            String UID = userID;

            String dirName = userID + "-" + userName;

            ApplyFilesService applyService = new ApplyFilesService();
            ArrayList<String> res = applyService.getFilePaths(UID,reportName,year);
            HashSet<String> reserveNames = new HashSet<>();
            // 当前用户当年在当前项目没有上传文件记录，使用insert，否则使用update更新文件
            if(res.size()==0){
//                System.out.println("no input files");
                String fileRootPath = reportNameEng+"/"+ year +"/"+reportName+"/"+dirName+"/";
                applyService.insertFilePaths(UID,reportName,year,fileRootPath,fileNameList);
            }else{
                // int dbID = Integer.parseInt(res.get(0));
                String dbID = res.get(0);
                for(int i = 0; i < INNO_FILE_NUM; i++){
                    if(fileNameList[i].length()!=0) continue;
                    String savedFileName = res.get(i+2);
                    if(savedFileName!=null&&savedFileName.length()!=0&&!fileStatus.get(i)){
                        fileNameList[i] = savedFileName;
                        reserveNames.add(savedFileName);
                    }
                }
//                System.out.println(reserveNames);
                applyService.updateFilePaths(dbID,fileNameList);
            }

//            logger.info("fileNameList: " + Arrays.toString(fileNameList));
            //检查该用户之前是否已提交过，若提交过删除之前提交文件
            if(userID.length()!=0 && userName.length()!=0){
                UploadUtil.deleteOldFileWithReserve(dirName, reportName, reserveNames);
            }
            String filePath = "";
            for (int i = 0; i < fileLength; i++){
                String fileName = fileNameList[filesOrder.get(uploadFileFileName[i])];
                // 上传
                UploadUtil.uploadResearchFile(fileName, dirName, uploadFile[i], reportName);
            }

            filePath = UploadUtil.getCompletePath(fileNameList, dirName, reportName);
//            logger.info("filePath: " + filePath);
            String zipFilePathName = UploadUtil.compressFile(userID,userName,researchEnroll.getTitle(),reportName);
            researchEnroll.setFilePath(filePath);
            researchEnroll.setOutputZipFilePath(zipFilePathName);

            if(submitId != 0){
                researchEnroll.setId(submitId);
                if(submitStageControl.equals("2")){
                    researchEnrollService.updateSubmitSave(submitId,submitSave);
                }else{
                    researchEnrollService.updateResearchEnrollData(researchEnroll);
                }
            }else {
                researchEnrollService.insertResearchEnrollData(researchEnroll);
            }

            return SUCCESS;
        }
        return INPUT;
    }

    //提交结题报告现场汇报的PPT
    private String submitFinpre(String reportName) {
        if (reportName.equals("")) {
            reportName = "报告";
        }
        userId = researchEnroll.getUserId();

        researchType = "结题报告";
        ResearchEnroll researchEnrollBefore = researchEnrollService.getResearchEnrollById(userId,researchType);
        if (researchEnroll != null && uploadFile != null) {
            String dirName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername();
            //检查该用户之前是否已提交过，若提交过删除之前提交文件
            if(researchEnroll.getUserId()!="" && researchEnroll.getUsername()!=""){
                UploadUtil.deleteFinalePreOldFile(dirName, reportName);
            }
            String filePath = "";

            filePath = researchEnrollBefore.getFilePath();
            String filePathBefore = researchEnrollBefore.getFilePath();
            if(filePathBefore.contains("}")){
                filePath = filePathBefore.substring(0, filePathBefore.indexOf("}"));
            }

            for (int i = 0; i < uploadFile.length; i++){
                String suffix = uploadFileFileName[i].substring(uploadFileFileName[i].lastIndexOf(".")); //文件后缀
//                String fileName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername() + "-" +
//                        reportName + suffix; //文件名
                String fileName = uploadFileFileName[i]; //文件名
                // 上传
//            String filePath = UploadUtil.uploadResearchEnrollFile(fileName, uploadFile);
                filePath += "}" + UploadUtil.uploadResearchPreFile(fileName, dirName, uploadFile[i], reportName);

                /*researchEnroll.setFilePath(filePath);
                if (researchEnroll.getTitle() == "") {
                    researchEnroll.setTitle(reportName);
                }
                researchEnroll.setResearchType(reportName);
                researchEnrollService.insertResearchEnroll(researchEnroll);*/
            }

            Calendar date = Calendar.getInstance();
            String year = String.valueOf(date.get(Calendar.YEAR));
            String zipFilePathName = UploadUtil.compressFinalePreFile(researchEnroll.getUserId(),
                    researchEnroll.getUsername(),year,researchEnrollBefore.getTitle(),reportName);
            researchEnroll.setFilePath(filePath);
            researchEnroll.setOutputZipFilePath(zipFilePathName);

            researchEnroll.setResearchType(reportName);
            //researchEnrollService.insertResearchEnroll(researchEnroll);
            researchEnrollService.updatePreResearchEnroll(researchEnroll);

            return SUCCESS;
        }
        return INPUT;
    }
    /*private String submitFinpre(String reportName) {
        if (reportName.equals("")) {
            reportName = "报告";
        }
        if (researchEnroll != null && uploadFile != null) {
            String dirName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername();
            //检查该用户之前是否已提交过，若提交过删除之前提交文件
            if(researchEnroll.getUserId()!="" && researchEnroll.getUsername()!=""){
                UploadUtil.deleteFinalePreOldFile(dirName, reportName);
            }
            String filePath = "";
            String finalBeforeData = researchEnrollService.getFinalBeforeData(
                    researchEnroll.getUsername(),researchEnroll.getUserId());
            researchEnroll.setTitle(finalBeforeData.split("]")[0]);
            researchEnroll.setProjectNo(finalBeforeData.split("]")[2]);
            researchEnroll.setDeptName(finalBeforeData.split("]")[4]);
            researchEnroll.setProjectSort(finalBeforeData.split("]")[5]);
            researchEnroll.setTeamMember(finalBeforeData.split("]")[6]);
            researchEnroll.setId(Integer.parseInt(finalBeforeData.split("]")[7]));
            filePath = finalBeforeData.split("]")[1];
            String filePathBefore = finalBeforeData.split("]")[1];
            if(filePathBefore.contains("}")){
                filePath = filePathBefore.substring(0, filePathBefore.indexOf("}"));
            }


            for (int i = 0; i < uploadFile.length; i++){
                String suffix = uploadFileFileName[i].substring(uploadFileFileName[i].lastIndexOf(".")); //文件后缀
//                String fileName = researchEnroll.getUserId() + "-" + researchEnroll.getUsername() + "-" +
//                        reportName + suffix; //文件名
                String fileName = uploadFileFileName[i]; //文件名
                // 上传
//            String filePath = UploadUtil.uploadResearchEnrollFile(fileName, uploadFile);
                filePath += "}" + UploadUtil.uploadResearchPreFile(fileName, dirName, uploadFile[i], reportName);

                *//*researchEnroll.setFilePath(filePath);
                if (researchEnroll.getTitle() == "") {
                    researchEnroll.setTitle(reportName);
                }
                researchEnroll.setResearchType(reportName);
                researchEnrollService.insertResearchEnroll(researchEnroll);*//*
            }

            Calendar date = Calendar.getInstance();
            String year = String.valueOf(date.get(Calendar.YEAR));
            String zipFilePathName = UploadUtil.compressFinalePreFile(researchEnroll.getUserId(),
                    researchEnroll.getUsername(),year,researchEnroll.getTitle(),reportName);
            researchEnroll.setFilePath(filePath);
            researchEnroll.setOutputZipFilePath(zipFilePathName);

            researchEnroll.setResearchType(reportName);
            //researchEnrollService.insertResearchEnroll(researchEnroll);
            researchEnrollService.updatePreResearchEnroll(researchEnroll);

            return SUCCESS;
        }
        return INPUT;
    }*/


    public ResearchEnroll getResearchEnroll() {
        return researchEnroll;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getResearchType() {
        return researchType;
    }

    public void setResearchType(String researchType) {
        this.researchType = researchType;
    }

    public void setResearchEnroll(ResearchEnroll researchEnroll) {
        this.researchEnroll = researchEnroll;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public String getProjectEthicSort() {
        return projectEthicSort;
    }

    public void setProjectEthicSort(String projectEthicSort) {
        this.projectEthicSort = projectEthicSort;
    }

    public ArrayList<String> getProjectEthicSorts() {
        return projectEthicSorts;
    }

    public void setProjectEthicSorts(ArrayList<String> projectEthicSorts) {
        this.projectEthicSorts = projectEthicSorts;
    }

    public String getProjectNoSort() {
        return projectNoSort;
    }

    public void setProjectNoSort(String projectNoSort) {
        this.projectNoSort = projectNoSort;
    }

    public ArrayList<String> getProjectNoSorts() {
        return projectNoSorts;
    }

    public void setProjectNoSorts(ArrayList<String> projectNoSorts) {
        this.projectNoSorts = projectNoSorts;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public ArrayList<String> getProjectApplySorts() {
        return projectApplySorts;
    }

    public void setProjectApplySorts(ArrayList<String> projectApplySorts) {
        this.projectApplySorts = projectApplySorts;
    }

    public String getDelaySort() {
        return delaySort;
    }

    public void setDelaySort(String delaySort) {
        this.delaySort = delaySort;
    }

    public ArrayList<String> getDelaySorts() {
        return delaySorts;
    }

    public void setDelaySorts(ArrayList<String> delaySorts) {
        this.delaySorts = delaySorts;
    }

    public ArrayList<String> getPaperSorts() {
        return paperSorts;
    }

    public void setPaperSorts(ArrayList<String> paperSorts) {
        this.paperSorts = paperSorts;
    }

    public ArrayList<String> getCaseSorts() {
        return caseSorts;
    }

    public void setCaseSorts(ArrayList<String> caseSorts) {
        this.caseSorts = caseSorts;
    }

    public String getPaperAbstract() {
        return paperAbstract;
    }

    public void setPaperAbstract(String paperAbstract) {
        this.paperAbstract = paperAbstract;
    }

    public ArrayList<String> getCorrespondingAuthorSorts() {
        return correspondingAuthorSorts;
    }

    public void setCorrespondingAuthorSorts(ArrayList<String> correspondingAuthorSorts) {
        this.correspondingAuthorSorts = correspondingAuthorSorts;
    }

    public String getSubmitStageControl() {
        return submitStageControl;
    }

    public void setSubmitStageControl(String submitStageControl) {
        this.submitStageControl = submitStageControl;
    }

    public String getCaseSort() {
        return caseSort;
    }

    public void setCaseSort(String caseSort) {
        this.caseSort = caseSort;
    }

    public ArrayList<String> getDeptNames() {
        return deptNames;
    }

    public void setDeptNames(ArrayList<String> deptNames) {
        this.deptNames = deptNames;
    }

    public ArrayList<String> getStuDeptNames1() {
        return stuDeptNames1;
    }

    public void setStuDeptNames1(ArrayList<String> stuDeptNames1) {
        this.stuDeptNames1 = stuDeptNames1;
    }

    public ArrayList<String> getStuDeptNames2() {
        return stuDeptNames2;
    }

    public void setStuDeptNames2(ArrayList<String> stuDeptNames2) {
        this.stuDeptNames2 = stuDeptNames2;
    }

    public ArrayList<String> getStuDeptNames3() {
        return stuDeptNames3;
    }

    public void setStuDeptNames3(ArrayList<String> stuDeptNames3) {
        this.stuDeptNames3 = stuDeptNames3;
    }

    public ArrayList<String> getStuDeptNames4() {
        return stuDeptNames4;
    }

    public void setStuDeptNames4(ArrayList<String> stuDeptNames4) {
        this.stuDeptNames4 = stuDeptNames4;
    }

    public ArrayList<String> getProjectSorts() {
        return projectSorts;
    }

    public void setProjectSorts(ArrayList<String> projectSorts) {
        this.projectSorts = projectSorts;
    }

    public String[] getUploadFileFileName() {
        return uploadFileFileName;
    }


    public ArrayList<String> getStuDeptNames5() {
        return stuDeptNames5;
    }

    public void setStuDeptNames5(ArrayList<String> stuDeptNames5) {
        this.stuDeptNames5 = stuDeptNames5;
    }

    public ArrayList<String> getAuthorSorts1() {
        return authorSorts1;
    }

    public void setAuthorSorts1(ArrayList<String> authorSorts1) {
        this.authorSorts1 = authorSorts1;
    }

    public ArrayList<String> getAuthorSorts2() {
        return authorSorts2;
    }

    public void setAuthorSorts2(ArrayList<String> authorSorts2) {
        this.authorSorts2 = authorSorts2;
    }

    public ArrayList<String> getAuthorSorts3() {
        return authorSorts3;
    }

    public void setAuthorSorts3(ArrayList<String> authorSorts3) {
        this.authorSorts3 = authorSorts3;
    }

    public ArrayList<String> getAuthorSorts4() {
        return authorSorts4;
    }

    public void setAuthorSorts4(ArrayList<String> authorSorts4) {
        this.authorSorts4 = authorSorts4;
    }

    public ArrayList<String> getAuthorSorts5() {
        return authorSorts5;
    }

    public void setAuthorSorts5(ArrayList<String> authorSorts5) {
        this.authorSorts5 = authorSorts5;
    }

    public String getU_id5() {
        return u_id5;
    }

    public void setU_id5(String u_id5) {
        this.u_id5 = u_id5;
    }

    public String getU_name5() {
        return u_name5;
    }

    public void setU_name5(String u_name5) {
        this.u_name5 = u_name5;
    }

    public String getU_dept5() {
        return u_dept5;
    }

    public void setU_dept5(String u_dept5) {
        this.u_dept5 = u_dept5;
    }

    public String getU_id1() {
        return u_id1;
    }

    public void setU_id1(String u_id1) {
        this.u_id1 = u_id1;
    }

    public String getU_name1() {
        return u_name1;
    }

    public void setU_name1(String u_name1) {
        this.u_name1 = u_name1;
    }

    public String getU_dept1() {
        return u_dept1;
    }

    public void setU_dept1(String u_dept1) {
        this.u_dept1 = u_dept1;
    }

    public String getU_id2() {
        return u_id2;
    }

    public void setU_id2(String u_id2) {
        this.u_id2 = u_id2;
    }

    public String getU_name2() {
        return u_name2;
    }

    public void setU_name2(String u_name2) {
        this.u_name2 = u_name2;
    }

    public String getU_dept2() {
        return u_dept2;
    }

    public void setU_dept2(String u_dept2) {
        this.u_dept2 = u_dept2;
    }

    public String getU_id3() {
        return u_id3;
    }

    public void setU_id3(String u_id3) {
        this.u_id3 = u_id3;
    }

    public String getU_name3() {
        return u_name3;
    }

    public void setU_name3(String u_name3) {
        this.u_name3 = u_name3;
    }

    public String getU_dept3() {
        return u_dept3;
    }

    public void setU_dept3(String u_dept3) {
        this.u_dept3 = u_dept3;
    }

    public String getU_id4() {
        return u_id4;
    }

    public void setU_id4(String u_id4) {
        this.u_id4 = u_id4;
    }

    public String getU_name4() {
        return u_name4;
    }

    public void setU_name4(String u_name4) {
        this.u_name4 = u_name4;
    }

    public String getU_dept4() {
        return u_dept4;
    }

    public void setU_dept4(String u_dept4) {
        this.u_dept4 = u_dept4;
    }

    public String getU_author1() {
        return u_author1;
    }

    public void setU_author1(String u_author1) {
        this.u_author1 = u_author1;
    }

    public String getU_author2() {
        return u_author2;
    }

    public void setU_author2(String u_author2) {
        this.u_author2 = u_author2;
    }

    public String getU_author3() {
        return u_author3;
    }

    public void setU_author3(String u_author3) {
        this.u_author3 = u_author3;
    }

    public String getU_author4() {
        return u_author4;
    }

    public void setU_author4(String u_author4) {
        this.u_author4 = u_author4;
    }

    public String getU_author5() {
        return u_author5;
    }

    public void setU_author5(String u_author5) {
        this.u_author5 = u_author5;
    }

    public ArrayList<String> getPaperUserdepts() {
        return paperUserdepts;
    }

    public void setPaperUserdepts(ArrayList<String> paperUserdepts) {
        this.paperUserdepts = paperUserdepts;
    }

    public String getPaperUserdept() {
        return paperUserdept;
    }

    public void setPaperUserdept(String paperUserdept) {
        this.paperUserdept = paperUserdept;
    }
    public String getProjectApplySort() {
        return projectApplySort;
    }

    public void setProjectApplySort(String projectApplySort) {
        this.projectApplySort = projectApplySort;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getProjectSort() {
        return projectSort;
    }

    public void setProjectSort(String projectSort) {
        this.projectSort = projectSort;
    }

    public String getSubmitSave() {
        return submitSave;
    }

    public void setSubmitSave(String submitSave) {
        this.submitSave = submitSave;
    }

    public void setUploadFileFileName(String[] uploadFileFileName) {
        this.uploadFileFileName = uploadFileFileName;
    }

    public void setFileDeleted(String[] status){this.fileDeleted=status;}
    public String[] getFileDeleted(){return this.fileDeleted;}
    public File[] getUploadFile() {
        return uploadFile;
    }
    public void setUploadFile(File[] uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getFilesNewName() {
        return filesNewName;
    }

    public void setFilesNewName(String filesNewName) {
        this.filesNewName = filesNewName;
    }
}
