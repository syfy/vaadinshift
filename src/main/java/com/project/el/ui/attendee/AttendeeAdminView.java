package com.project.el.ui.attendee;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.el.builders.AttendeeBuilder;
import com.project.el.domain.AttendeePresentationOnGrid;
import com.project.el.domain.AttendeePresentationOnGridAggregate;
import com.project.el.service.AttendeeService;
import com.project.el.service.PersonalInformationService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SpringView(name = "admin/attendee")
public class AttendeeAdminView extends VerticalLayout implements View {
	private Grid attendeeGrid;

	@Autowired
	AttendeeService attendeeService;
	@Autowired
	PersonalInformationService personalInformationService;
	
	Window subWindow = new Window("Sub-window");
	FormLayout attendeeForm = new FormLayout();
	Button createAttendeeButton = new Button("Create", this::openCreateDialog);

	final TextField textFirstName = new TextField("First Name");
	final TextField textLastName = new TextField("Last Name");
	final TextField textMobileNumber = new TextField("Mobile Number");
	final TextField textEmail = new TextField("Email Address");
	final TextField textFacebookAccount = new TextField("Facebook Account");
	final TextField textSchoolName = new TextField("School Name");
	final PopupDateField birthDate = new PopupDateField("BirthDate");
	AttendeePresentationOnGridAggregate attendeePresentationOnGridAggregate ;
	BeanItemContainer<AttendeePresentationOnGrid> beanItemContainer = new BeanItemContainer<AttendeePresentationOnGrid>(AttendeePresentationOnGrid.class);
	

	@Override
	public void enter(ViewChangeEvent event) {

	}

	@PostConstruct
	void init() {
		 attendeeGrid = new Grid();
		 attendeeGrid.setSizeFull();
		attendeePresentationOnGridAggregate= new AttendeePresentationOnGridAggregate(attendeeService.findAllJpql());
		beanItemContainer.addAll(attendeePresentationOnGridAggregate.getAttendeePresentationOnGridAggregate());
		attendeeGrid.setContainerDataSource(beanItemContainer);
		addComponent(attendeeGrid);
		setSpacing(true);
		setMargin(true);
		setSizeFull();

		
		subWindow.setModal(true);
		attendeeForm.addStyleName("outlined");
		attendeeForm.setSizeFull();
		attendeeForm.setSpacing(true);

		attendeeForm = new FormLayout();
		attendeeForm.addStyleName("outlined");
		attendeeForm.setSizeFull();
		attendeeForm.setSpacing(true);

		textFirstName.setInputPrompt("First Name");
		attendeeForm.addComponent(textFirstName);

		textLastName.setInputPrompt("Last Name");

		attendeeForm.addComponent(textLastName);

		textMobileNumber.setInputPrompt("Mobile Number");
		attendeeForm.addComponent(textMobileNumber);

		textEmail.setInputPrompt("Email Address");
		attendeeForm.addComponent(textEmail);

		textFacebookAccount.setInputPrompt("Facebook Account");
		attendeeForm.addComponent(textFacebookAccount);

		textSchoolName.setInputPrompt("School Name");
		attendeeForm.addComponent(textSchoolName);
		attendeeForm.addComponent(birthDate);

		attendeeForm.addComponent(new Button("Save And Close", this::closeDialog));
		attendeeForm.setMargin(true);

		createAttendeeButton.setHeight("5%");
		addComponent(createAttendeeButton);

		subWindow.setHeight("50%");
		subWindow.setWidth("30%");
	}

	
	private void createAttendee(){
		AttendeeBuilder attendeeBuilder = new AttendeeBuilder();
		attendeeBuilder.setBirthDate(new java.sql.Date(birthDate.getValue().getTime()))
		.setFirstName(textFirstName.getValue())
		.setLastName(textLastName.getValue())
		.setMobileNumber(textMobileNumber.getValue())
		.setSchoolName(textSchoolName.getValue())
		.setEmailAddress(textEmail.getValue()) 
		.setFaceBookAccountURL(textFacebookAccount.getValue());
	//	personalInformationService.save(attendeeBuilder.build().getPersonalInformation());
		attendeeService.save(attendeeBuilder.build());
		
	}
	
	private void closeDialog(Button.ClickEvent event) {
		createAttendee();
		 refresh() ;
		subWindow.close();
	}

	private void openCreateDialog(Button.ClickEvent event) {

		subWindow.setContent(attendeeForm);
		UI.getCurrent().addWindow(subWindow);

	}
	

	private void refresh() {
		
		beanItemContainer.removeAllItems();
		attendeePresentationOnGridAggregate= new AttendeePresentationOnGridAggregate(attendeeService.findAllJpql());

		beanItemContainer.addAll(attendeePresentationOnGridAggregate.getAttendeePresentationOnGridAggregate());

	}


}
