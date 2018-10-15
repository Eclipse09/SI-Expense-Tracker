package com.bsplat.system.web;

public class Theme {
	private String userStyle;

	private String imgRoot;

	public String getUserStyle() {
		return userStyle;
	}

	public void setUserStyle(String userStyle) {
		this.userStyle = userStyle;
	}

	public String getImgRoot() {
		return imgRoot;
	}

	public void setImgRoot(String imgRoot) {
		this.imgRoot = imgRoot;
	}

	public String getCssRoot() {
		return cssRoot;
	}

	public void setCssRoot(String cssRoot) {
		this.cssRoot = cssRoot;
	}

	private String cssRoot;
}
