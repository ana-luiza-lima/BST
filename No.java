/**
 * Nó usado pela árvore binária de pesquisa.
 * Os campos são acessíveis no mesmo pacote para manter a implementação simples.
 */
public class No {
    int chave;
    No esquerdo;
    No direito;

    public No(int chave) {
        this.chave = chave;
    }
}
