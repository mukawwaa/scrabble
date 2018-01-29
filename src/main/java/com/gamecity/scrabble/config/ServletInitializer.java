package com.gamecity.scrabble.config;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.gamecity.scrabble.Constants;
import com.gamecity.scrabble.filter.CORSFilter;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
    @Override
    protected Class<?>[] getRootConfigClasses()
    {
        return new Class<?>[] { WebConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses()
    {
        return null;
    }

    @Override
    protected String[] getServletMappings()
    {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters()
    {
        return new Filter[] {
            new DelegatingFilterProxy("springSecurityFilterChain"), new CharacterEncodingFilter(Constants.CHARACTER_ENCODING, true),
            new CORSFilter() };
    }

    @Override
    protected void customizeRegistration(Dynamic registration)
    {
        registration.setAsyncSupported(true);
    }
}
