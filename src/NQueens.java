import java.util.Random;
import java.util.Scanner;
import java.math.*;

public class NQueens {
	
	private static double T;
	private static double P;

	public static void main(String[] args)
	{
		int n;
		int counter;
		System.out.println("Input the number of Queens:");
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		System.out.println(n);
		
		State current = new State(n);
		State best = new State(n);
		State working;
		current.printState();
		
		
		//Variables
		T = 100;
		int num_runs = (int) (1500/T); 
		double alpha = 0.99;
		int num_solutions = 5;
		
		int min_nrg = 1000;
		int time_step = 0;
		
		while(T > 0.10 && time_step < 2000){
			time_step++;
			counter = 0;
			
			num_runs = (int) (1500/T);
			
			for(int i = 0; i < num_runs; i++){
				working = current.tweak();
				
				if(working.getEnergy() < current.getEnergy() || Prob(current, working)){
					current = working;
					counter++;
				}
				
				if(current.getEnergy() < min_nrg)
				{
					min_nrg = current.getEnergy();
					best = current;
				}
				
				if(current.getEnergy() == 0)
					break;
				
				
			}
			
			if(current.getEnergy() == 0)
				break; 
			
			if(time_step%50 == 0)
				System.out.println(time_step + ".  " + T + "  " + counter + " " + min_nrg);
			
			
			if(counter >= num_solutions || T < 0.3) T = T*alpha;
			else if(counter == 0) T = T/alpha + 0.5;
			
		}
		
		best.printState();
		
		
		return;
	}
	
	public static boolean Prob(State c, State w){
		Random gen = new Random();
		int deltaNRG = w.getEnergy() - c.getEnergy();
		
		P = Math.exp(- deltaNRG/ T);
		
		if(P > gen.nextDouble())
			return true;
		else	
			return false;
	}
	
}
