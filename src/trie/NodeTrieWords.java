
package trie;

public class NodeTrieWords {
    private static final int numberOfChracters = 27;
    NodeTrieWords dad;
    NodeTrieWords [] sons;
    int cant; //or end
    boolean empty;
    int hijosInicializados;
    
    public NodeTrieWords(){
        this.sons = new NodeTrieWords[numberOfChracters];
        this.cant = 0;
        this.empty = true;
        this.hijosInicializados = 0;
    }
    
    public boolean isEmpty(){
        int i = 0;
        while( i<numberOfChracters && sons[i] == null )
            i++;
        return i >= numberOfChracters;
    }

}
