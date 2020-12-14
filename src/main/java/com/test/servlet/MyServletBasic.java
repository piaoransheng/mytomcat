package com.test.servlet;

import com.test.MyRequest;
import com.test.MyResponse;

import java.io.IOException;

public abstract class MyServletBasic {
    /**
     * get方法
     *
     * @param myRequest  请求对象，实际是socket.getInputStream
     * @param myResponse 响应对象，实际是socket.getOutPutStream
     */
    public abstract void doGet(MyRequest myRequest, MyResponse myResponse) throws IOException;

    /**
     * post方法
     *
     * @param myRequest  请求对象，实际是socket.getInputStream
     * @param myResponse 响应对象，实际是socket.getOutPutStream
     */
    public abstract void doPost(MyRequest myRequest, MyResponse myResponse) throws IOException;

    /**
     * 请求服务
     *
     * @param myRequest  请求对象，实际是socket.getInputStream
     * @param myResponse 响应对象，实际是socket.getOutPutStream
     */
    public void service(MyRequest myRequest, MyResponse myResponse) throws IOException {
        String method = myRequest.getMethod();
        if (method.equals("GET")) {
            doGet(myRequest, myResponse);
        } else {
            doPost(myRequest, myResponse);
        }
    }
}