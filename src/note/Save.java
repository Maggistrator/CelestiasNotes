package note;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Save implements ActionListener {

    private JLabel princessNameNDateLabel;
    private JTextArea letter;
    private int num = 1;

    public Save(JLabel label2, JTextArea text) {
        this.princessNameNDateLabel = label2;
        this.letter = text;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            String name = "Letter " + num;
            ArrayList<String> sysData = new ArrayList<String>();

            BufferedWriter out = null, sysout = null;
            BufferedReader sysin = null;
            File reg = new File("data/reg.dat");
            File file;
            String current;
            if (!reg.exists()) {
                reg.createNewFile();
            } else {
                sysin = new BufferedReader(new FileReader(reg));
                while ((current = sysin.readLine()) != null) {
                    sysData.add(current);
                }
            }
            if (!sysData.isEmpty()) {
                for (int i = 1; i <= sysData.size(); i++) {
                    String usedName = sysData.get(i - 1);
                    System.out.println("listitem# " + i + " " + sysData.get(i - 1));
                    int usedNum = Integer.parseInt(usedName.split(" ")[1]);
                    if (usedNum == num) {
                        num++;
                        name = "Letter " + num;
                        System.out.println("Used name: " + usedName);
                        System.out.println("New name: " + name);
                    }
                }
            }
            sysData.add(name);
            file = new File("saved/" + name + ".txt");
            file.createNewFile();
            out = new BufferedWriter(new FileWriter(file));
            sysout = new BufferedWriter(new FileWriter(reg));
            out.write(princessNameNDateLabel.getText());
            out.newLine();
            out.write(letter.getText());
            out.close();
            //Files.write(Paths.get("data/reg.dat"), name.getBytes(), StandardOpenOption.APPEND);
            if (!sysData.isEmpty()) {
                sysout.write("");
                for (String string : sysData) {
                    sysout.append(string);
                    sysout.newLine();
                }
            }
            sysout.close();
            JOptionPane.showMessageDialog(null, "Письмо сохранено");
        } catch (IOException ex) {
            System.out.println("Ошибка при записи!");
        }
    }
}
