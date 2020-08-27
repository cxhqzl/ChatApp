package LinkServer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import paramer.MessagePool;
/**
 * 接收消息
 * @author Xinhai Cao
 *
 */
public class MessageServer extends Thread{
	
	
	
	public void run() {
		while(true) {
			try {
				byte[] bytes = new byte[1024*10];
				DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
				client.receive(datagramPacket);
				String str = new String(datagramPacket.getData(),0,datagramPacket.getData().length);
				
				MessagePool.getMessageStorage().addMessage(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private DatagramSocket client = null;
	
	public MessageServer(DatagramSocket client) {
		this.client = client;
		this.start();
	}
	
}
