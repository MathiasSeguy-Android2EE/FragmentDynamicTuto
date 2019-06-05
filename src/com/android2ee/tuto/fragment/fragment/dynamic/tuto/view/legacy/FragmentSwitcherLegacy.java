/**<ul>
 * <li>FragmentDynamicTuto</li>
 * <li>com.android2ee.tuto.fragment.fragment.dynamic.tuto.view.legacy</li>
 * <li>24 oct. 2012</li>
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
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.android2ee.tuto.fragment.fragment.dynamic.tuto.MApplication;
import com.android2ee.tuto.fragment.fragment.dynamic.tuto.R;
import com.android2ee.tuto.fragment.fragment.dynamic.tuto.view.callback.MainFragmentCallBack;
import com.android2ee.tuto.fragment.fragment.dynamic.tuto.view.legacy.detail.DetailFragmentLegacy;
import com.android2ee.tuto.fragment.fragment.dynamic.tuto.view.legacy.gen.MActivityLeg;
import com.android2ee.tuto.fragment.fragment.dynamic.tuto.view.legacy.gen.MFragmentLegacy;
import com.android2ee.tuto.fragment.fragment.dynamic.tuto.view.legacy.main.MainFragmentLegacy;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 * This class aims to:
 * <ul><li></li></ul>
 */
public class FragmentSwitcherLegacy extends MFragmentLegacy implements MainFragmentCallBack {
	private final String tag = "FragmentSwitcherLegacy";
	/**
	 * The main activity
	 */
	MActivityLeg activity;
	/******************************************************************************************/
	/** FRAGMENT'S TAG DEFINITION **************************************************************************/
	/******************************************************************************************/
	/**
	 * The main fragment TAG
	 */
	private final String mainFragmentTag = "mainFragmentTag";
	/**
	 * The detail fragment of the first pane TAG
	 */
	private final String detailFragmentFPTag = "detailFragmentFTTag";
	/**
	 * The detail fragment of the second pane TAG
	 */
	private final String detailFragmentSPTag = "detailFragmentSTTag";
	/******************************************************************************************/
	/** Attributes **************************************************************************/
	/******************************************************************************************/
	/**
	 * The linearLayouts that contains the fragments
	 */
	LinearLayout firstPane, secondPane;
	/**
	 * DON'T KEEP POINTER ON THE FRAGMENT !!!!!
	 * NEVER DO THAT
	 */
	// MainFragmentLegacy mainFragment;
	/**
	 * DON'T KEEP POINTER ON THE FRAGMENT !!!!!
	 * NEVER DO THAT
	 */
	// DetailFragmentLegacy detailFragmentFirstPane, detailFragmentSecondPane;
	/**
	 * To know if the activity has been destroyed and recreated
	 */
	Boolean creationMode;
	/**
	 * The current twoPane states
	 */
	boolean twoPanes;
	/**
	 * To know if the DetailFragment is displayed in the first pane of the panel
	 */
	boolean detailDisplayedOnFP = false;

	/******************************************************************************************/
	/** Constructors **************************************************************************/
	/******************************************************************************************/
	/**
	 * 
	 */
	public FragmentSwitcherLegacy(MActivityLeg parent) {
		super();
		activity = parent;

	}

	/******************************************************************************************/
	/** Initialize **************************************************************************/
	/******************************************************************************************/

	/**
	 * Initialize the activity with their fragments
	 * Should be called by the oneCreate of the Activity
	 */
	public void initialize(boolean creationMode) {

		// initialize the attributes
		firstPane = (LinearLayout) activity.findViewById(R.id.firstpane);
		secondPane = (LinearLayout) activity.findViewById(R.id.secondpane);
		twoPanes = activity.getResources().getBoolean(R.bool.twoPane);
		detailDisplayedOnFP = (MApplication.getInstance().getDisplayedFragment() == MApplication.DETAIL_FRAGMENT_DISPLAYED);
		
		this.creationMode = creationMode;
		// if not creation mode the fragments should be re instantiate automatically
		// but in fact not really
		// if you change once, they are, but twice the one that are not visible inbetween are not
		// (port->land->port)

		backStackLog(Log.WARN, "FSwitch_initialize");
		// one or two panes: need to set the first fragment as the mainFragment
		firstFragmentInitialization();
		if (twoPanes) {
			// so what ever happen we need to reinstanciate that elements
			secondFragmentsInitialization();
			secondPane.setVisibility(View.VISIBLE);

		} else {
			secondPane.setVisibility(View.GONE);
		}
	}

