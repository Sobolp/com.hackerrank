package GraphTheoryChallenges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SoBoLp on 3/18/16.
 *
 * https://www.hackerrank.com/challenges/even-tree
 */
public class EvenTree {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String next = in.readLine();
        String[] arr = next.split(" ");
        int N = Integer.parseInt(arr[0]);
        int M = Integer.parseInt(arr[1]);
        WeightQUUF testCasegr = new WeightQUUF(N+1);
        for (int i = 0; i<M; i++){
            next = in.readLine();
            String[] arr1 = next.split(" ");
            int q = Integer.parseInt(arr1[0]);
            int p = Integer.parseInt(arr1[1]);
            if (!testCasegr.connected(p,q))
                testCasegr.union(p,q);
        }
        System.out.println(testCasegr);
    }

    private static class TreeGr<T>{
        private Node <T> root;
        private int count;

        public TreeGr(Node<T> root) {
            this.root = root;
            this.count = 1;
        }
        public void addNode(Node<T> node){
            root.addChild(node);
            count++;
        }
        public int getSize(){
            return count;
        }
    }

    private static class Node<T>{
        private int name;
        private Node<T> perent;
        private List<Node<T>> children;

        public Node(int name) {
            this.name = name;
            this.perent = null;

        }

        public Node<T> getPerent() {
            return perent;
        }

        public void setPerent(Node<T> perent) {
            this.perent = perent;
        }

        public void addChild(Node<T> child){
            child.setPerent(this);
            children.add(child);
        }

        public int getNumChildren(){
            return children.size();
        }

        public List<Node<T>> getChildren() {
            return children;
        }
    }

    private static class Union<T> {
        private Map<T, T> parent;

        public Union() {
            this.parent = new HashMap<>();
        }

        public T root(T element) {
            T next = element;
            T prev = null;
            while ((next != null)) {
                prev = next;
                next = parent.get(next);
            }
            return prev;
        }

        public boolean connected(T p, T q) {
            return root(p) == root(q);
        }

        public void union(T p, T q) {
            T i = root(p);
            T j = root(q);
            parent.put(i, j);
        }
    }

        private static class WeightQUUF {
        private int[] id;
        private int[] sz;

        public WeightQUUF(int N) {
            id = new int[N];
            sz = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
                sz[i] = 1;
            }
        }

        private int root(int i) {
            while (i != id[i])
                i = id[i];
            return i;
        }

        void union(int p, int q) {
            int i = root(p);
            int j = root(q);
            if (i == j)
                return;
            if (sz[i] < sz[j]) {
                id[i] = j;
                sz[j] += sz[i];
            } else {
                id[j] = i;
                sz[i] += sz[j];
            }
        }

        boolean connected(int p, int q) {
            return root(p) == root(q);
        }

        @Override
        public String toString() {
            String resalt = "{ ";
            for (int i : id) {
                resalt += i + " ";
            }
            resalt += "}";
            return resalt;
        }
    }
}
