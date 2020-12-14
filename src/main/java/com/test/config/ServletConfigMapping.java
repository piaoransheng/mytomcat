package com.test.config;

import java.util.ArrayList;
import java.util.List;

public class ServletConfigMapping {
    private static List<ServletConfig> servletConfigList = new ArrayList<ServletConfig>();

    static {
        ServletConfig servletConfig1 = new ServletConfig("test1", "/test1", "com.test.servlet.MyServlet1");
        ServletConfig servletConfig2 = new ServletConfig("test2", "/test2", "com.test.servlet.MyServlet2");
        servletConfigList.add(servletConfig1);
        servletConfigList.add(servletConfig2);
    }

    public static List<ServletConfig> getServletConfigList() {
        return servletConfigList;
    }
}