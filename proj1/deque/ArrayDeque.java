package deque;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class ArrayDeque<T> implements Iterable<T[]>, Deque<T[]>{


    public static class Node<T>{
        public T[] item;
        public Node<T> next;
        public Node<T> prev;
        public Node(T[] item, Node<T> next, Node<T> prev){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
    private int size;
    private Node<T> last;
    private Node<T> sentinel;
    public ArrayDeque() {
        sentinel = new Node<>(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
        last = sentinel;
    }
    public  void  addFirst(T[] item){
        Node<T> newnode = new Node<>(item, sentinel.next,sentinel.prev);
        sentinel.next.prev  = newnode;
        sentinel.next = newnode;
        if(size == 0){
            last = newnode;
        }
        size++;
    }
    public  void addLast(T[] item){
        Node<T> newnode = new Node<>(item, sentinel, sentinel.prev);
        sentinel.prev.next = newnode;
        sentinel.prev = newnode;
        last = newnode;
        size++;
    }
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }
    public  int size(){
        return size;
    }
    public void  printDeque(){
        Node<T> current = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(Arrays.toString(current.item));
            System.out.print(" ");
            current = current.next;
        }
        System.out.println();
    }
    public T[] removeFirst(){
        if (size == 0){
            return null;
        }
        Node<T> current = sentinel.next;
        sentinel.next.prev = sentinel;
        sentinel.next = current.next;
        size--;
        return  current.item;
    }
    public T[] removeLast(){
        if(size == 0){
            return null;
        }
        Node<T> current = sentinel.prev;
        current.prev.next = sentinel;
        sentinel.prev = current.prev;
        size--;
        return  current.item;
    }
    public T[] get(int index){
        if (index < 0 || index >= size){
            return null;
        }
        return get_iteration(sentinel.next,index);
    }
    private  T[] get_iteration(Node<T> node,int index){
        if (index == 0){
            return node.item;
        }
        return get_iteration(node.next,index-1);
    }
    public T[] get_Recurrence(int index){
        if(index < 0 || index >= size){
            return  null;
        }
        Node<T> current = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return  current.item;
    }
    @Override
    public Iterator<T[]> iterator() {
        return new ArrayDequeIterable();
    }
    public class ArrayDequeIterable implements Iterator<T[]> {
        private Node<T> current;
        public  ArrayDequeIterable(){
            current =  sentinel.next;
        }
        @Override
        public boolean hasNext() {
            return  current != sentinel;
        }

        @Override
        public T[] next() {
            T[] returnItem = current.item;
            current = current.next;
            return returnItem;
        }
    }
    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        if(size != (((ArrayDeque<T>) o).size)) return false;
        Node<T> current = sentinel.next;
        Node<T> o_current = ((ArrayDeque<T>) o).sentinel.next;
        for (int i = 0; i < size; i++){
            if(!Arrays.equals(current.item, o_current.item)) return false;
            current = current.next;
            o_current = o_current.next;
        }
        return true;
    }
    public static void main(String[] args) {
        ArrayDeque<String> s = new ArrayDeque<>();
        s.addFirst(new String[]{"a", "b", "c"});
        s.addLast(new String[]{"x", "y"});
        s.addFirst(new String[]{"z"});

        s.printDeque();
        System.out.println("Size: " + s.size());
        System.out.println("Is empty? " + s.isEmpty());
    }

}
