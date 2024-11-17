package s3.ai;


import java.util.ArrayList;
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
			Pair<Double, Double> path = Start;

			PriorityQueue<Node> fila = new PriorityQueue<>(); //criacao de fila prioritaria
			fila.add(new Node(Start, 0));


			while (Passo != Goal){

				//TODO: retirar elemento da fila
				
				for (Pair<Double, Double> dir : direcoes){
					Pair<Double, Double> newPath = addPair(path, dir);


					

					//TODO: validar se newPath é viavel

					//TODO: calcular heuristica

					//TODO:calcular distancia

					//adicionar ao priorityqueue
					fila.add(new Node(newPath, 1));

				}

				break;
			}

            return null;
	}

	private Pair<Double, Double> addPair(Pair<Double, Double> a, Pair<Double, Double> b){
		double x = a.m_a + b.m_a;
		double y = a.m_b + b.m_b;

		return new Pair<Double, Double>(x, y);
	}

}
