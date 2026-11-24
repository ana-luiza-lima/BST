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

## Demonstração passo a passo

O método `main` em `Main.java` executa uma sequência fixa para mostrar as operações:

1. **Cria uma árvore vazia.**
2. **Insere as chaves** `40, 20, 60, 10, 30, 50, 40`, nessa ordem. As seis primeiras formam a árvore do exemplo. A última chave (`40`) é repetida, então `inserir` retorna `false` e a demonstração informa que o valor foi ignorado.
3. **Imprime a árvore.** O método `imprimir()` desenha a árvore de lado: visita primeiro o ramo direito, imprime o nó e depois visita o ramo esquerdo. Por isso, os valores maiores aparecem acima e a raiz fica alinhada à esquerda.
4. **Consulta o estado e os valores:** mostra o tamanho (`6`), busca o valor `30` (`true`), e consulta o mínimo (`10`) e o máximo (`60`).
5. **Executa os três percursos:**
   - em ordem: `[10, 20, 30, 40, 50, 60]`;
   - pré-ordem: `[40, 20, 10, 30, 60, 50]`;
   - pós-ordem: `[10, 30, 20, 50, 60, 40]`.
6. **Mostra a altura**, que é `2`: o caminho mais longo da raiz até uma folha tem dois ramos (por exemplo, `40 -> 20 -> 10`).
7. **Remove `20`**, que tem dois filhos (`10` e `30`). A implementação usa o sucessor em ordem, `30`, para manter a propriedade de pesquisa. O percurso em ordem passa a ser `[10, 30, 40, 50, 60]` e o tamanho passa a `5`.
8. **Tenta remover `20` novamente.** Como a chave já não está na árvore, `remover` retorna `false`.

Assim, a demonstração exercita inserção normal e duplicada, impressão, tamanho, busca, mínimo, máximo, percursos, altura e os resultados de sucesso e falha da remoção.

### Como executar a demonstração

Na raiz do projeto, execute no PowerShell:

```powershell
javac *.java
java Main
```

## Testes passo a passo

Os testes ficam em `TestesArvore.java` e são executados em grupos. Cada chamada a `testar(...)` compara o resultado obtido com o esperado; resultados iguais são contados como `[OK]`, diferenças como `[FALHA]`. O conjunto usa uma árvore de exemplo com as chaves `40, 20, 60, 10, 30, 50`, salvo nos casos em que o teste constrói outra árvore.

### Como executar

Na raiz do projeto, execute:

```powershell
javac *.java
java TestesArvore
```

Cada linha `[OK]` indica uma verificação aprovada. No fim, o programa imprime o total; se houver qualquer falha, lança `AssertionError` para que a execução seja reportada como malsucedida.

### O que cada teste verifica

#### `testarPercursosEBusca` — 8 verificações

Usa a árvore de exemplo e verifica:

1. `emOrdem()` retorna `[10, 20, 30, 40, 50, 60]`, confirmando que esse percurso entrega as chaves em ordem crescente.
2. `preOrdem()` retorna `[40, 20, 10, 30, 60, 50]`, visitando cada nó antes dos filhos.
3. `posOrdem()` retorna `[10, 30, 20, 50, 60, 40]`, visitando os filhos antes do nó.
4. `buscar(30)` retorna `true` para uma chave presente.
5. `buscar(35)` retorna `false` para uma chave ausente.
6. `altura()` retorna `2` para a árvore de exemplo.
7. `minimo()` retorna `10`, o valor mais à esquerda.
8. `maximo()` retorna `60`, o valor mais à direita.

#### `testarArvoreVazia` — 7 verificações

Confirma o comportamento sem elementos:

1. `vazia()` retorna `true`.
2. `buscar(10)` retorna `false`.
3. `altura()` retorna `-1`, convenção definida para árvore vazia.
4. `remover(10)` retorna `false`, pois nada pode ser removido.
5. `emOrdem()` retorna uma lista vazia (`[]`).
6. `minimo()` lança `IllegalStateException`, pois não há mínimo.
7. `maximo()` lança `IllegalStateException`, pois não há máximo.

Os dois últimos casos usam `testarExcecao`: o teste falha se a operação não lançar exceção ou se lançar um tipo diferente do esperado.

#### `testarArvoreComUmValor` — 2 verificações

Insere `5` numa árvore vazia e verifica que a altura de uma árvore com apenas a raiz é `0`. Em seguida, remove `5` e confirma que a árvore voltou a ficar vazia.

#### `testarInsercaoDuplicada` — 2 verificações

Tenta inserir novamente a raiz (`40`) na árvore de exemplo. Verifica que `inserir(40)` retorna `false` e que `tamanho()` continua `6`, ou seja, uma duplicata não cria outro nó.

#### `testarRemocao` — 8 verificações

Exercita os principais cenários de remoção:

1. Remove `10`, uma folha, e verifica que o percurso em ordem não contém mais esse valor.
2. Confirma que o tamanho diminuiu de `6` para `5`.
3. Tenta remover `999`, uma chave ausente, e espera `false`.
4. Confirma que essa tentativa não alterou o tamanho (`5`).
5. Numa árvore específica, remove `20`, que tem apenas um filho (`10`), e verifica a pré-ordem `[40, 10, 60]`. Isso confirma que o filho ocupa corretamente o lugar do nó removido.
6. Na árvore de exemplo, remove `20`, que tem dois filhos, e verifica a pré-ordem `[40, 30, 10, 60, 50]`. Isso confirma a substituição pelo sucessor `30` e a estrutura resultante.
7. Verifica também o percurso em ordem `[10, 30, 40, 50, 60]`, assegurando que a propriedade de pesquisa foi preservada.
8. Remove a raiz `40` e verifica a pré-ordem `[50, 20, 10, 30, 60]`, cobrindo especificamente a remoção da raiz.

#### `testarAltura` — 2 verificações

1. Insere `1, 2, 3, 4, 5, 6, 7` em ordem crescente. Como cada valor novo fica à direita do anterior, a árvore vira uma cadeia e sua altura é `6`.
2. Insere os valores `4, 2, 6, 1, 3, 5, 7`. Essa ordem distribui os nós de modo equilibrado, resultando em altura `2`.

No total, são **29 verificações**. O resumo esperado é:

```text
Resumo: 29 testes OK, 0 falhas.
```

## Complexidade

No caso médio, as operações seguem pela altura da árvore:

| Operação | Caso médio | Pior caso |
|---|---:|---:|
| Inserção | `O(log n)` | `O(n)` |
| Busca | `O(log n)` | `O(n)` |
| Remoção | `O(log n)` | `O(n)` |
| Percurso completo | `O(n)` | `O(n)` |

O pior caso ocorre quando as inserções produzem uma cadeia, como acontece ao inserir valores crescentes. Uma árvore equilibrada mantém alturas próximas de `log n`.
