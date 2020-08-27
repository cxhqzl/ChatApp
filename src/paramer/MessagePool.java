package paramer;

import java.util.HashMap;
import java.util.LinkedList;

import net.sf.json.JSONObject;
import pane.ChatWindow;
import pane.RefreshData;

/**
 * �洢��������������Ϣ
 * @author Xinhai Cao
 *
 */
public class MessagePool {
	
	private MessagePool() {
		
	}
	private static MessagePool messageStorage = new MessagePool();
	public static MessagePool getMessageStorage() {
		return messageStorage;
	}
	
	/**��Ϣ�洢��*/
	public static HashMap<String,LinkedList<Message>> hashMap = new HashMap<String,LinkedList<Message>>(); 
	
	/**��Ϣ��ʾ*/
	public void addMessage(String str) {
		JSONObject jsonObject = JSONObject.fromObject(str);
		String myUid = jsonObject.getString("myUid");
		String toUid = jsonObject.getString("toUid");
		String message = jsonObject.getString("msg");
		String code = jsonObject.getString("code");
		String type = jsonObject.getString("type");
		
		Message msg = new Message();
		msg.setCode(code);
		msg.setMsg(message);
		msg.setMyUid(myUid);
		msg.setToUid(toUid);
		msg.setType(type);
		
		try {
			ChatWindow chatWindow = Config.chatWindow.get(myUid);
			if(chatWindow.isVisible()) {
				chatWindow.addFriendMessage(msg);
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			RefreshData r = Config.list.get(myUid);
			r.addMessage(msg);
		}
	}
}
