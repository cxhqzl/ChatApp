package GamePanel;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import paramer.Config;

public class Bullet extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**�ӵ�����*/
	private String bulletD;
	public String getBulletD() {
		return bulletD;
	}
	public void setBulletD(String bulletD) {
		this.bulletD = bulletD;
	}
	/**�ӵ����*/
	private String bulletID;

	public Bullet(int x,int y,String bulletD,String id) {
		this.bulletD = bulletD;
		this.setBulletID(id);
		switch(Config.Tfangxiang) {
		case "W":
			this.setIcon(new ImageIcon(Bullet.class.getResource("/img/bulletU.gif")));
			break;
		case "S":
			this.setIcon(new ImageIcon(Bullet.class.getResource("/img/bulletD.gif")));
			break;
		case "A":
			this.setIcon(new ImageIcon(Bullet.class.getResource("/img/bulletL.gif")));
			break;
		case "D":
			this.setIcon(new ImageIcon(Bullet.class.getResource("/img/bulletR.gif")));
			break;
		}
		this.setSize(12, 12);
		this.setLocation(x,y);
	}
	/**
	 * �����ӵ�
	 * @param x λ��
	 * @param y λ��
	 * @param bulletD ����
	 * @param id ID
	 */
	public void setBullet(int x,int y,String bulletD,String id) {
		this.bulletD = bulletD;
		this.setBulletID(id);
		switch(bulletD) {
		case "W":
			this.setIcon(new ImageIcon(Bullet.class.getResource("/img/bulletU.gif")));
			break;
		case "S":
			this.setIcon(new ImageIcon(Bullet.class.getResource("/img/bulletD.gif")));
			break;
		case "A":
			this.setIcon(new ImageIcon(Bullet.class.getResource("/img/bulletL.gif")));
			break;
		case "D":
			this.setIcon(new ImageIcon(Bullet.class.getResource("/img/bulletR.gif")));
			break;
		}
		this.setSize(12, 12);
		this.setLocation(x,y);
	}
	
	/**
	 * �ӵ��ƶ�
	 * @param w ��峤
	 * @param h ����
	 * @throws Exception
	 */
	public void BulletMove(int w, int h) throws Exception {
		Point p = this.getLocation();
		
		switch(bulletD) {
		case "W":
			if(p.y+12<=0) {
				throw new Exception();
			}
			p.translate(0, -Config.Bspeed);
			break;
		case "S":
			if(p.y-12>=h) {
				throw new Exception();
			}
			p.translate(0, +Config.Bspeed);
			break;
		case "A":
			if(p.x-12<=0) {
				throw new Exception();
			}
			p.translate(-Config.Bspeed, 0);
			break;
		case "D":
			if(p.x+12>=w) {
				throw new Exception();
			}
			p.translate(+Config.Bspeed, 0);
			break;
		}
		this.setLocation(p);
	}
	public String getBulletID() {
		return bulletID;
	}
	public void setBulletID(String bulletID) {
		this.bulletID = bulletID;
	}
}