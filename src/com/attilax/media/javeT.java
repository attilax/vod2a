/**
 * 
 */
package com.attilax.media;

import java.io.File;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.MultimediaInfo;

import com.attilax.core;

/**
 * @author ASIMO
 *
 */
public class javeT {

	/**
	@author attilax 老哇的爪子
	 * @throws EncoderException 
	 * @throws InputFormatException 
	@since   obp e_i_x
	 
	 */
	public static void main(String[] args) throws InputFormatException, EncoderException {
		//m = encoder.getInfo(fil);
		Encoder encoder = new Encoder();
		MultimediaInfo m;
   //   ClassNotFoundException
			File fil = new File( "aa");
			m = encoder.getInfo(fil);
		 
 

	}

}
