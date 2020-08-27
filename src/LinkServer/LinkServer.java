package LinkServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import net.sf.json.JSONObject;
import paramer.Config;

/**
 * ����������ֳ�������
 * @author Xinhai Cao
 *
 */
public class LinkServer implements Runnable {
	
	private Socket socket = null;
	private InputStream in = null;
	private OutputStream out = null;
	private Thread thread = null;//�߳�
	/**�̴߳��ڱ�־*/
	private boolean run = false;
	
	private LinkServer() {
		
	}
	
	private static LinkServer linkServer = new LinkServer();
	/**��ȡ��������*/
	public static LinkServer getLinkServer() {
		return linkServer;
	}
	
	public void run() {
		try {
			byte[] bytes = new byte[1024*10];
			int length = 0;
			while(run) {
				//����������������ߺ����б�
				out.write("Q02".getBytes());
				out.flush();
				in.read();
				out.write(Config.analyse_friendData.getBytes());
				out.flush();
				length = in.read(bytes);
				String online = new String(bytes,0,length);
				System.out.println("�����˻���"+online);
				
				try {
					try {
						if(!Config.onlineFriendData.equals(online)) {
							Config.onlineFriendData = online;
							Config.friendList.Refresh();
						}
					}catch(Exception e) {}
				}catch(Exception e) {
					
				}
				Config.onlineFriendData = online;
				
				//�޸ĸ�������
				out.write("Q04".getBytes());
				out.flush();
				in.read();
				out.write(Config.personInfo.getBytes());
				out.flush();
				in.read();
				
				//������������¸�������
				out.write("Q03".getBytes());
				out.flush();
				//����
				length = in.read(bytes);
				Config.personInfo = new String(bytes,0,length);//���������Ϣ
				System.out.println("�������ϣ�"+Config.personInfo);
				try {
					Config.index.RefreshPerson();
				}catch(Exception e) {}
				try {
					//ÿ3��ˢ��һ��
					Thread.sleep(3000);
				} catch (InterruptedException e) {//�쳣ʱ�߳̽���
					e.printStackTrace();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			run = false;
		}
	}
	
	@SuppressWarnings("deprecation")
	public JSONObject login() throws IOException,UnknownHostException{
		socket = new Socket(Config.IP,Config.port);
		in = socket.getInputStream();
		out = socket.getOutputStream();
		String jsonData = "{\"uid\":\""+Config.uid+"\",\"password\":\""+Config.password+"\"}";
		//���ݸ�������
		out.write(jsonData.getBytes());
		out.flush();
		
		//�������ظ�
		byte[] bytes = new byte[1024];
		int length = in.read(bytes);
		jsonData = new String(bytes,0,length);
		JSONObject json = JSONObject.fromObject(jsonData);
		if(json.getInt("state")==0) {//��¼�ɹ�
			if(thread!=null) {//�̴߳���
				if(thread.getState()==Thread.State.RUNNABLE) {//���߳�����
					run = false;//��ֹ�߳�
					try {
						thread.stop();
					}catch(Exception e) {
						
					}
				}//�̴߳��ڴ������
			}//�̴߳��ڴ������
			
			//������������º����б�
			out.write("Q01".getBytes());
			out.flush();
			//����
			bytes = new byte[1024*10];
			length = in.read(bytes);
			Config.friendData = new String(bytes,0,length);
			Config.analyse_friendData = Config.Analyse_friendData(Config.friendData);
			System.out.println("�����б�"+Config.friendData);
			
			//������������¸�������
			out.write("Q03".getBytes());
			out.flush();
			//����
			length = in.read(bytes);
			Config.personInfo = new String(bytes,0,length);//���������Ϣ
			System.out.println("�������ϣ�"+Config.personInfo);
			
			//���������������ע���û�
			out.write("Q05".getBytes());
			out.flush();
			//����
			bytes = new byte[1024*10];
			length = in.read(bytes);
			Config.allUser = new String(bytes,0,length);
			System.out.println("��ע���û���"+Config.allUser);
			Config.analyse_allUser = Config.Analyse_friendData(Config.allUser);
			
			
			/*******************************************/
			//������Ϣ���ͺͽ��ܷ���
			Config.datagramSocket_client = new DatagramSocket();
			new MessageServer(Config.datagramSocket_client);
			new MessageRegServer(Config.datagramSocket_client);
			/********************************************/
			
			//���¿����߳�
			thread = new Thread(this);
			run = true;
			thread.start();
		}//��¼�ɹ��������
		
		return json;
	}
}
