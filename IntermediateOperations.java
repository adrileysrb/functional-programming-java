import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.function.*;

public class IntermediateOperations {
    public static void main(String args[]) throws IllegalAccessException, InvocationTargetException {
        // Obtém todas as methods da classe
        Method[] methods = IntermediateOperations.class.getDeclaredMethods();

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

    static Consumer<Object> print = (value) -> System.out.println(value);

    // filter
    static void questao01() {
        Predicate<Integer> fun1 = (value) -> value % 2 == 0;
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        numbers.stream().filter(fun1).forEach(print);
    }

    // map
    static void questao02() {
        List<String> names = List.of("Alice", "Bob", "Charlie");
        names.stream().map(String::toUpperCase).forEach(print);
    }

    // trying to use map do extract some field of object in a list
    static void questao03() {
        record Test(Integer id, String name) {
        }
        List<Test> listTest = List.of(new Test(1, "Small"), new Test(2, "Ismar"));
        listTest.stream().map(value -> value.name).forEach(print);
    }

    // flatMap
    static void questao04() {
        record Test(Integer id, List<String> name) {
        }
        List<Test> listTest = List.of(new Test(1, List.of("Small", "Name1")), new Test(2, List.of("Ismar", "Name2")));
        List<String> result = listTest.stream().flatMap((value) -> value.name.stream()).toList();
        print.accept(result);
    }

    // sorted
    static void questao05() {
        List<Integer> list = Arrays.asList(9, 1, 2, 3);
        list.set(2, 4);
        Comparator<Integer> fun1 = (a, b) -> b.compareTo(a);
        list.stream().sorted(fun1).forEach(print);
    }

    // distinct
    static void questao06() {
        List<Integer> list = Arrays.asList(9, 1, 2, 3, 2, 1, 1, 2);
        List<Integer> result = list.stream().distinct().toList();
        result.forEach(print);
    }

    // try to use Set instead use distinct
    static void questao07() {
        List<Integer> list = Arrays.asList(9, 1, 2, 3, 2, 1, 1, 2);
        list.stream().collect(Collectors.toCollection(LinkedHashSet::new)).forEach(print);
    }

    // limit
    static void questao08() {
        List<Integer> list = Arrays.asList(9, 1, 2, 3, 2, 1, 1, 2);
        list.stream().limit(2).forEach(print);
    }

    // skip
    static void questao09() {
        List<Integer> list = Arrays.asList(9, 1, 2, 3, 2, 1, 1, 2);
        list.stream().skip(1).forEach(print);
    }

    // peek
    static void questao10() {
        List<Integer> list = Arrays.asList(9, 1, 2, 3, 2, 1, 1, 2);
        list.stream().peek((value) -> System.out.println("Peek value: " + value)).filter((a) -> a % 2 == 0).peek(print).toList();
    }

}
