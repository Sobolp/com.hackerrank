package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by SoBoLp on 3/16/16.
 */
public class bfsshortreach {


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
        int[] arrS = new int[numTests];
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
                        , testCasegr.getVertex(Integer.parseInt(arr1[1])));
            }
            try {
                next = in1.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            arrS[tc] = Integer.parseInt(next.split(" ")[0]);
            StringBuilder sb = new StringBuilder();
//                for (Integer i : testCasegr.getBfsToAll(testCasegr.getVertex(arrS[tc]))) {
            for (Integer i : testCasegr.getBfsToAllNaigb(testCasegr.getVertex(arrS[tc]))) {
                    if (i != 0)
                        sb.append(i + " ");
                }
                result.add(sb.toString());

        }
/*

        Scanner in = new Scanner(System.in);
        if (args.length > 0) {
            File inFile = new File(args[0]);
            try {
                in = new Scanner(inFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        int numTests = in.nextInt();
        Graph[] testCasegr = new Graph[numTests];
        List<String> result = new ArrayList<>();
        int[] arrS = new int[numTests];
        for (int tc = 0; tc < numTests; tc++) {
            testCasegr[tc] = new Graph();
            int N = in.nextInt();
            int M = in.nextInt();
            for (int i = 0; i < N; i++) {
                testCasegr[tc].addVertex(new Node(i + 1));
            }
            for (int i = 0; i < M; i++) {
                testCasegr[tc].addEdge(testCasegr[tc].getVertex(in.nextInt()), testCasegr[tc].getVertex(in.nextInt()));
            }
            arrS[tc] = in.nextInt();

        }
*/


        for (String s : result) {
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
//        ./txt/bfsshortreach/input05.txt
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

        public Integer bfsDist(Node start, Node goal) {
            HashMap<Node, Node> parent = new HashMap<>();
            HashMap<Node, Integer> dist = new HashMap<>();
            LinkedList<Node> queue = new LinkedList<>();
            HashSet<Node> visited = new HashSet<>();
            queue.add(start);
            visited.add(start);
            Integer distToNext = 0;
            while (!queue.isEmpty()) {
                Node curr = queue.removeFirst();
                if (curr == goal) {
                    // return path
                    return getPathDist(parent, goal);
                }
                distToNext +=6;
                for (Node neighbor : curr.getNeigbors()) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        parent.put(neighbor, curr);
                        dist.put(neighbor,distToNext);
                        queue.add(neighbor);
                    }
                }
            }
            return null;
        }

        public HashMap<Node, Integer> bfsDist(Node start) {
            HashMap<Node, Integer> dist = new HashMap<>();
            LinkedList<Node> queue = new LinkedList<>();
            HashSet<Node> visited = new HashSet<>();
            queue.add(start);
            visited.add(start);
            Integer distToNext = 6;
            while (!queue.isEmpty()) {
                Node curr = queue.removeFirst();
                if (!curr.equals(start))
                    distToNext = dist.get(curr) + 6;
                for (Node neighbor : curr.getNeigbors()) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        if (!dist.containsKey(neighbor))
                            dist.put(neighbor,distToNext);
                        queue.add(neighbor);
                    }
                }
            }
            return dist;
        }
        private Integer getPathDist(HashMap<Node, Node> parent, Node goal) {
            Integer result = 0;
            Node curr = goal;
            while (curr != null) {
                Node next = parent.get(curr);
                if (next != null)
                    result += curr.getDistance(next);
                curr = next;
            }
            return result;
        }

        private List<Node> getPath(HashMap<Node, Node> parent, Node goal) {
            List<Node> result = new LinkedList<>();
            Node next = goal;
            do {
                result.add(next);
                next = parent.get(next);
            } while ((next != null));
//            Collections.reverse(result);
            return result;
        }

        private Integer getShort(Node start, Node goal, boolean is) {
            if (!is) return null;
            if (start.equals(goal)) return 0;
            if (start.getNumEdges() == 0) return -1;
            Integer result = bfsDist(start, goal);
            if (result == null) return -1;
            else
                return result;
        }

        private Integer getShort(Node start, Node goal) {

            if (start.equals(goal)) return 0;
            if (start.getNumEdges() == 0) return -1;
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
//                result.add(getShort(start, nodeSet.get(i)));
                result.add(getShort(start, nodeSet.get(i),true));
            }
            return result;
        }
        public List<Integer> getBfsToAllNaigb(Node start) {
            List<Integer> result = new ArrayList<>();
            HashMap<Node,Integer> distToNaigb = bfsDist(start);
            for (int i = 0; i < nodeSet.size(); i++) {
                Node curr = nodeSet.get(i);
                if (curr != start) {
                    if (distToNaigb.containsKey(curr))
                        result.add(distToNaigb.get(curr));
                    else result.add(-1);
                }
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
        static final int DEFAULT_LENGTH = 6;
        private Node start;
        private Node end;
        private int length;

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
}
