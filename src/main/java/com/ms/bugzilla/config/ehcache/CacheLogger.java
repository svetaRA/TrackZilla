/*
 * package com.ms.bugzilla.config.ehcache;
 * 
 * import org.ehcache.event.CacheEvent; import
 * org.ehcache.event.CacheEventListener; import org.slf4j.Logger; import
 * org.slf4j.LoggerFactory;
 * 
 * public class CacheLogger implements CacheEventListener<Object, Object> {
 * 
 * private final Logger LOG = LoggerFactory.getLogger(CacheLogger.class);
 * 
 * @Override public void onEvent(CacheEvent<Object, Object> event) { // final
 * Exchange
 * 
 * LOG.info("Key: {} | EventType: {} | Old value: {} | New value: {}",
 * event.getKey(), event.getType(), event.getOldValue(), event.getNewValue());
 * 
 * // message.setHeader(EhcacheConstants.KEY, event.getKey()); //
 * message.setHeader(EhcacheConstants.EVENT_TYPE, event.getType()); //
 * message.setHeader(EhcacheConstants.OLD_VALUE, event.getOldValue()); //
 * message.setBody(event.getNewValue());
 * 
 * } }
 */