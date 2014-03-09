package com.sca.adaptor;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sca.model.GWTPropertyDescriptor;
import com.sca.model.MethodPropertyDescriptor;
import com.sca.model.PersistentObject;
import com.sca.model.Property;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;

public class BeanItemAdapter<T extends PersistentObject> implements
		FormFieldFactory {

	private static final long serialVersionUID = 677532970926992036L;
	private Map<Property, BuildableObjectProperty> propertyCache;
	private HashMap<Object, Property> map = new HashMap<Object, Property>();
	private LinkedList<Object> list = new LinkedList<Object>();

	public BeanItemAdapter(T bean) {
		initializeItemProps();
	}

	public BeanItemAdapter(Object bean, Collection<?> propertyIds) {

		LinkedHashMap<String, GWTPropertyDescriptor> pds = getPropertyDescriptors(bean);
		for (Object id : propertyIds) {
			GWTPropertyDescriptor pd = pds.get(id);
			if (pd != null) {
				addItemProperty(pd.getName(),
						pd.createProperty(bean.getClass()));
			}
		}
	}

	public BeanItemAdapter(T bean, String[] propertyIds) {

		this(bean, Arrays.asList(propertyIds));
		initializeItemProps();
	}

	protected void initializeItemProps() {

	}

	private static List<java.beans.PropertyDescriptor> getBeanPropertyDescriptor(
			final Object bean) throws IntrospectionException {
		// Oracle bug 4275879: Introspector does not consider superinterfaces of
		// an interface
		if (bean.getClass().isInterface()) {
			List<PropertyDescriptor> propertyDescriptors = new ArrayList<PropertyDescriptor>();

			for (Class<?> cls : bean.getClass().getInterfaces()) {
				propertyDescriptors.addAll(getBeanPropertyDescriptor(cls));
			}

			BeanInfo info = Introspector.getBeanInfo(bean.getClass());
			propertyDescriptors.addAll(Arrays.asList(info
					.getPropertyDescriptors()));

			return propertyDescriptors;
		} else {
			BeanInfo info = Introspector.getBeanInfo(bean.getClass());
			return Arrays.asList(info.getPropertyDescriptors());
		}
	}

	private static LinkedHashMap<String, GWTPropertyDescriptor> getPropertyDescriptors(
			Object bean) {
		final LinkedHashMap<String, GWTPropertyDescriptor> pdMap = new LinkedHashMap<String, GWTPropertyDescriptor>();

		try {
			List<java.beans.PropertyDescriptor> propertyDescriptors = getBeanPropertyDescriptor(bean);

			// Add all the bean properties as MethodProperties to this Item
			// later entries on the list overwrite earlier ones
			for (java.beans.PropertyDescriptor pd : propertyDescriptors) {
				final Method getMethod = pd.getReadMethod();
				if ((getMethod != null)
						&& getMethod.getDeclaringClass() != Object.class) {
					GWTPropertyDescriptor propertyDescriptor = new MethodPropertyDescriptor(
							pd.getName(), pd.getPropertyType(),
							pd.getReadMethod(), pd.getWriteMethod());
					pdMap.put(pd.getName(), propertyDescriptor);
				}
			}
		} catch (final java.beans.IntrospectionException ignored) {
		}

		return pdMap;
	}

	public boolean addItemPropertyX(Object id, Property property) {

		// Null ids are not accepted
		if (id == null) {
			throw new NullPointerException("Item property id can not be null");
		}

		// Cant add a property twice
		if (map.containsKey(id)) {
			return false;
		}

		// Put the property to map
		map.put(id, property);
		list.add(id);

		return true;
	}

	private void addItemProperty(String name, Property createProperty) {

		boolean addPropBool = addItemPropertyX(name, createProperty);

		if (addPropBool == true) {

			if (propertyCache == null)
				propertyCache = new HashMap<Property, BuildableObjectProperty>();

			BuildableObjectProperty buildableObjectProperty = propertyCache
					.get(createProperty);
			if (buildableObjectProperty == null) {
				buildableObjectProperty = new BuildableObjectProperty(
						createProperty);
				propertyCache.put(createProperty, buildableObjectProperty);
			}
		}
	}

	public BuildableObjectProperty getItemProperty(Object id) {
		Property property = getItemPropertyX(id);

		if (propertyCache == null)
			propertyCache = new HashMap<Property, BuildableObjectProperty>();

		BuildableObjectProperty buildableObjectProperty = propertyCache
				.get(property);
		if (buildableObjectProperty == null) {
			buildableObjectProperty = new BuildableObjectProperty(property);
			propertyCache.put(property, buildableObjectProperty);
		}
		return buildableObjectProperty;
	}

	private Property getItemPropertyX(Object id) {
		return map.get(id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Field<T> createField(String id) {

		Field field = null;

		if (this.getItemProperty(id) != null) {
			BuildableObjectProperty property = this.getItemProperty(id);
			if (property.getOptions().size() == 0) {
				if (property.isRadio() == false) { 
					Radio rad = new Radio();
					 field=rad;
				} else {
					CheckBox chk =new CheckBox();
					field=chk;
				}
			} else {

				if (property.getType() == Date.class) {
					 DateField dateField = new  DateField();
 					field = dateField;
				} else if (property.isRichText()) {
					TextField richTextArea = new TextField();
					field = richTextArea;
				} else {
					TextField textField = new TextField();
					field = textField;
				}
			}

			if (field != null) {

			}
		}

		return field;
	}

}
