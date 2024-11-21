package s3.ai;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import s3.base.S3;
import s3.entities.S3PhysicalEntity;
import s3.util.Pair;


//Implementar codigo aqui

public class AStar {
	Pair<Double, Double> Start;
	Pair<Double, Double> Goal;
	Pair<Double, Double> Passo;
	S3PhysicalEntity object;
	S3 game;

	private final List<Pair<Double, Double>> Direcoes(){
		List<Pair<Double, Double>> out = new ArrayList<>();

		out.add(new Pair<Double,Double>(1.0,0.0)); // frente
		out.add(new Pair<Double,Double>(-1.0,0.0)); // tras
		out.add(new Pair<Double,Double>(0.0,1.0)); // esquerda
		out.add(new Pair<Double,Double>(0.0,-1.0)); // direita
		 
		return out;
	}

	//caluclar manhattan heuristica
	//h(n) = |x1 - x2| + |y1 - y2|
	private double heuristica(Pair<Double, Double> newPath){
		return Math.abs(newPath.m_a - this.Goal.m_a) + Math.abs(newPath.m_b - this.Goal.m_b);
	}
	
	
	public static int pathDistance(double start_x, double start_y, double goal_x, double goal_y,
			S3PhysicalEntity i_entity, S3 the_game) {
		AStar a = new AStar(start_x,start_y,goal_x,goal_y,i_entity,the_game);
		List<Pair<Double, Double>> path = a.computePath();
		if (path!=null) return path.size();
		return -1;
	}

	public AStar(double start_x, double start_y, double goal_x, double goal_y,
			S3PhysicalEntity i_entity, S3 the_game) {
            // ...

			this.Start = new Pair<Double,Double>(start_x, start_y);
			this.Goal = new Pair<Double, Double>(goal_x, goal_y);
			this.game = the_game;
			this.object = i_entity;			
	}

	public List<Pair<Double, Double>> computePath() {
            // ...

			List<Pair<Double, Double>> direcoes = Direcoes();
			Pair<Double, Double> path = this.Start;
			HashMap<Pair<Double, Double>, Pair<Double, Double>> allPaths = new HashMap<>();  //filho: mae

			PriorityQueue<Node> fila = new PriorityQueue<>(); //criacao de fila prioritaria
			fila.add(new Node(path, 0, heuristica(path)));
			System.out.println(this.object.entityID + " está a " + heuristica(path));


			while (!fila.isEmpty()){

				//retirar elemento da fila
				Node node = fila.poll();
				path = node.position;
				path.print(heuristica(path));

				if (path.isEqual(Goal)){
					//retornar caminho
					List<Pair<Double, Double>> Path = new ArrayList<>();
					System.out.println("Encontrou o Goal");

					while (!path.isEqual(Start)){
						Path.add(allPaths.get(path));
						path = allPaths.get(path);
					}

					 // Retorna o caminho invertido
					Collections.reverse(Path);
					return Path;
				}

				for (Pair<Double, Double> dir : direcoes){

					
					Pair<Double, Double> newPath = addPair(path, dir);

					if (allPaths.containsValue(newPath)) continue;
					newPath.print(heuristica(newPath));


					//System.out.println(allPaths.containsKey(newPath));//959 97 01-> Stefany Rocha Estudante de Direito

					//validar se newPath é viavel
					if (game.isSpaceFree(1, newPath.m_a.intValue(), newPath.m_b.intValue()) && !allPaths.containsValue(newPath))
					{
						//calcular heuristica
						double heurist = heuristica(newPath);

						//calcular custo
						double custo = node.cost + 1;

						//adicionar ao priorityqueue
						fila.add(new Node(newPath ,custo, heurist));

						//registar filho da mae
						allPaths.put(newPath, path);//filho : mae
					}
				}

			}

            return null;
	}

	private Pair<Double, Double> addPair(Pair<Double, Double> a, Pair<Double, Double> b){
		double x = a.m_a + b.m_a;
		double y = a.m_b + b.m_b;

		return new Pair<Double, Double>(x, y);
	}

}
