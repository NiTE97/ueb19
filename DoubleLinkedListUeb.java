package com.company;

import java.util.*;

public class DoubleLinkedListUeb<T> implements List<T>{
    private static final String MSG_INDEX = "Der Index ist nicht vorhanden!";
    private static final String MSG_OBJEKT = "Das Objekt ist nicht vorhanden!";
    private static final String MSG_NOTSUPPORTED = "Diese Operation wird nicht unterstützt";

    private Node<T> head;
    private Node<T> tail;
    private int nodeCount;


    /**
     * Standart Konstruktor der den nodeCount mit 0 initialisiert
     */
    public DoubleLinkedListUeb() {
        nodeCount = 0;
    }

    /**
     * Konstruktor der direkt ein erstes Element hinzufuegt
     *
     * @param data Inhalt der ersten Node
     */
    public DoubleLinkedListUeb(T data) {
        add(data);
    }

    /**
     * Methode zum Ausgeben der Groesse der List
     *
     * @return nodeCount
     */
    @Override
    public int size() {
        return nodeCount;
    }

    /**
     * Methode zum Testen ob die Liste leer ist
     *
     * @return true wenn leer
     */
    @Override
    public boolean isEmpty() {
        return nodeCount == 0;
    }

    @Override
    public boolean contains(Object o) {
        boolean found = false;
        if (head != null) {
            Node<T> cur = head;
            while (cur != null && cur.getValue() != o) {
                cur = cur.getNext();
            }
            if (cur != null) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomIterator();
    }

    /**
     * Methode um die Liste in ein Array umzuwandeln
     *
     * @return das neu erstellte Array
     */
    @Override
    public Object[] toArray() {
        Object[] arr = new Object[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            arr[i] = get(i);
        }
        return arr;
    }

    /**
     * Methode um die Liste in ein Array umzuwandeln
     *
     * @param a  das Array in dem die Eintraege gespeichert werden
     * @param <T> Typ der Daten
     * @return das gefuellte Array
     */
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < nodeCount) {
            a = (T[]) new Object[nodeCount];

        }
        if (a.length >= nodeCount) {
            int i = 0;
            if (head != null) {
                Node<T> cur = (Node<T>) head;
                while (cur != null) {
                    a[i] = (T) cur.getValue();
                    cur = cur.getNext();
                    i++;
                }
            }
        }
        return a;
    }

    @Override
    public boolean add(T e) {
        Node<T> node = new Node<>();
        node.setValue(e);

        if (head == null) {
            head = node;
            tail = node;
            nodeCount++;
            return true;
        } else {
            node.setPrev(tail);
            tail.setNext(node);
            tail = node;
            nodeCount++;
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        boolean found = false;
        if (head != null) {
            Node<T> cur = head;
            while (cur != null && cur.getValue() != o) {
                cur = cur.getNext();
            }
            if (cur != null) {
                found = true;
                removeOperation(cur);
            }
        }
        return found;
    }

    private void removeOperation(Node<T> cur) {
        Node<T> prev = cur.getPrev();
        Node<T> next = cur.getNext();
        if (prev != null) {
            prev.setNext(next);
        } else {
            head = next;
        }
        if (next != null) {
            next.setPrev(prev);
        } else {
            tail = prev;
        }
        cur.setPrev(null);
        cur.setValue(null);
        nodeCount--;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException(MSG_NOTSUPPORTED);
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean modified = false;
        for (T e : collection) if (add(e)) modified = true;
        return modified;
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        throw new UnsupportedOperationException(MSG_NOTSUPPORTED);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException(MSG_NOTSUPPORTED);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException(MSG_NOTSUPPORTED);
    }

    @Override
    public void clear() {
        if (head != null) {
            Node<T> cur = head;
            while (cur != null) {
                remove(cur.getValue());
                cur = cur.getNext();
            }
        }
    }

    @Override
    public T get(int i) {
        Validator.check(i > nodeCount, MSG_INDEX);
        if (head != null) {
            Node<T> cur = head;
            int p = 0;
            while (cur != null && p < i) {
                cur = cur.getNext();
                p++;
            }
            if (cur != null) {
                return cur.getValue();
            }
        }
        return null;
    }

    @Override
    public T set(int i, T e) {
        Validator.check(i > nodeCount + 1, MSG_INDEX);
        if (head != null) {
            Node<T> cur = head;
            int p = 0;
            while (cur != null && p < i) {
                cur = cur.getNext();
                p++;
            }
            T temp = cur != null ? cur.getValue() : null;
            if (cur != null) {
                cur.setValue(e);
            }
            return temp;
        }
        return null;
    }

    @Override
    public void add(int i, T e) {
        Validator.check(i > nodeCount, MSG_INDEX);
        Node<T> node = new Node<>();
        node.setValue(e);

        //Add first and last
        if (nodeCount == 0) {
            head = tail = node;
        } else {
            //Add first
            if (i == 0) {
                node.next = head;
                head.prev = node;
                head = node;
            }

            //Add last
            else if (i == nodeCount) {
                node.prev = tail;
                tail.next = node;
                tail = node;
            }

            //Add between
            else {
                Node<T> current = this.head;

                for (int p = 0; p < i; p++) {
                    current = current.next;
                }
                node.next = current;
                node.prev = current.prev;
                current.prev.next = node;
            }
        }
        nodeCount++;
    }

    @Override
    public T remove(int i) {
        Validator.check(i > nodeCount, MSG_INDEX);
        if (head != null) {
            Node<T> cur = head;
            int p = 0;
            while (cur != null && p < i) {
                cur = cur.getNext();
                p++;
            }
            if (cur != null) {
                removeOperation(cur);
                return cur.getValue();
            }
        }
        return null;
    }

    @Override
    public int indexOf(Object o) {
        int index = -1;
        int p = 0;
        if (head != null) {
            Node<T> cur = head;
            while (cur != null && cur.getValue() != o) {
                cur = cur.getNext();
                p++;
            }
            if (cur != null) {
                index = p;
            }
        }
        Validator.check(index == -1, MSG_OBJEKT);
        return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException(MSG_NOTSUPPORTED);
    }

    @Override
    public ListIterator<T> listIterator() {
        return new CustomListIterator();
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        Validator.check(i > nodeCount, MSG_INDEX);
        return new CustomListIterator(i);
    }

    @Override
    public List<T> subList(int i, int i1) {
        throw new UnsupportedOperationException(MSG_NOTSUPPORTED);
    }

    public Node<T> node(int index){
        Validator.check(index > nodeCount, MSG_INDEX);
        Node<T> temp;
        if(index < (nodeCount >> 1)){
            temp = head;
            for (int i = 0; i < index; i++){
                temp = temp.getNext();
            }
        } else {
            temp = tail;
            for (int i = nodeCount -1; i > index; i--){
                temp = temp.getPrev();
            }
        }
        return temp;
    }

    static class Node<T> {
        private T value;
        private Node<T> prev;
        private Node<T> next;

        public void setValue(T value) {
            this.value = value;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        public T getValue() {
            return value;
        }

        public Node<T> getNext() {
            return next;
        }

        public Node<T> getPrev() {
            return prev;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

    class CustomListIterator implements ListIterator<T> {

        private final Node<T> iterator;

        CustomListIterator(){
            iterator = head;
        }

        CustomListIterator(int i){
            iterator = (i == nodeCount) ? null : node(i);
        }

        @Override
        public boolean hasNext() {
            return iterator.getNext() != null;
        }

        @Override
        public T next() {
            Validator.check(iterator.getNext() == null, MSG_OBJEKT);
            return iterator.getNext().getValue();
        }

        @Override
        public boolean hasPrevious() {
            return iterator.getPrev() != null;
        }

        @Override
        public T previous() {
            return iterator.getPrev().getValue();
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(T e) {

        }

        @Override
        public void add(T e) {

        }
    }

    class CustomIterator implements Iterator<T> {

        private final Node<T> iterator;

        CustomIterator(){
            iterator = head;
        }

        @Override
        public boolean hasNext() {
            return iterator.getNext() != null;
        }

        @Override
        public T next() {
            Validator.check(iterator.getNext() == null, MSG_OBJEKT);
            return iterator.getNext().getValue();
        }

        @Override
        public void remove() {

        }
    }



}
