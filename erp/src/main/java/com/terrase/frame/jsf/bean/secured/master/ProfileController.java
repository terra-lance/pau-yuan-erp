package com.terrase.frame.jsf.bean.secured.master;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.terrase.frame.data.Profile;
import com.terrase.frame.data.User;
import com.terrase.frame.jsf.bean.system.AuthenticatedBean;
import com.terrase.frame.model.LazyProfile;
import com.terrase.frame.service.ProfileService;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean(name = "profileController")
@ViewScoped
public class ProfileController extends AuthenticatedBean {
	private static final long serialVersionUID = 1L;

	public static final String NAVIGATION_SPACE = "profile";
	public static final String MODULE_NAME = "Profile";

	private LazyDataModel<Profile> objects;
	private Profile object;

	@Autowired
	@Setter(AccessLevel.NONE)
	private transient ProfileService classSvc;

	@Override
	@PostConstruct
	public void init() {
		try {
			super.init();

			module = sessionBean.findModuleByName(MODULE_NAME);
			title = MODULE_NAME;
			page = NAVI_INDEX;

			objects = new LazyProfile();
			object = new Profile();

			super.endInit();
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}

	public String index() {
		try {
			if (authenticate(module, OPERATION_VIEW)) {
				object = classSvc.findFirst();

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

			object = classSvc.findFirst();
		} catch (Throwable t) {
			addErrorMessage(t.getClass().getName(), t.getMessage());
		}
	}

	public void update() {
		try {
			if (authenticate(module, OPERATION_UPDATE)) {
				object = classSvc.findFirst();

				page = NAVI_UPDATE;
			} else {
				addWarningMessage("Notification", "You do not have permission to access this operation");
			}
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}

	public void saveUpdate() {
		try {
			classSvc.update(object, (User) sessionBean.getUser().clone());
			addInfoMessage("Notification", "Record successfully updated");

			init();
			page = NAVI_INDEX;
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}
}
