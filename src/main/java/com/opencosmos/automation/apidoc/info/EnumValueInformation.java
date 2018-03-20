package com.opencosmos.automation.apidoc.info;

public class EnumValueInformation extends AbstractEntityInformation {

	public EnumValueInformation(EntityInformationFactory factory, Enum<?> value) {
		super(factory.getEnumFriendlyName(value), value.name(), Utility.getDescription(value));
	}

}
