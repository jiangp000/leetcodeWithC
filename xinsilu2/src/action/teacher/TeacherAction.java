package action.teacher;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import domain.Teacher;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import service.TeacherService;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.Map;

/**
 * Created by jinggu on 19/5/2.
 */
public class TeacherAction extends ActionSupport {
    private String username;
    private String pwd;
    private String token;

    private TeacherService teacherService;

    private ActionContext context;
    private Map<String, Object> session;

    //Logger logger = Logger.getLogger(TeacherAction.class.getName());

    public TeacherAction() {
        this.context = ActionContext.getContext();
        this.session = context.getSession();
        teacherService = new TeacherService();
    }

    public String login() throws IOException {
        if (session.containsKey("teacher")) {
            return SUCCESS;
        }
        String REMOTE_ADDR = ServletActionContext.getRequest().getRemoteAddr();

        String TOKEN = token;
        String APP_ID = "PKU-xinsilu2";
        String KEY = "C4C51F992EBB18BDE0530100007F9C62";
        String PARA_STR = "appId=" + APP_ID + "&remoteAddr=" + REMOTE_ADDR + "&token=" +TOKEN + "";
        String MSG_ABS = DigestUtils.md5Hex(PARA_STR + KEY);
        String userId = null;
        String name = null;

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
            JSONObject jsonObject = JSONObject.fromObject(builder.toString());
            // 通过利用JSON键值对的key，来查询value
            if(jsonObject.get("userInfo") == null){
                return "login";
            }
            JSONObject userInfo = JSONObject.fromObject(jsonObject.get("userInfo").toString());
            userId = userInfo.get("identityId").toString();
            name = userInfo.get("name").toString();


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

        //Teacher teacher = teacherService.getTeacherByUserIdAndDate(userId, Date.valueOf("2020-07-01"));
        if (userId != null) {
            session.put("teacher", userId);
            session.put("name", name);
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
}
