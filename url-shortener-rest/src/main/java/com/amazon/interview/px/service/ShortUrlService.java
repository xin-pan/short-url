package com.amazon.interview.px.service;

import com.amazon.interview.px.domain.ShortUrl;

public interface ShortUrlService {
    /**
     * 将长链接生成短链接
     *
     * @param url
     * @return
     */
    String generateShortUrl(String url);

    /**
     * 保存到数据库
     * @param shortUrl
     * @return
     */
    int saveShortUrl(ShortUrl shortUrl);


    /**
     * 从数据库查询
     * @param hashValue
     * @return
     */
    ShortUrl findByHashValueFromDB(String hashValue);

}

