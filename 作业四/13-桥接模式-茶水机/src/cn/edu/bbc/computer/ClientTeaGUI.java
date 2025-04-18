package cn.edu.bbc.computer;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
//import com.sun.java.swing.plaf.windows.*;

public class ClientTeaGUI extends JFrame {
	public static final String FIND_PRICE = "Find Price";
	public static final String EXIT = "Exit";
	public static final String SUPER_CUP = "Super Cup";
	public static final String MEDIUM_CUP = "Medium Cup";
	public static final String RED_TEA = "Red Tea";
	public static final String GREEN_TEA = "Green Tea";

	private JLabel lblCupSize, lblTeaKind, lblTeaPrice, lblChosenTeaPrice;
	private JComboBox<String> cmbCupSize, cmbTeaKind;
	private JButton findBtn, exitButton;

	public ClientTeaGUI() {
		super(" Bridge Pattern: Tea Seller Machine ");

		cmbCupSize = new JComboBox<>(new String[] { SUPER_CUP, MEDIUM_CUP }); // 参数化构造
        cmbTeaKind = new JComboBox<>(new String[] { RED_TEA, GREEN_TEA }); // 参数化构造

        lblCupSize = new JLabel("Choose Cup Size");
        lblTeaKind = new JLabel("Choose Tea Kind");
        lblTeaPrice = new JLabel("Tea Price:");
        lblChosenTeaPrice = new JLabel("Tea Price will be shown here");


		// Create the open button
		findBtn = new JButton(FIND_PRICE);
		findBtn.setMnemonic(KeyEvent.VK_V);
		exitButton = new JButton(EXIT);
		exitButton.setMnemonic(KeyEvent.VK_X);
		ButtonListener objButtonListener = new ButtonListener();

		findBtn.addActionListener(objButtonListener);
		exitButton.addActionListener(new ButtonListener());

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(findBtn);
		buttonPanel.add(exitButton);

		JPanel UIPanel = new JPanel();

		// ****************************************************
		GridBagLayout gridBag = new GridBagLayout();
		UIPanel.setLayout(gridBag);
		GridBagConstraints gbc = new GridBagConstraints();

		UIPanel.add(lblCupSize);
		UIPanel.add(cmbCupSize);
		UIPanel.add(lblTeaKind);
		UIPanel.add(cmbTeaKind);
		UIPanel.add(lblTeaPrice);
		UIPanel.add(lblChosenTeaPrice);
		UIPanel.add(buttonPanel);

		gbc.insets.top = 5;
		gbc.insets.bottom = 5;
		gbc.insets.left = 5;
		gbc.insets.right = 5;
		gbc.anchor = GridBagConstraints.WEST;

		gbc.gridx = 0;
		gbc.gridy = 0;
		gridBag.setConstraints(lblCupSize, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gridBag.setConstraints(cmbCupSize, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gridBag.setConstraints(lblTeaKind, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gridBag.setConstraints(cmbTeaKind, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gridBag.setConstraints(lblTeaPrice, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gridBag.setConstraints(lblChosenTeaPrice, gbc);

		gbc.insets.left = 2;
		gbc.insets.right = 2;
		gbc.insets.top = 40;

		gbc.gridx = 1;
		gbc.gridy = 5;
		gridBag.setConstraints(buttonPanel, gbc);

		Container contentPane = getContentPane();
		contentPane.add(UIPanel, BorderLayout.CENTER);

//		try {
//			UIManager.setLookAndFeel(new WindowsLookAndFeel());
//			SwingUtilities.updateComponentTreeUI(ClientTeaGUI.this);
//		} catch (Exception ex) {
//			System.out.println(ex);
//		}
	}

	public static void main(String[] args) {
		JFrame frame = new ClientTeaGUI();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setSize(350, 300);
		frame.setVisible(true);
	}

	public String getTeaSize() {
		return (String) cmbCupSize.getSelectedItem();
	}

	public String getTeaKind() {
		return (String) cmbTeaKind.getSelectedItem();
	}

	class ButtonListener implements ActionListener {

        TeaKind tKind;
        TeaSize tSize;

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(EXIT)) {
                System.exit(1);
            }
            if (e.getActionCommand().equals(FIND_PRICE)) {

                String size = getTeaSize();
                String kind = getTeaKind();

                // Create a customer object
				//修改的部分，将杯子和茶品互换
                if (size.equals(SUPER_CUP)) 
                    tSize = new SuperCup();
                if (size.equals(MEDIUM_CUP)) 
                    tSize = new MediumCup();
                if (kind.equals(GREEN_TEA)) 
                    tKind = new GreenTea(tSize);
                if (kind.equals(RED_TEA)) 
                    tKind = new RedTea(tSize);
               
                float price = tKind.getPrice();
                lblChosenTeaPrice.setText("" + price + "  dollars");  
            }
        }
	} // End of class ButtonListener
}

