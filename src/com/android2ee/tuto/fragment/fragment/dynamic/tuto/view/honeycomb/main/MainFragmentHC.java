/**<ul>
 * <li>FragmentStaticTuto</li>
 * <li>com.android2ee.tuto.fragment.fragmentstatic.tuto.view.honeycomb</li>
 * <li>16 oct. 2012</li>
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
package com.android2ee.tuto.fragment.fragment.dynamic.tuto.view.honeycomb.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android2ee.tuto.fragment.fragment.dynamic.tuto.MApplication;
import com.android2ee.tuto.fragment.fragment.dynamic.tuto.R;
import com.android2ee.tuto.fragment.fragment.dynamic.tuto.dao.HumanDao;
import com.android2ee.tuto.fragment.fragment.dynamic.tuto.view.adapter.HumanArrayAdapter;
import com.android2ee.tuto.fragment.fragment.dynamic.tuto.view.callback.MainFragmentCallBack;
import com.android2ee.tuto.fragment.fragment.dynamic.tuto.view.honeycomb.MainActivityHC;
import com.android2ee.tuto.fragment.fragment.dynamic.tuto.view.honeycomb.gen.MFragment;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 *        This class aims to:
 *        <ul>
 *        <li></li>
 *        </ul>
 */
@SuppressLint("NewApi")
public class MainFragmentHC extends MFragment {
	/**
	 * The ListView
	 */
	private ListView listView;
	/**
	 * The arrayAdapter
	 */
	private HumanArrayAdapter arrayAdapter;
	/**
	 * The parent of the fragment (its callback/its activity container)
	 */
	private MainFragmentCallBack parent;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// use this method to link the fragment with its parent/container
		setName("MainFragmentHC");
		parent = (MainFragmentCallBack)((MainActivityHC) activity).getfSwitcher();
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.v("SpeakerDetailFragment onCreateView", "getView :" + getView());
		// inflate the view
		View view = inflater.inflate(R.layout.fragment_list, container, false);
		// Retrieve the ListView, EditText and Button
		listView = (ListView) view.findViewById(R.id.humanListView);
		// Create the adapter
		arrayAdapter = new HumanArrayAdapter(getActivity(), HumanDao.instance.getHumans());
		// bind the listView and its adapter.
		listView.setAdapter(arrayAdapter);
		// Add a listener on the listView
		listView.setClickable(true);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				onItemSelected(position);
			}
		});
		return view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onResume()
	 */
	@Override
	public void onResume() {
		super.onResume();
		// Update the selected item (the one that has been stored in the application object when the
		// user has selected an item before the activity has been destroyed and recreated due to a
		// Configuration change)
		// first find if it a two pane configuration or not
		Boolean twoPanes = getResources().getBoolean(R.bool.twoPane);
		// then retrieve the selected item
		int selectedItem = MApplication.getInstance().getSelectedItem();
		if (twoPanes) {
			// then make a full update, it will update the DetailFragmentHC and the arrayAdapter
			if (selectedItem != MApplication.ITEM_ID_DEFAULT_VALUE) {
				onItemSelected(selectedItem);
			}
		} else {
			// then do a gentle update (if we update the parent activity, it won't update the
			// DetailFragmentHC that is not displayed, but launch the DetailActivity and it's not the
			// goal here)
			if (selectedItem != MApplication.ITEM_ID_DEFAULT_VALUE) {
				onItemSelectedSimple(selectedItem);
			}
		}
	}

	/**
	 * An item has been updated, notify the change to every body
	 * @param position of the item
	 */
	public void onItemSelected(int position) {
		//Notify the parent that a new item has been selected
		parent.onItemSelected(position);
		//notify the arrayAdapter that an item has been selected
		arrayAdapter.setSelectedItem(position);
		//and notify the Application object too, it will persist the information when activity will be destroyed and recreated
		MApplication.getInstance().setSelectedItem(position);
	}

	/**
	 * An item has been updated, notify only the adapter of the change
	 * @param position of the item
	 */
	public void onItemSelectedSimple(int position) {
		arrayAdapter.setSelectedItem(position);
		parent.forceOnePaneItemSelection(position);
	}
	
	/******************************************************************************************/
	/** Menu Management **************************************************************************/
	/******************************************************************************************/
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.android2ee.tuto.fragment.fragment.dynamic.tuto.view.honeycomb.gen.MFragment#onCreate(
	 * android.os.Bundle)
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Enable the menu for the fragment
		setHasOptionsMenu(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onCreateOptionsMenu(android.view.Menu, android.view.MenuInflater)
	 */
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// Build the menu's view for the fragment
		inflater.inflate(R.menu.main_fragment_menu, menu);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onOptionsItemSelected(android.view.MenuItem)
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		//handle your menu item here
		switch (item.getItemId()) {
		case R.id.menu_main_fragment:
			// Do something
			Toast.makeText(getActivity(), getString(R.string.main_fragment_menu_toast_mes), Toast.LENGTH_SHORT).show();
			return true;
		default:
			// Keep going looking for someone to handle that event
			return super.onOptionsItemSelected(item);
		}
	}
}
