package com.project.el.domain;

import java.util.ArrayList;
import java.util.List;

public class FellowshipPresentationCursorAggregate {
	ArrayList<FellowshipPresentationCursor> fellowshipPresentationList ;

	public ArrayList<FellowshipPresentationCursor> getFellowshipPresentationList() {
		return fellowshipPresentationList;
	}

	public void setFellowshipPresentationList(ArrayList<FellowshipPresentationCursor> fellowshipPresentationList) {
		this.fellowshipPresentationList = fellowshipPresentationList;
	}

	public FellowshipPresentationCursorAggregate(List<Fellowship> listOfFellowship) {
		
		fellowshipPresentationList  = new ArrayList<FellowshipPresentationCursor>();
		for (Fellowship fellowship : listOfFellowship) {
			FellowshipPresentationCursor cursorRow = new FellowshipPresentationCursor();
			cursorRow.setDate(fellowship.getDate());
			cursorRow.setId(fellowship.getId());
			fellowshipPresentationList.add(cursorRow);
		}
	}

}
