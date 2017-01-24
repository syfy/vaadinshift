package com.project.el.ui.attendee;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class AttendeeForm extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PostConstruct
	void init() {
		System.out.println("asdasd");
		final TextField textLastName = new TextField("Last Name");
		textLastName.setInputPrompt("Last Name");
		textLastName.setWidth(100.0f, Unit.PERCENTAGE);
		addComponent(textLastName);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
