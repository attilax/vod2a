//package com.attilax.skin;
//
//import java.awt.Component;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.Set;
//import java.util.Vector;
//
//import javax.swing.JComboBox;
//import javax.swing.JDialog;
//import javax.swing.JFrame;
//import javax.swing.JRootPane;
//import javax.swing.LookAndFeel;
//import javax.swing.SwingUtilities;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
//
//import org.pushingpixels.substance.api.skin.SubstanceCremeLookAndFeel;
//import org.pushingpixels.substance.api.skin.SubstanceMistAquaLookAndFeel;
//import org.pushingpixels.substance.api.skin.SubstanceSaharaLookAndFeel;
//
//import com.birosoft.liquid.LiquidLookAndFeel;
//
//public class SkinX {
//	// public SkinX() {
//	// super();
//	// // TODO Auto-generated constructor stub
//	// }
//	private static void setUndecoratedTitlebar(JFrame frame, boolean b) {
//		// -----------refresh titlebar
//  //  if("1".equals("1"))return;
//		try {
//			frame.hide();
//			frame.setVisible(false);
//		 	frame.dispose();
//		 
//			frame.setUndecorated(b);
// 
//
//			frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);// set
// 
//			Notitlebar = true;
//			
//		 	frame.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	
//
//	}
//
//	public static Map map;
//	private static Map map2=new HashMap();
//	static {
//		map = new LinkedHashMap() {
//			{
//				this.put("windows", new IS‌kinInir() {
//
//					@Override
//					public void ini(JFrame frame) {
//						 
//						setLookAndFeel(skinName_class.windows);
//						setUndecoratedTitlebar(frame, false);
//
//					}
//
//				});
//				
//				this.put("Substance.Creme..", new IS‌kinInir() {
//
//					@Override
//					public void ini(JFrame frame) {
//						 
//						setLookAndFeel(new SubstanceCremeLookAndFeel());
//						setUndecoratedTitlebar(frame, true);
//
//					}
//
//				});
//				
//
//				this.put("Substance.sahara..", new IS‌kinInir() {
//
//					@Override
//					public void ini(JFrame frame) {
//						 
//						setLookAndFeel(new SubstanceSaharaLookAndFeel());
//						setUndecoratedTitlebar(frame, true);
//
//					}
//
//				});
//				
//				this.put("Substance.mistAqua..", new IS‌kinInir() {
//
//					@Override
//					public void ini(JFrame frame) {
//					 
//						setLookAndFeel(new SubstanceMistAquaLookAndFeel());
//						setUndecoratedTitlebar(frame, true);
//
//					}
//
//				});
//				
//				
//			
//				
//			 
//				 
//				map2.put("windows", skinName_class.windows);
//				
//				// --指定为Windows的界面外观, 仅在Windows平台起作用.
//				this.put("mac.Liquid", new IS‌kinInir() {
//
//					@Override
//					public void ini(JFrame frame) {
//						 
//						setLookAndFeel( skinName_class.mac_Liquid);
//						LiquidLookAndFeel.setLiquidDecorations(true);
//						setUndecoratedTitlebar(frame, true);
//					}
//
//				});
//				map2.put("mac.Liquid", skinName_class.mac_Liquid);
//				 
//				
//				
//				
//				// UIManager.get
//				this.put("java", new IS‌kinInir() {
//
//					@Override
//					public void ini(JFrame frame) {
//
//						setLookAndFeel(UIManager
//								.getCrossPlatformLookAndFeelClassName());
//						setUndecoratedTitlebar(frame, true);
//					}
//				});
//				
//				map2.put("java", UIManager
//						.getCrossPlatformLookAndFeelClassName());
//				// );
//				// "javax.swing.plaf.metal.MetalLookAndFeel"
//				// --指定为Java的界面外观,
//				// 也就是方法UIManager.getCrossPlatformLookAndFeelClassName()的返回值.
//				this.put("sys", new IS‌kinInir() {
//
//					@Override
//					public void ini(JFrame frame) {
//
//						setLookAndFeel(UIManager
//								.getSystemLookAndFeelClassName());
//						setUndecoratedTitlebar(frame, false);
//					}
//				});
//				map2.put("sys",UIManager
//								.getSystemLookAndFeelClassName());
//				// );
//				// UIManager.getSystemLookAndFeelClassName()
//				// --指定为当前平台的界面外观.在32位Windows平台, 为Windows界面外观; 在Mac OS平台, 为Mac
//				// OS界面外观; 在Sun平台, 为CDE/Motif界面外观.
//				// LiquidLookAndFeel.setLiquidDecorations(true ); } );
//				this.put("CDE/Motif", new IS‌kinInir() {
//
//					@Override
//					public void ini(JFrame frame) {
//
//						setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//						setUndecoratedTitlebar(frame, false);
//					}
//				});
//			 
//			 
// 
//				
//				putX(this,new String[]{"Substance.MistSilver,org.jvnet.substance.skin.SubstanceMistSilverLookAndFeel",
//						"Substance.NebulaBrickWall,org.jvnet.substance.skin.SubstanceNebulaBrickWallLookAndFeel",
//						"jtattoo.acryl,  com.jtattoo.plaf.acryl.AcrylLookAndFeel",
//						"sun.nimbus,com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel",	
//						"substance.BusinessBlueSteel,org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel",
//						"Substance.Creme,org.jvnet.substance.skin.SubstanceCremeLookAndFeel"},true);
//				
//				
//				
//			
//				// );
//
//				// --指定为CDE/Motif的界面外观, 可以在所有平台起作用.
//				// jeig ui window hamyar k neke l ...
//
//				this.put("mac(only in macOS)",
//						"javax.swing.plaf.mac.MacLookAndFeel");
//
//				// --指定为Mac OS的界面外观, 仅在Mac OS平台起作用.
//
//				// "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"
//				// com.birosoft.liquid.LiquidLookAndFeel
//			}
//		};
//	}
//
//	/**
//	 * BasicLookAndFeel
//	 * @param skinClass
//	 */
//	private static void setLookAndFeel(Object skinClass) {
//		
//		try {
//			if(skinClass instanceof String)
//			{
//				skinClass=skinClass.toString().trim();
//			UIManager.setLookAndFeel(skinClass.toString());
//			}else
//				UIManager.setLookAndFeel((LookAndFeel) skinClass);
//				
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			throw new RuntimeException("ClassNotFoundException" + skinClass);
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//			throw new RuntimeException("InstantiationException");
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//			throw new RuntimeException("IllegalAccessException");
//
//		} catch (UnsupportedLookAndFeelException e) {
//			e.printStackTrace();
//			throw new RuntimeException("UnsupportedLookAndFeelException");
//
//		}
//	}
//
//	protected static void putX(Map mp, String[] strings, final boolean b) {
//	     for (String skiname : strings) {
//	    	 String[] a=skiname.split(",");
//	    	 String name=a[0].trim();
//	    	 final String cls=a[1].trim();
//	    	 mp.put(name, new IS‌kinInir() {
//
//					@Override
//					public void ini(JFrame frame) {
//
//						setLookAndFeel(cls);
//						setUndecoratedTitlebar(frame, b);
//					}
//				});
//	    	 map2.put(name, cls);
//		}
//		
//	}
//
////	protected static void putX(Map mp,   String[] strings) {
////		 
////		
////	}
//
//	public static void main(String[] args) {
//
//	}
//
////	@SuppressWarnings("rawtypes")
////	public static Vector skinNames() {
////
////		return (Vector) map.entrySet();
////	}
//
//	public static void setComboBoxVal(JComboBox comboBox) {
//		// TODO Auto-generated method stub
//		Set set = map.keySet();
//		Iterator<String> it = set.iterator();
//		while (it.hasNext()) {
//			String str = it.next();
//			comboBox.addItem(str);
//		}
//	}
//
//	public static void setSkin(String index, JFrame frame) {
////		switchLnF( SkinX.map2.get(index).toString(),frame);
//	//	if("1".equals("1"))return;
//	 
//	//	org.jvnet.substance.skin.SubstanceMistSilverLookAndFeel
//		try {
//			Object item = SkinX.map.get(index);
//		
//
//			// 改变标题栏 title bar and border JFrame JDialog
//		 //	JFrame.setDefaultLookAndFeelDecorated(true);
//		 //	JDialog.setDefaultLookAndFeelDecorated(true);
//
//			if (item instanceof IS‌kinInir) {
//				((IS‌kinInir) item).ini(frame);
//			} else {
//				setLookAndFeel(item.toString());
//			}
//			if (Notitlebar) {
//			}
//
//		 
//			SwingUtilities.updateComponentTreeUI(frame);// apply new ui
//			// frame.pack(); jeig bnen use ,beirs main win gchw le
//			// .重置每个顶层容器的大小来适应它所包含的组件的新大小
//		} catch (Exception e) {
//		//	e.printStackTrace();
//		}
//		// org.jvnet.substance.utils.SubstanceTitlePane
//		/// org.jvnet.substance.utils.SubstanceTitlePane.
//		
//
//	}
//	
//	
//	private static void switchLnF(String LnF,JFrame frame){	
//		try {
//			JFrame.setDefaultLookAndFeelDecorated(true);
//			UIManager.setLookAndFeel(LnF);
//			SwingUtilities.updateComponentTreeUI(frame);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.err.println("Cannot install " + LnF
//					+ " on this platform:" + e.getMessage());
//		}
//	}
//
//	static boolean Notitlebar = false;
//
//}
