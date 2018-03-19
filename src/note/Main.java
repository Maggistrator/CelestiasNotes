package note;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class Main{
	
	Main() {
		final JFrame frame = new JFrame("Celestia's notes - Write Mode");
		final JFileChooser fc = new JFileChooser();
		final JTextArea textArea = new JTextArea();
		JLabel datetimeLabel = new JLabel();
		frame.setSize(220, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar jmb = new JMenuBar();
		JMenu jmFile = new JMenu("File");
		JMenuItem jmiOpen = new JMenuItem("Open");
		JMenuItem jmiSave = new JMenuItem("Save");
		JMenuItem jmiExit = new JMenuItem("Exit");
		jmFile.add(jmiOpen);
		jmFile.add(jmiSave);
		jmFile.addSeparator();
		jmFile.add(jmiExit);
		jmb.add(jmFile);
		JMenu jmOptions = new JMenu("Options");
		JMenu mode = new JMenu("Mode");
		JMenuItem b = new JMenuItem("Read");
		JMenuItem c = new JMenuItem("Write");
		mode.add(b);
		mode.add(c);
		jmOptions.add(mode);
		jmb.add(jmOptions);
		JMenu jmHelp = new JMenu("Help");
		JMenuItem jmiAbout = new JMenuItem("About");
		jmHelp.add(jmiAbout);
		jmb.add(jmHelp);
		jmiOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fc.setCurrentDirectory(new File("saved"));
				int returnVal = fc.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						FileInputStream input;
						String result = null;
						input = new FileInputStream(fc.getSelectedFile());
						CharsetDecoder decoder = Charset.forName("windows-1251").newDecoder();
						decoder.onMalformedInput(CodingErrorAction.IGNORE);
						InputStreamReader reader = new InputStreamReader(input, decoder);
						BufferedReader bufferedReader = new BufferedReader(reader);
						StringBuilder sb = new StringBuilder();
						String line = bufferedReader.readLine();
						while (line != null) {
							sb.append(line+"\n");
							line = bufferedReader.readLine();
						}
						bufferedReader.close();
						result = sb.toString();
						textArea.setText(result);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					textArea.setEnabled(false);
					frame.setTitle("Celestia's notes - Read Mode");
					frame.repaint();
				}
			}
		});
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
