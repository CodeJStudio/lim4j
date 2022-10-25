package com.codejstudio.lim.pojo.i;

import java.util.Collection;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.pojo.role.Observer;
import com.codejstudio.lim.pojo.role.ObserverGroup;
import com.codejstudio.lim.pojo.role.Proposer;

/**
 * IOwnable.class
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v1.0.0
 */
public interface IOwnable {

	/* getters & setters */

	public Proposer getProposer();

	public void setProposer(Proposer proposer) throws LIMException;
	
	public ObserverGroup getObserverGroup();


	/* CRUD for collections, maps, sub-groups: observers */

	public boolean containObserver(Observer observer) throws LIMException;

	public boolean addObserver(Observer... observers) throws LIMException;
	
	public boolean addObserver(Collection<Observer> observers) throws LIMException;

	public boolean removeObserver(Observer... observers) throws LIMException;
	
	public boolean removeObserver(Collection<Observer> observers) throws LIMException;
	
}
