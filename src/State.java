import java.util.Random;

public class State {
	
	private	boolean board[][];// board[x][y]
	private	int energy;
	private int n;
	private double t;
	private double p;
	
	public State(int NQueens){
		n = NQueens;
		Random generator = new Random();
		int rand;
		
		board = new boolean[n][n];
		
		for(int i=0; i<n; i++)
		for(int j=0; j<n; j++)
		{
			if(i==j)
				board[i][j] = true;
			else
				board[i][j] = false;
		}
		
		
		for(int i=0; i<n; i++){
			rand = generator.nextInt(n);
			swap(i, rand);
		}
		
		assessEnergy();
		
	}
	
	public int getEnergy(){
		return energy;
	}
	
	public State tweak(){
		State w = new State(n);
		Random generator = new Random();
		int rand1 = generator.nextInt(n);
		int rand2 = generator.nextInt(n);
		
		w.setBoard(board);
		w.swap(rand1, rand2);
		
		w.assessEnergy();
		
		return w;
	}
	
	public void setBoard(boolean b[][]){
		board = b;
	}
	
	public void assessEnergy(){
		int nrg = 0;
		
		for(int i=0; i<board.length; i++)
		for(int j=0; j<board.length; j++){
			if(board[i][j])
				nrg = nrg + detectCollision(i, j);
		}
		
		energy = nrg/2;
		
	}
	
	private int detectCollision(int x, int y){
		int c = 0;
		
		for(int i=0; i<board.length; i++)
		for(int j=0; j<board.length; j++)
		{
			if(board[i][j] && (i+j == x+y || i-j == x-y) && (i != x && j != y))
				c++;
		}
		
		return c;
	}
	
	public void swap(int n1, int n2){
		
		boolean column[] = board[n1];
		board[n1] = board[n2];
		board[n2] = column;
		
	}
	
	public void printState(){
		
		for(int i=0; i<board.length; i++)
		{
			for(int j=0; j<board.length; j++)
			{
				if(board[i][j] == true)
					System.out.print("+");
				else
					System.out.print("-");
			}
			System.out.println();
		}
		
		System.out.println("Energy: " + energy);
	}
	
}
