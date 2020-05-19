package br.com.codenation.desafioexe;

import java.util.List;
import java.util.ArrayList;

public class DesafioApplication {

    public static List<Integer> fibonacci() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        for (int index = 2; list.get(index - 1) <= 350; index++) {
            list.add(list.get(index - 1) + list.get(index - 2));
        }
        return list;
    }

    public static Boolean isFibonacci(Integer a) {
        return fibonacci().contains(a);
    }

}