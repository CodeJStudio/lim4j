package com.codejstudio.lim.pojo.relation;

import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.AbstractElement;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.i.IIntegratable;

/**
 * CausalityRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class CausalityRelation extends BaseRelation {

	/* constants */
	
	protected static final String CAUSE = "cause";
	
	protected static final String EFFECT = "effect";


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public CausalityRelation() throws LIMException {
		super();
	}

	public CausalityRelation(boolean ifInitId) throws LIMException {
		super(ifInitId, true);
	}

	public CausalityRelation(boolean ifInitId, AbstractRelationableInformationElement cause, AbstractRelationableInformationElement effect) throws LIMException {
		super(ifInitId, true);
		setCause(cause);
		setEffect(effect);
	}


	public CausalityRelation(AbstractRelationableInformationElement cause, AbstractRelationableInformationElement effect) throws LIMException {
		this(true, cause, effect);
	}


	/* getters & setters */

	public AbstractRelationableInformationElement getCause() {
		return super.primaryElement;
	}

	public void setCause(AbstractRelationableInformationElement cause) throws LIMException {
		super.setPrimaryElement(cause, false);
		if(cause != null) {
			super.putIntegratedElementDelegate(CAUSE, new BaseElement(cause));
		}
	}

	public AbstractRelationableInformationElement getEffect() {
		return super.secondaryElement;
	}

	public void setEffect(AbstractRelationableInformationElement effect) throws LIMException {
		super.setSecondaryElement(effect, false);
		if(effect != null) {
			super.putIntegratedElementDelegate(EFFECT, new BaseElement(effect));
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

		BaseElement cause = map.get(CAUSE);
		if(cause != null && cause.getId() != null) {
			AbstractElement element = rootElementMap.get(cause.getId());
			this.primaryElement = (element instanceof AbstractRelationableInformationElement) 
					? (AbstractRelationableInformationElement) element : this.primaryElement;
		}
		BaseElement effect = map.get(EFFECT);
		if(effect != null && effect.getId() != null) {
			AbstractElement element = rootElementMap.get(effect.getId());
			this.secondaryElement = (element instanceof AbstractRelationableInformationElement) 
					? (AbstractRelationableInformationElement) element : this.secondaryElement;
		}
	}
	
}
