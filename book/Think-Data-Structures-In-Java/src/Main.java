import list_client_example.MyListClientExample;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        MyListClientExample listClientExample = new MyListClientExample();
        List list = listClientExample.getList();
        System.out.println(list);
    }
}