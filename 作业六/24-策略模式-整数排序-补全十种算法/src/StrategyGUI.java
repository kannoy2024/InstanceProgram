import java.awt.event.*;
import java.awt.*;
// import java.net.*;
import javax.swing.*;
// import javax.swing.border.Border;
// import java.io.File;
// import java.io.IOException;

public class StrategyGUI extends JFrame {
   private JSplitPane bigSplitPane;
   private JScrollPane showInfoPane;
   private JPanel btnPanel;
   private JComboBox<String> cmbAlgorithm;
   private JLabel lblAlgorithm;
   private Dimension minimumSize;
   private JTextArea txtForInfo;
   private JTextField txtToBeSorted;
   private Context context;

   public static final String SORT = "Sort";
   public static final String EXIT = "Exit";
   public static final String BUBBLE = "Bubble Sort";// 冒泡排序
   public static final String HEAP = "Heap Sort";// 堆排序
   public static final String INSERT = "Insertion Sort";// 插入排序
   public static final String QUICK = "Quick Sort";// 快速排序

   //添加的算法
   public static final String SELECTION = "Selection Sort";// 选择排序
   public static final String SHELL = "Shell Sort";// 希尔排序
   public static final String MERGE = "Merge Sort";// 归并排序
   public static final String BUCKET = "Bucket Sort";// 桶排序
   public static final String COUNTING = "Counting Sort";// 计数排序
   public static final String RADIX = "Radix Sort";//  基数排序

   public StrategyGUI() {
      super("Strategy Pattern- Sorting Algorithms. ");
      minimumSize = new Dimension(130, 100);
      setUpChoicePanel();
      setUpScrollPanes();

   }

   private void setUpChoicePanel() {

      JLabel lblToBeSorted = new JLabel("Input Integers");
      String toBeSortStr = "6666";
      for (int k = 0; k < 10000; k++) {
         toBeSortStr = toBeSortStr + " " + (10000 - k);
      }
      txtToBeSorted = new JTextField(toBeSortStr, 20);
      cmbAlgorithm = new JComboBox<String>();
      cmbAlgorithm.addItem(BUBBLE);
      cmbAlgorithm.addItem(HEAP);
      cmbAlgorithm.addItem(INSERT);
      cmbAlgorithm.addItem(QUICK);
      cmbAlgorithm.addItem(SELECTION);
      cmbAlgorithm.addItem(SHELL);
      cmbAlgorithm.addItem(MERGE);
      cmbAlgorithm.addItem(BUCKET);
      cmbAlgorithm.addItem(COUNTING);
      cmbAlgorithm.addItem(RADIX);

      lblAlgorithm = new JLabel("Sort Algorithm");

      // Create the open button
      JButton openButton = new JButton(SORT);
      openButton.setMnemonic(KeyEvent.VK_S);
      JButton exitButton = new JButton(EXIT);
      exitButton.setMnemonic(KeyEvent.VK_X);

      ButtonListener btnListener = new ButtonListener();

      // add action Listener
      openButton.addActionListener(btnListener);
      exitButton.addActionListener(btnListener);

      btnPanel = new JPanel();

      // ------------------------------------------------
      GridBagLayout gridBag = new GridBagLayout();
      btnPanel.setLayout(gridBag);
      GridBagConstraints gbc = new GridBagConstraints();

      btnPanel.add(lblToBeSorted);
      btnPanel.add(txtToBeSorted);

      btnPanel.add(lblAlgorithm);
      btnPanel.add(cmbAlgorithm);
      btnPanel.add(openButton);
      btnPanel.add(exitButton);

      gbc.insets.top = 5;
      gbc.insets.bottom = 5;
      gbc.insets.left = 5;
      gbc.insets.right = 5;

      gbc.gridx = 0;
      gbc.gridy = 0;
      gridBag.setConstraints(lblToBeSorted, gbc);
      gbc.gridx = 1;
      gbc.gridy = 0;
      gridBag.setConstraints(txtToBeSorted, gbc);
      gbc.gridx = 0;
      gbc.gridy = 1;
      gridBag.setConstraints(lblAlgorithm, gbc);
      gbc.gridx = 1;
      gbc.gridy = 1;
      gridBag.setConstraints(cmbAlgorithm, gbc);

      gbc.insets.left = 2;
      gbc.insets.right = 2;
      gbc.insets.top = 15;
      gbc.gridx = 0;
      gbc.gridy = 5;
      gridBag.setConstraints(openButton, gbc);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.gridx = 1;
      gbc.gridy = 5;
      gridBag.setConstraints(exitButton, gbc);
      btnPanel.setMinimumSize(minimumSize);
      // -----------------------------------------------
   }

   private void setUpScrollPanes() {
      txtForInfo = new JTextArea("Sorted Array will be shown here.", 20, 30); // 先初始化
      txtForInfo.setBorder(BorderFactory.createRaisedBevelBorder());

      txtForInfo = new JTextArea("Sorted Array will be shown here.", 20, 30);
      txtForInfo.setLineWrap(true);

      txtForInfo.setFont(new Font("Helvetica", Font.BOLD, 15));
      txtForInfo.setBackground(Color.cyan);

      showInfoPane = new JScrollPane(txtForInfo);

      bigSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, showInfoPane, btnPanel);
      bigSplitPane.setDividerLocation(150);

      getContentPane().add(bigSplitPane);
      setSize(new Dimension(500, 300));
      setVisible(true);
   }

   class ButtonListener implements ActionListener {
      public void actionPerformed(ActionEvent ae) {

         if (ae.getActionCommand().equals(EXIT)) {
            System.exit(1);
         }
         if (ae.getActionCommand().equals(SORT)) {
            int len = 0;
            String text = txtToBeSorted.getText();
            String[] toBeSorted = text.split(" ");
            len = toBeSorted.length;

            int[] intArray = new int[len];

            for (int k = 0; k < len; k++) {
               if (toBeSorted[k] == null) {
                  len--;
                  System.out.println(len);
               } else {
                  intArray[k] = Integer.parseInt(toBeSorted[k]);
               }
            }

            String type = (String) cmbAlgorithm.getSelectedItem();
            SortAlgorithm sa = null;

            if (type.equals(BUBBLE)) {
               sa = new BubbleSort();
            }
            if (type.equals(HEAP)) {
               sa = new HeapSort();
            }
            if (type.equals(INSERT)) {
               sa = new InsertSort();
            }
            if (type.equals(QUICK)) {
               sa = new QuickSort();
            }
            if (type.equals(SELECTION)) {
               sa = new SelectionSort();
            }
            if (type.equals(SHELL)) {
               sa = new ShellSort();
            }
            if (type.equals(MERGE)) {
               sa = new MergeSort();
            }
            if (type.equals(BUCKET)) {
               sa = new BucketSort();
            }
            if (type.equals(COUNTING)) {
               sa = new CountingSort();
            }
            if (type.equals(RADIX)) {
               sa = new RadixSort();
            }

            context = new Context(sa);

            intArray = context.sortIntArray(intArray);
            long eTime = context.getExeTime();
            txtForInfo.setText("");

            for (int k = 0; k < len; k++) {
               txtForInfo.append(" " + intArray[k]);
            }
            txtForInfo.append("\nExecution time =  " + eTime);
         }
      }
   }

   public static void main(String args[]) {
      try {
         UIManager.setLookAndFeel("com.sun.java.swing.piaf.windows.WindowsLookAndFeel");
      } catch (Exception evt) {
      }

      StrategyGUI frame = new StrategyGUI();
      frame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            System.exit(0);
         }
      });
      frame.setSize(500, 320);
      frame.setVisible(true);
   }
}
