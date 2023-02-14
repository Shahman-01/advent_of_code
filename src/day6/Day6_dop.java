package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day6_dop {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader("src/day6/test"));
		String msg = bufferedReader.readLine();
		System.out.println(f(msg));
	}


	public static String f(String msg) {
		return String.valueOf(charsBeforePacket(14, msg));
	}

	private static int charsBeforePacket(int size, String msg) {
		for (int i = 0, j = size; j < msg.length(); i++, j++) {
			if (allUnique(msg.substring(i, j)))
				return j;
		}
		return 0;
	}

	private static boolean allUnique(String substring) {
		return substring.chars().distinct().count() == substring.length();
	}

}