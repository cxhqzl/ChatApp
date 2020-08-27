package LinkServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import GamePanel.Game;
import paramer.Config;

/**
 * �ӵ�����������ݽ���
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
	 * �����ӵ�����
	 * @param id �ӵ����
	 * @param x �ӵ�λ��
	 * @param y �ӵ�λ��
	 * @param fangxiang �ӵ�����
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
