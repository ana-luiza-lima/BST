public class Main {

    public static void main(String[] args) {
        ArvoreBinariaPesquisa arvore = new ArvoreBinariaPesquisa();
        int[] valores = {40, 20, 60, 10, 30, 50, 40};

        for (int valor : valores) {
            boolean inserido = arvore.inserir(valor);
            if (!inserido) {
                System.out.println("Valor " + valor + " já existe e foi ignorado.");
            }
        }

        System.out.println("Árvore binária de pesquisa:");
        arvore.imprimir();

        System.out.println("\nTamanho: " + arvore.tamanho());
        System.out.println("Busca pelo valor 30: " + arvore.buscar(30));
        System.out.println("Menor valor: " + arvore.minimo());
        System.out.println("Maior valor: " + arvore.maximo());
        System.out.println("Percurso em ordem: " + arvore.emOrdem());
        System.out.println("Percurso pré-ordem: " + arvore.preOrdem());
        System.out.println("Percurso pós-ordem: " + arvore.posOrdem());
        System.out.println("Altura: " + arvore.altura());

        arvore.remover(20);
        System.out.println("\nApós remover 20: " + arvore.emOrdem());
        System.out.println("Novo tamanho: " + arvore.tamanho());
        System.out.println("Remover 20 novamente: " + arvore.remover(20));
    }
}
