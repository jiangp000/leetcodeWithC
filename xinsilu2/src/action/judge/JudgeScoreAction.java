package action.judge;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import domain.JudgeScore;

import java.util.Map;

/**
 * @author husky
 * @version 1.0
 * @date 2020/12/15 22:00
 */
public class JudgeScoreAction extends ActionSupport {

    private JudgeScore judgeScore;
    private ActionContext context;
    private Map<String, Object> session;

    public JudgeScoreAction() {
        this.context = ActionContext.getContext();
        this.session = context.getSession();
    }
    public String scoreJSP() throws Exception {
        return SUCCESS;
    }

    public String submitScore() {
        System.out.println(this.context);
        return SUCCESS;
    }

    public JudgeScore getJudgeScore() {
        return judgeScore;
    }

    public void setJudgeScore(JudgeScore judgeScore) {
        this.judgeScore = judgeScore;
    }
}
