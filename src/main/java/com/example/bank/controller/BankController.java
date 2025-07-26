package com.example.bank.controller;

import com.example.bank.model.Customer;
import com.example.bank.model.Transaction;
import com.example.bank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BankController {

    @Autowired
    private BankService bankService;

    @ModelAttribute
    public void init() {
        if (bankService.getAllCustomers().isEmpty()) {
            bankService.addCustomer(1001, "Alice", 1000.0);
            bankService.addCustomer(1002, "Bob", 500.0);
        }
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/customers")
    public String customerList(Model model) {
        model.addAttribute("customers", bankService.getAllCustomers());
        return "customerList";
    }

    @GetMapping("/addCustomer")
    public String addCustomerForm() {
        return "addCustomer";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@RequestParam int accountNumber,
                              @RequestParam String name,
                              @RequestParam double balance) {
        bankService.addCustomer(accountNumber, name, balance);
        return "redirect:/customers";
    }

    @GetMapping("/transfer")
    public String transferForm(Model model) {
        model.addAttribute("customers", bankService.getAllCustomers());
        return "transfer";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam int from,
                           @RequestParam int to,
                           @RequestParam double amount,
                           Model model) {
        boolean success = bankService.transfer(from, to, amount);
        model.addAttribute("transferSuccess", success);
        return "redirect:/customers";
    }

    @GetMapping("/account/{id}")
    public String accountDetails(@PathVariable int id, Model model) {
        Customer customer = bankService.getCustomer(id);
        model.addAttribute("customer", customer);
        model.addAttribute("transactions", bankService.getTransactionsForAccount(id));
        return "account";
    }
}
