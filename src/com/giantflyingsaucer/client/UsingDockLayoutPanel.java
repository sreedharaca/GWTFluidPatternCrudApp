package com.giantflyingsaucer.client;

import java.util.GregorianCalendar;

import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.Container;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.Row;
import com.github.gwtbootstrap.client.ui.WellNavList;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.sca.adaptor.items.PartyAdaptor;
import com.sca.model.Party;
import com.sencha.gxt.widget.core.client.Portlet;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class UsingDockLayoutPanel implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	private Container con;
	private Row mainContent;
	private Column menuCol;
	private Column formCol;

	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {
 

		con = new Container();

		mainContent = new Row();
		menuCol = new Column(4);
		formCol = new Column(8);
		mainContent.add(menuCol);
		mainContent.add(formCol);

		//menuCol.add(new MenuLayout());
		WellNavList list = new WellNavList();
		
		for(int i=0;i<5;i++){
			NavLink navs = new NavLink();
			navs.setText("System Features Link "+i);
			navs.setIcon(IconType.PLANE);
			list.add(navs);
		}

		menuCol.add(list);
		
		Party customer = new Party("Bob", "Smith", new GregorianCalendar(1980, 5, 3)
        .getTime());
		
		PartyAdaptor part = new PartyAdaptor(customer);
		
		formCol.add(part.getClientForm());
		// Add Nav Bar
		con.add(new NavBarUiBinder());

		// Add Container
		con.add(mainContent);
		RootPanel.get("mainDiv").add(con);

	}
	
	private void configPanel(final Portlet panel) {
	    panel.setCollapsible(true);
	    panel.setAnimCollapse(false);
	    panel.getHeader().addTool(new ToolButton(ToolButton.GEAR));
	    panel.getHeader().addTool(new ToolButton(ToolButton.CLOSE, new SelectHandler() {
	 
	      @Override
	      public void onSelect(SelectEvent event) {
	        panel.removeFromParent();
	      }
	    }));
	  } 
}
