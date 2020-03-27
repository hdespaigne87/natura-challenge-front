package com.natura.challenge.front.exceptions;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

import org.omnifaces.exceptionhandler.FullAjaxExceptionHandlerFactory;

public class FullAjaxExceptionHandlerFactoryCustom extends FullAjaxExceptionHandlerFactory {

    public FullAjaxExceptionHandlerFactoryCustom(ExceptionHandlerFactory exceptionHandlerFactory) {
        super(exceptionHandlerFactory);
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        return new FullAjaxExceptionHandlerCustom(getWrapped().getExceptionHandler());
    }
}
