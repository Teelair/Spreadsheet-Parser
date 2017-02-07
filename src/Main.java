import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main 
{
	private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static void main(String[] args)
	{
		parseString("FXSHRXW");
	}

	public static void parseString(String input)
	{
		List<Character> charArray = new ArrayList<Character>();
		for(char ch : input.toCharArray())
		{
			charArray.add(ch);
		}
		Collections.reverse(charArray);
		int runningTotal = 0;
		for(int i = input.length() - 1; i >= 0; i--)
		{
			runningTotal += (Math.pow(26, i)) * (alphabet.indexOf(charArray.get(i)) + 1);
		}
		System.out.println(runningTotal);
	}
}
