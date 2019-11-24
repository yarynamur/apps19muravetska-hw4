package ua.edu.ucu.tries;

import java.util.ArrayList;
import java.util.List;

public class RWayTrie implements Trie {
    private static int R = 256;
    private Node root;
    private static class Node
    {
        private Object val;
        private Node[] next = new Node[R];
    }

    @Override
    public void add(Tuple t) {
        root = add(root, t, 0);
    }

    private Node add(Node x, Tuple t, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == t.term.length()) {
            x.val = t.weight;
            return x;
        }
        char c = t.term.charAt(d);
        x.next[c] = add(x.next[c], t, d+1);
        return x;
    }

    public Tuple get(String word)
    {
        Node x = get(root, word, 0);
        if (x == null) return null;
        return (Tuple) x.val;
    }
    private Node get(Node x, String word, int d) {
        if (x == null){
            return null;
        }
        if (d == word.length()){
            return x;
        }
        char c = word.charAt(d);
        return get(x.next[c], word, d+1);
    }

    @Override
    public boolean contains(String word) {
        if (get(word).equals(null)){
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String word) {
        root = delete(root, word, 0);
        if (root.equals(null)){
            return false;
        }
        return true;
    }


    private Node delete(Node x, String word, int d) {
        if (x == null) {
            return null;
        }
        if (d == word.length())
            x.val = null;
        else {
            char c = word.charAt(d);
            x.next[c] = delete(x.next[c], word, d+1);
        }
        if (x.val != null) {
            return x;
        }
        for (char c = 0; c < R; c++) {
            if (x.next[c] != null) return x;
        }
        return null;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String pref) {
        List<String> dict = new ArrayList<>();
        Queue q = new Queue();
        collect(get(root, pref, 0), pref, q);
        int size = q.size();
        for (int i = 0; i < size;i++){
            dict.add((String) q.dequeue());
        }
        return dict;
    }
    private void collect(Node x, String pre, Queue q) {
        if (x == null) return;
        if (x.val != null) q.enqueue(pre);
        for (char c = 0; c < R; c++)
            collect(x.next[c], pre + c, q);
    }

    @Override
    public int size() {
        return size(root);
    }
    private int size(Node x)
    {
        if (x == null) return 0;
        int cnt = 0;
        if (x.val != null) cnt++;
        for (char c = 0; c < R; c++)
            cnt += size(x.next[c]);
        return cnt; }

}