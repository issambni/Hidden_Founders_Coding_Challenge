package com.hidden.founders.model;

import java.util.Date;

public class UserDislike {
	private String id;
	private Date dislikedAt;
	public UserDislike(String id, Date dislikedAt) {
		super();
		this.id = id;
		this.dislikedAt = dislikedAt;
	}
	public UserDislike() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDislikedAt() {
		return dislikedAt;
	}
	public void setDislikedAt(Date dislikedAt) {
		this.dislikedAt = dislikedAt;
	}
	@Override
	public String toString() {
		return "UserDislike [id=" + id + ", dislikedAt=" + dislikedAt + "]";
	}
	

}
