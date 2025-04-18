package vo;

/* **
 * 파일 설명: 후원자 후원 가능 목록에 사용할 Value Object
 * 파일 상세: 후원자 후원 가능 목록에 사용할 Value Object 기술
 *  - GiverMainClassDAO 에서 사용할 데이터
 *  - 후원 상세 페이지로 데이터를 넘겨주기 위해서 수혜자 정보를 전부 가져올 것
 *  
 * 
 * */

public class GiverMainClassVO {
	private int idx;
	private int receiver_id;
	private String receiver_name;
	private String receiver_reason;
	private String receiver_phone;
	public GiverMainClassVO(int idx, int receiver_id, String receiver_name, String receiver_reason,
			String receiver_phone) {
		this.setIdx(idx);
		this.setReceiver_id(receiver_id);
		this.setReceiver_name(receiver_name);
		this.setReceiver_reason(receiver_reason);
		this.setReceiver_phone(receiver_phone);
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(int receiver_id) {
		this.receiver_id = receiver_id;
	}
	public String getReceiver_phone() {
		return receiver_phone;
	}
	public void setReceiver_phone(String receiver_phone) {
		this.receiver_phone = receiver_phone;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public String getReceiver_reason() {
		return receiver_reason;
	}
	public void setReceiver_reason(String receiver_reason) {
		this.receiver_reason = receiver_reason;
	}
}
