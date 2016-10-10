public class PercolationStats {  
    private double recT[];  
    private double res_mean;  
    private double res_stddev;  
    private int N;  
     
    // perform T independent computational experiments on an N-by-N grid  
    public PercolationStats(int N, int T){  
        recT = new double[T];  
        this.N = N;  
         
        int times = 0;  
        if (N <= 0){  
            throw new IllegalArgumentException();  
        }  
        if (T <= 0){  
            throw new IllegalArgumentException();  
        }  
        while (times < T){  
            Percolation percolation = new Percolation(N);  
            boolean[][] arrOpen = new boolean[N+1][N+1];  
            int count = 0;  
            while(true){  
                count++;  
                while(true){  
                    int x = StdRandom.uniform(N) + 1;  
                    int y = StdRandom.uniform(N) + 1;  
                    if (arrOpen[x][y]){  
                        continue;  
                    }else{  
                        percolation.open(x, y);  
                        arrOpen[x][y] = true;  
                        break;  
                    }  
                }  
                if (percolation.percolates()){  
                    recT[times] = (double)count / ((double)N * (double)N);  
                    break;  
                }  
            }  
            times++;  
        }  
        this.res_mean = StdStats.mean(recT);  
        this.res_stddev = StdStats.stddev(recT);  
    }  
     
    // sample mean of percolation threshold  
    public double mean(){  
        return this.res_mean;  
    }  
     
    // sample standard deviation of percolation threshold  
    public double stddev(){  
        return this.res_stddev;  
    }  
     
    // returns lower bound of the 95% confidence interval  
    public double confidenceLo(){  
        return this.res_mean - 1.96*this.res_stddev / Math.sqrt(N);  
    }  
     
    // returns upper bound of the 95% confidence interval  
    public double confidenceHi(){  
        return this.res_mean + 1.96*this.res_stddev / Math.sqrt(N);  
    }  
     
    // test client, described below  
    public static void main(String[] args){  
        int N = StdIn.readInt();  
        int T = StdIn.readInt();  
        PercolationStats percolationStats = new PercolationStats(N, T);  
        StdOut.println("mean = " + percolationStats.mean());  
        StdOut.println("stddev = " + percolationStats.stddev());  
        StdOut.println("95% confidence interval " + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi());  
    }  
}  