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
 * 与服务器保持持续连接
 * @author Xinhai Cao
 *
 */
public class LinkServer implements Runnable {
	
	private Socket socket = null;
	private InputStream in = null;
	private OutputStream out = null;
	private Thread thread = null;//线程
	/**线程存在标志*/
	private boolean run = false;
	
	private LinkServer() {
		
	}
	
	private static LinkServer linkServer = new LinkServer();
	/**获取单例对象*/
	public static LinkServer getLinkServer() {
		return linkServer;
	}
	
	public void run() {
		try {
			byte[] bytes = new byte[1024*10];
			int length = 0;
			while(run) {
				//请求服务器更新在线好友列表
				out.write("Q02".getBytes());
				out.flush();
				in.read();
				out.write(Config.analyse_friendData.getBytes());
				out.flush();
				length = in.read(bytes);
				String online = new String(bytes,0,length);
				System.out.println("在线账户："+online);
				
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
				
				//修改个人资料
				out.write("Q04".getBytes());
				out.flush();
				in.read();
				out.write(Config.personInfo.getBytes());
				out.flush();
				in.read();
				
				//请求服务器更新个人资料
				out.write("Q03".getBytes());
				out.flush();
				//接收
				length = in.read(bytes);
				Config.personInfo = new String(bytes,0,length);//保存好友信息
				System.out.println("个人资料："+Config.personInfo);
				try {
					Config.index.RefreshPerson();
				}catch(Exception e) {}
				try {
					//每3秒刷新一次
					Thread.sleep(3000);
				} catch (InterruptedException e) {//异常时线程结束
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
		//传递给服务器
		out.write(jsonData.getBytes());
		out.flush();
		
		//服务器回复
		byte[] bytes = new byte[1024];
		int length = in.read(bytes);
		jsonData = new String(bytes,0,length);
		JSONObject json = JSONObject.fromObject(jsonData);
		if(json.getInt("state")==0) {//登录成功
			if(thread!=null) {//线程存在
				if(thread.getState()==Thread.State.RUNNABLE) {//若线程运行
					run = false;//终止线程
					try {
						thread.stop();
					}catch(Exception e) {
						
					}
				}//线程存在处理结束
			}//线程存在处理结束
			
			//请求服务器更新好友列表
			out.write("Q01".getBytes());
			out.flush();
			//接收
			bytes = new byte[1024*10];
			length = in.read(bytes);
			Config.friendData = new String(bytes,0,length);
			Config.analyse_friendData = Config.Analyse_friendData(Config.friendData);
			System.out.println("好友列表："+Config.friendData);
			
			//请求服务器更新个人资料
			out.write("Q03".getBytes());
			out.flush();
			//接收
			length = in.read(bytes);
			Config.personInfo = new String(bytes,0,length);//保存好友信息
			System.out.println("个人资料："+Config.personInfo);
			
			//请求服务器更新已注册用户
			out.write("Q05".getBytes());
			out.flush();
			//接收
			bytes = new byte[1024*10];
			length = in.read(bytes);
			Config.allUser = new String(bytes,0,length);
			System.out.println("已注册用户："+Config.allUser);
			Config.analyse_allUser = Config.Analyse_friendData(Config.allUser);
			
			
			/*******************************************/
			//启动消息发送和接受服务
			Config.datagramSocket_client = new DatagramSocket();
			new MessageServer(Config.datagramSocket_client);
			new MessageRegServer(Config.datagramSocket_client);
			/********************************************/
			
			//重新开启线程
			thread = new Thread(this);
			run = true;
			thread.start();
		}//登录成功处理结束
		
		return json;
	}
}
