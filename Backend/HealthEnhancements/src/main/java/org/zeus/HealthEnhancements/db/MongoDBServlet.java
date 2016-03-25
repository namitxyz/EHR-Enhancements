package org.zeus.HealthEnhancements.db;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;


@WebServlet(name = "MongoDBServlet")
public class MongoDBServlet extends HttpServlet {
    private Mongo mongo;
    private DB mongoDB;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
	String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
        String sport = System.getenv("OPENSHIFT_MONGODB_DB_PORT");
        String db = System.getenv("OPENSHIFT_APP_NAME");
        if(db == null)
            db = "mydb";
        String user = System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");
        String password = System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");
        int port = Integer.decode(sport);

        try {
            mongo = new Mongo(host , port);
        } catch (UnknownHostException e) {
            throw new ServletException("Failed to access Mongo server", e);
        }
        mongoDB = mongo.getDB(db);
        if(mongoDB.authenticate(user, password.toCharArray()) == false) {
            throw new ServletException("Failed to authenticate against db: "+db);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cmd = request.getParameter("cmd");
        handleCmd(cmd, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cmd = request.getParameter("cmd");
        handleCmd(cmd, response);
    }
    protected void handleCmd(String cmd, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");
        if(cmd == null) {
            // Initialize the db
            
        }
        else if(cmd.equalsIgnoreCase("query")) {

        }
        else {
            pw.format("<h1>Error, on %s</h1>\nNo support for handling the command exists yet", cmd);
        }
        pw.flush();
    }

    protected void handleQuery(PrintWriter pw, String i) {
        try {
            mongoDB.requestStart();
            //Handle the query here..
        } finally {
            mongoDB.requestDone();
        }
    }
}