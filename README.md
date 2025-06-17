# Jogo de Sudoku em Java

Este projeto é um jogo de Sudoku desenvolvido durante as aulas da DIO, com foco em conceitos de **Programação Orientada a Objetos (POO)** e uso da **Stream API** do Java.

## Sobre o Projeto

O objetivo deste projeto é demonstrar como aplicar princípios de POO para estruturar um jogo de Sudoku, além de utilizar a Stream API para manipulação eficiente de coleções, validação de regras e geração de tabuleiros.

## Como Iniciar

O jogo possui uma interface de usuário (UI) simples para facilitar a interação. Para iniciar o jogo, siga os passos abaixo:

1. **Clone o repositório:**

   ```sh
   git clone https://github.com/seu-usuario/seu-repositorio.git
   ```

2. **Abra o projeto no Visual Studio Code** ou na sua IDE Java preferida.

3. **Compile a aplicação:**

    ```sh
    // Exemplo para rodar via terminal
    javac Main.java
    ```

4. **Execute a aplicação**:

    execute o arquivo main...

    ```sh
    // Exemplo para rodar via terminal
    java Main
    ```

    com os argumentos:

    ```sh
        0,0;4,false 1,0;7,false 2,0;9,true 3,0;5,false 4,0;8,true 5,0;6,true 6,0;2,true 7,0;3,false 8,0;1,false 0,1;1,false 1,1;3,true 2,1;5,false 3,1;4,false 4,1;7,true 5,1;2,false 6,1;8,false 7,1;9,true 8,1;6,true 0,2;2,false 1,2;6,true 2,2;8,false 3,2;9,false 4,2;1,true 5,2;3,false 6,2;7,false 7,2;4,false 8,2;5,true 0,3;5,true 1,3;1,false 2,3;3,true 3,3;7,false 4,3;6,false 5,3;4,false 6,3;9,false 7,3;8,true 8,3;2,false 0,4;8,false 1,4;9,true 2,4;7,false 3,4;1,true 4,4;2,true 5,4;5,true 6,4;3,false 7,4;6,true 8,4;4,false 0,5;6,false 1,5;4,true 2,5;2,false 3,5;3,false 4,5;9,false 5,5;8,false 6,5;1,true 7,5;5,false 8,5;7,true 0,6;7,true 1,6;5,false 2,6;4,false 3,6;2,false 4,6;3,true 5,6;9,false 6,6;6,false 7,6;1,true 8,6;8,false 0,7;9,true 1,7;8,true 2,7;1,false 3,7;6,false 4,7;4,true 5,7;7,false 6,7;5,false 7,7;2,true 8,7;3,false 0,8;3,false 1,8;2,false 2,8;6,true 3,8;8,true 4,8;5,true 5,8;1,false 6,8;4,true 7,8;7,false 8,8;9,false
    ```

5. **Interface de Usuário:**
   - Ao iniciar, a UI exibirá o tabuleiro de Sudoku.
   - selecione o quadro que deseja inserir um número e insira-o.
   - A qualquer momento você clicar em `verificar o Status do jogo` para consultar se há erros
   - Clique em resetar se deseja apagar todos os campos inseridos.
   - ao concluir clique em `concluir`.

Um bom jogo para você