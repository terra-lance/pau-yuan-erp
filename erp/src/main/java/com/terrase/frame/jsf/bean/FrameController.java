package com.terrase.frame.jsf.bean;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.terrase.frame.data.Configuration;
import com.terrase.frame.data.Module;
import com.terrase.frame.data.User;
import com.terrase.frame.data.UserGroupAccess;
import com.terrase.frame.data.UserRole;
import com.terrase.frame.service.ConfigurationService;
import com.terrase.frame.service.ModuleService;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FrameController extends Controller {
	private static final long serialVersionUID = 1L;

	private static final String[] decimalMasks = { "#0", "#0.0", "#0.00", "#0.000", "#0.0000" };

	protected Configuration configuration;

	public static final int OPERATION_VIEW = 0;
	public static final int OPERATION_INSERT = 1;
	public static final int OPERATION_UPDATE = 2;
	public static final int OPERATION_DELETE = 3;
	public static final int OPERATION_PRINT = 4;
	public static final int OPERATION_POST = 5;

	public static final int NAVI_INDEX = 0;
	public static final int NAVI_DETAIL = 1;
	public static final int NAVI_INSERT = 2;
	public static final int NAVI_UPDATE = 3;
	public static final int NAVI_DELETE = 4;

	public int getNaviIndex() {
		return NAVI_INDEX;
	}

	public int getNaviDetail() {
		return NAVI_DETAIL;
	}

	public int getNaviInsert() {
		return NAVI_INSERT;
	}

	public int getNaviUpdate() {
		return NAVI_UPDATE;
	}

	public int getNaviDelete() {
		return NAVI_DELETE;
	}

	public static final String INDEX_PAGE = "-index";
	public static final String DETAIL_PAGE = "-detail";
	public static final String INSERT_PAGE = "-insert";
	public static final String UPDATE_PAGE = "-update";
	public static final String DELETE_PAGE = "-delete";

	public static final String CONTENT_CSV_FILE = "text/csv";
	public static final String CONTENT_XLS_FILE = "application/vnd.ms-excel";
	public static final String CONTENT_XLSX_FILE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	public static final String CONTENT_PDF_FILE = "application/pdf";

	protected String title;
	protected Module module;
	protected int page;

	@Autowired
	@Setter(AccessLevel.NONE)
	private transient ConfigurationService configSvc;

	@Autowired
	@Setter(AccessLevel.NONE)
	protected transient ModuleService moduleSvc;

	@PostConstruct
	public void init() {
		configuration = configSvc.findFirst();
	}

	protected boolean authorize(User user, String moduleName) {
		try {
			Module module = moduleSvc.findByName(moduleName);

			return authenticate(user, module, OPERATION_VIEW);
		} catch (Throwable t) {
			return false;
		}
	}

	protected boolean authenticate(User user, Module module, int operation) {
		try {
			if (user == null || module == null) {
				return false;
			}

			for (UserRole userRole : user.getUserRoles()) {
				for (UserGroupAccess access : userRole.getUserGroup().getUserGroupAccesses()) {
					if (access.getModule() == null || access.getModule().getId() != module.getId()) {
						continue;
					}

					switch (operation) {
					case OPERATION_VIEW:
						if (access.isViewRights())
							return access.isViewRights();
						break;

					case OPERATION_INSERT:
						if (access.isAddRights())
							return access.isAddRights();
						break;

					case OPERATION_UPDATE:
						if (access.isUpdateRights())
							return access.isUpdateRights();
						break;

					case OPERATION_DELETE:
						if (access.isDeleteRights())
							return access.isDeleteRights();
						break;

					case OPERATION_PRINT:
						if (access.isPrintRights())
							return access.isPrintRights();
						break;

					case OPERATION_POST:
						if (access.isPostRights())
							return access.isPostRights();
						break;
					}
				}
			}

			return false;
		} catch (Throwable t) {
			getLogger().error(t.getMessage(), t);
			return false;
		}
	}

	public String getQuantityDecimalMask() {
		return decimalMasks[configuration.getQuantityDecimal()];
	}

	public String getMonetaryDecimalMask() {
		return decimalMasks[configuration.getMonetaryDecimal()];
	}
}
