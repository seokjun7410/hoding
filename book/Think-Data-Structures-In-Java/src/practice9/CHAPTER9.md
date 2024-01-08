# 9. Map Interface

## MyLinearMap
- 모든 메소드가 선형이다
- 엔트리 갯수가 작다면 쓸만하지만 핵심 메소드가 상수시간인 Map이 존재한다.
```java
public class MyLinearMap<K, V> implements Map<K, V> {

    private List<Entry> entries = new ArrayList<Entry>();

    //Key,value의 컨테이너 클래스
    public class Entry implements Map.Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }
        @Override
        public V getValue() {
            return value;
        }
        @Override
        public V setValue(V newValue) {
            value = newValue;
            return value;
        }
    }

    @Override
    public void clear() {
        entries.clear();
    }

    @Override
    public boolean containsKey(Object target) {
        return findEntry(target) != null;
    }

   //실행속도는 entry갯수에 의존 = 선형
    private Entry findEntry(Object target) {
        for (Entry entry: entries) {
            if (equals(target, entry.getKey())) {
                return entry;
            }
        }
        return null;
    }
    
    //실행속도는 target과 obj 크기에 의존 = 상수시간
    private boolean equals(Object target, Object obj) {
        if (target == null) {
            return obj == null;
        }
        return target.equals(obj);
    }

    @Override
    public boolean containsValue(Object target) {
        for (Map.Entry<K, V> entry: entries) {
            if (equals(target, entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    //put과 같은이유로 선형
    @Override
    public V get(Object key) {
        Entry entry = findEntry(key);
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }

    @Override
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<K>();
        for (Entry entry: entries) {
            set.add(entry.getKey());
        }
        return set;
    }

    //findEntry를 제외하고는 모두 상수시간 
    //전체적으로 선형시간
    @Override
    public V put(K key, V value) {
        Entry entry = findEntry(key);
        if (entry == null) {
            //entries는 arrayList객체이므로 상수시간
            entries.add(new Entry(key, value));
            return null;
        } else {
            V oldValue = entry.getValue(); //상수시간
            entry.setValue(value); //상수시간
            return oldValue;
        }
    }
    
    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry: map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    //entries.remove(entry); entry가 위치한 곳에 따라 선형일수도 있다.
    //remove는 최악에 경우에도 선형이다.
    @Override
    public V remove(Object key) {
        Entry entry = findEntry(key);
        if (entry == null) {
            return null;
        } else {
            V value = entry.getValue();
            entries.remove(entry);
            return value;
        }
    }

    @Override
    public int size() {
        return entries.size();
    }

    @Override
    public Collection<V> values() {
        Set<V> set = new HashSet<V>();
        for (Entry entry: entries) {
            set.add(entry.getValue());
        }
        return set;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Map<String, Integer> map = new MyLinearMap<String, Integer>();
        map.put("Word1", 1);
        map.put("Word2", 2);
        Integer value = map.get("Word1");
        System.out.println(value);

        for (String key: map.keySet()) {
            System.out.println(key + ", " + map.get(key));
        }
    }

    /**
     * Returns a reference to `entries`.
     *
     * This is not part of the Map interface; it is here to provide the functionality
     * of `entrySet` in a way that is substantially simpler than the "right" way.
     *
     * @return
     */
    protected Collection<? extends java.util.Map.Entry<K, V>> getEntries() {
        return entries;
    }
}
```
## 상수시간 Map
- 엔트리를 하나의 List에 저장하지 않고 다수의 작은 리스트로 쪼갠다.
- 각키에 대해서 해시코드를 사용하여 어느 리스트를 사용할지 선택한다.
- 