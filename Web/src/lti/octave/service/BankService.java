package lti.octave.service;

import java.util.List;

import lti.octave.bean.AadharBean;
import lti.octave.bean.AccountBean;
import lti.octave.bean.AddBeneficiaryBean;
import lti.octave.bean.DateBean;
import lti.octave.bean.ForgetBean;
import lti.octave.bean.FundTransferBean;
import lti.octave.bean.LoginBean;
import lti.octave.bean.TransactionBean;
import lti.octave.repo.AccountAlreadyExistException;
import lti.octave.repo.BalanceException;
import lti.octave.repo.InvalidAccountException;
import lti.octave.repo.NoBeneficiaryExistException;
import lti.octave.repo.PasswordDoesnotExistException;

public interface BankService {

	// Method to validate Aadhar number
	AadharBean validate(long aadharNo) throws AccountAlreadyExistException;

	// Method to fetch user personal details
	List<AadharBean> getDetails(int aadharNo);

	// Method to save pan and email details to db
	boolean save(AccountBean account);

	// Method to authenticate email and password from database
	AccountBean authenticate(LoginBean login) throws PasswordDoesnotExistException;

	// Method to check email and mobileNo in forget password page from database.
	boolean check(ForgetBean forget);

	// Method to Update Password in the Database
	void updatePassword(String newPassword, ForgetBean forget);

	void deposit(FundTransferBean fund, long accountNo) throws InvalidAccountException;

	AccountBean withdraw(FundTransferBean fund, long acntNo) throws BalanceException;

	// Method to get transaction details
	List<TransactionBean> getTxnDetails(AccountBean user);
	
	AadharBean getUserDetails(AccountBean user);
	
	List<TransactionBean> getStatementDetails(AccountBean user, DateBean date);
	
	void addBeneficiary(AddBeneficiaryBean beneficiary) ;
	
	List<AccountBean> getBeneficiary(long accountNo) throws NoBeneficiaryExistException;

	AccountBean checkBeneficiary(AddBeneficiaryBean beneficiary) throws InvalidAccountException, AccountAlreadyExistException;
	

}
