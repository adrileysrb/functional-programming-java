import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FunctionalInterfacesAdvanced {
    public static void main(String args[]) throws IllegalAccessException, InvocationTargetException {
        // Obtém todas as methods da classe
        Method[] methods = FunctionalInterfacesAdvanced.class.getDeclaredMethods();

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

    static void questao01() {
        TriFunction<Integer, Integer, Integer, Integer> fun1 = (a, b, c) -> 1;
        TriFunction.teste();
 
        Optional<Integer> val = Optional.ofNullable(null);
        val.isPresent();
        val.isEmpty();
    }

    interface TriFunction<T, K, V, Z> {
        Z apply(T t, K k, V v);

        default void teste1() {
        }

        static void teste() {
        }
    }
}
