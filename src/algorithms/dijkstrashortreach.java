package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by SoBoLp on 3/17/16.
 */
public class DijkstraShortReach {

    public static void main(String[] args) {
        BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));
        int numTests = 0;
        String next = "";
        try {
            next = in1.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        numTests = Integer.parseInt(next.split(" ")[0]);
        Graph testCasegr;
        List<String> result = new ArrayList<>();

        for (int tc = 0; tc < numTests; tc++) {
            testCasegr = new Graph();

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
            StringBuilder sb = new StringBuilder();
            for (Integer i : testCasegr.getDijkstra(testCasegr.getVertex(S))) {
                if (i != 0)
                    sb.append(i + " ");
            }
            result.add(sb.toString());
        }
        for (int i = 0; i < result.size() - 1; i++) {
            System.out.print(result.get(i));
            System.out.println();
        }
        System.out.print(result.get(result.size() - 1));
//        for (String s : result) {
//            System.out.println(s);
//        }
//        System.out.println();
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


        public HashMap<Node, Integer> dijkstra(Node start) {
            HashMap<Node, Integer> distances = new HashMap<>();
            PriorityQueue<QueueElement> queue = new PriorityQueue<>();
            HashSet<Node> visited = new HashSet<>();
            HashMap<Node, QueueElement> graphQE = new HashMap<>();
            for (Node node : this.nodeSet) {
                graphQE.put(node, new QueueElement(node, Integer.MAX_VALUE));
            }
            QueueElement startQE = graphQE.get(start);
            startQE.setDistance(0);
            queue.add(startQE);
            while (!queue.isEmpty()) {
                QueueElement current = queue.poll();
                if (!visited.contains(current.getNode())) {
                    visited.add(current.getNode());
//                    if (current.equals(goalQE)){
//                        return getPath(parent,goalQE.getNode());
//                    }
                    for (Node neighbor : current.getNode().getNeigbors()) {
                        if (!visited.contains(neighbor)) {
                            QueueElement neighborQE = graphQE.get(neighbor);
                            Integer distance_new = current.getDistance() + current.getNode().getDistance(neighbor);
                            if (distance_new <= neighborQE.getDistance()) {
                                graphQE.get(neighbor).setDistance(distance_new);
//                                queue.add(graphQE.get(neighbor));
                            }
                            queue.add(graphQE.get(neighbor));
                        }
                    }
                }
            }
            for (Node node : graphQE.keySet())
                if (graphQE.get(node).getDistance() < Integer.MAX_VALUE)
                    distances.put(node, graphQE.get(node).getDistance());
                else distances.put(node, -1);
            return distances;
        }

        public List<Integer> getDijkstra(Node start) {
            List<Integer> result = new ArrayList<>();
            HashMap<Node, Integer> distToNeighbor = dijkstra(start);
            for (int i = 0; i < nodeSet.size(); i++) {
                Node curr = nodeSet.get(i);
                if (!curr.equals(start)) {
                    if (distToNeighbor.containsKey(curr))
                        result.add(distToNeighbor.get(curr));
                    else result.add(-1);
                }
            }
            return result;
        }
    }

    private static class QueueElement implements Comparable<QueueElement> {
        private Node node;
        private Integer distance;

        public QueueElement(Node node, Integer distance) {
            this.node = node;
            this.distance = distance;
        }

        public Node getNode() {
            return node;
        }

        public Integer getDistance() {
            return distance;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }

        @Override
        public int compareTo(QueueElement o) {
            if (o.distance.equals(this.distance))
                return 1;
            if (o.distance > this.distance) {
                return -1;
            } else if (o.distance <= this.distance) {
                return 1;
            } else
                return -1;
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
    }
}

