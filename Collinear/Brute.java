public class Brute {  
    public static void main(String[] args){  
        // rescale coordinates and turn on animation mode  
        StdDraw.setXscale(0, 32768);  
        StdDraw.setYscale(0, 32768);  
          
        String filename = args[0];  
        In in = new In(filename);  
        int N = in.readInt();  
        Point[] pointArray = new Point[N];  
        for (int i = 0; i < N; i++) {  
            int x = in.readInt();  
            int y = in.readInt();  
            Point p = new Point(x, y);  
            pointArray[i] = p;  
            p.draw();  
        }  
        Quick3way.sort(pointArray);  
        BruteForce(pointArray);  
        StdDraw.show(0);  
    }  
      
    private static void BruteForce(Point[] pointArray){  
        int N = pointArray.length;  
        for (int i=0; i<N; i++){  
            for (int j=i+1; j<N; j++){  
                for (int k=j+1; k<N; k++){  
                    for (int l=k+1; l<N; l++){  
                        if (pointArray[i].slopeTo(pointArray[j]) == pointArray[i].slopeTo(pointArray[k]) &&  
                            pointArray[i].slopeTo(pointArray[j]) == pointArray[i].slopeTo(pointArray[l])){  
                            StdOut.println(pointArray[i] + " -> " + pointArray[j] + " -> "  + pointArray[k] + " -> "  + pointArray[l]);  
                            pointArray[i].drawTo(pointArray[l]);  
                        }  
                    }  
                }  
            }  
        }  
    }  
}  