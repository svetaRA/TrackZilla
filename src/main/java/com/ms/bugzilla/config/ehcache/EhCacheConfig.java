
  package com.ms.bugzilla.config.ehcache;
  
  
  
import org.slf4j.Logger;
import
  org.slf4j.LoggerFactory;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import
  org.springframework.context.annotation.Bean;
import
  org.springframework.context.annotation.Configuration;
  
  
  @Configuration 
  public class EhCacheConfig {
	  private final Logger log =LoggerFactory.getLogger(EhCacheConfig.class);
	  
		
		/*
		 * @Bean public CacheManager cacheManager() {
		 * 
		 * net.sf.ehcache.CacheManager ehcacheCacheManager =
		 * ehCacheManagerFactoryBean().getObject();
		 * 
		 * return new EhCacheCacheManager(ehcacheCacheManager);
		 * 
		 * }
		 */
		 

  }
  
  
  
  
 
 

/*
 * @Bean public JCacheManagerCustomizer cacheManagerCustomizer() { return new
 * JCacheManagerCustomizer() {
 * 
 * @Override public void customize(CacheManager cacheManager) {
 * cacheManager.createCache("studentCache", new MutableConfiguration<>()
 * .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new
 * Duration(TimeUnit.MINUTES, 5))) .setStoreByValue(false)
 * .setStatisticsEnabled(true));
 * 
 * } }; }
 */
