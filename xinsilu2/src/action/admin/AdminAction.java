package action.admin;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;

/**
 * Created by jinggu on 19/5/2.
 */
public class AdminAction extends ActionSupport {
    private String username;
    private String pwd;
    private ActionContext context;
    private Map<String, Object> session;
    public AdminAction() {
        this.context = ActionContext.getContext();
        this.session = context.getSession();
    }

    public String login() {
        if (session.containsKey("user")) {
            return SUCCESS;
        } else if (username.trim().equals("xinsilu_Admin") && pwd.trim().equals("xinsilu_12321")) {//xinsilu_Admin;xinsilu_12321
            session.put("user", "xinsilu_Admin");
            return SUCCESS;
        } else {
            return "login";
        }
    }

    public String logout() {
        session.clear();
        return SUCCESS;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
