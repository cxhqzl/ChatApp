package pane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import GamePanel.Game;
import LinkServer.TankeLoginServer;
import net.sf.json.JSONObject;
import paramer.Config;
import paramer.WindowsXY;
/**
 * 登录后主页面
 * @author Xinhai Cao
 *
 */
public class Index extends JFrame implements ActionListener,MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**头像*/
	private JLabel myImage = new JLabel();
	/**网名*/
	JLabel myNetName = new JLabel();
	/**签名*/
	JLabel myInfo = new JLabel();
	JLabel label;

	public Index() {
		super();
		setTitle("聊天室");
		this.setBounds(400, 400, 400, 800);
		this.setLocation(WindowsXY.getXY(this.getWidth(), this.getHeight()));//窗口居中打开
		getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(14, 66, 368, 644);
		getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("联系人", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(new FriendList());
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(14, 13, 368, 51);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		myImage.setBounds(0, 0, 48, 48);
		panel_2.add(myImage);
		
		myNetName.setFont(new Font("宋体", Font.PLAIN, 25));
		myNetName.setBounds(62, 0, 204, 30);
		panel_2.add(myNetName);
		
		myInfo.setBounds(62, 28, 204, 18);
		panel_2.add(myInfo);
		
		JButton button = new JButton("\u5766\u514B\u5927\u6218");
		button.setBounds(14, 713, 150, 30);
		getContentPane().add(button);
		button.addActionListener(this);
		
		JButton button_1 = new JButton("\u9000\u51FA\u767B\u5F55");
		button_1.setBounds(232, 713, 150, 30);
		getContentPane().add(button_1);
		
		label = new JLabel(new ImageIcon("message.png"));
		label.setBounds(190, 720, 16, 16);
		getContentPane().add(label);
		button_1.addActionListener(this);
		label.addMouseListener(this);
		
		myImage.addMouseListener(this);
		
		RefreshPerson();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.requestFocus();
		if(e.getActionCommand().equals("退出登录")) {
			System.exit(0);
		}else if(e.getActionCommand().equals("坦克大战")) {
			try {
				new TankeLoginServer(Config.IP,Config.uid);
				Game game = new Game();
				game.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this,e1.getMessage());
			}
		}
	}
	
	/**更新个人信息*/
	public void RefreshPerson() {
		JSONObject jsonObject = JSONObject.fromObject(Config.personInfo);
		myImage.setIcon(new ImageIcon("face0/"+jsonObject.getString("image")+".png"));
		myNetName.setText(jsonObject.getString("netName"));
		myInfo.setText(jsonObject.getString("info"));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==myImage) {
			if(e.getClickCount()==2) {
				if(Config.personalData==null) {
					Config.personalData = new PersonalData();
					JSONObject jsonO = JSONObject.fromObject(Config.personInfo);
					Config.personalData.refreshData(jsonO.getString("image"), jsonO.getString("netName"), jsonO.getString("info"), jsonO.getString("name"), jsonO.getString("sex"), jsonO.getInt("yy"), jsonO.getInt("mm"), jsonO.getInt("dd"), jsonO.getString("phoneNumber"),jsonO.getString("uid"));
				}else {
					Config.personalData.setVisible(true);
				}
			}
		}else if(e.getSource()==label) {
			if(e.getClickCount()==2) {
				
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
