import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.*;
import java.util.function.*;

public class IntermediateOperationsAdvanced {
    public static void main(String args[]) throws IllegalAccessException, InvocationTargetException {
        // Obtém todas as methods da classe
        Method[] methods = IntermediateOperationsAdvanced.class.getDeclaredMethods();

        // Filtra os métodos que começam com "questao"
        List<Method> questoes = Arrays.stream(methods)
                .filter(m -> m.getName().startsWith("questao"))
                .sorted(Comparator.comparingInt(m -> Integer.parseInt(m.getName().replaceAll("\\D+", ""))))
                .collect(Collectors.toList());

        // Obtém a última questão
        Method ultimaQuestao = questoes.get(questoes.size() - 1);

        System.out.println("Executando: " + ultimaQuestao.getName());

        // Invoca o método (como é static, o primeiro parâmetro é null)
        ultimaQuestao.invoke(null);
    }

    static Consumer<Integer> println = System.out::println;

    // takeWhile
    static void questao01() {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);
        Predicate<Integer> fun1 = (Integer value) -> value < 3;
        list.stream().takeWhile(fun1).forEach(println);
    }

    // dropWhile
    static void questao02() {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);
        Predicate<Integer> fun1 = (Integer value) -> value < 3;
        list.stream().dropWhile(fun1).forEach(println);
    }

    // onClose
    static void questao03() {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);
        Predicate<Integer> fun1 = (Integer value) -> value < 3;

        Runnable fun2 = new Runnable() {
            public void run() {
                System.out.println("On Close Test");
            }
        };

        list.stream().dropWhile(fun1).onClose(fun2).forEach(println);
        list.stream().close();
    }

    // boxed, asLongStream, asDoubleStream
    static void questao04() {
        IntStream intStream = IntStream.range(1, 5);
        Stream<Integer> boxedStream = intStream.boxed();
        DoubleStream a;
        LongStream b = null;
        b.asDoubleStream();
        intStream.asDoubleStream();
        intStream.asLongStream();
    }

    // mapToInt
    static void questao05_() {
    }

    // flatMapToInt
    static void questao06_() {
    }

}
