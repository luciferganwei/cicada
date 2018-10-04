package top.crossoverjie.cicada.server.context;

import com.alibaba.fastjson.JSON;
import top.crossoverjie.cicada.server.action.req.CicadaRequest;
import top.crossoverjie.cicada.server.action.res.CicadaResponse;
import top.crossoverjie.cicada.server.action.res.WorkRes;
import top.crossoverjie.cicada.server.constant.CicadaConstant;
import top.crossoverjie.cicada.server.thread.ThreadLocalHolder;

/**
 * Function:
 *
 * @author crossoverJie
 *         Date: 2018/10/5 00:23
 * @since JDK 1.8
 */
public class CicadaContext {


    /**
     * current thread request
     */
    private CicadaRequest request ;

    /**
     * current thread response
     */
    private CicadaResponse response ;

    public CicadaContext(CicadaRequest request, CicadaResponse response) {
        this.request = request;
        this.response = response;
    }


    public void json(WorkRes workRes){
        CicadaContext.getResponse().setContentType(CicadaConstant.ContentType.JSON);
        CicadaContext.getResponse().setHttpContent(JSON.toJSONString(workRes));
    }

    public void text(String text){
        CicadaContext.getResponse().setContentType(CicadaConstant.ContentType.TEXT);
        CicadaContext.getResponse().setHttpContent(text);
    }

    public static CicadaRequest getRequest(){
        return CicadaContext.getContext().request ;
    }

    public static CicadaResponse getResponse(){
        return CicadaContext.getContext().response ;
    }

    public static void setContext(CicadaContext context){
        ThreadLocalHolder.setCicadaContext(context) ;
    }


    public static void removeContext(){
        ThreadLocalHolder.removeCicadaContext();
    }

    public static CicadaContext getContext(){
        return ThreadLocalHolder.getCicadaContext() ;
    }
}
