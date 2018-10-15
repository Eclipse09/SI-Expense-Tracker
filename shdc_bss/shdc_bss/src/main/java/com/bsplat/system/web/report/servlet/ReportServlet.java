package com.bsplat.system.web.report.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/** 
 * @author liu
 * @email  lxc502045798@163.com
 * @createDate 2012-9-12 上午11:30:08
 * @version 1.0
 * @descrīption -.-
 */
public class ReportServlet extends HttpServlet {

	    /**
	     * 
	     */
	    private static final long serialVersionUID = 7256087174207798983L;

	    private static final String REPORT_SERVLET_ID = "REPORT_SERVLET_MAP";

	    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {
	        doPost(req, resp);
	    }

	    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {
	        HttpSession session = req.getSession();
	        
	        Map e = req.getParameterMap();  //获取导出页面的部分参数作判断 进行拦截跳转
	        if(e.get("pages")!=null&&e.get("op")!=null&&e.get("ID")!=null&&e.get("format")!=null){
	        	
	           String[] objpages=(String[]) e.get("pages");
	           String[] objop=(String[]) e.get("op");
	           String[] objID=(String[]) e.get("ID");
	           String[] objformat=(String[]) e.get("format");
	
	           resp.sendRedirect("http://reports.bsteel.com/sree/Examples?pages="+objpages[0]+"&op="+objop[0]+"&ID="+objID[0]+"&format="+objformat[0]+"");
 	        }
	        
	        Map reportServletMap = (Map) session
	                .getAttribute(REPORT_SERVLET_ID);
	        System.out.println(session.getAttribute(REPORT_SERVLET_ID));
	        if (reportServletMap == null) {
	            write2ErrorChannel(resp, "no reports");
	            return;
	        }
	        String isoUrl = req.getPathInfo().substring(1);
	        String origUrl = (String) reportServletMap.get(isoUrl);
	        if (origUrl == null) {
	            write2ErrorChannel(resp, "it's not a true uri for reports");
	            return;
	        }
	        String queryString = req.getQueryString();
	        
	        if (null != queryString && !"".equals(queryString)) {

	            if (origUrl.indexOf('?') > 0) {
	                if (queryString.indexOf('&') > 0) {
	                    queryString = queryString.substring(queryString.indexOf('&'));
	                } else {
	                    queryString = "";
	                }
	            } else {
	                queryString = "?" + queryString;
	            }
	        } else {
	            queryString = "";
	        }
	        readTrueSportsURI(req, resp, origUrl, queryString);
	        
	    }

