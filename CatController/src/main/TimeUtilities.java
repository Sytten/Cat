package main;

import communication.Message;

public class TimeUtilities {

	public static String parseTime(Message message, int mininumValue, int maximumValue) throws NumberFormatException {
		String timeParameter = null;

		timeParameter = message.getParameter("QTY");

		int time = Integer.parseInt(timeParameter);
		
		if (time >= mininumValue & time <= maximumValue) {
			return timeParameter;
		} else {
			return null;
		}

	}
}
