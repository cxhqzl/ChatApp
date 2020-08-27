package GamePanel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * ������̹��
 * @author Xinhai Cao
 *
 */
public class Tanke extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**̹�����*/
	public String tankeID;
	/**̹�˱��*/
	public int id;
	/**������ʱ�Ĵ�*/
	public int source;
	
	
	/**
	 * 
	 * ��̹�˹�����
	 * @param str ̹�˳�������
	 * @param x x����
	 * @param y y����
	 * @param tankeID ̹��ID
	 * @param id ̹���������
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
	 * ����̹�˷���
	 * @param str ����
	 * @param id  ̹���������
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
	 * ����̹��λ��
	 * @param x
	 * @param y
	 */
	public void tankeLocation(int x,int y) {
		this.setLocation(x, y);
	}
}
