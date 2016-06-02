package com.belhard.utils;

public final class StringUtils {

	public static final String EMPTY_STR = "";

	private StringUtils() {
	}

	public static String textFieldValidator(String fieldValue) {
		if (isEmpty(fieldValue))
			fieldValue += " - Incorect value!";
		return fieldValue;
	}

	public static String ageFieldValidator(String ageValue) {
		if (isNotEmpty(ageValue))
			if (Integer.parseInt(ageValue) < 1 || Integer.parseInt(ageValue) > 120)
				ageValue = " - Incorect age!";
		return ageValue;
	}

	public static String listFieldValidator(String listValue) {
		if (listValue == null)
			listValue = "Incorect value! Choose something!";
		return listValue;
	}

	
	public static String infoListFieldValidator(String[] courseInfo) {
		String tmpStr = "";
		if (courseInfo != null) {
			for (int i = 0; i < courseInfo.length; i++) {
				tmpStr += courseInfo[i] + "; ";
			}
		}
		else
			tmpStr = "Choose some variant!";
		
		return tmpStr;
	}
	
	public static String themesToString(String[] themes) {
		String tmpStr = "";
		if (themes != null) {
			for (int i = 0; i < themes.length; i++) {
				tmpStr += "\""+themes[i]+"\", \r\n";
			}
		}
		else
			tmpStr = "Choose some variant!";
		return tmpStr;
	}

	public static String additionalTextValidator(String otherValue, String findValue) {
		if (findValue != null) {
			if (findValue.contains("Other")){
				if (isEmpty(otherValue))
					otherValue = "Incorect value! Choose something!";
				else
					return otherValue;
			}
			else
				otherValue = "other option wasn't selected";
		}

		return otherValue;
	}
	
	public static boolean isEmpty(String str) {
		if (str != null) {
			return EMPTY_STR.equals(str);
		}

		return true;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isBlank(String str) {
		if (isNotEmpty(str)) {
			return EMPTY_STR.equals(str.trim());
		}

		return true;
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	public static String capitalizeFirstLetter(String str) {
		if (StringUtils.isBlank(str)) {
			return str;
		}

		Character firstChar = str.charAt(0);
		Character firstCapitalChar = Character.toUpperCase(firstChar);

		String result = firstCapitalChar + str.substring(1);

		return result;
	}

}
