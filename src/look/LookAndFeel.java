//package look;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.LinkedList;
//import java.util.List;
//
//import javax.swing.BorderFactory;
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.DefaultListModel;
//import javax.swing.JButton;
//import javax.swing.JCheckBox;
//import javax.swing.JComboBox;
//import javax.swing.JEditorPane;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JList;
//import javax.swing.JPanel;
//import javax.swing.JPasswordField;
//import javax.swing.JProgressBar;
//import javax.swing.JRadioButton;
//import javax.swing.JScrollBar;
//import javax.swing.JScrollPane;
//import javax.swing.JSeparator;
//import javax.swing.JSlider;
//import javax.swing.JSpinner;
//import javax.swing.JTabbedPane;
//import javax.swing.JTable;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//import javax.swing.JToggleButton;
//import javax.swing.JTree;
//import javax.swing.SwingUtilities;
//import javax.swing.UIManager;
//import javax.swing.border.BevelBorder;
//import javax.swing.border.Border;
//import javax.swing.border.EtchedBorder;
//import javax.swing.border.LineBorder;
//import javax.swing.border.SoftBevelBorder;
//import javax.swing.border.TitledBorder;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.tree.DefaultMutableTreeNode;
//import javax.swing.tree.DefaultTreeModel;
//
//import org.dyno.visual.swing.layouts.Bilateral;
//import org.dyno.visual.swing.layouts.Constraints;
//import org.dyno.visual.swing.layouts.GroupLayout;
//import org.dyno.visual.swing.layouts.Leading;
//import org.dyno.visual.swing.layouts.Trailing;
//
////VS4E -- DO NOT REMOVE THIS LINE!
//public class LookAndFeel extends JFrame {
//
//	
//	private static final long serialVersionUID = 1L;
//	private JTabbedPane jTabbedPane0;
//	private JPanel jPanel0;
//	private JPanel jPanel1;
//	private JPanel jPanel2;
//	private JLabel jLabel0;
//	private JButton jButton0;
//	private JToggleButton jToggleButton0;
//	private JCheckBox jCheckBox0;
//	private JRadioButton jRadioButton0;
//	private JList jList0;
//	private JScrollPane jScrollPane0;
//	private JTextField jTextField0;
//	private JTextArea jTextArea0;
//	private JScrollPane jScrollPane1;
//	private JTable jTable0;
//	private JScrollPane jScrollPane2;
//	private JTree jTree0;
//	private JScrollPane jScrollPane3;
//	private JEditorPane jEditorPane0;
//	private JScrollPane jScrollPane4;
//	private JSpinner jSpinner0;
//	private JSeparator jSeparator0;
//	private JProgressBar jProgressBar0;
//	private JPasswordField jPasswordField0;
//	private JSlider jSlider0;
//	private JScrollBar jScrollBar0;
//	private JLabel jLabel1;
//	private JComboBox jComboBox0;
//	private JLabel jLabel2;
//	private JComboBox jComboBox1;
//	private JLabel jLabel3;
//	private JComboBox jComboBox2;
//	private JLabel jLabel4;
//	private JComboBox jComboBox3;
//	private JLabel jLabel5;
//	private JLabel jLabel6;
//	private JComboBox jComboBox4;
//	
//	public LookAndFeel() {
//		initComponents();
//	}
//
//	private void initComponents() {
//		setLayout(new GroupLayout());
//		add(getJPanel0(), new Constraints(new Bilateral(12, 12, 0), new Leading(12, 389, 10, 10)));
//		setSize(737, 411);
//	}
//
//	private JComboBox getJComboBox4() {
//		if (jComboBox4 == null) {
//			jComboBox4 = new JComboBox();
//			jComboBox4.setModel(new DefaultComboBoxModel(lookAndFeelName4.toArray()));
//			jComboBox4.setDoubleBuffered(false);
//			jComboBox4.setBorder(null);
//			jComboBox4.addActionListener(new ActionListener() {
//	
//				public void actionPerformed(ActionEvent event) {
//					jComboBox4ActionActionPerformed(event);
//				}
//			});
//		}
//		return jComboBox4;
//	}
//
//	private JLabel getJLabel6() {
//		if (jLabel6 == null) {
//			jLabel6 = new JLabel();
//			jLabel6.setText("JTattoo");
//		}
//		return jLabel6;
//	}
//
//	private JLabel getJLabel5() {
//		if (jLabel5 == null) {
//			jLabel5 = new JLabel();
//			jLabel5.setText("LookAndFeel");
//		}
//		return jLabel5;
//	}
//
//	private JComboBox getJComboBox3() {
//		if (jComboBox3 == null) {
//			jComboBox3 = new JComboBox();
//			jComboBox3.setModel(new DefaultComboBoxModel(lookAndFeelName3.toArray()));
//			jComboBox3.setDoubleBuffered(false);
//			jComboBox3.setBorder(null);
//			jComboBox3.addActionListener(new ActionListener() {
//	
//				public void actionPerformed(ActionEvent event) {
//					jComboBox3ActionActionPerformed(event);
//				}
//			});
//		}
//		return jComboBox3;
//	}
//
//	private JLabel getJLabel4() {
//		if (jLabel4 == null) {
//			jLabel4 = new JLabel();
//			jLabel4.setText("Other");
//		}
//		return jLabel4;
//	}
//
//	private JComboBox getJComboBox2() {
//		if (jComboBox2 == null) {
//			jComboBox2 = new JComboBox();
//			jComboBox2.setModel(new DefaultComboBoxModel(lookAndFeelName2.toArray()));
//			jComboBox2.setDoubleBuffered(false);
//			jComboBox2.setBorder(null);
//			jComboBox2.addActionListener(new ActionListener() {
//	
//				public void actionPerformed(ActionEvent event) {
//					jComboBox2ActionActionPerformed(event);
//				}
//			});
//		}
//		return jComboBox2;
//	}
//
//	private JLabel getJLabel3() {
//		if (jLabel3 == null) {
//			jLabel3 = new JLabel();
//			jLabel3.setText("Swing");
//		}
//		return jLabel3;
//	}
//
//	private JComboBox getJComboBox1() {
//		if (jComboBox1 == null) {
//			jComboBox1 = new JComboBox();
//			jComboBox1.setModel(new DefaultComboBoxModel(lookAndFeelName1.toArray()));
//			jComboBox1.setDoubleBuffered(false);
//			jComboBox1.setBorder(null);
//			jComboBox1.addActionListener(new ActionListener() {
//	
//				public void actionPerformed(ActionEvent event) {
//					jComboBox1ActionActionPerformed(event);
//				}
//			});
//		}
//		return jComboBox1;
//	}
//
//	private JLabel getJLabel2() {
//		if (jLabel2 == null) {
//			jLabel2 = new JLabel();
//			jLabel2.setText("Substance");
//		}
//		return jLabel2;
//	}
//
//	private JComboBox getJComboBox0() {
//		if (jComboBox0 == null) {
//			jComboBox0 = new JComboBox();
//			jComboBox0.setModel(new DefaultComboBoxModel(borderName.toArray()));
//			jComboBox0.setDoubleBuffered(false);
//			jComboBox0.setBorder(null);
//			jComboBox0.addActionListener(new ActionListener() {
//	
//				public void actionPerformed(ActionEvent event) {
//					jComboBox0ActionActionPerformed(event);
//				}
//			});
//		}
//		return jComboBox0;
//	}
//
//	private JLabel getJLabel1() {
//		if (jLabel1 == null) {
//			jLabel1 = new JLabel();
//			jLabel1.setText("Border");
//		}
//		return jLabel1;
//	}
//
//	private JScrollBar getJScrollBar0() {
//		if (jScrollBar0 == null) {
//			jScrollBar0 = new JScrollBar();
//		}
//		return jScrollBar0;
//	}
//
//	private JSlider getJSlider0() {
//		if (jSlider0 == null) {
//			jSlider0 = new JSlider();
//		}
//		return jSlider0;
//	}
//
//	private JPasswordField getJPasswordField0() {
//		if (jPasswordField0 == null) {
//			jPasswordField0 = new JPasswordField();
//			jPasswordField0.setText("jPasswordField0");
//			jPasswordField0.setEchoChar('*');
//		}
//		return jPasswordField0;
//	}
//
//	private JProgressBar getJProgressBar0() {
//		if (jProgressBar0 == null) {
//			jProgressBar0 = new JProgressBar();
//			jProgressBar0.setValue(50);
//		}
//		return jProgressBar0;
//	}
//
//	private JSeparator getJSeparator0() {
//		if (jSeparator0 == null) {
//			jSeparator0 = new JSeparator();
//		}
//		return jSeparator0;
//	}
//
//	private JSpinner getJSpinner0() {
//		if (jSpinner0 == null) {
//			jSpinner0 = new JSpinner();
//		}
//		return jSpinner0;
//	}
//
//	private JScrollPane getJScrollPane4() {
//		if (jScrollPane4 == null) {
//			jScrollPane4 = new JScrollPane();
//			jScrollPane4.setViewportView(getJEditorPane0());
//		}
//		return jScrollPane4;
//	}
//
//	private JEditorPane getJEditorPane0() {
//		if (jEditorPane0 == null) {
//			jEditorPane0 = new JEditorPane();
//			jEditorPane0.setText("jEditorPane0");
//		}
//		return jEditorPane0;
//	}
//
//	private JScrollPane getJScrollPane3() {
//		if (jScrollPane3 == null) {
//			jScrollPane3 = new JScrollPane();
//			jScrollPane3.setViewportView(getJTree0());
//		}
//		return jScrollPane3;
//	}
//
//	private JTree getJTree0() {
//		if (jTree0 == null) {
//			jTree0 = new JTree();
//			DefaultTreeModel treeModel = null;
//			{
//				DefaultMutableTreeNode node0 = new DefaultMutableTreeNode("JTree");
//				{
//					DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("colors");
//					{
//						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("blue");
//						node1.add(node2);
//					}
//					{
//						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("violet");
//						node1.add(node2);
//					}
//					{
//						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("red");
//						node1.add(node2);
//					}
//					{
//						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("yellow");
//						node1.add(node2);
//					}
//					node0.add(node1);
//				}
//				{
//					DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("sports");
//					{
//						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("basketball");
//						node1.add(node2);
//					}
//					{
//						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("soccer");
//						node1.add(node2);
//					}
//					{
//						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("football");
//						node1.add(node2);
//					}
//					{
//						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("hockey");
//						node1.add(node2);
//					}
//					node0.add(node1);
//				}
//				{
//					DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("food");
//					{
//						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("hot dogs");
//						node1.add(node2);
//					}
//					{
//						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("pizza");
//						node1.add(node2);
//					}
//					{
//						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("ravioli");
//						node1.add(node2);
//					}
//					{
//						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("bananas");
//						node1.add(node2);
//					}
//					node0.add(node1);
//				}
//				treeModel = new DefaultTreeModel(node0);
//			}
//			jTree0.setModel(treeModel);
//		}
//		return jTree0;
//	}
//
//	private JScrollPane getJScrollPane2() {
//		if (jScrollPane2 == null) {
//			jScrollPane2 = new JScrollPane();
//			jScrollPane2.setViewportView(getJTable0());
//		}
//		return jScrollPane2;
//	}
//
//	private JTable getJTable0() {
//		if (jTable0 == null) {
//			jTable0 = new JTable();
//			jTable0.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
//				private static final long serialVersionUID = 1L;
//				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
//	
//				public Class<?> getColumnClass(int columnIndex) {
//					return types[columnIndex];
//				}
//			});
//		}
//		return jTable0;
//	}
//
//	private JScrollPane getJScrollPane1() {
//		if (jScrollPane1 == null) {
//			jScrollPane1 = new JScrollPane();
//			jScrollPane1.setViewportView(getJTextArea0());
//		}
//		return jScrollPane1;
//	}
//
//	private JTextArea getJTextArea0() {
//		if (jTextArea0 == null) {
//			jTextArea0 = new JTextArea();
//			jTextArea0.setText("jTextArea0");
//		}
//		return jTextArea0;
//	}
//
//	private JTextField getJTextField0() {
//		if (jTextField0 == null) {
//			jTextField0 = new JTextField();
//			jTextField0.setText("jTextField0");
//		}
//		return jTextField0;
//	}
//
//	private JScrollPane getJScrollPane0() {
//		if (jScrollPane0 == null) {
//			jScrollPane0 = new JScrollPane();
//			jScrollPane0.setViewportView(getJList0());
//		}
//		return jScrollPane0;
//	}
//
//	private JList getJList0() {
//		if (jList0 == null) {
//			jList0 = new JList();
//			DefaultListModel listModel = new DefaultListModel();
//			listModel.addElement("item0");
//			listModel.addElement("item1");
//			listModel.addElement("item2");
//			listModel.addElement("item3");
//			jList0.setModel(listModel);
//		}
//		return jList0;
//	}
//
//	private JRadioButton getJRadioButton0() {
//		if (jRadioButton0 == null) {
//			jRadioButton0 = new JRadioButton();
//			jRadioButton0.setSelected(true);
//			jRadioButton0.setText("jRadioButton0");
//		}
//		return jRadioButton0;
//	}
//
//	private JCheckBox getJCheckBox0() {
//		if (jCheckBox0 == null) {
//			jCheckBox0 = new JCheckBox();
//			jCheckBox0.setText("jCheckBox0");
//		}
//		return jCheckBox0;
//	}
//
//	private JToggleButton getJToggleButton0() {
//		if (jToggleButton0 == null) {
//			jToggleButton0 = new JToggleButton();
//			jToggleButton0.setText("jToggleButton0");
//		}
//		return jToggleButton0;
//	}
//
//	private JButton getJButton0() {
//		if (jButton0 == null) {
//			jButton0 = new JButton();
//			jButton0.setText("jButton0");
//		}
//		return jButton0;
//	}
//
//	private JLabel getJLabel0() {
//		if (jLabel0 == null) {
//			jLabel0 = new JLabel();
//			jLabel0.setText("jLabel0");
//		}
//		return jLabel0;
//	}
//
//	private JPanel getJPanel1() {
//		if (jPanel1 == null) {
//			jPanel1 = new JPanel();
//			jPanel1.setLayout(new GroupLayout());
//		}
//		return jPanel1;
//	}
//	
//	private JPanel getJPanel2(){
//		if(jPanel2==null){
//			jPanel2=new JPanel();
//			jPanel2.setLayout(new GroupLayout());
//		}
//		return jPanel2;
//	}
//
//	private JPanel getJPanel0() {
//		if (jPanel0 == null) {
//			jPanel0 = new JPanel();
//			jPanel0.setLayout(new GroupLayout());
//			jPanel0.add(getJTabbedPane0(), new Constraints(new Leading(12, 203, 10, 10), new Leading(12, 100, 12, 12)));
//			jPanel0.add(getJLabel0(), new Constraints(new Leading(233, 88, 10, 10), new Leading(12, 23, 10, 10)));
//			jPanel0.add(getJButton0(), new Constraints(new Leading(231, 10, 10), new Leading(47, 12, 12)));
//			jPanel0.add(getJTextField0(), new Constraints(new Leading(12, 79, 10, 10), new Leading(126, 12, 12)));
//			jPanel0.add(getJScrollPane1(), new Constraints(new Leading(11, 100, 12, 12), new Leading(157, 80, 12, 12)));
//			jPanel0.add(getJSpinner0(), new Constraints(new Leading(16, 79, 10, 10), new Leading(252, 10, 10)));
//			jPanel0.add(getJProgressBar0(), new Constraints(new Leading(11, 174, 10, 10), new Leading(312, 10, 10)));
//			jPanel0.add(getJPasswordField0(), new Constraints(new Leading(207, 120, 10, 10), new Leading(306, 12, 12)));
//			jPanel0.add(getJSlider0(), new Constraints(new Leading(12, 12, 12), new Leading(342, 10, 10)));
//			jPanel0.add(getJScrollPane2(), new Constraints(new Leading(117, 200, 12, 12), new Leading(132, 150, 12, 12)));
//			jPanel0.add(getJSeparator0(), new Constraints(new Leading(9, 312, 12, 12), new Leading(293, 10, 12, 12)));
//			jPanel0.add(getJRadioButton0(), new Constraints(new Leading(337, 8, 8), new Leading(54, 8, 8)));
//			jPanel0.add(getJCheckBox0(), new Constraints(new Leading(337, 8, 8), new Leading(12, 8, 8)));
//			jPanel0.add(getJScrollBar0(), new Constraints(new Leading(451, 12, 12), new Leading(18, 183, 10, 10)));
//			jPanel0.add(getJLabel1(), new Constraints(new Leading(480, 63, 10, 10), new Leading(25, 23, 10, 10)));
//			jPanel0.add(getJComboBox0(), new Constraints(new Bilateral(555, 12, 60), new Leading(23, 12, 12)));
//			jPanel0.add(getJLabel3(), new Constraints(new Leading(480, 66, 10, 10), new Leading(132, 22, 10, 10)));
//			jPanel0.add(getJComboBox2(), new Constraints(new Bilateral(555, 12, 60), new Leading(130, 12, 12)));
//			jPanel0.add(getJComboBox3(), new Constraints(new Bilateral(555, 12, 60), new Leading(172, 12, 12)));
//			jPanel0.add(getJLabel4(), new Constraints(new Leading(480, 62, 84, 84), new Leading(172, 25, 12, 12)));
//			jPanel0.add(getJScrollPane3(), new Constraints(new Leading(502, 161, 12, 12), new Leading(242, 117, 10, 10)));
//			jPanel0.add(getJScrollPane4(), new Constraints(new Leading(359, 10, 10), new Leading(242, 118, 10, 10)));
//			jPanel0.add(getJScrollPane0(), new Constraints(new Leading(340, 92, 10, 10), new Leading(130, 85, 12, 12)));
//			jPanel0.add(getJToggleButton0(), new Constraints(new Leading(229, 208, 10, 10), new Leading(88, 10, 10)));
//			jPanel0.add(getJLabel5(), new Constraints(new Leading(555, 79, 12, 12), new Leading(60, 22, 12, 12)));
//			jPanel0.add(getJComboBox1(), new Constraints(new Leading(555, 146, 12, 12), new Leading(203, 12, 12)));
//			jPanel0.add(getJLabel2(), new Constraints(new Leading(480, 62, 12, 12), new Leading(203, 23, 12, 12)));
//			jPanel0.add(getJLabel6(), new Constraints(new Leading(478, 66, 12, 12), new Leading(100, 21, 12, 12)));
//			jPanel0.add(getJComboBox4(), new Constraints(new Trailing(12, 146, 539, 562), new Leading(98, 12, 12)));
//		}
//		return jPanel0;
//	}
//
//	private JTabbedPane getJTabbedPane0() {
//		if (jTabbedPane0 == null) {
//			jTabbedPane0 = new JTabbedPane();
//		    jTabbedPane0.add("Tab1",getJPanel1());
//		    jTabbedPane0.add("Tab2",getJPanel2());
//		    
//		}
//		return jTabbedPane0;
//	}
//
//	private static void installLnF() {
//		try {
//			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
//			if (lnfClassname == null)
//				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
//			UIManager.setLookAndFeel(lnfClassname);
//		} catch (Exception e) {
//			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
//					+ " on this platform:" + e.getMessage());
//		}
//	}
//
//	/**
//	 * Main entry of the class.
//	 * Note: This class is only created so that you can easily preview the result at runtime.
//	 * It is not expected to be managed by the designer.
//	 * You can modify it as you like.
//	 */
//	public static void main(String[] args) {
//		installLnF();
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				LookAndFeel frame = new LookAndFeel();
//				frame.setDefaultCloseOperation(LookAndFeel.EXIT_ON_CLOSE);
//				frame.setTitle("LookAndFeel");
//				frame.getContentPane().setPreferredSize(frame.getSize());
//				frame.pack();
//				frame.setLocationRelativeTo(null);
//				frame.setVisible(true);
//			}
//		});
//	}
//
//	public static List<Border> border = new LinkedList<Border>();
//	public static List<String> borderName = new LinkedList<String>();
//static {
//		borderName.add("Null");
//		border.add(null);
//
//		borderName.add("Empty");
//		border.add(BorderFactory.createEmptyBorder(0, 0, 0, 0));
//
//		borderName.add("Line");
//		border.add(new LineBorder(Color.black, 1, false));
//
//		borderName.add("Compound");
//		border.add(BorderFactory.createCompoundBorder(new LineBorder(
//				Color.black, 1, false), new LineBorder(Color.WHITE, 1, true)));
//
//		borderName.add("Bebel");
//		border.add(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null,
//				null, null, null));
//
//		borderName.add("Etched");
//		border.add(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null,
//				null));
//
//		borderName.add("Matte");// 鍥涘懆鍘氬害涓嶄竴鏍�
//		border.add(BorderFactory.createMatteBorder(2, 3, 4, 5, Color.darkGray));
//
//		borderName.add("SoftBevel");
//		border.add(new SoftBevelBorder(BevelBorder.LOWERED, Color.BLUE,
//				Color.white, Color.yellow, Color.pink));
//
//		borderName.add("Title");
//		border.add(BorderFactory.createTitledBorder(null, "Border Title",
//				TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font(
//						"Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
//	}
//
//    //border
//	private void jComboBox0ActionActionPerformed(ActionEvent event) {
//		int n=jComboBox0.getSelectedIndex();
//		System.out.println("Border:"+borderName.get(n));
//		jPanel0.setBorder(border.get(n));	
//	}
//
//	//////////////////////////substance////////////////////// 16
//	public static List<String> lookAndFeel1 = new LinkedList<String>();
//	public static List<String> lookAndFeelName1 = new LinkedList<String>();
//	static{
//		lookAndFeel1.add("org.jvnet.substance.skin.SubstanceBusinessLookAndFeel");//
//		lookAndFeelName1.add("BusinessLookAndFeel");
//		
//		lookAndFeel1.add("org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel");
//		lookAndFeelName1.add("BusinessBlueSteelLookAndFeel");
//		
//		lookAndFeel1.add("org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel");
//		lookAndFeelName1.add("BusinessBlackSteelLookAndFeel");
//		
//		lookAndFeel1.add("org.jvnet.substance.skin.SubstanceCremeLookAndFeel");
//		lookAndFeelName1.add("CremeLookAndFeel");
//		
//		lookAndFeel1.add("org.jvnet.substance.skin.SubstanceCremeCoffeeLookAndFeel");
//		lookAndFeelName1.add("CremeCoffeeLookAndFeel");
//		
//		lookAndFeel1.add("org.jvnet.substance.skin.SubstanceSaharaLookAndFeel");
//		lookAndFeelName1.add("SaharaLookAndFeel");
//		
//		lookAndFeel1.add("org.jvnet.substance.skin.SubstanceModerateLookAndFeel");
//		lookAndFeelName1.add("ModerateLookAndFeel");
//		
//		lookAndFeel1.add("org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel");
//		lookAndFeelName1.add("OfficeSilver2007LookAndFeel");
//		
//		lookAndFeel1.add("org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel");
//		lookAndFeelName1.add("OfficeBlue2007LookAndFeel");
//		
//		lookAndFeel1.add("org.jvnet.substance.skin.SubstanceNebulaBrickWallLookAndFeel");
//		lookAndFeelName1.add("NebulaBrickWallLookAndFeel");
//		
//		lookAndFeel1.add("org.jvnet.substance.skin.SubstanceAutumnLookAndFeel");
//		lookAndFeelName1.add("AutumnLookAndFeel");
//		
//		lookAndFeel1.add("org.jvnet.substance.skin.SubstanceMistSilverLookAndFeel");//
//		lookAndFeelName1.add("MistSilverLookAndFeel");
//		
//		lookAndFeel1.add("org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel");
//		lookAndFeelName1.add("MistAquaLookAndFeel");
//		
//		lookAndFeel1.add("org.jvnet.substance.skin.SubstanceDustLookAndFeel");
//		lookAndFeelName1.add("DustLookAndFeel");
//		
//		lookAndFeel1.add("org.jvnet.substance.skin.SubstanceDustCoffeeLookAndFeel");
//		lookAndFeelName1.add("DustCoffeeLookAndFeel");
//		
//		lookAndFeel1.add("org.jvnet.substance.api.skin.SubstanceGeminiLookAndFeel");
//		lookAndFeelName1.add("GeminiLookAndFeel");
//	}
//	private void jComboBox1ActionActionPerformed(ActionEvent event) {
//		//浠巗ubstance鍒囨崲鍒板叾瀹僉nF浼氬嚭寮傚父
//		jComboBox2.setEnabled(false);
//		jComboBox3.setEnabled(false);
//		jComboBox4.setEnabled(false);
//		int n=jComboBox1.getSelectedIndex();
//		System.out.println("LookAndFeel:" + lookAndFeelName1.get(n));
//		switchLnF(lookAndFeel1.get(n));
//	}
//
//	////////////////////////////swing////////////////////////////////////// 5
//	public static List<String> lookAndFeel2 = new LinkedList<String>();
//	public static List<String> lookAndFeelName2 = new LinkedList<String>();
//	static{
//		lookAndFeel2.add("javax.swing.plaf.metal.MetalLookAndFeel");
//		lookAndFeelName2.add("MetalLookAndFeel");
//
//		lookAndFeel2.add("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//		lookAndFeelName2.add("WindowsLookAndFeel");
//
//		lookAndFeel2.add("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
//		lookAndFeelName2.add("WindowsClassicLookAndFeel");
//
//		lookAndFeel2.add("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//		lookAndFeelName2.add("MotifLookAndFeel");
//
//		lookAndFeel2.add("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//		lookAndFeelName2.add("NimbusLookAndFeel");
//	}
//	private void jComboBox2ActionActionPerformed(ActionEvent event) {
//	
//		int n=jComboBox2.getSelectedIndex();
//		System.out.println("LookAndFeel:" + lookAndFeelName2.get(n));
//		switchLnF(lookAndFeel2.get(n));
//	}
//
//	//////////////////////other LookAndFeel///////////////////// 6
//	public static List<String> lookAndFeel3 = new LinkedList<String>();
//	public static List<String> lookAndFeelName3 = new LinkedList<String>();
//	static{
//		lookAndFeel3.add("napkin.NapkinLookAndFeel");
//		lookAndFeelName3.add("NapkinLookAndFeel");
//		
//		lookAndFeel3.add("org.fife.plaf.OfficeXP.OfficeXPLookAndFeel");
//		lookAndFeelName3.add("OfficeXPLookAndFeel");
//		
//		lookAndFeel3.add("org.fife.plaf.VisualStudio2005.VisualStudio2005LookAndFeel");
//		lookAndFeelName3.add("VisualStudio2005LookAndFeel");
//		
//		lookAndFeel3.add("de.hillenbrand.swing.plaf.threeD.ThreeDLookAndFeel");
//		lookAndFeelName3.add("3DLookAndFeel");
//		
//		lookAndFeel3.add("com.pagosoft.plaf.PgsLookAndFeel");
//		lookAndFeelName3.add("PgsLookAndFeel");
//		
//		lookAndFeel3.add("com.nilo.plaf.nimrod.NimRODLookAndFeel");
//		lookAndFeelName3.add("NimRODLookAndFeel");
//	}
//	private void jComboBox3ActionActionPerformed(ActionEvent event) {
//		
//		int n=jComboBox3.getSelectedIndex();
//		System.out.println("LookAndFeel:" + lookAndFeelName3.get(n));
//		switchLnF(lookAndFeel3.get(n));
//	}
//	
//	////////////////////JTattoo LookAndFeel////////////////////// 11
//	public static List<String> lookAndFeel4 = new LinkedList<String>();
//	public static List<String> lookAndFeelName4 = new LinkedList<String>();
//	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
//	static{
//		lookAndFeel4.add("com.jtattoo.plaf.noire.NoireLookAndFeel");
//		lookAndFeelName4.add("NoireLookAndFeel");//鏌斿拰榛�
//		
//		lookAndFeel4.add("com.jtattoo.plaf.smart.SmartLookAndFeel");
//		lookAndFeelName4.add("SmartLookAndFeel");//鏈ㄨ川鎰�+xp椋庢牸
//		
//		lookAndFeel4.add("com.jtattoo.plaf.mint.MintLookAndFeel");
//		lookAndFeelName4.add("MintLookAndFeel");//妞渾鎸夐挳+榛勮壊鎸夐挳鑳屾櫙
//		
//		lookAndFeel4.add("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
//		lookAndFeelName4.add("McWinLookAndFeel");//妞渾鎸夐挳+缁胯壊鎸夐挳鑳屾櫙
//		
//		lookAndFeel4.add("com.jtattoo.plaf.luna.LunaLookAndFeel");
//		lookAndFeelName4.add("LunaLookAndFeel");//绾疿P椋庢牸
//		
//		lookAndFeel4.add("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
//		lookAndFeelName4.add("HiFiLookAndFeel");//榛戣壊椋庢牸
//		
//		lookAndFeel4.add("com.jtattoo.plaf.fast.FastLookAndFeel");
//		lookAndFeelName4.add("FastLookAndFeel");//鏅�歴wing椋庢牸+钃濊壊杈规
//		
//		lookAndFeel4.add("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
//		lookAndFeelName4.add("BernsteinLookAndFeel");//榛勮壊椋庢牸
//		
//		lookAndFeel4.add("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
//		lookAndFeelName4.add("AluminiumLookAndFeel");//妞渾鎸夐挳+缈犵豢鑹叉寜閽儗鏅�+閲戝睘璐ㄦ劅
//		
//		lookAndFeel4.add("com.jtattoo.plaf.aero.AeroLookAndFeel");
//		lookAndFeelName4.add("AeroLookAndFeel");//xp娓呮柊椋庢牸
//		
//		lookAndFeel4.add("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
//		lookAndFeelName4.add("AcrylLookAndFeel");//甯冭川鎰�+swing绾鏍�
//	}
//	private void jComboBox4ActionActionPerformed(ActionEvent event) {
//		int n=jComboBox4.getSelectedIndex();
//		System.out.println("LookAndFeel:" + lookAndFeelName4.get(n));
//		switchLnF(lookAndFeel4.get(n));
//	}
//	
//	private void switchLnF(String LnF){	
//		try {
//			JFrame.setDefaultLookAndFeelDecorated(true);
//			UIManager.setLookAndFeel(LnF);
//			SwingUtilities.updateComponentTreeUI(this);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.err.println("Cannot install " + LnF
//					+ " on this platform:" + e.getMessage());
//		}
//	}
//
//
//
//}
