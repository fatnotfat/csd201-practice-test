/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BigNumPro;

/**
 *
 * @author HAU NUONG MO HANH
 */
public class SimpleQueue<T> {
    GenericNode<T> front;
    GenericNode<T> end;
    
    public int length() {
        int count = 0;
        for(GenericNode<T> tmp=front; tmp!=null; tmp=tmp.next)
            count++;
        
        return count;
    }
    
    @Override
    public String toString() {
        String result = "";
        if(front==null)
            return "<EMPTY>";
        
        for(GenericNode<T> tmp=front; tmp!=null; tmp=tmp.next)
            result += tmp.info.toString() + ";";
        
        return result;
    }
    
    
    public void clear() {
        front = null;
    }
    
    public boolean isEmpty() {
        return front==null;
    }
    
    public void enqueue(T el) {
        GenericNode<T> nNode = new GenericNode<>(el);
        
        if(front==null)
            front = end = nNode;
        else {
//            GenericNode<T> tmp;
//            for(tmp=front;tmp.next!=null; tmp=tmp.next)
//            { }
            end.next = nNode;
            end  = nNode;
        }
       
        
    }
    
    
    public T dequeue() {
        if(front==null)
            throw new java.util.EmptyStackException();
        
        if(front.next == null) {
            T result = front.info;
            front = null;
            return result;
            
        }
            
        
        T result = front.info;
        front = front.next;
        
        return result;
    }
    
    public T firstEl() {
        if(front==null)
            throw new java.util.EmptyStackException();
        
        return front.info;
    }
    
    public void moveElsTo(SimpleQueue<T> obj, int noOfEl) {
        if(front==null)
            throw new java.util.EmptyStackException();
        for (int i = 0; i < noOfEl; i++) {
            obj.enqueue(this.dequeue());
        }
    }
    
    
    public void moveAllElsTo(SimpleQueue<T> obj) {
        if(front==null)
            throw new java.util.EmptyStackException();
        for(GenericNode<T> tmp = front; tmp!=null; tmp=tmp.next) {
            obj.enqueue(this.dequeue());
        }
    }
    
    
}
