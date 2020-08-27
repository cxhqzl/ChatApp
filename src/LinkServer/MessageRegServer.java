package LinkServer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import net.sf.json.JSONObject;
import paramer.Config;
/**
 * ������Ϣ,ȷ��ͨ�Ŷ˿ڲ��Ͽ�
 * @author Xinhai Cao
 *
 */
public class MessageRegServer extends Thread{
	
	private DatagramSocket client = null;
	
	public MessageRegServer(DatagramSocket client) {
		this.client = client;
		this.start();
	}
	
	public void run() {
		String uid = JSONObject.fromObject(Config.personInfo).getString("uid");
		String jsonData = "{\"type\":\"req\",\"myUid\":\""+uid+"\"}";
		byte[] bytes = jsonData.getBytes();
		
		while(true) {
			try {
				DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length,InetAddress.getByName(Config.IP),8887);
				client.send(datagramPacket);
				Config.user_IP = datagramPacket.getAddress().getHostAddress();
				//10������һ��
				Thread.sleep(10000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
