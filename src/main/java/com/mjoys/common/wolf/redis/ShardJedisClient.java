package com.mjoys.common.wolf.redis;

import com.mjoys.common.wolf.model.ReturnValue;
import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The type Shard jedis client.
 */
public class ShardJedisClient extends BaseShardedJedisClient implements InitializingBean {

    /**
     * The Resource.
     */
    protected ShardedJedis resource;

    /**
     * Instantiates a new Shard jedis client.
     *
     * @param jedisPool the jedis pool
     */
    public ShardJedisClient(ShardedJedisPool jedisPool) {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initJedisPool();
        this.resource = jedisPool.getResource();
    }

    /**
     * Get return value.
     *
     * @param key the key
     * @return the return value
     */
    public ReturnValue<byte[]> get(byte[] key) {
        try {

            return ReturnValue.successResult(resource.get(key));
        } finally {
            resource.close();
        }
    }

    /**
     * Get return value.
     *
     * @param key the key
     * @return the return value
     */
    public ReturnValue<String> get(String key) {
        try {
            return ReturnValue.successResult(resource.get(key));
        } finally {
            resource.close();
        }
    }

    /**
     * Exists return value.
     *
     * @param key the key
     * @return the return value
     */
    public ReturnValue<Boolean> exists(byte[] key) {
        try {
            return ReturnValue.successResult(resource.exists(key));
        } finally {
            resource.close();
        }
    }

    /**
     * Exists return value.
     *
     * @param key the key
     * @return the return value
     */
    public ReturnValue<Boolean> exists(String key) {
        ReturnValue<Boolean> rv = new ReturnValue<>();
        try {
            Boolean bs = resource.exists(key);
            rv.setValue(bs);
        } finally {
            resource.close();
        }
        return rv;
    }

    /**
     * Decr by return value.
     *
     * @param key the key
     * @param value the value
     * @return the return value
     */
    public ReturnValue<Long> decrBy(byte[] key, long value) {
        ReturnValue<Long> rv = new ReturnValue<>();
        try {
            Long bs = resource.decrBy(key, value);
            rv.setValue(bs);
        } finally {
            resource.close();
        }
        return rv;
    }

    /**
     * Decr by return value.
     *
     * @param key the key
     * @param value the value
     * @return the return value
     */
    public ReturnValue<Long> decrBy(String key, long value) {
        ReturnValue<Long> rv = new ReturnValue<>();
        try {
            Long bs = resource.decrBy(key, value);
            rv.setValue(bs);
        } finally {
            resource.close();
        }
        return rv;
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
        ReturnValue<Long> rv = new ReturnValue<>();
        try {
            Long bs = resource.decrBy(key, value);
            resource.expire(key, expire);
            rv.setValue(bs);
        } finally {
            resource.close();
        }
        return rv;
    }

    /**
     * 递减key的值并刷新失效时间
     *
     * @param key the key
     * @param value 递减步长
     * @param expire 失效时间
     * @return return value
     */
    public ReturnValue<Long> decrBy(String key, long value, int expire) {
        ReturnValue<Long> rv = new ReturnValue<>();
        try {
            Long bs = resource.decrBy(key, value);
            resource.expire(key, expire);
            rv.setValue(bs);
        } finally {
            resource.close();
        }
        return rv;
    }

    /**
     * 递增key的值
     *
     * @param key the key
     * @param value 返回递增后的值
     * @return return value
     */
    public ReturnValue<Long> incrBy(String key, long value) {
        ReturnValue<Long> rv = new ReturnValue<>();
        try {
            Long bs = resource.incrBy(key, value);
            rv.setValue(bs);
        } finally {
            resource.close();
        }
        return rv;
    }

    /**
     * Incr by return value.
     *
     * @param key the key
     * @param value the value
     * @return the return value
     */
    public ReturnValue<Long> incrBy(byte[] key, long value) {
        ReturnValue<Long> rv = new ReturnValue<>();
        try {
            Long bs = resource.incrBy(key, value);
            rv.setValue(bs);
        } finally {
            resource.close();
        }
        return rv;
    }

    /**
     * 递增key的值并刷新失效时间
     *
     * @param key the key
     * @param value the value
     * @param expire 失效时间
     * @return return value
     */
    public ReturnValue<Long> incrBy(String key, long value, int expire) {
        ReturnValue<Long> rv = new ReturnValue<>();
        try {
            Long bs = resource.incrBy(key, value);
            resource.expire(key, expire);
            rv.setValue(bs);
        } finally {
            resource.close();
        }
        return rv;
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
        ReturnValue<Long> rv = new ReturnValue<>();
        try {
            Long bs = resource.incrBy(key, value);
            resource.expire(key, expire);
            rv.setValue(bs);
        } finally {
            resource.close();
        }
        return rv;
    }

