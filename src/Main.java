import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
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
		//System.out.println(numberToCell(new BigInteger("839")));
		//System.out.println(parseStringBI("CRPXNLSKVLJDTX"));
		//System.out.println(parseStringBI("IWPJNFTCRUPGWTNYQJTMKGOXMAJVMDTYLCNYPWNRFLQFEIAETRQEMZGBBUMEUZAALDUEKDXXQRVKHKARVTQDHWWBSQLEVMSWMNVIBUGWPLAIHKTWTSNNNKOFRBILUOLQMCQAZKVZOJSIHZTJDXGQJPPNNZPSQADKGDYAINIUEJYRWFMTJYPMXNQALITDWETDQSVOSYYUNEKURDWFIMAQQWJEAYVZNCQTKMTOVVDCASEEUBMQMLMPVHTKWNDLNBDPDWZUSSRXEYYCWWYQJXRTWRNGPZKBLKXUBMMBBZXYZIKOLKBQZWPJQLOZUVEUGWTXUCUPZUWZTSQNOBTGGBMTUNLMEIQSEXKTCZDRTMHEJRVMESMAVPFBAKRMVZDFVUTYDWANNCJSJMIJGSMZTJZFNGBTUCAXJUJSOOAYYMMCNGCMESKSYQCV"));
		//BigInteger largePrime = new BigInteger(largePrime);
		try
		{
			FileReader fr = new FileReader("C:\\Users\\rifejt000\\Desktop\\Large Prime.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			PrintWriter pw = new PrintWriter("C:\\Users\\rifejt000\\Desktop\\Compressed Prime.txt");
			while((line = br.readLine()) != null)
			{
				String output = numberToCell(new BigInteger(line));
				pw.println(output);
			}
			pw.close();
			br.close();
		}catch(Exception e){}
		//String output = numberToCell(new BigInteger("2").pow(2048));
		
		//	parseStringBI("A");
		//parseStringBI("FXSHRXW");
	}
/**
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
*/
	public static BigInteger parseStringBI(String input)
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
		return runningTotal;
	}

	public static String numberToCell(BigInteger number)
	{
		BigInteger upperBound = BigInteger.ZERO;
		int power = 0;
		while(upperBound.compareTo(number) == -1)
		{
			upperBound = upperBound.add(new BigInteger("26").pow(power));
			power++;
			System.out.println(upperBound + ", " + power);
			if(upperBound.compareTo(number) == 0)
			{
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < power; i++)
				{
					sb.append("A");
				}
				return sb.toString();
			}
		}
		//we know the power, let's start converging.
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < (power - 1); i++)
		{
			sb.append("Z");
		}
		System.out.println(sb.toString());
		return recursivelyConverge(number, sb.toString().toCharArray(), power - 1, 0);
	}
	
	public static String recursivelyConverge(BigInteger target, char[] resolved, int power, int lastIndex)
	{
		System.out.println(lastIndex);
		if(lastIndex > resolved.length - 1)
		{
			return new String(resolved);
		}
		BigInteger overallResolvedValue = parseStringBI(new String(resolved));
		while(overallResolvedValue.compareTo(target) == 1)
		{
			//System.out.println(new String(resolved));
			int charIndex = alphabet.indexOf(resolved[lastIndex]);
			if(charIndex - 1 >= 0)
			{
				char[] lastResolved = resolved.clone();
				resolved[lastIndex] = alphabet.charAt(charIndex - 1);
				overallResolvedValue = parseStringBI(new String(resolved));
				if(overallResolvedValue.compareTo(target) == -1)
				{
					return recursivelyConverge(target, lastResolved, power, lastIndex + 1);
				}
			}
			else
			{
				resolved[lastIndex] = 'A';
				return recursivelyConverge(target, resolved, power, lastIndex + 1);
			}
			overallResolvedValue = parseStringBI(new String(resolved));
		}
		return recursivelyConverge(target, resolved, power, lastIndex + 1);
	}
}