package GraphTheoryChallenges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by SoBoLp on 3/18/16.
 */
public class KruskalMSTRSub {

    public static void main(String[] args) {
        BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));

        String next = "";
        Graph testCasegr = new Graph();

        try {
            next = in1.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] arr = next.split(" ");
        int N = Integer.parseInt(arr[0]);
        int M = Integer.parseInt(arr[1]);
        for (int i = 0; i < N; i++) {
            testCasegr.addVertex(new Node(i + 1));
        }

        for (int i = 0; i < M; i++) {
            try {
                next = in1.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] arr1 = next.split(" ");
            testCasegr.addEdge(testCasegr.getVertex(Integer.parseInt(arr1[0]))
                    , testCasegr.getVertex(Integer.parseInt(arr1[1]))
                    , Integer.parseInt(arr1[2]));
        }

        try {
            next = in1.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int S = Integer.parseInt(next.split(" ")[0]);
        System.out.print(testCasegr.getkruskalMST());
    }

    private static class Graph {
        private List<Node> nodeSet;
        private List<Edge> edgeSet;
        private boolean backRoad = true; // need for consider edge endNode to startNode too (back Road)

        public Graph() {
            this.nodeSet = new ArrayList<>();
            this.edgeSet = new ArrayList<>();
        }

        public void addVertex(Node node) {
            nodeSet.add(node);
        }

        public Node getVertex(int name) {
            return nodeSet.get(name - 1);
        }

        public void addEdge(Node from, Node to, Integer distance) {
            Edge road = new Edge(from, to, distance);
            from.addEdge(road);
            edgeSet.add(road);
            if (backRoad) {
                Edge roadEdgeBack = new Edge(to, from, distance);
                to.addEdge(roadEdgeBack);
                edgeSet.add(roadEdgeBack);
            }
        }


        public List<Edge> kruskalMST() {
            Union <Node>  visitedU = new Union<>();
            List<Edge> distances = new ArrayList<>();
            PriorityQueue<Edge> queue = new PriorityQueue<>();
//            HashSet<Node> visited = new HashSet<>();
            for (Edge edge : edgeSet)
                queue.add(edge);
            while (!queue.isEmpty()) {
                Edge current = queue.poll();
                if (!visitedU.connected(current.getStart(),current.getEnd())){
                    visitedU.union(current.getStart(),current.getEnd());
                    distances.add(current);
                }
                if (distances.size() - 1 == nodeSet.size()) {
                    return distances;
                }
            }
            return distances;
        }

        public Integer getkruskalMST() {
            Integer result = 0;
            List<Edge> distToNeighbor = kruskalMST();
            for (Edge edge : distToNeighbor) {
                result += edge.getLength();
            }
            return result;
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

    private static class Node {
        private int name;
        private HashMap<Node, Edge> edgeHashMap;

        public Node(int name) {
            this.name = name;
            edgeHashMap = new HashMap<Node, Edge>();
        }

        public void addEdge(Edge next) {
            if (!edgeHashMap.containsKey(next.getEnd()))
                edgeHashMap.put(next.getEnd(), next);
            else if (next.getLength() <= edgeHashMap.get(next.getEnd()).getLength())
                edgeHashMap.replace(next.getEnd(), next);
        }

        public Edge getEdge(Node neighbor) {
            return edgeHashMap.get(neighbor);
        }

        public int getNumEdges() {
            return edgeHashMap.size();
        }

        public Set<Node> getNeigbors() {
            return edgeHashMap.keySet();
        }

        public int getDistance(Node neighbor) {
            return edgeHashMap.get(neighbor).getLength();
        }
    }

    private static class Edge implements Comparable<Edge> {
        private Node start;
        private Node end;
        private int length;

        public Edge(Node start, Node end, int length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }

        public Node getStart() {
            return start;
        }

        public Node getEnd() {
            return end;
        }

        public int getLength() {
            return length;
        }

        @Override
        public int compareTo(Edge o) {
            if (o.length == this.length)
                return 0;
            if (o.length > this.length)
                return -1;
            else if (o.length < this.length)
                return 1;
            else
                return -1;
        }
    }
}
