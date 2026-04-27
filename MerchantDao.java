package org.MerchantApp.dao;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;


import org.MerchantApp.dto.Merchant;

public class MerchantDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
	EntityManager man= emf.createEntityManager();
	
	//Save the merchant record by using  reference variable 
	public Merchant saveMerchant(Merchant m){
		EntityTransaction etran=man.getTransaction();
		etran.begin();
		man.persist(m);
		etran.commit();
		
		return m;
	}
	
	//Updating the merchant record by accessing id
	public Merchant updateMerchant(Merchant m) {
		EntityTransaction etran=man.getTransaction();
		etran.begin();
		Merchant mer=man.find(Merchant.class, m.getId());
		if(mer!=null) {
			mer.setName(m.getName());
			mer.setEmail(m.getEmail());
			mer.setGst_num(m.getGst_num());
			mer.setPhone(m.getPhone());
			mer.setPassword(m.getPassword());
			etran.commit();
			
			return mer;
		}
		else {
			return null;

		}
		
		
	}
	public Merchant findMerchantById(int mid) {

		return man.find(Merchant.class, mid);
		
		
	}
	
	public Merchant verifyMerchantByEmailIdAndPassword(String em, String pw) {
		Query q = man.createQuery("select m from Merchant m where m.email=?1 and m.password=?2");
		q.setParameter(1, em);
		q.setParameter(2, pw);
		try {
			Merchant mdb = (Merchant) q.getSingleResult();
			return mdb;
		}
		catch(NoResultException e) {
			return null;
		}
		
	}
	
	public Merchant verifyMerchantByPhoneAndPassword(long ph, String pw) {
		Query q  =  man.createQuery("select m from Merchant m where m.phone=?1 and m.password=?2");
		q.setParameter(1, ph);
		q.setParameter(2, pw);
		try {
			Merchant mdb = (Merchant) q.getSingleResult();
			return mdb;
		}
		catch (NoResultException e) {
			return null;
		}
		
	}
}
