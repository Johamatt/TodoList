package model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter					//lombok plugin	
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Task {					
	private int id;
	private String description;
	private int checked;
	private Date date;
	
	
	public Task(String description, int checked, Date date) {
		this.id = 0;
		this.description = description;
		this.checked = checked;
		this.date = date;

	}
}
