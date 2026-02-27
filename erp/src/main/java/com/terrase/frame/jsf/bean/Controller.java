package com.terrase.frame.jsf.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.Map;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.application.Resource;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.terrase.spring.util.SpringUtil;

public abstract class Controller extends SpringBeanAutowiringSupport implements Serializable {
	private static final long serialVersionUID = 1L;

	public Logger getLogger() {
		return Logger.getLogger(this.getClass());
	}

	public <T> T springLookup(String id) {
		return SpringUtil.lookup(id);
	}

	public <T> T springLookup(Class<T> clazz) {
		return SpringUtil.lookup(clazz);
	}

	@SuppressWarnings("unchecked")
	public <T> T beanLookup(String id) {
		FacesContext faces = FacesContext.getCurrentInstance();
		ELContext context = faces.getELContext();
		return (T) faces.getApplication().getELResolver().getValue(context, null, id);
	}

	public Map<String, String> getRequestParameters() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	}

	public String getResourcePath(String library) {
		FacesContext context = FacesContext.getCurrentInstance();
		Resource resource = context.getApplication().getResourceHandler().createResource(library);
		return resource.getURL().toExternalForm().replaceFirst("file:/", "");
	}

	public void download(String fileName, String contentType) throws IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

		InputStream input = null;
		OutputStream output = null;

		try {
			response.reset();
			response.setContentType(contentType);
			response.setHeader("Content-disposition",
					"attachment; filename=\"" + Paths.get(fileName).getFileName().toString() + "\"");

			File file = new File(fileName);
			input = new FileInputStream(file);
			output = response.getOutputStream();
			byte[] buffer = new byte[1024];

			int counter = 0;

			while ((counter = input.read(buffer, 0, buffer.length)) > 0) {
				output.write(buffer, 0, counter);
			}
		} finally {
			if (output != null) {
				output.close();
			}
			if (input != null) {
				input.close();
			}
			facesContext.responseComplete();
		}
	}

	public void preview(String fileName, String contentType) throws IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

		InputStream input = null;
		OutputStream output = null;

		try {
			response.reset();
			response.setContentType(contentType);
			response.setHeader("Content-disposition",
					"inline; filename=\"" + Paths.get(fileName).getFileName().toString() + "\"");

			File file = new File(fileName);
			input = new FileInputStream(file);
			output = response.getOutputStream();
			byte[] buffer = new byte[1024];

			int counter = 0;

			while ((counter = input.read(buffer, 0, buffer.length)) > 0) {
				output.write(buffer, 0, counter);
			}
		} finally {
			if (output != null) {
				output.close();
			}
			if (input != null) {
				input.close();
			}
			facesContext.responseComplete();
		}
	}

	public String getFileType(String filename) {
		if (filename == null) {
			return null;
		}

		if (filename.toLowerCase().endsWith(".pdf")) {
			return "adobe/pdf";
		} else if (filename.toLowerCase().endsWith(".doc") || filename.toLowerCase().endsWith(".docx")) {
			return "doc";
		} else if (filename.toLowerCase().endsWith(".jpg")) {
			return "image/jpeg";
		}

		return null;
	}

	public void addInfoMessage(String summary, String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, summary, message));
		getLogger().info(summary + System.lineSeparator() + message);
	}

	public void addWarningMessage(String summary, String message) {
		FacesContext.getCurrentInstance().addMessage("messages",
				new FacesMessage(FacesMessage.SEVERITY_WARN, summary, message));
		getLogger().warn(summary + System.lineSeparator() + message);
	}

	public void addErrorMessage(String summary, String message) {
		FacesContext.getCurrentInstance().addMessage("messages",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, message));
		getLogger().error(summary + System.lineSeparator() + message);
	}

	public void addErrorMessage(Throwable t) {
		String message = t.getMessage();
		if (t instanceof HibernateOptimisticLockingFailureException) {
			message = "Data is updated by other users/operations, please refresh your record to retrieve latest data and try again";
		} else if (t instanceof DataIntegrityViolationException) {
			DataIntegrityViolationException exception = (DataIntegrityViolationException) t;
			if (exception.getRootCause() != null) {
				if (StringUtils.containsIgnoreCase(message, "Duplicate Entry")
						|| StringUtils.containsIgnoreCase(message, "UNIQUE")) {
					message = "Code is in use by other record, duplicated value is not allowed";
				}
			}
		}

		FacesContext.getCurrentInstance().addMessage("messages",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notification", message));
		getLogger().error(message, t);
	}

	protected void updateComponent(String id, String form) {
		PrimeFaces.current().ajax().update(String.format(":%s:%s", form, id));
	}

	protected void updateForm(String form) {
		PrimeFaces.current().ajax().update(form + "Form");
	}

	protected void showWidget(String widget) {
		PrimeFaces.current().executeScript("PF('" + widget + "WV').show()");
	}

	protected void hideWidget(String widget) {
		PrimeFaces.current().executeScript("PF('" + widget + "WV').hide()");
	}

	public void focus(String focusId, String form) {
		PrimeFaces.current().focus(String.format(":%s:%s", form, focusId));
	}
}
