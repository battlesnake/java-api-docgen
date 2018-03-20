package com.opencosmos.automation.apidoc.info;

import java.util.Collection;

public interface AnalysisResult {

	Collection<ClassInformation> getClassCache();

	Collection<EnumInformation> getEnumCache();

	Collection<EnumValueInformation> getEnumValueCache();

	Collection<PrimitiveInformation> getPrimitiveCache();

	Collection<FieldInformation> getFieldCache();

}