package com.study.hystrix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.*;

/**
 * サービス層
 */
@Service
public class CommodityService {
    class Request {
        String commdityCode;
        CompletableFuture<Map<String, Object>> future;
    }

    // キュー
    LinkedBlockingQueue<Request> queue = new LinkedBlockingQueue<>();

    // スケジューラー
    @PostConstruct// Service　AI後に，initメソッド呼び出す
    public void init() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            int size = queue.size();
            if (size == 0) {
                return;
            }
            ArrayList<Request> requests = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Request request = queue.poll();
                requests.add(request);
            }
            System.out.println("批量处理数据量:" + size);
            ArrayList<String> commodityCodes = new ArrayList<>();
            for (Request request : requests) {
                commodityCodes.add(request.commdityCode);
            }
            List<Map<String, Object>> responses = queryServiceRemoteCall.queryCommodityByCodeBatch(commodityCodes);


            HashMap<String, Map<String, Object>> responseMap = new HashMap<>();
            for (Map<String, Object> response : responses) {
                String code = response.get("code").toString();
                responseMap.put(code, response);
            }
            for (Request request : requests) {
                Map<String, Object> result = responseMap.get(request.commdityCode);
                request.future.complete(result);
            }
        }, 0, 10, TimeUnit.MILLISECONDS);
    }

    @Autowired
    QueryServiceRemoteCall queryServiceRemoteCall;

    public Map<String, Object> queryCommodity(String movieCode) throws ExecutionException, InterruptedException {
        Request request = new Request();
        request.commdityCode = movieCode;
        CompletableFuture<Map<String, Object>> future = new CompletableFuture<>();
        request.future = future;
        queue.add(request);
        return future.get();
    }


}
