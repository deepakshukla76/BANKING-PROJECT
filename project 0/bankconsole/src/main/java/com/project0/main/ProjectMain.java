package com.project0.main;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.project0.dao.ProjectDAO;
import com.project0.dao.impl.ProjectDaoImpl;
import com.project0.exception.BankingException;
import com.project0.model.Project;
import com.project0.model.Transaction;

public class ProjectMain {
	private static Logger log = Logger.getLogger(ProjectMain.class);

	public static void main(String[] args) throws BankingException {

		Scanner sc = new Scanner(System.in);
		int ch = 0;
		do {
			log.info("Welcome to Deepak BankingApp V1.0");
			log.info("*********************************");
			log.info("Select the Option-");
			log.info("*******************************************");
			log.info("1)Login");
			log.info("2)Register");
			log.info("3)Exit");
			log.info("Enter your Choice 1-3");
			log.info("*****************************************************");
			log.info("If you Don't have registered yet then Register yourself");
			try {
				ch = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {

			}
			switch (ch) {
			case 1:
				int s = 0;
				do {
					log.info("1)Login as Customer: ");
					log.info("2)Login as Employee: ");
					log.info("3)Main Menu: ");
					log.info("4)Exit: ");
					log.info("Enter your Choice 1-4");

					try {
						s = Integer.parseInt(sc.nextLine());
					} catch (NumberFormatException e) {

					}
					switch (s) {
					case 1:
						int c = 0;
						String str = null;
						log.info("Customer Login Screen");
						log.info("---------------------------------------------------------------------------------------");
						log.info("Enter your Login Id : \n");
						String LoginId = sc.nextLine();
						try {
							ProjectDAO projectDAO = new ProjectDaoImpl();
							str = projectDAO.getCustomerByEmailId(LoginId);
							if (str != null) {
								log.info("Enter Password: \n");
								String Password = sc.nextLine();
								String pass = null;
								try {
									pass = projectDAO.getCustomerByPassword(Password);
									if (pass != null) {
										System.out.println("Login Successfully \n ");
										do {
											log.info("1)Transfer ");
											log.info("2)Withdraw  ");
											log.info("3)Deposit  ");
											log.info("4)View Statement ");
											log.info("5)Exit");
											log.info("Enter your Choice 1-5");
											log.info("Choose the Menu ");

											try {
												sc.nextLine();
												c = Integer.parseInt(sc.nextLine());
											} catch (NumberFormatException e) {

											}
											switch (c) {
											case 1:
												log.info("Transfer");
												log.info("---------------------------------------------------------------------------------------\n");
												int id1 = projectDAO.getCustomerByCustomerId(LoginId);
												log.info("Enter the Other user Id : ");
												String loginIdother=sc.nextLine();
												if (projectDAO.getCheckForAccount(id1) == false)
													log.info("You don't have an Account");
												else {
													log.info("Transfer Money");
													log.info("---------------------------------------------------------------------------------------\n");
													log.info("Enter Amount to Transfer : \n");
													float amount = sc.nextFloat();
													float total = projectDAO.getTransactionTotalAmount(id1);
													if (amount > total)log.info("Low amount");
													else {
	
														int id7 = projectDAO.getCustomerByCustomerId(loginIdother);
														if (projectDAO.getCheckForAccount(id7) == false)
															log.info("You don't have an Account");
														else {
															log.info("Credit Money");
															log.info(
																	"---------------------------------------------------------------------------------------\n");
															float total1= projectDAO.getTransactionTotalAmount(id7);

															Transaction transaction = new Transaction(0f, 0f, amount,
																	amount + total1, id7);
															if (transaction != null) {
																transaction = projectDAO.openAccount(transaction);
																System.out.println("Account with id " + id7
																		+ " details after transfer are : ");
																System.out.println(transaction);
															}

														}

													}

												}

												break;
											case 2:
												log.info("Withdraw ");
												log.info(
														"---------------------------------------------------------------------------------------\n");
												int id3 = projectDAO.getCustomerByCustomerId(LoginId);
												if (projectDAO.getCheckForAccount(id3) == false)
													log.info("You don't have an Account");
												else {
													log.info("Debit Money");
													log.info(
															"---------------------------------------------------------------------------------------\n");
													log.info("Enter Amount to withdraw : \n");
													float amount = sc.nextFloat();
													float total = projectDAO.getTransactionTotalAmount(id3);
													if (amount > total)
														log.info("less Amount");
													Transaction transaction = new Transaction(0f, amount, 0f,
															total - amount, id3);
													if (transaction != null) {
														transaction = projectDAO.openAccount(transaction);
														System.out.println("Account with id " + id3
																+ " details after deposit are : ");
														System.out.println(transaction);
													}

												}
												break;
											case 3:
												log.info("Deposit");
												log.info(
														"---------------------------------------------------------------------------------------\n");
												int id2 = projectDAO.getCustomerByCustomerId(LoginId);
												if (projectDAO.getCheckForAccount(id2) == false)
													log.info("You don't have an Account");
												else {
													log.info("Credit Money :");
													log.info(
															"---------------------------------------------------------------------------------------\n");
													log.info("Enter Amount to Deposit : \n");
													float amount = sc.nextFloat();
													float total = projectDAO.getTransactionTotalAmount(id2);

													Transaction transaction = new Transaction(0f, 0f, amount,
															amount + total, id2);
													if (transaction != null) {
														transaction = projectDAO.openAccount(transaction);
														System.out.println("Account with id " + id2
																+ " details after deposit are : ");
														System.out.println(transaction);
													}

												}
												break;
											case 4:
												log.info("View Statement");
												log.info(
														"---------------------------------------------------------------------------------------");
												int id4 = projectDAO.getCustomerByCustomerId(LoginId);
												if (projectDAO.getCheckForAccount(id4) == false)
													log.info("You don't have an Account");
												else {
													List<Transaction> transactionList = null;
													try {
														transactionList = projectDAO.getViewStatement(id4);
														if (transactionList != null && transactionList.size() > 0) {
															System.out.println("We have " + transactionList.size()
																	+ " no of product/s in DB details are");
															for (Transaction transaction : transactionList) {
																System.out.println(transaction);
															}
														}
													} catch (BankingException e) {
														System.out.println(e.getMessage());
													}

												}
												break;
											case 5:
												log.info("Logout");
												log.info("---------------------------------------------------------------------------------------");
												break;
											default:
												log.warn("Invalid Choice... Please enter input between 1-5");
												break;
											}
										} while (c != 5);
									}
								} catch (BankingException e) {
									System.out.println(e.getMessage());
								}
							}
						} catch (BankingException e) {
							System.out.println(e.getMessage());
						}

						break;
					case 2:
						int e = 0;
						String emp = null;
						log.info("Employee Login Screen : \n");
						log.info(
								"---------------------------------------------------------------------------------------");
						log.info("Enter your Login Id : \n");
						String empLoginId = sc.nextLine();
						try {
							ProjectDAO projectDAO = new ProjectDaoImpl();
							str = projectDAO.getEmployeeByEmailId(empLoginId);
							if (str != null) {
								log.info("Enter Password: \n");
								String Password = sc.nextLine();
								String pass = null;
								try {
									pass = projectDAO.getEmployeeByPassword(Password);
									if (pass != null) {
										System.out.println("Login Successfully,Welcome Employee");
										do {
											log.info("1)Open Account    : ");
											//log.info("2)Update Record   : ");
										//	log.info("3)Read Details    : ");
										//	log.info("4)Delete Account  : ");
											log.info("2)Main Menu       : ");
											log.info("3)Exit            : ");
											log.info("Enter your Choice 1-3");
											log.info("Choose the Menu : ");

											try {
												e = Integer.parseInt(sc.nextLine());
											} catch (NumberFormatException n) {

											}
											switch (e) {
											case 1:
												log.info("Open Account : ");
												log.info(
														"---------------------------------------------------------------------------------------");
												log.info("Enter Customer Login Id : \n");
												String empLoginId1 = sc.nextLine();
												int id = projectDAO.getCustomerByCustomerId(empLoginId1);
												if (projectDAO.getCheckForAccount(id) == true)
													log.info("You have already an Account");
												else {
													log.info("Open Account :");
													log.info(
															"---------------------------------------------------------------------------------------\n");
													log.info("Enter Starting Amount : \n");
													float amount = sc.nextFloat();
													if (amount >= 500) {
														Transaction transaction = new Transaction(0f, 0f, amount,
																amount, id);
														if (transaction != null) {
															transaction = projectDAO.openAccount(transaction);
															projectDAO.updateCustomerId(id, id);
															System.out.println(
																	"Account with id " + id + " found details are : ");
															System.out.println(transaction);
														}
													} else {
														log.info("Minimum amount to open Bank Account is Rs 500");
													}
												}
												break;
//											case 2:
//												log.info("Update Record : ");
//												log.info("---------------------------------------------------------------------------------------");
//
//												break;
//											case 3:
//												log.info("Read Account details: ");
//												log.info(	"---------------------------------------------------------------------------------------");
//
//												break;
//											case 4:
//												log.info("Delete Account  :");
//												log.info(
//														"---------------------------------------------------------------------------------------");
//												break;
											case 2:
												log.info("Main Menu :");
												log.info("---------------------------------------------------------------------------------------");
												break;
											case 3:
												log.info("Exit   :");
												log.info(
														"---------------------------------------------------------------------------------------");
												break;
											default:
												log.warn("Invalid Choice... Please enter input between 1-3");
												break;
											}
										} while (e != 3);
									}
								} catch (BankingException b) {
									System.out.println(b.getMessage());
								}
							}
						} catch (BankingException b) {
							System.out.println(b.getMessage());
						}

						break;
					case 3:
						log.info("\n\nGoing to Main Menu............\n\n");
						log.info(
								"---------------------------------------------------------------------------------------");
						break;
					case 4:
						log.info("Exit");
						break;
					default:
						log.warn("Invalid Choice... Please enter input between 1-6");
						break;
					}
				} while (s != 4);
				break;
			case 2:
				log.info("Register :\n\n-");
				log.info("Enter Name -:");
				String name = sc.nextLine();
				log.info("Enter Contact Number -:");
				int no = Integer.parseInt(sc.nextLine());
				log.info("Enter Email:-");
				String Email = sc.nextLine();
				log.info("Enter City :-");
				String city = sc.nextLine();
				log.info("Enter State :-");
				String state = sc.nextLine();
				log.info("Enter Country  :-");
				String Country = sc.nextLine();
				log.info("Enter Pan Number:-");
				String pn = sc.nextLine();
				log.info("Create Password:-");
				String pd = sc.nextLine();
				Project project = new Project(name, no, Email, city, state, Country, pn, pd);

				ProjectDAO projectDAO = new ProjectDaoImpl();
				try {
					project = projectDAO.createAccount(project);
					if (project.getCustomerId() != 0) {
						log.info("Account Created successfully with below details ");
						log.info(project);
					}
				} catch (BankingException e) {
					log.info(e.getMessage());
				}

				break;
			case 3:
				log.info("Thanks for using our App.. See you soon. :)");

				break;
			default:
				log.warn("Invalid Choice... Please enter input between 1-6");
				break;
			}
		} while (ch != 3);
		sc.close();

	}
}
