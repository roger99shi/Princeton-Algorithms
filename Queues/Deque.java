public class Deque<Item> implements Iterable<Item> {  
    private int N;         // number of elements on queue  
    private Node first;    // beginning of queue  
    private Node last;     // end of queue  
  
    private class Node {  
        private Item item;  
        private Node next;  
        private Node previous;  
    }  
     
     
    public Deque() {  
        first = null;  
        last  = null;  
        N = 0;  
    }  
     
     
    public boolean isEmpty() {  
        return N == 0;  
    }  
     
     
    public int size() {  
        return N;      
    }  
     
    public void addFirst(Item item){  
        if (item == null){  
            throw new java.lang.NullPointerException();  
        }  
        Node oldfirst = first;  
        first = new Node();  
        first.item = item;  
        first.previous = null;  
        if (isEmpty()){  
            last = first;  
            first.next = null;  
        }else{  
            oldfirst.previous = first;  
            first.next = oldfirst;  
        }  
        N++;  
    }  
      
    public void addLast(Item item){  
        if (item == null){  
            throw new java.lang.NullPointerException();  
        }  
        Node oldlast = last;  
        last = new Node();  
        last.item = item;  
        last.next = null;  
        if (isEmpty()){  
            first = last;  
            last.previous = null;  
        }else{  
            oldlast.next = last;  
            last.previous = oldlast;  
        }  
        N++;  
    }  
      
    public Item removeFirst(){  
        if (isEmpty()) throw new java.util.NoSuchElementException();  
        Item item = first.item;  
        first = first.next;  
        N--;  
        if (isEmpty()){  
            last = null;  
            first = null;  
        }else{  
            first.previous = null;  
        }  
        return item;  
    }  
     
    public Item removeLast(){  
        if (isEmpty()) throw new java.util.NoSuchElementException();  
        Item item = last.item;  
        last = last.previous;  
        N--;  
        if (isEmpty()){  
            last = null;  
            first = null;  
        }else{  
            last.next = null;  
        }  
        return item;  
    }  
     
     
    public java.util.Iterator<Item> iterator()  {  
        return new ListIterator();   
    }  
  
    private class ListIterator implements java.util.Iterator<Item> {  
        private Node current = first;  
  
        public boolean hasNext()  { return current != null;                     }  
        public void remove()      { throw new java.lang.UnsupportedOperationException();  }  
  
        public Item next() {  
            if (!hasNext()) throw new java.util.NoSuchElementException();  
            Item item = current.item;  
            current = current.next;  
            return item;  
        }  
    }  
}  