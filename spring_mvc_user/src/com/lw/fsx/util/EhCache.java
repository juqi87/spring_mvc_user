package com.lw.fsx.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 缓存类,交给spring IOC所以为单例。
 * @author 
 */
public class EhCache {
	
	/**
	 * 缓存管理器,使用default下的配置（默认配置）
	 */
	private CacheManager singletonManager = CacheManager.create();
	/**
	 * 此项目的缓冲对象
	 */
	private Cache cache = null;
	
	private EhCache(){
		//创建一个付费通缓存，也使用默认配置。并将这个付费通缓存放置到cache对象。
		singletonManager.addCache("fftCache");
		cache=singletonManager.getCache("fftCache");
	}

	/**
	 * 功能： 放入缓存   <br/>
	 * 作者：
	 * 时间：Sep 1, 2011 3:07:56 PM
	 * @param key 键
	 * @param value 值
	 */
	public void putCache(String key,Object value){
		Element element = new Element(key, value);
		cache.put(element);
	}
	
	/**
	 * 功能： 放入缓存,second为多少秒后过期   <br/>
	 * 作者：
	 * 时间：Sep 1, 2011 3:07:56 PM
	 * @param key 键
	 * @param value 值
	 * @param second 多少秒后过期
	 */
	public void putCache(String key,Object value,int second){
		Element element = new Element(key, value);
		element.setTimeToLive(second);
		cache.put(element);
	}
	
	/**
	 * 功能：根据键从缓存中得到值   <br/>
	 * 作者：
	 * 时间：Sep 1, 2011 3:08:21 PM
	 * @param key 键
	 * @return 对应的值
	 */
	public Object getCache(String key){
		Element element = cache.get(key); 
		return element==null ? null : element.getValue() ;
	}
	
	/**
	 * 功能：根据键从缓存中移除一个对象    <br/>
	 * 作者：
	 * 时间：Sep 1, 2011 3:08:45 PM
	 * @param key 键
	 */
	public void removeCache(String key){
		cache.remove(key);  
	}
	/**
	 * 清除所有缓存
	 */
	public void removeAllCache(){
		cache.removeAll();
	}
}
