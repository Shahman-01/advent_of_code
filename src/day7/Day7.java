package day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day7 {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader("src/day7/test"));
	}

	private static void f() {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> map1 = new HashMap<>();
		map.put("hello", map1);
	}

}
