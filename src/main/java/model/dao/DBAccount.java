package model.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter

public class DBAccount {	
	String dbUsername = "bgm074"; 
	String dbPassword = "*";
	String url = "jdbc:mysql://localhost:3306/bgm074";
}
