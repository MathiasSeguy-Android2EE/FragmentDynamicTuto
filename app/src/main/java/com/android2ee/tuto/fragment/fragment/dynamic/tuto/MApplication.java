/**<ul>
 * <li>FragmentStaticTuto</li>
 * <li>com.android2ee.tuto.fragment.fragmentstatic.tuto</li>
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
package com.android2ee.tuto.fragment.fragment.dynamic.tuto;

import android.app.Application;
import android.util.Log;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 * This class aims to keep the application state when activities die
 * Here it keeps the item id selected
 */
public class MApplication extends Application {
	/******************************************************************************************/
	/** Attributes to know which configuration is displayed***********************************/
	/******************************************************************************************/
	/**
	 * Constant meaning the Main fragment is displayed
	 */
	public static final int MAIN_FRAGMENT_DISPLAYED=0;
	/**
	 * Constant meaning the Detail fragment is displayed
	 */
	public static final int DETAIL_FRAGMENT_DISPLAYED=1;
	/**
	 * Variable to store the configuration of the variable visibility
	 */
	private int displayedFragment=-1;//MAIN_FRAGMENT_DISPLAYED;
	/******************************************************************************************/
	/** Attribute to manage the selected item **************************************************************************/
	/******************************************************************************************/
	/**
	 * Constant for default value of an item not iniitialized
	 */
	public static final int ITEM_ID_DEFAULT_VALUE = -1;
	/**
	 * The selectedItem
	 */
	private int selectedItem=ITEM_ID_DEFAULT_VALUE;
	/******************************************************************************************/
	/** DEBUG MODE **************************************************************************/
	/******************************************************************************************/
	public static final boolean DEBUG=true;
	
	/******************************************************************************************/
	/** Access Every Where **************************************************************************/
	/******************************************************************************************/
	/**
	 * instance of this
	 */
	private static MApplication instance;

	/**
	 * @return The instance of the application
	 */
	public static MApplication getInstance() {
		return instance;
	}
	/******************************************************************************************/
	/** Managing LifeCycle **************************************************************************/
	/******************************************************************************************/

	@Override
	public void onCreate() {
		super.onCreate();
		Log.e("MApplication:onCreate","Application is create");
		instance = this;
	}
	/******************************************************************************************/
	/** Getters/setters **************************************************************************/
	/******************************************************************************************/

	/**
	 * @return the selectedItem
	 */
	public final int getSelectedItem() {
		return selectedItem;
	}
	/**
	 * @param selectedItem the selectedItem to set
	 */
	public final void setSelectedItem(int selectedItem) {
		this.selectedItem = selectedItem;
	}
	/**
	 * @return the displayedFragment
	 */
	public final int getDisplayedFragment() {
		Log.v("MApplication","getDisplayedFragment : "+displayedFragment);
		return displayedFragment;
	}
	/**
	 * @param displayedFragment the displayedFragment to set
	 */
	public final void setDisplayedFragment(int displayedFragment) {
		Log.v("MApplication","setDisplayedFragment : "+displayedFragment);
		this.displayedFragment = displayedFragment;
	}
	
}
