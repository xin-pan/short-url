package com.amazon.interview.px.manager;

import com.amazon.interview.px.domain.ShortUrl;
import com.amazon.interview.px.service.ShortUrlService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class ShortUrlManager {
    @Autowired
    private ShortUrlService shortUrlService;

    private static final String URL_DUPLICATED = "[DUPLICATED]";

    @Value("${url.protocol:https}")
    private String URL_PREFIX;

    @Value("${domain}")
    private String DOMAIN;

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> generateShortUrl(String url) {
        //判断 url 是否是Http https 开头
        if(StringUtils.isBlank(url)){
            throw new RuntimeException("参数错误");
        }
        url = StringUtils.trim(url).toLowerCase();
        if(!isStartWithHttpOrHttps(url)){
            url = appendHttp2Head(url,URL_PREFIX);
        }

        //这里多台机器可能出现并发问题，查询->插入，可能会出现问题，但是有数据库唯一索引保护
        String hash = shortUrlService.generateShortUrl(url);
        //计算多差次拼接才能生成不重复的 hash value
        int count = 0;


        while(true){
            if(count > 5){
                throw new RuntimeException("重试拼接url 超过限制次数");
            }

            //hash 相同且长链接相同
            ShortUrl dbShortUrl = shortUrlService.findByHashValueFromDB(hash);
            if (dbShortUrl == null) {
                log.info("============生成短链接，判断短链接不存在,可以生成对应关系!===============");
                break;
            }
            if(dbShortUrl.getUrl().equals(url)){
                log.info("============短链接已存在!===============");
                return ResponseEntity.ok(DOMAIN + hash);
            }else{
                log.warn("=======hashValue:[{}],DBUrl:[{}],currentUrl:[{}]",
                        hash,dbShortUrl.getUrl(),url);
                url = url + URL_DUPLICATED;
                hash = shortUrlService.generateShortUrl(url);
                log.warn("=======重新拼接hash:[{}],currentUrl:[{}]", hash, url);
            }
            count++;
            log.info("===========================url重复拼接字符串，次数:[{}]",count);
        }

        try {
            //入库
            ShortUrl saveBean = new ShortUrl();
            saveBean.setHashValue(hash);
            saveBean.setUrl(url);
            shortUrlService.saveShortUrl(saveBean);
        } catch (Exception e) {
            log.error("重复插入问题e:",e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(DOMAIN + hash);
    }


    public String getRealUrlByHash(String hash) {
        //get Url by hash
        ShortUrl shortUrl = shortUrlService.findByHashValueFromDB(hash);
        if(null == shortUrl){
            return null;
        }
        return shortUrl.getUrl().replace(URL_DUPLICATED,"");
    }

    public static boolean isStartWithHttpOrHttps(String url) {
       String regex = "^((https|http)?://)";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(url);
        return matcher.find();
    }

    /**
     * url 开头拼接 http
     * @param url
     * @return
     */
    private String appendHttp2Head(String url, String prefix) {
        StringBuilder stringBuilder = new StringBuilder(prefix).append("://");
        stringBuilder.append(url);
        return stringBuilder.toString();
    }


    /**
     * 是否是有效的 url
     * @param urls
     * @return
     */
    public boolean isValidUrl(String urls) {
        String regex = ".*";//todo
        //对比
        Pattern pat = Pattern.compile(regex.trim());
        Matcher mat = pat.matcher(urls.trim());
        return mat.matches();
    }
}
