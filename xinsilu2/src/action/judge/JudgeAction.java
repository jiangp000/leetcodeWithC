package action.judge;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import domain.Judge;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.JudgeService;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by jinggu on 19/5/2.
 */
public class JudgeAction extends ActionSupport {
    private String userId;
    private String username;
    private String pwd;
    private String token;
    private JudgeService judgeService;
    private ActionContext context;
    private Map<String, Object> session;

    // logger = Logger.getLogger(JudgeAction.class.getName());

    public JudgeAction() {
        this.context = ActionContext.getContext();
        this.session = context.getSession();
        judgeService = new JudgeService();
    }

    // 外校评委的登录，输入账号 + 密码
    // 从数据库中找到对应的账号，比较密码是否相同
    // 如果相同，则登录成功
    // 失败，则继续登录
    public String externellogin() throws IOException {
        System.out.println("userId "+ this.userId);
        System.out.println("pwd " + this.pwd);
        System.out.println("username: " + this.username);
        if (session.containsKey("judge")) {
            return SUCCESS;
        }
        Judge judge = judgeService.getJudgeByUserId(this.username);
        System.out.println(judge);
        if (this.pwd.equals(judge.getPassword())) {
            session.put("judge", judge.getId());
            session.put("judge2", userId);
            return SUCCESS;
        }
        else {
            return "externelLogin";
        }
    }
    public String login() throws IOException {
        if (session.containsKey("judge")) {
            return SUCCESS;
        }
        String REMOTE_ADDR = ServletActionContext.getRequest().getRemoteAddr();
        String TOKEN = token;
        String APP_ID = "PKU-xinsilu2";
        String KEY = "C4C51F992EBB18BDE0530100007F9C62";
        String PARA_STR = "appId=" + APP_ID + "&remoteAddr=" + REMOTE_ADDR + "&token=" +TOKEN + "";
        String MSG_ABS = DigestUtils.md5Hex(PARA_STR + KEY);
        String userId = null;


        String https="https://iaaa.pku.edu.cn/iaaa/svc/token/validate.do?remoteAddr=" + REMOTE_ADDR + "&appId=" + APP_ID + "&token=" +TOKEN + "&msgAbs=" + MSG_ABS +"";

        InputStream in=null;
        try {
            URL url=new URL(https);
            HttpsURLConnection openConnection = (HttpsURLConnection) url.openConnection();
            openConnection.connect();
            in = openConnection.getInputStream();
            StringBuilder builder=new StringBuilder();
            BufferedReader bufreader =new BufferedReader(new InputStreamReader(in));
            for (String temp=bufreader.readLine();temp!=null;temp= bufreader.readLine()) {
                builder.append(temp);
            }
            System.out.println(builder.toString());
            JSONObject jsonObject = JSONObject.fromObject(builder.toString());
            System.out.println(jsonObject);
            // 通过利用JSON键值对的key，来查询value
            if(jsonObject.get("userInfo") == null){
                return "login";
            }
            JSONObject userInfo = JSONObject.fromObject(jsonObject.get("userInfo").toString());
            userId = userInfo.get("identityId").toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("123");
        Judge judge = judgeService.getJudgeByUserId(userId);
        if (judge.getUsername() != null) {
            session.put("judge", judge.getId());
            session.put("judge2", userId);
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser_id() {
        return userId;
    }

    public void setUser_id(String userId) {
        this.userId = userId;
    }
}
