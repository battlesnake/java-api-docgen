package com.opencosmos.automation.apidoc;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/** Annotation for describing a class/field/enum/enum-value */
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {

	public String value();

}
