package com.et.expense.tracker.resources;

import com.et.expense.tracker.domain.Transaction;
import com.et.expense.tracker.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5500", maxAge = 3600)
@RestController
@RequestMapping("/api/categories/{categoryId}/transactions")
public class TransactionResource {

    @Autowired
    TransactionService transactionService;

    @PostMapping("")
    public ResponseEntity<Transaction> addTransaction(HttpServletRequest request, @PathVariable("categoryId") Integer categoryId, @RequestBody Map<String,Object> transactionMap){
        int userId= (Integer) request.getAttribute("userId");
        Double amount= Double.valueOf(transactionMap.get("amount").toString());
        String note= (String) transactionMap.get("note");
        Long transactionDate= (Long) transactionMap.get("transactionDate");
        Transaction transaction=transactionService.addTransaction(userId,categoryId,amount,note,transactionDate);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(HttpServletRequest request,@PathVariable("categoryId") Integer categoryId,@PathVariable("transactionId") Integer transactionId){
        int userId= (Integer) request.getAttribute("userId");
        Transaction transaction= transactionService.fetchTransactionById(userId,categoryId,transactionId);
        return new ResponseEntity<>(transaction,HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Transaction>> getAllTransactions(HttpServletRequest request, @PathVariable("categoryId") Integer categoryId){
        int userId= (Integer) request.getAttribute("userId");
        System.out.println(userId);
        List<Transaction> transactions=transactionService.fetchAllTransaction(userId,categoryId);
        return new ResponseEntity<>(transactions,HttpStatus.OK);
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<Map<String,Boolean>> updateTransaction(HttpServletRequest request,@PathVariable("categoryId") Integer categoryId,@PathVariable("transactionId") Integer transactionId,@RequestBody Transaction transaction){
        int userId= (Integer) request.getAttribute("userId");
        transactionService.updateTransaction(userId,categoryId,transactionId,transaction);
        Map<String,Boolean> map=new HashMap<>();
        map.put("success",true);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Map<String,Boolean>> deleteTransaction(HttpServletRequest request,@PathVariable("categoryId") Integer categoryId, @PathVariable("transactionId") Integer transctionId){
        int userId= (Integer) request.getAttribute("userId");
        transactionService.removeTransaction(userId,categoryId,transctionId);
        Map<String,Boolean> map=new HashMap<>();
        map.put("success",true);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

}
