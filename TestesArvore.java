public class TestesArvore {
    public static void main(String[] args) {
        ArvoreBinariaPesquisa arvore = new ArvoreBinariaPesquisa();

        // 1. Insere os valores na árvore
        int[] valores = {40, 20, 60, 10, 30, 50};
        for (int valor : valores) {
            arvore.inserir(valor);
        }

        // 2. Imprime a árvore inicial
        System.out.println("Árvore após inserções:");
        arvore.imprimir();

        // 3. Remove o nó 20
        System.out.println("\nRemovendo 20...");
        arvore.remover(20);

        // 4. Imprime a árvore atualizada
        System.out.println("\nÁrvore após remover o 20:");
        arvore.imprimir();
    }
}