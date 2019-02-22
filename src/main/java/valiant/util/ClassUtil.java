package valiant.util;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);
	
	/**
	 * @return 获取类加载器,获取线程上下文类加载器
	 */
	public static ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}
	/**
	 * 加载类，默认不进行初始化
	 * @param className
	 */
	public static void  loadClass(String className) {
		loadClass(className, false);
	}
	
	/**
	 * @param className 全限定类路径名
	 * @param isInitialized 是否需要初始化
	 * @return 返回加载类的Class对象
	 */
	public static Class<?> loadClass(String className, boolean isInitialized) {
		Class<?> cls;
		try {
			cls = Class.forName(className, isInitialized, getClassLoader());
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			LOGGER.error("load class failure", e);
			throw new RuntimeException(e);
		}
		return cls;
	}
	/**
	 * @param packageName
	 * @return 获取并加载指定包名下的所有类
	 */
	public static Set<Class<?>> getClassSet(String packageName) {
		Set<Class<?>> classSet = new HashSet<>();
		try {
			Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				if (url != null) {
					String protocol = url.getProtocol();
					if (protocol.equals("file")) {
						//文件名在转换为字符串时，空格会被替换成%20，将字符串中的%20替换成空格
						String packagePath = url.getPath().replaceAll("%20", " ");
						addClass(classSet, packagePath, packageName);
					} else if (protocol.equals("jar")) {
						JarURLConnection jarURLConnection = (JarURLConnection)url.openConnection();
						if(jarURLConnection != null) {
							JarFile jarFile = jarURLConnection.getJarFile();
							if (jarFile != null) {
								Enumeration<JarEntry> jarEntries = jarFile.entries();
								while (jarEntries.hasMoreElements()) {
									JarEntry jarEntry = (JarEntry) jarEntries.nextElement();
									String jarEntryName = jarEntry.getName();
									if (jarEntryName.endsWith(".class")) {
										String className = jarEntryName.substring(0, 
												jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
										doAddClass(classSet, className);
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("get class set failure", e);
			throw new RuntimeException(e);
		}
		return classSet;
	}
	/**
	 * @param classSet
	 * @param packagePath 包路径，在操作系统中的绝对路径
	 * @param packageName 包名，在工程内容的名称
	 */
	public static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
		File[] files = new File(packagePath).listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File file) {
				// TODO Auto-generated method stub
				return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
			}
		});
		for (File file : files) {
			String fileName = file.getName();
			if (file.isFile()) {
				String className = fileName.substring(0, fileName.lastIndexOf("."));
				if (StringUtil.isNotEmpty(packageName)) {
					className = packageName + "." + className;
				}
				doAddClass(classSet, className);
			} else {
				//递归的添加子文件夹中的类到类集合中
				String subPackagePath = fileName;
				if (StringUtil.isNotEmpty(packagePath)) {
					subPackagePath = packagePath + "/" + subPackagePath;
				}
				String subPackageName = fileName;
				if (StringUtil.isNotEmpty(packageName)) {
					subPackageName = packageName + "." + subPackageName;
				}
				addClass(classSet, subPackagePath, subPackageName);
			}
		}
 	}
	
	/**
	 * 加载类并把类加入到类集合中
	 * @param classSet
	 * @param className
	 */
	public static void doAddClass (Set<Class<?>> classSet, String className) {
		Class<?> cls = loadClass(className, false);
		classSet.add(cls);
	}
}
