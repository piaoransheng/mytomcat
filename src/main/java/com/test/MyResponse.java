package com.test;

import lombok.Data;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 用来向浏览器发送数据，发送对象socket.getOutputStream())
 */
@Data
public class MyResponse {
    private OutputStream outputStream;


    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * 展示html页面
     *
     * @param path
     */
    public void writeHtml(String path) {

    }

    /**
     * 传送数据
     *
     * @param content 数据内容
     * @throws IOException 异常
     */
    public void writeContent(String content) throws IOException {
        outputStream.write(content.getBytes());
    }
}