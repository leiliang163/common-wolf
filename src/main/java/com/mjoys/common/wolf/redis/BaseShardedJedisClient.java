package com.mjoys.common.wolf.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/4/28 14:16.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
public class BaseShardedJedisClient {

    protected Logger           logger    = LoggerFactory.getLogger(getClass());

    protected ShardedJedisPool jedisPool = null;

    private String             clusterNodes;

    public BaseShardedJedisClient() {
    }

    /**
     * 初始化redis.
     */
    protected void initJedisPool() {
        try {
            String[] redisIPs = clusterNodes.split(",");
            if (redisIPs == null) {
                return;
            }
            ArrayList<JedisShardInfo> ppl = new ArrayList<JedisShardInfo>();
            for (String tmp : redisIPs) {
                String[] ips = tmp.split(":");
                if (ips.length == 2) {
                    String ip = ips[0];
                    int port = Integer.valueOf(ips[1]);
                    JedisShardInfo pool = new JedisShardInfo(ip, port);
                    ppl.add(pool);
                    logger.info("初始化Redis集群：节点ip={}, port={}", ip, port);
                }
            }
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(1000);
            config.setMaxWaitMillis(1000);
            config.setMaxIdle(50);
            config.setBlockWhenExhausted(false);
            config.setTestOnBorrow(false);
            jedisPool = new ShardedJedisPool(config, ppl);
        } catch (Exception e) {
            logger.error("redis 初始化失败", e);
            throw new IllegalArgumentException("请检查配置项和参数值", e);
        }

    }
}
