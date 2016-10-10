public class Percolation {  
    private WeightedQuickUnionUF uf;  
    private WeightedQuickUnionUF uf_backwash;  
    private int N;  
    private boolean[] arrayOpen;  
    // create N-by-N grid, with all sites blocked  
    public Percolation(int N){  
        this.N = N;  
        uf = new WeightedQuickUnionUF((N+1)*(N)+N+1);  
        uf_backwash = new WeightedQuickUnionUF(N*N+N+1);  
        arrayOpen = new boolean[(N+1)*(N)+N+1];  
        for (int i=1; i<=N; i++){  
            uf.union(0*N+1, 0*N+i);  
            uf_backwash.union(0*N+1, 0*N+i);  
            arrayOpen[0*N+i] = true;  
            uf.union((N+1)*N+1, (N+1)*N+i);  
            arrayOpen[(N+1)*N+i] = true;  
        }  
    }  
     
    // open site (row i, column j) if it is not already  
    public void open(int i, int j){  
        if (i < 1 || i > N){  
            throw new IndexOutOfBoundsException("row index " + i + " out of bounds");  
        }  
        if (j < 1 || j > N){  
            throw new IndexOutOfBoundsException("row index " + j + " out of bounds");  
        }  
        if (arrayOpen[i*N+j]){  
            return;  
        }  
        arrayOpen[i*N+j] = true;  
        if (arrayOpen[(i-1)*N+j]){  
            uf.union(i*N+j, (i-1)*N+j);  
            uf_backwash.union(i*N+j, (i-1)*N+j);  
        }  
        if (arrayOpen[(i+1)*N+j]){  
            uf.union(i*N+j, (i+1)*N+j);  
            if (i!=N){  
                uf_backwash.union(i*N+j, (i+1)*N+j);  
            }  
        }  
        if (j!=1 && arrayOpen[i*N+j-1]){  
            uf.union(i*N+j, i*N+j-1);  
            uf_backwash.union(i*N+j, i*N+j-1);  
        }  
        if (j!=N && arrayOpen[i*N+j+1]){  
            uf.union(i*N+j, i*N+j+1);  
            uf_backwash.union(i*N+j, i*N+j+1);  
        }  
    }  
     
    // is site (row i, column j) open?  
    public boolean isOpen(int i, int j){  
        if (i <1 || i > N){  
            throw new IndexOutOfBoundsException("row index " + i + " out of bounds");  
        }  
        if (j < 1 || j > N){  
            throw new IndexOutOfBoundsException("row index " + j + " out of bounds");  
        }  
        return arrayOpen[i*N+j];  
    }  
     
    // is site (row i, column j) full?  
    public boolean isFull(int i, int j){  
        if (i <1 || i > N){  
            throw new IndexOutOfBoundsException("row index " + i + " out of bounds");  
        }  
        if (j < 1 || j > N){  
            throw new IndexOutOfBoundsException("row index " + j + " out of bounds");  
        }  
        return uf_backwash.connected(i*N+j, 0*N+1) && arrayOpen[i*N+j];  
    }  
     
    // does the system percolate?  
    public boolean percolates(){  
        return uf.connected(0*N+1, (N+1)*N+1);  
    }  
}  