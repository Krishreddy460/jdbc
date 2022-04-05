package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import com.model.Worker;
import java.sql.Statement;
import com.util.DatabaseConnection;

public class WorkerDAOImplementation implements WorkerDAO 
{
    Connection connection;

    public WorkerDAOImplementation() throws SQLException {
        this.connection = DatabaseConnection.getConnection();

    }
    

    public int add(Worker worker) throws SQLException {
          int workerId = worker.getWorkerId();
          String firstName = worker.getFirstName();
          String lastName = worker.getLastName();
          int salary = worker.getSalary();
          Date date = worker.getJoiningDate();
          String department = worker.getDepartment();
          String email = worker.getEmail();
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          String joiningDate = sdf.format(date);
          String query = String.format("INSERT INTO worker VALUES(%d,'%s','%s',%d,'%s','%s','%s');", workerId,
                  firstName,
                  lastName, salary, joiningDate, department, email);
          try (Statement statement = connection.createStatement()) {
              return statement.executeUpdate(query);
          }

      }

     
      public Worker getWorker(int workerId) throws SQLException {
         // String query = "SELECT * FROM worker WHERE worker_id=?";
          
          String query="select * from worker where worker_id="+workerId;
          Worker res = null;
        // PreparedStatement ps = connection.prepareStatement(query); 
          //    ps.setInt(1, workerId);
            //  ResultSet result = ps.executeQuery();
          Statement st=connection.createStatement();
          ResultSet result =st.executeQuery(query);
          
              while (result.next()) {
                  int workerId1 = result.getInt("worker_id");
                  String firstName = result.getString("first_name");
                  String lastName = result.getString("last_name");
                  int salary = result.getInt("salary");
                  Date date = result.getDate("joining_date");
                  String department = result.getString("department");
                  String email = result.getString("mmmail");
                  res = new Worker(workerId1, firstName, lastName, salary, date, department, email);
              }
          
          return res;
      }
  	@Override
  	public void update(Worker worker) throws SQLException {
  		// TODO Auto-generated method stub
  		
  		
  		 int workerId = worker.getWorkerId();
         String firstName = worker.getFirstName();
         String lastName = worker.getLastName();
         int salary = worker.getSalary();
         Date date = worker.getJoiningDate();
         String department = worker.getDepartment();
         String email = worker.getEmail();
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String joiningDate = sdf.format(date);
         String query=String.format("update worker set worker_id=%d,first_name='%s',last_name='%s',salary='%d',joining_date='%s',department='%s',email='%s' where worker_id=%d",workerId,firstName,
        		 lastName,salary,joiningDate,department,email);
  	}

  	
  	

  	@Override
  	public void delete(int workerId) throws SQLException 
  	{
  		String str="delete from worker where worker_id="+workerId;
  		Statement st=connection.createStatement();
  		boolean b=st.execute(str);
  		System.out.print("is deletion done ?"+b);		
  		
  	}

  	@Override
  	public List<Worker> getWorkers() throws SQLException {
  		// TODO Auto-generated method stub
  		
  		List<Worker> list=new ArrayList<>();
  		String s="select * from worker";
  		Statement st=connection.createStatement();
  		ResultSet result=st.executeQuery(s);
  		while(result.next())
  		{
  			 int workerId1 = result.getInt("worker_id");
               String firstName = result.getString("first_name");
               String lastName = result.getString("last_name");
               int salary = result.getInt("salary");
               Date date = result.getDate("joining_date");
               String department = result.getString("department");
               String email = result.getString("mmmail");
  		list.add( new Worker(workerId1, firstName, lastName, salary, date, department, email));
               
              
  		}
  		return list;
  	}



}

