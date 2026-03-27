package com.terrase.frame.jsf.bean.system;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.terrase.frame.data.Module;
import com.terrase.frame.data.User;
import com.terrase.frame.data.UserPreference;
import com.terrase.frame.enumerator.EnumSystem;
import com.terrase.frame.jsf.bean.FrameController;
import com.terrase.frame.service.CompanyService;
import com.terrase.frame.service.ConfigurationService;
import com.terrase.frame.service.UserPreferenceService;
import com.terrase.frame.service.UserService;
import com.terrase.util.EncryptionUtil;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean(name = "sessionBean", eager = true)
@SessionScoped
public class SessionBean extends FrameController {
	private static final long serialVersionUID = 1L;

	protected String project;

	protected User user;
	protected boolean authenticated;

	private String username;
	private String password;
	private String newPassword;
	private String confirmPassword;

	private EnumSystem[] systems = { EnumSystem.ERP, EnumSystem.WMS };
	private EnumSystem system;

	private List<Module> modules;

	private Map<String, Serializable> sessionObjects;
	private UserPreference userPreference;

	@Autowired
	@Setter(AccessLevel.NONE)
	private transient UserService userSvc;

	@Autowired
	@Setter(AccessLevel.NONE)
	private transient UserPreferenceService userPreferenceSvc;

	@Autowired
	@Setter(AccessLevel.NONE)
	private transient CompanyService companySvc;

	@Autowired
	@Setter(AccessLevel.NONE)
	private transient ConfigurationService configSvc;

	public String getSystemNamespace() {
		return system.name().toLowerCase();
	}

	@Override
	@PostConstruct
	public void init() {
		super.init();

		project = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("project");

		user = new User();
		authenticated = false;
		modules = Collections.emptyList();

		sessionObjects = new LinkedHashMap<>();
		userPreference = new UserPreference();
	}

	public void resetInput() {
		username = null;
		password = null;
		newPassword = null;
		confirmPassword = null;
		user.setPassword(null);
	}

	public String login() {
		try {
			User user = userSvc.findByUsername(username);

			if (user != null && !user.isDeleted() && user.isActive()) {
				String encodedPassword = EncryptionUtil.encrypt("terrase", password);

				if (user.getPassword().equals(encodedPassword)) {
					this.user = user;
					authenticated = true;

					userPreference = userPreferenceSvc.findFirst();
				}
			}

			if (!authenticated) {
				addWarningMessage("Notification", "Invalid Username or Password");
			} else {
				modules = Collections.unmodifiableList(moduleSvc.find());
				addInfoMessage("Notification", "Welcome");
				return getSystemNamespace().concat("-home");
			}
		} catch (Throwable t) {
			addErrorMessage(t);
		} finally {
			resetInput();
		}

		return null;
	}

	public String logout() {
		this.init();
		authenticated = false;
		return "login";
	}

	public void changePassword() {
		try {
			String encodedPassword = EncryptionUtil.encrypt("terrase", password);
			user = userSvc.find(user.getId());

			if (user.getPassword().equals(encodedPassword)) {
				if (newPassword.equals(confirmPassword)) {
					String newEncodedPassword = EncryptionUtil.encrypt("terrase", newPassword);
					user.setPassword(newEncodedPassword);
					userSvc.update(user, user);
					addInfoMessage("Notification", "Password successfully changed");
				} else {
					addWarningMessage("Notification", "New password does not match with confirmation");
				}
			} else {
				addWarningMessage("Notification", "Invalid password");
			}
		} catch (Throwable t) {
			addErrorMessage(t);
		} finally {
			resetInput();
		}
	}

	public boolean authorize(String moduleName, String system) {
		return authorize(user, moduleName, EnumSystem.valueOf(system));
	}

	@Override
	protected boolean authorize(User user, String moduleName, EnumSystem system) {
		try {
			Module module = findModuleByName(moduleName, system);
			return authenticate(user, module, OPERATION_VIEW);
		} catch (Throwable t) {
			return false;
		}
	}

	public boolean authenticate(Module module, int operation) {
		return authenticate(user, module, operation);
	}

	public void cancel() {
		resetInput();
	}

	public Module findModuleByName(String moduleName, EnumSystem system) {
		for (Module module : modules) {
			if (StringUtils.equalsIgnoreCase(module.getName(), moduleName) && module.getSystem() == system) {
				return module;
			}
		}
		return null;
	}

	public Module findModuleByCode(String moduleCode, EnumSystem system) {
		for (Module module : modules) {
			if (StringUtils.equalsIgnoreCase(module.getCode(), moduleCode) && module.getSystem() == system) {
				return module;
			}
		}
		return null;
	}

	public void savePreference() {
		try {
			userPreferenceSvc.update(userPreference, user);
			userPreference = userPreferenceSvc.find(userPreference.getId());
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}

	public void setLayoutPrimaryColor(String layoutPrimaryColor) {
		try {
			userPreference.setLayoutPrimaryColor(layoutPrimaryColor);
			savePreference();
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}
}
