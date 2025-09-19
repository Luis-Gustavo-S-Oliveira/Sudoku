package br.com.main;

import br.com.modelo.Board;
import java.util.Scanner;
import static java.util.stream.Collectors.*;
import java.util.stream.Stream;


public class Main {
    private final static Scanner Scan = new Scanner(System.in);

    private static Board board;

    private final static int TAMANHO_BOARD = 9;
    public static void main(String[] args) {
        final var positions = Stream.of(args)
                .collect(toMap(
                        k -> k.split(";")[0],
                        v -> v.split(";")[1]
                ));
        var option = -1;
    }

}
