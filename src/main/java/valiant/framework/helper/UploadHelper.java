package valiant.framework.helper;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class UploadHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadHelper.class);
	
	/**
	 * Apache Common FileUpload 锟结供锟斤拷Servlet锟侥硷拷锟较达拷锟斤拷锟斤拷
	 */
	private static ServletFileUpload servletFileUpload;
	
	/**
	 * 锟斤拷始锟斤拷
	 * @param servletContext
	 */
	public static void init(ServletContext servletContext) {
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		servletFileUpload = new ServletFileUpload(new DiskFileItemFactory
				(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, repository));
//		int uploadLimit = ConfigHelper.;
//		if (uploadLimit != 0) {
//			servletFileUpload.setFileSizeMax(uploadLimit * 1024 * 1024);
//		}
	}
	/**
	 * 锟叫讹拷锟斤拷锟斤拷锟角凤拷为multipart锟斤拷锟斤拷
	 * @param request
	 * @return
	 */
	public static boolean isMultipart(HttpServletRequest request) {
		return ServletFileUpload.isMultipartContent(request);
	}
	
//	public static Param createParam() {
//		
//	}
}
