package com.opencosmos.automation.apidoc.info;

import java.util.HashSet;
import java.util.Set;

public class EnumInformation extends AbstractTypeInformation {

	private final Set<EnumValueInformation> valueInfo = new HashSet<>();

	public Set<EnumValueInformation> getValueInfo() {
		return valueInfo;
	}

	public EnumInformation(EntityInformationFactory factory, Class<? extends Enum<?>> type) {
		super(type.getSimpleName(), type.getCanonicalName(), Utility.getDescription(type));
		for (Enum<?> value : type.getEnumConstants()) {
			valueInfo.add(factory.getEnumValueInfo(value));
		}
	}

}
