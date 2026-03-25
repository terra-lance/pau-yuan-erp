package com.terrase.frame.jsf.bean.system;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import com.terrase.frame.data.Module;
import com.terrase.frame.enumerator.EnumSystem;
import com.terrase.frame.jsf.bean.FrameController;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AuthenticatedBean extends FrameController {
	private static final long serialVersionUID = 1L;

	public static final int NAVI_GRAPH = 5;

	public int getNaviGraph() {
		return NAVI_GRAPH;
	}

	@ManagedProperty(value = "#{sessionBean}")
	protected SessionBean sessionBean;

	@Override
	@PostConstruct
	public void init() {
		super.init();
	}

	public void endInit() throws Exception {
		if (!authenticate(module, OPERATION_VIEW)) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/retail/login.xhtml");
		}
	}

	public boolean authorized() {
		return authenticate(module, OPERATION_VIEW);
	}

	public boolean authorize(String moduleName, EnumSystem system) {
		return authorize(sessionBean.getUser(), moduleName, system);
	}

	public boolean authenticate(Module module, int operation) {
		return authenticate(sessionBean.getUser(), module, operation);
	}
}
