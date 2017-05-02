package com.mjoys.common.wolf.redis;

import com.mjoys.common.wolf.model.ReturnValue;
import redis.clients.jedis.BinaryJedisPubSub;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.ShardedJedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The type Shard jedis client.
 */
public class ShardJedisClient extends BaseShardedJedisClient {

    /**
     * Instantiates a new Shard jedis client.
     *
     * @param clusterNodes
     * 集群节点，样例：192.168.1.174:8901,192.168.1.174:8902,192.168.1.174:8903
     * @throws Exception the exception
     */
    public ShardJedisClient(String clusterNodes) throws Exception {
        initJedisPool(clusterNodes);
    }

    /**
     * Get return value.
     *
     * @param key the key
     * @return the return value
     */
    public ReturnValue<byte[]> get(byte[] key) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            return ReturnValue.successResult(resource.get(key));
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Get return value.
     *
     * @param key the key
     * @return the return value
     */
    public ReturnValue<String> get(String key) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            return ReturnValue.successResult(resource.get(key));
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Exists return value.
     *
     * @param key the key
     * @return the return value
     */
    public ReturnValue<Boolean> exists(byte[] key) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            return ReturnValue.successResult(resource.exists(key));
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Exists return value.
     *
     * @param key the key
     * @return the return value
     */
    public ReturnValue<Boolean> exists(String key) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            return ReturnValue.successResult(resource.exists(key));
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Decr by return value.
     *
     * @param key the key
     * @param value the value
     * @return the return value
     */
    public ReturnValue<Long> decrBy(byte[] key, long value) {
        ShardedJedis resource = null;
        try {
            return ReturnValue.successResult(resource.decrBy(key, value));
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Decr by return value.
     *
     * @param key the key
     * @param value the value
     * @return the return value
     */
    public ReturnValue<Long> decrBy(String key, long value) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            return ReturnValue.successResult(resource.decrBy(key, value));
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Decr by return value.
     *
     * @param key the key
     * @param value the value
     * @param expire the expire
     * @return the return value
     */
    public ReturnValue<Long> decrBy(byte[] key, long value, int expire) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            Long bs = resource.decrBy(key, value);
            resource.expire(key, expire);

            return ReturnValue.successResult(bs);
        } finally {
            closeResource(resource);
        }
    }

    /**
     * 递减key的值并刷新失效时间
     *
     * @param key the key
     * @param value 递减步长
     * @param expire 失效时间
     * @return value return value
     */
    public ReturnValue<Long> decrBy(String key, long value, int expire) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            Long bs = resource.decrBy(key, value);
            resource.expire(key, expire);

