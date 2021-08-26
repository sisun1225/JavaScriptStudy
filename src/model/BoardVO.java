package model;

import java.sql.Date;

public class BoardVO {

	private long boarde_seq;
	private String board_title;
	private String board_contents;
	private int board_writer;
	private Date board_date;
	private int board_viewcount;
	private String board_password;
	private String board_image;

	
	
	
	
	public BoardVO() {      //아규먼트 없는 생성자??????
		
		super();
	}

// 아무것도 없는거 위에것으로 하나만들고
	//다른곳에 넣을때 아무것도 없는 게 필요할수도있고 값을 다 넣을 순 없으니 한꺼번에 넣는 의미로 생성..
//아래 전부 있는거 다만들기



	public BoardVO(long boarde_seq, String board_title, String board_contents, int board_writer, Date board_date,
			int board_viewcount, String board_password, String board_image) {
		super();
		this.boarde_seq = boarde_seq;
		this.board_title = board_title;
		this.board_contents = board_contents;
		this.board_writer = board_writer;
		this.board_date = board_date;
		this.board_viewcount = board_viewcount;
		this.board_password = board_password;
		this.board_image = board_image;
	}





	public long getBoarde_seq() {
		return boarde_seq;
	}





	public void setBoarde_seq(long boarde_seq) {
		this.boarde_seq = boarde_seq;
	}





	public String getBoard_title() {
		return board_title;
	}





	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}





	public String getBoard_contents() {
		return board_contents;
	}





	public void setBoard_contents(String board_contents) {
		this.board_contents = board_contents;
	}





	public int getBoard_writer() {
		return board_writer;
	}





	public void setBoard_writer(int board_writer) {
		this.board_writer = board_writer;
	}





	public Date getBoard_date() {
		return board_date;
	}





	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}





	public int getBoard_viewcount() {
		return board_viewcount;
	}





	public void setBoard_viewcount(int board_viewcount) {
		this.board_viewcount = board_viewcount;
	}





	public String getBoard_password() {
		return board_password;
	}





	public void setBoard_password(String board_password) {
		this.board_password = board_password;
	}





	public String getBoard_image() {
		return board_image;
	}





	public void setBoard_image(String board_image) {
		this.board_image = board_image;
	}





	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardVO [boarde_seq=").append(boarde_seq).append(", board_title=").append(board_title)
				.append(", board_contents=").append(board_contents).append(", board_writer=").append(board_writer)
				.append(", board_date=").append(board_date).append(", board_viewcount=").append(board_viewcount)
				.append(", board_password=").append(board_password).append(", board_image=").append(board_image)
				.append("]");
		return builder.toString();
	}

	
	
	
	
	
	
	
	
	
	
	
	

	}










/*

	private int board_seq;
	private String board_title;
	private String board_contents;
	private int board_writer ;
	private Date board_date;
	private int board_viewcount ;
	private String board_password;
	private String board_image;
	
	
	public BoardVO(){
		super();
	}
	
	public BoardVO(int board_seq, String board_title, String board_contents, int board_writer, Date board_date,
			int board_viewcount, String board_password, String board_image) {
		super();
		this.board_seq = board_seq;
		this.board_title = board_title;
		this.board_contents = board_contents;
		this.board_writer = board_writer;
		this.board_date = board_date;
		this.board_viewcount = board_viewcount;
		this.board_password = board_password;
		this.board_image = board_image;
	}



	public int getBoard_seq() {
		return board_seq;
	}



	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}



	public String getBoard_title() {
		return board_title;
	}



	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}



	public String getBoard_contents() {
		return board_contents;
	}



	public void setBoard_contents(String board_contents) {
		this.board_contents = board_contents;
	}



	public int getBoard_writer() {
		return board_writer;
	}



	public void setBoard_writer(int board_writer) {
		this.board_writer = board_writer;
	}



	public Date getBoard_date() {
		return board_date;
	}



	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}



	public int getBoard_viewcount() {
		return board_viewcount;
	}



	public void setBoard_viewcount(int board_viewcount) {
		this.board_viewcount = board_viewcount;
	}



	public String getBoard_password() {
		return board_password;
	}



	public void setBoard_password(String board_password) {
		this.board_password = board_password;
	}



	public String getBoard_image() {
		return board_image;
	}



	public void setBoard_image(String board_image) {
		this.board_image = board_image;
	}



	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardVO [boarde_seq=").append(board_seq).append(", board_title=").append(board_title)
				.append(", board_contents=").append(board_contents).append(", board_writer=").append(board_writer)
				.append(", board_date=").append(board_date).append(", board_viewcount=").append(board_viewcount)
				.append(", board_password=").append(board_password).append(", board_image=").append(board_image)
				.append("]");
		return builder.toString();
	}
	
	
	
	*/
	
	
	
	
	
	

