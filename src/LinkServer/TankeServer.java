package LinkServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import GamePanel.Game;
import paramer.Config;

/**
 * ̹�˶�����������ݽ���
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
	 * ��������
	 * @param username �û�
	 * @param x ̹��Xλ��
	 * @param y ̹��Yλ��
	 * @param fangxiang ̹�˷���
	 * @param life ̹������ֵ
	 * @param source ����
	 * @param id ̹�˱��
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
	 * ���������������ֵ
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
