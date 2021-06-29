package com.esprit.weBank.controller;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.esprit.weBank.entities.Transaction;
import com.esprit.weBank.entities.User;
import com.esprit.weBank.repository.ITransactionRepository;
import com.esprit.weBank.services.TransactionService;

@RestController
public class TransactionRestController {
	@Autowired
	private TransactionService transactionService;
	private ITransactionRepository iTransactionRepository;

	@PutMapping(value = "/createTransaction")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
		return transactionService.addTransaction(transaction);
	}
	@GetMapping(value ="/getAllTransactions")
	public List<Transaction> getAllTransactions() {
		return transactionService.findAllTransaction();
	}	
	

	@DeleteMapping(value ="/deleteTransaction/{id}")
	public void deleteTransaction(@PathVariable(value = "id") int id) {
		transactionService.deleteTransactionById(id);
	}
	@GetMapping(value ="/getTransactionByRibE/{ribE}")
	public List<Transaction> findTransactionByRibE(@PathVariable(value = "ribE") int ribE) {
		return transactionService.findTransactionByRibE(ribE);
	}
	
	@GetMapping(value ="/getTransactionsById/{id}")
	public Transaction getTransactionById(@PathVariable(value = "id") int id) {
		return transactionService.findTransactionById(id);
}
	
	@PostMapping(value ="/updateTransaction/{id}")
	public Transaction updateTransaction(@PathVariable(value = "id") int id, @RequestBody Transaction transaction) {
		return transactionService.updateTransaction(transaction, id);
	}
	
	/*
	@RequestMapping(value= {"/transaction/{id}"})
	public String home( Model model,
			@RequestParam(name="page",defaultValue="0")int p,
			@RequestParam(name="size",defaultValue="5")int s,
			@RequestParam(name="motCle",defaultValue="")String motCle) {
		
		Pageable paging = PageRequest.of(p, s);
		
		Page<Transaction>pageTransactions = ITransactionRepository.getAllTransactionByIdUser("%"+motCle+"%",paging);
		
		//Page<Produit>pageProduits = produitRepository.findAll(paging);
		
		// Note : la méthode getContent() var retourner une liste de Produits de taille s.
		
		model.addAttribute("pageProduits",pageProduits.getContent());
		int[] pages = new int[pageProduits.getTotalPages()];
		model.addAttribute("pages",pages);
		model.addAttribute("size",s);
		model.addAttribute("pageCourante",p);
		model.addAttribute("motCle", motCle);
		return "produits";
	}
	*/
}