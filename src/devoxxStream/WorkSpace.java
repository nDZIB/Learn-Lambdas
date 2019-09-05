package devoxxStream;

import static org.junit.jupiter.api.DynamicTest.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorkSpace {

	public void doStuff() {
		List<String> alphabet = new ArrayList<String>();

		String[] listStarter = { "alpha", "beta", "zeta", "yota", "men", "very", "easily", "make", "Jokes", "serving",
				"useful", "purposes" };

		Stream<String> x = Stream.of(listStarter);

		x.map(String::toUpperCase).filter(word -> word.length() == 6).filter(word -> word.startsWith("E"))
				.forEach(System.out::println);
	}

	public void getWordsFromFile() {
		Path path = Paths.get("src/devoxxStream/there couldn't be a better day.txt");

		try {
			Stream<String> x = Files.lines(path);

			//List<String> words = x.flatMap(line -> Arrays.stream(line.split(" "))).collect(Collectors.toList());

//			Map<String, String> words = x.collect(Collectors.toMap(key -> key.substring(0,1), content -> content,
//					(previous, current)-> previous+System.lineSeparator()+current));
			
			
			//Map<String, List<String>> words = x.collect(Collectors.groupingBy(word -> word.substring(0,1)));
			
			
			Map<String, Set<String>> words = x.flatMap(line -> Arrays.stream(line.split(" "))).collect(Collectors
					.groupingBy(line-> line.substring(0,1), Collectors.mapping(word->word, Collectors.toSet())));
			
			System.out.println(words);
			x.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public IntUnaryOperator combine(List<IntUnaryOperator> operations) {
		return operations.stream().reduce(operation -> operation, IntUnaryOperator::andThen);
	}

	public void combieLambdas() {
		//creating list of operations
		IntUnaryOperator[] ops = {i->i+1, i->i*2, i->i+3};
		List<IntUnaryOperator> operations = new ArrayList<IntUnaryOperator>();

		for (IntUnaryOperator intUnaryOperator : ops) {
			operations.add(intUnaryOperator);
		}
		
		IntUnaryOperator combined = combine(operations);
		
		System.out.println(combined.applyAsInt(5));
	}
	
	public void leterfrequency() {
		String [] rsource = {"a", "a", "a","d","a","q"};
		
		Map<String, Long> freqT = Stream.of(rsource).collect(Collectors.groupingBy(c->c, Collectors.counting()));
		
		System.out.println(freqT);
	}

}
