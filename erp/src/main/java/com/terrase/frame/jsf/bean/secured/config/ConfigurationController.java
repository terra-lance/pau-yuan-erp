package com.terrase.frame.jsf.bean.secured.config;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;

import com.terrase.frame.data.Configuration;
import com.terrase.frame.data.User;
import com.terrase.frame.jsf.bean.system.AuthenticatedBean;
import com.terrase.frame.service.ConfigurationService;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean(name = "configurationController")
@ViewScoped
public class ConfigurationController extends AuthenticatedBean {
	private static final long serialVersionUID = 1L;

	public static final String NAVIGATION_SPACE = "configuration";
	public static final String MODULE_NAME = "Configuration";

	private Configuration object;

	private String[] themes = { "afterdark", "afternoon", "afterwork", "arya", "black-tie", "blitzer", "bluesky",
			"bootstrap", "casablanca", "cruze", "cupertino", "dark-hive", "dot-luv", "eggplant", "excite-bike", "flick",
			"glass-x", "home", "hot-sneaks", "humanity", "le-frog", "midnight", "mint-choc", "overcast",
			"pepper-grinder", "redmond", "rocket", "saga", "sam", "smoothness", "south-street", "start", "sunny",
			"swanky-purse", "trontastic", "ui-darkness", "ui-lightness", "vader", "vela" };

	@Autowired
	@Setter(AccessLevel.NONE)
	private transient ConfigurationService classSvc;

	@Override
	@PostConstruct
	public void init() {
		try {
			super.init();

			module = sessionBean.findModuleByName(MODULE_NAME);
			title = MODULE_NAME;
			page = NAVI_INDEX;

			object = classSvc.findFirst();

			super.endInit();
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}

	public String index() {
		try {
			if (authenticate(module, OPERATION_VIEW)) {
				return NAVIGATION_SPACE.concat(INDEX_PAGE);
			} else {
				addWarningMessage("Notification", "You do not have permission to access this operation");
			}
		} catch (Throwable t) {
			addErrorMessage(t.getClass().getName(), t.getMessage());
		}
		return null;
	}

	public void home() {
		try {
			init();
			page = NAVI_INDEX;
		} catch (Throwable t) {
			addErrorMessage(t.getClass().getName(), t.getMessage());
		}
	}

	public void update() {
		if (authenticate(module, OPERATION_UPDATE)) {
			page = NAVI_UPDATE;
		} else {
			addWarningMessage("Notification", "You do not have permission to access this operation");
		}
	}

	public void saveUpdate() {
		try {
			classSvc.update(object, (User) sessionBean.getUser().clone());
			addInfoMessage("Notification", "Record successfully updated");

			sessionBean.setConfiguration(object);

			page = NAVI_INDEX;
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}
}
