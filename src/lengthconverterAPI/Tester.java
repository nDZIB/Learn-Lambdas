package lengthconverterAPI;

public class Tester {

	public static void main(String [] args) {
		System.out.println("=========== The key is this ===========");
		System.out.println("from: 'M', 'KM', 'CM' to 'M', 'KM', 'CM'");
		
		System.out.println(Conversion.convert(400).from("KM").to("km")); 
	}
}
