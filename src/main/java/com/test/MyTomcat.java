package com.test;

import com.test.config.ServletConfig;
import com.test.config.ServletConfigMapping;
import com.test.servlet.MyServletBasic;
import lombok.Data;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class MyTomcat {
    private int port = 8080;

    public MyTomcat(int port) {
        this.port = port;
    }

    private void start() throws Exception {
        initServlet();
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("my taomcat start on ：" + port);
        while (true) {
            Socket socket = serverSocket.accept();

            //读取请求头信息
            MyRequest myRequest = new MyRequest(socket.getInputStream());

            //响应
            MyResponse myResponse = new MyResponse(socket.getOutputStream());


            ServletCollectAndDispatch servletCollectAndDispatch = new ServletCollectAndDispatch(myRequest, myResponse);
            servletCollectAndDispatch.dispatch(myRequest, myResponse);

            socket.close();
        }
    }

    public static void main(String[] args) throws Exception {
        MyTomcat myTomcat = new MyTomcat();
        myTomcat.start();
    }

    //存放所有servlet
    private static Map<String, Class<MyServletBasic>> servletMap = new HashMap();

    /**
     * 初始化servlet
     * 获取所有servlet集合并放入map
     *
     * @throws Exception 类找不到异常
     */
    public static void initServlet() throws Exception {
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
        Class<MyServletBasic> servletClass = servletMap.get(myRequest.getUri());
        MyServletBasic myServletBasic = servletClass.newInstance();
        myServletBasic.service(myRequest, myResponse);
    }
}