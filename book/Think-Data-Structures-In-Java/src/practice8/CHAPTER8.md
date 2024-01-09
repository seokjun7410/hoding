# 8. 인덱서

## TermCounter.class
- 검색어와 검색어가 페이지에서 등장하는 횟수 매핑
```java
public class TermCounter {

    private Map<String, Integer> map;
    private String label;

    public TermCounter(String label) {
        this.label = label;
        this.map = new HashMap<String, Integer>();
    }

    public String getLabel() {
        return label;
    }

    public int size() {
        int total = 0;
        for (Integer value : map.values()) {
            total += value;
        }
        return total;
    }

    //각 노드에 대해서 processTree를 실행합니다.
    public void processElements(Elements paragraphs) {
        for (Node node : paragraphs) {
            processTree(node);
        }
    }

    //텍스트를 포함한 노드를 찾아 텍스트를 추출하고 processText를 실행합니다.
    public void processTree(Node root) {
        for (Node node : new WikiNodeIterable(root)) {
            if (node instanceof TextNode) {
                processText(((TextNode) node).text());
            }
        }
    }

    //정규표현식을 이용해서 단어를 추출하고 단어별로 카운팅을 합니다.
    public void processText(String text) {
        String[] array = text.replaceAll("\\pP", " ").
                toLowerCase().
                split("\\s+");

        for (int i = 0; i < array.length; i++) {
            String term = array[i];
            incrementTermCount(term);
        }
    }

    //get이 존재하지 않을경우 0을 반환하므로 쉽게 구성가능
    public void incrementTermCount(String term) {
        put(term, get(term) + 1);
    }

    // 단순 래퍼 메소드
    public void put(String term, int count) {
        map.put(term, count);
    }

    //존재하지 않을경우 0 반환
    public Integer get(String term) {
        Integer count = map.get(term);
        return count == null ? 0 : count;
    }


    public Set<String> keySet() {
        return map.keySet();
    }

    public void printCounts() {
        for (String key : keySet()) {
            Integer count = get(key);
            System.out.println(key + ", " + count);
        }
        System.out.println("Total of all counts = " + size());
    }
}    
```
### 실행
```java
    public static void main(String[] args) throws IOException {
        String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";

        WikiFetcher wf = new WikiFetcher();
        Elements paragraphs = wf.fetchWikipedia(url);

        TermCounter counter = new TermCounter(url.toString());
        counter.processElements(paragraphs);
        counter.printCounts();
    }
```

## Index.class
### 코드
```java

public class Index {

    //검색어 : termCounter
    private Map<String, Set<TermCounter>> index = new HashMap<String, Set<TermCounter>>();


    public void add(String term, TermCounter tc) {
        Set<TermCounter> set = get(term);
        //검색어를 처음 찾을경우 새로운 Set을 생성합니다.
        if (set == null) {
            set = new HashSet<TermCounter>();
            index.put(term, set);
        }
        
        //termCount를 갱신합니다.
        set.add(tc);
    }


    public Set<TermCounter> get(String term) {
        return index.get(term);
    }


    public void printIndex() {
        for (String term : keySet()) {
            System.out.println(term);

            Set<TermCounter> tcs = get(term);
            for (TermCounter tc : tcs) {
                Integer count = tc.get(term);
                System.out.println("    " + tc.getLabel() + " " + count);
            }
        }
    }


    public Set<String> keySet() {
        return index.keySet();
    }
    
    //인덱스페이지 생성
    public void indexPage(String url, Elements paragraphs) {
        TermCounter tc = new TermCounter(url);
        tc.processElements(paragraphs);

        for (String term : tc.keySet()) {
            add(term, tc);
        }
    }

}
```
```java

	public static void main(String[] args) throws IOException {
		
		WikiFetcher wf = new WikiFetcher();
		Index indexer = new Index();

		String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
		Elements paragraphs = wf.fetchWikipedia(url);
		indexer.indexPage(url, paragraphs);
		
		url = "https://en.wikipedia.org/wiki/Programming_language";
		paragraphs = wf.fetchWikipedia(url);
		indexer.indexPage(url, paragraphs);
		
		indexer.printIndex();
	}
```