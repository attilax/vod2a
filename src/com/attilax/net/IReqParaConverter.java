/**
 * 
 */
package com.attilax.net;

import java.util.Map;

/**
 * @author ASIMO
 *
 */
public interface   IReqParaConverter<atiInput,atiOutput> {
	
	public   atiOutput convert(atiInput map);

}
