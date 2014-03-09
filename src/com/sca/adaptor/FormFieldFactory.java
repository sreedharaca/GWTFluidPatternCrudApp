package com.sca.adaptor;

import com.sencha.gxt.widget.core.client.form.Field;

public interface FormFieldFactory   {
 
	<T> Field<T> createField(String id);
	 
}
