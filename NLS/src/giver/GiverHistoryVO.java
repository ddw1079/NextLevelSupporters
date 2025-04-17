package giver;

import java.sql.Date;

/* **
 * 파일 설명: 후원자 후원내역에서 사용할 Value Object
 * 파일 상세: 후원자의 후원내역에서 사용할 Value Object 기술
 *  - 후원자 DAO에서 데이터를 사용할 것
 *  - 아래 데이터는 History DB와 같음
 * 
 * */

public class GiverHistoryVO {
	private int id;
	private Date create_date;
	private int giver_id;
	private int receiver_id;
	private int amount;
	private String message;
	private boolean is_received;
	public GiverHistoryVO() {}
	public GiverHistoryVO (int id, Date create_date, int giver_id, int receiver_id, int amount, String message, boolean is_received) {
		super();
		this.setId(id);
		this.setCreate_date(create_date);
		this.setGiver_id(giver_id);
		this.setReceiver_id(receiver_id);
		this.setAmount(amount);
		this.setMessage(message);
		this.setIs_received(is_received);
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public int getGiver_id() {
		return giver_id;
	}
	public void setGiver_id(int giver_id) {
		this.giver_id = giver_id;
	}
	public int getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(int receiver_id) {
		this.receiver_id = receiver_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isIs_received() {
		return is_received;
	}
	public void setIs_received(boolean is_received) {
		this.is_received = is_received;
	}
}
