package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T>{

    public static class Node<T> {
        public T item;
        public Node<T> next;
        public Node<T> prev;
        public Node(T i, Node<T> n, Node<T> p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private Node<T> sentinel;
    private Node<T> last;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node<>(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        last = sentinel;
        size = 0;
    }

    public LinkedListDeque(T x) {
        sentinel = new Node<>(null, null, null);
        sentinel.next = new Node<>(x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        last = sentinel.next;
        size = 1;
    }

    public void addFirst(T x) {
        Node<T> newNode = new Node<>(x, sentinel.next, sentinel.prev);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        if (size == 0) {
            last = newNode;
        }
        size += 1;
    }

    public void addLast(T x) {
        Node<T> newNode = new Node<>(x, sentinel, last);
        last.next = newNode;
        sentinel.prev = newNode;
        if (size == 0) {
            sentinel.next = newNode;
        }
        last = newNode;
        size += 1;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node<T> removed = sentinel.next;
        sentinel.next = removed.next;
        removed.next.prev = sentinel;
        size -= 1;
        if (size == 0) {
            last = sentinel;
        }
        return removed.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node<T> removed = sentinel.prev;
        sentinel.prev = removed.prev;
        removed.prev.next = sentinel;
        size -= 1;
        if (size == 0) {
            last = sentinel;
        }
        return removed.item;
    }
    // 递归的get方法
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return  get_iteration(sentinel,index);
    }
    //递归迭代的抽象方法
    private T get_iteration(Node<T> node, int index) {
        if (index == 0){
            return node.item;
        }
        return get_iteration(node.next, index - 1);
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node<T> node = sentinel.next;
        while (node != sentinel) {
            System.out.print(node.item + " ");
            node = node.next;
        }
        System.out.println();
    }
    // 迭代的get方法
    public T getRecursive(int index) {
        if(index < 0 || index >= size){
            return  null;
        }
        Node<T> removed = sentinel.next;
        for (int i = 0; i < index - 1; i++) {
            removed = sentinel.next;
        }
        return removed.item;
    }
    @Override
    public Iterator<T> iterator() {
        return new LinkedDequeIterator();
    }
    public class LinkedDequeIterator implements Iterator<T>{
        private Node current;
        public LinkedDequeIterator(){
            current = sentinel.next;
        }
        @Override
        public boolean hasNext() {
            return current != sentinel;
        }

        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            T returnItem = (T) current.item;
            current = current.next;
            return returnItem;
        }
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        if (this.size != ((LinkedListDeque<T>) o).size) return false;
        Node<T> current = this.sentinel.next;
        Node<T> o_current = ((LinkedListDeque<T>) o).sentinel.next;
        for (int i = 0; i < size; i++) {
            if(!current.item.equals(o_current.item)) return false;
            current = current.next;
            o_current = o_current.next;
        }
        return true;
    }
    public static void main(String[] args) {
        LinkedListDeque<Integer> list = new LinkedListDeque<>(5);
        list.addFirst(1);
        list.addFirst(2);
        System.out.println(list.removeFirst());
        list.printDeque();
    }

}