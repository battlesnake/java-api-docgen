package com.opencosmos.automation.apidoc.info;

import java.lang.reflect.Field;

public interface EntityInformationFactory {

	public ClassInformation getClassInfo(Class<?> type);

	public EnumInformation getEnumInfo(Class<? extends Enum<?>> type);

	public EnumValueInformation getEnumValueInfo(Enum<?> value);

	public PrimitiveInformation getPrimitiveInfo(Class<?> type);

	public FieldInformation getFieldInfo(Field field);

	public AbstractTypeInformation getTypeInfo(Field field);

	public String getEnumFriendlyName(Enum<?> value);

}