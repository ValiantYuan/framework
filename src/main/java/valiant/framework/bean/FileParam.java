package valiant.framework.bean;

import java.io.InputStream;
/**
 * 
 * @author yuanq5
 *
 */
public class FileParam {
	private String fieldName;
	private String fileName;
	private long filesize;
	private String contentType;
	private InputStream inputStream;
	
	/**
	 * 
	 * @param fieldName 	文件表单的字段名
	 * @param fileName		上传文件的文件名
	 * @param filesize		上传文件的大小
	 * @param contentType	上传文件的contentType，判断文件类型	
	 * @param inputStream	上传文件的字节输入流
	 */	
	public FileParam(String fieldName, String fileName, long filesize, String contentType, InputStream inputStream) {
		super();
		this.fieldName = fieldName;
		this.fileName = fileName;
		this.filesize = filesize;
		this.contentType = contentType;
		this.inputStream = inputStream;
	}
	public String getFieldName() {
		return fieldName;
	}
	public String getFileName() {
		return fileName;
	}
	public long getFilesize() {
		return filesize;
	}
	public String getContentType() {
		return contentType;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	
	
}
