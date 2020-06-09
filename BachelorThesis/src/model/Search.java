package model;

import java.util.HashMap;

public class Search 
{
	public static HashMap<String, String> ruleTable = new HashMap<String, String>();
	
	public static String replace(String searchString)
	{
		searchString = searchString.toLowerCase();
		ruleTable.put("xç", "(|xç|ss|s|xc|sç|sc)");
		ruleTable.put("sç", "(|sç|ss|s|xc|xç|sc)");
		ruleTable.put("xc", "(|xc|x|sc)");
		ruleTable.put("ss", "(|ss|s|sc|z|x|c|sç)");
		ruleTable.put("x", "(x|s|z|ch|cs|xc)");
		ruleTable.put("c", "(c|sç|ss|s|k|q|qu)");
		ruleTable.put("ç", "(ç|ss|s|sç)");
		ruleTable.put("s", "(s|ss|z|x|sc|sç|ç)");
		ruleTable.put("z", "(z|s|x)");
		ruleTable.put("i", "(i|y|u)");
		ruleTable.put("y", "(y|i|u)");
		ruleTable.put("u", "(u|i|y)");
		ruleTable.put("q", "(q|k|qu|c)");
		ruleTable.put("k", "(q|k|qu|c)");
		ruleTable.put("qu", "(|qu|k|c|q)");
		ruleTable.put("le", "(|le|lh|li)");
		ruleTable.put("li", "(|li|le|lh)");
		ruleTable.put("lh", "(|lh|li|le)");
		ruleTable.put("r", "(r|rr)");
		ruleTable.put("rr", "(|r|rr)");
		ruleTable.put("o", "(ou|o)");
		ruleTable.put("ou", "(|o|ou)");
		ruleTable.put("ei", "(|e|ei)");
		ruleTable.put("e", "(e|ei)");
		ruleTable.put("v", "(v|w|u)");
		ruleTable.put("w", "(u|v|w)");
		ruleTable.put("u", "(u|w|v)");
		ruleTable.put("nh", "(|nh|n|m)");
		ruleTable.put("m", "(n|m|nh)");
		ruleTable.put("n", "(m|nh|n)");
		ruleTable.put("g", "(g|j)");
		ruleTable.put("j", "(g|j)");
		ruleTable.put("ph", "(|f|ph)");
		ruleTable.put("f", "(f|ph)");
		ruleTable.put("h", "(h|)");
		
		StringBuilder firstSearch = new StringBuilder();
		for (int i = 0; i < searchString.length()-1; i++)
		{				
			if (i != searchString.length()-1 && ruleTable.containsKey(searchString.substring(i, i+2)))
			{
				System.out.println("1. Found " + searchString.substring(i, i+2) + ", adding" + ruleTable.get(searchString.substring(i, i+2)));
				firstSearch.append(ruleTable.get(searchString.substring(i, i+2)));
			}
			else
			{
				firstSearch.append(searchString.charAt(i));
			}
		}
		firstSearch.append(searchString.charAt(searchString.length()-1));
		StringBuilder finalSearch = new StringBuilder();
		System.out.println(firstSearch);
		boolean parenthesis = false;
		for (int i = 0; i < firstSearch.length(); i++)
		{
			if (firstSearch.charAt(i) == '(')
			{
				parenthesis = true;
			}
			if (firstSearch.charAt(i) == ')')
			{
				parenthesis = false;
			}
			if (parenthesis)
			{
				finalSearch.append(firstSearch.charAt(i));
			}
			if (!parenthesis)
			{
				if (ruleTable.containsKey(""+firstSearch.charAt(i)))
				{
					System.out.println("2. Found " + firstSearch.charAt(i) + ", adding" + ruleTable.get(""+firstSearch.charAt(i)));
					finalSearch.append(ruleTable.get(""+firstSearch.charAt(i)));
				}
				else
				{
					finalSearch.append(firstSearch.charAt(i));
				}			
			}		
		}			
		boolean automimic =  searchString.matches(finalSearch.toString());
		System.out.println("selfmatch: " + automimic + " should never be false");
		if (!automimic) System.err.println("DIDNT MATCH ITSELF WOOOP <-------------------------------------------------------------- fuk u");
		String finalS = finalSearch.toString().replaceAll("\\s+", "|");
		System.out.println(finalS);
		return finalS;
	}
	

