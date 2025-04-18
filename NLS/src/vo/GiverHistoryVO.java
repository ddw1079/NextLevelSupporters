package vo;

import java.sql.Date;

/* **
 * 파일 설명: 후원자 후원내역에서 사용할 Value Object
 * 파일 상세: 후원자의 후원내역에서 사용할 Value Object 기술
 *  - GiverHistoryDAO 에서 사용할 데이터
 *  - History 테이블 전체 내용과 User 테이블의 이름을 가져옴
 *  
 *  
 * */

public class GiverHistoryVO {
	private int idx;
	private Date create_date;
	private int giver_id;
	private String giver_name;
	private int receiver_id;
	private String receiver_name;
	private int amount;
	private String message;
	private boolean is_received;
	public GiverHistoryVO() {}
	public GiverHistoryVO (int idx, Date create_date, int giver_id, String giver_name, int receiver_id, String receiver_name, int amount, String message, boolean is_received) {
		super();
		this.setIdx(idx);
		this.setCreate_date(create_date);
		this.setGiver_id(giver_id);
		this.setGiver_name(giver_name);
		this.setReceiver_id(receiver_id);
		this.setReceiver_name(receiver_name);
		this.setAmount(amount);
		this.setMessage(message);
		this.setIs_received(is_received);
	}
	
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
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
	public String getGiver_name() {
		return giver_name;
	}
	public void setGiver_name(String giver_name) {
		this.giver_name = giver_name;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
}
