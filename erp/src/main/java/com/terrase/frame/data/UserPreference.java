package com.terrase.frame.data;

import com.terrase.frame.data.base.Entity;
import com.terrase.frame.enumerator.EnumPaginatorPosition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPreference extends Entity {
	private static final long serialVersionUID = 1L;

	protected User user;

	// Theme Preference
	protected String menuMode;
	protected String darkMode;
	protected String layoutPrimaryColor;
	protected String componentTheme;
	protected String topbarTheme;
	protected String menuTheme;
	protected String profileMode;
	protected String inputStyle;
	protected boolean lightLogo;

	// UI Preference
	protected EnumPaginatorPosition paginatorPosition;

	public UserPreference() {
		super();

		user = new User();

		menuMode = "layout-static layout-static-active";
		darkMode = "light";
		layoutPrimaryColor = "cyan";
		componentTheme = "cyan";
		topbarTheme = "colored";
		menuTheme = "dim";
		profileMode = "popup";
		inputStyle = "outlined";
		lightLogo = true;

		paginatorPosition = EnumPaginatorPosition.BOTH;
	}

	public void setDarkMode(String darkMode) {
		this.darkMode = darkMode;
		this.menuTheme = darkMode;
		this.lightLogo = !this.topbarTheme.equals("light");
	}

	public String getLayout() {
		return "layout-" + this.layoutPrimaryColor + '-' + this.darkMode;
	}

	public String getTheme() {
		return "rain" + '-' + this.componentTheme + '-' + this.darkMode;
	}

	public void setLayoutPrimaryColor(String layoutPrimaryColor) {
		this.layoutPrimaryColor = layoutPrimaryColor;
		this.componentTheme = layoutPrimaryColor;
	}

	public void setTopbarTheme(String topbarTheme) {
		this.topbarTheme = topbarTheme;
		this.lightLogo = !this.topbarTheme.equals("light");
	}

	public String getInputStyleClass() {
		return this.inputStyle.equals("filled") ? "ui-input-filled" : "";
	}

	public String getReadOnly() {
		return darkMode.equals("light") ? "light-read-only" : "dark-read-only";
	}
}
