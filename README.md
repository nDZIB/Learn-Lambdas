# Learn-Lambdas (GG)
# 
# 
# 
#** Day one (Monday, 2 September 2019)
#* Introduction
-	In Java 8 upwards
-	Syntax: parameter -> functionBody
-	Characteristics

		1. Optional return type: the compiler automatically returns the value of the body if 
		the body has only one expression and is surrounded by curly braces.
		2. Optional curly braces if only one expression is found in the body, or no  return 
		value is needed.
		3. Optional type specification for parameters.
		4.  Optional optional parenthesis around parameters, except otherwise, more than one
		 parameter are specified.
	
-	Lambdas are implementations of interfaces (specifically single abstract method (SAM) interfaces otherwise known as functional interfaces)


#** Day two (Tuesday, 3 September 2019)

-	Consumer functional interface

	1.	using the accept(T t) abstract method and the andThen(Consumer<T> t) default method
-	Predicate functional interface

	1.	Used the test(T t) abstract method
	2. used the negate() default instance method
	3. used the and(Predicate<T> t) default method + java's "fail fast" i.e Objects.requireNotNull
	. Created own Predicate interface, implementing above methods plus an XOR
-	Comparator functional interface

	1.	compare (T t, T t) abstract method
	2.	comparing(keyExtractor) static method
	3. thenComparing(keyExtractor) default method
-	Implemented a length conversion api, (interconverting between kilometers(km), meters(m) and centimeters(cm)

#** Day three (Wednesday, 4 September 2019)

-	Streams
	
	1. maps and filters, flatMap (map takes one param and returns it, flatmap takes one and returns 0 or 	variable)
	2. toMap(), toList(), groupinpBy(), reduce(identity, operation)


#** Day four (Thursday, 5 September 2019)

-	Recipe api


#** Day five (Friday, 6 September 2019)

-	Started transforming the login service of the todo system (built within the lessons) to use lambdas






#WEEK TWO

#** Day one (Monday, 09 September 2019)

- More practice on lambda expressions

#** Day Two (Tuesday, 10 September 2019)

- Continued practice on lambda expressions