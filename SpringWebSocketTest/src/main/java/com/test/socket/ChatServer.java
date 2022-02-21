package com.test.socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* 채팅 소켓 서버 */

@ServerEndpoint("/chatserver")
public class ChatServer {
	
	public Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/* 현재 채팅 서버에 접속한 클라이언트 목록 */
	// *static* 처리 유의! 
	private static List<Session> list = new ArrayList<Session>();
	
	@OnOpen
	public void handleOpen(Session session) {
		
		/* Session
		 *  - websocket에 있는 세션
		 * 	- import javax.websocket.Session
		 *  - 사용자 정보를 받아올 수 있음
		 */

		logger.info("클라이언트가 접속했습니다.");
		
		list.add(session);
	}
	
	
	@OnMessage
	public void handleMessage(String msg, Session session) {
		
		// 1#유저명 (채팅참여)
		// 2#유저명 (채팅종료)
		// 3#유저명#메시지
		// 4#유저명#이모티콘번호
		
		int index = -1;
		String no = "";
		String user = "";
		
		index = msg.indexOf('#');
		no = msg.substring(0, 1);
		user = msg.substring(index+1);
		
		
		/* #1 누군가가 접속 */
		if(no.equals("1")) {
			
			// 모든 접속자 중에서 메시지를 보낸 세션을 제외하고(방금 접속한 사람 빼고)
			for(Session s : list) {
				if(s != session) {
					
					// 다른 사람들에게 현재 접속자를 알리는 메시지 전달
					// 세션내 연결된 소켓들을 .getBasicRemote() 통해 얻어낼 수 있음
					// 이 소켓을 통해 메시지 전달
					try {
						s.getBasicRemote().sendText("1#" + user);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		/* #2 누군가가 종료 */
		else if(no.equals("2")) {
			
			// List에서 현재 세션 제거
			list.remove(session);
			
			// "누군가가 나갔습니다." 라는 메시지를 나머지 사람들에게 전달
			for(Session s : list) {
				try {
					s.getBasicRemote().sendText("2#" + user);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		/* #3 대화 메시지 전달 */
		else if(no.equals("3")) {
			
			for(Session s : list) {
				if(s != session) {
					try {
						s.getBasicRemote().sendText(msg); // 3#유저명#메시지
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		/* #4 이모티콘 전달 */
		else if(no.equals("4")) {
			
			for(Session s : list) {
				if(s != session) {
					try {
						s.getBasicRemote().sendText(msg); // 4#유저명#이모티콘번호
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	
	@OnClose
	public void handleClose() {
		logger.info("클라이언트와 연결이 종료되었습니다.");
		clearList();
	}

	
	@OnError
	public void handleError(Throwable e) {
		logger.info("에러 발생 " + e.getMessage());
		clearList();
	}
	
	
	/* 연결 끊긴 세션 제외 메소드 */
	private void clearList() {
		
		// List<Session> list
		for(int i = list.size()-1; i >=0; i--) {
			// 연결 끊긴 세션을 제외
			if(!list.get(i).isOpen())
				list.remove(list.get(i));
		}
	}
	
	

}
