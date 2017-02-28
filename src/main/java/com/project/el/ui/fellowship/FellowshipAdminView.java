package com.project.el.ui.fellowship;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.el.builders.FellowshipBuilder;
import com.project.el.domain.FellowshipPresentationCursor;
import com.project.el.domain.FellowshipPresentationCursorAggregate;
import com.project.el.service.AttendeeService;
import com.project.el.service.FellowshipService;
import com.project.el.service.PersonalInformationService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.renderers.DateRenderer;

@SpringView(name = "admin/fellowship")
public class FellowshipAdminView extends VerticalLayout implements View {
	private Grid fellowshipGrid;
	private static final String FORMAT = "%1$td/%1$tm/%1$tY";

	@Autowired
	FellowshipService fellowshipService;

	Window subWindow = new Window("Sub-window");
	FormLayout fellowshipForm = new FormLayout();
	Button createFellowshipButton = new Button("Create", this::openCreateDialog);

	BeanItemContainer<FellowshipPresentationCursor> beanItemContainer = new BeanItemContainer<FellowshipPresentationCursor>(
			FellowshipPresentationCursor.class);
	private Button openFellowshipButton;
	final PopupDateField birthDate = new PopupDateField("Date");

	@PostConstruct
	void init() {
		fellowshipGrid = new Grid();
		FellowshipPresentationCursorAggregate fellowshipPresentationCursorAggregate = new FellowshipPresentationCursorAggregate(
				fellowshipService.findAll());
		beanItemContainer.addAll(fellowshipPresentationCursorAggregate.getFellowshipPresentationList());

		fellowshipGrid.setContainerDataSource(beanItemContainer);
		fellowshipGrid.getColumn("date").setRenderer(new DateRenderer(FORMAT));
		fellowshipGrid.addSelectionListener(event -> toggleOpenFellowshipEnablement(event.getSelected().size() > 0));

		openFellowshipButton = new Button("Open Fellowship Selected", this::openFellowship);
		setMargin(true);
		setSpacing(true);
		setSizeFull();

		subWindow.setModal(true);
		subWindow.setHeight("30%");
		subWindow.setWidth("20%");

		fellowshipForm.addComponent(birthDate);
		fellowshipForm.addComponent(new Button("Save And Close", this::closeDialog));

		HorizontalLayout buttonContainer = new HorizontalLayout(createFellowshipButton, openFellowshipButton);

		addComponent(fellowshipGrid);
		addComponent(buttonContainer);

	}

	private void openCreateDialog(Button.ClickEvent event) {

		subWindow.setContent(fellowshipForm);
		UI.getCurrent().addWindow(subWindow);

	}

	private void createFellowship() {
		FellowshipBuilder fellowshipBuilder = new FellowshipBuilder();
		fellowshipBuilder.setDate(new java.sql.Date(birthDate.getValue().getTime()));
		fellowshipService.save(fellowshipBuilder.build());

	}

	private void closeDialog(Button.ClickEvent event) {
		createFellowship();
		refresh();
		subWindow.close();

	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	private void openFellowship(Button.ClickEvent event) {
		FellowshipPresentationCursor selectedFellowshipViewCursor = (FellowshipPresentationCursor) fellowshipGrid
				.getSelectedRow();
		if (selectedFellowshipViewCursor != null) {
			getUI().getNavigator().navigateTo(String.format("fellowship/%d", selectedFellowshipViewCursor.getId()));
		}
	}

	private void toggleOpenFellowshipEnablement(boolean enabled) {
		openFellowshipButton.setEnabled(enabled);
		if (enabled) {
			openFellowshipButton.setDescription("");
		} else {
			openFellowshipButton.setDescription("Please select a fellowsip from the list");
		}
	}

	private void refresh() {

		beanItemContainer.removeAllItems();
		FellowshipPresentationCursorAggregate fellowshipPresentationCursorAggregate = new FellowshipPresentationCursorAggregate(
				fellowshipService.findAll());
		beanItemContainer.addAll(fellowshipPresentationCursorAggregate.getFellowshipPresentationList());

	}

}
