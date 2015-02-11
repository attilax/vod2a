//package m.secury;
//
//
//import org.apache.log4j.Logger;
//
//import com.attilax.util.DbNdsController;
//import com.attilax.util.Dbcontroller;
//
//public class tConnBreak {
//	public static Logger logger = Logger.getLogger("ttt");
//
//	public static Dbcontroller dbc=new DbNdsController();
//	/**
//	 * @param args
//	 */
//
//	public static void main(String[] args) {
//
//		// .getClass());
//		securyWhile c = new securyWhile(new callbackItfs() {
//
//			@Override
//			public Object callMethod(Object obj) {
//			String id=	dbc.getIds("select * from mood order by id desc limit 1");
//				String r = " rid is " +id;
//				logger.info(r);
//				return r;
//			}
//		}, logger, 20000, 6000, 100);
//
//	}
//
//}
