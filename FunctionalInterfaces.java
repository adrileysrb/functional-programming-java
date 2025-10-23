import java.util.function.*;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Fuction
//  - Function
//      - UnaryOperator
//  - BiFunction
//      - BinaryOperator
// Consumer
//  - Consumer
//  - BiConsumer
// Predicate
//  - Predicate
//  - BiPredicate
// Supplier
//  - Supplier

// andThen, compose
// and, or, negate

// apply, accept, get, test 
public class FunctionalInterfaces {

    public static void main(String args[]) throws IllegalAccessException, InvocationTargetException {
        // Obtém todas as methods da classe
        Method[] methods = FunctionalInterfaces.class.getDeclaredMethods();

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

    // Function
    // Transforma um valor em outro
    static void questao01() {
        Function<Integer, Long> fun1 = (Integer value1) -> 1L;
        UnaryOperator<Integer> fun2 = (Integer value1) -> 1;

        BiFunction<Integer, Short, String> fun3 = (Integer value1, Short value2) -> "a";
        BinaryOperator<Integer> fun4 = (Integer value1, Integer value2) -> 1;
    }

    // Predicate
    // Testa uma condição e retorna boolean
    static void questao02() {
        Predicate<Integer> fun1 = (Integer value) -> true;
        BiPredicate<Integer, Short> fun2 = (Integer value1, Short value2) -> true;
    }

    // Consumer
    // Recebe um valor como parametro e executa uma operação sobre
    static void questao03() {
        Consumer<Integer> fun1 = (Integer value1) -> System.out.println(value1);
        BiConsumer<Integer, Short> fun2 = (Integer value1, Short value2) -> System.out.println(value1 + " - " + value2);
    }

    // Supplier
    // Não recebe parametro e retorna um valor
    static void questao04() {
        Supplier<Integer> fun1 = () -> 1;
        System.out.println(fun1.get());
    }

    // Composite
    // Encadear funções
    static void questao05() {
        Function<Integer, Long> fun1 = (a) -> {
            System.out.println("fun1");
            return a - 2L;
        };
        Function<Long, Long> fun2 = (a) -> {
            System.out.println("fun2");
            return a - 1L;
        };

        Long result = fun1.andThen(fun2).apply(10);
        System.out.println(result);
    }

    // Composite
    // Encadear funções - a ultima executa primeiro
    static void questao06() {
        Function<Long, Long> fun1 = (a) -> {
            System.out.println("fun1");
            return a - 2L;
        };
        Function<Integer, Long> fun2 = (a) -> {
            System.out.println("fun2");
            return a - 1L;
        };

        Long result = fun1.compose(fun2).apply(10);
        System.out.println(result);
    }

    // Composite
    // Encadear funções - a ultima executa primeiro
    static void questao07() {
        BiFunction<Integer, Short, String> fun1 = (x, y) -> "a";
        Function<String, Long> fun2 = (a) -> {
            System.out.println("fun2");
            return 1L;
        };
        Long result = fun1.andThen(fun2).apply(1, (short) 1);
        System.out.println(result);
    }

    // Composite in Consumer
    static void questao08() {
        Consumer<String> fun1 = (a) -> {
            System.out.print("fun1: ");
            System.out.println(a.toLowerCase());
        };
        Consumer<String> fun2 = (a) -> {
            System.out.print("fun2: ");
            System.out.println(a.toUpperCase());
        };

        // fun1.accept("Ismar");
        // fun2.accept("Ismar");

        fun1.andThen(fun2).accept("Ismar");
    }

    // Composite in Predicate
    static void questao09() {
        Predicate<Integer> fun1 = (value) -> value > 2;
        Predicate<Integer> fun2 = (value) -> value > 1;

        boolean result1 = fun1.and(fun2).test(3);
        System.out.println(result1);
        
        boolean result2 = fun1.or(fun2).test(2);
        System.out.println(result2);
        
        boolean result3 = fun1.negate().test(1);
        System.out.println(result3);
    }

}