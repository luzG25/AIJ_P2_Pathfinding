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
	Pair<Integer, Integer> Start;
	Pair<Integer, Integer> Goal;
	Pair<Integer, Integer> Passo;
	S3PhysicalEntity object;
	S3 game;

	private final List<Pair<Integer, Integer>> Direcoes() {
		List<Pair<Integer, Integer>> out = new ArrayList<>();
	
		// Movimentos ortogonais
		out.add(new Pair<>(1, 0));   // Frente
		out.add(new Pair<>(-1, 0));  // Trás
		out.add(new Pair<>(0, 1));   // Esquerda
		out.add(new Pair<>(0, -1));  // Direita
	
		// Movimentos diagonais
		out.add(new Pair<>(1, 1));   // Nordeste
		out.add(new Pair<>(1, -1));  // Sudeste
		out.add(new Pair<>(-1, 1));  // Noroeste
		out.add(new Pair<>(-1, -1)); // Sudoeste
	
		return out;
	}	
	

	//caluclar manhattan heuristica
	//h(n) = |x1 - x2| + |y1 - y2|
	private double heuristica(Pair<Integer, Integer> newPath){
		return Math.abs(newPath.m_a - this.Goal.m_a) + Math.abs(newPath.m_b - this.Goal.m_b);
	}
	
	
	public static int pathDistance(int start_x, int start_y, int goal_x, int goal_y,
			S3PhysicalEntity i_entity, S3 the_game) {
		AStar a = new AStar(start_x,start_y,goal_x,goal_y,i_entity,the_game);
		List<Pair<Integer, Integer>> path = a.computePath();
		if (path!=null) return path.size();
		return -1;
	}

	public AStar(int start_x, int start_y, int goal_x, int goal_y,
			S3PhysicalEntity i_entity, S3 the_game) {
            // ...

			this.Start = new Pair<Integer, Integer>(start_x, start_y);
			this.Goal = new Pair<Integer, Integer>(goal_x, goal_y);
			this.game = the_game;
			this.object = i_entity;			
	}

	public List<Pair<Integer, Integer>> computePath() {
            // ...

			List<Pair<Integer, Integer>> direcoes = Direcoes();
			Pair<Integer, Integer> path = this.Start;
			HashMap<Pair<Integer, Integer>, Pair<Integer, Integer>> allPaths = new HashMap<>();  //filho: mae

			PriorityQueue<Node> fila = new PriorityQueue<>(); //criacao de fila prioritaria
			fila.add(new Node(path, 0, heuristica(path)));
			System.out.println(this.object.entityID + " está a " + heuristica(path));


			while (!fila.isEmpty()){

				//retirar elemento da fila
				Node node = fila.poll();
				path = node.position;
				path.print(heuristica(path));

				if (path.equals(this.Goal)){
					//retornar caminho
					List<Pair<Integer, Integer>> Path = new ArrayList<>();
					System.out.println("Encontrou o Goal");
					Path.add(path);

					while (!path.equals(this.Start)){
						Path.add(allPaths.get(path));
						path = allPaths.get(path);
					}

					 // Retorna o caminho invertido
					Collections.reverse(Path);
					return Path;
				}

				for (Pair<Integer, Integer> dir : direcoes){
					Pair<Integer, Integer> newPath = addPair(path, dir);



					//System.out.println(allPaths.containsKey(newPath));//959 97 01-> Stefany Rocha Estudante de Direito

					//validar se newPath é viavel
					if ( !allPaths.containsValue(newPath))// && game.isSpaceFree(1, newPath.m_a, newPath.m_b))
					{
						//calcular heuristica
						double heurist = heuristica(newPath);

						//calcular custo
						double custo = node.cost + 1 ;

						//adicionar ao priorityqueue
						fila.add(new Node(newPath ,custo, heurist));

						//registar filho da mae
						allPaths.put(newPath, path);//filho : mae
					}
				}

			}

            return null;
	}

	private Pair<Integer, Integer> addPair(Pair<Integer, Integer> a, Pair<Integer, Integer> b){
		int x = a.m_a + b.m_a;
		int y = a.m_b + b.m_b;

		return new Pair<Integer, Integer>(x, y);
	}

}
