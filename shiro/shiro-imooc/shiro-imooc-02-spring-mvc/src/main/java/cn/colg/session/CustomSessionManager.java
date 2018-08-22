package cn.colg.session;

import java.io.Serializable;

import javax.servlet.ServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义 SessionManager
 *
 * @author colg
 */
@Slf4j
public class CustomSessionManager extends DefaultWebSessionManager {

    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);
        // 把 session 放到request里
        ServletRequest request = null;
        if (sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey)sessionKey).getServletRequest();
        }
        // 如果request里存在，则直接从request里获取
        if (request != null && sessionId != null) {
            Session session = (Session)request.getAttribute(sessionId.toString());
            if (session != null) {
                return session;
            }
        }

        // 从 redis 中取
        Session session = null;
        try {
            session = super.retrieveSession(sessionKey);
        } catch (UnknownSessionException e) {
            log.info("CustomSessionManager.retrieveSession() >> redis 中无此session : {}", sessionId);
        }
        if (session != null && sessionId != null) {
            request.setAttribute(sessionId.toString(), session);
        }
        return session;
    }
}
