import java.util.HashMap;

public class Spreadsheet {
	HashMap<String, String> mValues = new HashMap<String, String>();
	
	public String get(String cell) {
		// to be implemented
		return null;
	}
	
	public void set(String cell, String value) {
		if (value.length() > 0) {
			if (value.charAt(0) == '=' && value.length() > 1) {
				if (value.charAt(1) == '\'') {
					if (value.charAt(value.length() - 1) == '\'' && value.length() > 2) {
						value = value.substring(2, value.length() - 1);
					} else {
						value = "#Error";
					}
					
				} else if (!(value.charAt(1) < '0' || value.charAt(1) > '9') && value.charAt(1) != '-') {
					// Reference / No-op, return possible errors when
					// the cell is actually evaluated
					
				} else {
					boolean isInteger = true;
					for (int i = 1; i < value.length(); i++) {
						char c = value.charAt(i);
						if (i == 1 && c == '-')
							continue;
						
						if (c < '0' || c > '9') {
							isInteger = false;
							break;
						}
					}
					
					if (isInteger) {
						value = value.substring(1, value.length());
					} else {
						value = "#Error";
					}
				}
			} else if (value.charAt(0) == '\'') {
				if (value.length() > 1 && value.charAt(value.length() - 1) == '\'') {
					value = value.substring(1, value.length() - 1);
				} else {
					value = "#Error";
				}
			} else {
				boolean isInteger = true;
				for (int i = 0; i < value.length(); i++) {
					char c = value.charAt(i);
					if (i == 0 && c == '-')
						continue;
					
					if (c < '0' || c > '9') {
						isInteger = false;
						break;
					}
				}
				
				if (!isInteger)
					value = "#Error";
			}
		}
	
		mValues.put(cell, value);
	}
	
	public String evaluate(String cell) {
		String value = (String) mValues.get(cell);
		
		
		return value;
	}
	
}
