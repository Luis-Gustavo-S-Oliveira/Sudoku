package br.com.main;

import br.com.modelo.Board;
import br.com.modelo.Espaco;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import java.util.Scanner;
import static java.util.stream.Collectors.*;
import java.util.stream.Stream;


public class Main {
    private final static Scanner scan = new Scanner(System.in);

    private static Board board;

    private final static int TAMANHO_BOARD = 9;
    public static void main(String[] args) {

        final Map<String, String> positions = Stream.of(args)
                .collect(toMap(
                        k -> k.split(";")[0],
                        v -> v.split(";")[1]
                ));
        var option = -1;

        
        while (true){
                System.out.println("Selecione uma das opções a seguir");
            System.out.println("1 - Iniciar um novo Jogo");
            System.out.println("2 - Colocar um novo número");
            System.out.println("3 - Remover um número");
            System.out.println("4 - Visualizar jogo atual");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - limpar jogo");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");

            option = scan.nextInt();

            switch (option) {
                case 1 -> iniciarJogo(positions);
                case 2 -> entradaNumero();
                case 3 -> removerNumero(); 
                case 4 -> mostrarGame(); 
                case 5 -> estadoDoJogo();
                case 6 -> limparJogo();
                case 7 -> finalizarJogo(); 
                case 8 -> System.exit(0); 
                default -> System.out.println("Opção inválida");

            }
        }
    }

    private static void finalizarJogo() {
        if (isNull(board)){
            System.out.println("O jogo ainda não foi iniciado iniciado");
            return;
        }

        if (board.estaCompleto()){
            System.out.println("Parabéns você concluiu o jogo");
            mostrarGame();
            board = null;
        } else if (board.temErro()) {
            System.out.println("Seu jogo conté, erros, verifique seu board e ajuste-o");
        } else {
            System.out.println("Você ainda precisa preenhcer algum espaço");
        }   


    }

    private static void  limparJogo() {
        board.reset();
        
    }

    private static void iniciarJogo(final Map<String,String> positions){
        if(nonNull(board)){
            System.out.println("Já existe um jogo em andamento");
            return;
        }

        List<List<Espaco>> espacos = new ArrayList<>();
   
        for (int i = 0; i < TAMANHO_BOARD; i++) {
            espacos.add(new ArrayList<>());
            for (int j = 0; j < TAMANHO_BOARD; j++) {
                String posicaoConfig = positions.get("%s,%s".formatted(i,j));
                int esperado  = Integer.parseInt(posicaoConfig.split(",")[0]);
                boolean fixo = Boolean.parseBoolean(posicaoConfig.split(",")[1]);
                Espaco espacoCerto = new Espaco(esperado, fixo);
                espacos.get(i).add(espacoCerto);  
            }
        }

        board = new Board(espacos);
        System.out.println("Jogo iniciado com sucesso");
    }

    public static void entradaNumero(){
        if(isNull(board)){
            System.out.println("Nenhum jogo em andamento");
            return;
        }
          System.out.println("Digite a coluna");
        int coluna = runUntilGetValidNumber(0,8);
        System.out.println("Digite a linha");
        int linha = runUntilGetValidNumber(0,8);
        System.out.println("Digite o valor");
        int valor = runUntilGetValidNumber(1,9);
        if(board.alteralValor(coluna, linha, valor)){
            System.out.println("Valor alterado com sucesso");
        }
      
    }

    public static void removerNumero(){
        if(isNull(board)){
            System.out.println("Nenhum jogo em andamento");
        }
        System.out.println("Digite a coluna");
        int coluna = runUntilGetValidNumber(0,8);
        System.out.println("Digite a linha");
        int linha = runUntilGetValidNumber(0,8);
        
        if(board.limparvalores(coluna, linha)){
            System.out.println("Valor excluido com sucesso");
        } 
        
        


    }
    
    public static void mostrarGame(){
        if(isNull(board)){
            System.out.println("Nenhum jogo em andamento");
        }

        var args = new Object[81];   
        var argsPos = 0;
        for (int i = 0; i < TAMANHO_BOARD; i++) {       
            for (var coluna : board.getBoard()) {
                args[argsPos ++] = " " + ((isNull(coluna.get(i).getValor())) ? " " : coluna.get(i).getValor());
            }
            
        }
        

    }

    public static void estadoDoJogo (){
        if(isNull(board)){
            System.out.println("Nenhum jogo em andamento");
        }

        System.out.printf("O estado do jogo é %s\n", board.verificarEstadoJogo().getLabel());
       if(board.temErro()){
        System.out.println("O jogo tem erro");
       }else {System.out.println("O jogo nao tem erro");} 
    }








    
    public static int runUntilGetValidNumber(final int min,final int max){
        int current  = scan.nextInt();
        while (current < min || current > max){
            System.out.println("Número inválido, insira um número entre %s e %s".formatted(min,max));
            current  = scan.nextInt();
        }


        return current;
    
       
    }





    // validacao para entrada de dados nao numericos nao foi feita

}
