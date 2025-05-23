package deque;

import java.util.Comparator;

public class MayArrayDeque<T> implements Deque<T> {


    public  static class Node<T>{
        private T items;
        private Node next;
        private Node prev;
        public Node(T items,Node next,Node prev){
            this.items = items;
            this.next = next;
            this.prev = prev;
        }
    }
    private Node sentinel;
    private int size;
    private Node<T> last;
    private Comparator<T> comparator;
    public MayArrayDeque(Comparator<T> c){
        sentinel = new Node<T>(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        last = sentinel;
        size = 0;
        this.comparator = c;
    }

    @Override
    public void addFirst(T item) {
        Node<T> newnode = new Node(item, sentinel.next, sentinel);
        sentinel.next.prev = newnode;
        sentinel.next = newnode;
        if(size == 0){
            last = newnode;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        Node newcode = new  Node(item,sentinel,sentinel.prev);
        sentinel.prev.next = newcode;
        sentinel.prev = newcode;
        if(size == 0){
            last = newcode;
        }
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node current = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.println(current.items);
            current = current.next;

        }
    }

    @Override
    public T removeFirst() {
        if(size == 0){
            return null;
        }
        Node<T> removed = sentinel.next;
        sentinel.next = removed.next;
        sentinel.next.prev = sentinel;
        removed.next = null;
        removed.prev = null;
        size--;
        if(isEmpty()){
            last = sentinel;
        }
        return removed.items;
    }

    @Override
    public T removeLast() {
        if(size == 0){
            return null;
        }
        Node<T> removed = sentinel.prev;
        sentinel.prev = removed.prev;
        sentinel.prev.next = sentinel;
        removed.next = null;
        removed.prev = null;
        size--;
        if(isEmpty()){
            last = sentinel;
        }
        return removed.items;
    }

    @Override
    public T get(int index) {
        if(size == 0 ||index < 0 || index >= size){
            return null;
        }
        return (T) T_iteration(sentinel.next,index).items;
    }
    private Node<T> T_iteration(Node<T> current,int index){
        if(index == 0){
            return current;
        }
        return T_iteration(current.next, index - 1);
    }
    public T max(){
        return max(this.comparator);
    }
    public T max(Comparator<T> c){
        if(size == 0){
            return null;
        }
        Node<T> current = sentinel.next;
        T largest = current.items;
        for (int i = 0; i < size; i++) {
            if(c.compare(current.items,largest) > 0){
                largest = current.items;
            }
            current = current.next;
        }
        return largest;
    }
}
