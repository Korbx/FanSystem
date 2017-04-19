package implementation;

import java.awt.*;

import javax.swing.*;

public class ViewerDisplay extends JPanel{
	/* sarà la GUI in cui viene creato il Frame, chiamato dal Main,
	 * e in cui verranno messi i vari componenti dei Viewers
	 * 
	 */
	private JFrame frame;
	private String text;
	private GridBagLayout layout;
	private GridBagConstraints c;
	
	public ViewerDisplay(){
		frame = new JFrame("Viewers");
		layout = new GridBagLayout();
		c = new GridBagConstraints();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(300, 600));
		this.setLayout(layout);
		frame.add(this);
		frame.setVisible(true);
	}
	
	public void addJLabel(JLabel label, int gridx, int gridy){
		c.gridx = gridx;
		c.gridy = gridy;
		this.add(label, c);
	}
	
	public void addJTextField(JTextField textField, int gridx, int gridy){
		c.gridx = gridx;
		c.gridy = gridy;
		this.add(textField, c);
		//usa text.SetField per cambiarlo dal Viewer
	}
}
