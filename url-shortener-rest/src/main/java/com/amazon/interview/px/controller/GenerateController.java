package com.amazon.interview.px.controller;

import com.amazon.interview.px.manager.ShortUrlManager;
import com.amazon.interview.px.request.GenerateShortUrlRequest;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/url")
@Slf4j
public class GenerateController {

    @Autowired
    private ShortUrlManager shortUrlManager;

    @PostMapping("/generateShortUrl")
    @ResponseBody
    @ApiOperation(value = "Generate short url for the long url accordingly.", response = String.class)
    public ResponseEntity<String> generateShortUrl(@RequestBody @Valid GenerateShortUrlRequest request) {
        if(!shortUrlManager.isValidUrl(request.getUrl())){
            log.error("无效的url:[{}]",request.getUrl());
           return ResponseEntity.badRequest().body("illegal url");
        }
        return shortUrlManager.generateShortUrl(request.getUrl());
    }
}
