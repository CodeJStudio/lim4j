package com.codejstudio.lim.pojo.statement;

import java.util.Map;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.ObjectUtil;
import com.codejstudio.lim.pojo.AbstractElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.i.IIntegratable;

/**
 * Definition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class Definition extends JudgedStatement {

	/* constants */
	
	protected static final String DEFINIENDUM = "definiendum";
	
	protected static final String DEFINIENS = "definiens";
	
	protected static final String CONNECTIVE_OF_DEFINITION = "connective-of-definition";


	/* variables */

	protected Concept definiendum;

	protected Concept definiens;

	protected Concept connectiveOfDefinition;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Definition() throws LIMException {
		super();
	}

	public Definition(boolean ifInitId, boolean ifInitType) throws LIMException {
		super(ifInitId, ifInitType);
	}

	public Definition(boolean ifInitId, boolean ifInitType, String discription) throws LIMException {
		super(ifInitId, ifInitType, discription);
	}
	

	public Definition(String discription) throws LIMException {
		super(true, true, discription);
	}

	public Definition(Concept definiendum, Concept definiens, Concept connectiveOfDefinition) throws LIMException {
		super(true, true);
		setDefiniendum(definiendum);
		setDefiniens(definiens);
		setConnectiveOfDefinition(connectiveOfDefinition);
	}


	/* getters & setters */

	public Concept getDefiniendum() {
		return definiendum;
	}

	public void setDefiniendum(Concept definiendum) throws LIMException {
		if(ObjectUtil.checkEquals(this.definiendum, definiendum)) {
			return;
		}
		
		if(this.definiendum != null) {
			super.removeIntegratedElementDelegate(DEFINIENDUM);
			super.removeInnerElementDelegate(this.definiendum);
			this.definiendum = null;
		}
		if(definiendum != null) {
			this.definiendum = definiendum;
			super.addInnerElementDelegate(definiendum);
			super.putIntegratedElementDelegate(DEFINIENDUM, new BaseElement(definiendum));
		}
	}

	public Concept getDefiniens() {
		return definiens;
	}

	public void setDefiniens(Concept definiens) throws LIMException {
		if(ObjectUtil.checkEquals(this.definiens, definiens)) {
			return;
		}
		
		if(this.definiens != null) {
			super.removeIntegratedElementDelegate(DEFINIENS);
			super.removeInnerElementDelegate(this.definiens);
			this.definiens = null;
		}
		if(definiens != null) {
			this.definiens = definiens;
			super.addInnerElementDelegate(definiens);
			super.putIntegratedElementDelegate(DEFINIENS, new BaseElement(definiens));
		}
	}

	public Concept getConnectiveOfDefinition() {
		return connectiveOfDefinition;
	}

	public void setConnectiveOfDefinition(Concept connectiveOfDefinition) throws LIMException {
		if(ObjectUtil.checkEquals(this.connectiveOfDefinition, connectiveOfDefinition)) {
			return;
		}
		
		if(this.connectiveOfDefinition != null) {
			super.removeIntegratedElementDelegate(CONNECTIVE_OF_DEFINITION);
			super.removeInnerElementDelegate(this.connectiveOfDefinition);
			this.connectiveOfDefinition = null;
		}
		if(connectiveOfDefinition != null) {
			this.connectiveOfDefinition = connectiveOfDefinition;
			super.addInnerElementDelegate(connectiveOfDefinition);
			super.putIntegratedElementDelegate(CONNECTIVE_OF_DEFINITION, new BaseElement(connectiveOfDefinition));
		}
	}


	public void setDefiniendumAndDefiniens(Concept definiendum, Concept definiens) throws LIMException {
		setDefiniendum(definiendum);
		setDefiniens(definiens);
	}

	public void setDefiniendumAndDefiniens(Concept definiendum, Concept definiens, Concept connectiveOfDefinition) throws LIMException {
		setDefiniendumAndDefiniens(definiendum, definiens);
		setConnectiveOfDefinition(connectiveOfDefinition);
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

		BaseElement definiendum = map.get(DEFINIENDUM);
		if(definiendum != null && definiendum.getId() != null) {
			AbstractElement element = rootElementMap.get(definiendum.getId());
			this.definiendum = (element instanceof Concept) 
					? (Concept) element : this.definiendum;
		}
		BaseElement definiens = map.get(DEFINIENS);
		if(definiens != null && definiens.getId() != null) {
			AbstractElement element = rootElementMap.get(definiens.getId());
			this.definiens = (element instanceof Concept) 
					? (Concept) element : this.definiens;
		}
		BaseElement connectiveOfDefinition = map.get(CONNECTIVE_OF_DEFINITION);
		if(connectiveOfDefinition != null && connectiveOfDefinition.getId() != null) {
			AbstractElement element = rootElementMap.get(connectiveOfDefinition.getId());
			this.connectiveOfDefinition = (element instanceof Concept) 
					? (Concept) element : this.connectiveOfDefinition;
		}
	}


	@Override
	public Definition cloneElement() throws LIMException {
		Definition cloneElement = (Definition) super.cloneElement();
		
		cloneElement.definiendum = (this.definiendum != null) 
				? (Concept) this.definiendum.cloneElement() : cloneElement.definiendum;
		cloneElement.definiens = (this.definiens != null) 
				? (Concept) this.definiens.cloneElement() : cloneElement.definiens;
		cloneElement.connectiveOfDefinition = (this.connectiveOfDefinition != null) 
				? (Concept) this.connectiveOfDefinition.cloneElement() : cloneElement.connectiveOfDefinition;
		
		return cloneElement;
	}

}
