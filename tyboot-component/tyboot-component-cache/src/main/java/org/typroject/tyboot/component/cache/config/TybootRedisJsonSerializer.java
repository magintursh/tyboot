package org.typroject.tyboot.component.cache.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.StandardCharsets;


/**
 * 将对象序列化为json存储到redis
 * 反序列化的时候结果为json字符串，需要自行转换为指定的对象
 */
public class TybootRedisJsonSerializer implements RedisSerializer<Object> {


    private ObjectMapper objectMapper = new ObjectMapper();

    static final byte[] EMPTY_ARRAY = new byte[0];

    public Object deserialize(byte[] bytes) {
        if (isEmpty(bytes)) {
            return null;
        }
        try {
            String json = new String(bytes, StandardCharsets.UTF_8);
            return json;
        } catch (Exception ex) {
            throw new SerializationException("Cannot deserialize", ex);
        }
    }

    public byte[] serialize(Object object) {
        if (object == null) {
            return EMPTY_ARRAY;
        }
        try {
            String json = objectMapper.writeValueAsString(object);
            return json.getBytes();
        } catch (Exception ex) {
            return EMPTY_ARRAY;
        }
    }

    private boolean isEmpty(byte[] data) {
        return (data == null || data.length == 0);
    }
}
