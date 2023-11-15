package list_client_example;

import java.util.LinkedList;
import java.util.List;

/**
 *  구체적 자료구조를 노출하지않고 추상화한 클래스입니다.
 *  필요에따라 LinkedList를 다른 자료구조료 변경하더라도 해당객체를 사용하는 외부의 변화 방지할 수 있습니다.
 */
public class MyListClientExample {
    private List list;

    public MyListClientExample(){
        list = new LinkedList<>();
    }

    public List getList(){
        return list;
    }
}
