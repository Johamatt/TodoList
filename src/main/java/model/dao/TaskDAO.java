package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Task;


	/**
	 * Task-tietokohteen tietokantakäsittelypalvelut kuten
		 * findAll() - hae kaikki tasks tietokannasta
		 * findById() - hae yhden taskin tiedot annetulla taskid:llä  TODO
		 * addTask() - lisää task tietokantaan
		 * removeTask() - poista taskin tiedot tietokannasta
		 * updateTask() - päivitä taskin tiedot tietokantaan  TODO
	 *
	 */

	public class TaskDAO extends DAO {
		
		public ArrayList<Task> findAll() {	
			Connection connection = null;  
			PreparedStatement statement = null; 
			ResultSet resultset = null;   
			
			ArrayList<Task> tasks = new ArrayList<Task>();
			Task task = null; 
			try {
				connection = getDBConnection();
				String sqlSelect = 											//sql pyyntö
						"SELECT id, description, checked, date FROM Task;";
				statement = connection.prepareStatement(sqlSelect);	//alustetaan pyyntö tietokannalle
				resultset = statement.executeQuery();				//lähetetään pyyntö tietokannalle, palauttaa setin
	
				while (resultset.next()) {					// iteroidaan setistä task rivit
					task = readTask(resultset);
					tasks.add(task);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				closeDBConnection(resultset, statement, connection);	//suljetaan DB yhteys
			}
			return tasks;												//palautetaan taskit
		}
		
		public Task findById(int id) {
			
			Connection connection = null;  
			PreparedStatement statement = null; 
			ResultSet resultset = null;  
			Task task = null;
				
			int id2 = 0;				
			String description = ""; 
			int checked = 0;
			Date date = null;
			
			try {
				connection = getDBConnection();
				String sqlSelect ="SELECT id, description, checked, date FROM Task WHERE id =" + id;
				statement = connection.prepareStatement(sqlSelect);

				resultset = statement.executeQuery();
				
				if(resultset.next()) {
					id2 = resultset.getInt("id");
					description = resultset.getString("description");
					checked = resultset.getInt("checked");
					date = resultset.getDate("date");
				}
				
				task = new Task(id2,description,checked,date);

			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				closeDBConnection(resultset, statement, connection);
			}
			return task;	
		}
		
		
		private Task readTask(ResultSet resultset) {	
			try {
				int id = resultset.getInt("id");
				String description = resultset.getString("description");
				int checked = resultset.getInt("checked");
				Date date = resultset.getDate("date");
								
				return new Task(id, description, checked, date);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
						
		public boolean addTask(Task task)  {
			Connection connection = null;
			PreparedStatement stmtInsert = null;
			boolean updateSuccessed = false; 
		
			try {

				connection = getDBConnection();
				String sqlInsert = 
				"INSERT INTO Task (description, checked, date) VALUES (?, ?, ?)";

				stmtInsert = connection.prepareStatement(sqlInsert);
				stmtInsert.setString(1, task.getDescription());
				stmtInsert.setInt(2, 1);
				stmtInsert.setDate(3, task.getDate());
				
				int rowAffected = stmtInsert.executeUpdate();
				if (rowAffected == 1) updateSuccessed = true;
				
			} catch (SQLException e) {
				e.printStackTrace(); 
				throw new RuntimeException(e);
			} finally {
				closeDBConnection(stmtInsert, connection); 
			}
			return updateSuccessed;
		}
		
		public boolean removeTask(int taskId) {
			Connection connection = null;
			PreparedStatement stmtDelete = null;
			boolean updateSuccessed = false;

			try {
				connection = getDBConnection();
				String sql = "DELETE FROM Task WHERE id = ?";
				stmtDelete = connection.prepareStatement(sql);
				stmtDelete.setInt(1, taskId);

				int rowAffected = stmtDelete.executeUpdate();
				if (rowAffected == 1) updateSuccessed = true;
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				closeDBConnection(stmtDelete, connection); 
			}
			return updateSuccessed;
		}
		
		
		public boolean updateTask(Task task) {
			Connection connection = null;
			PreparedStatement stmtPut = null;
			boolean updateSuccessed = false;
			
			
			try {
				connection = getDBConnection();
				
				String sql = "UPDATE Task SET description = ?, checked= ?, date = ? WHERE ID= ?";
				stmtPut = connection.prepareStatement(sql);
				
				stmtPut.setString(1, task.getDescription());				
				stmtPut.setInt(2, task.getChecked());			
				stmtPut.setDate(3, task.getDate());
				stmtPut.setInt(4, task.getId());
				
				int rowAffected = stmtPut.executeUpdate();
				if (rowAffected == 1) updateSuccessed = true;
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				closeDBConnection(stmtPut, connection); 
			}
			return updateSuccessed;
		}
		
		

		
		
			
		
	}

