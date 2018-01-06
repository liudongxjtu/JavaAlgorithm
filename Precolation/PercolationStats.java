import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats 
{
	private double 	[]threshodArray;
	private int 	trialNum;
	public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
	{
		if (n <= 0 || trials <= 0) 
		{
			throw new IllegalArgumentException("Given N <= 0 || T <= 0");
		}

		trialNum = trials;
		threshodArray = new double[trials];
		
		for (int k = 0; k < trialNum; k++)
		{
			int cnt = 0;
			Percolation p1 = new Percolation(n);
			while (!p1.percolates())
			{
				int i = StdRandom.uniform(1, n+1);
				int j = StdRandom.uniform(1, n+1);

				if(!p1.isOpen(i, j))
				{
					p1.open(i, j);
					cnt++;
				}


			}
			//threshodArray[k] = (double) p1.numberOfOpenSites() / (double)(n*n);
			double threshod = (double) cnt / (double)Math.pow(n, 2);
			threshodArray[k] = threshod;
			//System.out.printf("threshod %f\n", threshodArray[k]);

		}
		//mean();

	}
	public double mean()                          // sample mean of percolation threshold
	{
		return StdStats.mean(threshodArray);

	}
	public double stddev()                        // sample standard deviation of percolation threshold
	{
		return StdStats.stddev(threshodArray);
	}
	public double confidenceLo()                  // low  endpoint of 95% confidence interval
	{
		return mean() - ((1.96 * stddev()) / Math.sqrt(trialNum));

	}
	public double confidenceHi()                  // high endpoint of 95% confidence interval
	{
		return mean() + ((1.96 * stddev()) / Math.sqrt(trialNum));
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PercolationStats ps = new PercolationStats(200, 100);

		String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();

		StdOut.println("mean                    = " + ps.mean());
		StdOut.println("stddev                  = " + ps.stddev());
		StdOut.println("95% confidence interval = " + confidence);


	}



}
