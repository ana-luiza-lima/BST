import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma árvore binária de pesquisa cujas chaves são inteiros.
 * Para cada nó, todas as chaves da subárvore esquerda são menores e todas
 * as chaves da subárvore direita são maiores.
 */
public class ArvoreBinariaPesquisa {
    private No raiz;
    private int tamanho;

    public boolean vazia() {
        return raiz == null;
    }

    public int tamanho() {
        return tamanho;
    }

    // Chaves repetidas são ignoradas.
    public boolean inserir(int chave) {
        int tamanhoAnterior = tamanho;
        raiz = inserir(raiz, chave);
        return tamanho > tamanhoAnterior;
    }

    private No inserir(No atual, int chave) {
        if (atual == null) {
            tamanho++;
            return new No(chave);
        }

        if (chave < atual.chave) {
            atual.esquerdo = inserir(atual.esquerdo, chave);
        } else if (chave > atual.chave) {
            atual.direito = inserir(atual.direito, chave);
        }

        return atual;
    }

    public boolean buscar(int chave) {
        No atual = raiz;

        while (atual != null) {
            if (chave == atual.chave) {
                return true;
            }

            if (chave < atual.chave) {
                atual = atual.esquerdo;
            } else {
                atual = atual.direito;
            }
        }

        return false;
    }

    public int minimo() {
        if (raiz == null) {
            throw new IllegalStateException("A árvore está vazia.");
        }
        return encontrarMinimo(raiz).chave;
    }

    private No encontrarMinimo(No atual) {
        while (atual.esquerdo != null) {
            atual = atual.esquerdo;
        }
        return atual;
    }

    public int maximo() {
        if (raiz == null) {
            throw new IllegalStateException("A árvore está vazia.");
        }

        No atual = raiz;
        while (atual.direito != null) {
            atual = atual.direito;
        }
        return atual.chave;
    }

    public boolean remover(int chave) {
        int tamanhoAnterior = tamanho;
        raiz = remover(raiz, chave);
        return tamanho < tamanhoAnterior;
    }

    private No remover(No atual, int chave) {
        if (atual == null) {
            return null;
        }

        if (chave < atual.chave) {
            atual.esquerdo = remover(atual.esquerdo, chave);
        } else if (chave > atual.chave) {
            atual.direito = remover(atual.direito, chave);
        } else if (atual.esquerdo == null) {
            tamanho--;
            return atual.direito;
        } else if (atual.direito == null) {
            tamanho--;
            return atual.esquerdo;
        } else {
            // Com dois filhos, substitui pelo menor valor da subárvore direita.
            No sucessor = encontrarMinimo(atual.direito);
            atual.chave = sucessor.chave;
            atual.direito = remover(atual.direito, sucessor.chave);
        }

        return atual;
    }

    public List<Integer> emOrdem() {
        List<Integer> valores = new ArrayList<>();
        emOrdem(raiz, valores);
        return valores;
    }

    private void emOrdem(No atual, List<Integer> valores) {
        if (atual == null) {
            return;
        }
        emOrdem(atual.esquerdo, valores);
        valores.add(atual.chave);
        emOrdem(atual.direito, valores);
    }

    public List<Integer> preOrdem() {
        List<Integer> valores = new ArrayList<>();
        preOrdem(raiz, valores);
        return valores;
    }

    private void preOrdem(No atual, List<Integer> valores) {
        if (atual == null) {
            return;
        }
        valores.add(atual.chave);
        preOrdem(atual.esquerdo, valores);
        preOrdem(atual.direito, valores);
    }

    public List<Integer> posOrdem() {
        List<Integer> valores = new ArrayList<>();
        posOrdem(raiz, valores);
        return valores;
    }

    private void posOrdem(No atual, List<Integer> valores) {
        if (atual == null) {
            return;
        }
        posOrdem(atual.esquerdo, valores);
        posOrdem(atual.direito, valores);
        valores.add(atual.chave);
    }

    // Árvore vazia tem altura -1; uma folha tem altura 0.
    public int altura() {
        return altura(raiz);
    }

    private int altura(No atual) {
        if (atual == null) {
            return -1;
        }
        return 1 + Math.max(altura(atual.esquerdo), altura(atual.direito));
    }

    // Imprime a árvore deitada, com os valores da direita acima dos da esquerda.
    public void imprimir() {
        if (raiz == null) {
            System.out.println("  (árvore vazia)");
            return;
        }
        imprimir(raiz, 0);
    }

    private void imprimir(No atual, int nivel) {
        if (atual == null) {
            return;
        }
        imprimir(atual.direito, nivel + 1);
        System.out.println("  " + "      ".repeat(nivel) + atual.chave);
        imprimir(atual.esquerdo, nivel + 1);
    }
}
