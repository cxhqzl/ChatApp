package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.painter.StandardGradientPainter;
import org.jvnet.substance.skin.FieldOfWheatSkin;
import org.jvnet.substance.skin.SubstanceCremeLookAndFeel;
import org.jvnet.substance.theme.SubstanceAquaTheme;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;

import LinkServer.LinkServer;
import net.sf.json.JSONObject;
import pane.Index;
import paramer.Config;
import paramer.WindowsXY;

public class Login extends JDialog implements ActionListener,WindowListener,KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTextField username;
	private JPasswordField password;
	private JTextField email;
	private JTextField code;
	private JPasswordField Zpassword;
	private JPasswordField Zpassword1;
	
	public Login() {
		super();
		setTitle("聊天室");
		setResizable(true);//设置对话框置顶显示
		this.setBounds(300, 300, 400, 300);//650
		this.setLocation(WindowsXY.getXY(this.getWidth(), this.getHeight()));//窗口居中打开
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8D26\u53F7");
		label.setBounds(79, 86, 30, 18);
		getContentPane().add(label);
		
		username = new JTextField();
		username.setBounds(123, 83, 190, 24);
		username.addKeyListener(this);
		getContentPane().add(username);
		username.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setBounds(79, 133, 30, 18);
		getContentPane().add(label_1);
		
		password = new JPasswordField();
		password.setBounds(123, 130, 190, 24);
		password.addKeyListener(this);
		getContentPane().add(password);
		
		JPanel panel = new JPanel();
		panel.setBounds(14, 306, 354, 292);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label_2 = new JLabel("\u90AE   \u7BB1");
		label_2.setBounds(43, 34, 60, 18);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u5BC6   \u7801");
		label_3.setBounds(43, 158, 60, 18);
		panel.add(label_3);
		
		JLabel lblNewLabel_1 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		lblNewLabel_1.setBounds(43, 203, 60, 18);
		panel.add(lblNewLabel_1);
		
		email = new JTextField();
		email.setBounds(117, 31, 188, 24);
		panel.add(email);
		email.setColumns(10);
		
		JButton btnNewButton = new JButton("注册");
		btnNewButton.setBounds(57, 178, 113, 42);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		JButton btnNewButton_1 = new JButton("登录");
		btnNewButton_1.setBounds(244, 178, 113, 42);
		getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		
		JButton btnNewButton_2 = new JButton("发送验证码");
		btnNewButton_2.setBounds(198, 68, 107, 27);
		panel.add(btnNewButton_2);
		btnNewButton_2.addActionListener(this);
		
		JButton btnNewButton_3 = new JButton("取消");
		btnNewButton_3.setBounds(53, 234, 113, 45);
		panel.add(btnNewButton_3);
		btnNewButton_3.addActionListener(this);
		
		JButton btnNewButton_4 = new JButton("确认");
		btnNewButton_4.setBounds(227, 235, 113, 42);
		panel.add(btnNewButton_4);
		btnNewButton_4.addActionListener(this);
		
		JLabel label_4 = new JLabel("\u9A8C\u8BC1\u7801");
		label_4.setBounds(43, 113, 60, 18);
		panel.add(label_4);
		
		code = new JTextField();
		code.setBounds(117, 110, 86, 24);
		panel.add(code);
		code.setColumns(10);
		
		Zpassword = new JPasswordField();
		Zpassword.setBounds(117, 155, 188, 24);
		panel.add(Zpassword);
		
		Zpassword1 = new JPasswordField();
		Zpassword1.setBounds(117, 200, 188, 24);
		panel.add(Zpassword1);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u6CE8\u518C");
		lblNewLabel.setBounds(14, 283, 72, 18);
		getContentPane().add(lblNewLabel);
		
		this.addWindowListener(this);
		this.addKeyListener(this);
	}
	public static void main(String[] args) {
		try {
            UIManager.setLookAndFeel(new SubstanceCremeLookAndFeel());
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            SubstanceLookAndFeel.setCurrentTheme(new SubstanceAquaTheme());
            SubstanceLookAndFeel.setSkin(new FieldOfWheatSkin());
            SubstanceLookAndFeel.setCurrentButtonShaper(new ClassicButtonShaper());
            SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBubblesWatermark());
            SubstanceLookAndFeel.setCurrentBorderPainter(new StandardBorderPainter());
            SubstanceLookAndFeel.setCurrentGradientPainter(new StandardGradientPainter());
        } catch (Exception e) {
            
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login login = new Login();
					login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.requestFocus();
		if(arg0.getActionCommand().equals("注册")) {
			if(Login.this.getHeight()==300) {
				Login.this.setSize(400, 650);
			}else {
				Login.this.setSize(400, 300);
			}
			this.setLocation(WindowsXY.getXY(this.getWidth(), this.getHeight()));//窗口居中打开
		}else if(arg0.getActionCommand().equals("登录")) {
			String uid = username.getText().trim();
			String Password = String.valueOf(password.getPassword());
			if(username.getText().trim().equals("")||password.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(Login.this,"输入不能为空！");
				return;
			}
			Config.uid = uid;
			Config.password = Password;
			try {
				JSONObject json = LinkServer.getLinkServer().login();
				if(json.getInt("state")==0) {
					if(json.getString("msg").equals("密码错误！")||json.getString("msg").equals("账户被冻结！")||json.getString("msg").equals("用户不存在！")) {
						JOptionPane.showMessageDialog(Login.this,json.getString("msg"));
					}else {//登录成功
						Config.index = new Index();
						Config.index.setVisible(true);
						Login.this.setVisible(false);
						Login.this.dispose();
					}
				}
				else {
					JOptionPane.showMessageDialog(Login.this,"用户被冻结！");
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(Login.this,"网络连接失败！");
			} 
		}else if(arg0.getActionCommand().equals("发送验证码")) {
			try {
				Socket socket = new Socket(Config.IP,Config.r_port);
				InputStream in =  socket.getInputStream();
				OutputStream out = socket.getOutputStream();
				
				out.write(("{\"type\":\"code\",\"username\":\""+email.getText()+"\"}").getBytes());
				out.flush();
				
				byte[] bytes = new byte[1024];
				int length = in.read(bytes);
				String str = new String(bytes,0,length);
				JSONObject json = JSONObject.fromObject(str);
				if(json.getInt("state")==0) {//发送成功
					JOptionPane.showMessageDialog(Login.this,"发送成功！");
				}else {//发送失败
					JOptionPane.showMessageDialog(Login.this,"发送失败，请重新发送！");
				}
				
				in.close();
				out.close();
				socket.close();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(arg0.getActionCommand().equals("确认")) {
			/************************ 提交前进行判断 *******************/
			if(email.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(Login.this,"邮箱不能为空！");
				return;
			}
			if(email.getText().trim().indexOf("@")==-1||email.getText().trim().indexOf("@")==0||email.getText().trim().indexOf("@")==(email.getText().trim().length()-1)){
				JOptionPane.showMessageDialog(Login.this,"邮箱格式不正确！");
				return;
			}
			if(code.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(Login.this,"验证码不能为空！");
				return;
			}
			if(Zpassword.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(Login.this,"密码不能为空！");
				return;
			}
			if(Zpassword1.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(Login.this,"确认密码不能为空！");
				return;
			}
			if(!(Zpassword.getText().trim().equals(Zpassword1.getText().trim()))) {
				JOptionPane.showMessageDialog(Login.this,"两次密码输入不一致！");
				return;
			}
			/******************** 判断结束 ****************************/
			Socket socket;
			try {
				socket = new Socket(Config.IP,Config.r_port);
				InputStream in = socket.getInputStream();
				OutputStream out = socket.getOutputStream();
				out.write(("{\"type\":\"reg\",\"username\":\""+email.getText()+"\",\"password\":\""+Zpassword.getText()+"\",\"code\":\""+code.getText()+"\"}").getBytes());
				out.flush();
				
				byte[] bytes = new byte[1024];
				int length = in.read(bytes);
				String str = new String(bytes,0,length);
				JSONObject json = JSONObject.fromObject(str);
				if(json.getInt("state")==1) {//用户已存在
					JOptionPane.showMessageDialog(Login.this,json.getString("msg"));
				}else if(json.getInt("state")==0) {//注册成功
					JOptionPane.showMessageDialog(Login.this,json.getString("msg"));
					email.setText("");
					code.setText("");
					Zpassword.setText("");
					Zpassword1.setText("");
				}
				
				in.close();
				out.close();
				socket.close();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
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
		if(arg0.getKeyChar()==KeyEvent.VK_ENTER) {
			String uid = username.getText().trim();
			String Password = String.valueOf(password.getPassword());
			if(username.getText().trim().equals("")||password.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(Login.this,"输入不能为空！");
				return;
			}
			Config.uid = uid;
			Config.password = Password;
			try {
				JSONObject json = LinkServer.getLinkServer().login();
				if(json.getInt("state")==0) {
					if(json.getString("msg").equals("密码错误！")||json.getString("msg").equals("账户被冻结！")||json.getString("msg").equals("用户不存在！")) {
						JOptionPane.showMessageDialog(Login.this,json.getString("msg"));
					}else {//登录成功
						Config.index = new Index();
						Config.index.setVisible(true);
						Login.this.setVisible(false);
						Login.this.dispose();
					}
				}
				else {
					JOptionPane.showMessageDialog(Login.this,"用户被冻结！");
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(Login.this,"网络连接失败！");
			} 
		}
	}
}
