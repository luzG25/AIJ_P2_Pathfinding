package s3.ai;


import java.util.ArrayList;
import java.util.List;
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

			Start = new Pair<Double,Double>(start_x, start_y);
			Goal = new Pair<Double, Double>(goal_x, goal_y);
			game = the_game;
			object = i_entity;			
	}

	public List<Pair<Double, Double>> computePath() {
            // ...

			List<Pair<Double, Double>> path = new ArrayList<>();
			path.add(Start);

			while (Passo != Goal){
				//frente

				//esquerda

				//direita

				//atras

				break;
			}

            return null;
	}

}
