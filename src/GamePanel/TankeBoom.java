package GamePanel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * 坦克爆炸动画
 * @author Xinhai Cao
 *
 */
public class TankeBoom extends Thread {
	JLabel panel;
	JLabel Label;
	int x;
	int y;
	/**
	 * 坦克爆炸构造器
	 * @param label 坦克
	 * @param x 中心位置
	 * @param y 中心位置
	 * @param panel 容器
	 */
	public TankeBoom(JLabel label,int x,int y,JLabel panel) {
		this.Label = label;
		this.x = x;
		this.y = y;
		this.panel = panel;
	}
	
	public void run() {
		int t = 5;
		for(int i=0;i<=10;i++) {
			String path = "/img/"+i+".gif";
			Label.setIcon(new ImageIcon(TankeBoom.class.getResource(path)));
			Label.setBounds(x-t/2, y-t/2, t, t);
			t+=5;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		panel.remove(Label);
		panel.updateUI();
	}
}
