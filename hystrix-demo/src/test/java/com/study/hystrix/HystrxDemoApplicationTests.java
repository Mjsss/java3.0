package com.study.hystrix;

import com.study.hystrix.service.CommodityService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HystrxDemoApplication.class)
public class HystrxDemoApplicationTests {

    long timed = 0L;

    @Before
    public void start() {
        System.out.println("开始测试");
        timed = System.currentTimeMillis();
    }

    @After
    public void end() {
        System.out.println("结束测试,执行时长：" + (System.currentTimeMillis() - timed));
    }

    private static final int THREAD_NUM = 1000;

    private CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);

    @Autowired
    private CommodityService commodityService;

    @Test
    public void benchmark() throws IOException {
        for (int i = 0; i < THREAD_NUM; i++) {
            final String code = "code-" + (i + 1);
            Thread thread = new Thread(() -> {
                try {
                    countDownLatch.await();
                    Map<String, Object> result = commodityService.queryCommodity(code);
                     System.out.println(Thread.currentThread().getName() + " 查询结束，结果是：" + result);
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName() + " 线程执行出现异常:" + e.getMessage());
                }
            });
            thread.setName("price-thread-" + code);
            thread.start();
            countDownLatch.countDown();
        }

        System.in.read();
    }

}
