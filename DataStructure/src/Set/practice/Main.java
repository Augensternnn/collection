package Set.practice;

import Set.Set;
import Set.ListSet;
import Set.TreeSet;
import Set.practice.file.FileInfo;
import Set.practice.file.Files;

/**
 * 统计文件中共有多少不同的单词
 */
public class Main {
	public static void main(String[] args) {
		FileInfo fileInfo = Files.read("C:\\Users\\Administrator\\Desktop", new String[]{"java"});
		System.out.println("文件数量：" + fileInfo.getFiles());
		System.out.println("代码行数：" + fileInfo.getLines());
		String[] words = fileInfo.words();
		System.out.println("单词数量(有重复)：" + words.length);

		Set<String> listSet = new ListSet<String>();
		for (int i = 0; i < words.length; i++) {
			listSet.add(words[i]);
		}
		System.out.println("单词数量(无重复)：" + listSet.size());
	}
}
