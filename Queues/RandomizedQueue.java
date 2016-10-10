public class RandomizedQueue<Item> implements Iterable<Item> {  
    private Item[] q;            
    private int N = 0;           
     
    public RandomizedQueue() {  
        q = (Item[]) new Object[2];  
    }  
     
    public boolean isEmpty(){  
        return N == 0;     
    }  
     
    public int size(){  
        return N;  
    }  
     
    private void resize(int max) {  
        assert max >= N;  
        Item[] temp = (Item[]) new Object[max];  
        for (int i = 0; i < N; i++) {  
            temp[i] = q[i];  
        }  
        q = temp;  
    }  
     
    public void enqueue(Item item) {  
        if (item == null){  
            throw new java.lang.NullPointerException();  
        }  
        if (N == q.length) resize(2*q.length);   
        q[N] = item;                        
        N++;  
    }  
     
    public Item dequeue() {  
        if (isEmpty()) throw new java.util.NoSuchElementException();  
        int index = StdRandom.uniform(N);  
        Item item = q[index];  
        if (index != N-1){  
            q[index] = q[N-1];  
        }  
        q[N-1] = null;                          
        N--;  
  
        if (N > 0 && N == q.length/4) resize(q.length/2);  
        return item;  
    }  
    public Item sample(){  
        if (isEmpty()) throw new java.util.NoSuchElementException();  
        int index = (StdRandom.uniform(N));  
        return q[index];  
    }  
     
    public java.util.Iterator<Item> iterator() { return new ArrayIterator(); }  
  
    private class ArrayIterator implements java.util.Iterator<Item> {  
        private Item[] tempItem = (Item[]) new Object[q.length];  
         
        private int tempN = N;  
        public boolean hasNext()  { return tempN != 0;                               }  
        public void remove()      { throw new UnsupportedOperationException();  }  
         
        public ArrayIterator(){  
            for (int j=0; j<q.length; j++){  
                tempItem[j] = q[j];  
            }  
        }  
         
        public Item next() {  
            if (!hasNext()) throw new java.util.NoSuchElementException();  
            int index = (StdRandom.uniform(tempN));  
            Item item = tempItem[index];  
            if (index != tempN-1){  
                tempItem[index] = tempItem[tempN-1];  
            }  
            tempItem[tempN-1] = null;                           
            tempN--;  
            return item;  
        }  
    }  
     
    public static void main(String[] args) {  
        RandomizedQueue<String> q = new RandomizedQueue<String>();  
        while (!StdIn.isEmpty()) {  
            String item = StdIn.readString();  
            if (!item.equals("-")) q.enqueue(item);  
            else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");  
        }  
        StdOut.println("(" + q.size() + " left on queue)");  
    }  
}  