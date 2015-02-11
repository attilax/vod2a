/**
 * @author attilax 老哇的爪子
	@since  o8d j_55_8$
 */
package com.attilax.Stream;

import com.attilax.Closure;
import com.attilax.core;
import com.attilax.collection.CollectionUtils;
import com.attilax.collection.map;

import static com.attilax.core.*;

import java.util.*;
import java.util.Map.Entry;
import java.net.*;
import java.io.*;

/**
 * @author attilax 老哇的爪子
 * @since o8d j_55_8$
 */
@SuppressWarnings("all")
public class Linq {

	private List li;

	/**
	 * @author attilax 老哇的爪子
	 * @since o8d j_56_v$
	 * 
	 * @param li
	 */
	public Linq(List li) {
		// attilax 老哇的爪子 j_56_v o8d
		this.li = li;
	}

	/**
	 * @author attilax 老哇的爪子
	 * @since o8d j_55_43
	 * 
	 * @param li
	 * @return
	 */
	public static Linq of(List li) {
		// attilax 老哇的爪子 j_55_43 o8d
		return new Linq(li);

	}

	// attilax 老哇的爪子 j_55_8 o8d
	// List grpby_li=new ArrayList();
	Map<String, GroupTable> grpbyRztLi_MpFmt = new HashMap<String, GroupTable>();
	private String[] grpby_flds;

	/**
	 * @author attilax 老哇的爪子
	 * @since o8d l_39_39
	 * 
	 * @param string
	 * @return
	 */
	public Linq groupBy(final String... fld) {
		// attilax 老哇的爪子 l_39_39 o8d
		this.grpby_flds = fld;
		CollectionUtils.each(this.li, new Closure<Map, Object>() {

			@Override
			public Object execute(Map m) throws Exception {
				// attilax 老哇的爪子 m_4_41 o8d

				Val v = Mapx.$(m).get(fld);

				// if(createGrp(m.get(fld)))
				String key = v.toString();
				if (grpbyRztLi_MpFmt.get(key) == null)
					grpbyRztLi_MpFmt.put(key, new GroupTable());
				GroupTable gt = grpbyRztLi_MpFmt.get(key);
				gt.grpby_fld = fld;
				gt.key = v;
				gt.add(m);

				return null;

			}

		});
		return this;

	}

	/**
	 * @author attilax 老哇的爪子
	 * @since o8d l_40_48
	 * 
	 * @param li2
	 * @return
	 */
	public static Linq from(List li2) {
		// attilax 老哇的爪子 l_40_48 o8d
		return new Linq(li2);

	}

	reduceCalcRztLi reduceCalcRztLiIns1 = new reduceCalcRztLi();
	List calcResult = new ArrayList();
	String countOutputColname;
	List<String> filtOutputFlds = new ArrayList<String>();

	/**
	 * @author attilax 老哇的爪子
	 * @since o8d l_46_a
	 * 
	 * @param string
	 * @param as
	 * @return
	 * @throws Exception
	 */
	public List select(final Object... slktObj) {
		// attilax 老哇的爪子 l_46_a o8d
		// list union
		List lix = CollectionUtils.each_RE(grpbyRztLi_MpFmt,
				new Closure<Entry<Object, GroupTable>, Object>() {

					@Override
					public Object execute(Entry<Object, GroupTable> e)
							throws Exception {
						// attilax 老哇的爪子 0_e_47 o8e
						List<Map> singTab = e.getValue().li;
						List<reduceCalcRzt_singleExprs> li = new ArrayList<reduceCalcRzt_singleExprs>();
						for (Object o : slktObj) {
							if (o instanceof Iredus) {
								Iredus ci = (Iredus) o;

								reduceCalcRzt_singleExprs rzt_se = ci
										.exec(singTab);
								li.add(rzt_se);
								filtOutputFlds.add(ci.getOutputColname());
							} else {
								filtOutputFlds.add(o.toString());
							}
						}
						reduceCalcRecord rcr = redusUtil.leftjoin(li);
						rcr.map.putAll(singTab.get(0));
						return rcr.map;

					}
				});
//filt  fld to output 
		List li2 = CollectionUtils.each_NS(lix, new Closure<Map, Map>() {

			@Override
			public Map execute(Map arg0) throws Exception {
				// attilax 老哇的爪子 12:29:50 AM Aug 17, 2014

				final Map m = new HashMap();
				CollectionUtils.each_RE(arg0, new Closure<Entry, Object>() {

					@Override
					public Object execute(Entry e) throws Exception {
						// attilax 老哇的爪子 12:33:21 AM Aug 17, 2014

						String k = e.getKey().toString();
						if (filtOutputFlds.contains(k))
							m.put(k, e.getValue());
						return null;

					}

				});
				return m;

			}
		});
		return li2;
	 

	}

	/**
	 * @author attilax 老哇的爪子
	 * @param closure
	 * @since o8e 0_o_7
	 * 
	 * @param countOutputColname2
	 */
	private void calcFksRzt(String OutputColname2, Closure closure) {
		// attilax 老哇的爪子 0_o_7 o8e
		// grpby_fld
		calcResult = CollectionUtils.each_RE(grpbyRztLi_MpFmt, closure);

	}

		/**
		@author attilax 老哇的爪子
		@since   oaj i_u_e
		 
		 */
	public List orderby(final String fld_name, String asc) {
		 Collections.sort(this.li, new Comparator<Map> () {

			@Override
			public int compare(Map o1, Map o2) {
				 
				return  o2.get(fld_name).toString().compareTo(o1.get(fld_name).toString());
			}
		});
		   
		return li;
	}
}

// attilax 老哇的爪子