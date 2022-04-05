package com.main;

import com.util.DatabaseConnection;
import java.util.List;

import com.dao.WorkerDAO;
import com.dao.WorkerDAOImplementation;
import com.model.Worker;
import java.util.Date;

public class App {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        WorkerDAO workerDao = new WorkerDAOImplementation();
     //  Worker worker = new Worker(199980,"krishna","vamsi",50,new Date(),"cse","maill");
     //   System.out.println(workerDao.add(worker) + " row is added!!!");
        Worker emp = new Worker(9, "Karan", "Seth", 50000, new Date(), "new","k.seth1r@my_org.in");
        workerDao.update(emp);
        System.out.println(workerDao.getWorker(1));
        System.out.println(workerDao.getWorker(2));
        List<Worker> list=workerDao.getWorkers();
        list.forEach(System.out::println);
        
        workerDao.delete(10);
        

        DatabaseConnection.closeConnection();
    }
}

