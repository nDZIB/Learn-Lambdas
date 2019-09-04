package lengthconverterAPI;

import java.util.function.Function;

public interface BiFunction {
	Conversion from(String fromUnit);

//	default Conversion to(double x) {
//		return (from -> x);
//	}

//	default Conversion from(double value) {
//		return (toUnit) -> value*toUnit;
//	}
}
