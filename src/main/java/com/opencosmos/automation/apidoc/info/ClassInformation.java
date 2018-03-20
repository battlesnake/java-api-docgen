package com.opencosmos.automation.apidoc.info;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class ClassInformation extends AbstractTypeInformation {

	private final Set<FieldInformation> fields = new HashSet<>();

	public Set<FieldInformation> getFields() {
		return fields;
	}

	public ClassInformation(EntityInformationFactory factory, Class<?> type) {
		super(type.getSimpleName(), type.getCanonicalName(), Utility.getDescription(type));
		for (Field field : type.getDeclaredFields()) {
			fields.add(factory.getFieldInfo(field));
		}
	}

}
