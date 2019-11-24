package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.*;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int words_count = 0;
        for (String s : strings){
            if (s.length() > 2){
                trie.add(new Tuple(s, s.length()));
                words_count++;
            }
        }
        return words_count;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        Iterable<String> old_q = trie.wordsWithPrefix(pref);
        int new_size = Math.min(k, trie.size());
        if (trie.size()>k){
            new_size++;
        }
        List<String> list = new ArrayList();
        for (String s: old_q) {
            list.add(s);
        }
        Comparator c = (Comparator<String>) (s1, s2) -> Integer.compare(s1.length(), s2.length());
        Collections.sort(list, c);
        return list.subList(0, new_size);
    }

    public int size() {
        return trie.size();
    }
}
