package algorithms;

import java.util.*;

/**
 * Created by SoBoLp on 3/16/16.
 */
public class bfsshortreach {


    private static class Graph {
        private List<Node> nodeSet;
        private boolean backRoad = true; // need for consider edge endNode to startNode too (back Road)

        public Graph() {
            this.nodeSet = new ArrayList<>();
        }

        public void addVertex(Node node) {
            nodeSet.add(node);
        }

        public Node getVertex(int name) {
            return nodeSet.get(name - 1);
        }

        public void addEdge(Node from, Node to) {
            Edge road = new Edge(from, to);
            from.addEdge(road);
            if (backRoad) {
                Edge roadEdgeBack = new Edge(to, from);
                to.addEdge(roadEdgeBack);
            }
        }

        public List<Node> bfs(Node start, Node goal) {
            HashMap<Node, Node> parent = new HashMap<>();
            LinkedList<Node> queue = new LinkedList<>();
            HashSet<Node> visited = new HashSet<>();
            queue.add(start);
            visited.add(start);
            while (!queue.isEmpty()) {
                Node curr = queue.removeFirst();
                if (curr == goal) {
                    // return path
                    return getPath(parent, goal);
                }
                for (Node neighbor : curr.getNeigbors()) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        parent.put(neighbor, curr);
                        queue.add(neighbor);
                    }
                }
            }
            return null;
        }

        private List<Node> getPath(HashMap<Node, Node> parent, Node goal) {
            List<Node> result = new LinkedList<>();
            Node next = goal;
            do {
                result.add(next);
                next = parent.get(next);
            } while ((next != null));
            Collections.reverse(result);
            return result;
        }

        private Integer getShort(Node start, Node goal) {

            if (start.equals(goal)) return 0;
            List<Node> path = bfs(start, goal);
            if (path == null) return -1;
            else {
                Integer result = 0;
                Node current = path.get(0);
                for (int i = 1; i < path.size(); i++) {
                    result += current.getDistance(path.get(i));
                    current = path.get(i);
                }
                return result;
            }
        }

        public List<Integer> getBfsToAll(Node start) {
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < nodeSet.size(); i++) {
                result.add(getShort(start, nodeSet.get(i)));
            }
            return result;
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
            edgeHashMap.put(next.getEnd(), next);
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

    private static class Edge {
        private Node start;
        private Node end;
        private int length;
        static final int DEFAULT_LENGTH = 6;

        public Edge(Node start, Node end, int length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }

        public Edge(Node start, Node end) {
            this(start, end, DEFAULT_LENGTH);
        }

        public Node getEnd() {
            return end;
        }

        public int getLength() {
            return length;
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int numTests = in.nextInt();
//        Graph[] testCasegr = new Graph[numTests];
        List<String> result = new ArrayList<>();
        for (int tc = 0; tc < numTests; tc++) {
            Graph testCasegr = new Graph();
            int N = in.nextInt();
            int M = in.nextInt();
            for (int i = 0; i < N; i++) {
                testCasegr.addVertex(new Node(i + 1));
            }
            for (int i = 0; i < M; i++) {
                testCasegr.addEdge(testCasegr.getVertex(in.nextInt()), testCasegr.getVertex(in.nextInt()));
            }
            int S = in.nextInt();
            StringBuilder sb = new StringBuilder();
            for (Integer i : testCasegr.getBfsToAll(testCasegr.getVertex(S))) {
                if (i != 0)
                    sb.append(i+" ");
            }
            result.add(sb.toString());
        }
        for (String s:result) {
            System.out.println(s);
        }
        /*
        tastCase:
        2
        4 2
        1 2
        1 3
        1
        3 1
        2 3
        2
*/
    }
}
