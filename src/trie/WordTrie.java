
package trie;

import java.util.ArrayList;


public class WordTrie {
    NodeTrieWords root;
    
    char [] simbols = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 
                       'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

   
    public WordTrie(){
        root = new NodeTrieWords();   
    }
    
    public void insertWord(String word){ //teo
        NodeTrieWords act = root;
        //Recorrer las letras de la palabra:
        for(int i=0; i<word.length() ; i++){
            act.empty=false;
            if( act.sons[ getIndexOf( word.charAt(i) ) ] == null){
                act.sons[ getIndexOf( word.charAt(i) ) ] = new NodeTrieWords();  
            }
            act = act.sons[ getIndexOf( word.charAt(i) ) ];
        }
        act.cant++;
    }
    public void insertRecursive(String llave, NodeTrieWords act){
        if(act == null)
            return;
        if(llave == "")
            act.cant=1;
        if(act.sons[ getIndexOf( llave.charAt(0) ) ] == null){
            act.sons[ getIndexOf( llave.charAt(0) ) ] = new NodeTrieWords();
            act.hijosInicializados++;
            act = act.sons[ getIndexOf( llave.charAt(0) ) ];
        }
        insertRecursive(llave.substring(1), act);
    }
    
    public boolean searchWord(String word){
        return searchWordP(word) != null;
    }
    public NodeTrieWords searchWordP(String wordKey){
        if(wordKey == null || wordKey.length() == 0)
            throw new RuntimeException("Empty parameter for searchWord!");
        
        NodeTrieWords node = root;
        for(int i=0; i<wordKey.length(); i++){
            if( node.sons[ getIndexOf( wordKey.charAt(i) ) ] == null)
                return null;
            node = node.sons[ getIndexOf( wordKey.charAt(i) ) ];  
        } 
        if( node !=null && node.cant > 0 )
            return node;
        else
            return null;
    }
    
    public boolean searchRecursive(String llave, NodeTrieWords actual){
        if(actual == null)
            return false;
        if(llave == "")
            return actual.cant > 0;
        System.out.println(llave + " " + actual.cant);
        return searchRecursive(llave.substring(1), actual.sons[ getIndexOf( llave.charAt(0) ) ]);
    }
    
  
    public boolean elimina(String key){
        return elimina(key, root, 0);
    }
    private boolean elimina(String key, NodeTrieWords node, int depth){
        boolean resp;
        
        //si si esta, la borro
        if(depth == key.length()){
            resp = node.cant > 0;
            node.cant--;
            return resp;
        }
        //primero lo busco, y de regreso, si el hijo de donde se borro no tiene
        // tiene hijos inicializados y su fin de palabra es falso, lo hago nulo
        else{
            //int pos = (int)llave.charAt(charActual);//actual.getPosChar((int)llave.charAt(charActual), simbolos)
            NodeTrieWords sig = node.sons[depth];
            //si el caracter actual no esta inicializado, no esta la palabra
            if(sig == null)
                return false;
            //si el caracter actual si esta, puede que si exista la palabra
            else{
                resp = elimina(key, sig, depth+1);
                //despues de regresar de la llamada recursiva
                if(sig.isEmpty()  && sig.cant == 0){
                    sig = null;
                    //actual.hijosInicializados -=1;
                }
            }
            return resp;
        }  

        
    }
    
    
    public void deleteWord(String word){
        if(word==null || word.length()==0)
            throw new RuntimeException("Empty parameter.");
        else
            deleteWordA(root, word,0);
    }
    
