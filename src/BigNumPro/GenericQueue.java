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
public class GenericQueue<T extends Comparable<T>> extends SimpleQueue {

    public void toGenericStack(GenericStack<T> stackToCopy) {
        if (front == null) {
            throw new java.util.EmptyStackException();
        }
        stackToCopy.clear();
        for (GenericNode<T> tmp = front; tmp != null; tmp = tmp.next) {
            stackToCopy.push(tmp.info);
        }
    }

    public GenericStack<T> toGenericStack() {
        if (front == null) {
            return null;
        }
        GenericStack<T> result = new GenericStack<>();

        for (GenericNode<T> tmp = front; tmp != null; tmp = tmp.next) {
            result.push(tmp.info);
        }

        return result;
    }

    public void moveAllElsTo(GenericStack<T> obj) {
        if (front == null) {
            throw new java.util.EmptyStackException();
        }
        for (GenericNode<T> tmp = front; tmp != null; tmp = tmp.next) {
            obj.push((T) this.dequeue());
        }
    }
}
