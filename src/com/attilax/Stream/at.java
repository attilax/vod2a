/**
 * @author attilax 老哇的爪子
	@since  o8d 0_52_f$
 */
package com.attilax.Stream;

import java.util.*;
import java.net.*;
import java.io.*;
import com.attilax.core;
//import org.josql.Query;
//import org.josql.QueryExecutionException;
//import org.josql.QueryParseException;
//import org.josql.QueryResults;
import static com.attilax.Stream.Linq.*;
import static com.attilax.Stream.reduce.*;

/**
 * @author attilax 老哇的爪子
 * @since o8d 0_52_f$
 */
public class at {

	/**
	 * @author attilax 老哇的爪子
	 * @since o8d 0_52_g
	 * 
	 * @param args
	 * @throws QueryParseException
	 * @throws QueryExecutionException
	 */
	public static void main(String[] args) { // throws QueryParseException,
												// QueryExecutionException {
		// attilax 老哇的爪子 0_52_g o8d
		List li = new ArrayList<Map>() {
			{
				this.add(new HashMap() {
					{
						put("url", "url1");
						put("user", "u1");
						put("len", 1);
					}

				});
				this.add(new HashMap() {
					{
						put("url", "url1");
						put("len", 9);
						put("user", "u2");
					}

				});
				this.add(new HashMap() {
					{
						put("url", "url2");
						put("len", 5);
						put("user", "u1");
					}

				});
			}
		};

		// Create a new Query.
		// Query q = new Query ();
		//
		// // Parse the SQL you are going to use.
		// q.parse
		// ("SELECT   url,@sumx FROM   java.util.Map GROUP BY url   EXECUTE ON GROUP_BY_RESULTS  sum(url)  sumx");
		//
		// // Execute the query.
		// QueryResults qr = q.execute (li);
		//
		// // Get the query results.
		// List res = qr.getResults ();

		List li2 = Linq
				.from(li)
				.groupBy("url","user" )
				.select("url",  count().as("countx"),
						avg("len").as("avgx"));
		core.print_wzFmt(li2);
		// Collectors.summingInt(p -> 1)));

		// System.out.println(map);
		// System.out.println(res.size());

	}
	// attilax 老哇的爪子 0_52_f o8d

}

// attilax 老哇的爪子