	/**
	 * Initialize the 1 pane configuration
	 */
	private void firstFragmentInitialization() {
		// Retrieve the fragment manager
		FragmentManager fm = activity.getSupportFragmentManager();
		FragmentTransaction fTransaction = fm.beginTransaction();
		// add the main fragment whatever happened
		// because you want the back stack to go back to him

		// instanciate the current fragment with the main one
		// Finds a fragment that was identified by the given tag either when inflated from XML or as
		// supplied when added in a transaction. This first searches through fragments that are
		// currently added to the manager's activity; if no such fragment is found, then all
		// fragments currently on the back stack are searched.
		MainFragmentLegacy mainFragment;
		mainFragment = (MainFragmentLegacy) fm.findFragmentByTag(mainFragmentTag);
		log(Log.VERBOSE,tag, "twoFragmentsInitialization : findFragmentByTag(mainFragmentTag) = " + mainFragment
				+ " && creationMode = " + creationMode);
		if (creationMode != (mainFragment == null)) {
			log(Log.ERROR,tag, "twoFragmentsInitialization : creationMode!=(mainFragment==null), [creationMode = "
					+ mainFragment + " && (mainFragment==null) = " + (mainFragment == null));
		}
		if (null == mainFragment) {
			log(Log.VERBOSE,tag, "onResumeOnePane : mainFragment==null");
			mainFragment = new MainFragmentLegacy();
			// add the fragment
			fTransaction.add(R.id.firstpane, mainFragment, mainFragmentTag);
			log(Log.VERBOSE,tag, "twoFragmentsInitialization : fTransaction.add(R.id.firstpane, mainFragment, mainFragmentTag);");
		} else {
			// mainFragment belongs either to the current view either is in the backStack
			// if it belongs to the currentView:do nothing should be the stuff to do
			// if it belongs to the backStack:pop the backstack to display it
			// ok so we pop the back stack
			fm.popBackStackImmediate(activity.getResources().getString(R.string.main_fragment),
					FragmentManager.POP_BACK_STACK_INCLUSIVE);
			// Now we are sure if it was on the backstack it is at the front
			// So showing it is enough
			fTransaction.show(mainFragment);
			// fTransaction.replace(R.id.firstpane, mainFragment, mainFragmentTag);
			log(Log.VERBOSE,tag, "onResumeOnePane : fTransaction.replace(R.id.firstpane, mainFragment, mainFragmentTag);");
		}

		// Commit
		fTransaction.commit();
	}

	/**
	 * Initialize the 2 panes configuration
	 */
	private void secondFragmentsInitialization() {		
		// now depending on the configuration of the fragment instantiate the right one
		FragmentManager fm = activity.getSupportFragmentManager();
		FragmentTransaction fTransaction = fm.beginTransaction();
		// retrieve the main fragment
		DetailFragmentLegacy detailFragmentSecondPane;
		detailFragmentSecondPane = (DetailFragmentLegacy) fm.findFragmentByTag(detailFragmentSPTag);
		// and update the selected item if it exists
		int selectedItem = MApplication.getInstance().getSelectedItem();

		if (null == detailFragmentSecondPane) {
			log(Log.VERBOSE,tag, "onResumeTwoPanes , detailFragmentSecondPane==null");
			detailFragmentSecondPane = new DetailFragmentLegacy();
			// add the information on the selected item using the arguments pattern
			Bundle bundle = new Bundle();
			bundle.putInt(DetailFragmentLegacy.ITEM_ID, selectedItem);
			detailFragmentSecondPane.setArguments(bundle);
			fTransaction.add(R.id.secondpane, detailFragmentSecondPane, detailFragmentSPTag);
		} else {
			log(Log.VERBOSE,tag,
					"onResumeTwoPanes  , fm.findFragmentByTag(detailFragmentSPTag)!=null detailFragmentSecondPane.updateData(selectedItem); called");
			detailFragmentSecondPane.updateData(selectedItem);
			fTransaction.show(detailFragmentSecondPane);
		}

		// Commit
		fTransaction.commit();
	}

