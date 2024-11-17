package s3.ai;

import s3.util.Pair;

public class Node implements Comparable<Node> {
    Pair<Double, Double> path;
    double cost;

    public Node(Pair<Double, Double> path, double cost) {
        this.path = path;
        this.cost = cost;
    }

    // Método para comparar dois nós com base no custo
    @Override
    public int compareTo(Node other) {
        return Double.compare(this.cost, other.cost);
    }
    
    @Override
    public String toString() {
        return path.m_a + ":" + path.m_b + " (Custo: " + cost + ")";
    }
} 
