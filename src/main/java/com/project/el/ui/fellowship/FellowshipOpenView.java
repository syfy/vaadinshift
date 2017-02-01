package com.project.el.ui.fellowship;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.el.domain.Attendee;
import com.project.el.domain.AttendeePresentationOnGrid;
import com.project.el.domain.AttendeePresentationOnGridAggregate;
import com.project.el.domain.Fellowship;
import com.project.el.service.AttendeeService;
import com.project.el.service.FellowshipService;
import com.project.el.service.PersonalInformationService;
import com.project.el.ui.models.FellowshipModel;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.Grid.MultiSelectionModel;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@Component
@SpringView(name = "fellowship")
@Transactional
public class FellowshipOpenView extends HorizontalLayout implements View {

	Grid fellowshipAttendeesGrid;
	Grid attendeesSearchGrid;
	VerticalLayout verticalLayoutFrameAttendees;
	VerticalLayout verticalLayoutFrameSearch;
	VerticalLayout mainLayout;
	@Autowired
	FellowshipModel fellowshipModel;
	@Autowired
	FellowshipService fellowshipService;
	@Autowired
	AttendeeService attendeeService;
	@Autowired
	PersonalInformationService personalInformationService;

	AttendeePresentationOnGridAggregate attendeePresentationOnGridAggregate;
	BeanItemContainer<AttendeePresentationOnGrid> beanItemContainerAttendeeSearch = new BeanItemContainer<AttendeePresentationOnGrid>(
			AttendeePresentationOnGrid.class);
	BeanItemContainer<AttendeePresentationOnGrid> beanItemContainerfellowshipAttendees = new BeanItemContainer<AttendeePresentationOnGrid>(
			AttendeePresentationOnGrid.class);

	Button addSelectedAttendeesButton;
	Button removeSelectedAttendeeButton;

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		Long id = Long.valueOf(event.getParameters());

		// TODO add error handling
		fellowshipModel.initialize(id);
		Fellowship selectedFellowship = fellowshipModel.getFellowship();
		beanItemContainerfellowshipAttendees
				.addAll(selectedFellowship.getAttendeeAsPresentation().getAttendeePresentationOnGridAggregate());

		attendeePresentationOnGridAggregate = new AttendeePresentationOnGridAggregate(attendeeService.findAll());

		beanItemContainerAttendeeSearch
				.addAll(removeDuplicateFromBeans(selectedFellowship).getAttendeePresentationOnGridAggregate());

