package pane;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import paramer.Config;
import paramer.Message;

/*import sun.audio.AudioPlayer;
import sun.audio.AudioStream;*/
/**
 * ˢ���б�����
 * @author Xinhai Cao
 *
 */
public class RefreshData extends JPanel implements Comparable<RefreshData>,MouseListener,Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String image;
	private String netName;
	private String info;
	private String uid;
	private boolean online = false;
	/**ͷ��*/
	private JLabel lblNewLabel;
	/**����*/
	private JLabel label;
	/**ǩ��*/
	private JLabel label_1;
	
	public RefreshData(String image,String netName,String info,String uid) {
		this.image = image;
		this.netName = netName;
		this.info = info;
		this.uid = uid;
		
		this.setLayout(null);
		
		
		lblNewLabel = new JLabel();
		lblNewLabel.setBounds(4, 4, 48, 48);
		add(lblNewLabel);
		setImage(image);
		
		label = new JLabel();
		label.setFont(new Font("����", Font.PLAIN, 25));
		label.setBounds(58, 4, 478, 30);
		add(label);
		label.setText(netName);
		
		label_1 = new JLabel();
		label_1.setBounds(58, 34, 478, 18);
		add(label_1);
		label_1.setText(info);
		
		this.addMouseListener(this);
	}
	/**�޸�ͷ��*/
	public void setImage(String image) {
		if(online) {
			lblNewLabel.setIcon(new ImageIcon("face0/"+image+".png"));
		}else {
			lblNewLabel.setIcon(new ImageIcon("face1/"+image+".png"));
		}
		this.image = image;
	}
	/**�޸�����*/
	public void setNetName(String netName) {
		label.setText(netName);
		this.netName = netName;
	}
	/**�޸�ǩ��*/
	public void setInfo(String info) {
		label_1.setText(info);
		this.info = info;
	}
	/**�����Ƿ�����*/
	public void setOnline(boolean online) {
		this.online = online;
		if(online) {
			lblNewLabel.setIcon(new ImageIcon("face0/"+image+".png"));
		}else {
			lblNewLabel.setIcon(new ImageIcon("face1/"+image+".png"));
		}
	}
	
	/**��Ϣ�Ĵ���*/
	private Vector<Message> msgs = new Vector<Message>();
	
	private Thread thread = null;
	/**��Ϣ���ѱ�־*/
	private boolean flag = true;
	@Override
	public void run() {
		flag = true;
		playMessageMusic();
		int x = this.getX();
		int y = this.getY();
		
		while(flag) {//ͷ������
			this.setLocation(x-1,y-1);
			try {
				Thread.sleep(300);
			}catch(Exception e) {}
			this.setLocation(x+2,y+2);
			try {
				Thread.sleep(300);
			}catch(Exception e) {}
		}
		
		this.setLocation(x, y);//�ָ�
	}
	
	
	/**��Ϣ�Ĵ�*/
	public void addMessage(Message msg) {
		msgs.add(msg);
		if(thread==null||thread.getState()==Thread.State.TERMINATED||!flag) {
			thread = new Thread(this);
			thread.start();
		}else {
			thread = null;
			thread = new Thread(this);
			thread.start();
		}
//		if(thread==null) {
//			thread = new Thread(this);
//			thread.start();
//		}else if(thread.getState()==Thread.State.TERMINATED){
//			thread = new Thread(this);
//			thread.start();
//		}else if(!flag) {
//			thread = new Thread(this);
//			thread.start();
//		}
	}
	
	/**������ʾ����*/
	@Override
	public int compareTo(RefreshData r) {
		if(r.online) {
			return 1;
		}else if(this.online){
			return -1;
		}else {
			return 0;
		}
	}
	/**��Ϣ����*/
	@SuppressWarnings("restriction")
	public void playMessageMusic() {
		try {
			FileInputStream fileau=new FileInputStream("18.wav" );
			/*
			 * AudioStream as = new AudioStream(fileau); AudioPlayer.player.start(as);
			 */ 
	    }catch (Exception e){}  
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2) {
			if(online) {
				flag = false;//ֹͣͷ������
				Config.showChatWindow(uid, netName, image, info,msgs);
				System.out.println("��ִ��");
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
}
