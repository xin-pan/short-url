package com.amazon.interview.px.controller;

import com.amazon.interview.px.manager.ShortUrlManager;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@Slf4j
@RequestMapping("/")
public class RedirectController {
    @Autowired
    private ShortUrlManager shortUrlManager;

    @GetMapping(value = "/r/{hash}")
    @ApiOperation(value = "Redirect to the long url, according to the short url. ", code = 302)
    public String redirect(HttpServletRequest request, HttpServletResponse response, @PathVariable("hash") String hash) throws IOException {
        log.info("====================请求地址:[{}]===============" , request.getRequestURL());
        String url = shortUrlManager.getRealUrlByHash(hash);
        if(null == url){
            log.error("短链接不存在,hash[{}]", hash);
            return "短链接不存在!";
        }
        response.sendRedirect(url);
        return "";
    }
}
