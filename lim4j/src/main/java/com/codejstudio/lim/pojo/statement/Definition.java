package com.codejstudio.lim.pojo.statement;

import java.util.Map;
import java.util.Objects;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.GenericActionableElement;
import com.codejstudio.lim.pojo.GenericElement;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.i.IConvertible;
import com.codejstudio.lim.pojo.util.ElementTrace;
import com.codejstudio.lim.pojo.util.PojoElementClassConstant;

/**
 * Definition.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public class Definition extends JudgedStatement {

	/* constants */

	private static final long serialVersionUID = 3886392364048849854L;

	public static final String DEFINIENDUM = "definiendum";
	public static final String DEFINIENS = "definiens";
	public static final String CONNECTIVE_OF_DEFINITION = "connective-of-definition";


	/* variables */

	protected Concept definiendum;
	protected Concept definiens;
	protected Concept connectiveOfDefinition;


	/* constructors */

	/**
	 * only for JAXB auto unmarshalling usage
	 */
	public Definition() {
		super();
	}

	public Definition(boolean initIdFlag, boolean initTypeFlag) throws LIMException {
		super(initIdFlag, initTypeFlag);
	}

	public Definition(boolean initIdFlag, boolean initTypeFlag, String description) throws LIMException {
		super(initIdFlag, initTypeFlag, description);
	}


	public Definition(String description) throws LIMException {
		super(true, true, description);
	}

	public Definition(Concept definiendum, Concept definiens, Concept connectiveOfDefinition) 
			throws LIMException {
		super(true, true);
		setDefiniendumAndDefiniens(definiendum, definiens, connectiveOfDefinition);
	}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		PojoElementClassConstant.registerElementClassForInit(Definition.class);
		JudgedStatement.registerSubPojoClassForInit(Definition.class);
	}


	/* getters & setters */

	public Concept getDefiniendum() {
		return definiendum;
	}

	public boolean setDefiniendum(final Concept definiendum) throws LIMException {
		if (Objects.equals(this.definiendum, definiendum)) {
			return true;
		}

		boolean flag = true;
		if (this.definiendum != null) {
			flag &= super.removeIntegratedElementDelegate(DEFINIENDUM) 
					& super.removeInnerElementDelegate(this.definiendum);
			this.definiendum = null;
		}
		if (definiendum != null) {
			this.definiendum = definiendum;
			flag &= super.addInnerElementDelegate(definiendum) 
					& super.putIntegratedElementDelegate(DEFINIENDUM, new BaseElement(definiendum));
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setDefiniendum(" 
					+ ((definiendum == null) ? null : definiendum.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setDefiniendum(" 
					+ ((definiendum == null) ? null : definiendum.toBaseString()) + ")");
		}
		return flag;
	}


	public Concept getDefiniens() {
		return definiens;
	}

	public boolean setDefiniens(final Concept definiens) throws LIMException {
		if (Objects.equals(this.definiens, definiens)) {
			return true;
		}

		boolean flag = true;
		if (this.definiens != null) {
			flag &= super.removeIntegratedElementDelegate(DEFINIENS) 
					& super.removeInnerElementDelegate(this.definiens);
			this.definiens = null;
		}
		if (definiens != null) {
			this.definiens = definiens;
			flag &= super.addInnerElementDelegate(definiens) 
					& super.putIntegratedElementDelegate(DEFINIENS, new BaseElement(definiens));
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setDefiniens(" 
					+ ((definiens == null) ? null : definiens.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setDefiniens(" 
					+ ((definiens == null) ? null : definiens.toBaseString()) + ")");
		}
		return flag;
	}


	public Concept getConnectiveOfDefinition() {
		return connectiveOfDefinition;
	}

	public boolean setConnectiveOfDefinition(final Concept connectiveOfDefinition) throws LIMException {
		if (Objects.equals(this.connectiveOfDefinition, connectiveOfDefinition)) {
			return true;
		}

		boolean flag = true;
		if (this.connectiveOfDefinition != null) {
			flag &= super.removeIntegratedElementDelegate(CONNECTIVE_OF_DEFINITION) 
					& super.removeInnerElementDelegate(this.connectiveOfDefinition);
			this.connectiveOfDefinition = null;
		}
		if (connectiveOfDefinition != null) {
			this.connectiveOfDefinition = connectiveOfDefinition;
			flag &= super.addInnerElementDelegate(connectiveOfDefinition) 
					& super.putIntegratedElementDelegate(CONNECTIVE_OF_DEFINITION, new BaseElement(connectiveOfDefinition));
		}

		if (flag) {
			ElementTrace.log.info(this.toBaseString() + ": setConnectiveOfDefinition(" 
					+ ((connectiveOfDefinition == null) ? null : connectiveOfDefinition.toBaseString()) + ")");
		} else {
			ElementTrace.log.warn(this.toBaseString() + "fail to setConnectiveOfDefinition(" 
					+ ((connectiveOfDefinition == null) ? null : connectiveOfDefinition.toBaseString()) + ")");
		}
		return flag;
	}


	public void setDefiniendumAndDefiniens(final Concept definiendum, final Concept definiens) 
			throws LIMException {
		setDefiniendum(definiendum);
		setDefiniens(definiens);
	}

	public void setDefiniendumAndDefiniens(final Concept definiendum, final Concept definiens, 
			final Concept connectiveOfDefinition) throws LIMException {
		setDefiniendumAndDefiniens(definiendum, definiens);
		setConnectiveOfDefinition(connectiveOfDefinition);
	}


	/* overridden methods */

	@Override
	public IConvertible reload(final IConvertible convertible, 
			final Map<String, GenericElement> rootElementMap, 
			final Map<String, GenericActionableElement> rootActionableElementMap) throws LIMException {
		if (super.reload(convertible, rootElementMap, rootActionableElementMap) == null) {
			return null;
		}
		reloadFromRootElementMap(rootElementMap);
		return (IConvertible) this;
	}

	private void reloadFromRootElementMap(final Map<String, GenericElement> rootElementMap) 
			throws LIMException {
		Map<String, BaseElement> item;
		if (CollectionUtil.checkNullOrEmpty(rootElementMap) 
				|| CollectionUtil.checkNullOrEmpty(item = super.getIntegratedElementMap())) {
			return;
		}

		BaseElement definiendum = item.get(DEFINIENDUM);
		if (definiendum != null && definiendum.getId() != null) {
			GenericElement ge = rootElementMap.get(definiendum.getId());
			this.definiendum = (ge instanceof Concept) ? (Concept) ge : this.definiendum;
			super.addInnerElementDelegate(this.definiendum);
		}
		BaseElement definiens = item.get(DEFINIENS);
		if (definiens != null && definiens.getId() != null) {
			GenericElement ge = rootElementMap.get(definiens.getId());
			this.definiens = (ge instanceof Concept) ? (Concept) ge : this.definiens;
			super.addInnerElementDelegate(this.definiens);
		}
		BaseElement connectiveOfDefinition = item.get(CONNECTIVE_OF_DEFINITION);
		if (connectiveOfDefinition != null && connectiveOfDefinition.getId() != null) {
			GenericElement ge = rootElementMap.get(connectiveOfDefinition.getId());
			this.connectiveOfDefinition = (ge instanceof Concept) 
					? (Concept) ge : this.connectiveOfDefinition;
			super.addInnerElementDelegate(this.connectiveOfDefinition);
		}
	}


	@Override
	public Definition cloneElement(final Map<String, BaseElement> clonedElementMap) throws LIMException {
		if (!CollectionUtil.checkNullOrEmpty(clonedElementMap) && this.id != null) {
			if (clonedElementMap.containsKey(this.id)) {
				BaseElement value = clonedElementMap.get(this.id);
				if (value != null && value.getClass().equals(Definition.class)) {
					return (Definition) value;
				}
			} else {
				clonedElementMap.put(this.id, new BaseElement(this.id, this.type));
			}
		}

		Definition clonedElement = (Definition) super.cloneElement(clonedElementMap);
		return cloneToElement(clonedElement, clonedElementMap);
	}

	@Override
	public Definition cloneToElement(final GenericElement clonedElement) throws LIMException {
		GenericElement ce;
		if (!(clonedElement instanceof Definition) 
				|| !((ce = super.cloneToElement(clonedElement)) instanceof Definition)) {
			return null;
		}
		return cloneToElement((Definition) ce, null);
	}

	private Definition cloneToElement(final Definition clonedElement, 
			final Map<String, BaseElement> clonedElementMap) throws LIMException {
		clonedElement.definiendum = (this.definiendum != null) 
				? (Concept) this.definiendum.cloneElement(clonedElementMap) : clonedElement.definiendum;
		clonedElement.definiens = (this.definiens != null) 
				? (Concept) this.definiens.cloneElement(clonedElementMap) : clonedElement.definiens;
		clonedElement.connectiveOfDefinition = (this.connectiveOfDefinition != null) 
				? (Concept) this.connectiveOfDefinition.cloneElement(clonedElementMap) 
				: clonedElement.connectiveOfDefinition;

		return clonedElement;
	}

}
