package com.codejstudio.lim.pojo.adapter;

import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.adapter.XmlAttributeMapAdapter.XmlAttributeEntry;

/**
 * XmlAttributeMapAdapter.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     javax.xml.bind.annotation.adapters.XmlAdapter
 * @since   lim4j_v1.0.0
 */
public class XmlAttributeMapAdapter extends XmlAdapter<XmlAttributeEntry[], Map<String, String>> {

	/* overridden methods */

	@Override
	public Map<String, String> unmarshal(XmlAttributeEntry[] entries) throws Exception {
		if(CollectionUtil.checkNullOrEmpty(entries)) {
			return null;
		}

		Map<String, String> map = CollectionUtil.getNewMap();
		for (XmlAttributeEntry entry : entries) {
			map.put(entry.key, entry.value);
		}
		return map;
	}

	@Override
	public XmlAttributeEntry[] marshal(Map<String, String> map) throws Exception {
		if(CollectionUtil.checkNullOrEmpty(map)) {
			return null;
		}

		int i = 0;
		XmlAttributeEntry[] entries = new XmlAttributeEntry[map.size()];
		for (Map.Entry<String, String> mapEntry : map.entrySet()) {
			XmlAttributeEntry entry = new XmlAttributeEntry(mapEntry.getKey(), mapEntry.getValue());
			entries[i++] = entry;
		}
		return entries;
	}

	
	
	public static class XmlAttributeEntry {

		/* variables */
		
	    @XmlAttribute
		private String key;
		
	    @XmlAttribute
		private String value;

		
		/* constructors */
		
		public XmlAttributeEntry() {
			super();
		}

		public XmlAttributeEntry(String key, String value) throws LIMException {
			this.key = key;
			this.value = value;
		}
	}
}