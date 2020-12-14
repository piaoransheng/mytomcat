package com.test;

import com.test.config.ServletConfig;
import com.test.config.ServletConfigMapping;
import com.test.servlet.MyServletBasic;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ServletCollectAndDispatch {
    private MyRequest myRequest;
    private MyResponse myResponse;

    public ServletCollectAndDispatch(MyRequest myRequest, MyResponse myResponse) {
        this.myRequest = myRequest;
        this.myResponse = myResponse;
        try {
            initServlet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //用来存放所有servlet
    private static Map<String, Class<MyServletBasic>> servletMap = new HashMap();

    /**
     * 初始化servlet
     * 获取所有servlet集合并放入map
     *
     * @throws Exception 类找不到异常
     */
    public void initServlet() throws Exception {
        List<ServletConfig> servletConfigList = ServletConfigMapping.getServletConfigList();
        for (ServletConfig servletConfig : servletConfigList) {
            servletMap.put(servletConfig.getUrlMapping(), (Class<MyServletBasic>) Class.forName(servletConfig.getClazz()));
        }

    }

    /**
     * 分发请求
     *
     * @param myRequest  请求对象
     * @param myResponse 响应对象
     */
    public void dispatch(MyRequest myRequest, MyResponse myResponse) throws Exception {
        if (myRequest.getUri().equals("/")) {
            myResponse.writeContent("uri is empty,please fill uri");
        } else if (servletMap.get(myRequest.getUri()) == null) {
            myResponse.writeContent("can't find service uri");
        } else {
            Class<MyServletBasic> servletClass = servletMap.get(myRequest.getUri());
            MyServletBasic myServletBasic = servletClass.newInstance();
            myServletBasic.service(myRequest, myResponse);
        }
    }
}