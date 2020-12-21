package control;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Task;
import model.dao.TaskDAO;

@WebServlet("/api/tasks")

public class TaskRESTServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			TaskDAO taskdao = new TaskDAO();
			ArrayList<Task> tasks = taskdao.findAll();
			Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String strJSON = gson.toJson(tasks);
			response.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS, HEAD");			//CORS -ehtoja
			response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
			response.addHeader("Access-Control-Max-Age", "1728000");
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().println(strJSON);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			String strJSONInput = request.getReader().lines().collect(Collectors.joining()); 		//Otetaan requestistä vain Body -osuus
			Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();						//Alustetaan gsonbuilderi pvm käsittelyä varten
			Task task = gson.fromJson(strJSONInput, Task.class);									//Muunnetaan JSON task -luokaksi
			TaskDAO taskdao = new TaskDAO();
			taskdao.addTask(task);
			String strJSONOutput = new Gson().toJson(task);
																													
			response.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS, HEAD");		  
			response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
			response.addHeader("Access-Control-Max-Age", "1728000");											
			response.setContentType("application/json; charset=UTF-8");
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().println(strJSONOutput);											//Lähetetään viesti takaisin (frontille)
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)								
			throws ServletException, IOException {
		try {
			
			String strJSONInput = request.getReader().lines().collect(Collectors.joining());		//sama tääl
			Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			Task task1 = gson.fromJson(strJSONInput, Task.class);
	        TaskDAO taskdao = new TaskDAO();
	        taskdao.updateTask(task1);        
			String strJSONOutput = new Gson().toJson(task1);
			
			response.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS, HEAD");
			response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
			response.addHeader("Access-Control-Max-Age", "1728000");
	
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().println(strJSONOutput);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {			
			response.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS, HEAD");		
			response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
			response.addHeader("Access-Control-Max-Age", "1728000");
		
			String idStr = request.getParameter("id");
			int taskId = new Integer(idStr);
			TaskDAO taskdao = new TaskDAO();
			taskdao.removeTask(taskId);			
			
			response.setContentType("application/json; charset=UTF-8");	
			response.getWriter().println(response);                     // väärä response?	 		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
