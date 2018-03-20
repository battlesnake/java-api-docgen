package com.opencosmos.automation.apidoc.info;

import java.lang.reflect.Field;
import java.util.Collection;

public class FieldInformation extends AbstractEntityInformation {

	private final AbstractTypeInformation typeInfo;
	private final boolean isPlural;

	public AbstractTypeInformation getTypeInfo() {
		return typeInfo;
	}

	public boolean isPlural() {
		return isPlural;
	}

	public FieldInformation(EntityInformationFactory factory, Field field) {
		super(field.getName(), field.getName(), Utility.getDescription(field));
		isPlural = field.getType().isArray() || Collection.class.isAssignableFrom(field.getType());
		typeInfo = factory.getTypeInfo(field);
	}

}
