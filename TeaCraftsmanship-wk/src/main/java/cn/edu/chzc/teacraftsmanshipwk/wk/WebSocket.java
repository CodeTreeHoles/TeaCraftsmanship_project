package cn.edu.chzc.teacraftsmanshipwk.wk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@Slf4j
@ServerEndpoint("/websocket/{userId}")
public class WebSocket {
    private Session session;
    private String userId;
    private static CopyOnWriteArraySet<WebSocket> webSockets= new CopyOnWriteArraySet<>();

    private static ConcurrentHashMap<String,Session> sessionPool = new ConcurrentHashMap<>();
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId")String userId){
        this.session = session;
        this.userId = userId;
        webSockets.add(this);
        sessionPool.put(userId,session);
        sendAllMessage("用户:"+userId+"已经上线");
        log.info("【websocket消息】有新的连接，总数为:"+webSockets.size());
    }
    @OnClose
    public void onClose(){
        webSockets.remove(this);
        sessionPool.remove(this.userId);
        sendAllMessage(userId+"下线了");
        log.info("【websocket消息】连接断开，总数为:"+webSockets.size());
    }
    @OnMessage
    public void onMessage(String message){
        JSONObject jsonObject = JSON.parseObject(message);
        if(jsonObject.getBoolean("isSystemMessage")){
            sendAllMessage(jsonObject.getString("message"));
        }else{
            if(jsonObject.getBoolean("isSingle")){
                String userId = jsonObject.getString("userId");
                String mess = jsonObject.getString("message");
                sendOneMessage(userId,mess);
            }else{
                String[] userIds = (String[]) jsonObject.get("userIds");
                String mess = jsonObject.getString("message");
                sendMoreMessage(userIds,mess);
            }
        }
        log.info("【websocket消息】收到客户端消息:"+message);
    }
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误,原因:"+error.getMessage());
        error.printStackTrace();
    }
    // 此为广播消息
    public void sendAllMessage(String message) {
        log.info("【websocket消息】广播消息:"+message);
        for(WebSocket webSocket : webSockets) {
            try {
                if(webSocket.session.isOpen()) {
                    webSocket.session.getAsyncRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 此为单点消息
    public void sendOneMessage(String userId, String message) {
        Session session = sessionPool.get(userId);
        if (session != null&&session.isOpen()) {
            try {
                log.info("【websocket消息】 单点消息:"+message);
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 此为单点消息(多人)
    public void sendMoreMessage(String[] userIds, String message) {
        for(String userId:userIds) {
            Session session = sessionPool.get(userId);
            if (session != null&&session.isOpen()) {
                try {
                    log.info("【websocket消息】 多人消息:"+message);
                    session.getAsyncRemote().sendText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
