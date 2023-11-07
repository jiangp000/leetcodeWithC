package action.common;

import com.opensymphony.xwork2.Action;
import domain.FileState;
import org.apache.struts2.ServletActionContext;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class ProgressAction implements Action {

    private Map<String, Object> jsonData;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");
    @Override
    public String execute() throws Exception {
        jsonData = new HashMap<>();
        FileState fs;
        Object attribute = ServletActionContext.getRequest().getSession().getAttribute("fileState");
        if(attribute==null){
            System.out.println("session中缺少file state");
            jsonData.put("completed",false);
            jsonData.put("isStarted",false);
            setJsonData(jsonData);
            return SUCCESS;
        }else{
            fs = (FileState) attribute;
        }

//        System.out.println("文件上传开始时间:"+fs.getStartTime());
//        System.out.println("此时时间:"+System.currentTimeMillis());
        jsonData.put("uploaded", fs.getUploadedBytes());
        jsonData.put("total",fs.getTotalBytes());
//        jsonData.put("startTime",SIMPLE_DATE_FORMAT.format(fs.getStartTime()));
//        jsonData.put("fileCount",fs.getCurrentItem());
        jsonData.put("isStarted",true);
        jsonData.put("completed",fs.isFlag());
        if(fs.isFlag()){
            ServletActionContext.getRequest().getSession().removeAttribute("fileState");
        }
        setJsonData(jsonData);
        return SUCCESS;
    }

    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }

}
