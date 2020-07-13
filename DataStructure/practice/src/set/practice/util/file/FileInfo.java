package set.practice.util.file;

public class FileInfo {
	/**
	 * 一共读取多少行
	 */
	private int lines;
	/**
	 * 一共读取多少文件
	 */
	private int files;
	private String content = "";

	/**
	 * 返回所有单词
	 */
	public String[] words() {
		return content.split("[^a-zA-Z]+");
	}
	
	public int getFiles() {
		return files;
	}

	public void setFiles(int files) {
		this.files = files;
	}

	public int getLines() {
		return lines;
	}
	
	public void setLines(int lines) {
		this.lines = lines;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public FileInfo append(FileInfo info) {
		if (info != null && info.lines > 0) {
			this.files += info.files;
			this.lines += info.lines;
			this.content = new StringBuilder(this.content)
					.append("\n")
					.append(info.content)
					.toString();
		}
		return this;
	}
}
