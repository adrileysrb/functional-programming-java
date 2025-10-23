import java.util.function.Function;
import java.util.function.Predicate;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

// couting
// summing
// averaging
// summarizing
// max
// min
public class CollectorsExample {

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

    // Crie uma lista de números, colete somente os números pares e imprima. Utilize
    // predicado e streams.
    static void questao01() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        Predicate<Integer> isPar = (value) -> value % 2 == 0;
        List<Integer> result = list.stream().filter(isPar).collect(Collectors.toList());

        result.forEach(System.out::println);
    }

    // Crie uma lista de numeros e conte quantas vezes cada numero aparece na lista
    static void questao02() {
        List<Integer> list = Arrays.asList(1, 1, 2, 3, 4, 3, 3, 3, 5, 6);

        Map<Integer, Long> result = list.stream().collect(Collectors.groupingBy(valor -> valor, Collectors.counting()));
        result.entrySet().forEach(System.out::println);
    }

    // Somar todos os números de uma lista de números
    static void questao03() {
        List<Integer> list = Arrays.asList(1, 1, 2);
        Integer result = list.stream().collect(Collectors.summingInt(value -> value));
        System.out.println(result);
    }

    // somar todos os números que se repetem
    static void questao04() {
        List<Integer> list = Arrays.asList(1, 1, 2, 3, 4, 4);

        Map<Integer, Integer> result = list.stream()
                .collect(Collectors.groupingBy(value -> value, Collectors.summingInt(value -> value)));

        System.out.println(result);
    }

    // media dos numeros de uma lista
    static void questao05() {
        List<Integer> list = Arrays.asList(2, 2, 8, 4, 4);
        Double resultado = list.stream().collect(Collectors.averagingInt(value -> value));
        System.out.println(resultado);
    }

    // Contar quantas ocorrências do numero 1
    static void questao06() {
        List<Integer> list = Arrays.asList(1, 1, 2, 3, 4, 3, 3, 3, 5, 6);

        IntSummaryStatistics result = list.stream().collect(Collectors.summarizingInt(x -> x));

        System.out.println(result);
    }

    // Menor número de uma lista
    static void questao07() {
        List<Integer> list = Arrays.asList(1, 1, 2, 3, 4, 3, 17, 3, 3, 5, 6);

        Optional<Integer> result = list.stream().collect(Collectors.minBy(Comparator.comparing(value -> value)));

        System.out.println(result.get());
    }

    // Maior numero de uma lista
    static void questao08() {
        List<Integer> list = Arrays.asList(1, 1, 2, 3, 4, 3, 17, 3, 3, 5, 6);
        Comparator<Integer> comparator = (value1, value2) -> value1.compareTo(value2);
        Optional<Integer> result = list.stream().collect(Collectors.maxBy(comparator));

        System.out.println(result.get());
    }

    // Juntar todas as palavras de uma lista
    static void questao09() {
        List<String> list = Arrays.asList("Ismar", "Ismau", "Ismel", "Ismael");

        String result = list.stream().collect(Collectors.joining());
        System.out.println(result);
    }

    // Juntar todas as palavras de uma lista e colocar uma virgula entre elas
    static void questao10() {
        List<String> list = Arrays.asList("Ismar", "Ismau", "Ismel", "Ismael");

        String result = list.stream().collect(Collectors.joining(", "));
        System.out.println(result);
    }

    // Juntar todas as palavras de uma lista e colocar virgula, prefixo e sufixo.
    static void questao11() {
        List<String> list = Arrays.asList("Ismar", "Ismau", "Ismel", "Ismael");

        String result = list.stream().collect(Collectors.joining(", ", "[", "]"));
        System.out.println(result);
    }

    static void questao12(){
        List<Integer> list = Arrays.asList(1, 1, 2, 3, 4, 3, 17, 3, 3, 5, 6);
        list.stream().collect(Collectors.reducing(Integer::sum));
    }

}