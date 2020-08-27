package LinkServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import GamePanel.Game;
import paramer.Config;

/**
 * 坦克端与服务器数据交互
 * @author Xinhai Cao
 *
 */
public class TankeServer extends Thread {
	
	static DatagramSocket client;
	static Game g = null;
	
	public TankeServer(Game g) {
		try {
			TankeServer.g = g;
			client = new DatagramSocket();
		} catch (SocketException e) {
		}
		
		this.start();
	}
	
	public void run() {
		
		while(true) {
			try {
				byte[] b = new byte[1024*50];
				DatagramPacket data = new DatagramPacket(b,b.length);
				client.receive(data);
				String str = (new String(data.getData()).trim());
				g.Update(str);
			} catch (IOException e) {
			}
		}
	}
	/**
	 * 发送数据
	 * @param username 用户
	 * @param x 坦克X位置
	 * @param y 坦克Y位置
	 * @param fangxiang 坦克方向
	 * @param life 坦克生命值
	 * @param source 分数
	 * @param id 坦克编号
	 */
	public void send(String username,String x,String y,String fangxiang,int life,int source,int id) {
		try {
			String data = username+","+x+","+y+","+fangxiang+","+life+","+source+","+id;
			DatagramPacket d = new DatagramPacket(data.getBytes(),data.getBytes().length,InetAddress.getByName(Config.IP),Config.tanke_port);
			client.send(d);
		} catch (Exception e) {
		}
	}
	/**
	 * 向服务器更新生命值
	 * @param username
	 * @param life
	 */
	public void send(String username) {
		try {
			String data = "LIFE,"+username;
			DatagramPacket d = new DatagramPacket(data.getBytes(),data.getBytes().length,InetAddress.getByName(Config.IP),Config.tanke_port);
			client.send(d);
		}catch(Exception e) {}
	}
	
}
