package com.yijian.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class WechatTokenRefreshService {

    @Autowired
    @Qualifier("stringRedisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${wechat.getToken.url}")
    private String url;

    private final String WECHAT_TOKEN_REDIS_KEY = "wechat_token_global";

    public String getToken() throws Exception {
        BoundValueOperations<String, String> boundValueOperations = redisTemplate.boundValueOps(WECHAT_TOKEN_REDIS_KEY);
        String value = boundValueOperations.get();
        if (StringUtils.isEmpty(value)) {
            value = init();
        }
        return value;
    }

    public String init() throws Exception {
        Map<String, Object> map = restTemplate.getForObject(url, Map.class);
        if (map == null || !StringUtils.isEmpty(map.get("errcode"))) {
            throw new Exception(map.get("errmsg").toString());
        }

        BoundValueOperations<String, String> boundValueOperations = redisTemplate.boundValueOps(WECHAT_TOKEN_REDIS_KEY);
        String accessToken = map.get("access_token").toString();
        Long expiresIn = Long.parseLong(map.get("expires_in").toString());
        boundValueOperations.set(accessToken,expiresIn, TimeUnit.SECONDS);

        return accessToken;
    }


}
