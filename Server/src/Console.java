
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Console {
	static final JFrame frame = new JFrame("Server Console");
	static final JPanel panel = new JPanel();
	
	static final int width = 450;
	static final int height = 600;
	
	public static final JTextArea textArea = new JTextArea();
	public static final JScrollPane scroll = new JScrollPane(textArea);
	public static final JTextField textField = new JTextField();
	public static final JButton button = new JButton("Send");
	
	public Console(){
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		scroll.setPreferredSize(new Dimension(width-25, height-100));
		textField.setPreferredSize(new Dimension(width-93,27));
		
		panel.add(scroll);
		frame.add(panel);
		
	}
	
	public void writeInServerConsole(String text){
		textArea.append(text + "\n");
	}
}
