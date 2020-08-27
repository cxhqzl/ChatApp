package paramer;

import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import LinkServer.TankeLoginServer;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pane.ChatWindow;
import pane.FriendList;
import pane.Index;
import pane.PersonalData;
import pane.RefreshData;

/**
 * 服务器相关数据
 * @author Xinhai Cao
 *
 */
public class Config {
	/***********************************聊天参数*******************************************************************/
	/**服务器地址*/
	public static final String IP = "127.0.0.1";
	/**登录端口*/
	public static final int port = 8888;
	/**注册端口*/
	public static final int r_port = 8889;
	/**UDP服务器端口*/
	public static final int udp_port = 8887;
	/**坦克服务器端口*/
	public static final int tanke_port = 8886;
	/**子弹服务器端口*/
	public static final int bullet_port = 8885;
	/**用户IP*/
	public static String user_IP = "";
	/**用户名*/
	public static String uid = "";
	/**密码*/
	public static String password = "";
	/**好友信息*/
	public static String friendData = "";
	/**存储解析后的好友列表数据*/
	public static String analyse_friendData = "";
	/**个人资料*///{"dd":21,"image":"5","info":"今天不错","mm":2,"name":"","netName":"大哥","phoneNumber":"123456789","sex":"男","uid":"123456","yy":1998}
	public static String personInfo = "";
	/**在线好友*/
	public static String onlineFriendData = "";
	/**好友列表Panel*/
	public static FriendList friendList = null;
	/**已注册用户*/
	public static String allUser = "";
	/**存储解析后的注册用户数据*/
	public static String analyse_allUser = "";
	/**UDP发送和接受端*/
	public static DatagramSocket datagramSocket_client = null;
	/**存储好友*/
	public static Hashtable<String,RefreshData> list = new Hashtable<String,RefreshData>();
	/**存储相互侧用户的资料*/
	public static HashMap<String,JSONObject> userJson = new HashMap<String,JSONObject>();
	/**存储所有打开的Index窗口*/
	public static Index index = null;
	/**个人资料窗口*/
	public static PersonalData personalData = null;
	/**************************************************************************************************/
	
	/************************************坦克参数*****************************************************/
	/**坦克移动速度*/
	public static int Tspeed = 20;
	/**子弹移动速度*/
	public static int Bspeed = 5;
	/**坦克方向*/
	public static String Tfangxiang = "W";
	/**坦克在线连接*/
	public static TankeLoginServer tankeLoginServer = null;
	/**坦克生命值*/
	public static int life = 100;
	/**坦克编号*/
	public static int id = 1;
	/**登录到服务器的序号*/
	public static int number = 1;
	/**成绩*/
	public static int source = 0;
	/**所有用户成绩*/
	public static String sources = "";
	/**排行榜显示框*/
	public static JTextArea textArea = null;
	/**排行榜容器*/
	public static JScrollPane scrollPane = null;
	/**在线人数*/
	public static int onlinePeople = 0;
	/***************************************************************************************************/
	
	/**解析好友列表数据*/
	public static String Analyse_friendData(String str) {
		JSONArray json = JSONArray.fromObject(str);
		StringBuffer data = new StringBuffer();
		for(int i=0;i<json.size();i++) {
			JSONObject jsonobj = (JSONObject) json.get(i);
			userJson.put(jsonobj.getString("uid"), jsonobj);
			data.append(jsonobj.getString("uid"));
			data.append(",");
		}
		return data.toString();
	}
	/**登记已经打开的聊天窗口*/
	public static Hashtable<String,ChatWindow> chatWindow = new Hashtable<String,ChatWindow>();
	/**显示聊天窗口*/
	public static void showChatWindow(String uid,String netName,String image,String info,Vector<Message> msgs) {
		
		if(chatWindow.get(uid)==null) {//为打开创建新窗口
			ChatWindow c = new ChatWindow(uid, netName, image, info,msgs);
			chatWindow.put(uid, c);
		}else {//已打开设置可见
			chatWindow.get(uid).setVisible(true);
		}
	}
	/**关闭聊天窗口*/
	public static void closeChatWindow(String uid) {
		chatWindow.remove(uid);
	}
	/**刷新排行榜*/
	public static void refreshData() {
		String[] title = {"名次","用户名","账号","分数"};
		DefaultTableModel model = new DefaultTableModel();
		model.setRowCount(0);
		model.fireTableDataChanged();
		model.setColumnIdentifiers(title);
		
		System.out.println(Config.sources);
		JSONArray json = JSONArray.fromObject(Config.sources);
		for(int i=0;i<json.size();i++) {
			JSONObject jsonobj = (JSONObject) json.get(i);
			if(jsonobj.getString("uid").equals(Config.uid)) {
				Config.source = jsonobj.getInt("source");
			}
			model.addRow(new Object[] {i+1,jsonobj.getString("name"),jsonobj.getString("uid"),jsonobj.getInt("source")});
		}
		
		model.fireTableDataChanged();
		JTable table = new JTable(model);
		scrollPane.setViewportView(table);
	}
}