	/******************************************************************************************/
	/** Initialize **************************************************************************/
	/******************************************************************************************/
	/**
	 * Should be called during the onResume when going from two pane to one pane
	 * To restore the detail view
	 */
	public void forceOnePaneItemSelection(int itemId) {
		// The first pane is initialized we now should display the DetailFragment within
		onItemSelectedOnePane(itemId);
	}

	/******************************************************************************************/
	/** OnItemSelection Management **************************************************************************/
	/******************************************************************************************/
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.android2ee.tuto.fragment.fragment.dynamic.tuto.view.callback.MainFragmentCallBack#
	 * onItemSelected(int)
	 */
	@Override
	public void onItemSelected(int itemId) {
		// Ok the two cases moment
		// Depending on the number of pane we either update the detailFragment either change the
		// fragment displayed in the firstPane
		if (twoPanes) {
			onItemSelectedTwoPanes(itemId);
		} else {
			onItemSelectedOnePane(itemId);
		}
	}

	/**
	 * OnPane Management
	 * 
	 * @param itemId
	 *            the item to display
	 */
	public void onItemSelectedOnePane(int itemId) {
		log(Log.ERROR,tag, "onItemSelectedOnePane begins");
		backStackLog(Log.WARN, "MainAct_onItemSelectedOnePane");

		// Don't keep a reference to a fragment
		// (DetailFragmentLegacy) fm.findFragmentByTag(detailFragmentFPTag) should be null
		// we need to create it and to add it to the layout
		// because either it hasn't been create (first creation)
		// either it has been destroyed (created and then backstack.popup called)
		// in every case it doesn't exist anymore
		// And add it to the backstack
		// find the fragment manager
		FragmentManager fm = activity.getSupportFragmentManager();
		DetailFragmentLegacy detailFragmentFirstPane = (DetailFragmentLegacy) fm.findFragmentByTag(detailFragmentFPTag);
		if (null == detailFragmentFirstPane) {
			log(Log.VERBOSE,tag, "onItemSelectedOnePane:null == fm.findFragmentByTag(detailFragmentFPTag)");
			// should be the normal behavior
			detailFragmentFirstPane = new DetailFragmentLegacy();
			// add the information on the selected item using the arguments pattern
			Bundle bundle = new Bundle();
			bundle.putInt(DetailFragmentLegacy.ITEM_ID, itemId);
			detailFragmentFirstPane.setArguments(bundle);
			// Store the displayed fragment
			MApplication.getInstance().setDisplayedFragment(MApplication.DETAIL_FRAGMENT_DISPLAYED);
			// need to replace mainFragment by detailFragment
			FragmentTransaction fTransaction = fm.beginTransaction();

			// set the animation
			fTransaction.setCustomAnimations(R.anim.anim_push_left_in, R.anim.anim_push_left_out,
					R.anim.anim_push_right_in, R.anim.anim_push_right_out);
			// so replace
			fTransaction.replace(R.id.firstpane, detailFragmentFirstPane, detailFragmentFPTag);
			fTransaction.addToBackStack(activity.getString(R.string.main_fragment));
			fTransaction.commit();
		} else {
			log(Log.ERROR,tag, "onItemSelectedOnePane:null != fm.findFragmentByTag(detailFragmentFPTag)");
		}
		backStackLog(Log.WARN, "MainAct_onItemSelectedOnePane");
		log(Log.ERROR,tag, "onItemSelectedOnePane finishes");
	}

