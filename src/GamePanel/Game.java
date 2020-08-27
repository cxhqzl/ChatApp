package GamePanel;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import sun.audio.*;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.painter.StandardGradientPainter;
import org.jvnet.substance.skin.FieldOfWheatSkin;
import org.jvnet.substance.skin.SubstanceCremeLookAndFeel;
import org.jvnet.substance.theme.SubstanceAquaTheme;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;

import LinkServer.BulletUDPServer;
import LinkServer.TankeLoginServer;
import LinkServer.TankeServer;
import paramer.Config;
import paramer.WindowsXY;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
/**
 * 游戏面板
 * @author Xinhai Cao
 *
 */
public class Game extends JFrame implements KeyListener,WindowListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**坦克*/
	Tanke label;
	JPanel panel_1;
	TankeServer gameServerLink;
	/**存储已发出的子弹*/
	Vector<Bullet> bullets = new Vector<Bullet>();
	Random random = new Random();
	BulletUDPServer bulletUDPServer;
	/**生命血条*/
	JProgressBar Life;
	JPanel panel;
	/**排行榜*/
	JTextArea textArea;
	
	//当前在线用户信息
	private JLabel user_1;
	private JLabel user_2;
	private JLabel user_3;
	private JLabel user_4;
	private JLabel name_1;
	private JLabel name_2;
	private JLabel name_3;
	private JLabel name_4;
	
	JLabel myID;
	private JLabel myName;
	private JLabel bgLabel;
	JScrollPane scrollPane;
	JLabel mySource;
	
	public Game() {
		this.setSize(967, 600);
		this.setLocation(WindowsXY.getXY(this.getWidth(), this.getHeight()));
		getContentPane().setLayout(null);
		
		JPanel panel_2 = new JPanel();
		JPanel panel_3 = new JPanel();
		panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(604, 13, 345, 527);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(14, 13, 582, 527);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		user_1 = new JLabel();
		user_1.setBounds(5, 34, 28, 28);
		panel_3.add(user_1);
		
		name_1 = new JLabel();
		name_1.setBounds(47, 39, 94, 18);
		panel_3.add(name_1);
		
		
		user_2 = new JLabel();
		user_2.setBounds(5, 75, 28, 28);
		panel_3.add(user_2);
		
		name_2 = new JLabel();
		name_2.setBounds(47, 80, 94, 18);
		panel_3.add(name_2);
		
		user_3 = new JLabel();
		user_3.setBounds(5, 116, 28, 28);
		panel_3.add(user_3);
		
		name_3 = new JLabel();
		name_3.setBounds(47, 121, 94, 18);
		panel_3.add(name_3);
		
		user_4 = new JLabel();
		user_4.setBounds(5, 157, 28, 28);
		panel_3.add(user_4);
		
		name_4 = new JLabel();
		name_4.setBounds(47, 162, 94, 18);
		panel_3.add(name_4);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(2, 363, 339, 118);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		Config.textArea = textArea;
		Config.scrollPane = scrollPane;
		Config.refreshData();
		
		bgLabel = new JLabel();
		bgLabel.setBounds(0, 0, 582, 527);
		panel_1.add(bgLabel);
		bgLabel.setIcon(new ImageIcon(Game.class.getResource("/img/screen.jpg")));
		
		Config.id = Config.number;
		
		label = new Tanke("W",random.nextInt(550),random.nextInt(500),Config.uid,Config.id,Config.source);
		label.id = Config.id;
		bgLabel.add(label);
		
		panel_2.setName("");
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(2, 0, 339, 132);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		myID = new JLabel("New label");
		myID.setBounds(10, 28, 28, 28);
		panel_2.add(myID);
		myID.setIcon(new ImageIcon(Game.class.getResource("/img/number_"+label.id+".png")));
		
		myName = new JLabel();
		myName.setBounds(53, 33, 138, 18);
		panel_2.add(myName);
		myName.setText(label.tankeID);
		
		JLabel label_1 = new JLabel("\u751F\u547D\u503C");
		label_1.setBounds(10, 69, 45, 18);
		panel_2.add(label_1);
		
		Life = new JProgressBar();
		Life.setBounds(10, 100, 192, 14);
		panel_2.add(Life);
		Life.setMaximum(100);
		Life.setValue(100);
		
		JLabel label_3 = new JLabel("\u6211\u7684\u4FE1\u606F");
		label_3.setBounds(10, 0, 72, 18);
		panel_2.add(label_3);
		
		JLabel label_5 = new JLabel("\u5206\u6570");
		label_5.setFont(new Font("宋体", Font.PLAIN, 20));
		label_5.setBounds(236, 29, 72, 23);
		panel_2.add(label_5);
		
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(2, 145, 339, 191);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel label_2 = new JLabel("\u5728\u7EBF\u7528\u6237");
		label_2.setBounds(5, 3, 72, 18);
		panel_3.add(label_2);
		
		JButton btnNewButton = new JButton("\u518D\u73A9\u4E00\u6B21");
		btnNewButton.setBounds(120, 494, 113, 27);
		btnNewButton.addActionListener(this);
		panel.add(btnNewButton);
		
		JLabel label_4 = new JLabel("\u6392\u884C\u699C");
		label_4.setBounds(2, 344, 54, 18);
		panel.add(label_4);
		name_4.setVisible(false);
		user_4.setVisible(false);
		name_3.setVisible(false);
		user_3.setVisible(false);
		name_2.setVisible(false);
		user_2.setVisible(false);
		name_1.setVisible(false);
		user_1.setVisible(false);
		panel.updateUI();
		
		mySource = new JLabel("50",JLabel.CENTER);
		mySource.setFont(new Font("宋体", Font.PLAIN, 40));
		mySource.setBounds(248, 52, 72, 62);
		panel_2.add(mySource);
		mySource.setText(Config.source+"");
		
		
		this.addKeyListener(this);
		
		this.setVisible(true);
		
		new BulletThread().start();
		bulletUDPServer = new BulletUDPServer(this);
		
		gameServerLink = new TankeServer(this);
		gameServerLink.send(Config.uid, label.getX()+"",label.getY()+"", Config.Tfangxiang, 100,label.source,label.id);
		init();
		try {
			bulletUDPServer.send("0@"+Config.uid+"@A", -10+"",-10+"", "W");
		}catch(Exception e) {}
		this.requestFocus();
		
		refreshUser(Config.id,Config.uid);
		
	}
	/**
	 * 刷新在线用户
	 * @param id 坦克编号
	 * @param uid 坦克身份
	 */
	public void refreshUser(int id,String uid) {
		switch(id) {
			case 1:
				user_1.setIcon(new ImageIcon(Game.class.getResource("/img/number_"+id+".png")));
				user_1.setVisible(true);
				name_1.setText(uid);
				name_1.setVisible(true);
				break;
			case 2:
				user_2.setIcon(new ImageIcon(Game.class.getResource("/img/number_"+id+".png")));
				user_2.setVisible(true);
				name_2.setText(uid);
				name_2.setVisible(true);
				break;
			case 3:
				user_3.setIcon(new ImageIcon(Game.class.getResource("/img/number_"+id+".png")));
				user_3.setVisible(true);
				name_3.setText(uid);
				name_3.setVisible(true);
				break;
			case 4:
				user_4.setIcon(new ImageIcon(Game.class.getResource("/img/number_"+id+".png")));
				user_4.setVisible(true);
				name_4.setText(uid);
				name_4.setVisible(true);
				break;
		}
	}
	
	/**
	 * 判断坦克是否出界
	 * @param p 坦克当前位置
	 * @param d 坦克当前方向
	 * @throws Exception 
	 */
	public void isOutLine(Point p,String d) throws Exception {
		switch (d) {
		case "W":
			if (p.y <= 0) {
				p.y = 0;
				throw new Exception();
			}
			break;
		case "A":
			if (p.x <= 0) {
				p.x = 0;
				throw new Exception();
			}
			break;
		case "S":
			if (p.y + 34 >= bgLabel.getHeight()) {
				p.y = bgLabel.getHeight() - 34;
				throw new Exception();
			}
			break;
		case "D":
			if (p.x + 35 >= bgLabel.getWidth()) {
				p.x = bgLabel.getWidth() - 35;
				throw new Exception();
			}
			break;
		}
		label.setLocation(p);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		Point p = label.getLocation();
		try {
			if(e.getKeyCode()==KeyEvent.VK_W) {//上
				Config.Tfangxiang = "W";
				label.tankeFangxiang("W",label.id);
				isOutLine(p,"W");
				p.translate(0, -Config.Tspeed);
			}else if(e.getKeyCode()==KeyEvent.VK_S) {//下
				Config.Tfangxiang = "S";
				label.tankeFangxiang("S",label.id);
				isOutLine(p,"S");
				p.translate(0, +Config.Tspeed);
			}else if(e.getKeyCode()==KeyEvent.VK_A) {//左
				Config.Tfangxiang = "A";
				label.tankeFangxiang("A",label.id);
				isOutLine(p,"A");
				p.translate(-Config.Tspeed, 0);
			}else if(e.getKeyCode()==KeyEvent.VK_D) {//右
				Config.Tfangxiang = "D";
				label.tankeFangxiang("D",label.id);
				isOutLine(p,"D");
				p.translate(+Config.Tspeed, 0);
			}else if(e.getKeyCode()==KeyEvent.VK_J) {
				if(bullets.size()<3) {//限制同时打出的子弹数
					//给打出的子弹添加编号
					String bulletID = new Date().getTime()+"@"+Config.uid+"@"+(int)Math.random()*1000000;
					switch(Config.Tfangxiang) {
						case "W":{
							Bullet b = new Bullet(p.x+24/2,p.y-10,"W",bulletID);
							bullets.add(b);
							bgLabel.add(b);
							playBulletMusic();
							bulletUDPServer.send(bulletID,p.x+24/2+"", p.y-10+"", "W");
						}
							break;
						case "S":{
							Bullet b = new Bullet(p.x+20/2,p.y+34,"S",bulletID);
							bullets.add(b);
							bgLabel.add(b);
							playBulletMusic();
							bulletUDPServer.send(bulletID, p.x+20/2+"",p.y+34+"", "S");
						}
							break;
						case "A":{
							Bullet b = new Bullet(p.x-20/2,p.y+10,"A",bulletID);
							bullets.add(b);
							bgLabel.add(b);
							playBulletMusic();
							bulletUDPServer.send(bulletID, p.x-20/2+"", p.y+10+"", "A");
						}
							break;
						case "D":{
							Bullet b = new Bullet(p.x+66/2,p.y+12,"D",bulletID);
							bullets.add(b);
							bgLabel.add(b);
							playBulletMusic();
							bulletUDPServer.send(bulletID, p.x+66/2+"", p.y+12+"", "D");
						}
							break;
					}
					System.out.println(label.source);
				}
			}
			bgLabel.updateUI();
			if(Life.getValue()<=0) {
				gameServerLink.send(Config.uid, -100+"", -100+"", Config.Tfangxiang, Life.getValue(),Config.source,label.id);
			}else {
				gameServerLink.send(Config.uid, p.x+"", p.y+"", Config.Tfangxiang, Life.getValue(),Config.source,label.id);
			}
		}catch(Exception e1) {}
		label.setLocation(p);
	}
	/**
	 * 子弹移动线程
	 * @author Xinhai Cao
	 *
	 */
	class BulletThread extends Thread{
		public void run() {
			while(true) {
				@SuppressWarnings("unchecked")
				Vector<Bullet> bullets1 = (Vector<Bullet>) bullets.clone();
				for(Bullet b : bullets1) {
					try {
						b.BulletMove(bgLabel.getWidth(), bgLabel.getHeight());
						if(hitBullet.indexOf(b.getBulletID())>=0) {
							bulletUDPServer.send(b.getBulletID(), -100+"",-100+"", b.getBulletD());
							b.setVisible(false);
							bullets.remove(b);
							bgLabel.remove(b);
							bgLabel.updateUI();
							continue;
						}
						bulletUDPServer.send(b.getBulletID(), b.getLocation().x+"",b.getLocation().y+"", b.getBulletD());
					} catch (Exception e) {
						bullets.remove(b);
						bgLabel.remove(b);
						bgLabel.updateUI();
					}
				}
				
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {}
			}
		}
	}
	
	/**保存已经创建过的坦克*/
	Hashtable<String,Tanke> tankes = new Hashtable<String,Tanke>();
	/**
	 * 更新坦克
	 * @param str
	 */
	public void Update(String str) {
		try {
			//切分出用户
			String[] str1 = str.split("&");
			Config.onlinePeople = str1.length;
			
			/**保存当前在线用户*/
			Vector<String> names = new Vector<String>();
			for(String s : str1) {//name,x,y,fangxiang,life
				
				String[] str2 = s.split(",");
				String name = str2[0];
				int x = Integer.parseInt(str2[1]);
				int y = Integer.parseInt(str2[2]);
				String fangxiang = str2[3];
				int life = Integer.parseInt(str2[4]);
				int source = Integer.parseInt(str2[5]);
				int id = Integer.parseInt(str2[6]);
				
				if(name.equals(Config.uid)) {//遇到自己坦克
					Life.setValue(life);
					if(Life.getValue()>=90) {
						Life.setForeground(Color.green);
					}else if(Life.getValue()>=30) {
						Life.setForeground(Color.yellow);
					}else if(Life.getValue()>0) {
						Life.setForeground(Color.red);
					}else if(Life.getValue()==0) {//坦克死亡
						if(hitBullet.size()>0) {
							String bulletID = hitBullet.get(hitBullet.size()-1);
							String name1 = bulletID.split("@")[1];
							tankes.get(name1).source+=5;
						}
						JLabel jl = new JLabel();
						bgLabel.add(jl);
						int xx = label.getX()+label.getWidth()/2;
						int yy = label.getY()+label.getHeight()/2;
						tankes.remove(name);
						bgLabel.remove(label);
						label = null;
						new TankeBoom(jl,xx,yy,bgLabel).start();
						playBoomMusic();
						gameServerLink.send(Config.uid, -100+"", -100+"", Config.Tfangxiang, 0,Config.source,Config.id);
					}
					continue;
				}
				names.add(name);
				
				if(tankes.get(name)==null) {//不存在即添加
					if(life>0) {
						Tanke t = new Tanke(fangxiang,x,y,name,id,source);
						tankes.put(name, t);
						bgLabel.add(t);
						refreshUser(t.id,t.tankeID);
						System.out.println(id);
						bgLabel.updateUI();
						panel.updateUI();
					}
					
				}else {//更新坦克信息
					Tanke t = tankes.get(name);
					if(life<=0) {
						JLabel jl = new JLabel();
						int xx = t.getX()+t.getWidth()/2;
						int yy = t.getY()+t.getHeight()/2;
						tankes.remove(name);
						names.remove(name);
						bgLabel.remove(t);
						t = null;
						bgLabel.add(jl);
						new TankeBoom(jl,xx,yy,bgLabel).start();
						playBoomMusic();
						gameServerLink.send(t.tankeID, -100+"", -100+"", Config.Tfangxiang, 0,t.source,t.id);
					}else {
						t.tankeFangxiang(fangxiang,t.id);
						t.tankeLocation(x, y);
						bgLabel.updateUI();
					}
				}
			}
			
			//获取当前在线坦克
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Set<Entry<String,Tanke>> t = ((Hashtable) tankes.clone()).entrySet();
			for(Entry<String,Tanke> entry : t) {
				if(names.indexOf(entry.getKey())==-1) {//移除已退出或死亡的坦克
					tankes.remove(entry.getKey());
					bgLabel.remove(entry.getValue());
				}
			}
			
		} catch (Exception e) {
		}
	}
	
	/**敌人子弹*/
	static HashMap<String,Bullet> B = new HashMap<String,Bullet>();
	/**初始化子弹*/
	public void init() {
		for(int i=0;i<1000;i++) {
			Bullet b = new Bullet(0,0,"","");
			b.setVisible(false);
			
			B.put("new"+i, b);
			bgLabel.add(b);
		}
		bgLabel.updateUI();
	}
	
	/**保存已击中目标的子弹*/
	Vector<String> hitBullet = new Vector<String>();
	
	/**更新子弹*/
	public void updateBullet(String str) {
		
		int index = 0;
		String[] str1 = str.split("&");
		
		//判断子弹是否击中
		for(String s : str1) {
			String[] str2 = s.split(",");
			String id = str2[0];
			String x = str2[1];
			String y = str2[2];
			
			Set<Entry<String,Tanke>> T = tankes.entrySet();
			for (Entry<String, Tanke> entry : T) {
				if(Integer.parseInt(x)>=entry.getValue().getX()&&
						Integer.parseInt(y)>=entry.getValue().getY()&&
						Integer.parseInt(x)<=entry.getValue().getX()+entry.getValue().getWidth()&&
						Integer.parseInt(y)<=entry.getValue().getY()+entry.getValue().getHeight()) {
					if(hitBullet.indexOf(id)<=-1) {//击中坦克
						if((id.split("@")[1]).equals(Config.uid)) {
							Config.source+=2;
							label.source=Config.source;
							mySource.setText(Config.source+"");
						}
						hitBullet.add(id);
						gameServerLink.send(entry.getValue().tankeID);
					}
				}
			}
			
		}
		
		Set<Entry<String,Bullet>> b1 = B.entrySet();
		for(Entry<String,Bullet> entry : b1) {
			entry.getValue().setVisible(false);
		}
		
		//更新子弹位置
		for(String s : str1) {
			String[] str2 = s.split(",");
			String id = str2[0];
			String x = str2[1];
			String y = str2[2];
			String fangxiang  = str2[3];
			
			//遇到初始化子弹或自己的子弹或已击中目标的子弹结束本轮循环
			if(id.split("@")[2].equals("A")||id.split("@")[1].equals(Config.uid)||hitBullet.indexOf(id)>=0) {
				continue;
			}
			
			Bullet b2 = B.get("new"+index);
			b2.setBullet(Integer.parseInt(x), Integer.parseInt(y), fangxiang,id);
			b2.setVisible(true);
			
			index++;
		}
		
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
		Game g = new Game();
		g.requestFocus();
	}
	
	/**子弹发射声音*/
	@SuppressWarnings("restriction")
	public void playBulletMusic() {
		try {
			FileInputStream fileau=new FileInputStream("4055_2.wav" );
			/*
			 * AudioStream as = new AudioStream(fileau); AudioPlayer.player.start(as);
			 */ 
	    }catch (Exception e){}  
	}
	/**爆炸声音*/
	@SuppressWarnings("restriction")
	public void playBoomMusic() {
		try {
			FileInputStream fileau=new FileInputStream("2479_2.wav" );
			/*
			 * AudioStream as = new AudioStream(fileau); AudioPlayer.player.start(as);
			 */
	    }catch (Exception e){}  
	} 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.requestFocus();
		if(e.getActionCommand().equals("再玩一次")) {
			if(label==null) {
				Config.life = 100;
				Life.setValue(100);
				Config.Tfangxiang = "W";
				label = new Tanke("W",random.nextInt(550),random.nextInt(500),Config.uid,Config.id,Config.source);
				gameServerLink.send(label.tankeID, label.getX()+"", label.getY()+"", Config.Tfangxiang, 100,Config.source,Config.id);
				bgLabel.add(label);
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		gameServerLink.send(Config.uid, -100+"", -100+"", Config.Tfangxiang, Life.getValue(),Config.source,Config.id);
		TankeLoginServer.Close();
		this.dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
