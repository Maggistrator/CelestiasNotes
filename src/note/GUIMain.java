package note;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

/**
 * Main frame of the application
 */
public class GUIMain extends JFrame {

    public static final int MODE_READ = 0;
    public static final int MODE_WRITE = 1;

    JTextArea textArea = new JTextArea();   // Main text area, all magic happens here
    JMenuBar jmb = new JMenuBar();          // Menu bar, allows access to all functions of the app, no toolbars
    JLabel datetimeLabel = new JLabel();    // Unknown legacy piece of code. Dunno what is it doing, but it's definitly important.

    GUIMain() {
        setName("Celestia's Notes");
        setTitle("Celestia's Notes");
        JPanel newContentContainer = new JPanel();
        newContentContainer.setAlignmentX(LEFT_ALIGNMENT);
        setContentPane(newContentContainer);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Calendar calendar = new GregorianCalendar();
        DateFormat humanReadableDate = DateFormat.getDateInstance();
        datetimeLabel.setText("Дорогая Принцесса Селестия! Сегодня, " + humanReadableDate.format(calendar.getTime()) + ".");
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        textArea.setDisabledTextColor(Color.black);
        this.setMinimumSize(new Dimension(400, 550));
        this.setLocationRelativeTo(null);
        packLetterMenu();
        packModeMenu(this);
        packHelpMenu();
        this.setJMenuBar(jmb);
        setLayout(new BorderLayout());
        this.add(datetimeLabel, BorderLayout.PAGE_START);
        this.add(textArea);
        this.pack();
    }

    private void packLetterMenu() {
        // creating file (aka "letter") menu
        JMenu jmLetter = new JMenu("File");
        // creating submenues for each file functions: save and load
        JMenuItem letter_Open = new JMenuItem("Open");
        JMenuItem letter_Save = new JMenuItem("Save");
        JMenuItem letter_Exit = new JMenuItem("Exit");
        // setting action listeners to a menu items
        letter_Open.addActionListener(new Load(this));
        letter_Save.addActionListener(new Save(datetimeLabel, textArea));
        letter_Exit.addActionListener((event) -> System.exit(0));
        // adding all created submenues to a menu
        jmLetter.add(letter_Open);
        jmLetter.add(letter_Save);
        jmLetter.addSeparator();
        jmLetter.add(letter_Exit);
        // adding created menu to a menu bar
        jmb.add(jmLetter);
    }

    private void packModeMenu(JFrame parent) {
        JMenu jmMode = new JMenu("Mode");
        JMenuItem mode_Read = new JMenuItem("Read");
        JMenuItem mode_Write = new JMenuItem("Write");
        // action listener that responsible for changing mode from read to write and vice-versa
        ActionListener modeSwitch = (ActionEvent e) -> {
            if (e.getActionCommand().equals("disable")) setMode(MODE_READ);
            else setMode(MODE_WRITE);
        };
        // action commands help mode switch to decide, how to react to event - enabling or disabling TA
        mode_Read.setActionCommand("disable");
        mode_Write.setActionCommand("enable");
        // registring mode switch on menu items
        mode_Read.addActionListener(modeSwitch);
        mode_Write.addActionListener(modeSwitch);
        // adding elements just as in all other places
        jmMode.add(mode_Read);
        jmMode.add(mode_Write);
        jmb.add(jmMode);
    }

    private void packHelpMenu() {
        // all the same as packLetterMenu()
        JMenu jmHelp = new JMenu("Help");
        JMenuItem jmiAbout = new JMenuItem("About");
        jmiAbout.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(this,
                    "<html>"
                    + "Возможно, Принцесса никогда не прочитает эти письма.<br>"
                    + "Но будучи через время прочитанными ВАМИ, они помогут Вам вспомнить,<br>"
                    + "помогут принять правильные решения, и не заблудиться в своей же голове.<br>"
                    + "Это - дневники разработчика, это - <b>Celestia's Notes</b>."
                    + "</html>");
        });
        jmHelp.add(jmiAbout);
        jmb.add(jmHelp);
    }

    
    public void setMode(int mode) {
        if(mode == MODE_WRITE) {
            textArea.setEnabled(true);
            textArea.setOpaque(true);
            add(datetimeLabel, BorderLayout.PAGE_START);
            setTitle(getName() + ": " + "Write Mode");
            pack();
        } else if(mode == MODE_READ){
            textArea.setEnabled(false);
            textArea.setOpaque(false);
            remove(datetimeLabel);
            setTitle(getName() + ": " + "Read Mode");
            pack();
        }
    }
    
    public void setLetterText(String t) {
        textArea.setText(t);
    }
}
