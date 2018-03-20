package com.opencosmos.automation.apidoc;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MarkdownGenerator {

	private final ClassForestInformation info;

	public MarkdownGenerator(ClassForestInformation info) {
		this.info = info;
	}

	private static String getDescription(Class<?> type) {
		Description annot = type.getAnnotation(Description.class);
		if (annot == null) {
			throw new Error("Failed to get description for class " + type.getCanonicalName());
		}
		return annot.value();
	}

	private static String getDescription(Class<?> type, Field field) {
		Description annot = field.getAnnotation(Description.class);
		if (annot == null) {
			throw new Error(
					"Failed to get description for field " + field.getName() + " of type " + type.getCanonicalName());
		}
		return annot.value();
	}

	public List<String> generate(String title, Class<?> topLevel) {
		List<String> out = new ArrayList<>();
		out.add("# " + title);
		out.add("Top level class: " + topLevel.getSimpleName());
		for (Map.Entry<Class<?>, Set<Field>> kv : info.data.entrySet().stream()
				.sorted((a, b) -> a.getKey().getSimpleName().compareToIgnoreCase(b.getKey().getSimpleName()))
				.collect(Collectors.toList())) {
			Class<?> type = kv.getKey();
			out.add("");
			out.add(String.format("# %s", type.getSimpleName()));
			out.add(getDescription(type));
			if (kv.getValue().isEmpty()) {
				continue;
			}
			out.add("Members:");
			for (Field field : kv.getValue().stream().sorted((a, b) -> a.getName().compareToIgnoreCase(b.getName()))
					.collect(Collectors.toList())) {
				out.add(String.format(" * %s : %s - %s", field.getName(), field.getType().getSimpleName(),
						getDescription(type, field)));
			}
		}
		out.add("");
		return out;
	}

}
