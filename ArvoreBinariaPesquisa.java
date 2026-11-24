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

    // insere uma nova chave e retorna true se ela foi adicionada (chaves repetidas são ignoradas).
    public boolean inserir(int chave) {
        int tamanhoAnterior = tamanho;
        raiz = inserir(raiz, chave);
        return tamanho > tamanhoAnterior;
    }

    // percorre a árvore recursivamente até achar uma posição vazia para o novo nó.
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

    // procura uma chave na árvore de forma iterativa; retorna true se encontrar.
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

    // retorna o menor valor da árvore
    public int minimo() {
        if (raiz == null) {
            throw new IllegalStateException("A árvore está vazia.");
        }
        return encontrarMinimo(raiz).chave;
    }

    // o menor valor está sempre no nó mais à esquerda.
    private No encontrarMinimo(No atual) {
        while (atual.esquerdo != null) {
            atual = atual.esquerdo;
        }
        return atual;
    }

    // retorna o maior valor da árvore (nó mais à direita).
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

    // remove uma chave da árvore e retorna true se ela existia e foi removida.
    public boolean remover(int chave) {
        int tamanhoAnterior = tamanho;
        raiz = remover(raiz, chave);
        return tamanho < tamanhoAnterior;
    }

    // busca e remove o nó recursivamente, ajustando os ponteiros para manter a árvore válida.
    private No remover(No atual, int chave) {
        if (atual == null) {
            return null; // Chave não encontrada.
        }

        if (chave < atual.chave) {
            atual.esquerdo = remover(atual.esquerdo, chave);

        } else if (chave > atual.chave) {
            atual.direito = remover(atual.direito, chave);

        } else if (atual.esquerdo == null) {
            // Caso sem filho esquerdo (ou nó folha): sobe o filho direito.
            tamanho--;
            return atual.direito;

        } else if (atual.direito == null) {
            // Caso sem filho direito: sobe o filho esquerdo.
            tamanho--;
            return atual.esquerdo;
            
        } else {
            // Caso com dois filhos: substitui pelo menor valor da subárvore direita e remove o duplicado.
            No sucessor = encontrarMinimo(atual.direito);
            atual.chave = sucessor.chave;
            atual.direito = remover(atual.direito, sucessor.chave);
        }

        return atual;
    }

    // percurso Em-Ordem (Esquerda -> Raiz -> Direita): retorna os valores em ordem crescente.
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

    // percurso Pré-Ordem (Raiz -> Esquerda -> Direita): útil para copiar ou salvar a estrutura da árvore.
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

    // percurso Pós-Ordem (Esquerda -> Direita -> Raiz): visita os filhos antes do pai.
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

    // calcula a altura da árvore (vazia = -1; apenas raiz = 0).
    public int altura() {
        return altura(raiz);
    }

    private int altura(No atual) {
        if (atual == null) {
            return -1;
        }
        // Soma 1 à maior altura entre as subárvores esquerda e direita.
        return 1 + Math.max(altura(atual.esquerdo), altura(atual.direito));
    }

    // imprime a árvore deitada no console, com os valores da direita acima dos da esquerda.
    public void imprimir() {
        if (raiz == null) {
            System.out.println("  (árvore vazia)");
            return;
        }
        imprimir(raiz, 0);
    }

    // usa o nível do nó para adicionar espaços (indentação) e mostrar a hierarquia.
    private void imprimir(No atual, int nivel) {
        if (atual == null) {
            return;
        }
        imprimir(atual.direito, nivel + 1);
        System.out.println("  " + "      ".repeat(nivel) + atual.chave);
        imprimir(atual.esquerdo, nivel + 1);
    }
}