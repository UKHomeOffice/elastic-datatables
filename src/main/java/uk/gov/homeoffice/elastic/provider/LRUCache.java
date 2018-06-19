package uk.gov.homeoffice.elastic.provider;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> {

    final LinkedHashMap<K, V> cache;
    final int cacheSize;

    public LRUCache(int cacheSize) {
        this.cacheSize = cacheSize;
        cache = new LinkedHashMap<K, V>(cacheSize + 1, .75F, false) {
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > cacheSize;
            }
        };
    }

    public void clear(){
        cache.clear();
    }

    public boolean contains(K key){
        return cache.containsKey(key);
    }

    public V getCache(K key) {
        return cache.get(key);
    }

    public void putCache(K key, V value) {
        cache.put(key, value);
    }

    public Collection<V> values(){
        return cache.values();
    }

}
