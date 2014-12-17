package com.prj.util;

public enum AccountCharacter {
	ANY("Any",0),
	ADMINISTRATOR("Administrator",1),
	TEACHER("Teacher",2),
	STUDENT("Student",3);
	
	private String label;
	private Integer code;
	
	AccountCharacter(String label, Integer code) {
		this.setLabel(label);
		this.setCode(code);
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getLabel() {
		return label;
	}
}