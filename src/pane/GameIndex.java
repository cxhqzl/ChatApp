package pane;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import paramer.WindowsXY;

import javax.swing.JButton;

public class GameIndex extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	
	public GameIndex() {
		this.setSize(400, 300);
		this.setLocation(WindowsXY.getXY(this.getWidth(), this.getHeight()));
		this.setVisible(true);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u670D\u52A1\u5668");
		label.setBounds(75, 49, 52, 18);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(146, 46, 182, 24);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u540D\u79F0");
		label_1.setBounds(75, 97, 52, 18);
		getContentPane().add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(146, 94, 182, 24);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton button = new JButton("\u786E\u8BA4");
		button.setBounds(146, 172, 113, 27);
		getContentPane().add(button);
		
	}
}
