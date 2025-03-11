package com.inventory.Erp.Services;

import com.inventory.Erp.Repository.LoanRepository;
import com.inventory.Erp.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }
    public List<Loan> getLoanList(){
        return loanRepository.findAll();
    }
    public Loan getLoanById(int id){
        Loan fetchLoanById = loanRepository.findById(id).orElseThrow(()-> new RuntimeException("Loan not found"));
        return fetchLoanById;
    }
    public Loan createNewLoan(Loan loan){
        loan.setLoanId(loan.getLoanId());
        loan.setLoanName(loan.getLoanName());
        loan.setLoanType(loan.getLoanType());
        loan.setLoanStatus(true);
        return loanRepository.save(loan);
    }
    public Loan editLoanEditails(int id){
       return loanRepository.findById(id).orElseThrow(()-> new RuntimeException("Loan not found"));
    }
    public Loan updateLoans (int id, Loan updateLoans){
        Optional<Loan> editLoan = loanRepository.findById(id);
        if(editLoan.isEmpty()) throw new RuntimeException("record not found");
        Loan changedetails = editLoan.get();
        changedetails.setLoanStatus(updateLoans.isLoanStatus());
        changedetails.setLoanName(updateLoans.getLoanName());
        updateLoans.setLoanType(updateLoans.getLoanType());
        updateLoans.setLoanName(updateLoans.getLoanName());
       return loanRepository.save(updateLoans);
    }

}
