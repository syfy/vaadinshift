package com.project.el.builders;

import java.sql.Date;

import com.project.el.domain.Fellowship;

public class FellowshipBuilder {
	Fellowship fellowship;
	public FellowshipBuilder(){
		fellowship = new Fellowship();
	}
	public FellowshipBuilder setDate(Date date) {
		fellowship.setDate(date);
		return this;
	}
	
	public Fellowship build(){
		return fellowship;
	}

}
