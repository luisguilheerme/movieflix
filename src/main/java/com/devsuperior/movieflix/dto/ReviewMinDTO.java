package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Review;

public class ReviewMinDTO {
	
	private Long id;
    private String text;
    private String userName;
    
    
    public ReviewMinDTO() {
        	
    }	        
    
    public ReviewMinDTO(Long id, String text, String userName) {
		this.id = id;
		this.text = text;
		this.userName = userName;
	}

    public ReviewMinDTO(Review entity) {
		id = entity.getId();
		text = entity.getText();		
		userName = entity.getUser().getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

    
}
