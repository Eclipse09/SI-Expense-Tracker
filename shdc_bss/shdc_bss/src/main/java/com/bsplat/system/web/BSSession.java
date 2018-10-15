package com.bsplat.system.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BSSession {
	public static final String COOKIE_BS_STYLE = "bsStyle";
	public static final String STYLES_PATH = "/styles";
	public static final String DEFAULT_STYLE = "defaultStyle";
	public static final String SESSION_KEY = "BS_SESSION";
	private static final ThreadLocal threadLocal = new ThreadLocal();
	// private User user;

	private Theme theme;

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	// private SubMarket subMarket;

	private String local;

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	private BSSession() {

	}

	public static BSSession getSession(HttpServletRequest request,
			HttpServletResponse response) {
		Object obj = null;
		try {
			obj = BSSession.getInSessionProperty(SESSION_KEY);
		} catch (Exception e) {
			obj = null;
			e.printStackTrace();
		}

		if (obj == null) {
			return initSession(request, response);
		}

		return (BSSession) obj;
	}

	public static void reFresh(HttpServletRequest request,
			HttpServletResponse response) {
		
		if (request.getParameter(BSSession.COOKIE_BS_STYLE) != null && !"".equals(request.getParameter(BSSession.COOKIE_BS_STYLE))) {
			HttpSession session = request.getSession();

			BSSession bsSession= new BSSession();
			session.removeAttribute(SESSION_KEY);
			String userStyle = request.getParameter(BSSession.COOKIE_BS_STYLE)
					.toString();

			String cssRoot = STYLES_PATH;
			String imageRoot = "";
			String local = "";
			Theme theme = new Theme();

			Cookie cookie = new Cookie(COOKIE_BS_STYLE, userStyle);
			cookie.setPath("/");
			cookie.setMaxAge(33333333);
			response.addCookie(cookie);

			cssRoot += "/" + userStyle;
			imageRoot = cssRoot + "/images";
			theme.setUserStyle(userStyle);
			theme.setCssRoot(cssRoot);
			theme.setImgRoot(imageRoot);
			bsSession.setTheme(theme);
			if (bsSession.getLocal() == null) {
				if (request.getLocale() != null) {
					local = request.getLocale().toString();
				}
				bsSession.setLocal(local);
			}
			session.setAttribute(BSSession.SESSION_KEY, bsSession);
		}
	}

	private static BSSession initSession(HttpServletRequest request,
			HttpServletResponse response) {
		BSSession bsSession = new BSSession();
		String userStyle = DEFAULT_STYLE;
		String cssRoot = STYLES_PATH;
		String imageRoot = "";
		String local = "";
		Theme theme = null;
		if (bsSession.getTheme() != null) {
			theme = bsSession.getTheme();
			if (theme.getUserStyle() != null && "".equals(theme.getUserStyle())) {
				userStyle = theme.getUserStyle();
			}

		} else {
			theme = new Theme();
			bsSession.setTheme(theme);
		}
		if (theme.getUserStyle() == null || "".equals(theme.getUserStyle())) {

			// if user first enter use default theme else read it from
			// cookie
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = (Cookie) cookies[i];
					// System.out.println("%%%%%%%%%cookie1="+cookie.getName());
					if (COOKIE_BS_STYLE.equals(cookie.getName())) {
						userStyle = cookie.getValue();
					}
				}
			} else {
				// if does not exists use default theme
				Cookie cookie = new Cookie(COOKIE_BS_STYLE, userStyle);
				cookie.setPath("/");
				cookie.setMaxAge(33333333);
				response.addCookie(cookie);
			}
			cssRoot += "/" + userStyle;
			imageRoot = cssRoot + "/images";
			theme.setUserStyle(userStyle);
			theme.setCssRoot(cssRoot);
			theme.setImgRoot(imageRoot);
		}
		if (bsSession.getLocal() == null) {
			if (request.getLocale() != null) {
				local = request.getLocale().toString();
			}
			bsSession.setLocal(local);
		}
		HttpSession session = request.getSession();
		session.setAttribute(BSSession.SESSION_KEY, bsSession);
		return bsSession;
	}

	public static Map getData() {
		Map map = (Map) threadLocal.get();
		if (map == null) {
			map = Collections.synchronizedMap(new HashMap());
			threadLocal.set(map);
		}
		return map;
	}

	public static Object getProperty(String keyName) {
		Map map = getData();
		return map.get(keyName);
	}

	public static Map getInSession() {
		return (Map) getProperty("IN_SESSION");
	}

	public static Object getInSessionProperty(String key) {
		Map map = getInSession();
		if (map != null) {
			return map.get(key);
		}
		return null;
	}

	public static void setProperty(String keyName, Object keyValue) {
		Map map = getData();
		map.put(keyName, keyValue);
	}
}
