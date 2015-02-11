/**
 * 
 */
package com.attilax.media;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;

import com.attilax.core;
import com.attilax.io.filex;

/**
 * @author ASIMO
 *
 */
public class jmfT {

	/**
	@author attilax 老哇的爪子
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws NoPlayerException 
	 * @throws CannotRealizeException 
	 * @throws InterruptedException 
	@since   oad f_43_8
	 
	 */
	public static void main(String[] args) throws NoPlayerException, MalformedURLException, IOException, CannotRealizeException, InterruptedException {
		    Player player;
		    //通过调用Manager的createPlayer方法来创建一个Player的对象
            //这个对象是媒体播放的核心控制对象
		    filex.file2url("c:\\aa");
            String urlfile = "file:/C:/share/CIMG1604.MOV";
            urlfile="file:/C:/workspace/vodx/WebRoot/uploadf/0916_225231_461.mp4";
           
		 	player = Manager.createPlayer(new URL(urlfile));
		//	player = Manager.createRealizedPlayer(new URL(urlfile));
		 	 player.start();  
	            Thread.sleep(500); 
	            double seconds = player.getDuration().getSeconds(); 
       // 
            
          //   filex.save_SF(core.toJsonStrO88(player), "c:\\pl.txt");
           
          System.out.println(seconds);  
          System.out.println("--");
            		//    url = new URL("file:/E:/c-d.avi");

	}

}
