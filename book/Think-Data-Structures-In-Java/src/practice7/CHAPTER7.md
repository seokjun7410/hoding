# 7. 철학으로 가는 길

### Iterator를 활용한 반복적 DFS
```java
public class WikiNodeIterable implements Iterable<Node>{
    private Node root;
    public WikiNodeIterable(Node root){
        this.root = root;
    }
    @Override
    public Iterator<Node> iterator(){
        return new WikiNodeIterator(root);
    }
}
```
```java
private class WikiNodeIterator implements Iterator<Node>{
    Deque<Node> stack;
    public WikiNodeIterator(){
        stack = new AraayDeqque<Node>();
        stack.push(root);
    }
    
    @Override
    public boolean hasNext(){
        return !stack.isEmpty();
    }

    @Override
    public Node next(){
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        
        Node node = stack.pop();
        List<Node> nodes = new ArrayList<Node>(node.childNodes());
        Collections.reverse(nodes);
        for(Node child : nodes){
            stack.push(child);
        }
        return node;
    }
}
```

- 노드를 반복적으로 탐색하는 로직과, 그 노드를 처리하는 로직을 분리할 수 있다.

### Fetcher 클래스 만 들기
- 크롤링 특성상 적당한 딜레기아 없으면 서비스 약관을 위반할 수 있다.
```java
public class WikiFetcher {
    private long lastRequestTime = -1;
    private long minInterval = 1000;

    public Elements fetchWikipedia(String url) throws IOException {
        sleepIfNeeded();

        Connection conn = Jsoup.connect(url);
        Document doc = conn.get();
        Element content = doc.getElementById("mw-content-text");
        Elements paras = content.select("p");
        return paras;
    }

    private void sleepIfNeeded() {
        if (lastRequestTime != -1) {
            long currentTime = System.currentTimeMillis();
            long nextRequestTime = lastRequestTime + minInterval;
            if (currentTime < nextRequestTime) {
                try {
                    Thread.sleep(nextRequestTime - currentTime);
                } catch (InterruptedException e) {
                    System.err.println("Warning: sleep interrupted in fetchWikipedia.");
                }
            }
        }
        lastRequestTime = System.currentTimeMillis();
    }
}
```
- Fetcher의 딜레이 시간을 멀티스레드 환경에서도 보장하려면 싱글톤으로 생성하여아 한다.
