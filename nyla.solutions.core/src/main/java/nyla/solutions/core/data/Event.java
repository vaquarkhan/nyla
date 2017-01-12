package nyla.solutions.core.data;

import java.io.Serializable;
import java.util.Date;

import nyla.solutions.core.data.Event;
import nyla.solutions.core.data.Time;
import nyla.solutions.core.data.TimeInterval;
import nyla.solutions.core.data.TimeSlot;

/**
 * 
 * <b>Event</b> represents a calendar event
 * 
 * @author Gregory Green
 *
 */
public class Event implements Serializable, Comparable<Event>
{
	/**
    * 
    */
	private static final long serialVersionUID = 4865756250759427705L;

	/**
	 * 
	 * @param name
	 * @param startHour24
	 * @param durationSeconds
	 * @return a new event
	 */
	public static Event getEvent(String name, int startHour24,
			int durationSeconds)
	{
		Event event = new Event();
		event.setName(name);

		Time time = new Time();
		time.setHour24(startHour24);

		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartDate(time.getDate());

		time.addSeconds(durationSeconds);
		timeSlot.setEndDate(time.getDate());

		event.setTimeSlot(timeSlot);

		return event;
	}// --------------------------------------------

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the timeSlot
	 */
	public TimeInterval getTimeSlot()
	{
		return timeSlot;
	}

	/**
	 * @param timeSlot the timeSlot to set
	 */
	public void setTimeSlot(TimeSlot timeSlot)
	{
		this.timeSlot = timeSlot;
	}

	/**
	 * @return the location
	 */
	public String getLocation()
	{
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location)
	{
		this.location = location;
	}

	/**
	 * @return the reminderDate
	 */
	public Date getReminderDate()
	{
		if (reminderDate == null)
			return null;

		return new Date(reminderDate.getTime());
	}// --------------------------------------------------------

	/**
	 * @param reminderDate the reminderDate to set
	 */
	public void setReminderDate(Date reminderDate)
	{
		if(reminderDate == null)
			this.reminderDate = null;
		else
			this.reminderDate = new Date(reminderDate.getTime());
	}// --------------------------------------------

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((reminderDate == null) ? 0 : reminderDate.hashCode());
		result = prime * result
				+ ((timeSlot == null) ? 0 : timeSlot.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (location == null)
		{
			if (other.location != null)
				return false;
		}
		else if (!location.equals(other.location))
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		if (reminderDate == null)
		{
			if (other.reminderDate != null)
				return false;
		}
		else if (!reminderDate.equals(other.reminderDate))
			return false;
		if (timeSlot == null)
		{
			if (other.timeSlot != null)
				return false;
		}
		else if (!timeSlot.equals(other.timeSlot))
			return false;
		return true;
	}

	/**
	 * 
	 * Compare the timeslots of two events
	 * 
	 * @see java.lang.Comparable#compareTo(T)
	 */
	public int compareTo(Event other)
	{
		return this.timeSlot.compareTo(other.timeSlot);
	}

	private Date reminderDate = null;
	private String name = null;
	private TimeSlot timeSlot = null;
	private String location = null;

}