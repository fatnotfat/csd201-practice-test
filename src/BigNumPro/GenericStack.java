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
public class GenericStack<T extends Comparable<T>>
        extends SimpleStack<T> {

    public void toGenericStack(GenericStack<T> stackToCopy) {
        if (top == null) {
            throw new java.util.EmptyStackException();
        }

        stackToCopy.clear();
        for (GenericNode<T> tmp = top; tmp != null; tmp = tmp.pre) {
            stackToCopy.push(tmp.info);
        }
    }

    public GenericStack<T> toGenericStack() {
        if (top == null) {
            return null;
        }

        GenericStack<T> result = new GenericStack<>();
        for (GenericNode<T> tmp = top; tmp != null; tmp = tmp.pre) {
            result.push(tmp.info);
        }

        return result;
    }

    public void ascendingSort() {
        SimpleStack<T> S = this;
        SimpleStack<T> addStack = new SimpleStack<>();
        int cLen = S.length();
        T min = null;
        int minPos = -1;
        while (cLen > 0) {
            min = S.topEl();
            minPos = 0;
            for (int i = 0; i < cLen; i++) {
                T sTop = S.pop();
                if (min.compareTo(sTop) > 0) {
                    min = sTop;
                    minPos = i;
                }
                addStack.push(sTop);
            }

            S.push(min);
            int l = addStack.length();
            cLen = l;
            for (int i = 0; i < l; i++) {
                T popVal = addStack.pop();
                if (i == (l - minPos - 1)) {
                    cLen--;
                    continue;
                }
                S.push(popVal);
            }

        }
    }
}
