package lengthconverterAPI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

@FunctionalInterface
public interface Conversion {

	double to(String toUnit);
	
	static BiFunction convert(double value) {
		return (fromUnit) -> {
			return toUnit ->  {
				
				Path toTable = Paths.get("coeficients.txt");
				try {
					List<String> lines = Files.readAllLines(toTable);
							
					HashMap<String, HashMap> table = new HashMap<String, HashMap>();
					for(int i=1; i<lines.size(); i++) {
						String row = lines.get(i).trim().toLowerCase();
						String [] fields = row.split("\t");
						
						HashMap<String, Double> rows = new HashMap<String, Double>();
						
						rows.put("cm", Double.parseDouble(fields[1]));
						rows.put("m", Double.parseDouble(fields[2]));
						rows.put("km", Double.parseDouble(fields[3]));
						
						table.putIfAbsent(fields[0], rows);
					}
					
					
					double coeficient =(Double)table.get(fromUnit.toLowerCase()).get(toUnit.toLowerCase());
					return value*coeficient;
				} catch (IOException e) {
					e.printStackTrace();
					return 0;
				}
			};
		};
	}
}
