/**<ul>
 * <li>FragmentDynamicTuto</li>
 * <li>com.android2ee.tuto.fragment.fragment.dynamic.tuto.view.legacy</li>
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
package com.android2ee.tuto.fragment.fragment.dynamic.tuto.view.legacy;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android2ee.tuto.fragment.fragment.dynamic.tuto.MApplication;
import com.android2ee.tuto.fragment.fragment.dynamic.tuto.R;
import com.android2ee.tuto.fragment.fragment.dynamic.tuto.view.legacy.gen.MActivityLeg;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 *        This class aims to:
 *        <ul>
 *        <li></li>
 *        </ul>
 */
public class MainActivityLegacy extends MActivityLeg {
	private final String tag = "MainActivityHC";

	/******************************************************************************************/
	/** LifeCycle **************************************************************************/
	/******************************************************************************************/

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// link to the fragmentSwitcher
		// done in the super
		// fSwitcher=new FragmentSwitcherHC(this);
		// Watch out Fragment management
		FragmentManager.enableDebugLogging(true);
		// ok build the view and find the layouts
		setContentView(R.layout.activity_hc);
		// The fragment switcher has been initialized in the super class MActivity
		// Launch the Fragment Switcher initialization
		fSwitcher.initialize(savedInstanceState == null);
		// then do nothing, jobs has to be done in onResume not here

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		// release the selected item (it is not selected anymore)
		((MApplication) getApplication()).setSelectedItem(MApplication.ITEM_ID_DEFAULT_VALUE);
	}

	/******************************************************************************************/
	/** Menu Management **************************************************************************/
	/******************************************************************************************/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// inflate the menu
		getMenuInflater().inflate(R.menu.main_activity_menu, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//When you handle a MenuItem int this method
		//you need to first check if it belongs to the activity
		//else forward the event to your fragments (using default)
		switch (item.getItemId()) {
		case R.id.menu_main_activity:
			// Do something
			Toast.makeText(this, getString(R.string.main_activity_menu_toast_mes), Toast.LENGTH_SHORT).show();
			return true;
		default:
			// call your fragment to handle the selection
			return super.onOptionsItemSelected(item);
		}
	}
}
