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
package com.android2ee.tuto.fragment.fragment.dynamic.tuto.view.honeycomb.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android2ee.tuto.fragment.fragment.dynamic.tuto.MApplication;
import com.android2ee.tuto.fragment.fragment.dynamic.tuto.R;
import com.android2ee.tuto.fragment.fragment.dynamic.tuto.dao.HumanDao;
import com.android2ee.tuto.fragment.fragment.dynamic.tuto.model.Human;
import com.android2ee.tuto.fragment.fragment.dynamic.tuto.view.honeycomb.gen.MFragment;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 *        This class aims to display an human using a fragment
 */
@SuppressLint("NewApi")
public class DetailFragmentHC extends MFragment {

	/**
	 * The constant to pass parameter to the activity
	 */
	public static final String ITEM_ID = "selected_item_id";
	/**
	 * The reference to the picture
	 */
	public int picture1 = R.drawable.picture_human1, picture2 = R.drawable.picture_human2,
			picture3 = R.drawable.picture_human3, picture4 = R.drawable.picture_human4;
	/**
	 * The id of the displayed item
	 */
	public int itemId = MApplication.ITEM_ID_DEFAULT_VALUE;

	/******************************************************************************************/
	/** LifeCycle **************************************************************************/
	/******************************************************************************************/

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.v("DetailFragmentHC updateData", "updateData:getView :" + getView());
		setName("DetailFragmentHC");
		// as usual, build the view and return it
		View view = inflater.inflate(R.layout.fragment_detail, container, false);
		if (getArguments() != null) {
			if (getArguments().getInt(ITEM_ID) != 0) {
				updateView(getArguments().getInt(ITEM_ID), view);
			}
		}
		return view;
	}

	/******************************************************************************************/
	/** Methods **************************************************************************/
	/******************************************************************************************/

	/**
	 * Update the data according to the item ID
	 * 
	 * @param itemId
	 */
	public void updateData(int itemId) {
		Log.v("DetailFragmentHC updateData", "updateData:getView :" + getView());
		View view = getView();
		// check if the view is not null (can occurs when other developpers of the team make
		// nonsense. This avoid nullPointerException
		// Also check if there is a selected item
		updateView(itemId, view);
	}

	/**
	 * @param itemId
	 * @param view
	 */
	public void updateView(int itemId, View view) {
		// check if the view is not null (can occurs when other developpers of the team make
		// nonsense. This avoid nullPointerException
		// Also check if there is a selected item
		if (null != view && itemId != MApplication.ITEM_ID_DEFAULT_VALUE) {
			// then retrieve the data to display
			Human hum = HumanDao.instance.getHumanById(itemId);
			// update the view
			((TextView) view.findViewById(R.id.fd_name)).setText(hum.getName());
			((TextView) view.findViewById(R.id.fd_firstName)).setText(hum.getFirstName());
			((TextView) view.findViewById(R.id.fd_nickName)).setText(hum.getNickName());
			((TextView) view.findViewById(R.id.fd_age)).setText("" + hum.getAge());
			int pictId;
			switch (hum.getPictureId()) {
			case 1:
				pictId = picture1;
				break;
			case 2:
				pictId = picture2;
				break;
			case 3:
				pictId = picture3;
				break;
			case 4:
				pictId = picture4;
				break;
			default:
				pictId = picture1;
				break;
			}
			((ImageView) view.findViewById(R.id.fd_picture)).setBackgroundResource(pictId);
		}
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
			inflater.inflate(R.menu.detail_fragment_menu, menu);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onOptionsItemSelected(android.view.MenuItem)
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		// handle your menu item here
		switch (item.getItemId()) {
		case R.id.menu_detail_fragment:
			// Do something
			Toast.makeText(getActivity(), getString(R.string.detail_fragment_menu_toast_mes), Toast.LENGTH_SHORT)
					.show();
			return true;
		default:
			// Keep going looking for someone to handle that event
			return super.onOptionsItemSelected(item);
		}
	}

}
