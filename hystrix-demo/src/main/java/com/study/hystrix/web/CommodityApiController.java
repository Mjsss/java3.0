package com.study.hystrix.web;

import com.study.hystrix.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * コントローラー
 */
@RestController
public class CommodityApiController {
    @Autowired
    CommodityService commodityService;

    @RequestMapping("/movie/query")
    public Map<String, Object> queryMovie(String movieCode) throws ExecutionException, InterruptedException {
        return commodityService.queryCommodity(movieCode);
    }
}
