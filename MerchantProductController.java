package org.merchantProductApp.controller;

import java.util.List;
import java.util.Scanner;

import org.MerchantApp.dao.MerchantDao;
import org.MerchantApp.dao.ProductDao;
import org.MerchantApp.dto.Merchant;
import org.MerchantApp.dto.Product;

public class MerchantProductController {

	static Scanner sc = new Scanner(System.in);
	static MerchantDao mdao = new MerchantDao();
	static ProductDao pdao = new ProductDao();

	public static void main(String[] args) {

		System.out.println("1.Save Merchant");
		System.out.println("2.Update Merchant");
		System.out.println("3.Find Merchant By Id");
		System.out.println("4.Verify Merchant by email and password");
		System.out.println("5.Verify Merchant by phone and password");
		System.out.println("6.Add Product");
		System.out.println("7.Update Product");
		System.out.println("8.Find Products by id");
		System.out.println("9.Find Products by brand and category");
		System.out.println("10.Find Products by merchant id");
		System.out.println("Enter your choice");

		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			saveMerchant();

			break;
		case 2:
			updateMerchant();

			break;
		case 3:
			findMerchantById();

			break;
		case 4:
			verifyMerchantByEmailIdAndPassword();

			break;
		case 5:
			verifyMerchantByPhoneAndPassword();

			break;
		case 6:
			addProduct();

			break;
		case 7:
			updateProduct();

			break;
		case 8:
			findProductById();

			break;
		case 9:
			findProductByCategoryAndBrand();

			break;
		

		default:
			System.err.println("Enter the valid choice");
			break;
		}

	}

	// 1. Save the Merchant

	private static void saveMerchant() {
		System.out.println("Enter Merchant info---name,phone,gstno,email,password");
		String name = sc.next();
		long ph = sc.nextLong();
		String gst = sc.next();
		String em = sc.next();
		String pw = sc.next();

		Merchant m = new Merchant();

		m.setName(name);
		m.setPhone(ph);
		m.setGst_num(gst);
		m.setEmail(em);
		m.setPassword(pw);

		Merchant mdb = mdao.saveMerchant(m);
		System.out.println("Merchant is saved with an id :" + mdb.getId());
	}

	// 2. Update the Merchant

	private static void updateMerchant() {

		System.out.println("Enter merchan info---id,name,phone,gstno,email,password");
		Merchant m = new Merchant();
		m.setId(sc.nextInt());
		m.setName(sc.next());
		m.setPhone(sc.nextLong());
		m.setGst_num(sc.next());
		m.setEmail(sc.next());
		m.setPassword(sc.next());

		Merchant mdb = mdao.updateMerchant(m);
		if (mdb != null) {
			System.out.println("Merchant is updated : " + mdb);
		} else {
			System.err.println("Unable to update the merchant since id is invalid");
		}
	}

//3. Find Merchant By Id

	private static void findMerchantById() {
		System.out.println("Enter Merchant id to find Merchant ");
		int mid = sc.nextInt();
		Merchant mdb = mdao.findMerchantById(mid);
		if (mdb != null) {
			System.out.println(mdb);
		} else {
			System.err.println("Unable to find the merchant information since id is invalid");
		}
	}

//4. Verify Merchant by email and password

	private static void verifyMerchantByEmailIdAndPassword() {
		System.out.println("Enter email");
		String em = sc.next();
		System.out.println("Enter password");
		String pw = sc.next();
		Merchant mdb = mdao.verifyMerchantByEmailIdAndPassword(em, pw);
		if (mdb != null) {
			System.out.println(mdb);
		} else {
			System.err.println("Merchant is not found since email id and password is invalid");
		}

	}

// 5.Verify Merchant by phone and password
	
	
	private static void verifyMerchantByPhoneAndPassword() {
		System.out.println("Enter phone");
		long ph = sc.nextLong();
		System.out.println("Enter password");
		String pw = sc.next();
		Merchant mdb = mdao.verifyMerchantByPhoneAndPassword(ph, pw);
		if (mdb != null) {
			System.out.println(mdb);
		} else {
			System.out.println("Merchant is not found since Phone and password is invalid");
		}

	}
	
	
	// 6.Add Product
	
	private static void addProduct() {
		System.out.println("Enter Merchant id to add the product");
		int mid = sc.nextInt();

		Product p = new Product();
		System.out.println("Enter Name of the product");
		p.setName(sc.next());
		System.out.println("Enter Brand of the product");
		p.setBrand(sc.next());
		System.out.println("Enter Category of the product");
		p.setCategory(sc.next());
		System.out.println("Enter cost of the product");
		p.setCost(sc.nextDouble());

		Product pdb = pdao.addProduct(mid, p);
		if (pdb != null) {
			System.out.println("Product is added to the Merchant with an id " + pdb.getId());
		} else {
			System.out.println("Unable o add the product since merchant is inavlid");
		}

	}
	
	// 7.Update Product
	
	private static void updateProduct() {
		System.out.println("Enter the product info---id,name,brand,category,cost");
		Product p = new Product();
		System.out.println("Enter id");
		p.setId(sc.nextInt());
		System.out.println("Enter Name");
		p.setName(sc.next());
		System.out.println("Enter Brand");
		p.setBrand(sc.next());
		System.out.println("Enter category");
		p.setCategory(sc.next());
		System.out.println("Enter cost");
		p.setCost(sc.nextDouble());
		Product pdb = pdao.updateProduct(p);
		if (pdb != null) {
			System.out.println("Product is updated : " + pdb);
		} else {
			System.err.println("Unable to update the product since id is invalid");
		}
	}
	
	// 8.Find Products by id
	
	
	private static void findProductById() {
		System.out.println("Enter Product Id");
		int mid = sc.nextInt();
		List<Product> lpdb = pdao.findProductByMerchantId(mid);
		if (lpdb.size() > 0) {
			for (Product product : lpdb) {
				System.out.println(product);
			}
		} else {
			System.out.println("Product id is inavlid");
		}
	}

	// 9.Find Products by brand and category
	private static void findProductByCategoryAndBrand() {
		System.out.println("Enter the category");
		String category = sc.next();
		List<Product> lpList = pdao.findProductByCategory(category);
		if (lpList.size() > 0) {
			for (Product product : lpList) {
				System.out.println(product);
			}
		} else {
			System.err.println("No Products is found in the database");
		}
	}

	
}
