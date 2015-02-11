package com.attilax.db;

import java.io.IOException;
import org.apache.commons.collections.Closure;
import com.attilax.core;
import com.attilax.io.filex;
import com.attilax.util.numUtil;

public class ix {
	
	public static void main(String[] args) {
		final String f = "C:\\num.txt";
		new ix(1000).times(new Closure() {

			@Override public void execute(Object arg0) {
				// attilax 老哇的爪子 0_h_x o8o
				int n = Integer.parseInt(arg0.toString());
				String ns = "03"+numUtil.pad0(n, 6);
				try {
					filex.appendLine(f, ns);
				} catch (IOException e) {
					// attilax 老哇的爪子 0_42_j o8o
					e.printStackTrace();
				}
			}
		});
		System.out.println("--f");
	}

	private int max;

	public ix(int i) {
		this.max=i;
	}

	public String times(Closure closure) {
		
		for(int i=0;i<max;i++)
		{
			try {
				closure.execute(i);
			} catch (Exception e) {
				core.log(e);
			}
			
		}
		return "";
		 
		
	}

}
