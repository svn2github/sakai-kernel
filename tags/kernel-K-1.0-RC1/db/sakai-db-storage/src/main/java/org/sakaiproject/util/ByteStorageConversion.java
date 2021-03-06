/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2003, 2004, 2005, 2006, 2007 The Sakai Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/

package org.sakaiproject.util;

/**
 * Bytes are signed in Java, if we store them in a char we will consume the top
 * 128 characters of a char range, rather than do that we are going to unsign a
 * byte before storing it in a char, that way the range 0-127 is still readable,
 * and the range 128-255 does not cosume masses of memory.
 * 
 * @author ieb
 */
public class ByteStorageConversion
{
	/**
	 * convert the signed byte array b into char starting at bstart for length
	 * bytes into c starting at cstart
	 * 
	 * @param b
	 *        the byte array to convert
	 * @param bstart
	 *        the starting position in the byte array
	 * @param c
	 *        the destination char array
	 * @param cstart
	 *        the staring possition in the char array
	 * @param length
	 *        the number of bytes to convert
	 */
	public static void toChar(byte[] b, int bstart, char[] c, int cstart, int length)
	{
		int bi = bstart;
		int ci = cstart;
		for (int i = 0; i < length; i++, bi++, ci++)
		{
			if (b[bi] >= 0)
			{
				c[ci] = (char) (b[bi]);
			}
			else
			{
				c[ci] = (char) (256 + b[bi]);
			}
		}
	}

	/**
	 * convert the char array into signed byte starting at cstart for length
	 * chars into b starting at bstart
	 * 
	 * @param c
	 *        the char array to convert
	 * @param cstart
	 *        the staring possition in th char array
	 * @param b
	 *        the destination byte array
	 * @param bstart
	 *        the starting possition in the destination byte array
	 * @param length
	 *        the number of chars to convert
	 */
	public static void toByte(char[] c, int cstart, byte[] b, int bstart, int length)
	{
		int bi = bstart;
		int ci = cstart;
		for (int i = 0; i < length; i++, bi++, ci++)
		{
			if (c[ci] > 127)
			{
				b[bi] = (byte) (c[ci] - 256);
			}
			else
			{
				b[bi] = (byte) (c[ci]);
			}
		}
	}

}
