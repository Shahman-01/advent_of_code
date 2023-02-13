package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day6 {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader("src/day6/test"));
		System.out.println(f(bufferedReader));
	}

	public static int f(BufferedReader reader) throws IOException {
		char[] arr = reader.readLine().toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (i < arr.length - 5 && check(arr[i], arr[i + 1], arr[i + 2], arr[i + 3]))
				return i + 4;
		}
		return 0;
	}

	public static boolean check(char x1, char x2, char x3, char x4) {
		if (x1 == x2 || x1 == x3 || x1 == x4)
			return false;
		if (x2 == x3 || x2 == x4)
			return false;
		if (x3 == x4)
			return false;
		return true;
	}
}
