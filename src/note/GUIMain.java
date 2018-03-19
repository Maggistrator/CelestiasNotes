package note;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class GUIMain{
	
	GUIMain() {
		final JFrame frame = new JFrame("Celestia's notes - Write Mode");
		final JTextArea textArea = new JTextArea();
		frame.setSize(220, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel datetimeLabel = new JLabel();
		JMenuBar jmb = new JMenuBar();
		JMenu jmFile = new JMenu("File");
		JMenuItem jmiOpen = new JMenuItem("Open");
		JMenuItem jmiSave = new JMenuItem("Save");
		JMenuItem jmiExit = new JMenuItem("Exit");
		JMenu jmOptions = new JMenu("Options");
		JMenu mode = new JMenu("Mode");
		JMenuItem b = new JMenuItem("Read");
		JMenuItem c = new JMenuItem("Write");
		jmb.add(jmFile);
		jmFile.add(jmiOpen);
		jmFile.add(jmiSave);
		jmFile.addSeparator();
		jmFile.add(jmiExit);
		mode.add(b);
		mode.add(c);
		jmOptions.add(mode);
		jmb.add(jmOptions);
		JMenu jmHelp = new JMenu("Help");
		JMenuItem jmiAbout = new JMenuItem("About");
		jmHelp.add(jmiAbout);
		jmb.add(jmHelp);
		jmiOpen.addActionListener(new Load(frame, textArea));
		jmiSave.addActionListener(new Save(datetimeLabel, textArea));
		jmiExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		jmiAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Эта программа создана в рамках\n"
						+ "отчета по практике не на этой технологии, но ладно\n"
						+ "Иванников, 33ПО2");
			}
		});
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setEnabled(false);
				frame.setTitle("Celestia's notes - Read Mode");
				frame.repaint();
			}
		});
		c.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setEnabled(true);
				frame.setTitle("Celestia's notes - Write Mode");
				frame.repaint();
			}
		});
		
		Calendar calendar = new GregorianCalendar();
		datetimeLabel.setText("" + calendar.getTime());
		datetimeLabel.setMinimumSize(new Dimension(200, 20));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBorder(new LineBorder(Color.black));
		frame.setMinimumSize(new Dimension(400, 550));
		frame.setLocationRelativeTo(null);
		frame.setJMenuBar(jmb);
		frame.add(datetimeLabel);
		frame.add(textArea);
		frame.setVisible(true);
	}

}
