package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebFilter(urlPatterns = {"/modify-category.pcat", "/view-exp-categories.pcat", "/view-expcategory-product.pcat", "/modify-product.pcat", "/view-exp-catalog.pcat"})
public class ModificationFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fChain)
			throws IOException, ServletException {
		HttpServletRequest requestVariable = (HttpServletRequest)request;
		HttpServletResponse responseVariable = (HttpServletResponse)response;
		
		if(requestVariable.getSession().getAttribute("userID") != null) {
			fChain.doFilter(requestVariable, responseVariable);
		} else {
			responseVariable.sendRedirect("/view-catalog.pcat");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
