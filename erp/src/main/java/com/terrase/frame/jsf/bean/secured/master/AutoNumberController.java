package com.terrase.frame.jsf.bean.secured.master;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.terrase.frame.data.AutoNumber;
import com.terrase.frame.data.User;
import com.terrase.frame.enumerator.EnumAutoNumberReset;
import com.terrase.frame.jsf.bean.system.AuthenticatedBean;
import com.terrase.frame.model.LazyAutoNumber;
import com.terrase.frame.service.AutoNumberService;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean(name = "autoNumberController")
@ViewScoped
public class AutoNumberController extends AuthenticatedBean {
	private static final long serialVersionUID = 1L;

	public static final String NAVIGATION_SPACE = "auto-number";
	public static final String MODULE_NAME = "Auto Number";

	private LazyDataModel<AutoNumber> objects;
	private AutoNumber object;

	private EnumAutoNumberReset autoNumberResets[] = EnumAutoNumberReset.values();

	@Autowired
	@Setter(AccessLevel.NONE)
	private transient AutoNumberService classSvc;

	@Override
	@PostConstruct
	public void init() {
		try {
			super.init();

			module = sessionBean.findModuleByName(MODULE_NAME);
			title = MODULE_NAME;
			page = NAVI_INDEX;

			objects = new LazyAutoNumber();
			object = new AutoNumber();

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

	public void detail(long id) {
		try {
			if (authenticate(module, OPERATION_VIEW)) {
				object = classSvc.find(id);

				page = NAVI_DETAIL;
			} else {
				addWarningMessage("Notification", "You do not have permission to access this operation");
			}
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}

	public void insert() {
		try {
			if (authenticate(module, OPERATION_INSERT)) {
				object = new AutoNumber();

				page = NAVI_INSERT;
			} else {
				addWarningMessage("Notification", "You do not have permission to access this operation");
			}
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}

	public void saveInsert() {
		try {
			classSvc.insert(object, (User) sessionBean.getUser().clone());
			addInfoMessage("Notification", "Record successfully created");

			insert();
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}

	public void update(long id) {
		try {
			if (authenticate(module, OPERATION_UPDATE)) {
				object = classSvc.find(id);

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

	public void delete(long id) {
		try {
			if (authenticate(module, OPERATION_DELETE)) {
				object = classSvc.find(id);

				page = NAVI_DELETE;
			} else {
				addWarningMessage("Notification", "You do not have permission to access this operation");
			}
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}

	public void saveDelete() {
		try {
			classSvc.delete(object, (User) sessionBean.getUser().clone());
			addInfoMessage("Notification", "Record successfully deleted");

			init();
			page = NAVI_INDEX;
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}
}
