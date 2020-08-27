package GamePanel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * 创建新坦克
 * @author Xinhai Cao
 *
 */
public class Tanke extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**坦克身份*/
	public String tankeID;
	/**坦克编号*/
	public int id;
	/**分数临时寄存*/
	public int source;
	
	
	/**
	 * 
	 * 新坦克构造器
	 * @param str 坦克出生方向
	 * @param x x坐标
	 * @param y y坐标
	 * @param tankeID 坦克ID
	 * @param id 坦克申请序号
	 */
	public Tanke(String str,int x,int y, String tankeID,int id,int source) {
		this.id = id;
		this.tankeID = tankeID;
		this.source = source;
		this.setSize(35,34);
		if(str.equals("W")) {
			this.setIcon(new ImageIcon(Tanke.class.getResource("/img/tankU_"+id+".gif")));
		}else if(str.equals("S")) {
			this.setIcon(new ImageIcon(Tanke.class.getResource("/img/tankD_"+id+".gif")));
		}else if(str.equals("A")) {
			this.setIcon(new ImageIcon(Tanke.class.getResource("/img/tankL_"+id+".gif")));
		}else if(str.equals("D")) {
			this.setIcon(new ImageIcon(Tanke.class.getResource("/img/tankR_"+id+".gif")));
		}
		
		this.setLocation(x, y);
	}
	/**
	 * 更新坦克方向
	 * @param str 方向
	 * @param id  坦克申请序号
	 */
	public void tankeFangxiang(String str,int id) {
		if(str.equals("W")) {
			this.setIcon(new ImageIcon(Tanke.class.getResource("/img/tankU_"+id+".gif")));
		}else if(str.equals("S")) {
			this.setIcon(new ImageIcon(Tanke.class.getResource("/img/tankD_"+id+".gif")));
		}else if(str.equals("A")) {
			this.setIcon(new ImageIcon(Tanke.class.getResource("/img/tankL_"+id+".gif")));
		}else if(str.equals("D")) {
			this.setIcon(new ImageIcon(Tanke.class.getResource("/img/tankR_"+id+".gif")));
		}
	}
	/**
	 * 更新坦克位置
	 * @param x
	 * @param y
	 */
	public void tankeLocation(int x,int y) {
		this.setLocation(x, y);
	}
}
