//package com.attilax.seo;
//
//import java.awt.EventQueue;
//import java.awt.Toolkit;
//
//import javax.swing.JDialog;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JRootPane;
//import javax.swing.JTextField;
//import javax.swing.JButton;
//import javax.swing.JTextArea;
//import javax.swing.SwingUtilities;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
//
//import java.awt.datatransfer.DataFlavor;
//import java.awt.datatransfer.Transferable;
//import java.awt.datatransfer.UnsupportedFlavorException;
//import java.awt.dnd.DnDConstants;
//import java.awt.dnd.DropTarget;
//import java.awt.dnd.DropTargetDragEvent;
//import java.awt.dnd.DropTargetDropEvent;
//import java.awt.dnd.DropTargetEvent;
//import java.awt.dnd.DropTargetListener;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//import java.awt.event.ItemEvent;
//
//import javax.swing.SpringLayout;
//
//import com.attilax.Closure2;
//import com.attilax.floatWin.FloatWin;
//import com.attilax.skin.IS‌kinInir;
//import com.attilax.skin.SkinX;
//import com.attilax.ui.MsgBox;
//import com.attilax.win.Iskin;
//import com.attilax.win.MenuX;
//import com.birosoft.liquid.LiquidLookAndFeel;
//
//import javax.swing.JComboBox;
//
//import java.awt.event.ItemListener;
//
//import javax.swing.JMenuBar;
//import javax.swing.JMenu;
//import javax.swing.JMenuItem;
//import javax.swing.JPopupMenu;
//
//import java.awt.Component;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class SetImgSoftlinkGener extends JFrame  implements Iskin, DropTargetListener {
//
//	private JFrame frame;
//	private JTextField txtWatcheskof;
//	private JTextField txtimagesallwatchesttwimagemedium;
//	JComboBox comboBox;
//
//	/**
//	 * Launch the application.
//	 * @throws UnsupportedLookAndFeelException 
//	 * @throws IllegalAccessException 
//	 * @throws InstantiationException 
//	 * @throws ClassNotFoundException 
//	 */
//	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
//		 //   UIManager.setLookAndFeel("javax.swing.plaf.mac.MacLookAndFeel");
//		//   LiquidLookAndFeel.setLiquidDecorations(true );
//	//	JFrame.setDefaultLookAndFeelDecorated(true);
//	//	UIManager.setLookAndFeel(LnF);
//	//	SwingUtilities.updateComponentTreeUI(this);
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SetImgSoftlinkGener window = new SetImgSoftlinkGener();
//				//	window.show();
//				//	window.comboBox.addItem(arg0);
//				//	window.comboBox.addItem(new String[]{"a","b"});
//					SkinX.setComboBoxVal(window.comboBox);
//					//window.comboBox.addItem(SkinX.skinNames());
//					AppIco.setWindowIcon(window.frame);
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		
//		
//	}
//
//	/**
//	 * Create the application.
//	 */
//	public SetImgSoftlinkGener() {
//		initialize();
//	}
//	JMenuBar menuBar;
//	/**
//	 * Initialize the contents of the frame.
//	 */
//	private void initialize() {
//		menuBar = new JMenuBar();
//		frame = new JFrame();
//		frame.setBounds(100, 100, 938, 484);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		SpringLayout springLayout = new SpringLayout();
//		frame.getContentPane().setLayout(springLayout);
//		
//		JLabel lblNewLabel = new JLabel("imgPostion");
//		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 54, SpringLayout.NORTH, frame.getContentPane());
//		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 27, SpringLayout.WEST, frame.getContentPane());
//		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, 103, SpringLayout.WEST, frame.getContentPane());
//	//	springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, 0, SpringLayout.NORTH, frame.getContentPane());
//		
//		frame.getContentPane().add(lblNewLabel);
//		
//		txtWatcheskof = new JTextField();
//		springLayout.putConstraint(SpringLayout.NORTH, txtWatcheskof, 21, SpringLayout.NORTH, frame.getContentPane());
//		springLayout.putConstraint(SpringLayout.WEST, txtWatcheskof, 113, SpringLayout.WEST, frame.getContentPane());
//		springLayout.putConstraint(SpringLayout.EAST, txtWatcheskof, 566, SpringLayout.WEST, frame.getContentPane());
//		//springLayout.putConstraint(SpringLayout.SOUTH, txtWatcheskof, 0, SpringLayout.NORTH, frame.getContentPane());
//		txtWatcheskof.setText("watcheskof");
//		frame.getContentPane().add(txtWatcheskof);
//		txtWatcheskof.setColumns(10);
//		
//		
//		JTextArea textArea = new JTextArea();
//		springLayout.putConstraint(SpringLayout.NORTH, textArea, 129, SpringLayout.NORTH, frame.getContentPane());
//		springLayout.putConstraint(SpringLayout.WEST, textArea, 21, SpringLayout.WEST, frame.getContentPane());
//		springLayout.putConstraint(SpringLayout.SOUTH, textArea, -22, SpringLayout.SOUTH, frame.getContentPane());
//		springLayout.putConstraint(SpringLayout.EAST, textArea, -20, SpringLayout.EAST, frame.getContentPane());
//	//	springLayout.putConstraint(SpringLayout.NORTH, textArea, 0, SpringLayout.NORTH, frame.getContentPane());
//		
//		frame.getContentPane().add(textArea);
//		
//		JButton btnNewButton = new JButton("New button");
//		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 0, SpringLayout.WEST, lblNewLabel);
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				try {
//					btn_click();
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (InstantiationException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (UnsupportedLookAndFeelException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				   String name=txtWatcheskof.getText();
//				     String imgPostion=txtimagesallwatchesttwimagemedium.getText();
//				     String tmp=" ln -s   @img  /home_src/@wbst0/public_html/images/";
//				     tmp=tmp.replaceAll("@img", imgPostion).replaceAll("@wbst", name);
//				     tmp=tmp+"small"+"\r\n"+tmp+"medium"+"\r\n"+tmp+"large";
//				     textArea.setText(tmp);
//			}
//		});
//		frame.getContentPane().add(btnNewButton);
//	
//		
//		JLabel label = new JLabel("websiteName");
//		springLayout.putConstraint(SpringLayout.NORTH, label, 24, SpringLayout.NORTH, frame.getContentPane());
//		springLayout.putConstraint(SpringLayout.WEST, label, 27, SpringLayout.WEST, frame.getContentPane());
//		springLayout.putConstraint(SpringLayout.EAST, label, 103, SpringLayout.WEST, frame.getContentPane());
//		frame.getContentPane().add(label);
//		
//		txtimagesallwatchesttwimagemedium = new JTextField();
//		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 15, SpringLayout.SOUTH, txtimagesallwatchesttwimagemedium);
//		springLayout.putConstraint(SpringLayout.NORTH, txtimagesallwatchesttwimagemedium, 51, SpringLayout.NORTH, frame.getContentPane());
//		springLayout.putConstraint(SpringLayout.WEST, txtimagesallwatchesttwimagemedium, 113, SpringLayout.WEST, frame.getContentPane());
//		springLayout.putConstraint(SpringLayout.EAST, txtimagesallwatchesttwimagemedium, 566, SpringLayout.WEST, frame.getContentPane());
//		txtimagesallwatchesttwimagemedium.setText("/images_all/watches_ttw888/image_medium");
//		txtimagesallwatchesttwimagemedium.setColumns(10);
//		frame.getContentPane().add(txtimagesallwatchesttwimagemedium);
//		
//		  comboBox = new JComboBox();
//		  springLayout.putConstraint(SpringLayout.WEST, comboBox, 382, SpringLayout.WEST, frame.getContentPane());
//		  springLayout.putConstraint(SpringLayout.EAST, comboBox, -30, SpringLayout.EAST, frame.getContentPane());
//		  comboBox.addItemListener(new ItemListener() {
//		  	public void itemStateChanged(ItemEvent arg0) {
//		  		skinCombox_change_event(arg0);
//		  	}
//		  });
////		  comboBox.addActionListener(new ActionListener() {
////		  	public void actionPerformed(ActionEvent arg0) {
////		  		skinCombox_change_event(arg0);
////		  	}
////		  });
//		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 1, SpringLayout.NORTH, btnNewButton);
//		frame.getContentPane().add(comboBox);
//		
//		JLabel lblSkin = new JLabel("skin");
//		springLayout.putConstraint(SpringLayout.NORTH, lblSkin, 4, SpringLayout.NORTH, btnNewButton);
//		springLayout.putConstraint(SpringLayout.EAST, lblSkin, -17, SpringLayout.WEST, comboBox);
//		frame.getContentPane().add(lblSkin);
//		
//	
//		springLayout.putConstraint(SpringLayout.WEST, menuBar, 1, SpringLayout.WEST, frame.getContentPane());
//		frame.getContentPane().add(menuBar);
//		
//		MenuX mx=new MenuX();
//		mx.menubar=this.menuBar;
//		try {
//			mx.geneMenu(new Closure2<String,Object> (){
//
//				@Override
//				public Object execute(String arg0) {
//					// TODO Auto-generated method stub
//					return null;
//				}});
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////		try {
////			mx.geneMenu( new Closure2<String,Object> () {
////
////				@Override
////				public Object execute(String fld) {
////					JMenu mnFile = new JMenu(fld);
////					menuBar.add(mnFile);
//////					JMenuItem mntmOpen = new JMenuItem("open");
//////					mnFile.add(mntmOpen);
////					return null;
////				}
////			});
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//	//	JMenu mnFile = new JMenu("File");
//	
//		
//		
// 
//		popupMenu = new JPopupMenu();
//	
//		addPopup(frame, popupMenu);
//		
//		JMenuItem mntmNewMenuItem_1 = new JMenuItem("About Attilax");
//		popupMenu.add(mntmNewMenuItem_1);
//		
//		JMenu mnNewMenu = new JMenu("New menu");
//		popupMenu.add(mnNewMenu);
//		
//		setSkinMenuitemInMenubar();
//		
//		frame.setDropTarget( new DropTarget(frame,  DnDConstants.ACTION_COPY_OR_MOVE, this, true) );
//		//     jlist.setDropTarget(new DropTarget(jlist,
//      // ));
//		new FloatWin();
//	}
//	JPopupMenu popupMenu  ;
//protected void skinCombox_change_event(ItemEvent e) {
//	  if(e.getStateChange() == ItemEvent.SELECTED){  
//	 String index= (String) this.comboBox.getSelectedItem();
//	 SkinX.setSkin(index,this.frame);
//	MsgBox.setTxt("ttt");
//	 }
//		
//	}
//
//@Deprecated
//	protected void btn_click() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
//	  //	SwingUtilities.updateComponentTreeUI(this);
//		
//		//  LiquidLookAndFeel.setLiquidDecorations(true );
//		  
//		  //change title 
//		//  frame.getContentPane().setw
//		//  frame.hide();//is outtime ,,will use setVisible  
//		//  frame.setVisible(false);
//		  
//		//frame.setDefaultLookAndFeelDecorated(arg0);
//		//  frame.fre
//		
//		//	SwingUtilities.updateComponentTreeUI(this);//apply  new ui
//
//		//	window.frame
//		//	SwingUtilities.upd
//			
//			
//		
//			
//		
//	     
//		
//	}
//	private static void addPopup(Component component, final JPopupMenu popup) {
//		component.addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				if (e.isPopupTrigger()) {
//					showMenu(e);
//				}
//			}
//			public void mouseReleased(MouseEvent e) {
//				if (e.isPopupTrigger()) {
//					showMenu(e);
//				}
//			}
//			private void showMenu(MouseEvent e) {
//				popup.show(e.getComponent(), e.getX(), e.getY());
//			}
//		});
//	}
//
// 
//
//	@Override
//	public void dragEnter(DropTargetDragEvent dtde) {
//		System.out.println("=====enter drag enter ");
//
//		// Get the type of object being transferred and determine
//		// whether it is appropriate.
//		// checkTransferType(dtde);
//		// Only accept a list of files
//		boolean acceptableType = dtde
//				.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
//
//		// Accept or reject the drag.
//		// acceptOrRejectDrag(dtde);
//		int dropAction = dtde.getDropAction();
//		int sourceActions = dtde.getSourceActions();
//		boolean acceptedDrag = false;
//
//		// Reject if the object being transferred
//		// or the operations available are not acceptable.
//		if (!acceptableType
//				|| (sourceActions & DnDConstants.ACTION_COPY_OR_MOVE) == 0) {
//			// DnDUtils.debugPrintln("Drop target rejecting drag");
//			dtde.rejectDrag();
//		} else if ((dropAction & DnDConstants.ACTION_COPY_OR_MOVE) == 0) {
//			// Not offering copy or move - suggest a copy
//			// DnDUtils.debugPrintln("Drop target offering COPY");
//			dtde.acceptDrag(DnDConstants.ACTION_COPY);
//			acceptedDrag = true;
//		} else {
//			// Offering an acceptable operation: accept
//			// DnDUtils.debugPrintln("Drop target accepting drag");
//			dtde.acceptDrag(dropAction);
//			acceptedDrag = true;
//		}
//
//		// return acceptedDrag;
//
//	}
//
//	@Override
//	public void dragExit(DropTargetEvent dte) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void dragOver(DropTargetDragEvent dtde) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void drop(DropTargetDropEvent dtde) {
//		System.out.println("=====enter drag drop... ");
//		// Check the drop action
//		if ((dtde.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0) {
//			// Accept the drop and get the transfer data
//			dtde.acceptDrop(dtde.getDropAction());
//			Transferable transferable = dtde.getTransferable();
//			System.out.println("");
//			try {
//				List<File> fileList = (List) transferable
//						.getTransferData(DataFlavor.javaFileListFlavor);
//			//	fileList.getClass().toString();
//
//		//		String path = fileList[0].getPath();
//				String path=fileList.get(0).getPath();
//				GeneLink gl=new GeneLink();
//				gl.gene(path);
//				MsgBox.setTxt(path);
//				System.out.println(path);
//			} catch ( Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			// dtde.dropComplete(result);
//
//			// dtde.rejectDrop();
//		}
//
//	}
//
//	@Override
//	public void dropActionChanged(DropTargetDragEvent dtde) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setSkinMenuitemInMenubar() {
//		// TODO Auto-generated method stub
//		
//	}
//}
