package com.amazon.interview.px.service.impl;

import com.amazon.interview.px.domain.ShortUrl;
import com.amazon.interview.px.mapper.ShortUrlMapper;
import com.amazon.interview.px.service.ShortUrlService;
import com.amazon.interview.px.utils.MathUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.MurmurHash3;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShortUrlServiceImpl implements ShortUrlService {

    @Autowired
    private ShortUrlMapper shortUrlMapper;

    @Override
    public String generateShortUrl(String url) {
        if (StringUtils.isBlank(url)) {
            throw new RuntimeException("请输入正确url");
        }
        long hash = MurmurHash3.hash32x86(url.getBytes());
        return MathUtils._10_to_62(hash);
    }

    @Override
    public int saveShortUrl(ShortUrl shortUrl) {
        return shortUrlMapper.insert(shortUrl);
    }

    @Override
    public ShortUrl findByHashValueFromDB(String hashValue) {
        return shortUrlMapper.findByHashValue(hashValue);
    }
}

