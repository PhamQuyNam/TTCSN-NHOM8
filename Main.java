import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    private final List<Set<Integer>> graph;


    public Main(List<Set<Integer>> graph) {
        this.graph = graph;
    }

    public List<Set<Integer>> findMaximalCliques() {
        List<Set<Integer>> maximalCliques = new ArrayList<>();
        Set<Integer> currentClique = new HashSet<>();
        findMaximalCliques(currentClique, new HashSet<>(graph.size()), maximalCliques);
        return maximalCliques;
    }

    private void findMaximalCliques(Set<Integer> currentClique, Set<Integer> remainingVertices, List<Set<Integer>> maximalCliques) {
        if (remainingVertices.isEmpty()) {
            maximalCliques.add(new HashSet<>(currentClique));
            return;
        }

        for (Integer vertex : remainingVertices) {
            Set<Integer> neighbors = graph.get(vertex);
            if (currentClique.containsAll(neighbors)) {
                Set<Integer> newCurrentClique = new HashSet<>(currentClique);
                newCurrentClique.add(vertex);
                Set<Integer> newRemainingVertices = new HashSet<>(remainingVertices);
                newRemainingVertices.remove(vertex);
                findMaximalCliques(newCurrentClique, newRemainingVertices, maximalCliques);
            }
        }
    }

    public static void main(String[] args) {
        // Ví dụ đồ thị
        List<Set<Integer>> graph = new ArrayList<>();
        graph.add(Set.of(1, 2, 3));
        graph.add(Set.of(1, 2));
        graph.add(Set.of(1, 3));
        graph.add(Set.of(2, 3));

        Main finder = new Main(graph);
        List<Set<Integer>> maximalCliques = finder.findMaximalCliques();

        System.out.println("Các clique cực đại:");
        for (Set<Integer> clique : maximalCliques) {
            System.out.println(clique);
        }
    }
}