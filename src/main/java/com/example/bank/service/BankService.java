package com.example.bank.service;

import com.example.bank.model.Customer;
import com.example.bank.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BankService {
    private Map<Integer, Customer> customers = new LinkedHashMap<>();
    private List<Transaction> transactions = new ArrayList<>();
    private int transactionCounter = 1;

    public void addCustomer(int accountNumber, String name, double balance) {
        customers.put(accountNumber, new Customer(accountNumber, name, balance));
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }

    public Customer getCustomer(int accountNumber) {
        return customers.get(accountNumber);
    }

    public boolean transfer(int fromAcc, int toAcc, double amount) {
        Customer sender = customers.get(fromAcc);
        Customer receiver = customers.get(toAcc);
        if (sender == null || receiver == null || sender.getBalance() < amount) {
            return false;
        }
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
        transactions.add(new Transaction(transactionCounter++, fromAcc, toAcc, amount));
        return true;
    }

    public List<Transaction> getTransactionsForAccount(int accountNumber) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getFromAccount() == accountNumber || t.getToAccount() == accountNumber) {
                result.add(t);
            }
        }
        return result;
    }
}
