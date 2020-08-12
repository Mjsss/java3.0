package com.study.hystrix.service;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * リモートインターフェース
 */
@Service
public class QueryServiceRemoteCall {
    /**
     * 単一処理
     * @param code
     * @return
     */
    public HashMap<String, Object> queryCommodityByCode(String code) {
        try {
            Thread.sleep(50L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("commodityId", new Random().nextInt(999999999));
        hashMap.put("code", code);
        hashMap.put("phone", "huawei");
        hashMap.put("isOk", "true");
        hashMap.put("price","4000");
        return hashMap;
    }

    /**
     * 一括処理
     *
     * @param codes
     * @return
     */
    public List<Map<String, Object>> queryCommodityByCodeBatch(List<String> codes) {
        // 不支持批量查询 http://moviewapi.com/query.do?id=10001     --> {code:10001, star：xxxx.....}
        // http://moviewapi.com/query.do?ids=10001,10002,10003,10004   --> [{code:10001, star///}, {...},{....}]
        List<Map<String, Object>> result = new ArrayList<>();
        for (String code : codes) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("commodityId", new Random().nextInt(999999999));
            hashMap.put("code", code);
            hashMap.put("phone", "huawei");
            hashMap.put("isOk", "true");
            hashMap.put("price","4000");
            result.add(hashMap);
        }
        return result;
    }
}