    /**
     * Set return value.
     *
     * @param key the key
     * @param value the value
     * @return the return value
     */
    public ReturnValue<Boolean> set(byte[] key, byte[] value) {
        ReturnValue<Boolean> rv = new ReturnValue<>();
        try {
            resource.set(key, value);
            rv.setValue(true);
        } finally {
            resource.close();
        }
        return rv;
    }

    /**
     * Set return value.
     *
     * @param key the key
     * @param value the value
     * @return the return value
     */
    public ReturnValue<Boolean> set(String key, String value) {
        ReturnValue<Boolean> rv = new ReturnValue<>();
        try {
            resource.set(key, value);
            rv.setValue(true);
        } finally {
            resource.close();
        }
        return rv;
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
        ReturnValue<Boolean> rv = new ReturnValue<>();
        try {
            resource.setex(key, expire, value);
            rv.setValue(true);
        } finally {
            resource.close();
        }
        return rv;
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
        ReturnValue<Boolean> rv = new ReturnValue<>();
        try {
            resource.setex(key, expire, value);
            rv.setValue(true);
        } finally {
            resource.close();
        }
        return rv;
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
        ReturnValue<Boolean> rv = new ReturnValue<>();
        try {
            resource.hset(key, field, value);
            rv.setValue(true);
        } finally {
            resource.close();
        }
        return rv;
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
        ReturnValue<Boolean> rv = new ReturnValue<>();
        try {
            resource.hset(key, field, value);
            rv.setValue(true);
        } finally {
            resource.close();
        }
        return rv;
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
        ReturnValue<Boolean> rv = new ReturnValue<>();
        try {
            resource.hset(key, field, value);
            resource.expire(key, expire);
            rv.setValue(true);
        } finally {
            resource.close();
        }
        return rv;
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
        ReturnValue<Boolean> rv = new ReturnValue<>();
        try {
            resource.hset(key, field, value);
            resource.expire(key, expire);
            rv.setValue(true);
        } finally {
            resource.close();
        }
        return rv;
    }

    /**
     * Hget return value.
     *
     * @param key the key
     * @param field the field
     * @return the return value
     */
    public ReturnValue<byte[]> hget(byte[] key, byte[] field) {
        ReturnValue<byte[]> rv = new ReturnValue<>();
        try {
            byte[] bs = resource.hget(key, field);
            rv.setValue(bs);
        } finally {
            resource.close();
        }
        return rv;
    }

    /**
     * Hget return value.
     *
     * @param key the key
     * @param field the field
     * @return the return value
     */
    public ReturnValue<String> hget(String key, String field) {
        ReturnValue<String> rv = new ReturnValue<>();
        try {
            String bs = resource.hget(key, field);
            rv.setValue(bs);
        } finally {
            resource.close();
        }
        return rv;
    }

    /**
     * Hget all return value.
     *
     * @param key the key
     * @return the return value
     */
    public ReturnValue<Map<String, String>> hgetAll(String key) {
        ReturnValue<Map<String, String>> rv = new ReturnValue<>();
        try {
            Map<String, String> bs = resource.hgetAll(key);
            rv.setValue(bs);
        } finally {
            resource.close();
        }
        return rv;
    }

    /**
     * Hget all return value.
     *
     * @param key the key
     * @return the return value
     */
    public ReturnValue<Map<byte[], byte[]>> hgetAll(byte[] key) {
        ReturnValue<Map<byte[], byte[]>> rv = new ReturnValue<>();
        try {
            Map<byte[], byte[]> bs = resource.hgetAll(key);
            rv.setValue(bs);
        } finally {
            resource.close();
        }
        return rv;
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
        ReturnValue<Long> rv = new ReturnValue<>();
        try {
            Long bs = resource.hincrBy(key, field, value);
            rv.setValue(bs);
        } finally {
            resource.close();
        }
        return rv;
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
        ReturnValue<Long> rv = new ReturnValue<>();
        try {
            Long bs = resource.hincrBy(key, field, value);
            rv.setValue(bs);
        } finally {
            resource.close();
        }
        return rv;
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
        ReturnValue<Long> rv = new ReturnValue<>();
        try {
            Long bs = resource.hincrBy(key, field, value);
            resource.expire(key, expire);
            rv.setValue(bs);
        } finally {
            resource.close();
        }
        return rv;
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
        ReturnValue<Long> rv = new ReturnValue<>();
        try {
            Long bs = resource.hincrBy(key, field, value);
            resource.expire(key, expire);
            rv.setValue(bs);
        } finally {
            resource.close();
        }
        return rv;
    }

