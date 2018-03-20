package com.opencosmos.automation.apidoc.info;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public abstract class AnalysisContext implements EntityInformationFactory, AnalysisResult {

	@Override
	public abstract String getEnumFriendlyName(Enum<?> value);

	private final Map<Class<?>, ClassInformation> classCache = new HashMap<>();
	private final Map<Class<? extends Enum<?>>, EnumInformation> enumCache = new HashMap<>();
	private final Map<Enum<?>, EnumValueInformation> enumValueCache = new HashMap<>();
	private final Map<Class<?>, PrimitiveInformation> primitiveCache = new HashMap<>();
	private final Map<Field, FieldInformation> fieldCache = new HashMap<>();

	@Override
	public Collection<ClassInformation> getClassCache() {
		return classCache.values();
	}

	@Override
	public Collection<EnumInformation> getEnumCache() {
		return enumCache.values();
	}

	@Override
	public Collection<EnumValueInformation> getEnumValueCache() {
		return enumValueCache.values();
	}

	@Override
	public Collection<PrimitiveInformation> getPrimitiveCache() {
		return primitiveCache.values();
	}

	@Override
	public Collection<FieldInformation> getFieldCache() {
		return fieldCache.values();
	}

	public <K, V> V get(Map<K, V> cache, K key, BiFunction<AnalysisContext, K, V> constructor) {
		return cache.computeIfAbsent(key, k -> constructor.apply(this, k));
	}

	@Override
	public ClassInformation getClassInfo(Class<?> type) {
		return get(classCache, type, ClassInformation::new);
	}

	@Override
	public EnumInformation getEnumInfo(Class<? extends Enum<?>> type) {
		return get(enumCache, type, EnumInformation::new);
	}

	@Override
	public EnumValueInformation getEnumValueInfo(Enum<?> value) {
		return get(enumValueCache, value, EnumValueInformation::new);
	}

	@Override
	public PrimitiveInformation getPrimitiveInfo(Class<?> type) {
		return get(primitiveCache, type, PrimitiveInformation::new);
	}

	@Override
	public FieldInformation getFieldInfo(Field field) {
		return get(fieldCache, field, FieldInformation::new);
	}

	@Override
	@SuppressWarnings("unchecked")
	public AbstractTypeInformation getTypeInfo(Field field) {
		Class<?> type = field.getType();
		if (type.isArray()) {
			type = type.getComponentType();
		}
		if (Collection.class.isAssignableFrom(type)) {
			type = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
		}
		if (type.isPrimitive() || type.equals(String.class)) {
			return getPrimitiveInfo(type);
		} else if (type.isEnum()) {
			return getEnumInfo((Class<? extends Enum<?>>) type);
		} else {
			return getClassInfo(type);
		}
	}

}
