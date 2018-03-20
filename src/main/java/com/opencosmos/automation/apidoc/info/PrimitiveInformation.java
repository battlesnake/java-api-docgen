package com.opencosmos.automation.apidoc.info;

public class PrimitiveInformation extends AbstractTypeInformation {

	public PrimitiveInformation(EntityInformationFactory factory, Class<?> type) {
		super(type.getSimpleName(), type.getCanonicalName(), "Built-in type");
	}

}
