class Trie {

    private Node head;
    
    public Trie() {
        head = new Node();
    }
    
    public void insert(String word) {
        head.add(word.toCharArray(), 0);
    }
    
    public boolean search(String word) {
        return head.search(word.toCharArray(), 0);
    }
    
    public boolean startsWith(String prefix) {
        return head.startsWith(prefix.toCharArray(), 0);
    }
    
    // Custom class "Node"
    static class Node {
        Node[] next;
        boolean canBeWord;
        
        public Node() {
            next = new Node[26];
        }
        
        public Node(char[] values, int now) {
            next = new Node[26];
            initNext(values, now);
        }
        
        private void initNext(char[] values, int now) {
            if (now != values.length) {
                if (next[values[now] - 'a'] == null) {
                    next[values[now] - 'a'] = new Node(values, now + 1);
                } else {
                    next[values[now] - 'a'].add(values, now + 1);
                }
            } else {
                canBeWord = true;
            }
        }
        
        public void add(char[] values, int now) {
            initNext(values, now);
        }
        
        public boolean search(char[] values, int now) {
            if (now == values.length) {
                if (canBeWord) {
                    return true;
                } else {
                    return false;
                }
            }
            if (next[values[now] - 'a'] == null) {
                    return false;
            }
            return next[values[now] - 'a'].search(values, now + 1);
        }
        
        public boolean startsWith(char[] values, int now) {
            if (now == values.length) {
                return true;
            }
            if (next[values[now] - 'a'] == null) {
                    return false;
            }
            return next[values[now] - 'a'].startsWith(values, now + 1); 
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */