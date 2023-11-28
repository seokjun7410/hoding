import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_1976_여행가자_success {

    private static int unionFindArr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        unionFindArr = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            unionFindArr[i] = i;
        }


        for (int i = 1; i < N + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                int value = Integer.parseInt(st.nextToken());

                if (value == 1)
                    union(i, j);
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int travelCityParent[] = new int[M];
        for (int i = 0; i < M; i++) {
             int city = Integer.parseInt(st.nextToken());
             travelCityParent[i] = find(city);
        }

        if(isPossiblePath(travelCityParent))
            System.out.println("YES");
        else
            System.out.println("NO");

    }

    private static boolean isPossiblePath(final int[] travelCityParent) {
        int firstParent = travelCityParent[0];
        boolean available = true;
        for (int j = 1; j < travelCityParent.length; j++) {
            if(firstParent != travelCityParent[j])
                available = false;
        }
        return available;
    }

    private static int find(final int a) {
        if(unionFindArr[a] == a)
            return a;

        int parent = find(unionFindArr[a]);
        return unionFindArr[a] = parent;
    }


    private static void union(final int i, final int j) {
        unionFindArr[find(i)] = unionFindArr[find(j)];
    }
}
