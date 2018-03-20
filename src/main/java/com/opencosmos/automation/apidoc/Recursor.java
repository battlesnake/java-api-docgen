package com.opencosmos.automation.apidoc;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

public class Recursor {

	private final ClassForestInformation allInfo = new ClassForestInformation();

	public Recursor() {
	}

	private void recurse(Class<?> type) {
		Set<Field> typeInfo = new HashSet<>();
		if (allInfo.data.putIfAbsent(type, typeInfo) != null) {
			return;
		}
		for (Field field : type.getDeclaredFields()) {
			int modifiers = field.getModifiers();
			if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers)) {
				continue;
			}
			typeInfo.add(field);
			Class<?> fieldType = field.getType();
			if (fieldType.isArray()) {
				fieldType = fieldType.getComponentType();
			}
			if (fieldType.isPrimitive() || fieldType.equals(String.class)) {
				continue;
			}
			// if (Collection.class.isAssignableFrom(fieldType)) {
			// fieldType = (Class<?>) ((ParameterizedType)
			// field.getGenericType()).getActualTypeArguments()[0];
			// }
			if (!fieldType.getCanonicalName().startsWith("com.opencosmos.")) {
				throw new Error(String.format("In class %s field %s: type %s not supported", type.getSimpleName(),
						field.getName(), fieldType.getCanonicalName()));
			}
			recurse(fieldType);
		}
	}

	public Recursor analyse(Class<?> type) {
		recurse(type);
		return this;
	}

	public ClassForestInformation get() {
		return allInfo;
	}

}