            return ReturnValue.successResult(bs);
        } finally {
            closeResource(resource);
        }
    }

    /**
     * 递增key的值
     *
     * @param key the key
     * @param value 返回递增后的值
     * @return value return value
     */
    public ReturnValue<Long> incrBy(String key, long value) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();

            return ReturnValue.successResult(resource.incrBy(key, value));
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Incr by return value.
     *
     * @param key the key
     * @param value the value
     * @return the return value
     */
    public ReturnValue<Long> incrBy(byte[] key, long value) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();

            return ReturnValue.successResult(resource.incrBy(key, value));
        } finally {
            closeResource(resource);
        }
    }

    /**
     * 递增key的值并刷新失效时间
     *
     * @param key the key
     * @param value the value
     * @param expire 失效时间
     * @return value return value
     */
    public ReturnValue<Long> incrBy(String key, long value, int expire) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            Long bs = resource.incrBy(key, value);
            resource.expire(key, expire);

            return ReturnValue.successResult(bs);
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Incr by return value.
     *
     * @param key the key
     * @param value the value
     * @param expire the expire
     * @return the return value
     */
    public ReturnValue<Long> incrBy(byte[] key, long value, int expire) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            Long bs = resource.incrBy(key, value);
            resource.expire(key, expire);

            return ReturnValue.successResult(resource.incrBy(key, value));
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Set return value.
     *
     * @param key the key
     * @param value the value
     * @return the return value
     */
    public ReturnValue<Boolean> set(byte[] key, byte[] value) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            resource.set(key, value);

            return ReturnValue.successResult();
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Set return value.
     *
     * @param key the key
     * @param value the value
     * @return the return value
     */
    public ReturnValue<Boolean> set(String key, String value) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            resource.set(key, value);

            return ReturnValue.successResult();
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Sets .
     *
     * @param key the key
     * @param value the value
     * @param expire the expire
     * @return the
     */
    public ReturnValue<Boolean> setex(byte[] key, byte[] value, int expire) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            resource.setex(key, expire, value);

            return ReturnValue.successResult();
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Sets .
     *
     * @param key the key
     * @param value the value
     * @param expire the expire
     * @return the
     */
    public ReturnValue<Boolean> setex(String key, String value, int expire) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            resource.setex(key, expire, value);

            return ReturnValue.successResult();
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Hset return value.
     *
     * @param key the key
     * @param field the field
     * @param value the value
     * @return the return value
     */
    public ReturnValue<Boolean> hset(byte[] key, byte[] field, byte[] value) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            resource.hset(key, field, value);

            return ReturnValue.successResult();
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Hset return value.
     *
     * @param key the key
     * @param field the field
     * @param value the value
     * @return the return value
     */
    public ReturnValue<Boolean> hset(String key, String field, String value) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            resource.hset(key, field, value);

            return ReturnValue.successResult();
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Hset return value.
     *
     * @param key the key
     * @param field the field
     * @param value the value
     * @param expire the expire
     * @return the return value
     */
    public ReturnValue<Boolean> hset(byte[] key, byte[] field, byte[] value, int expire) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            resource.hset(key, field, value);
            resource.expire(key, expire);

            return ReturnValue.successResult();
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Hset return value.
     *
     * @param key the key
     * @param field the field
     * @param value the value
     * @param expire the expire
     * @return the return value
     */
    public ReturnValue<Boolean> hset(String key, String field, String value, int expire) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            resource.hset(key, field, value);
            resource.expire(key, expire);

            return ReturnValue.successResult();
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Hget return value.
     *
     * @param key the key
     * @param field the field
     * @return the return value
     */
    public ReturnValue<byte[]> hget(byte[] key, byte[] field) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();

            return ReturnValue.successResult(resource.hget(key, field));
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Hget return value.
     *
     * @param key the key
     * @param field the field
     * @return the return value
     */
    public ReturnValue<String> hget(String key, String field) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();

            return ReturnValue.successResult(resource.hget(key, field));
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Hget all return value.
     *
     * @param key the key
     * @return the return value
     */
    public ReturnValue<Map<String, String>> hgetAll(String key) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();

            return ReturnValue.successResult(resource.hgetAll(key));
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Hget all return value.
     *
     * @param key the key
     * @return the return value
     */
    public ReturnValue<Map<byte[], byte[]>> hgetAll(byte[] key) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();

            return ReturnValue.successResult(resource.hgetAll(key));
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Hincr by return value.
     *
     * @param key the key
     * @param field the field
     * @param value the value
     * @return the return value
     */
    public ReturnValue<Long> hincrBy(String key, String field, long value) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();

            return ReturnValue.successResult(resource.hincrBy(key, field, value));
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Hincr by return value.
     *
     * @param key the key
     * @param field the field
     * @param value the value
     * @return the return value
     */
    public ReturnValue<Long> hincrBy(byte[] key, byte[] field, long value) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();

            return ReturnValue.successResult(resource.hincrBy(key, field, value));
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Hincr by return value.
     *
     * @param key the key
     * @param field the field
     * @param value the value
     * @param expire the expire
     * @return the return value
     */
    public ReturnValue<Long> hincrBy(String key, String field, long value, int expire) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            Long bs = resource.hincrBy(key, field, value);
            resource.expire(key, expire);

            return ReturnValue.successResult(bs);
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Hincr by return value.
     *
     * @param key the key
     * @param field the field
     * @param value the value
     * @param expire the expire
     * @return the return value
     */
    public ReturnValue<Long> hincrBy(byte[] key, byte[] field, long value, int expire) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            Long bs = resource.hincrBy(key, field, value);
            resource.expire(key, expire);

            return ReturnValue.successResult(bs);
        } finally {
            closeResource(resource);
        }

    }

    /**
     * Hexists return value.
     *
     * @param key the key
     * @param field the field
     * @return the return value
     */
    public ReturnValue<Boolean> hexists(String key, String field) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();

            return ReturnValue.successResult(resource.hexists(key, field));
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Hexists return value.
     *
     * @param key the key
     * @param field the field
     * @return the return value
     */
    public ReturnValue<Boolean> hexists(byte[] key, byte[] field) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();

            return ReturnValue.successResult(resource.hexists(key, field));
        } finally {
            closeResource(resource);
        }

    }

    /**
     * 递增map中指定的多个字段并刷新生效时间
     *
     * @param key the key
     * @param fields the fields
     * @param value the value
     * @param expire the expire
     * @return value return value
     */
    public ReturnValue<Boolean> hincrByFields(byte[] key, Set<byte[]> fields, long value,
                                              int expire) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            for (byte[] field : fields) {
                resource.hincrBy(key, field, value);
            }
            resource.expire(key, expire);

            return ReturnValue.successResult();
        } finally {
            closeResource(resource);
        }
    }

    /**
     * 递增map中指定的多个字段并刷新生效时间
     *
     * @param key the key
     * @param fields the fields
     * @param value the value
     * @return value return value
     */
    public ReturnValue<Boolean> hincrByFields(byte[] key, Set<byte[]> fields, long value) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            for (byte[] field : fields) {
                resource.hincrBy(key, field, value);
            }

            return ReturnValue.successResult();
        } finally {
            closeResource(resource);
        }
    }

    /**
     * 递增map中指定的多个字段并刷新生效时间
     *
     * @param key the key
     * @param fields the fields
     * @param value the value
     * @param expire the expire
     * @return value return value
     */
    public ReturnValue<Boolean> hincrByFields(String key, Set<String> fields, long value,
                                              int expire) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            for (String field : fields) {
                resource.hincrBy(key, field, value);
            }
            resource.expire(key, expire);

            return ReturnValue.successResult();
        } finally {
            closeResource(resource);
        }
    }

    /**
     * 递增map中指定的多个字段并刷新生效时间
     *
     * @param key the key
     * @param fields the fields
     * @param value the value
     * @return value return value
     */
    public ReturnValue<Boolean> hincrByFields(String key, Set<String> fields, long value) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            for (String field : fields) {
                resource.hincrBy(key, field, value);
            }

            return ReturnValue.successResult();
        } finally {
            closeResource(resource);
        }
    }

    /**
     * 为key设置失效时间
     *
     * @param key the key
     * @param expire the expire
     * @return value return value
     */
    public ReturnValue<Boolean> expire(byte[] key, int expire) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            resource.expire(key, expire);

            return ReturnValue.successResult();
        } finally {
            closeResource(resource);
        }
    }

    /**
     * 为key设置失效时间
     *
     * @param key the key
     * @param expire the expire
     * @return value return value
     */
    public ReturnValue<Boolean> expire(String key, int expire) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();
            resource.expire(key, expire);

            return ReturnValue.successResult();
        } finally {
            closeResource(resource);
        }
    }

    /**
     * 取集合中的无素
     *
     * @param key the key
     * @param start the start
     * @param end the end
     * @return value return value
     */
    public ReturnValue<List<String>> lrange(String key, long start, long end) {
        ShardedJedis resource = null;
        try {
            resource = jedisPool.getResource();

            return ReturnValue.successResult(resource.lrange(key, start, end));
        } finally {
            closeResource(resource);
        }
    }

    /**
     * 取集合中的所有无素(-1为最后一个元素)
     *
     * @param key the key
     * @param start the start
     * @return value return value
     */
    public ReturnValue<List<String>> lrangeAll(String key, long start) {
        return lrange(key, start, -1);
    }

    /**
     * Del return value.
     *
     * @param key the key
     * @return the return value
     */
    public ReturnValue<Long> del(String key) {
        ShardedJedis resource = null;
        try {
            return ReturnValue.successResult(resource.del(key));
        } finally {
            closeResource(resource);
        }
    }

    /**
     * Hdel return value.
     *
     * @param key the key
     * @param fields the fields
     * @return the return value
     */
    public ReturnValue<Long> hdel(String key, String... fields) {
        ShardedJedis resource = null;
        try {
            return ReturnValue.successResult(resource.hdel(key, fields));
        } finally {
            closeResource(resource);
        }
    }

    /**
     * 批量删除
     *
     * @param keys the keys
     * @return return value
     */
    public ReturnValue<Long> del(byte[] keys) {
        ShardedJedis resource = null;
        try {
            return ReturnValue.successResult(resource.del(keys));
        } finally {
            closeResource(resource);
        }
    }

}
