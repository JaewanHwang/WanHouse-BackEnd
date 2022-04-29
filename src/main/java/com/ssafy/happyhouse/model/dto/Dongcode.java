package com.ssafy.happyhouse.model.dto;

public class Dongcode {
	private String code;
	private String name;

	public Dongcode(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Dongcode [code=" + code + ", name=" + name + "]";
	}

}