    private NodeTrieWords deleteWord(NodeTrieWords node, String word){
        if(node == null)
            System.out.println("");
        
        if(word == ""){
            node.cant--;
            if(node.isEmpty())
                return null;
            else 
                return node;
        }
        NodeTrieWords sig = node.sons[ getIndexOf( word.charAt(0) ) ];
        NodeTrieWords temp = deleteWord( sig, word.substring(1));
        node.sons[ getIndexOf( word.charAt(0) ) ] = temp;
        
        if(node.cant == 0 && node.isEmpty())
            return null;
        else
            return node;
    }
    private NodeTrieWords deleteWordA(NodeTrieWords node, String word, int depth){
        //SI EL NODO ES NULO:
        
        if(node==null){
            System.out.println("1.-nulo");
            return null;
        }

        //SI EL NODO YA CORRESPONDE  AL FINAL DE LA PALABRA:
        
        if(depth == word.length() && node.cant>0){
            node.cant--;
            return node;
        }
        
        //SI AUN NO LLEGO AL FINAL DE LA PALABRA:
        deleteWordA(node.sons[getIndexOf(word.charAt(depth))], word, depth+1);
        
        
        if(node.sons[ getIndexOf( word.charAt(depth) ) ].isEmpty() && node.sons[ getIndexOf( word.charAt(depth) ) ].cant == 0){ //node.sons[ getIndexOf( word.charAt(depth) ) ].isEmpty();
            //node= null;
            node.sons[ getIndexOf( word.charAt(depth) ) ] = null;
        }
        else if(node.sons[ getIndexOf( word.charAt(depth) ) ].isEmpty() && node.cant == 1){ //node.sons[ getIndexOf( word.charAt(depth) ) ].isEmpty();
            node= null;
            //node.sons[ getIndexOf( word.charAt(depth) ) ] = null;
        }
        return null;
    }
    
 
    
    public void print(){
        print(root);
    }
    private void print(NodeTrieWords node){
        if(node != null && !node.empty){
            for(int i=0; i<27; i++){
                if(node.sons[i]!=null){
                    System.out.print(simbols[i]);
                    if (node.sons[i].cant >0) System.out.print("("+node.sons[i].cant+")");
                    print(node.sons[i]);
                } 
            }  
        }else
            System.out.println("  ");
    }

    public int getIndexOf(char c){
        int i=0;
        while(c != simbols[i])
            i++;
        return i;
    }
    
    private void preOrder(ArrayList<String> lista){
        preOrder("", root, lista);
    }
    
    private void preOrder(String palabra, NodeTrieWords actual, ArrayList<String> lista){
            if(actual.cant>0)
                lista.add(palabra);
            int i = -1, cont = 0;
            for(NodeTrieWords h : actual.sons){
                ++i;
                if(h != null){
                    preOrder(palabra + simbols[i], h, lista);
                    ++cont;
                }
                if(actual.hijosInicializados < i)
                    break;
            }
    }
    
    public static ArrayList<String> ordenaLexicograficamente(ArrayList<String> palabras){
        if(palabras == null)
            throw new NullPointerException();
        WordTrie t = new WordTrie();
        for(String p : palabras)
            t.insertWord(p);
        palabras = new ArrayList<>();
        //t.preOrder(palabras);
        return palabras;
    }
    
    public static void main (String [] args){
        WordTrie trie = new WordTrie();
        trie.insertWord("si");
        
        trie.insertWord("teo");
        trie.insertWord("sipi");
        //trie.insertWord("perreo");
        trie.insertWord("perreo");
        trie.insertWord("perro");
        trie.insertWord("teodoro");
        trie.insertWord("juan");
        //trie.insertRecursive("hola", trie.root);
        trie.print();
        
        System.out.println("");
        //trie.searchRecursive( "teo", trie.root);
        //trie.elimina("teodoro");
        trie.deleteWord("perreo");
        trie.print();
        System.out.println("\n");
        //System.out.println("\n"+trie.searchWordP("juan").cant);
        /*
        System.out.println("Busca la palabra teo: " + trie.searchWord("teo"));
        System.out.println("Busca la palabra te: " + trie.searchWord("te"));
        System.out.println("Busca la palabra gato: " + trie.searchWord("gato"));
        */
    }
}
