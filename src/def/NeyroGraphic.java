package def;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.beans.decoder.ValueObject;

public class NeyroGraphic extends JFrame{

	private static final int DEFAUL_WIDTH = 600;
	private static final int DEFAUL_HEIGHT = 270;
	
	public NeyroGraphic(){
		setSize(DEFAUL_WIDTH, DEFAUL_HEIGHT);
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((screenSize.getWidth() - this.getWidth()) / 2);
		int y = (int) ((screenSize.getHeight() - this.getHeight()) /2);
		this.setBounds(x, y, this.getWidth(), this.getHeight());
		//setResizable(false);
		setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
	
				
		
		JLabel name1 = new JLabel("Команда 1");
		name1.setBounds(30, 20, 70, 25);
		panel.add(name1);
		
		JLabel name2 = new JLabel("Команда 2");
		name2.setBounds(400, 20, 70, 25);
		panel.add(name2);
		
		JLabel label_one = new JLabel("Рейтинг команды");
		label_one.setBounds(15, 60, 110, 25);
		
		JLabel label_two = new JLabel("Процент побед");
		label_two.setBounds(15,120, 110, 25);
		
		JLabel label_one2 = new JLabel("Рейтинг команды");
		label_one2.setBounds(370, 60, 110, 25);
		
		JLabel label_two2 = new JLabel("Процент побед");
		label_two2.setBounds(370,120, 110, 25);
		
		panel.add(label_one);
		panel.add(label_two);
		
		panel.add(label_one2);
		panel.add(label_two2);
		
		
		JTextField field_one = new JTextField();
		field_one.setBounds(130, 60, 75, 25);
		panel.add(field_one);
		
	JTextField field_two = new JTextField();
		field_two.setBounds(130, 120, 75, 25);
		panel.add(field_two);
		
		
		JTextField field_one2 = new JTextField();
		field_one2.setBounds(480, 60, 75, 25);
		panel.add(field_one2);
		
	JTextField field_two2 = new JTextField();
		field_two2.setBounds(480, 120, 75, 25);
		panel.add(field_two2);
		
		
	
		
		 JLabel rez = new JLabel("Результат");
		    rez.setBounds(130, 180, 70, 25);
			panel.add(rez);
		 
		JTextField field_rez = new JTextField();
		    field_rez.setBounds(200, 180, 200, 30);
			panel.add(field_rez);
		
			
			JButton button  = new JButton("OK");
			 button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int a = Integer.valueOf(field_one.getText());
					int b = Integer.valueOf(field_two.getText());
					
					int a2 = Integer.valueOf(field_one2.getText());
					int b2 = Integer.valueOf(field_two2.getText());
					 try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if (a+b> a2+b2)field_rez.setText("Победит команда 1");
					if (a+b< a2+b2)field_rez.setText("Победит команда 2");
					if (a+b == a2+b2)field_rez.setText("Недостаточно данных");
					
				}
			});
			 button.setBounds(480, 180, 90, 30);
			 panel.add(button);
			
		
		add(panel);
		
	}
}
