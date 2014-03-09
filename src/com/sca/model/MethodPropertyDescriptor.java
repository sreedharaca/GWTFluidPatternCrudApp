package com.sca.model;

import java.io.IOException;
import java.lang.reflect.Method;

public class MethodPropertyDescriptor implements GWTPropertyDescriptor {

	private final String name;
	private Class<?> propertyType;
	private transient Method readMethod;
	private transient Method writeMethod;

	public MethodPropertyDescriptor(String name, Class<?> propertyType,
			Method readMethod, Method writeMethod) {
		this.name = name;
		this.propertyType = propertyType;
		this.readMethod = readMethod;
		this.writeMethod = writeMethod;
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		SerializerHelper.writeClass(out, propertyType);

		if (writeMethod != null) {
			out.writeObject(writeMethod.getName());
			SerializerHelper.writeClass(out, writeMethod.getDeclaringClass());
			SerializerHelper.writeClassArray(out,
					writeMethod.getParameterTypes());
		} else {
			out.writeObject(null);
			out.writeObject(null);
			out.writeObject(null);
		}

		if (readMethod != null) {
			out.writeObject(readMethod.getName());
			SerializerHelper.writeClass(out, readMethod.getDeclaringClass());
			SerializerHelper.writeClassArray(out,
					readMethod.getParameterTypes());
		} else {
			out.writeObject(null);
			out.writeObject(null);
			out.writeObject(null);
		}
	}

	/* Special serialization to handle method references */
	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		in.defaultReadObject();
		try {
			@SuppressWarnings("unchecked")
			// business assumption; type parameters not checked at runtime
			Class<?> class1 = SerializerHelper.readClass(in);
			propertyType = class1;

			String name = (String) in.readObject();
			Class<?> writeMethodClass = SerializerHelper.readClass(in);
			Class<?>[] paramTypes = SerializerHelper.readClassArray(in);
			if (name != null) {
				writeMethod = writeMethodClass.getMethod(name, paramTypes);
			} else {
				writeMethod = null;
			}

			name = (String) in.readObject();
			Class<?> readMethodClass = SerializerHelper.readClass(in);
			paramTypes = SerializerHelper.readClassArray(in);
			if (name != null) {
				readMethod = readMethodClass.getMethod(name, paramTypes);
			} else {
				readMethod = null;
			}
		} catch (SecurityException e) {
			 
		} catch (NoSuchMethodException e) {
			 
		}
	};

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Class<?> getPropertyType() {
		return propertyType;
	}
 
	
	@Override
	public Property createProperty(Class<?> bean) {
 		return new MethodProperty<Object>(propertyType, bean, readMethod,
				writeMethod);
	}

}
