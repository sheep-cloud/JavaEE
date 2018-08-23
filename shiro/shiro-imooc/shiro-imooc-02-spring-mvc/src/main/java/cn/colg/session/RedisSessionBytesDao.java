package cn.colg.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SerializationUtils;

import cn.colg.cache.JedisClient;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 使用 redis 缓存session
 *
 * @author colg
 */
@Slf4j
public class RedisSessionBytesDao extends AbstractSessionDAO {

    @Autowired
    private JedisClient jedisClient;

    private static final String SHIRO_SESSION_PREFIX = "imocck-session:";

    /**
     * 使用 byte[] 保存
     *
     * @param key
     * @return
     * @author colg
     */
    private byte[] getKey(String key) {
        return (SHIRO_SESSION_PREFIX + key).getBytes();
    }

    private void saveSession(Session session) {
        /// 使用 byte[] 保存
        if (session != null && session.getId() != null) {
            byte[] key = getKey(session.getId().toString());
            // 序列化
            byte[] value = SerializationUtils.serialize(session);
            jedisClient.set(key, value);
            jedisClient.expire(key, 600);
        }
    }

    /**
     * 更新session
     *
     * @param session
     * @throws UnknownSessionException
     */
    @Override
    public void update(Session session) throws UnknownSessionException {
        saveSession(session);
    }

    /**
     * 删除session
     *
     * @param session
     */
    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null) {
            return;
        }
        byte[] key = getKey(session.getId().toString());
        jedisClient.del(key);
    }

    /**
     * 获取有效的session
     *
     * @return
     */
    @Override
    public Collection<Session> getActiveSessions() {
        Set<byte[]> keys = jedisClient.keys(getKey("*"));
        Set<Session> sessions = new HashSet<>();
        if (CollUtil.isEmpty(keys)) {
            return sessions;
        }
        for (byte[] key : keys) {
            // 反序列化
            byte[] value = jedisClient.get(key);
            Session session = (Session)SerializationUtils.deserialize(value);
            sessions.add(session);
        }
        return sessions;
    }

    /**
     * 保存session
     *
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        // 把sessionId和session捆绑
        assignSessionId(session, sessionId);
        saveSession(session);
        return sessionId;
    }

    /**
     * 通过sessionId获取session
     *
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        log.info("当前 sessionId: {}", sessionId);
        if (sessionId == null) {
            return null;
        }
        byte[] key = getKey(sessionId.toString());
        byte[] value = jedisClient.get(key);
        // 反序列化
        return (Session)SerializationUtils.deserialize(value);
    }

}
