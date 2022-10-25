package com.codejstudio.lim.pojo.relation;

import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.AbstractElement;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.i.IIntegratable;

/**
 * PredicateMappingRelation.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class PredicateMappingRelation extends MappingRelation {

	/* constants */
	
	protected static final String PREDICATE = "predicate";


	/* variables */
	
	protected Concept predicate;
	

	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public PredicateMappingRelation() throws LIMException {
		super();
	}

	public PredicateMappingRelation(boolean ifInitId) throws LIMException {
		super(ifInitId);
	}

	public PredicateMappingRelation(boolean ifInitId, AbstractRelationableInformationElement primaryElement, AbstractRelationableInformationElement secondaryElement, Concept predicate) throws LIMException {
		super(ifInitId, primaryElement, secondaryElement);
		if(predicate != null) {
			super.addInnerElementDelegate(predicate);
			super.putIntegratedElementDelegate(PREDICATE, new BaseElement(predicate));
		}
	}


	public PredicateMappingRelation(AbstractRelationableInformationElement primaryElement, AbstractRelationableInformationElement secondaryElement, Concept predicate) throws LIMException {
		this(true, primaryElement, secondaryElement, predicate);
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

		BaseElement predicate = map.get(PREDICATE);
		if(predicate != null && predicate.getId() != null) {
			AbstractElement element = rootElementMap.get(predicate.getId());
			this.predicate = (element instanceof Concept) 
					? (Concept) element : this.predicate;
		}
	}


	@Override
	public PredicateMappingRelation cloneElement() throws LIMException {
		PredicateMappingRelation cloneElement = (PredicateMappingRelation) super.cloneElement();
		cloneElement.predicate = (this.predicate != null) 
				? (Concept) this.predicate.cloneElement() : cloneElement.predicate;
		return cloneElement;
	}

}
