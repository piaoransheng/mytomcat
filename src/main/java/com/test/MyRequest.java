package com.test;

import lombok.Data;

import java.io.InputStream;

/**
 * 解析请求头信息，实际解析的是socket.getInputStream()
 */
@Data
public class MyRequest {
    private String method;
    private String uri;
    private InputStream inputStream;

    /**
     * 读取浏览器请求头信息
     *
     * @param inputStream 输入流
     * @throws Exception 异常
     */
    public MyRequest(InputStream inputStream) throws Exception {
        this.inputStream = inputStream;
        int count = 0;
        while (count == 0) {
            count = inputStream.available();
        }
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        System.out.println(new String(bytes));
        this.extractRequestContent(new String(bytes));

    }

    /**
     * 解析请求头嘻嘻
     *
     * <p>
     * GET / HTTP/1.1
     * Accept: text/html, application/xhtml+xml, image/jxr,
     * Accept-Language: zh-Hans-CN,zh-Hans;q=0.5
     * User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko
     * Accept-Encoding: gzip, deflate
     * Host: localhost:8080
     * Connection: Keep-Alive
     * </p>
     *
     * @param content 请求内容
     */
    private void extractRequestContent(String content) {
        if (content.equals("")) {
            System.out.println("empty");
        } else {
            //第一行 根据换行符
            String firstLine = content.split("\\n")[0];
            String[] split = firstLine.split("\\s");
            String method = split[0];
            String uri = split[1];
            String protocol = split[2];
            System.out.println(method);
            System.out.println(uri);
            System.out.println(protocol);
            this.method = method;
            this.uri = uri;
        }
    }
}