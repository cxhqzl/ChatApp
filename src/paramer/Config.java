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
 * �������������
 * @author Xinhai Cao
 *
 */
public class Config {
	/***********************************�������*******************************************************************/
	/**��������ַ*/
	public static final String IP = "127.0.0.1";
	/**��¼�˿�*/
	public static final int port = 8888;
	/**ע��˿�*/
	public static final int r_port = 8889;
	/**UDP�������˿�*/
	public static final int udp_port = 8887;
	/**̹�˷������˿�*/
	public static final int tanke_port = 8886;
	/**�ӵ��������˿�*/
	public static final int bullet_port = 8885;
	/**�û�IP*/
	public static String user_IP = "";
	/**�û���*/
	public static String uid = "";
	/**����*/
	public static String password = "";
	/**������Ϣ*/
	public static String friendData = "";
	/**�洢������ĺ����б�����*/
	public static String analyse_friendData = "";
	/**��������*///{"dd":21,"image":"5","info":"���첻��","mm":2,"name":"","netName":"���","phoneNumber":"123456789","sex":"��","uid":"123456","yy":1998}
	public static String personInfo = "";
	/**���ߺ���*/
	public static String onlineFriendData = "";
	/**�����б�Panel*/
	public static FriendList friendList = null;
	/**��ע���û�*/
	public static String allUser = "";
	/**�洢�������ע���û�����*/
	public static String analyse_allUser = "";
	/**UDP���ͺͽ��ܶ�*/
	public static DatagramSocket datagramSocket_client = null;
	/**�洢����*/
	public static Hashtable<String,RefreshData> list = new Hashtable<String,RefreshData>();
	/**�洢�໥���û�������*/
	public static HashMap<String,JSONObject> userJson = new HashMap<String,JSONObject>();
	/**�洢���д򿪵�Index����*/
	public static Index index = null;
	/**�������ϴ���*/
	public static PersonalData personalData = null;
	/**************************************************************************************************/
	
	/************************************̹�˲���*****************************************************/
	/**̹���ƶ��ٶ�*/
	public static int Tspeed = 20;
	/**�ӵ��ƶ��ٶ�*/
	public static int Bspeed = 5;
	/**̹�˷���*/
	public static String Tfangxiang = "W";
	/**̹����������*/
	public static TankeLoginServer tankeLoginServer = null;
	/**̹������ֵ*/
	public static int life = 100;
	/**̹�˱��*/
	public static int id = 1;
	/**��¼�������������*/
	public static int number = 1;
	/**�ɼ�*/
	public static int source = 0;
	/**�����û��ɼ�*/
	public static String sources = "";
	/**���а���ʾ��*/
	public static JTextArea textArea = null;
	/**���а�����*/
	public static JScrollPane scrollPane = null;
	/**��������*/
	public static int onlinePeople = 0;
	/***************************************************************************************************/
	
	/**���������б�����*/
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
	/**�Ǽ��Ѿ��򿪵����촰��*/
	public static Hashtable<String,ChatWindow> chatWindow = new Hashtable<String,ChatWindow>();
	/**��ʾ���촰��*/
	public static void showChatWindow(String uid,String netName,String image,String info,Vector<Message> msgs) {
		
		if(chatWindow.get(uid)==null) {//Ϊ�򿪴����´���
			ChatWindow c = new ChatWindow(uid, netName, image, info,msgs);
			chatWindow.put(uid, c);
		}else {//�Ѵ����ÿɼ�
			chatWindow.get(uid).setVisible(true);
		}
	}
	/**�ر����촰��*/
	public static void closeChatWindow(String uid) {
		chatWindow.remove(uid);
	}
	/**ˢ�����а�*/
	public static void refreshData() {
		String[] title = {"����","�û���","�˺�","����"};
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
