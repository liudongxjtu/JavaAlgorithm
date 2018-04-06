import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation 
{
	private boolean[][] opened;
	private int gridSize;//zero-indexed
	private WeightedQuickUnionUF quf;
	private int top = 0;
	private int bottom;
	
	public Percolation(int n)                // create n-by-n grid, with all sites blocked
	{
		
		if (n < 1) 
		{
			throw new IllegalArgumentException("index should more than 0\n");  
		}
		gridSize = n;
		
		top 	= 0;
		bottom	= gridSize*gridSize+1;

		opened = new boolean[n][n];

		for (int i=0; i < n; i++)
		{
			for (int j=0; j<n; j++)
			{
				opened[i][j] = false;//every site is blocked.
			}
		}

		quf = new WeightedQuickUnionUF(n*n+2);
		//Initialize the connection in top and bottom lines
/*		for(int i=1;i<n+1;i++)
		{
			quf.union(top, i);
		}

		for(int i=n*(n-1)+1;i<n*n+1;i++)
		{
			quf.union(bottom, i);
		}
*/
	}


	public    void open(int row, int col)    // open site (row, col) if it is not open already
	{
		if (row < 1) {
			throw new IllegalArgumentException("index " + row + " is not between 0 and " + (gridSize));  
		}

		if (col < 1) {
			throw new IllegalArgumentException("index " + row + " is not between 0 and " + (gridSize));  
		}

		opened[row-1][col-1] = true;


		if (row == 1)//NO Up direction, only connect the down direction
		{
			quf.union(GridIndtoQUFInd(row,col), top);
			if (gridSize > 1)
			{	
				if (isOpen(row+1, col) )
				{
					quf.union( GridIndtoQUFInd(row+1, col), GridIndtoQUFInd(row, col));
				}
			}
			else
			{
				quf.union(1,top);
				quf.union(1, bottom);
			}
		}
		else if (row == (gridSize))// No down direction, only connect the up direction
		{
			quf.union(GridIndtoQUFInd(row,col), bottom);
			if (isOpen(row-1, col))
			{
				quf.union(GridIndtoQUFInd(row-1, col), GridIndtoQUFInd(row, col));
			}
		}
		else//both up and down directions
		{
			if (isOpen(row+1, col))
			{
				quf.union( GridIndtoQUFInd(row+1, col), GridIndtoQUFInd(row, col));
			}

			if (isOpen(row-1, col))
			{
				quf.union(GridIndtoQUFInd(row-1, col), GridIndtoQUFInd(row, col));
			}
		}



		if (col == 1)//NO left direction, only connect right direction
		{
			if (gridSize > 1)
			{
				if (isOpen(row, col+1))
				{
					quf.union( GridIndtoQUFInd(row, col+1), GridIndtoQUFInd(row, col));
				}
			}
		}
		else if (col == (gridSize))// no right direction, only connect left direction
		{
			if (isOpen(row, col-1))
			{
				quf.union( GridIndtoQUFInd(row, col-1), GridIndtoQUFInd(row, col));
			}
		}
		else
		{
			if (isOpen(row, col+1))
			{
				quf.union( GridIndtoQUFInd(row, col+1), GridIndtoQUFInd(row, col));
			}

			if (isOpen(row, col-1))
			{
				quf.union( GridIndtoQUFInd(row, col-1), GridIndtoQUFInd(row, col));
			}

		}

		//		System.out.printf("%s\n", quf.toString());


	}

	public boolean isOpen(int row, int col)  // is site (row, col) open?
	{
		if (row < 1) {
			throw new IllegalArgumentException("index " + row + " is not between 1 and " + (gridSize));  
		}

		if (col < 1) {
			throw new IllegalArgumentException("index " + row + " is not between 1 and " + (gridSize));  
		}

		return (opened[row-1][col-1] == true);


	}


	public boolean isFull(int row, int col)  // is site (row, col) full?
	{
		if (row < 1) {
			throw new IllegalArgumentException("index " + row + " is not between 0 and " + (gridSize));  
		}

		if (col < 1) {
			throw new IllegalArgumentException("index " + row + " is not between 0 and " + (gridSize));  
		}

		return quf.connected(0, GridIndtoQUFInd(row, col));

	}


	public     int numberOfOpenSites()       // number of open sites
	{
		int cnt = 0;
		for (int i=0; i < gridSize; i++)
		{
			for (int j=0;j < gridSize; j++)
			{
				if (opened[i][j] == true)
				{
					cnt++;//every site is blocked.
				}
			}
		}

		return cnt;

	}
	
	public boolean percolates()              // does the system percolate?
	{
		//the virtual top is 0
		//the virtual bottom is gridSize*gridSize+1
		return quf.connected(0, gridSize*gridSize+1);

/*		for(int j=1;j<=gridSize;j++)
		{
			if(isFull(gridSize, j))
			{
				return true;
			}
		}
		return false;*/
	}	


	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		Percolation p1 = new Percolation(1);
		p1.open(1, 1);
		
//		p1.open(2, 1);
//		p1.open(3, 1);
//		p1.open(-1,5);

		System.out.printf("%d,%b", p1.numberOfOpenSites(), p1.percolates());


	}


	

	private int GridIndtoQUFInd(int row, int col)
	{
		return (row-1)*gridSize+(col-1)+1;
	}
}



