package com.codejstudio.lim.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CaseFormatUtil.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class CaseFormatUtil {

	/* enumeration */
	
	public enum WordSeparator{
		HYPHEN('-'),
		UNDERSCORE('_'),
		;
		
		private char separator;

		WordSeparator(char separator) {
			this.separator = separator;
		}

		public char getSeparator() {
			return separator;
		}
		
	}
	

	/* static methods */

	/**
	* "lowerCamel", "UpperCamel" -> "lower_underscore", "lower-hyphen", "UPPER_UNDERSCORE", "UPPER-HYPHEN"
	*/
	public static String camelToSeparated(String s, WordSeparator separator, boolean isUpperOutput) {
		Pattern pattern = Pattern.compile("[A-Z0-9]");
		Matcher matcher = pattern.matcher("" + Character.toLowerCase(s.charAt(0)) + s.substring(1));
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, separator.separator + matcher.group(0));
		}
		matcher.appendTail(sb);
		return isUpperOutput ? sb.toString().toUpperCase() : sb.toString().toLowerCase();
	}
	
	
	/**
	* "lower_underscore", "lower-hyphen", "UPPER_UNDERSCORE", "UPPER-HYPHEN" -> "lowerCamel", "UpperCamel"
	*/
	public static String separatedToCamel(String s, WordSeparator separator, boolean isUpperOutput) {
		Pattern linePattern = Pattern.compile("" + separator.separator + "(\\w)");
		Matcher matcher = linePattern.matcher(s.toLowerCase());
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		if(isUpperOutput) {
			return "" + Character.toUpperCase(sb.charAt(0)) + sb.substring(1);
		}else {
			return sb.toString();
		}
	}
	
	/**
	* "lower_underscore", "lower-hyphen", "UPPER_UNDERSCORE", "UPPER-HYPHEN" -> "lowerCamel", "UpperCamel"
	*/
	@Deprecated
	public static String separatedToCamel0(String s, WordSeparator separator, boolean isUpperOutput) {
		StringBuilder sb = new StringBuilder(s.length());
		if(s.length() > 0) {
			char c = isUpperOutput ? Character.toUpperCase(s.charAt(0)) : Character.toLowerCase(s.charAt(0));
			sb.append(c);
		}
		for (int i = 1; i < s.length(); i++) {
			char c = Character.toLowerCase(s.charAt(i));
			if(c == separator.separator) {
				if(++i < s.length()) {
					sb.append(Character.toUpperCase(s.charAt(i)));
				}
			}else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
}
