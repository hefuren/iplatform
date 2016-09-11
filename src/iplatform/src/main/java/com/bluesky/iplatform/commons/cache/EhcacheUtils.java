package com.bluesky.iplatform.commons.cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.CacheManagerBuilder;
import org.ehcache.config.CacheConfigurationBuilder;

public class EhcacheUtils {

private static final String path = "/ehcache.xml";
	
	/**
	 * Activiti Cache Key
	 */
	public static final String EHCACHE_KEY_ACTIVITI = "Activiti-Ehcache";
	public static final String EHCACHE_KEY_PLATFORM = "EHCACHE_PLATFORM";
	public static final String EHCACHE_KEY_SEQUENCE = "EHCACHE_SEQUENCE";
	
	private CacheManager cacheManager;
	
	private static EhcacheUtils ehCaches;
	
	private EhcacheUtils(String path){
		cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
				.withCache(path, CacheConfigurationBuilder.newCacheConfigurationBuilder()
						.buildConfig(String.class, Object.class)).build(false);
		cacheManager.init();
	}
	
	public static EhcacheUtils getInstance(String cacheKey){
		if(ehCaches == null){
			ehCaches = new EhcacheUtils(cacheKey);
		}
		return ehCaches;
	}
	
	public void put(Cache cache, String key, Object value){
		cache.put(key, value);
	}
	
	public Object get(Cache cache, String key){
		Object element = cache.get(key);
		return element;
	}
	
	public Object get(String cacheName, String key){
		Cache<String, Object> cache = cacheManager.getCache(cacheName, String.class, Object.class);
		if(cache == null){
			cache = cacheManager.createCache(cacheName, CacheConfigurationBuilder.newCacheConfigurationBuilder().buildConfig(String.class, Object.class));
		}
		Object element = cache.get(key);
		return element;
	}
	
	public void remove(Cache cache, String cacheName, String key){
		cache.remove(key);
	}
	
	public void clearAll(Cache cache, String cacheName){
		cache.clear();
	}
	
	public Cache getEhcache(String cacheName){
		Cache<String, Object> cache = cacheManager.getCache(cacheName, String.class, Object.class);
		if(cache == null){
			cache = cacheManager.createCache(cacheName, CacheConfigurationBuilder.newCacheConfigurationBuilder().buildConfig(String.class, Object.class));
		}
		return cache;
	}
}
