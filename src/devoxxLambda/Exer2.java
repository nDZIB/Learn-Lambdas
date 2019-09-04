package devoxxLambda;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

class Exer2 {

	@Test
	void test() {Consumer<List<String>> c1 = list -> list.add("first");
    Consumer<List<String>> c2 = list -> list.add("second");

    Consumer<List<String>> consumer = c1.andThen(c2);

    List<String> list =
            new ArrayList<>(Arrays.asList("a", "b", "c"));

    consumer.accept(list);
	}

}
