package cn.web.model;

import java.math.BigDecimal;
import java.util.Date;

// 数据库表对应Java的类
// 表的记录对应Java的对象
// 表的字段对应Java属性
// 表的字段属性值,对应Java属性的值
public class Product extends Object {

	private Integer id;
	private String name;
	private BigDecimal price;
	private String remark;
	private Date date;

	// alt + shift + s
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", remark=" + remark + ", date=" + date
				+ "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		System.out.println("getName.........");
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
