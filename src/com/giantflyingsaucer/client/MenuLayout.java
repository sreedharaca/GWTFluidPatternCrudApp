package com.giantflyingsaucer.client;
import com.github.gwtbootstrap.client.ui.Accordion;
import com.github.gwtbootstrap.client.ui.AccordionGroup;
import com.github.gwtbootstrap.client.ui.NavHeader;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.WellNavList;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
 

public class MenuLayout implements EntryPoint, IsWidget {

	private WellNavList header;
	private Accordion menuItem;
	private NavHeader headerTitle;
	private AccordionGroup links; 

	@Override
	public Widget asWidget() {

		if (header == null) {
			 
			header = new WellNavList();
			
			headerTitle = new NavHeader();
			headerTitle.setText("Menu");
			/*<b:Accordion>
		    <b:AccordionGroup heading="One">
		        <b:Paragraph>one</b:Paragraph>
		        <b:Paragraph>two</b:Paragraph>
		    </b:AccordionGroup>
		    <b:AccordionGroup heading="Defalut Open" defaultOpen="true">
		        <b:Paragraph>The default opened Accordion</b:Paragraph>
		    </b:AccordionGroup>
		    <b:AccordionGroup defaultOpen="true" heading="With Icon" icon="GITHUB">
		        <b:Paragraph>Icon style</b:Paragraph>
		    </b:AccordionGroup>
		    <b:AccordionGroup heading="With Custom Icon">
		        <b:customTrigger>
		            <b:Image addStyleNames="{style.icon}" resource="{res.logo}"/>
		        </b:customTrigger>
		        <b:Paragraph>Custom Icon Style</b:Paragraph>
		    </b:AccordionGroup>
		</b:Accordion>*/
			
			menuItem = new Accordion();
			
			addAccordionGroups(menuItem,"View");
			addAccordionGroups(menuItem,"Input User");
			addAccordionGroups(menuItem,"Control User");
			
			header.add(menuItem);

		}
		return header;
	}

	private void addAccordionGroups(Accordion menuItem, String headerTitle) {
		 
		AccordionGroup newAg = new AccordionGroup();
		newAg.setHeading(headerTitle);
		
		for(int i=0;i<5;i++){
			NavLink navs = new NavLink();
			navs.setText("System Features Link "+i);
			newAg.add(navs);
		}
		
		menuItem.add(newAg);
	}

	
	@Override
	public void onModuleLoad() {

	}

}