		attendeePresentationOnGridAggregate.getAttendeePresentationOnGridAggregate()
				.removeAll(selectedFellowship.getAttendeeAsPresentation().getAttendeePresentationOnGridAggregate());

	}

	AttendeePresentationOnGridAggregate removeDuplicateFromBeans(Fellowship fellowship) {
	
		AttendeePresentationOnGridAggregate returnValue = new AttendeePresentationOnGridAggregate();
		for (AttendeePresentationOnGrid attendeePresentationOnGridRow : attendeePresentationOnGridAggregate
				.getAttendeePresentationOnGridAggregate()) {
			boolean isDuplicate = false;
			for (AttendeePresentationOnGrid attendeeRow : fellowshipService
					.getPresentationOfAttendeesList(fellowship.getId())) {

				if ((attendeeRow.getId() == attendeePresentationOnGridRow.getId())) {
					// if((attendeeRow.equals(attendeePresentationOnGridRow))){
					isDuplicate = true;

				}

			}

			if (!isDuplicate) {
				returnValue.getAttendeePresentationOnGridAggregate().add(attendeePresentationOnGridRow);
			}
		}

		return returnValue;
	}

	void refreshDataSource(Fellowship fellowship) {

		beanItemContainerfellowshipAttendees.removeAllItems();
		beanItemContainerfellowshipAttendees
				.addAll(fellowshipService.getPresentationOfAttendeesList(fellowship.getId()));
		fellowshipModel.initialize(fellowship.getId());
	}

	private void removeAttendeeFromFellowshipEvent(Button.ClickEvent event) {
		this.
		beanItemContainerfellowshipAttendees.removeItem(fellowshipAttendeesGrid.getSelectedRow());
		beanItemContainerAttendeeSearch.addItem(fellowshipAttendeesGrid.getSelectedRow());
		Fellowship fellowship = fellowshipModel.getFellowship();
		AttendeePresentationOnGrid selectedAttendeeViewRow = (AttendeePresentationOnGrid)fellowshipAttendeesGrid.getSelectedRow();
		Attendee attendeeToBeRemoved = attendeeService.getOneJpql(selectedAttendeeViewRow.getId());
		fellowship.removeAttendee(attendeeToBeRemoved);
		fellowshipService.save(fellowship);

	}
	@PostConstruct
	void init() {
		setMargin(true);
		setSpacing(true);
		setSizeFull();

		verticalLayoutFrameAttendees = new VerticalLayout();
		verticalLayoutFrameAttendees.setCaption("Fellowship Enrolled Attendees");

		verticalLayoutFrameSearch = new VerticalLayout();
		verticalLayoutFrameSearch.setCaption("Search to be added Attendees");

		mainLayout = new VerticalLayout();
		mainLayout.setSizeFull();
		fellowshipAttendeesGrid = new Grid();

		fellowshipAttendeesGrid.setSizeFull();

		attendeesSearchGrid = new Grid();

		attendeesSearchGrid.setSizeFull();
		attendeesSearchGrid.setSelectionMode(SelectionMode.MULTI);

		attendeesSearchGrid.setContainerDataSource(beanItemContainerAttendeeSearch);

		fellowshipAttendeesGrid.setContainerDataSource(beanItemContainerfellowshipAttendees);

		HeaderRow filterRow = attendeesSearchGrid.appendHeaderRow();

		// Set up a filter for all columns
		for (Object pid : attendeesSearchGrid.getContainerDataSource().getContainerPropertyIds()) {
			HeaderCell cell = filterRow.getCell(pid);

			TextField filterField = new TextField();
			filterField.setSizeFull();

			// add search field for search grid

			filterField.addTextChangeListener(change -> {
				// Can't modify filters so need to replace
				beanItemContainerAttendeeSearch.removeContainerFilters(pid);
				// (Re)create the filter if necessary
				if (!change.getText().isEmpty())
					beanItemContainerAttendeeSearch
							.addContainerFilter(new SimpleStringFilter(pid, change.getText(), true, false));
			});
			cell.setComponent(filterField);
		}
		MultiSelectionModel selection = (MultiSelectionModel) attendeesSearchGrid.getSelectionModel();
		selection.setSelectionLimit(50000);
		
		removeSelectedAttendeeButton = new Button("Remove Selected",this::removeAttendeeFromFellowshipEvent);
		
		addSelectedAttendeesButton = new Button("Add Selected", e -> {
			// Delete all selected data items
			Fellowship fellowship = fellowshipModel.getFellowship();
			for (Object itemId : selection.getSelectedRows()) {

				AttendeePresentationOnGrid attendee = (AttendeePresentationOnGrid) itemId;

				if (!beanItemContainerfellowshipAttendees.containsId(itemId)) {
					Attendee attendeeToBeSaved = attendeeService.get(attendee.getId());
					fellowship.addAttendee(attendeeToBeSaved);
					beanItemContainerAttendeeSearch.removeItem(itemId);

				}

				// Otherwise out of sync with container
			}

			fellowshipAttendeesGrid.getSelectionModel().reset();
			attendeesSearchGrid.getSelectionModel().reset();
			selection.reset();
			fellowshipService.save(fellowship);

			refreshDataSource(fellowship);
			// fellowshipModel.getFellowship().addAttendee(attendeeService.get(itemId));

			// Disable after deleting
			// e.getButton().setEnabled(false);
		});
		verticalLayoutFrameAttendees.setSizeFull();
		verticalLayoutFrameSearch.setSizeFull();

		verticalLayoutFrameAttendees.addComponent(fellowshipAttendeesGrid);
		verticalLayoutFrameAttendees.addComponent(removeSelectedAttendeeButton);
		verticalLayoutFrameSearch.addComponent(attendeesSearchGrid);
		verticalLayoutFrameSearch.addComponent(addSelectedAttendeesButton);

		mainLayout.addComponent(verticalLayoutFrameAttendees);
		mainLayout.addComponent(verticalLayoutFrameSearch);
		addComponent(mainLayout);

	}

}
