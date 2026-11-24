import java.util.Objects;

public class TestesArvore {
    private static int testesOk;
    private static int falhas;

    public static void main(String[] args) {
        testarPercursosEBusca();
        testarArvoreVazia();
        testarArvoreComUmValor();
        testarInsercaoDuplicada();
        testarRemocao();
        testarAltura();

        System.out.println("\nResumo: " + testesOk + " testes OK, " + falhas + " falhas.");
        if (falhas > 0) {
            throw new AssertionError("Há testes que falharam.");
        }
    }

    private static void testarPercursosEBusca() {
        ArvoreBinariaPesquisa arvore = arvoreExemplo();
        testar("Percurso em ordem", "[10, 20, 30, 40, 50, 60]", arvore.emOrdem().toString());
        testar("Percurso pré-ordem", "[40, 20, 10, 30, 60, 50]", arvore.preOrdem().toString());
        testar("Percurso pós-ordem", "[10, 30, 20, 50, 60, 40]", arvore.posOrdem().toString());
        testar("Busca de valor existente", true, arvore.buscar(30));
        testar("Busca de valor inexistente", false, arvore.buscar(35));
        testar("Altura da árvore de exemplo", 2, arvore.altura());
        testar("Menor valor", 10, arvore.minimo());
        testar("Maior valor", 60, arvore.maximo());
    }

    private static void testarArvoreVazia() {
        ArvoreBinariaPesquisa arvore = new ArvoreBinariaPesquisa();
        testar("Árvore vazia", true, arvore.vazia());
        testar("Busca em árvore vazia", false, arvore.buscar(10));
        testar("Altura da árvore vazia", -1, arvore.altura());
        testar("Remoção em árvore vazia", false, arvore.remover(10));
        testar("Percurso em ordem de árvore vazia", "[]", arvore.emOrdem().toString());
        testarExcecao("Mínimo de árvore vazia", IllegalStateException.class, arvore::minimo);
        testarExcecao("Máximo de árvore vazia", IllegalStateException.class, arvore::maximo);
    }

    private static void testarArvoreComUmValor() {
        ArvoreBinariaPesquisa arvore = new ArvoreBinariaPesquisa();
        arvore.inserir(5);
        testar("Altura de árvore com um valor", 0, arvore.altura());
        arvore.remover(5);
        testar("Remover o único valor deixa a árvore vazia", true, arvore.vazia());
    }

    private static void testarInsercaoDuplicada() {
        ArvoreBinariaPesquisa arvore = arvoreExemplo();
        testar("Inserção de valor repetido", false, arvore.inserir(40));
        testar("Tamanho não muda ao inserir valor repetido", 6, arvore.tamanho());
    }

    private static void testarRemocao() {
        ArvoreBinariaPesquisa folha = arvoreExemplo();
        folha.remover(10);
        testar("Remover folha", "[20, 30, 40, 50, 60]", folha.emOrdem().toString());
        testar("Tamanho após remover folha", 5, folha.tamanho());
        testar("Remover chave inexistente", false, folha.remover(999));
        testar("Tamanho não muda após remoção inexistente", 5, folha.tamanho());

        ArvoreBinariaPesquisa umFilho = new ArvoreBinariaPesquisa();
        for (int valor : new int[]{40, 20, 60, 10}) {
            umFilho.inserir(valor);
        }
        umFilho.remover(20);
        testar("Remover nó com um filho", "[40, 10, 60]", umFilho.preOrdem().toString());

        ArvoreBinariaPesquisa doisFilhos = arvoreExemplo();
        doisFilhos.remover(20);
        testar("Percurso após remover nó com dois filhos",
                "[40, 30, 10, 60, 50]", doisFilhos.preOrdem().toString());
        testar("Remover nó com dois filhos", "[10, 30, 40, 50, 60]",
                doisFilhos.emOrdem().toString());

        ArvoreBinariaPesquisa raiz = arvoreExemplo();
        raiz.remover(40);
        testar("Remover a raiz", "[50, 20, 10, 30, 60]", raiz.preOrdem().toString());
    }

    private static void testarAltura() {
        ArvoreBinariaPesquisa emCadeia = new ArvoreBinariaPesquisa();
        for (int valor = 1; valor <= 7; valor++) {
            emCadeia.inserir(valor);
        }
        testar("Inserção crescente forma uma cadeia", 6, emCadeia.altura());

        ArvoreBinariaPesquisa equilibrada = new ArvoreBinariaPesquisa();
        for (int valor : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            equilibrada.inserir(valor);
        }
        testar("Inserção em outra ordem forma árvore menor", 2, equilibrada.altura());
    }

    private static ArvoreBinariaPesquisa arvoreExemplo() {
        ArvoreBinariaPesquisa arvore = new ArvoreBinariaPesquisa();
        for (int valor : new int[]{40, 20, 60, 10, 30, 50}) {
            arvore.inserir(valor);
        }
        return arvore;
    }

    private static void testar(String descricao, Object esperado, Object obtido) {
        if (Objects.equals(esperado, obtido)) {
            testesOk++;
            System.out.println("[OK] " + descricao);
        } else {
            falhas++;
            System.out.println("[FALHA] " + descricao + " — esperado: " + esperado
                    + ", obtido: " + obtido);
        }
    }

    private static void testarExcecao(String descricao, Class<? extends Exception> excecaoEsperada,
            Runnable operacao) {
        try {
            operacao.run();
            falhas++;
            System.out.println("[FALHA] " + descricao + " — nenhuma exceção foi lançada");
        } catch (Exception excecao) {
            testar(descricao, excecaoEsperada, excecao.getClass());
        }
    }
}
