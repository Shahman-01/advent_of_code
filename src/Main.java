import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		String path = "src/test.txt";
		String line = "";
		List<List<Character>> listOfLists = new ArrayList<>();

		for (int i = 0; i < 9; i++) {
			listOfLists.add(new ArrayList<>());
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			while ((line = reader.readLine()) != null) {
				int position = 1;

				if (line.contains("1"))
					break;
				if (line.length() == 27)
					line = line + "        ";
				for (int i = 0; i < 9; i++) {
					listOfLists.get(i).add(line.charAt(position));
					position += 4;
				}
			}

			while ((line = reader.readLine()) != null) {
				if (line.startsWith("move")) {
					String arr = line.replaceAll("[^\\d.]", "");
					int com1, com2, com3;
					int pos = 0;

					if (arr.length() == 4) {
						com1 = Integer.parseInt(arr.substring(0, 2));
						arr = arr.substring(1);
					} else
						com1 = Integer.parseInt(arr.charAt(0) + "");
					com2 = Integer.parseInt(arr.charAt(1) + "");
					com3 = Integer.parseInt(arr.charAt(2) + "");


					int listSize = listOfLists.get(com2 - 1).size();
					if (listSize < com1)
						com1 = listSize;
					int count = com1;
					List<Character> timeList = new ArrayList<>();

					for (int i = listSize - com1; count > 0; i++,count--) {
						timeList.add(listOfLists.get(com2 - 1).get(i));
					}


					for (int i = com1 - 1; i >= 0; i--) {
						char nonNullEl = listOfLists.get(com3 - 1).stream().filter(m -> m != ' ').findAny().get();
						int index = listOfLists.get(com3 - 1).indexOf(nonNullEl);
						listOfLists.get(com3 - 1).add(index, timeList.get(i));
					}
				}
			}

			for (int i = 0; i < 9; i++) {
				System.out.println(listOfLists.get(i));
			}
		}
	}
}