package LinkServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import GamePanel.Game;
import paramer.Config;

/**
 * 子弹与服务器数据交互
 * @author Xinhai Cao
 *
 */
public class BulletUDPServer extends Thread {
	
	static DatagramSocket client;
	static Game g = null;
	
	public BulletUDPServer(Game g) {
		try {
			BulletUDPServer.g = g;
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
				g.updateBullet(str);
			} catch (IOException e) {
			}
		}
	}
	/**
	 * 发送子弹数据
	 * @param id 子弹编号
	 * @param x 子弹位置
	 * @param y 子弹位置
	 * @param fangxiang 子弹方向
	 */
	public void send(String id,String x,String y,String fangxiang) {
		try {
			String data = id+","+x+","+y+","+fangxiang;
			DatagramPacket d = new DatagramPacket(data.getBytes(),data.getBytes().length,InetAddress.getByName(Config.IP),Config.bullet_port);
			client.send(d);
		} catch (Exception e) {
		}
	}
	
}
