package GraphTheoryChallenges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by SoBoLp on 3/18/16.
 */
public class PrimsMSTSub {

    public static void main(String[] args) {
        BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));

        String next ="";
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
            System.out.println(testCasegr.getPrimsMST(testCasegr.getVertex(S)));

        }


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

        public void addEdge(Node from, Node to, Integer distance) {
            Edge road = new Edge(from, to, distance);
            from.addEdge(road);
            if (backRoad) {
                Edge roadEdgeBack = new Edge(to, from, distance);
                to.addEdge(roadEdgeBack);
            }
        }


        public HashMap<Node, Edge> primsMST(Node start) {
            HashMap<Node, Edge> distances = new HashMap<>();
            PriorityQueue<Edge> queue = new PriorityQueue<>();
            HashSet<Node> visited = new HashSet<>();
            for (Node neighbor: start.getNeigbors())
                queue.add(start.getEdge(neighbor));
            visited.add(start);
            while (!queue.isEmpty()) {
                Edge current = queue.poll();
                if (!visited.contains(current.getEnd())) {
                    distances.put(current.getEnd(),current);
                    visited.add(current.getEnd());
                    if (visited.size() == nodeSet.size()){
                        return distances;
                    }
                    for (Node neighbor : current.getEnd().getNeigbors()) {
                        if (!visited.contains(neighbor)) {
                            queue.add(current.getEnd().getEdge(neighbor));
                        }
                    }
                }
            }
            return distances;
        }

        public Integer getPrimsMST(Node start) {
            Integer result = 0;
            HashMap<Node, Edge> distToNeighbor = primsMST(start);
            for (Node node:distToNeighbor.keySet()){
                result += distToNeighbor.get(node).getLength();
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
            if (!edgeHashMap.containsKey(next.getEnd()))
                edgeHashMap.put(next.getEnd(), next);
            else if (next.getLength() < edgeHashMap.get(next.getEnd()).getLength())
//                edgeHashMap.replace(next.getEnd(), next);
            {
                edgeHashMap.remove(next.getEnd());
                edgeHashMap.put(next.getEnd(), next);
            }
        }
        public Edge getEdge(Node neighbor){
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

    private static class Edge implements Comparable<Edge>{
        //        static final int DEFAULT_LENGTH = 6;
        private Node start;
        private Node end;
        private int length;

        public Edge(Node start, Node end, int length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }

//        public Edge(Node start, Node end) {
//            this(start, end, DEFAULT_LENGTH);
//        }

        public Node getEnd() {
            return end;
        }

        public int getLength() {
            return length;
        }

        @Override
        public int compareTo(Edge o) {
            if (o.length > this.length) {
                return -1;
            } else if (o.length <= this.length) {
                return 1;
            } else
                return -1;
        }
    }
}
