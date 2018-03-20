package com.opencosmos.automation.apidoc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.opencosmos.automation.apidoc.info.AnalysisResult;
import com.opencosmos.automation.apidoc.info.ClassInformation;
import com.opencosmos.automation.apidoc.info.EnumInformation;
import com.opencosmos.automation.apidoc.info.EnumValueInformation;
import com.opencosmos.automation.apidoc.info.FieldInformation;

public class MarkdownGenerator {

	private final AnalysisResult info;

	public MarkdownGenerator(AnalysisResult info) {
		this.info = info;
	}

	public List<String> generate(String title, Class<?> topLevel) {
		List<String> out = new ArrayList<>();
		out.add("# " + title);
		out.add("Top level class: " + topLevel.getSimpleName());
		out.add("");
		out.add("# Classes");
		for (ClassInformation classInfo : info.getClassCache().stream()
				.sorted((a, b) -> a.getFriendlyName().compareToIgnoreCase(b.getFriendlyName()))
				.collect(Collectors.toList())) {
			out.add("");
			out.add(String.format("## %s (%s)", classInfo.getFriendlyName(), classInfo.getFullName()));
			out.add(classInfo.getDescription());
			if (classInfo.getFields().isEmpty()) {
				continue;
			}
			out.add("Members:");
			for (FieldInformation field : classInfo.getFields().stream()
					.sorted((a, b) -> a.getFriendlyName().compareToIgnoreCase(b.getFriendlyName()))
					.collect(Collectors.toList())) {
				out.add(String.format(" * %s: %s - %s", field.getFriendlyName(), field.getTypeInfo().getFriendlyName(),
						field.getDescription()));
			}
		}
		out.add("");
		out.add("# Enumerations");
		for (EnumInformation enumInfo : info.getEnumCache().stream()
				.sorted((a, b) -> a.getFriendlyName().compareToIgnoreCase(b.getFriendlyName()))
				.collect(Collectors.toList())) {
			out.add("");
			out.add(String.format("## %s (%s)", enumInfo.getFriendlyName(), enumInfo.getFullName()));
			out.add(enumInfo.getDescription());
			out.add("Values:");
			for (EnumValueInformation value : enumInfo.getValueInfo().stream()
					.sorted((a, b) -> a.getFriendlyName().compareToIgnoreCase(b.getFriendlyName()))
					.collect(Collectors.toList())) {
				out.add(String.format(" * %s (internally: %s) - %s", value.getFriendlyName(), value.getFullName(),
						value.getDescription()));
			}
		}
		out.add("");
		return out;
	}

}
