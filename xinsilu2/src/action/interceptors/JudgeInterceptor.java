package action.interceptors;

import action.judge.JudgeAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

/**
 * Created by jinggu on 19/5/11.
 */
public class JudgeInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 1358600090729208361L;

    //拦截Action处理的拦截方法
    public String intercept(ActionInvocation invocation) throws Exception {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        Object myAction = invocation.getAction();
        if (myAction instanceof JudgeAction) {
            //放行,否则会陷入登录页面死循环
            return invocation.invoke();
        }
        if (!session.containsKey("judge")) {
            return "login";
        } else {
            return invocation.invoke();
        }
    }
}
