package com.bwizard.cegame.validation;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;

import com.bwizard.cegame.logs.LogInfo;
import com.bwizard.cegame.logs.LogMonitor;
import com.bwizard.cegame.validation.interfaces.IValidField;

public class BaseValidator {
	
	private static final LogInfo logInfo = new LogInfo(BaseValidator.class);
	
	private HashSet<String> nameFields = null;
	private HashSet<String> valueFields = null;
	
	public BaseValidator() {
		nameFields = new HashSet<String>();
		valueFields = new HashSet<String>();
	}
	
	public void validateFields(List<IValidField> listFieldsToCheck) throws IllegalArgumentException, IllegalAccessException {
		
		Object obj = null;
		String nameField;
		String valueField;

		int i = 0;
		while(i < listFieldsToCheck.size()) {
			
			obj = listFieldsToCheck.get(i);
			for (Field field : obj.getClass().getDeclaredFields()) {
				nameField = field.getName();
				valueField = field.get(obj).toString();
				if (!nameFields.contains(nameField) && !valueFields.contains(valueField)) {
					nameFields.add(nameField);
					valueFields.add(valueField);
				} else {
					String info = "Element " + nameField + " = \"" + valueField  + "\" from " + obj + " duplicated.";
					LogMonitor.buildInfo(logInfo, info);
					throw new AssertionError(info);
				}
			}
			i++;
		}
	
	}

}
