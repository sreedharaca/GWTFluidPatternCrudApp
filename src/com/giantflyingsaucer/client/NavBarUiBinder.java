/**
 * 
 */
package com.giantflyingsaucer.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author SIRI
 *
 */
public class NavBarUiBinder extends Composite {

	private static NavBarUiBinderUiBinder uiBinder = GWT
			.create(NavBarUiBinderUiBinder.class);
	 
	interface NavBarUiBinderUiBinder extends UiBinder<Widget, NavBarUiBinder> {
	}

	 
	public NavBarUiBinder() {
		initWidget(uiBinder.createAndBindUi(this));
	}
 
	public NavBarUiBinder(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));

		 
	}
  
}
