import java.util.*;
import java.io.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AgentGUI extends JFrame {
  public static final String ADDINFO = "AddInfo";
  public static final String EXIT = "Exit";
  public static final String FOLDENCRYPTION = "Folding Encryption";
  public static final String BIDIRSHIFTENCRYPTION = "BiShift Encryption";
  public static final String FILEWRITER = "File Writer";
  public static final String DBWRITER = "Database Writer";

  private JTextField txtFirstName, txtLastName, txtCode;
  private JLabel lblFirstName, lblLastName, lblCode, lblEncryptMethod, lblLogMethod;
  private JComboBox cmbEncryptMethod, cmbLogMethod;
  private JButton addInfoBtn, exitButton;

  public AgentGUI() {
    super(" Bridge Pattern: Agent System ");

    txtFirstName = new JTextField(20);
    txtLastName = new JTextField(20);
    txtCode = new JTextField(20);

    cmbEncryptMethod = new JComboBox();
    cmbEncryptMethod.addItem(FOLDENCRYPTION);
    cmbEncryptMethod.addItem(BIDIRSHIFTENCRYPTION);

    cmbLogMethod = new JComboBox();
    cmbLogMethod.addItem(FILEWRITER);
    cmbLogMethod.addItem(DBWRITER);

    lblFirstName = new JLabel("First Name:");
    lblLastName = new JLabel("Last Name");
    lblCode = new JLabel("Agent Code");
    lblEncryptMethod = new JLabel("Choose Encryption Method");

    lblLogMethod = new JLabel("Choose Writer");

    addInfoBtn = new JButton(ADDINFO);
    addInfoBtn.setMnemonic(KeyEvent.VK_V);
    exitButton = new JButton(EXIT);
    exitButton.setMnemonic(KeyEvent.VK_X);
    ButtonHandler objButtonHandler = new ButtonHandler(this);

    addInfoBtn.addActionListener(objButtonHandler);
    exitButton.addActionListener(new ButtonHandler());

    // JPanel buttonPanel = new JPanel();
    // buttonPanel.add(addInfoBtn);
    // buttonPanel.add(exitButton);

    JPanel UIPanel = new JPanel();

    GridLayout gridbag = new GridLayout(6, 2, 20, 20);
    UIPanel.setLayout(gridbag);

    UIPanel.add(lblFirstName);
    UIPanel.add(txtFirstName);
    UIPanel.add(lblLastName);
    UIPanel.add(txtLastName);
    UIPanel.add(lblCode);
    UIPanel.add(txtCode);
    UIPanel.add(lblEncryptMethod);
    UIPanel.add(cmbEncryptMethod);
    UIPanel.add(lblLogMethod);
    UIPanel.add(cmbLogMethod);
    // UIPanel.add(buttonPanel);
    UIPanel.add(addInfoBtn);
    UIPanel.add(exitButton);


    Container contentPane = getContentPane();
    contentPane.add(UIPanel, BorderLayout.CENTER);

    try {
      SwingUtilities.updateComponentTreeUI(
          AgentGUI.this);
    } catch (Exception ex) {
      System.out.println(ex);
    }
  }

  public static void main(String[] args) {
    JFrame frame = new AgentGUI();
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    frame.setSize(350, 300);
    frame.setVisible(true);
  }

  public String getFirstName() {
    return txtFirstName.getText();
  }

  public String getLastName() {
    return txtLastName.getText();
  }

  public String getCode() {
    return txtCode.getText();
  }

  public String getEncryptMethod() {
    return (String) cmbEncryptMethod.getSelectedItem();
  }

  public String getLogMethod() {
    return (String) cmbLogMethod.getSelectedItem();
  }
} // End of class AgentGUI

class ButtonHandler implements ActionListener {
  AgentGUI objAgentGUI;
  MessageWriter writer;
  AgentInfo info;

  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals(AgentGUI.EXIT)) {
      System.exit(1);
    }
    if (e.getActionCommand().equals(AgentGUI.ADDINFO)) {
      String firstNm = objAgentGUI.getFirstName();
      String lastNm = objAgentGUI.getLastName();
      String code = objAgentGUI.getCode();

      String encryptWay = objAgentGUI.getEncryptMethod();
      String logWay = objAgentGUI.getLogMethod();

      // Create a customer object
      if (logWay.compareTo(AgentGUI.DBWRITER) == 0)
        writer = new DBWriter();
      if (logWay.compareTo(AgentGUI.FILEWRITER) == 0)
        writer = new FileWriter();

      if (encryptWay.compareTo(AgentGUI.FOLDENCRYPTION) == 0)
        info = new EncryptedInfo1(writer);

      if (encryptWay.compareTo(AgentGUI.BIDIRSHIFTENCRYPTION) == 0)
        info = new EncryptedInfo2(writer);

      System.out.println(firstNm + lastNm + code);

      info.log(lastNm, firstNm, code);

    }
  }

  public ButtonHandler() {
  }

  public ButtonHandler(AgentGUI inObjAgentGUI) {
    objAgentGUI = inObjAgentGUI;
  }

} // End of class ButtonHandler
