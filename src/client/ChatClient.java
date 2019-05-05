// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import common.*;
import java.sql.Date;
import models.Customer;
import models.User;
import models.CustomerCard;
import ocsf.client.AbstractClient;
import java.io.*;
import java.sql.ClientInfoStatus;

/**
 * xv This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient {
	// Instance variables **********************************************

	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */
	ChatIF clientUI;

	/**
	 * The Login ID of the user.
	 */
	String loginID;
	User usr = new User("", "");
	CustomerCard customercard = new CustomerCard(-1, "x", 0, "x", "x");
	Customer customer = new Customer("", "", 0, 0, customercard);

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host
	 *            The server to connect to.
	 * @param port
	 *            The port number to connect on.
	 * @param clientUI
	 *            The interface type variable.
	 */

	public ChatClient(String host, int port, ChatIF clientUI) throws IOException {
		super(host, port); // Call the superclass constructor
		this.clientUI = clientUI;
		openConnection();
		this.loginID = "";
		sendToServer("#login: ANONYMOUS");
	}

	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param loginID
	 *            The user ID.
	 * @param host
	 *            The server to connect to.
	 * @param port
	 *            The port number to connect on.
	 * @param clientUI
	 *            The interface type variable.
	 */

	public ChatClient(String loginID, String host, int port, ChatIF clientUI) throws IOException {
		super(host, port); // Call the superclass constructor
		this.clientUI = clientUI;
		this.loginID = loginID;
		openConnection();

	}

	// Instance methods ************************************************

	/**
	 * This method handles all data that comes in from the server.
	 * 
	 * @param msg
	 *            The message from the server.
	 */
	public void handleMessageFromServer(Object msg) {
		if (msg.toString().charAt(0) == '@') {
			String[] splited = msg.toString().split("\\s+");
			this.usr.setUserID(splited[1]);
			this.usr.setPassword(splited[2]);
			this.usr.setRegisterDate(splited[3]);
			handleMessageFromClientUI("@" + splited[1]);

		}
		if (msg.toString().charAt(0) == '%') {
			String[] splited = msg.toString().split("\\s+");
			this.customer.setCusID(Integer.parseInt(splited[2]));
			this.customer.setPurchases(Integer.parseInt(splited[3]));
			this.customer.setPassword(this.usr.getPassword());
			this.customer.setUserID(this.usr.getUserID());
			String sql = "!SELECT * FROM CustomersCard WHERE cusID=" + this.customer.getCusID();
			try {
				sendToServer(sql);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (msg.toString().charAt(0) == '!') {
			String[] splited = msg.toString().split("\\s+");
			this.customercard.setCusID(Integer.parseInt(splited[1]));
			this.customercard.setCustomerName(splited[2] + " " + splited[3]);
			this.customercard.setAge(Integer.parseInt(splited[4]));
			this.customercard.setPhone(splited[5]);
			this.customercard.setEmail(splited[6]);
			handleMessageFromClientUI("@" + splited[1]);
		}
		if (msg.toString().charAt(0) == '1') {
			clientUI.display("Your Purchase Has Been Made Successfully");
			Loggin();
		}
		// clientUI.display(msg.toString());

	}

	/**
	 * This method handles all data coming from the UI
	 *
	 * @param message
	 *            The message from the UI.
	 */
	public void handleMessageFromClientUI(String message) {

		// detect commands
		if (message.charAt(0) == '#') {
			runCommand(message);
		}
		if (message.charAt(0) == '!') {
			this.loginID = message.substring(1);
			String msg = "@Select * From Users  WHERE userID =" + this.loginID;
			try {
				sendToServer(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (message.charAt(0) == '@') {
			String splited = message.replace("@", "");
			if (splited.equals(this.usr.getUserID()))
				clientUI.display("please enter $ password:");
		}
		if (message.charAt(0) == '$') {
			message = message.replace("$", "");
			if (message.equals(this.usr.getPassword())) {
				clientUI.display("login successful");
				message = "%Select * From Customers  WHERE userID =" + this.usr.getUserID();
				try {
					sendToServer(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Loggin();
		}
		if (message.equals("1")) {
			String msg = "\nCstomer ID: " + this.customer.getCusID() + "\n" + "Customer Name: "
					+ this.customercard.getCustomerName() + "\n" + "Purchases: "
					+ Integer.toString(this.customer.getPurchases()) + "\n" + "Age: "
					+ Integer.toString(this.customercard.getAge()) + "\n" + "Phone: " + this.customercard.getPhone()
					+ "\n" + "Email: " + this.customercard.getEmail();
			clientUI.display(msg);
			Loggin();
		}
		if (message.equals("2")) {
			int newAmountOfPurchases = this.customer.getPurchases() + 1;
			this.customer.setPurchases(newAmountOfPurchases);
			try {
				sendToServer("$UPDATE Customers SET purchases = purchases + " + 1 + " WHERE userID="
						+ this.customer.getUserID());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * This method executes client commands. Benjamin Bergman, Oct 22, 2009
	 *
	 * @param message
	 *            string from the client console
	 */
	private void runCommand(String message) {
		// a bunch of ifs
		if (message.equalsIgnoreCase("#quit")) {
			quit();
		} else if (message.equalsIgnoreCase("#logoff")) {
			try {
				closeConnection();
			} catch (IOException e) {
			}
			clientUI.display("You have logged off.");
		} else if (message.toLowerCase().startsWith("#setport")) {
			// requires the command, followed by a space, then the port number
			try {
				int newPort = Integer.parseInt(message.substring(9));
				setPort(newPort);
				// error checking for syntax a possible addition
				clientUI.display("Port changed to " + getPort());
			} catch (Exception e) {
				System.out.println("Unexpected error while setting client port!");
			}
		} else if (message.toLowerCase().startsWith("#sethost")) {
			setHost(message.substring(9));
			clientUI.display("Host changed to " + getHost());
		} else if (message.toLowerCase().startsWith("#login")) {
			if (isConnected()) {
				clientUI.display("You must logout before you can login.");
				return;
			}
			// login
			// if no id, login anonymous
			loginID = message.substring(7);
			try {
				openConnection();
				sendToServer("#login " + loginID);
			} catch (Exception e) {
				clientUI.display("Connection could not be established.");
			}
		} else if (message.equalsIgnoreCase("#gethost")) {
			clientUI.display("Current host: " + getHost());
		} else if (message.equalsIgnoreCase("#getport")) {
			clientUI.display("Current port: " + Integer.toString(getPort()));
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}

	/**
	 * Reacts to a closed connection while waiting for messages from the server.
	 * Overrides method in <code>AbstractClient</code>. Added by Benjamin
	 * Bergman, Oct 22, 2009.
	 *
	 * @param exception
	 *            the exception raised.
	 */
	protected void connectionException(Exception exception) {
		clientUI.display("The connection to the Server (" + getHost() + ", " + getPort() + ") has been disconnected");
	}

	public void Loggin() {

		clientUI.display("\n      choose your action: \n" + "------------------------------ \n"
				+ "1) see your detalis\n" + "2) purchase map" + "\n ------------------------------ \n");

	}
}

// End of ChatClient class
