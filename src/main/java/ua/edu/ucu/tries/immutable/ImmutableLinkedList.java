package ua.edu.ucu.tries.immutable;

import java.util.Arrays;

public class ImmutableLinkedList implements ImmutableList {
    private Node head;
    private Node tail;
    public int len;

    public ImmutableLinkedList() {
        this.head = null;
        this.tail = null;
        this.len = 0;
    }

    private void checkSize(int index) throws IndexOutOfBoundsException {
        if (index > this.len || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return add(this.len, e);
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        checkSize(index);
        ImmutableLinkedList NewImmutableLinkedList = new ImmutableLinkedList();
        NewImmutableLinkedList.head = head;
        NewImmutableLinkedList.tail = tail;
        NewImmutableLinkedList.len = len+1;
        if (index==0){
            Node newNode = new Node(e, NewImmutableLinkedList.head);
            NewImmutableLinkedList.head = newNode;
        }
        else{
            Node hd = new Node(head.value, head.next);
            NewImmutableLinkedList.head = hd;
            Node temp = NewImmutableLinkedList.head;
            for (int i = 0; i < index - 1; i++){
                temp = temp.next;
            }
            Node nextEl = temp.next;
            temp.next = new Node(e, nextEl);
        }
        return NewImmutableLinkedList;
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(this.len, c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        checkSize(index);
        ImmutableLinkedList TempImmutableLinkedList = new ImmutableLinkedList();
        ImmutableLinkedList NewImmutableLinkedList;
        TempImmutableLinkedList.head = head;
        TempImmutableLinkedList.tail = tail;
        TempImmutableLinkedList.len = len;
        NewImmutableLinkedList = TempImmutableLinkedList;
        for (int i = 0; i < c.length; i++) {
            NewImmutableLinkedList = NewImmutableLinkedList.add(index, c[i]);
            index++;
        }
        return NewImmutableLinkedList;
    }

    @Override
    public Object get(int index) {
        checkSize(index);
        Node temp = new Node(head.value, head.next);
        for (int i = 0; i < index; i++){
            temp = temp.next;
        }
        return temp.value;
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        checkSize(index);
        ImmutableLinkedList NewImmutableLinkedList = new ImmutableLinkedList();
        NewImmutableLinkedList.head = head;
        NewImmutableLinkedList.tail = tail;
        NewImmutableLinkedList.len = len-1;
        if (index==0){
            NewImmutableLinkedList.head = NewImmutableLinkedList.head.next;
            return NewImmutableLinkedList;
        }
        else {
            Node hd = head;
            NewImmutableLinkedList.head = new Node(hd.value, hd.next);
            Node change = NewImmutableLinkedList.head;
            hd = hd.next;
            int counter = 0;
            while (counter < index - 1) {
                change.next = new Node(hd.value, hd.next);
                change = change.next;
                hd = hd.next;
                counter += 1;
            }
            hd = hd.next;
            while (hd != null) {
                change.next = new Node(hd.value, hd.next);
                change = change.next;
                hd = hd.next;
            }
            return NewImmutableLinkedList;
        }
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        checkSize(index);
        ImmutableLinkedList NewImmutableLinkedList = new ImmutableLinkedList();
        NewImmutableLinkedList.head = head;
        NewImmutableLinkedList.tail = tail;
        NewImmutableLinkedList.len = len;
        return (ImmutableLinkedList) NewImmutableLinkedList.remove(index).add(index, e);
    }

    @Override
    public int indexOf(Object e) {
        Node temp;
        temp = head;
        for (int i = 0; i < len; i++) {
            if (temp.value.equals(e)) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public int size() {
        return len;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return len == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] NewImmutableLinkedList = new Object[len];
        Node temp = head;
        for (int i = 0; i < len; i++) {
            NewImmutableLinkedList[i] = temp.value;
            temp = temp.next;
        }
        return NewImmutableLinkedList;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    public ImmutableLinkedList addFirst(Object e) {
        return (ImmutableLinkedList) add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return (ImmutableLinkedList) add(e);
    }

    public Object getFirst() {
        return head.value;
    }

    public Object getLast() {
        return get(size()-1);
    }

    public ImmutableLinkedList removeFirst() {
        return (ImmutableLinkedList) remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return (ImmutableLinkedList) remove(len - 1);
    }
}
