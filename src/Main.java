import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main 
{
	private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static void main(String[] args)
	{
		//2^31 - 1 = FXSHRXX
		//2^63 - 1 = CRPXNLSKVLJDTX
		parseStringBI("A");
		parseStringBI("FXSHRXW");
		parseStringBI("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
	}

	public static void parseString(String input)
	{
		List<Character> charArray = new ArrayList<Character>();
		for(char ch : input.toCharArray())
		{
			charArray.add(ch);
		}
		Collections.reverse(charArray);
		long runningTotal = 0;
		for(int i = input.length() - 1; i >= 0; i--)
		{
			runningTotal += (Math.pow(26, i)) * (alphabet.indexOf(charArray.get(i)) + 1);
		}
		System.out.println(runningTotal);
	}
	
	public static void parseStringBI(String input)
	{
		List<Character> charArray = new ArrayList<Character>();
		for(char ch : input.toCharArray())
		{
			charArray.add(ch);
		}
		Collections.reverse(charArray);
		BigInteger runningTotal = BigInteger.ZERO;
		for(int i = input.length() - 1; i >= 0; i--)
		{
			runningTotal = runningTotal.add(new BigInteger("26").pow(i).multiply(new BigInteger((alphabet.indexOf(charArray.get(i)) + 1) + "")));
		}
		System.out.println(runningTotal);
	}
	
	public static void numberToCell(int number)
	{
		BigInteger guess = BigInteger.ZERO;
		
	}
}
