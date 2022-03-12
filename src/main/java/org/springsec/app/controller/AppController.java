package org.springsec.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springsec.app.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Controller
@RequestMapping("/data")
public class AppController {

    public final EntityManagerFactory entityManagerFactory;

    @Autowired
    public AppController(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Transactional
    @GetMapping("/allUsers")
    public String allUsers(Model model) {
        EntityManager entityManager =
                entityManagerFactory.createEntityManager();
        List<Customer> theCustomers = entityManager
                .createQuery("from Customer order by firstName",
                        Customer.class)
                .getResultList();
        for (Customer theCustomer : theCustomers) {
            System.out.println(theCustomer.getFirstName());
        }
        model.addAttribute("customers", theCustomers);
        return "all-users";
    }

}
