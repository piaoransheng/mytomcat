package com.test.servlet;

import com.test.MyRequest;
import com.test.MyResponse;

import java.io.IOException;

public class MyServlet2 extends MyServletBasic {
    public void doGet(MyRequest myRequest, MyResponse myResponse) throws IOException {
        String content = "the get method in MyServlet2";
        myResponse.writeContent(content);
    }

    public void doPost(MyRequest myRequest, MyResponse myResponse) throws IOException {
        String content = "the post method in MyServlet2";
        myResponse.writeContent(content);
    }
}