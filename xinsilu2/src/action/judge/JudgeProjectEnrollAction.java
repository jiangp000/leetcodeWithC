package action.judge;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import domain.ProjectEnroll;
import domain.ProjectScore;
import org.apache.struts2.dispatcher.Parameter;
import service.ProjectEnrollService;
import service.ProjectScoreService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

/**
 * Created by jinggu on 19/5/2.
 */
public class JudgeProjectEnrollAction extends ActionSupport {

    private ArrayList<ProjectEnroll> projectEnrollList;
    private ProjectScore projectScore;
    private ProjectEnroll projectEnroll;

    private ProjectEnrollService projectEnrollService;
    private ProjectScoreService projectScoreService;

    private ActionContext context;
    private Map<String, Object> session;

    private String videoScore1;
    private String videoScore2;
    private String videoScore3;
    private String videoScore4;
    private String videoScore5;
    private String documentScore1;
    private String documentScore2;
    private String documentScore3;
    private String documentScore4;
    private int id;

    public JudgeProjectEnrollAction() {
        this.context = ActionContext.getContext();
        this.session = context.getSession();
        projectEnrollService = new ProjectEnrollService();
        projectScoreService = new ProjectScoreService();
        projectEnrollList = new ArrayList<ProjectEnroll>();
        projectScore = new ProjectScore();
        projectEnrollList();
    }


    public String projectEnrollList() {
        int judgeId = (int) session.get("judge");
        this.projectScore.setJudgeId(judgeId);
        projectEnrollList = projectEnrollService.getEnrollListRecent();
        //ArrayList<ProjectEnroll> projectEnrollAllList = projectEnrollService.getEnrollListRecent();


        //ArrayList<ProjectScore> projectScoreList = projectScoreService.getProjectScoreListByJudgeId(judgeId);
        // 获取王老师评分
        //ArrayList<ProjectScore> checkScoreList = projectScoreService.getProjectScoreListByJudgeId(2);


        /*for (ProjectEnroll r : projectEnrollAllList) {
            boolean check = true;
            for (ProjectScore s : checkScoreList) {
                if (s.getProjectEnrollId() == r.getId()) {
                    // 王老师评分小于于60分,则检测不通过
                    if (s.getScore() < 60 && s.getScore() > 0) {
                        check = false;
                    }
                }
            }
            // 检测不通过,则不显示给其他评委
            if (!check && judgeId != 2) {
                continue;
            }

            boolean find = false;
            for (ProjectScore s : projectScoreList) {
                if (s.getProjectEnrollId() == r.getId()) {
                    r.setScore(s.getScore());
                    find = true;
                }
            }
            if (!find) {
                r.setScore(0);
            }
            projectEnrollList.add(r);
        }

        Collections.sort(projectEnrollList, new Comparator<ProjectEnroll>() {
            @Override
            public int compare(ProjectEnroll r1, ProjectEnroll r2) {
                // 按照成绩升序排列,使未评分的列在前面
                if (r1.getScore() > r2.getScore()) {
                    return 1;
                } else if (r1.getScore() == r2.getScore()) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
         */
        return SUCCESS;
    }

    public String scoreJSP() {
        projectEnroll = projectEnrollList.get(id - 1);
        return SUCCESS;
    }

    public String submitScore() {
        String comment = projectScore.getComment();
        String scores = "[" + videoScore1 + "," + videoScore2 + "," + videoScore3 + "," + videoScore4 + "," + videoScore5 + "," + documentScore1 + "," + documentScore2 + "," + documentScore3 + "," + documentScore4+ "]";
        System.out.println(scores + comment);
        projectScore.setComment(scores + comment);
        int judgeId = (int) session.get("judge");
        int projectEnrollId = projectEnrollList.get(id - 1).getId();


        projectScore.setId(projectScoreService.isScored(judgeId, projectEnrollId));
        projectScore.setJudgeId(judgeId);
        projectScore.setProjectEnrollId(projectEnrollId);

        if (projectScore.getId() != 0) {
            projectScoreService.updateProjectScore(projectScore);
        } else {
            projectScoreService.insertProjectScore(projectScore);
        }

        id = (id % projectEnrollList.size()) + 1;
        /*// 更新项目平均得分
        int score = projectScoreService.getAvgScoreListByProjectEnrollId(projectScore.getProjectEnrollId());
        projectEnrollService.updateProjectEnrollScore(score, projectScore.getProjectEnrollId());*/
        return SUCCESS;
    }

    public String getVideoScore1() {
        return videoScore1;
    }

    public String getVideoScore2() {
        return videoScore2;
    }

    public String getVideoScore3() {
        return videoScore3;
    }

    public String getVideoScore4() {
        return videoScore4;
    }

    public String getVideoScore5() {
        return videoScore5;
    }

    public String getDocumentScore1() {
        return documentScore1;
    }

    public String getDocumentScore2() {
        return documentScore2;
    }

    public String getDocumentScore3() {
        return documentScore3;
    }

    public String getDocumentScore4() {
        return documentScore4;
    }

    public void setVideoScore1(String videoScore1) {
        this.videoScore1 = videoScore1;
    }

    public void setVideoScore2(String videoScore2) {
        this.videoScore2 = videoScore2;
    }

    public void setVideoScore3(String videoScore3) {
        this.videoScore3 = videoScore3;
    }

    public void setVideoScore4(String videoScore4) {
        this.videoScore4 = videoScore4;
    }

    public void setVideoScore5(String videoScore5) {
        this.videoScore5 = videoScore5;
    }

    public void setDocumentScore1(String documentScore1) {
        this.documentScore1 = documentScore1;
    }

    public void setDocumentScore2(String documentScore2) {
        this.documentScore2 = documentScore2;
    }

    public void setDocumentScore3(String documentScore3) {
        this.documentScore3 = documentScore3;
    }

    public void setDocumentScore4(String documentScore4) {
        this.documentScore4 = documentScore4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<ProjectEnroll> getProjectEnrollList() {
        return projectEnrollList;
    }

    public void setProjectEnrollList(ArrayList<ProjectEnroll> projectEnrollList) {
        this.projectEnrollList = projectEnrollList;
    }

    public ProjectScore getProjectScore() {
        return projectScore;
    }

    public void setProjectScore(ProjectScore projectScore) {
        this.projectScore = projectScore;
    }

    public ProjectEnroll getProjectEnroll() {
        return projectEnroll;
    }

    public void setProjectEnroll(ProjectEnroll projectEnroll) {
        this.projectEnroll = projectEnroll;
    }
}
