package pane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import paramer.Config;
import paramer.WindowsXY;

public class PersonalData extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField info;
	private JTextField netName;
	private JTextField name;
	private JTextField phone;
	JLabel image;
	JComboBox<String> yy;
	JComboBox<String> mm;
	JComboBox<String> dd;
	JRadioButton sexM;
	JRadioButton sexW;
	String IMAGE;
	String UID;
	
	public PersonalData() {
		super();
		setTitle("个人资料");
		setResizable(true);//设置对话框置顶显示
		this.setSize(800, 660);
		this.setLocation(WindowsXY.getXY(this.getWidth(), this.getHeight()));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(14, 13, 754, 108);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		image = new JLabel(new ImageIcon("jiqi.jpg"));
		image.setBounds(14, 13, 85, 85);
		panel.add(image);
		
		netName = new JTextField();
		netName.setBounds(113, 13, 613, 29);
		panel.add(netName);
		netName.setColumns(10);
		
		info = new JTextField();
		info.setBounds(113, 69, 613, 29);
		panel.add(info);
		info.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(14, 176, 754, 424);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("\u771F\u5B9E\u59D3\u540D");
		label_1.setBounds(48, 39, 60, 18);
		panel_1.add(label_1);
		
		name = new JTextField();
		name.setBounds(122, 36, 152, 24);
		panel_1.add(name);
		name.setColumns(10);
		
		JLabel label_2 = new JLabel("\u6027\u522B");
		label_2.setBounds(371, 39, 41, 18);
		panel_1.add(label_2);
		
		sexM = new JRadioButton("\u7537");
		sexM.setSelected(true);
		sexM.setBounds(422, 35, 54, 27);
		panel_1.add(sexM);
		
		sexW = new JRadioButton("\u5973");
		sexW.setBounds(482, 35, 52, 27);
		panel_1.add(sexW);
		
		JLabel label_3 = new JLabel("\u51FA\u751F\u65E5\u671F");
		label_3.setBounds(48, 101, 60, 18);
		panel_1.add(label_3);
		
		yy = new JComboBox<String>();
		yy.setBounds(122, 98, 77, 24);
		panel_1.add(yy);
		yy.addItem("1997");
		
		JLabel label_4 = new JLabel("\u5E74");
		label_4.setBounds(203, 101, 15, 18);
		panel_1.add(label_4);
		
		mm = new JComboBox<String>();
		mm.setBounds(245, 98, 59, 24);
		panel_1.add(mm);
		mm.addItem("12");
		
		JLabel label_5 = new JLabel("\u6708");
		label_5.setBounds(305, 101, 15, 18);
		panel_1.add(label_5);
		
		dd = new JComboBox<String>();
		dd.setBounds(352, 98, 59, 24);
		panel_1.add(dd);
		dd.addItem("31");
		
		JLabel label_6 = new JLabel("\u65E5");
		label_6.setBounds(419, 101, 15, 18);
		panel_1.add(label_6);
		
		JLabel label_7 = new JLabel("\u7535  \u8BDD");
		label_7.setBounds(48, 149, 60, 18);
		panel_1.add(label_7);
		
		phone = new JTextField();
		phone.setBounds(122, 146, 152, 24);
		panel_1.add(phone);
		phone.setColumns(10);
		
		JButton button = new JButton("\u4FDD\u5B58\u5E76\u9000\u51FA");
		button.setBounds(627, 369, 113, 42);
		panel_1.add(button);
		button.addActionListener(this);
		
		JButton button_1 = new JButton("\u9000\u51FA");
		button_1.setBounds(482, 369, 113, 42);
		panel_1.add(button_1);
		button_1.addActionListener(this);
		
		JLabel label = new JLabel("\u4E2A\u4EBA\u8D44\u6599");
		label.setBounds(14, 148, 72, 18);
		getContentPane().add(label);
		
		this.setVisible(true);
	}
	/**
	 * 更新个人资料页面
	 * @param Image 头像
	 * @param netName 网名
	 * @param info 签名
	 * @param name 真实姓名
	 * @param sex 性别
	 * @param yy 年
	 * @param mm 月
	 * @param dd 日
	 * @param phone 电话
	 */
	public void refreshData(String Image,String NetName,String Info,String Name,String Sex,int Yy,int Mm,int Dd,String Phone,String uid) {
		this.IMAGE = Image;
		this.UID = uid;
		image.setIcon(new ImageIcon("face0/"+Image+".png"));
		netName.setText(NetName);
		info.setText(Info);
		name.setText(Name);
		if(Sex.equals("女")) {
			sexW.setSelected(true);
		}else {
			sexM.setSelected(true);
		}
		yy.setSelectedItem(String.valueOf(Yy));
		mm.setSelectedItem(String.valueOf(Mm));
		dd.setSelectedItem(String.valueOf(Dd));
		phone.setText(Phone);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("保存并退出")) {
			String NetName = netName.getText();
			String Info = info.getText();
			String Name = name.getText();
			String Sex;
			if(sexM.isSelected()) {
				Sex = "男";
			}else {
				Sex = "女";
			}
			int Yy = Integer.parseInt(yy.getSelectedItem().toString());
			int Mm = Integer.parseInt(mm.getSelectedItem().toString());
			int Dd = Integer.parseInt(dd.getSelectedItem().toString());
			String Phone = phone.getText();
			String str = "{\"dd\":"+Dd+",\"image\":\""+IMAGE+"\",\"info\":\""+Info+"\",\"mm\":"+Mm+",\"name\":\""+Name+"\",\"netName\":\""+NetName+"\",\"phoneNumber\":\""+Phone+"\",\"sex\":\""+Sex+"\",\"uid\":\""+UID+"\",\"yy\":"+Yy+"}";
			Config.personInfo = str;
			this.dispose();
		}else if(e.getActionCommand().equals("退出")) {
			this.dispose();
		}
	}
}
