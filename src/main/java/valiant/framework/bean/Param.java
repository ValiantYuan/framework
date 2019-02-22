package valiant.framework.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

import valiant.util.CastUtil;
import valiant.util.CollectionUtil;
import valiant.util.StringUtil;
/**
 * 请求url中的参数对象
 * @author yuanq5
 *
 */
public class Param {
	private Map<String, Object> paramMap;
	
	private List<FormParam> formParamList;
	private List<FileParam> fileParamList;
	
	
	public Param(Map<String, Object> paramMap) {
		// TODO Auto-generated constructor stub
		this.paramMap = paramMap;
	}
	
	
	
	public Param(List<FormParam> formParamList, List<FileParam> fileParamList) {
		super();
		this.formParamList = formParamList;
		this.fileParamList = fileParamList;
	}



	public Param(List<FormParam> formParamList) {
		super();
		this.formParamList = formParamList;
	}


	/**
	 * 获取请求参数映射
	 * @return
	 */
	public Map<String, Object> getFieldMap() {
		Map<String, Object> fieldMap = new HashedMap<>();
		if (CollectionUtil.isNotEmpty(formParamList)) {
			for (FormParam formParam : formParamList) {
				String fieldName = formParam.getFieldName();
				Object fieldValue = formParam.getFieldValue();
				if (fieldMap.containsKey(fieldName)) {
					fieldValue = fieldMap.get(fieldName)  + StringUtil.SEPARATOR + fieldValue;
				}
				fieldMap.put(fieldName, fieldValue);
			}
		}
		return fieldMap;
	}
	
	/**
	 * 获取上传文件映射
	 * @return
	 */
	public Map<String, List<FileParam>> getFileMap() {
		Map<String, List<FileParam>> fileMap = new HashMap<String, List<FileParam>>();
		if (CollectionUtil.isNotEmpty(fileParamList)) {
			for (FileParam fileParam : fileParamList) {
				String fieldName = fileParam.getFieldName();
				List<FileParam> fileParamList;
				if (fileMap.containsKey(fieldName)) {
					fileParamList = fileMap.get(fieldName);
				} else {
					fileParamList = new ArrayList<FileParam>();
				}
				fileParamList.add(fileParam);
				fileMap.put(fieldName, fileParamList);
			} 
		}
		return fileMap;
	}
	/**
	 * 获取所有上传文件
	 * @param fieldName
	 * @return
	 */
	public List<FileParam> getFileList(String fieldName) {
		return getFileMap().get(fieldName);
	}
	/**
	 * 获取唯一上传文件
	 * @param fieldName
	 * @return
	 */
	public FileParam getFile(String fieldName) {
		List<FileParam> fileParamList = getFileList(fieldName);
		if (CollectionUtil.isNotEmpty(fileParamList)
				&& fileParamList.size() == 1) {
			return fileParamList.get(0);
		}
		return null;
	}
	
	/**
	 * 验证参数是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return CollectionUtil.isEmpty(formParamList) && CollectionUtil.isEmpty(fileParamList);
	}
	
	/**
	 * 按参数名获取String型参数值
	 * @param name
	 * @return
	 */
	public String getString(String name) {
			return CastUtil.castString(getFileMap().get(name));
	}
	/**
	 * 按参数名获取int型参数值
	 * @param name
	 * @return
	 */
	public int getInt(String name) {
		return CastUtil.castInt(getFileMap().get(name));
	}
	/**
	 * 按参数名获取double型参数值
	 * @param name
	 * @return
	 */
	public double getDouble(String name) {
		return CastUtil.castDouble(getFileMap().get(name));
	}
	/**
	 * 按参数名获取boolean型参数值
	 * @param name
	 * @return
	 */
	public boolean getBoolean(String name) {
		return CastUtil.castBoolean(getFileMap().get(name));
	}
	/**
	 * 按参数名获取long型参数值
	 * @param name
	 * @return
	 */
	public long getLong(String name) {
			return CastUtil.castLong(getFileMap().get(name));
	}
}	