	public static String special(String searchString) {
		searchString = searchString.toLowerCase();
		StringBuilder search = new StringBuilder();
		for (int i = 0; i < searchString.length()-1; i++) {
			search.append(searchString.charAt(i)).append("([a-zA-Z]*?)");
		}
		search.append(searchString.charAt(searchString.length()-1));
		return search.toString();
	}
	
	public static String choppedToThrees(String searchString) {
		searchString = searchString.toLowerCase();
		
		int interval = 4; // 3 for groups of three
		// yugioh
		// yugi
		//  ugio
		//   gioh
		
		if (searchString.length() <= interval) return searchString;
		
		StringBuilder search = new StringBuilder();
		for (int i = 0; i < searchString.length() - interval + 1; i++) {
			search.append(searchString.substring(i, i+interval)).append("|");
		}
		return search.toString().substring(0, search.length()-1);
	}
	
	
	public static String replace2(String searchString)
	{
		searchString = searchString.toLowerCase();
		ruleTable.put("xç", "xç|ss|s|xc|sç|sc");
		ruleTable.put("sç", "sç|ss|s|xc|xç|sc");
		ruleTable.put("xc", "xc|x|sc");
		ruleTable.put("ss", "ss|s|sc|z|x|c|sç");
		ruleTable.put("x", "x|s|z|ch|cs|xc");
		ruleTable.put("c", "c|sç|ss|s|k|q|qu");
		ruleTable.put("ç", "ç|ss|s|sç");
		ruleTable.put("s", "s|ss|z|x|sc|sç|ç");
		ruleTable.put("z", "z|s|x");
		ruleTable.put("i", "i|y|u");
		ruleTable.put("y", "y|i|u");
		ruleTable.put("u", "u|i|y");
		ruleTable.put("q", "q|k|qu|c");
		ruleTable.put("k", "q|k|qu|c");
		ruleTable.put("qu", "qu|k|c|q");
		ruleTable.put("le", "le|lh|li");
		ruleTable.put("li", "li|le|l");
		ruleTable.put("lh", "lh|li|le");
		ruleTable.put("r", "r|rr");
		ruleTable.put("rr", "r|rr");
		ruleTable.put("o", "ou|o");
		ruleTable.put("ou", "o|ou");
		ruleTable.put("ei", "e|ei");
		ruleTable.put("e", "e|ei");
		ruleTable.put("v", "v|w|u");
		ruleTable.put("w", "u|v|w");
		ruleTable.put("u", "u|w|v");
		ruleTable.put("nh", "nh|n|m");
		ruleTable.put("m", "n|m|nh");
		ruleTable.put("n", "m|nh|n");
		ruleTable.put("g", "g|j");
		ruleTable.put("j", "g|j");
		ruleTable.put("ph", "f|ph");
		ruleTable.put("f", "f|ph");
		ruleTable.put("h", "h|");
		
		StringBuilder firstSearch = new StringBuilder();
		boolean lastDouble = false;
		for (int i = 0; i < searchString.length(); i++)
		{				
			if (i < searchString.length()-1 && ruleTable.containsKey(searchString.substring(i, i+2)))
			{
				System.out.println("Found " + searchString.substring(i, i+2) + ", matching " + ruleTable.get(searchString.substring(i, i+2)));
				firstSearch.append("(").append(ruleTable.get(searchString.substring(i, i+2))).append(")");
				lastDouble = true;
			}
			else if (ruleTable.containsKey(searchString.substring(i, i+1)))
			{
				System.out.print("Found " + searchString.substring(i, i+1) + ", matching " + ruleTable.get(searchString.substring(i, i+1)));
				firstSearch.append("(").append(ruleTable.get(searchString.substring(i, i+1)));
				if (lastDouble) {
					System.out.print(" -- last was double so we're compensating with a OR-NADA");
					firstSearch.append("|");
				}
				firstSearch.append(")");
				System.out.println();
				lastDouble = false;
			} else {
				lastDouble = false;
				firstSearch.append(searchString.charAt(i));
				System.out.println("      " + searchString.charAt(i));
			}
		}
		boolean automimic =  searchString.matches(firstSearch.toString());
		System.out.println("selfmatch: " + automimic + " should never be false");
		if (!automimic) System.err.println("DIDNT MATCH ITSELF WOOOP <-------------------------------------------------------------- fuk u");
		String finalS = firstSearch.toString().replaceAll("\\s+", "|");
		System.out.println(finalS);
		return finalS;
	}
}
