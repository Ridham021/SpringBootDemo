package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO{

    // define fields for entity manager
     private EntityManager entityManager ;

    // setup constructor injection
    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager theEntityManager)
    {
        entityManager = theEntityManager;
    }

    @Override
    @Transactional //manage different transactions commmit , start etc
    public List<Employee> findAll() {

        //get the cureent hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a Query
        Query<Employee> theQuery = currentSession.createQuery("from Employee ",Employee.class);

        // Execute Query and get Result list

        List<Employee> employees = theQuery.getResultList();

        // return the result

        return employees;

    }

    @Override
    public Employee findById(int theId) {

        //get the current hibernate session

        Session currentSession =  entityManager.unwrap(Session.class);

        // get the Employee

        Employee theEmployee = currentSession.get(Employee.class,theId);

        // return the Employee

        return theEmployee;
    }

    @Override
    public void save(Employee theEmployee) {
        //get the current hibernate session

        Session currentSession =  entityManager.unwrap(Session.class);

        //save or update

        currentSession.saveOrUpdate(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        //get the current hibernate session

        Session currentSession =  entityManager.unwrap(Session.class);

        //delete by Id

        Query theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");
        theQuery.setParameter("employeeId",theId);

        theQuery.executeUpdate();


    }


}
