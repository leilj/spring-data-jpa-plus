package wang.uvu.jpa.base;

import lombok.Data;

@Data
public abstract class Query {

	private int page_ = 0;// 从0开始
	private int size_ = 20;
	private String orders_;
	private String fields_;
	private boolean or_ = false;
}
