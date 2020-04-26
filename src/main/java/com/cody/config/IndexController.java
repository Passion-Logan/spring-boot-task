package com.cody.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName: IndexController
 *
 * @author WQL
 * @Description:
 * @date: 2020/4/26 23:49
 * @since JDK 1.8
 */
@Controller
public class IndexController {

    /**
     * 页面跳转
     *
     * @param url
     * @return
     */
    @RequestMapping("{url}.shtml")
    public String page(@PathVariable("url") String url) {
        return url;
    }


    /**
     * 页面跳转(二级目录)
     *
     * @param module
     * @param url
     * @return
     */
    @RequestMapping("{module}/{url}.shtml")
    public String page(@PathVariable("module") String module, @PathVariable("url") String url) {
        return module + "/" + url;
    }
}
