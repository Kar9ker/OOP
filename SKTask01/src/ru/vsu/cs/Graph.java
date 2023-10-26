package ru.vsu.cs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Graph {
    private class GraphNode {
        public List<GraphNode> edges = new ArrayList<>();
        private final TestObject object;
        public boolean visited = false;

        public GraphNode(TestObject object) {
            this.object = object;
        }
    }
    private List<GraphNode> graphNodeList = new ArrayList<>();
    private List<TestObject> testObjectList;
    public Graph(List<TestObject> testObjectList) {
        this.testObjectList = testObjectList;
        createGraph();
    }

    private void createGraph() {
        for (TestObject to : testObjectList) {
            addVertex(to);
        }
        for (TestObject to : testObjectList) {
            if (to.links == null) {
                break;
            }
            for (TestObject link : to.links) {
                addEdge(to, link);
            }
        }
    }

    private void addVertex(final TestObject obj) {
        if (obj == null) {
            System.err.println("null");
            return;
        }
        GraphNode gn = new GraphNode(obj);
        graphNodeList.add(gn);
    }

    private void addEdge(final TestObject obj1, final TestObject obj2) {
        if (obj1 == null || obj2 == null) {
            System.err.println("null");
            return;
        }
        if (obj1.equals(obj2)) {
            System.err.println("Same object");
            return;
        }
        for (GraphNode gn : graphNodeList) {
            if (gn.object.equals(obj1)) {
                gn.edges.add(getGraphNode(obj2));
            }
        }
    }

    public void showAdjacencies() {
        addSpaces();
        for (GraphNode gn : graphNodeList) {
            System.out.print(gn.object.toString() + " : ");
            if (gn.edges == null || gn.edges.size() == 0) {
                System.out.println("нет смежных вершин!\n");
                continue;
            }
            for (GraphNode t : gn.edges) {
                System.out.print(t.object.toString() + " ");
            }
            System.out.println("\n");
        }
        addSpaces();
    }
    public void collectGarbage() {
        graphTraversal(graphNodeList.get(0));
        Iterator<GraphNode> iterator = graphNodeList.iterator();
        while (iterator.hasNext()) {
            GraphNode element = iterator.next();
            if (!element.visited) {
                iterator.remove();
            }
        }

        for(GraphNode gn : graphNodeList) {
            gn.visited = false;
        }
    }
    private void graphTraversal(final GraphNode curr) {
        curr.visited = true;
        for (GraphNode adjElement : curr.edges) {
            if (!adjElement.visited) {
                graphTraversal(adjElement);
            }
        }
    }

    private GraphNode getGraphNode(final TestObject object) {
        for (GraphNode gn : graphNodeList) {
            if (gn.object.equals(object)) {
                return gn;
            }
        }
        return null;
    }

    private void addSpaces() {
        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}