	    private void readTrueSportsURI(HttpServletRequest req,
	            HttpServletResponse resp, String sportsURI, String queryString) {

	        try {

	            boolean isHtml = false;

	            URL url = new URL(sportsURI + queryString);

	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	            Map fields = connection.getHeaderFields();

	            Iterator it = fields.keySet().iterator();
	            while (it.hasNext()) {
	                Object o = it.next();

	                if (null != o) {
	                    String v = fields.get(o).toString();
	                    System.out.println(o + ":" + v);
	                    if (v.indexOf("text/html") > 0) {
	                        isHtml = true;
	                    }
	                    resp.addHeader((String) o, v.substring(1, v.length() - 1));
	                }

	            }
	            InputStream in = connection.getInputStream();
	
	            if (isHtml) { //如果打开的报表是HTML格式
	                StringBuffer readString = new StringBuffer();

	                byte[] b = new byte[102400];
	                int length = 0;
	                while ((length = in.read(b)) > 0) {
	                    byte[] bb = new byte[length];
	                    for (int i = 0; i < length; i++) {
	                        bb[i] = b[i];
	                    }
	                    readString.append(new String(bb, "UTF-8"));
	                }
                    String origHTML=readString.toString();
	
                    origHTML = origHTML.replaceAll("<option value='PDF' selected>PDF</option>","&nbsp;");//删除   Select a Format的 PDF格式导出
                    origHTML = origHTML.replaceAll("<option value='XML'>XML</option>","&nbsp;");//删除   Select a Format的 XML 格式导出
                    origHTML = origHTML.replaceAll("<option value='HTML_NO_PAGINATION'>","&nbsp;");//删除   Select a Format的 HTML 格式导出
                    origHTML = origHTML.replaceAll("<option value='HTML'>","&nbsp;");//删除   Select a Format的 HTML 格式导出
                    origHTML = origHTML.replaceAll("</option>&nbsp;HTML '>","&nbsp;");//删除   Select a Format的 HTML 格式导出
                    origHTML = origHTML.replaceAll("<option value='SVG'>SVG</option>","&nbsp;");//删除   Select a Format的 SVG 格式导出
	                String mapStr = "http://reports.bsteel.com/"; // 替换报表的域名改为APP域名
	                
	                int beginIndex = 0;
	                int endIndex = 0;

	                StringBuffer newContent = new StringBuffer();
	                beginIndex = origHTML.indexOf(mapStr, beginIndex);
	                while (beginIndex > 0) {
	                    char ch = origHTML.charAt(beginIndex - 1);
	                    if(ch=='('){
		                    newContent.append(origHTML.substring(endIndex, beginIndex));
		                    endIndex = origHTML.indexOf(')', beginIndex);
		                    String origURL = origHTML.substring(beginIndex, endIndex);
		                    String newURL = convertUrl(origURL, req);
		                    newContent.append(newURL);
		                    beginIndex = origHTML.indexOf(mapStr, endIndex);
	                    	
	                    }else{
	                    newContent.append(origHTML.substring(endIndex, beginIndex));
	                    endIndex = origHTML.indexOf(ch, beginIndex);
	                    String origURL = origHTML.substring(beginIndex, endIndex);
	                    String newURL = convertUrl(origURL, req);
	                    newContent.append(newURL);
	                    beginIndex = origHTML.indexOf(mapStr, endIndex);
	                    }
	                  }
	                newContent.append(origHTML.substring(endIndex));
	                resp.setContentType("text/html;charset=utf-8");
	                resp.getWriter().print(newContent.toString());
	                resp.getWriter().flush();

	                in.close();

	            } else {
	                byte[] buffer = new byte[1024];
	                OutputStream out = resp.getOutputStream();

	                int count = 0;
	                
	                try {
	                    while ((count = in.read(buffer)) > 0) {
	                        System.out.println("Read byte: "+count);
	                        out.write(buffer, 0, count);
	                    }
	                    
	                    out.flush();
	                    
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    write2ErrorChannel(resp, e.getMessage());
	                } finally {
	                    out.close();
	                    in.close();
	                }
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

	    private void write2ErrorChannel(HttpServletResponse resp, String msg)
	            throws IOException {
	        resp.setContentType("text/html;charset=utf-8");
	        OutputStream out = resp.getOutputStream();
	        try {
	            byte[] b = ("<html><body>" + msg + "</body></html>").getBytes();
	            out.write(b);
	        } finally {
	            out.close();
	        }
	    }

	    public static String convertUrl(String origUrl, HttpServletRequest req) {
	        String isoUrl = randIsoUrl(origUrl, req);
	        add2Session(isoUrl, origUrl, req.getSession());
	        String prefix = req.getSession().getServletContext().getInitParameter(
	                "urlproxy");

	        if (null == prefix) {
	            throw new RuntimeException(
	                    "urlproxy context parameter is not configed!");
	        }
	        String split = prefix.startsWith("/") ? "" : "/";

	        String split2 = prefix.endsWith("/") ? "" : "/";

	        if (origUrl.indexOf('?') > 0) {
	        	
	            return req.getContextPath() + split + prefix + split2 + isoUrl
	                    + "?sessionId=" + req.getSession().getId();
	            
	        } else {
	        	
	            return req.getContextPath() + split + prefix + split2 + isoUrl;
	        }

	    }

	    private static String randIsoUrl(String origUrl, HttpServletRequest req) {
	        int code = (origUrl + req.getSession().getId()).hashCode();
	        return code > 0 ? String.valueOf(code) : String
	                .valueOf(Integer.MAX_VALUE + code);
	    }

	    private synchronized static void add2Session(String key, String value,
	            HttpSession session) {
	        Map reportServletMap = (HashMap) session
	                .getAttribute(REPORT_SERVLET_ID);
	        if (reportServletMap == null) {
	            reportServletMap = new HashMap();
	        }
	        reportServletMap.put(key, value);
	        session.setAttribute(REPORT_SERVLET_ID, reportServletMap);
	    }
}