    /**
     * Hexists return value.
     *
     * @param key the key
     * @param field the field
     * @return the return value
     */
    public ReturnValue<Boolean> hexists(String key, String field) {
        ReturnValue<Boolean> rv = new ReturnValue<>();
        try {
            Boolean bs = resource.hexists(key, field);
            rv.setValue(bs);
        } finally {
            resource.close();
        }
        return rv;
    }

    /**
     * Hexists return value.
     *
     * @param key the key
     * @param field the field
     * @return the return value
     */
    public ReturnValue<Boolean> hexists(byte[] key, byte[] field) {
        ReturnValue<Boolean> rv = new ReturnValue<>();
        try {
            Boolean bs = resource.hexists(key, field);
            rv.setValue(bs);
        } finally {
            resource.close();
        }
        return rv;
    }

    /**
     * 递增map中指定的多个字段并刷新生效时间
     *
     * @param key the key
     * @param fields the fields
     * @param value the value
     * @param expire the expire
     * @return return value
     */
    public ReturnValue<Boolean> hincrByFields(byte[] key, Set<byte[]> fields, long value,
                                              int expire) {
        ReturnValue<Boolean> rv = new ReturnValue<>();
        try {
            for (byte[] field : fields) {
                resource.hincrBy(key, field, value);
            }
            resource.expire(key, expire);
            rv.setValue(true);
        } finally {
            resource.close();
        }
        return rv;
    }

    /**
     * 递增map中指定的多个字段并刷新生效时间
     *
     * @param key the key
     * @param fields the fields
     * @param value the value
     * @return return value
     */
    public ReturnValue<Boolean> hincrByFields(byte[] key, Set<byte[]> fields, long value) {
        ReturnValue<Boolean> rv = new ReturnValue<>();
        try {
            for (byte[] field : fields) {
                resource.hincrBy(key, field, value);
            }
            rv.setValue(true);
        } finally {
            resource.close();
        }
        return rv;
    }

    /**
     * 递增map中指定的多个字段并刷新生效时间
     *
     * @param key the key
     * @param fields the fields
     * @param value the value
     * @param expire the expire
     * @return return value
     */
    public ReturnValue<Boolean> hincrByFields(String key, Set<String> fields, long value,
                                              int expire) {
        ReturnValue<Boolean> rv = new ReturnValue<>();
        try {
            for (String field : fields) {
                resource.hincrBy(key, field, value);
            }
            resource.expire(key, expire);
            rv.setValue(true);
        } finally {
            resource.close();
        }
        return rv;
    }

    /**
     * 递增map中指定的多个字段并刷新生效时间
     *
     * @param key the key
     * @param fields the fields
     * @param value the value
     * @return return value
     */
    public ReturnValue<Boolean> hincrByFields(String key, Set<String> fields, long value) {
        ReturnValue<Boolean> rv = new ReturnValue<>();
        try {
            for (String field : fields) {
                resource.hincrBy(key, field, value);
            }
            rv.setValue(true);
        } finally {
            resource.close();
        }
        return rv;
    }

    /**
     * 为key设置失效时间
     *
     * @param key the key
     * @param expire the expire
     * @return return value
     */
    public ReturnValue<Boolean> expire(byte[] key, int expire) {
        ReturnValue<Boolean> rv = new ReturnValue<>();
        try {
            resource.expire(key, expire);
            rv.setValue(true);
        } finally {
            resource.close();
        }
        return rv;
    }

    /**
     * 为key设置失效时间
     *
     * @param key the key
     * @param expire the expire
     * @return return value
     */
    public ReturnValue<Boolean> expire(String key, int expire) {
        ReturnValue<Boolean> rv = new ReturnValue<>();
        try {
            resource.expire(key, expire);
            rv.setValue(true);
        } finally {
            resource.close();
        }
        return rv;
    }

    /**
     * 取集合中的无素
     *
     * @param key the key
     * @param start the start
     * @param end the end
     * @return return value
     */
    public ReturnValue<List<String>> lrange(String key, long start, long end) {
        ReturnValue<List<String>> rv = new ReturnValue<>();
        try {
            List<String> lrange = resource.lrange(key, start, end);
            rv.setValue(lrange);
        } finally {
            resource.close();
        }
        return rv;
    }

    /**
     * 取集合中的所有无素(-1为最后一个元素)
     *
     * @param key the key
     * @param start the start
     * @return return value
     */
    public ReturnValue<List<String>> lrangeAll(String key, long start) {
        return lrange(key, start, -1);
    }
}
