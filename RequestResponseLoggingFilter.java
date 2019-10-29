package com.telintel.services.smscampaigns.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.LogRecord;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.common.io.CharStreams;

@Component
@Order(2)
public class RequestResponseLoggingFilter implements Filter {

	Logger log = LoggerFactory.getLogger(this.getClass());

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpServletCacheWrapper myRequestWrapper = new HttpServletCacheWrapper(req);
		String body = CharStreams.toString(new InputStreamReader(myRequestWrapper.getInputStream(), "UTF-8"));

		log.info("[START] [" + req.getMethod() + " REQUEST] [URI: " + req.getRequestURI() + "][Body is: {}]", body);
		chain.doFilter(myRequestWrapper, response);

		long meanTime = (long) request.getAttribute("startTime");
		request.removeAttribute("meanTime");
		long endTime = System.currentTimeMillis();

		log.info(
				"[END]  [" + req.getMethod() + " REQUEST] [URI: " + req.getRequestURI()
						+ "] [Execution Time: {} miliseconds] [Logging Response: {}]",
				(endTime - meanTime), res.getContentType());

	}

	public void destroy() {

	}

	public boolean isLoggable(LogRecord record) {
		// TODO Auto-generated method stub
		return false;
	}
}
