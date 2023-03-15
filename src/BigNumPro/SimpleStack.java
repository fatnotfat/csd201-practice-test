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
public class SimpleStack<T> {
    
    GenericNode<T> top;
    private int length = 0;
    
    public int length() {
        return length;
    }
    
    @Override
    public String toString() {
        
        if(top==null)
            return "<EMPTY>";
        
        
        SimpleStack<T> tmp = this.toStack();
        String reStr = "";
        for(GenericNode<T> i=tmp.top; i!=null; i=i.pre)
            reStr += i.info.toString() + ";";
        
        return reStr;

    }
    public void toStack(SimpleStack<T> stackToCopy) {
        if(top==null)
            throw new java.util.EmptyStackException();
        
        stackToCopy.clear();
        for(GenericNode<T> tmp=top; tmp!=null; tmp=tmp.pre)
            stackToCopy.push(tmp.info);
    }
    
    public SimpleStack<T> toStack() {
        if(top==null)
            return null;
        
        SimpleStack<T>  result = new SimpleStack<>();
        for(GenericNode<T> tmp=top; tmp!=null; tmp=tmp.pre)
            result.push(tmp.info);
        
        return result;
    }
    
    public void toQueue(SimpleQueue<T> queueToCopy) {
        if(top==null)
            throw new java.util.EmptyStackException();
        
        queueToCopy.clear();
        for(GenericNode<T> tmp=top; tmp!=null; tmp=tmp.pre)
            queueToCopy.enqueue(tmp.info);
        
    }
    
    
    public SimpleQueue<T> toQueue() {
        if(top==null)
            return null;
        
        SimpleQueue<T> result = new SimpleQueue<>();
        for(GenericNode<T> tmp=top; tmp!=null; tmp=tmp.pre)
            result.enqueue(tmp.info);
        
        return result;
    }
    
    public void clear() {
        top = null;
        length = 0;
    }
    
    public boolean isEmpty() {
        return top==null;
    }
    
    public void push(T el) {
        GenericNode<T> nNode = new GenericNode<>(el);
        
        if(top==null)
            top = nNode;
        else {
            nNode.pre = top;
            top = nNode;
        }
        
        this.length++;
       
        
    }
    
    
    public T pop() {
        if(top==null)
            throw new java.util.EmptyStackException();
        
        T result = top.info;
        top = top.pre;
        this.length--;
        return result;
    }
    
    public T topEl() {
        if(top==null)
            throw new java.util.EmptyStackException();
        
        return top.info;
    }
    
    public void moveElsTo(SimpleStack<T> obj, int noOfEl) {
        if(top==null)
            throw new java.util.EmptyStackException();
        for (int i = 0; i < noOfEl; i++) {
            obj.push(this.pop());
        }
    }
    
    public void moveAllElsTo(SimpleStack<T> obj) {
        if(top==null)
            throw new java.util.EmptyStackException();
        int l = length;
        for (int i=0; i<l; i++) {
            obj.push(this.pop());
        }
    }
    
    public void moveAllElsTo(SimpleQueue<T> obj) {
        if(top==null)
            throw new java.util.EmptyStackException();
        int l = length;
        for (int i=0; i<l; i++) {
            obj.enqueue(this.pop());
        }
    }
    
}
