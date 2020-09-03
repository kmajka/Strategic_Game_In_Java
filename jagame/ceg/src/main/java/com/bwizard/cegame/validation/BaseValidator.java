package com.bwizard.cegame.validation;

import com.bwizard.cegame.logs.LogInfo;
import com.bwizard.cegame.validation.interfaces.IValidField;
import org.apache.commons.lang3.tuple.Triple;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.List;

public class BaseValidator {
	
	private static final LogInfo logInfo = new LogInfo(BaseValidator.class);

	public boolean validateConstantFields(List<IValidField> listFieldsToCheck) {
		boolean res = true;
		Triple<Object, String, String> duplicatedField = getInfoAboutDuplicatedField(listFieldsToCheck);
		if (duplicatedField != null) {
			String txt = String.format("Element %s = %s from %s duplicated.",
					duplicatedField.getMiddle(), duplicatedField.getRight(), duplicatedField.getLeft());
			logInfo.error(txt);
			res = false;
		}
		return res;
	}

	public Triple<Object, String, String> getInfoAboutDuplicatedField(List<IValidField> listFieldsToCheck) {

		Object obj;
		String nameField;
		String valueField;

		HashSet<String> nameFields = new HashSet<>();
		HashSet<String> valueFields = new HashSet<>();

		int i = 0;
		while(i < listFieldsToCheck.size()) {
			
			obj = listFieldsToCheck.get(i);
			for (Field field : obj.getClass().getDeclaredFields()) {
				int modifiers = field.getModifiers();
				if (!Modifier.isStatic(modifiers)) {
					continue;
				}

				nameField = field.getName();
				try {
					valueField = field.get(obj).toString();
					if (!nameFields.contains(nameField) && !valueFields.contains(valueField)) {
						nameFields.add(nameField);
						valueFields.add(valueField);
					} else {
						return Triple.of(obj, nameField, valueField);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					logInfo.error(e.getMessage());
				}
			}
			i++;
		}
		return null;
	}
}
