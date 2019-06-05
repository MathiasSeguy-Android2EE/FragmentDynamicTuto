/**<ul>
 * <li>FragmentStaticTuto</li>
 * <li>com.android2ee.tuto.fragment.fragmentstatic.tuto.dao</li>
 * <li>17 oct. 2012</li>
 * 
 * <li>======================================================</li>
 *
 * <li>Projet : Mathias Seguy Project</li>
 * <li>Produit par MSE.</li>
 *
 /**
 * <ul>
 * Android Tutorial, An <strong>Android2EE</strong>'s project.</br> 
 * Produced by <strong>Dr. Mathias SEGUY</strong>.</br>
 * Delivered by <strong>http://android2ee.com/</strong></br>
 *  Belongs to <strong>Mathias Seguy</strong></br>
 ****************************************************************************************************************</br>
 * This code is free for any usage except training and can't be distribute.</br>
 * The distribution is reserved to the site <strong>http://android2ee.com</strong>.</br>
 * The intelectual property belongs to <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * 
 * *****************************************************************************************************************</br>
 *  Ce code est libre de toute utilisation mais n'est pas distribuable.</br>
 *  Sa distribution est reservée au site <strong>http://android2ee.com</strong>.</br> 
 *  Sa propriété intellectuelle appartient à <strong>Mathias Seguy</strong>.</br>
 *  <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * *****************************************************************************************************************</br>
 */
package com.android2ee.tuto.fragment.fragment.dynamic.tuto.dao;

import java.util.List;

import android.util.SparseArray;

import com.android2ee.tuto.fragment.fragment.dynamic.tuto.model.Human;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 *        This class aims to manage Acces to Human objects
 */
public enum HumanDao {

	/**
	 * the singleton
	 */
	instance;
	/**
	 * The human list managed by the Dao
	 */
	private List<Human> humans;
	/**
	 * The map that link humanId and human
	 */
	private SparseArray<Human> mapIdToHuman;

	/**
	 * private constructor
	 */
	private HumanDao() {
		humans = DummyHumanFactory.getHumans();
		mapIdToHuman = DummyHumanFactory.getMapIdToHuman();
	}

	/**
	 * Return the Human with id id
	 * 
	 * @param id
	 * @return
	 */
	public Human getHumanById(int id) {
		return mapIdToHuman.get(Integer.valueOf(id));
	}

	/**
	 * @return the HumanList
	 */
	public List<Human> getHumans() {
		return humans;
	}

}
