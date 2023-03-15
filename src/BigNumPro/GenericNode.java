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
public class GenericNode<T> {
    T info;
    GenericNode next;
    GenericNode pre;
    
    GenericNode() {
        
    }
    
    GenericNode(T el) {
        info = el;
    }
}
