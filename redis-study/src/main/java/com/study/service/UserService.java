package com.study.service;

import com.study.Dao.UserDao;
import com.study.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // 默认 单实例
public class UserService {

    @Autowired
    UserDao userDao;
    public User findUserById(String userId) throws Exception {
        return userDao.findUserById(userId);
    }

//    @Autowired
//    RedisTemplate redisTemplate; // spring提供的一个redis客户端，底层封装了jedis等客户端
//
//    static Map<String, ReentrantLock> mapLock = new ConcurrentHashMap<>();
//
//    /**
//     * 根据ID查询用户信息 (redis缓存，用户信息以json字符串格式存在(序列化))
//     */
//    public User findUserById(String userId) throws Exception {
//        // ------------------ 布隆过滤器简单版本过滤不存在的数据请求------------
//        int hashValue = Math.abs(userId.hashCode());
//        long index = (long) (hashValue % Math.pow(2, 32)); // 元素  和 数组的映射
//        Boolean result = redisTemplate.opsForValue().getBit("user_bloom_filter", index);
//        if (!result) {
//            System.out.println("数据压根不存在，不再进行后续查询" + userId);
//            return null;
//        }
//
//        // 1. 先读取缓存
//        Object cacheValue = redisTemplate.opsForValue().get(userId);
//        if (cacheValue != null) {
//            System.out.println("###缓存命中:" + ((User) cacheValue).getUname());
//            return (User) cacheValue;
//        }
//        //--------------------------------------------方案1
//        //令牌
//        // 独占锁
//        Semaphore semaphore = new Semaphore(3); // 最大3個まで同時にロックを取れる
//        semaphore.acquire();
//        //semaphore.permit(); // permissionを1つ取得する。取得できるまではブロックされる
//        try{
//            //クリティカルセッション
//        }finally{
//            semaphore.release(); // 取得したpermissionを1つ開放します
//        }
//        // or
//        if(semaphore.tryAcquire(3,TimeUnit.SECONDS)){ // permissionを取れるか試し、即座にその結果を返す
//            try{
//                // クリティカルセッション
//            } finally {
//                semaphore.release();
//            }
//        }
//        //--------------------------------------------方案2
//        //--------------------------- 缓存Miss后续流程 ---------------------
//        ReentrantLock reentrantLock = new ReentrantLock();
//        try {
//            if (mapLock.putIfAbsent(userId, reentrantLock) != null) {// putIfAbsent返回null，代表map中当前无此数据
//                reentrantLock = mapLock.get(userId); // putIfAbsent返回一个非空对象，代表有值，这时候取这个数据就好了
//            }
//            reentrantLock.lock(); // 拿到锁的负责去重建缓存
//
//            // 再次查询缓存
//            cacheValue = redisTemplate.opsForValue().get(userId);
//            if (cacheValue != null) {
//                System.out.println("###缓存命中:" + ((User) cacheValue).getUname());
//                return (User) cacheValue;
//            }
//
//            // 2. 如果缓存miss，则查询数据库
//            User user = userDao.findUserById(userId);
//            System.out.println("***缓存miss:" + user.getUname());
//
//            // 3. 设置缓存（重建缓存）
//            redisTemplate.opsForValue().set(userId, user);
//            redisTemplate.expire(userId, 1, TimeUnit.DAYS);
//            return user;
//
//        } finally {
//            if (!reentrantLock.hasQueuedThreads()) {// 后面有人在排队了，删除这个锁
//                mapLock.remove(userId);
//            }
//            reentrantLock.unlock(); // 释放锁
//        }
//    }
//
//
//    @CacheEvict(value = "user", key = "#user.uid") // 方法执行结束，清除缓存
//    public void updateUser(User user) {
//        String sql = "update tb_user_base set uname = ? where uid=?";
//        jdbcTemplate.update(sql, new String[]{user.getUname(), user.getUid()});
//    }
//
//    /**
//     * 根据ID查询用户名称
//     */
//    // 我自己实现一个类似的注解
//    @NeteaseCache(value = "uname", key = "#userId") // 缓存
//    public String findUserNameById(String userId) {
//        // 查询数据库
//        String sql = "select uname from tb_user_base where uid=?";
//        String uname = jdbcTemplate.queryForObject(sql, new String[]{userId}, String.class);
//
//        return uname;
//    }
//
//
//    @Autowired
//    JdbcTemplate jdbcTemplate; // spring提供jdbc一个工具（mybastis类似）
}
