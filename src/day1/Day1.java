package day1;

import java.awt.color.ICC_ColorSpace;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Day1 {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader("src/day6/test"));
		System.out.println(f(bufferedReader));
	}

	public static int f(BufferedReader reader) throws IOException {
		String str = "";
		int res = 0;
		int max = 0;

		while ((str = reader.readLine()) != null) {
			if (str.equals("")) {
				res = 0;
			} else {
				res += Integer.parseInt(str);
				if (res > max)
					max = res;
			}
		}

		return max;
	}

}
