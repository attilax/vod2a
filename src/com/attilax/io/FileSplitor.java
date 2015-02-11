package com.attilax.io;

import java.io.IOException;

import com.attilax.Closure;

public class FileSplitor {
	static  Integer counter=0;
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stubart
		final int start = 252686;
		String s = "D:\\website\\watchestank\\public_html\\watchestank_0422.sql";
		final filex c=new filex("c:\\sql2.sql");
		filex.read_HP(s, "utf-8", new Closure() {

			@Override
			public Object execute(Object arg0) throws Exception {
				// TODO Auto-generated method stub
				if(counter<100)
				System.out.println(arg0.toString());
			
				counter++;
				if(counter>=start)
					c.append_HP(arg0.toString()+"\r\n");
				return null;
			}
		});
		c.close();
		System.out.println("==");
		

	}

}
