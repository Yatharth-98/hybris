/**
 *
 */
package com.mystore.core.cron.jobs;

import java.io.IOException;
import java.util.Date;


/**
 * @author 91963
 *
 */
public class main
{

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(final String[] args) throws IOException
	{
		// XXX Auto-generated method stub
		String input = "[\"user1\",\"track1\",\"player1\", \"user1\",\"track2\",\"player2\", \"user1\",\"track3\",\"player3\"]";
		input = input.substring(1, input.length() - 1); // get rid of brackets

		final String todayDate = new Date().toLocaleString();
		System.out.println(todayDate);

		final String csvFileName = "/" + todayDate.replaceAll("[^A-Za-z0-9]", "") + ".csv";
		System.out.println(csvFileName);

	}

}