	/**
	 * TwoPane Management
	 * 
	 * @param itemId
	 *            the item to display
	 */
	public void onItemSelectedTwoPanes(int itemId) {
		log(Log.ERROR,tag, "onItemSelectedTwoPanes begins");
		backStackLog(Log.WARN, "MainAct_onItemSelectedTwoPanes");
		// find the fragment manager
		FragmentManager fm = activity.getSupportFragmentManager();
		// the fragment should exists and has been added to the layout
		// so just update it
		log(Log.VERBOSE,tag, "onItemSelectedTwoPanes:null == detailFragmentSecondPane ");
		DetailFragmentLegacy detailFragmentSecondPane = (DetailFragmentLegacy) fm.findFragmentByTag(detailFragmentSPTag);
		if (null == detailFragmentSecondPane) {
			log(Log.ERROR,tag, "onItemSelectedTwoPane:null == fm.findFragmentByTag(detailFragmentFPTag)");

		} else {
			log(Log.VERBOSE,tag, "onItemSelectedTwoPanes: null != fm.findFragmentByTag(detailFragmentSPTag) ");
		}

		log(Log.VERBOSE,tag, "onItemSelectedTwoPanes: null != detailFragmentSecondPane.updateData(itemId) ");
		// just need to update it
		detailFragmentSecondPane.updateData(itemId);
		backStackLog(Log.WARN, "MainAct_onItemSelectedTwoPanes");
		log(Log.ERROR, tag, "onItemSelectedTwoPanes finishes");
	}

	/******************************************************************************************/
	/** log **************************************************************************/
	/******************************************************************************************/

	/**
	 * Log the current BackStack
	 */
	public void backStackLog(int level, String tag) {
		if (MApplication.DEBUG) {
			FragmentManager fm = activity.getSupportFragmentManager();
			Log.println(level, this.tag, tag + " : BackStack Browsing (" + fm.getBackStackEntryCount() + ")");
			BackStackEntry bsEntry;
			for (int i = 0; i < fm.getBackStackEntryCount(); i++) {
				bsEntry = fm.getBackStackEntryAt(i);
				Log.println(level, this.tag, "[BS] " + tag + " : BackStack[" + i + "]" + bsEntry);
				Log.println(level, this.tag, "[BS] " + tag + " : Name {" + bsEntry.getName() + "}");
			}
		}
	}

	/**
	 * Log the current BackStack
	 */
	public void fragmentLog(int level, String tag) {
		if (MApplication.DEBUG) {
			FragmentManager fm = activity.getSupportFragmentManager();
			Log.println(level, this.tag, tag + " : Stored Fragment Loggers");
			DetailFragmentLegacy dfSP = (DetailFragmentLegacy) fm.findFragmentByTag(detailFragmentSPTag);
			DetailFragmentLegacy dfFP = (DetailFragmentLegacy) fm.findFragmentByTag(detailFragmentFPTag);
			MainFragmentLegacy mf = (MainFragmentLegacy) fm.findFragmentByTag(mainFragmentTag);
			Log.println(level, this.tag, "[Fr] " + tag + " : MainFr (" + mf + ")");
			Log.println(level, this.tag, "[Fr] " + tag + " : DetailFr (1p) (" + dfFP + ")");
			Log.println(level, this.tag, "[Fr] " + tag + " : DetailFr (2p) (" + dfSP + ")");
		}
	}

	/**
	 * Makes log depending on the Debug parameter of the application
	 * @param level
	 * @param tag
	 * @param message
	 */
	public void log(int level, String tag, String message) {
		if (MApplication.DEBUG) {
			Log.println(level, tag, message);
		}else {
			Log.println(Log.DEBUG, tag, message);
		}
	}
}
