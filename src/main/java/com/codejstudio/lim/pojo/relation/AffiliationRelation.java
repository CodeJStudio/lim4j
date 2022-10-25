package com.codejstudio.lim.pojo.relation;

import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.AbstractElement;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.attribute.Attribute;
import com.codejstudio.lim.pojo.i.IIntegratable;

/**
 * AffiliationRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class AffiliationRelation extends BaseRelation {

	/* constants */
	
	protected static final String MASTER = "master";
	
	protected static final String SLAVE = "slave";

	
	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public AffiliationRelation() throws LIMException {
		super();
	}

	public AffiliationRelation(boolean ifInitId) throws LIMException {
		super(ifInitId, true);
	}

	public AffiliationRelation(boolean ifInitId, AbstractRelationableInformationElement master, Attribute slave) throws LIMException {
		super(ifInitId, true);
		setMaster(master);
		setSlave(slave);
	}


	public AffiliationRelation(AbstractRelationableInformationElement master, Attribute slave) throws LIMException {
		this(true, master, slave);
	}


	/* getters & setters */

	public AbstractRelationableInformationElement getMaster() {
		return super.primaryElement;
	}

	public void setMaster(AbstractRelationableInformationElement master) throws LIMException {
		super.setPrimaryElement(master, false);
		if(master != null) {
			super.putIntegratedElementDelegate(MASTER, new BaseElement(master));
		}
	}

	public AbstractRelationableInformationElement getSlave() {
		return super.secondaryElement;
	}

	public void setSlave(AbstractRelationableInformationElement slave) throws LIMException {
		super.setSecondaryElement(slave, false);
		if(slave != null) {
			super.putIntegratedElementDelegate(SLAVE, new BaseElement(slave));
		}
	}

	
	/* overridden methods */

	@Override
	public IIntegratable reload(IIntegratable element, Map<String, AbstractElement> rootElementMap) throws LIMException {
		if(super.reload(element, rootElementMap) != null) {
			reloadFromRootElementMap(rootElementMap);
			return (IIntegratable) this;
		} else {
			return null;
		}
	}

	private void reloadFromRootElementMap(Map<String, AbstractElement> rootElementMap) {
		Map<String, BaseElement> map = getIntegratedElement();
		if(CollectionUtil.checkNullOrEmpty(map) 
				|| CollectionUtil.checkNullOrEmpty(rootElementMap)) {
			return;
		}

		BaseElement master = map.get(MASTER);
		if(master != null && master.getId() != null) {
			AbstractElement element = rootElementMap.get(master.getId());
			this.primaryElement = (element instanceof AbstractRelationableInformationElement) 
					? (AbstractRelationableInformationElement) element : this.primaryElement;
		}
		BaseElement slave = map.get(SLAVE);
		if(slave != null && slave.getId() != null) {
			AbstractElement element = rootElementMap.get(slave.getId());
			this.secondaryElement = (element instanceof AbstractRelationableInformationElement) 
					? (AbstractRelationableInformationElement) element : this.secondaryElement;
		}
	}

}
