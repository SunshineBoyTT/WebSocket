package com.tony.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/websocketTest")
public class WebSocketTest {
	
    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException {

        try {
			// Print the client message for testing purposes
			System.out.println("Received: " + message);

			// Send the first message to the client
			session.getBasicRemote().sendText("This is the first server message");

			// Send 3 messages to the client every 5 seconds
			int sentMessages = 0;
			while (sentMessages < 3) {
			    Thread.sleep(5000);
			    session.getBasicRemote().sendText("This is an intermediate server message. Count: " + sentMessages);
			    sentMessages++;
			}

			// Send a final message to the client
			session.getBasicRemote().sendText("This is the last server message");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			session.close();
		}
    }

    @OnOpen
    public void onOpen() {
        System.out.println("Client connected");
    }

    @OnClose
    public void onClose() {
        System.out.println("Connection closed");
    }
}
