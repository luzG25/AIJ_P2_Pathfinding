package s3.ai;

import s3.util.Pair;

class Node implements Comparable<Node> {
    Pair<Double, Double> position; // Posição do nó
    double cost;                   // Custo acumulado até aqui
    double heuristic;              // Heurística estimada

    public Node(Pair<Double, Double> position, double cost, double heuristic) {
        this.position = position;
        this.cost = cost;
        this.heuristic = heuristic;
    }

    // Retorna o valor f(n) = custo + heurística
    public double getPriority() {
        return cost + heuristic;
    }

    @Override
    public int compareTo(Node other) {
        // Compara baseado no valor f(n)
        return Double.compare(this.getPriority(), other.getPriority());
    }
}
