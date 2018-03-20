package com.opencosmos.automation.apidoc.info;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.opencosmos.automation.apidoc.Description;

public class Utility {

	public static String getDescription(Field field) {
		Description annot = field.getAnnotation(Description.class);
		if (annot == null) {
			throw new Error("Failed to find Description annotation for " + field.getName());
		}
		return annot.value();
	}

	public static String getDescription(Class<?> type) {
		Description annot = type.getAnnotation(Description.class);
		if (annot == null) {
			throw new Error("Failed to find Description annotation for " + type.getCanonicalName());
		}
		return annot.value();
	}

	public static String getDescription(Enum<?> enumValue) {
		Field field;
		try {
			field = enumValue.getClass().getField(enumValue.name());
			Description annot = field.getAnnotation(Description.class);
			if (annot == null) {
				throw new NoSuchFieldException();
			}
			return annot.value();
		} catch (NoSuchFieldException | SecurityException e) {
			throw new Error("Failed to find Description annotation for " + enumValue.getClass().getCanonicalName() + "."
					+ enumValue.name());
		}
	}

	public static <T extends Annotation> T getEnumAnnotation(Enum<?> value, Class<T> annotation) {
		try {
			T annot = value.getClass().getField(value.name()).getAnnotation(annotation);
			if (annot == null) {
				throw new Error("Failed to find SerializedName annotation for " + value.getClass().getCanonicalName()
						+ "." + value.name());
			}
			return annot;
		} catch (NoSuchFieldException | SecurityException e) {
			throw new Error("Failed to get annotation " + annotation.getCanonicalName() + " on enum value "
					+ value.getClass().getCanonicalName() + "." + value.name());
		}
	}

}
