package com.natura.challenge.front.exceptions;

import java.io.IOException;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omnifaces.exceptionhandler.FullAjaxExceptionHandler;
import org.omnifaces.util.Faces;
import org.omnifaces.util.FacesLocal;
import org.primefaces.PrimeFaces;

public class FullAjaxExceptionHandlerCustom extends FullAjaxExceptionHandler {

	private static final String ERROR_DEFAULT_LOCATION_MISSING = "Either HTTP 500 or java.lang.Throwable error page is required in web.xml or web-fragment.xml."
			+ " Neither was found.";

	public FullAjaxExceptionHandlerCustom(ExceptionHandler wrapped) {
		super(wrapped);
	}

	@Override
	protected Throwable findExceptionRootCause(FacesContext context, Throwable exception) {
		exception = super.findExceptionRootCause(context, exception);
		HttpServletRequest request = FacesLocal.getRequest(context);
		request.getSession().setAttribute("exception_object", exception);
		request.getSession().setAttribute("exception_type", exception.getClass());
		request.getSession().setAttribute("exception_message", exception.getMessage());
		request.getSession().setAttribute("exception_uri", request.getRequestURI());
		request.getSession().setAttribute("exception_error_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		request.getSession().setAttribute("exception_ajax_request", PrimeFaces.current().isAjaxRequest() ? "Si" : "No");
		try {
			Faces.redirect((request.getContextPath().equals("/") ? "" : request.getContextPath()) + "/500.xhtml");
		} catch (IOException e) {
			throw new IllegalArgumentException(ERROR_DEFAULT_LOCATION_MISSING);
		}
		exception.printStackTrace();
		return exception;
	}

	@Override
	protected boolean shouldHandleExceptionRootCause(FacesContext context, Throwable exception) {
		return false;
	}
}
