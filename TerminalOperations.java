import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.*;

public class TerminalOperations {
    public static void main(String args[]) throws IllegalAccessException, InvocationTargetException {
        // Obtém todas as methods da classe
        Method[] methods = TerminalOperations.class.getDeclaredMethods();

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

    static Consumer<Object> p = System.out::println;

    // forEach
    static void questao01() {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        list.forEach(p);
        list.stream().forEach(p);
    }

    // toArray
    static void questao02() {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        IntFunction<Integer[]> fun1 = (int size) -> new Integer[size];
        Integer[] result = list.stream().toArray(fun1);
        p.accept(Arrays.toString(result));
    }

    // reduce
    static void questao03() {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        BinaryOperator<Integer> fun1 = (Integer a, Integer b) -> a + b;
        Integer result = list.stream().reduce(10, fun1);
        System.out.println(result);
    }

    // count
    static void questao04() {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        Long result = list.stream().count();
        System.out.println(result);
    }

    // anyMatch
    static void questao05() {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        Predicate<Integer> fun1 = n -> n % 2 == 0;
        boolean result = list.stream().anyMatch(fun1);
        System.out.println(result);
    }

    // allMatch
    static void questao06() {
        List<Integer> list = List.of(2, 3, 4, 5);
        Predicate<Integer> fun1 = n -> n > 1;
        boolean result = list.stream().allMatch(fun1);
        System.out.println(result);
    }

    // noneMatch
    static void questao07() {
        List<Integer> list = List.of(2, 3, 4, 5);
        Predicate<Integer> fun1 = n -> n > 10;
        boolean result = list.stream().noneMatch(fun1);
        System.out.println(result);
    }

    // findFirst, findAny
    static void questao08() {
        List<Integer> list = List.of(2, 3, 4, 5);
        Optional<Integer> result = list.stream().findFirst();
        System.out.println(result);

        Optional<Integer> result2 = list.stream().findAny();
        System.out.println(result2);
    }

    // mix
    static void questao09() {
        List<Integer> list = List.of(2, 3, 4, 5);
        Optional<Integer> result = list.stream().min(Comparator.comparing(a -> a));
        System.out.println(result);
    }

    // max
    static void questao10() {
        List<Integer> list = List.of(2, 3, 4, 5);
        Comparator<Integer> fun1 = (a, b) -> a.compareTo(b);
        Optional<Integer> result = list.stream().max(fun1);
        System.out.println(result);
    }

    // collect - toList
    static void questao11() {
        List<Integer> list = List.of(2, 3, 4, 5);
        List<Integer> result1 = list.stream().collect(Collectors.toList());
        p.accept(result1);
    }

    // collect - topMap
    static void questao12() {
        List<Integer> list = List.of(2, 3, 3, 4, 1, 3, 4, 5);
        Map<Integer, Integer> resultMap = list.stream()
                .collect(Collectors.toMap(a -> a, a -> a, (a, b) -> a + b, HashMap::new));

        p.accept(resultMap);
    }

    // collect - partitioningBy
    static void questao13() {
        List<Integer> list = List.of(2, 3, 3, 4, 1, 3, 4, 5);
        Map<Boolean, List<Integer>> result = list.stream().collect(Collectors.partitioningBy((a) -> a % 2 == 0));
        p.accept(result);
    }

    // collect - groupingBy
    static void questao14() {
        List<Integer> list = List.of(2, 3, 3, 4, 1, 3, 4, 5);
        Function<Integer, Integer> fun1 = (Integer value) -> value;
        Map<Integer, List<Integer>> result = list.stream().collect(Collectors.groupingBy(fun1));
        System.out.println(result);
    }

    // collect - groupingBy - Function, Collector
    static void questao15() {
        List<Integer> list = List.of(2, 3, 3, 4, 1, 3, 4, 5);
        Function<Integer, Integer> fun1 = value -> value;
        Map<Integer, ArrayList<Integer>> result = list.stream().collect(
                Collectors.groupingBy(fun1, Collectors.mapping(a -> a, Collectors.toCollection(ArrayList::new))));
        System.out.println(result);
    }

    // collect - groupingBy - Function, Supplier, Collector
    static void questao16() {
        List<Integer> list = List.of(2, 3, 3, 4, 1, 3, 4, 5);
        Function<Integer, Integer> fun1 = value -> value;
        Map<Integer, ArrayList<Integer>> result = list.stream()
                .collect(Collectors.groupingBy(a -> a, LinkedHashMap::new, Collectors.toCollection(ArrayList::new)));
        System.out.println(result);
    }

    // collect - partitioningBy - Predicate, Collector
    static void questao17() {
        List<Integer> list = List.of(2, 3, 3, 4, 1, 3, 4, 5);
        Map<Boolean, List<Integer>> result = list.stream()
                .collect(Collectors.partitioningBy((a) -> a % 2 == 0, Collectors.mapping(a -> a, Collectors.toList())));
        p.accept(result);
    }

    // joining
    static void questao18() {
        List<String> list = List.of("Small", "Ismar");
        String result = list.stream().collect(Collectors.joining(", "));
        p.accept(result);
    }

}