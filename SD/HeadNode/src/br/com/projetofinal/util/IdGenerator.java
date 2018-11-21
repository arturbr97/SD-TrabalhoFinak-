package br.com.projetofinal.util;

abstract public class IdGenerator {

    public static int getId() {
        return 1000 + (int) (Math.random() * 100000);
    }
}
