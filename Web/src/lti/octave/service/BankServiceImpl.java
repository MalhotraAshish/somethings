
package lti.octave.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import lti.octave.repo.BankRepository;
import lti.octave.repo.InvalidAccountException;
import lti.octave.repo.NoBeneficiaryExistException;
import lti.octave.repo.PasswordDoesnotExistException;

/**
 * @author Vanita, Ashish,Yash,Pradyuman
 *
 */
@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankRepository repo;

	/* Validating Aadhar Number from the Database through Exception Handling */
	@Override
	public AadharBean validate(long aadharNo) throws AccountAlreadyExistException {
		AadharBean registerUser = repo.validate(aadharNo);
		return registerUser;
	}

	/* Fetching Details from Aadhar Table */
	@Override
	public List<AadharBean> getDetails(int aadharNo) {
		return repo.getDetails(aadharNo);
	}

	/* Saving Details in the Account Table */
	@Override
	public boolean save(AccountBean account) {
		boolean flag = repo.save(account);
		return flag;
	}

	/* Validating Login Process through Exception Handling */
	@Override
	public AccountBean authenticate(LoginBean login) throws PasswordDoesnotExistException {
		AccountBean user = repo.authenticate(login);
		return user;
	}

	/* Updating Password in the Database */
	@Override
	public void updatePassword(String newPassword, ForgetBean forget) {
		repo.upadatePassword(newPassword, forget);
	}

	/* Checking Email and MobileNo during ForgetPassword Process */
	@Override
	public boolean check(ForgetBean forgetBean) {

		if (repo.check(forgetBean)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void deposit(FundTransferBean fund, long accountNo) throws InvalidAccountException {
		repo.depositAmount(fund, accountNo);

	}

	@Override
	public AccountBean withdraw(FundTransferBean fund, long acntNo) throws BalanceException {
		return repo.withdrawAmount(fund, acntNo);
	}

	@Override
	public List<TransactionBean> getTxnDetails(AccountBean user) {
		return repo.fetchDetails(user);
	}

	@Override
	public AadharBean getUserDetails(AccountBean user) {
		AadharBean userName = repo.getUserDetails(user);
		return userName;
	}

	@Override
	public void addBeneficiary(AddBeneficiaryBean beneficiary) {
		repo.addBeneficiary(beneficiary);
	}

	@Override
	public List<AccountBean> getBeneficiary(long accountNo) throws NoBeneficiaryExistException{
		return repo.getBeneficiary(accountNo);
	}
	
	@Override
	public List<TransactionBean> getStatementDetails(AccountBean user, DateBean date) {
		return repo.getAccountStatement(user, date);
	}

	@Override
	public AccountBean checkBeneficiary(AddBeneficiaryBean beneficiary)
			throws InvalidAccountException, AccountAlreadyExistException {
		// TODO Auto-generated method stub
		return repo.checkBeneficiary(beneficiary);
	}

}