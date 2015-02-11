package com.attilax.win;

import java.util.Iterator;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import net.sf.json.JSONObject;

import com.attilax.Closure2;
import com.attilax.io.filex;
import com.attilax.json.JsonUtil4jackjson;

public class MenuX {

	public static void main(String[] args) throws Exception {
		// geneMenu();
		// JsonNode jn2=rootNode.path(1);
		// Iterator<JsonNode> ite = rootNode.getElements();
		//
		//
		// while (ite.hasNext()) {
		// JsonNode temp = ite.next();
		// // temp.get
		// System.out.println();
		// // System.out.println(temp.getTextValue());
		//
		// }
		// //jn.forEach(action);
		// rootNode.size();//==8
		// JsonNode n2=rootNode.get(1);
		// // jo.
		// System.out.println(txt);
		// System.out.println(MenuX.class.getResource(".").getPath());
		MenuX mx = new MenuX();
		String menuf = MenuX.class.getResource(".").getPath() + "menu.json";
		String txt = filex.read(menuf);
		// JSONObject jo=JSONObject.fromObject(txt); cant solu key order ..cause
		// inner sh hashmap
		JsonNode rootNode = JsonUtil4jackjson.buildNormalBinder()
				.toJsonFromStr(txt);
		Iterator<String> flds_Ittr = rootNode.getFieldNames();
		while (flds_Ittr.hasNext()) {
			String fld = flds_Ittr.next();
			if (mx.isMenuNode(fld, rootNode))
				System.out.println(fld);
			else
				System.out.println("noNode:" + fld);
		}

	}

	public JMenuBar menubar;

	public void geneMenu(Closure2 clsr) throws Exception {
		String menuf = MenuX.class.getResource(".").getPath() + "menu.json";
		String txt = filex.read(menuf);
		// JSONObject jo=JSONObject.fromObject(txt); cant solu key order ..cause
		// inner sh hashmap
		JsonNode rootNode = JsonUtil4jackjson.buildNormalBinder()
				.toJsonFromStr(txt);
		Iterator<String> flds_Ittr = rootNode.getFieldNames();
		while (flds_Ittr.hasNext()) {
			String fld = flds_Ittr.next();
			if (isMenuNode(fld, rootNode)) {
				JMenu menu = (JMenu) geneMenu(rootNode.get(fld), fld);
				menubar.add(menu);
			} else {
				JMenuItem mntmOpen = new JMenuItem(fld);
				// mntmOpen.set
				menubar.add(mntmOpen);
			}
			// return mntmOpen;

			// System.out.println(fld);

		}

		// menubar.add(menu);
		// JMenuItem mntmOpen = new JMenuItem("ttt");
		// menubar.add(mntmOpen);
		// geneMenu( node curMenu);
		//

	}

	private boolean isMenuNode(String fld, JsonNode rootNode) {
		@SuppressWarnings("unused")
		JsonNode jo = rootNode.get(fld); // normal sh textNode
		if (jo instanceof ObjectNode)
			return true;
		return false;
	}

	public JComponent geneMenu(JsonNode jsonNode, String menuName) {
		JMenu menu = new JMenu(menuName);
		Iterator<String> flds_Ittr = jsonNode.getFieldNames();
		while (flds_Ittr.hasNext()) {
			String fld = flds_Ittr.next();
			if (isMenuNode(fld, jsonNode)) {
				JMenu menu2 = (JMenu) geneMenu(jsonNode.get(fld), fld);
				menu.add(menu2);
				continue;
			}

			if (fld.contains("---")) {
			//	jmenus
				JSeparator separator = new JSeparator();
				menu.add(separator);
				continue;
			}
			JMenuItem mntmOpen = new JMenuItem(fld);
			// mntmOpen.set
			menu.add(mntmOpen);
		}

		return menu;
	}

}
