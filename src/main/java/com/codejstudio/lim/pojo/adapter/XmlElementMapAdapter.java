package com.codejstudio.lim.pojo.adapter;

import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.adapter.XmlElementMapAdapter.XmlElementEntry;

/**
 * XmlElementMapAdapter.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     javax.xml.bind.annotation.adapters.XmlAdapter
 * @since   lim4j_v1.0.0
 */
public class XmlElementMapAdapter extends XmlAdapter<XmlElementEntry[], Map<String, BaseElement>> {

	/* overridden methods */

	@Override
	public Map<String, BaseElement> unmarshal(XmlElementEntry[] entries) throws Exception {
		if(CollectionUtil.checkNullOrEmpty(entries)) {
			return null;
		}

		Map<String, BaseElement> map = CollectionUtil.getNewMap();
		for (XmlElementEntry entry : entries) {
			map.put(entry.key, new BaseElement(entry.id, entry.type));
		}
		return map;
	}

	@Override
	public XmlElementEntry[] marshal(Map<String, BaseElement> map) throws Exception {
		if(CollectionUtil.checkNullOrEmpty(map)) {
			return null;
		}

		int i = 0;
		XmlElementEntry[] entries = new XmlElementEntry[map.size()];
		for (Map.Entry<String, BaseElement> mapEntry : map.entrySet()) {
			XmlElementEntry entry = new XmlElementEntry(mapEntry.getKey(), mapEntry.getValue());
			entries[i++] = entry;
		}
		return entries;
	}

	
	
	public static class XmlElementEntry {

		/* variables */
		
	    @XmlAttribute
		private String key;
		
	    @XmlAttribute
	    private String id;
		
	    @XmlAttribute
	    private String type;

		
		/* constructors */
		
		public XmlElementEntry() {
			super();
		}

		public XmlElementEntry(String key, BaseElement element) throws LIMException {
			this.key = key;
			this.id = element.getId();
			this.type = element.getType();
		}
	}
}