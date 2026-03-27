package com.terrase.frame.jsf.bean.secured.master;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.terrase.frame.data.Module;
import com.terrase.frame.data.User;
import com.terrase.frame.data.UserGroup;
import com.terrase.frame.data.UserGroupAccess;
import com.terrase.frame.enumerator.EnumSystem;
import com.terrase.frame.jsf.bean.system.AuthenticatedBean;
import com.terrase.frame.model.LazyUserGroup;
import com.terrase.frame.service.UserGroupService;
import com.terrase.frame.util.EntityUtil;
import com.terrase.util.StringUtil;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean(name = "userGroupController")
@ViewScoped
public class UserGroupController extends AuthenticatedBean {
	private static final long serialVersionUID = 1L;

	public static final EnumSystem SYSTEM = EnumSystem.COMMON;
	public static final String NAVIGATION_SPACE = "user-group";
	public static final String MODULE_NAME = "User Group";

	private LazyDataModel<UserGroup> objects;
	private List<UserGroup> selectedObjects;
	private UserGroup object;

	// UI
	private String keyword;

	private boolean allView;
	private boolean allAdd;
	private boolean allUpdate;
	private boolean allDelete;
	private boolean allPrint;

	@Autowired
	@Setter(AccessLevel.NONE)
	private transient UserGroupService userGroupSvc;

	@Override
	@PostConstruct
	public void init() {
		try {
			super.init();

			module = sessionBean.findModuleByName(MODULE_NAME, EnumSystem.COMMON);
			title = MODULE_NAME;
			page = NAVI_INDEX;

			loadRecord();
			object = new UserGroup();

			resetFlag();

			super.endInit();
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}

	public void loadRecord() {
		try {
			HashMap<String, Object> predicates = new LinkedHashMap<>();
			if (!StringUtil.isEmpty(keyword)) {
				String condition = "(o.code like :param1 " + "or o.name like :param2 "
						+ "or o.description like :param3)";
				predicates.put(condition, "%" + keyword + "%");
				predicates.put("1 = 1", "%" + keyword + "%");
				predicates.put("2 = 2", "%" + keyword + "%");
			}

			objects = new LazyUserGroup(predicates);
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
				object = userGroupSvc.find(id);

				page = NAVI_DETAIL;
			} else {
				addWarningMessage("Notification", "You do not have permission to access this operation");
			}
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}

	public void detail() {
		try {
			if (selectedObjects.size() == 0) {
				addWarningMessage("Notification", "Please select record");
				return;
			}

			if (selectedObjects.size() > 1) {
				addWarningMessage("Notification", "Please select only one record");
				return;
			}

			detail(selectedObjects.get(0).getId());
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}

	public void insert() {
		try {
			if (authenticate(module, OPERATION_INSERT)) {
				object = new UserGroup();

				Set<UserGroupAccess> accesses = new LinkedHashSet<UserGroupAccess>();

				List<Module> modules = moduleSvc.find();
				for (Module module : modules) {
					UserGroupAccess access = new UserGroupAccess();
					access.setUserGroup(object);
					access.setModule(module);
					EntityUtil.markInsert(access, (User) sessionBean.getUser().clone());
					accesses.add(access);
				}

				object.setAccesses(accesses);
				resetFlag();

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
			userGroupSvc.insert(object, (User) sessionBean.getUser().clone());
			addInfoMessage("Notification", "Record successfully created");

			insert();
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}

	public void update(long id) {
		try {
			if (authenticate(module, OPERATION_UPDATE)) {
				object = userGroupSvc.find(id);

				page = NAVI_UPDATE;
			} else {
				addWarningMessage("Notification", "You do not have permission to access this operation");
			}
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}

	public void update() {
		try {
			if (selectedObjects.size() == 0) {
				addWarningMessage("Notification", "Please select record");
				return;
			}

			if (selectedObjects.size() > 1) {
				addWarningMessage("Notification", "Please select only one record");
				return;
			}

			update(selectedObjects.get(0).getId());
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}

	public void saveUpdate() {
		try {
			userGroupSvc.update(object, (User) sessionBean.getUser().clone());
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
				object = userGroupSvc.find(id);

				page = NAVI_DELETE;
			} else {
				addWarningMessage("Notification", "You do not have permission to access this operation");
			}
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}

	public void delete() {
		try {
			if (selectedObjects.size() == 0) {
				addWarningMessage("Notification", "Please select record");
				return;
			}

			if (selectedObjects.size() > 1) {
				addWarningMessage("Notification", "Please select only one record");
				return;
			}

			delete(selectedObjects.get(0).getId());
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}

	public void saveDelete() {
		try {
			if (authenticate(module, OPERATION_DELETE)) {
				userGroupSvc.delete(object, (User) sessionBean.getUser().clone());
				addInfoMessage("Notification", "Record successfully deleted");

				init();
				page = NAVI_INDEX;
			} else {
				addWarningMessage("Notification", "You do not have permission to access this operation");
			}
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}

	private void resetFlag() {
		allView = false;
		allAdd = false;
		allUpdate = false;
		allDelete = false;
		allPrint = false;
	}

	public void selectAll(int index) {
		for (UserGroupAccess access : object.getAccesses()) {
			switch (index) {
			case 0:
				access.setViewRights(allView);
				break;
			case 1:
				access.setAddRights(allAdd);
				break;
			case 2:
				access.setUpdateRights(allUpdate);
				break;
			case 3:
				access.setDeleteRights(allDelete);
				break;
			case 4:
				access.setPrintRights(allPrint);
				break;
			}
		}
	}
}
