package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class removeDuplicates {


	public void stripDuplicatesFromFile(String filename) throws IOException {
		
		    BufferedReader reader = new BufferedReader(new FileReader(filename));
		    int counter = 0;
		    Set<String> lines = new HashSet<String>(5000000);
		    String line;
		    while ((line = reader.readLine()) != null) {
		    	byte[] bytes = line.getBytes( StandardCharsets.UTF_8 );
		    	String v = new String( bytes, StandardCharsets.UTF_8 );
		        lines.add(v);
		        System.out.println(counter++);
		    }
		    reader.close();
		    
		    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		    for (String unique : lines) {
		    	unique.trim();
		    	if(unique.toString().length()>2) {
		    		//System.out.println(unique);
		    		if(unique.startsWith(" ")){
		    			unique = unique.toString().substring(1);
		    			System.out.println("boom");
		    			 writer.write(unique.toUpperCase());
					        writer.newLine();
		    		}
		    			
			        writer.write(unique.toUpperCase());
			        writer.newLine();
		    	}

		    }
		    writer.close();
		    System.out.println("DONE");
		}
	
}
