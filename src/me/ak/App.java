package me.ak;

import java.util.Arrays;

public class App {

	public static void main(String[] args) {
//		Trader raoul = new Trader("Raoul", "Cambridge");
//		Trader mario = new Trader("Mario", "Milan");
//		Trader alan = new Trader("Alan", "Cambridge");
//		Trader brian = new Trader("Brian", "Cambridge");
//
//		List<Transaction> transactions = Arrays.asList(new Transaction(brian,
//				2011, 300), new Transaction(raoul, 2012, 1000),
//				new Transaction(raoul, 2011, 400), new Transaction(mario, 2012,
//						710), new Transaction(mario, 2012, 700),
//				new Transaction(alan, 2012, 950));
//		
//		
//		List<Transaction> t = transactions.stream()
//				.filter(x -> x.getYear() == 2012)
//				.collect(Collectors.toList());
//		System.out.println(t);
//		
//		List<String> cities = transactions.stream()
//				.map(x -> x.getTrader().getCity())
//				.distinct()
//				.collect(Collectors.toList());
//		System.out.println(cities);
//		
//		List<Trader> tradersFromC = transactions.stream()
//				.map(x -> x.getTrader())
//				.filter(x -> x.getCity().equals("Cambridge"))
//				.sorted(Comparator.comparing(Trader::getName))
//				.distinct()
//				.collect(Collectors.toList());
//		System.out.println(tradersFromC);
		
		
		System.out.println(hungarize("AdnanKaracCar"));
	}
	
	private static String hungarize(String s) {
		return Arrays.asList(s.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])")).stream().reduce(
					"", (x, y) -> x + "_" + y 
				).toLowerCase();
	}

}
