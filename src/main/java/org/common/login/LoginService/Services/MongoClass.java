package org.common.login.LoginService.Services;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.common.login.LoginService.Model.LoginDAO;
import org.joda.time.DateTime;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Date;

import static com.mongodb.client.model.Filters.*;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;

public class MongoClass {
	MongoClient client = new MongoClient();
	MongoDatabase db = client.getDatabase("messsenger");

	public MongoClass() {

	}


	public String updateLogIn(LoginDAO login) {
		
		MongoCollection<Document> coll = db.getCollection("login");
		coll.updateOne(eq("username", login.getUsername()),
				new Document("$set", new Document("loginstatus", "YES").append("lastlogindate",
						new BasicDBObject("date", new Date()))));
		return "status updated to login";

	}

	public String updateLogoff(LoginDAO login) {

		MongoCollection<Document> coll = db.getCollection("login");
		coll.updateOne(eq("username", login.getUsername()),
				new Document("$set", new Document("loginstatus", login.getLoginstatus()).append("lastlogoffdate",
						new BasicDBObject("date", new Date()))));

		return "Status updated to log off";

	}

	public String verifyLogin(LoginDAO login) {
		MongoCollection<Document> coll = db.getCollection("login");
		Document getLogin = new Document();
		getLogin.append("username", login.getUsername());
		getLogin.append("password", login.getPassword());
		String loginOrNot = login.getLoginstatus();
		
		
			Document foundLogin = (Document) coll.find(getLogin).first();
			
			if (foundLogin.isEmpty()) {
				return "username password not found";

			} 
			else {
				
				String loginstatus = (String) foundLogin.get("loginstatus");
				if (loginstatus.equalsIgnoreCase("YES")) {
					return "Already logged in";
				} else
					return updateLogIn(login);
			}
		
	}

	public String insertLogin(LoginDAO login){
		System.out.println(login);
		MongoCollection<Document> coll = db.getCollection("login");
		Document prod = new Document("username",login.getUsername()).append("password",login.getPassword())
				.append("loginstatus","NO").append("lastlogindate", new BasicDBObject("date", new Date()))
				.append("lastlogoffdate", new BasicDBObject("date", new Date()));
		
		coll.insertOne(prod);
		return "Inserted 1 record";
	}
	public List<LoginDAO> getAllMessages() {

		System.out.println("into get all messages");
		MongoCollection<Document> coll = db.getCollection("login");
		List<Document> list = new ArrayList<>();
		LoginDAO login = new LoginDAO();
		List<LoginDAO> listnew = new ArrayList<>();

		list = coll.find().into(new ArrayList<Document>());

		for (int i = 0; i < coll.count(); i++) {
			Document doc = list.get(i);
			String username = doc.getString("username");
			String password = doc.getString("password");
			System.out.println(doc);
			listnew.add(new LoginDAO(username, password));
		}
		return listnew;
	}

}// end of .java
