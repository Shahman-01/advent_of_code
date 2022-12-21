package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day5 {
	public static void main(String[] args) throws IOException {
		String path = "src/test.txt";
		String line = "";
		Map<Integer, List<Character>> map = new HashMap<>();

		createLists(map);

		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			firstCycle(reader, map, line);
			secondCycle(reader, map, line);
		}

		printMatrix(map);
	}

	private static void secondCycle(BufferedReader reader, Map<Integer, List<Character>> map, String line) throws IOException {
		int com1, com2, com3;

		while ((line = reader.readLine()) != null) {
			if (line.startsWith("move")) {
				line = line.replaceAll("[^-?0-9]+", " ");
				List<String> comsStr = Arrays.asList(line.trim().split(" "));
				int[] coms = new int[3];
				for (int i = 0; i < 3; i++) {
					coms[i] = Integer.parseInt(comsStr.get(i));
				}

//				takeFrom(map, coms);
				gitveTo(map, coms, takeFrom(map, coms));
			}
		}
	}

	private static void gitveTo(Map<Integer, List<Character>> map, int[] coms, List<Character> timelyList) {
		int endSize = map.get(coms[2] - 1).size() + timelyList.size();
		int listSize = map.get(coms[2] - 1).size();
		while (map.get(coms[2] - 1).size() != endSize) {
			map.get(coms[2] - 1).add(listSize, timelyList.get(timelyList.size() - 1));
			timelyList.remove(timelyList.size() - 1);
		}
	}

	private static List<Character> takeFrom(Map<Integer, List<Character>> map, int[] coms) {
		List<Character> timelyList = new ArrayList<>();
		int listSize = map.get(coms[1] - 1).size();

		for (int i = listSize - coms[0];
		     i < listSize; i++) {
			timelyList.add(map.get(coms[1] - 1).get(i));
		}
		int endSize = listSize - coms[0];
		while (map.get(coms[1] - 1).size() != endSize)
			map.get(coms[1] - 1).remove(endSize);
//		System.out.println(timelyList);
		return timelyList;
	}

	private static void firstCycle(BufferedReader reader, Map<Integer, List<Character>> map, String line) throws IOException {
		while ((line = reader.readLine()) != null) {
			if (line.contains("1"))
				break;
			initMap(line, map);
		}
	}

	private static void initMap(String line, Map<Integer, List<Character>> map) {
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) > 64 && line.charAt(i) < 91) {
				if (i == 1)
					map.get(0).add(0, line.charAt(i));
				else
					map.get(i / 4).add(0, line.charAt(i));
			}
		}
	}

	private static void createLists(Map<Integer, List<Character>> map) {
		for (int i = 0; i < 9; i++) {
			map.put(i, new ArrayList<>());
		}
	}

	private static void printMatrix(Map<Integer, List<Character>> map) {
		for (int i = 0; i < map.size(); i++) {
			System.out.println(i+1 + " = " + map.get(i));
		}
	}
}
