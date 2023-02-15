package day7;

import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day7 {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader("src/day7/test"));
		List<String> list = new ArrayList<>();
		while (bufferedReader.ready()) {
			list.add(bufferedReader.readLine());
		}
		AtomicInteger index = new AtomicInteger(0);
		String[] arr = new String[list.size()];
		for (int i = 0; i < list.size(); i++)
			arr[i] = list.get(i);
		System.out.println(sumSize(findChildren(arr, index)));
	}

	public record Node(Map<String, Node> children, long size) {
	}

	public record File(long size, String name) {
	}

	public static Node findChildren(String[] commands, AtomicInteger index) {
		Map<String, Node> children = new HashMap<>();
		for (; index.get() < commands.length; index.incrementAndGet()) {
			String c = commands[index.get()];
			if (c.charAt(0) == '$') {
				String command = c.substring(2);
				if (command.startsWith("cd")) {
					String folder = command.substring(3);
					if (folder.equals("..")) break;
					index.incrementAndGet();
					children.put(folder, findChildren(commands, index));
				}
			} else {
				if (c.startsWith("dir")) {
					String dirName = c.substring(4);
					children.put(dirName, new Node(new HashMap<>(), 0));
				} else {
					int size = Integer.parseInt(c.split(" ")[0]);
					String name = c.split(" ")[1];
					children.put(name, new Node(new HashMap<>(), size));
				}
			}
		}
		return new Node(children, children.values().stream().mapToLong(Node::size).sum());
	}

	private static long sumSize(Node n) {
		long total = 0;
		for (Node node : n.children.values()) {
			if (node.size <= 100000 && !node.children.isEmpty()) {
				total += node.size;
			}
			total += sumSize(node);
		}
		return total;
	}

}
