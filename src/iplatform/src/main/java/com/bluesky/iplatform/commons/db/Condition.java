package com.bluesky.iplatform.commons.db;

import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * HQL/SQL 增删改查的条件
 * @author ElwinHe
 *
 * @param <K>
 * @param <V>
 */
@Component(value="Condition")
@Scope(value="prototype")
public class Condition<K, V> extends HashMap<K, V> {

}
