# Árvore Binária de Pesquisa em Java

Este projeto implementa uma **Árvore Binária de Pesquisa (BST)** com chaves inteiras. Os arquivos são independentes e não usam bibliotecas externas.

## Estrutura do projeto

- `ArvoreBinariaPesquisa.java`: implementação da árvore e de suas operações.
- `No.java`: representa um nó com uma chave e referências para os filhos esquerdo e direito.
- `Main.java`: demonstração executável dos principais métodos.
- `TestesArvore.java`: suíte de testes sem framework externo.

## Estrutura de dados

A árvore contém uma referência para o nó raiz e uma variável com a quantidade de nós.

```text
              40
             /  \
           20    60
          /  \    \
        10   30   50
```

Para cada nó:

- todas as chaves da subárvore esquerda são menores que a chave do nó;
- todas as chaves da subárvore direita são maiores que a chave do nó;
- chaves repetidas não são adicionadas.

Exemplo de busca pelo valor `30`:

- a busca começa na raiz (`40`);
- `30` é menor que `40`, então segue pela esquerda;
- `30` é maior que `20`, então segue pela direita;
- encontra `30` e retorna `true`.

## Métodos disponíveis

| Método | Descrição |
|---|---|
| `vazia()` | Retorna `true` quando não existem nós. |
| `tamanho()` | Retorna a quantidade de nós armazenados. |
| `inserir(chave)` | Insere uma chave e retorna `true`; retorna `false` para uma chave repetida. |
| `buscar(chave)` | Retorna `true` se a chave estiver na árvore. |
| `minimo()` | Retorna a menor chave. |
| `maximo()` | Retorna a maior chave. |
| `remover(chave)` | Remove uma chave e retorna `true`; retorna `false` se ela não existir. |
| `emOrdem()` | Percorre esquerda, nó atual e direita; devolve as chaves em ordem crescente. |
| `preOrdem()` | Percorre nó atual, esquerda e direita. |
| `posOrdem()` | Percorre esquerda, direita e nó atual. |
| `altura()` | Retorna a altura em número de níveis entre a raiz e o nó mais profundo. Uma árvore vazia tem altura `-1` e uma folha tem altura `0`. |
| `imprimir()` | Mostra visualmente a árvore no terminal. |

## Remoção de nós

A remoção considera três casos:

1. **Folha:** remove o nó diretamente.
2. **Um filho:** substitui o nó pelo seu único filho.
3. **Dois filhos:** substitui a chave pelo menor valor da subárvore direita, conhecido como sucessor, e remove esse sucessor.

## Como executar a demonstração

Na raiz do projeto, execute no PowerShell:

```powershell
javac *.java
java Main
```

A demonstração cria uma árvore com as chaves `40, 20, 60, 10, 30, 50`, mostra sua representação gráfica e executa buscas, mínimos, máximos, percursos, altura e remoção.

## Como executar os testes

```powershell
javac *.java
java TestesArvore
```

O resultado esperado é semelhante a:

```text
Resumo: 29 testes OK, 0 falhas.
```

Os testes cobrem percursos, busca, árvore vazia, um único valor, duplicatas, casos de remoção e altura. Em caso de falha, o programa termina com uma exceção `AssertionError`.

## Complexidade

No caso médio, as operações seguem pela altura da árvore:

| Operação | Caso médio | Pior caso |
|---|---:|---:|
| Inserção | `O(log n)` | `O(n)` |
| Busca | `O(log n)` | `O(n)` |
| Remoção | `O(log n)` | `O(n)` |
| Percurso completo | `O(n)` | `O(n)` |

O pior caso ocorre quando as inserções produzem uma cadeia, como acontece ao inserir valores crescentes. Uma árvore equilibrada mantém alturas próximas de `log n`.
