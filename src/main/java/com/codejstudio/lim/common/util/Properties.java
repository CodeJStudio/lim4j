package com.codejstudio.lim.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.InvalidPropertiesFormatException;
import java.util.Set;

/**
 * <code>Properties</code> is rewritten for "<code>java.util.Properties</code>" 
 * to extend Hashtable&lt;String, String&gt;, instead of Hashtable&lt;Object,Object&gt;.
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class Properties extends Hashtable<String, String> {

	/* constants */
	
	private static final long serialVersionUID = 2081406128281026566L;


	/* variables */
	
	protected java.util.Properties defaults;

	
	/* constructors */
	
	public Properties() {
		this(new java.util.Properties());
	}

	public Properties(java.util.Properties defaults) {
		this.defaults = defaults;
	}


	/* getters & setters */

	public String getProperty(String key) {
		return defaults.getProperty(key);
	}

	public String getProperty(String key, String defaultValue) {
		return defaults.getProperty(key, defaultValue);
	}

	public synchronized Object setProperty(String key, String value) {
		return defaults.setProperty(key, value);
	}


	/* rewritten methods */

	public Enumeration<?> propertyNames() {
		return defaults.propertyNames();
	}

	public Set<String> stringPropertyNames() {
		return defaults.stringPropertyNames();
	}
	
    public void list(PrintStream out) {
    	defaults.list(out);
    }

	public void list(PrintWriter out) {
    	defaults.list(out);
    }

	public synchronized void load(InputStream inStream) throws IOException {
    	defaults.load(inStream);
	}

	public synchronized void load(Reader reader) throws IOException {
    	defaults.load(reader);
	}

	public void store(OutputStream out, String comments) throws IOException {
		defaults.store(out, comments);
	}

	public void store(Writer writer, String comments) throws IOException {
		defaults.store(writer, comments);
	}

	public synchronized void loadFromXML(InputStream in) throws IOException, InvalidPropertiesFormatException {
		defaults.loadFromXML(in);
	}

	public void storeToXML(OutputStream os, String comment) throws IOException {
		defaults.storeToXML(os, comment);
	}

	public void storeToXML(OutputStream os, String comment, String encoding) throws IOException {
		defaults.storeToXML(os, comment, encoding);
	}

}
