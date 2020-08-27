package pane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import net.sf.json.JSONObject;
import paramer.Config;
import paramer.Message;
import paramer.WindowsXY;

public class ChatWindow extends JFrame implements ActionListener,WindowListener,KeyListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel Image = new JLabel(new ImageIcon("jiqi.jpg"));
	JLabel NetName = new JLabel();
	JLabel Info = new JLabel();
	private String uid;
	private String netName;
	@SuppressWarnings("unused")
	private String image;
	@SuppressWarnings("unused")
	private String info;
	JTextArea textArea;
	JTextArea textArea_1;
	
	/**
	 * 
	 * @param uid uid
	 * @param netName netName
	 * @param image image
	 * @param info info
	 */
	public ChatWindow(String uid,String netName,String image,String info,Vector<Message> msgs) {
		this.uid = uid;
		this.image = image;
		this.netName = netName;
		this.info = info;
		
		Image.setIcon(new ImageIcon("face0/"+image+".png"));
		NetName.setText(netName);
		Info.setText(info);
		this.setTitle(netName);
		
		this.setSize(800, 660);
		this.setLocation(WindowsXY.getXY(this.getWidth(), this.getHeight()));//窗口居中打开
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 782, 74);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		Image.setBounds(14, 13, 48, 48);
		panel.add(Image);
		
		NetName.setFont(new Font("宋体", Font.PLAIN, 25));
		NetName.setBounds(76, 13, 644, 30);
		panel.add(NetName);
		
		Info.setBounds(76, 44, 644, 18);
		panel.add(Info);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBounds(10, 87, 772, 526);
		getContentPane().add(splitPane);
		
		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton button = new JButton("\u53D1\u9001");
		panel_2.add(button);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		button.addActionListener(this);
		textArea.addKeyListener(this);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setLeftComponent(scrollPane_1);
		
		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		splitPane.setDividerLocation(350);
		textArea_1.setEditable(false);
		
		this.addWindowListener(this);
		this.setVisible(true);
		
		for(Message msg : msgs) {
			addFriendMessage(msg);
		}
		msgs.clear();
	}
	/**向聊天框添加好友的消息*/
	public void addFriendMessage(Message msg) {
		Date nowTime=new Date(); 
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String dateStr = time.format(nowTime);
		String str = "\n"+netName+"\t"+dateStr+"\n"+msg.getMsg()+"\n";
		textArea_1.setText(textArea_1.getText()+str);
		textArea_1.setSelectionStart(textArea_1.getText().toString().length());
		textArea_1.setSelectionEnd(textArea_1.getText().toString().length());
	}
	/**向聊天框添加自己的消息*/
	public void addPersonMessage(Message msg) {
		Date nowTime=new Date(); 
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String dateStr = time.format(nowTime);
		String str = "\n"+JSONObject.fromObject(Config.personInfo).getString("netName")+"\t"+dateStr+"\n"+msg.getMsg()+"\n";
		textArea_1.setText(textArea_1.getText()+str);
		textArea_1.setSelectionStart(textArea_1.getText().toString().length());
		textArea_1.setSelectionEnd(textArea_1.getText().toString().length());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		textArea.requestFocus();
		
		if(e.getActionCommand().equals("发送")) {
			try {
				if(textArea.getText().equals("")||textArea.getText()==null) {
					return;
				}
				Message msg = new Message();
				msg.setCode(System.currentTimeMillis()+"");
				msg.setMsg(textArea.getText());
				msg.setMyUid(JSONObject.fromObject(Config.personInfo).getString("uid"));
				msg.setToUid(uid);
				msg.setType("message");
				String json = JSONObject.fromObject(msg).toString();
				byte[] bytes = json.getBytes();
				DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length,InetAddress.getByName(Config.IP),8887);
				Config.datagramSocket_client.send(datagramPacket);
				textArea.setText("");
				addPersonMessage(msg);
				textArea.requestFocus();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		
	}
	
	@Override
	public void windowClosing(WindowEvent arg0) {
		Config.closeChatWindow(uid);
		this.dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyChar()==KeyEvent.VK_ENTER) {
			try {
				if(textArea.getText().equals("")||textArea.getText()==null) {
					return;
				}
				Message msg = new Message();
				msg.setCode(System.currentTimeMillis()+"");
				msg.setMsg(textArea.getText());
				msg.setMyUid(JSONObject.fromObject(Config.personInfo).getString("uid"));
				msg.setToUid(uid);
				msg.setType("message");
				String json = JSONObject.fromObject(msg).toString();
				byte[] bytes = json.getBytes();
				DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length,InetAddress.getByName(Config.IP),8887);
				Config.datagramSocket_client.send(datagramPacket);
				textArea.setText("");
				addPersonMessage(msg);
				textArea.requestFocus();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
