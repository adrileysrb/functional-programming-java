import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Optional_ {
    public static void main(String args[]) throws IllegalAccessException, InvocationTargetException {
        // Obtém todas as methods da classe
        Method[] methods = Optional_.class.getDeclaredMethods();

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

    static Consumer<Object> println = System.out::println;

    // orElse, orElseGet, orElseThrow
    static void questao01() {
        Optional<String> value = Optional.empty();
        println.accept(value.orElse("Ismar"));
        println.accept(value.orElseGet(() -> "Ismar2"));
        // println.accept(value.orElseThrow(() -> new RuntimeException()));
        // println.accept(value.orElseThrow(RuntimeException::new));
    }

    // ifPresent
    // ifPresentOrElse
    static void questao02() {
        Optional<String> vazio = Optional.empty();
        Optional<String> presente = Optional.of("Olá");

        presente.ifPresent(println);

        vazio.ifPresentOrElse(println, new Runnable() {

            @Override
            public void run() {
                System.out.println("Ismar");
            }
        });

        vazio.ifPresentOrElse(println, () -> System.out.println("Ismar 2"));
    }

    // Create Optional
    static void questao03() {
        Optional<Integer> op1 = Optional.of(10);
        Optional<Integer> op2 = Optional.ofNullable(null);
        Optional<Integer> op3 = Optional.empty();
    }

    // map
    static void questao04() {
        Optional<String> op1 = Optional.of("Ismar");
        
        Optional<Integer> length = op1.map(String::length);
        System.out.println(length.orElse(0));
    }

    // filter
    static void questao05() {
        Optional<String> op1 = Optional.of("Ismar");
        Optional<String> op2 = op1.filter(a -> a.startsWith("Is"));
        System.out.println(op2.orElse("Não achou"));
    }

    // flatMap
    static void questao06() {
        Optional<String> op1 = Optional.of("Ismar");
        Optional<Optional<String>> op2 = op1.map(a -> findAnotherWorld(a));
        System.out.println("op2: " + op2.get().get());
        Optional<String> op3 = op1.flatMap(a -> findAnotherWorld(a));
        println.accept("op3: " + op3.get());
    }

    static Optional<String> findAnotherWorld(String value) {
        if("Ismar".equals(value)) {
            return Optional.of("Small");
        }
        return Optional.empty();
    }

}
