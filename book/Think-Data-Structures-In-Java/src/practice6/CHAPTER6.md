# 6. 트리순회

#### 웹 크롤러
- DOM트리를 파싱하여 다운로드 하는 것
- DOM 트리는 노드로 이루어진 링크드 자료구조 이다.
- 각 노드는 텍스트,태그, 그 외 요소로 나뉜다.

#### JSOUP으로 DOM트리 다루기
```java
// <div id = "mv-content-text"> 요소를 찾는다.
Connection conn = Jsoup.connect(url);
Document doc = conn.get();
Element content = doc.getElementById("mw-content-text");
contet.select("p")
```

### 깊이 우선탐색
```java
// 재귀식 DFS 핵심개념 - sudo code
recursiveDFS(Node node)
    for Node child : parent.child
        recursiveDFS(child)
```
```java
// 반복적 DFS 핵심개념 - sudo code
iterativeDFS(Node root)
    while(!stack.isEmpty())
        for(Node child : parent.child)
            stack.push(child);
    // iterator로 구현하기 쉽다.
```
- 전위 순회 : 방문처리 후 순회한다.
```java
//pre-order - sudo code
    procedure preorder(node)
        if node = null
            return
        visit(node)
        preorder(node.left)
        preorder(node.right) 
```
- 후위 순회 : 순회 후 방문처리한다.
```java
//post-order - sudo code
    procedure postorder(node)
        if node = null
            return
        postorder(node.left)
        postorder(node.right)
        visit(node)
```
- 중위 순회 : 순회 중간에 방문처리한다.
```java
//In order - sudo code
    procedure inorder(node)
        if node = null
            return
        inorder(node.left)
        visit(node)
        inorder(node.right)
```