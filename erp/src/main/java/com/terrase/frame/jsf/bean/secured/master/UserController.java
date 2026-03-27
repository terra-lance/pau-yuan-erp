package com.terrase.frame.jsf.bean.secured.master;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.terrase.frame.data.User;
import com.terrase.frame.data.UserGroup;
import com.terrase.frame.data.UserRole;
import com.terrase.frame.enumerator.EnumSystem;
import com.terrase.frame.jsf.bean.system.AuthenticatedBean;
import com.terrase.frame.model.LazyUser;
import com.terrase.frame.service.UserGroupService;
import com.terrase.frame.service.UserRoleService;
import com.terrase.frame.service.UserService;
import com.terrase.frame.util.EntityUtil;
import com.terrase.util.EncryptionUtil;
import com.terrase.util.StringUtil;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean(name = "userController")
@ViewScoped
public class UserController extends AuthenticatedBean {
	private static final long serialVersionUID = 1L;

	public static final EnumSystem SYSTEM = EnumSystem.COMMON;
	public static final String NAVIGATION_SPACE = "user";
	public static final String MODULE_NAME = "User";

	public static final int NAVI_RESET = 5;

	public int getNaviReset() {
		return NAVI_RESET;
	}

	private LazyDataModel<User> objects;
	private List<User> selectedObjects;
	private User object;
	private String confirmPassword;

	private DualListModel<UserGroup> userGroups;

	private String resetPassword;
	private String confirmResetPassword;

	@Autowired
	@Setter(AccessLevel.NONE)
	private transient UserService classSvc;

	// UI
	private String keyword;

	@Autowired
	@Setter(AccessLevel.NONE)
	private transient UserGroupService userGroupSvc;

	@Autowired
	@Setter(AccessLevel.NONE)
	private transient UserRoleService userRoleSvc;

	@Override
	@PostConstruct
	public void init() {
		try {
			super.init();

			module = sessionBean.findModuleByName(MODULE_NAME, SYSTEM);
			title = MODULE_NAME;

			page = NAVI_INDEX;

			loadRecord();
			object = new User();

			userGroups = new DualListModel<>();

			super.endInit();
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}

	public void loadRecord() {
		try {
			HashMap<String, Object> predicates = new LinkedHashMap<>();
			predicates.put("o.deleted = :param1", false);

			if (!StringUtil.isEmpty(keyword)) {
				String condition = "(o.username like :param2 " + "or o.name like :param3 " + "or o.email like :param4)";
				predicates.put(condition, "%" + keyword + "%");
				predicates.put("1 = 1", "%" + keyword + "%");
				predicates.put("2 = 2", "%" + keyword + "%");
			}

			objects = new LazyUser(predicates);
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
				object = new User();

				List<UserGroup> userGroupSource = userGroupSvc.find();
				List<UserGroup> userGroupTarget = new ArrayList<>();
				userGroups = new DualListModel<UserGroup>(userGroupSource, userGroupTarget);

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
			if (StringUtil.isEmpty(object.getPassword())) {
				addWarningMessage("Notification", "Please insert password");
				return;
			}

			if (!object.getPassword().equals(confirmPassword)) {
				addWarningMessage("Notification", "Password does not match with confirmation");
				return;
			}

			String newEncodedPassword = EncryptionUtil.encrypt("terrase", object.getPassword());
			object.setPassword(newEncodedPassword.trim());

			List<UserGroup> userGroupSelected = getUserGroups().getTarget();

			for (UserGroup userGroup : userGroupSelected) {
				UserRole userRole = new UserRole();
				userRole.setUser(object);
				userRole.setUserGroup(userGroup);
				EntityUtil.markInsert(userRole, (User) sessionBean.getUser().clone());
				object.getUserRoles().add(userRole);
			}

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

				List<UserGroup> userSource = userGroupSvc.find();
				List<UserGroup> userTarget = userGroupSvc.findSelected(object.getId());
				userSource.removeAll(userTarget);
				userGroups = new DualListModel<UserGroup>(userSource, userTarget);

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
			object.getUserRoles().clear();

			List<UserGroup> userGroupSelected = getUserGroups().getTarget();

			for (UserGroup userGroup : userGroupSelected) {
				UserRole userRole = new UserRole();
				userRole.setUser(object);
				userRole.setUserGroup(userGroup);
				EntityUtil.markInsert(userRole, (User) sessionBean.getUser().clone());
				object.getUserRoles().add(userRole);
			}

			classSvc.update(object, (User) sessionBean.getUser().clone());
			addInfoMessage("Notification", "Record successfully updated");

			if (object.getId() == sessionBean.getUser().getId()) {
				sessionBean.setUser(object);
			}

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
				classSvc.delete(object, (User) sessionBean.getUser().clone());
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

	public void reset(long id) {
		try {
			if (authenticate(module, OPERATION_UPDATE)) {
				object = classSvc.find(id);
				setResetPassword(null);
				setConfirmResetPassword(null);

				page = NAVI_RESET;
			} else {
				addWarningMessage("Notification", "You do not have permission to access this operation");
			}
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}

	public void reset() {
		try {
			if (selectedObjects.size() == 0) {
				addWarningMessage("Notification", "Please select record");
				return;
			}

			if (selectedObjects.size() > 1) {
				addWarningMessage("Notification", "Please select only one record");
				return;
			}

			reset(selectedObjects.get(0).getId());
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}

	public void saveReset() {
		try {
			if (StringUtil.isEmpty(resetPassword)) {
				addWarningMessage("Notification", "Please enter a password");
				return;
			}

			if (StringUtil.isEmpty(confirmResetPassword) || !StringUtil.equals(resetPassword, confirmResetPassword)) {
				addWarningMessage("Notification", "Confirmation password does not match with password");
				return;
			}

			String encodedPassword = EncryptionUtil.encrypt("terrase", resetPassword);
			object.setPassword(encodedPassword.trim());
			classSvc.update(object, (User) sessionBean.getUser().clone());
			addInfoMessage("Notification", "User password successfully reset");

			init();
			page = NAVI_INDEX;
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}
}
