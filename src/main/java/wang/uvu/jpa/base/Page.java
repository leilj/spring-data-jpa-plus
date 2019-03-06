package wang.uvu.jpa.base;

import java.util.List;
import lombok.Data;

@Data
public class Page<T> {

	private int number;

	// 总页数
	private int totalPages;

	private long total;

	// 总数
	private int totalElements;

	// 每页显示条数
	private int size = 10;

	// 原集合
	private List<T> content;

	private boolean last;

	private boolean first;

	public Page() {
		super();
	}

	public Page(long total, List<T> content) {
		super();
		this.total = total;
		this.content = content;
	}

	public Page(int number, int size, int totalElements, int totalPages,
				List<T> content) {
		super();
		this.number = number;
		this.size = size;
		this.content = content;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
	}
	
	public Page(int number, int size, int totalElements, List<T> content) {
		super();
		this.number = number;
		this.size = size;
		this.content = content;
		this.totalElements = totalElements;
		this.totalPages = (totalElements + size -1) / size;
	}
}
