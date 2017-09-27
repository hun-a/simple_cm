package com.cubrid.cubridmanager.core.cubrid.dbspace.model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;

import com.cubrid.common.core.util.LogUtil;
import com.cubrid.cubridmanager.core.common.model.EnvInfo;
import com.cubrid.cubridmanager.core.common.model.IModel;
import com.cubrid.cubridmanager.core.common.model.ServerVersion;

public class DbSpaceInfoList implements
		IModel {

	public static class FreeTotalSizeSpacename{
		public String spaceName;
		public int freeSize, totalSize;

		public FreeTotalSizeSpacename(String spaceName, int freeSize, int totalSize){
			this.spaceName = spaceName;
			this.freeSize = freeSize;
			this.totalSize = totalSize;
		}
	}

	private static final Logger LOGGER = LogUtil.getLogger(DbSpaceInfoList.class);
	protected String dbname = null;
	protected int pagesize = 0;
	protected int logpagesize = 0;
	protected int freespace = 0;

	protected List<DbSpaceInfo> spaceinfo = null;
	private static final ServerVersion changedFormatVersion = new ServerVersion(10, 1);

	/***
	 * Get the list that encapsulates the instances of DbSpaceInfo
	 *
	 * @return List<DbSpaceInfo> the list that encapsulates the instances of
	 *         DbSpaceInfo
	 */
	public List<DbSpaceInfo> getSpaceinfo() {
		synchronized (this) {
			return spaceinfo;
		}
	}

	/**
	 * Get the map that encapsulates the instances of DbSpaceInfo
	 *
	 * @return Map<String, DbSpaceInfo> the map that encapsulates the instances
	 *         of DbSpaceInfo
	 */
	public Map<String, DbSpaceInfo> getSpaceInfoMap() {
		synchronized (this) {
			Map<String, DbSpaceInfo> map = new TreeMap<String, DbSpaceInfo>();
			if (spaceinfo == null) {
				return map;
			}
			for (DbSpaceInfo bean : spaceinfo) {
				String type = bean.getType().toUpperCase();
				if (map.containsKey(type)) {
					DbSpaceInfo model = map.get(type);
					int free = model.getFreepage();
					int totl = model.getTotalpage();
					model.setFreepage(free + bean.getFreepage());
					model.setTotalpage(totl + bean.getTotalpage());
					model.plusVolumeCount();
				} else {
					DbSpaceInfo model = new DbSpaceInfo();
					try {
						DbSpaceInfoList.copyBean2Bean(bean, model);
					} catch (IllegalAccessException e) {
						LOGGER.error("", e);
					} catch (InvocationTargetException e) {
						LOGGER.error("", e);
					}
					model.setSpacename("");
					model.plusVolumeCount();
					map.put(type, model);
				}
			}
			return map;
		}
	}

	/**
	 * Set a list that encapsulates the instances of DbSpaceInfo as a field of
	 * this instance
	 *
	 * @param spaceinfoList List<DbSpaceInfo> A list that encapsulates the
	 *        instances of DbSpaceInfo
	 */
	public void setSpaceinfo(List<DbSpaceInfo> spaceinfoList) {
		synchronized (this) {
			this.spaceinfo = spaceinfoList;
		}
	}

	/**
	 * Add a instance of DbSpaceInfo into the spaceinfo list in the current
	 * instance
	 *
	 * @param info DbSpaceInfo A instance of DbSpaceInfo
	 */
	public void addSpaceinfo(DbSpaceInfo info) {
		synchronized (this) {
			if (spaceinfo == null) {
				spaceinfo = new ArrayList<DbSpaceInfo>();
			}
			if (!spaceinfo.contains(info)) {
				spaceinfo.add(info);
			}
		}
	}

	/**
	 * Remove a instance of DbSpaceInfo from sapceinfo list in the current
	 * instance
	 *
	 * @param info DbSpaceInfo A instance of DbSpaceInfo
	 */
	public void removeSpaceinfo(DbSpaceInfo info) {
		synchronized (this) {
			if (spaceinfo != null) {
				spaceinfo.remove(info);
			}
		}
	}

	public String getTaskName() {
		return "dbspaceinfo";
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getLogpagesize() {
		return logpagesize;
	}

	public void setLogpagesize(int logpagesize) {
		this.logpagesize = logpagesize;
	}


	public int getFreespace() {
		return freespace;
	}

	public void setFreespace(int freespace) {
		this.freespace = freespace;
	}

	/**
	 * Reflect the src field value into dest object
	 *
	 * @param src Object source object
	 * @param dest Object destination object
	 * @throws IllegalAccessException illegal access exception
	 * @throws InvocationTargetException invocation target exception
	 */
	public static void copyBean2Bean(Object src, Object dest) throws IllegalAccessException,
	InvocationTargetException {
		try {
			Method[] srcMtds = src.getClass().getMethods();
			Method[] destMtds = src.getClass().getMethods();

			Field[] yy = dest.getClass().getDeclaredFields();
			String[] proname = new String[yy.length];
			for (int j = 0; j < yy.length; j++) {
				proname[j] = yy[j].getName();
			}

			for (int ff = 0; ff < yy.length; ff++) {
				// get the set-method from dest object and get-method from src object
				String fieldName = yy[ff].getName();
				Method srcGetMethod = null;
				Method destSetMethod = null;

				for (Method m : srcMtds) {
					if (m.getName().equalsIgnoreCase("get" + fieldName)) {
						srcGetMethod = m;
						break;
					}
				}

				for (Method m : destMtds) {
					if (m.getName().equalsIgnoreCase("set" + fieldName)) {
						destSetMethod = m;
						break;
					}
				}

				if (srcGetMethod == null || destSetMethod == null) {
					continue;
				}

				//get the value from the dest object
				Class<?>[] descParams = destSetMethod.getParameterTypes();
				Class<?>[] srcParams = srcGetMethod.getParameterTypes();

				if (srcParams.length != 0 || descParams.length != 1) {
					continue;
				}

				boolean flag = true;
				Object value = srcGetMethod.invoke(src);
				if (value == null) {
					flag = false;
				}
				if (flag && value != null && value.getClass() == Integer.class && descParams[0] == int.class) {
					flag = false;
				}
				if (flag && value != null && value.getClass() == Double.class && descParams[0] == double.class) {
					flag = false;
				}
				if (flag && value != null && descParams[0] != value.getClass()) {
					continue;
				}
				destSetMethod.invoke(dest, new Object[] { value });
			}
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (IllegalAccessException e) {
			throw e;
		} catch (InvocationTargetException e) {
			throw e;
		}
	}

	public int getTotalSize(){
		return 0;
	}

	public int getFreeSize(){
		return 0;
	}

	public ArrayList<FreeTotalSizeSpacename> getVolumesInfoByType(String fullType){
		ArrayList<FreeTotalSizeSpacename> info = new ArrayList<FreeTotalSizeSpacename>();
		String type = null;
		String purpose = null;

		int index = fullType.indexOf(" ");
		if (index >= 0) {
			type = fullType.substring(0, fullType.indexOf(" "));
			purpose = fullType.substring(fullType.indexOf(" ")+1, fullType.lastIndexOf(" "));
		}

		for (DbSpaceInfo bean : spaceinfo) {
			if (index < 0){
				if (bean.getType().equals(fullType)){
					info.add(new FreeTotalSizeSpacename(bean.getShortVolumeName(), bean.getFreepage(), bean.getTotalpage()));
				}
			} else {
				if (bean.getType().equals(type) && bean.getPurpose().equals(purpose)) {
					info.add(new FreeTotalSizeSpacename(bean.getShortVolumeName(), bean.getFreepage(), bean.getTotalpage()));
				}
			}
		}
		return info;
	}

	public static boolean useOld(EnvInfo envInfo) {
		return envInfo.getServerDetails().isSmallerThan(changedFormatVersion);
	}

}
