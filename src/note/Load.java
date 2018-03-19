package note;

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

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Load implements ActionListener{

	JFrame frame;
	JTextArea textArea;
	
	public Load(JFrame frame, JTextArea textArea) {
		this.frame = frame;
		this.textArea = textArea;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
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

}
