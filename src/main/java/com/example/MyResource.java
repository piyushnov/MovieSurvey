package com.example;

import com.example.datastore.PostgreSQL;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.UUID;




import static com.example.datastore.PostgreSQL.*;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("MyMovies")
public class MyResource {
    private static Connection c;
    private static Statement stmt = null;
    private static int counter = 0;
    private Client client;

    /*public static synchronized int nextId() {
        return ++counter;
    }*/

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Hello, Heroku!";
    }

    @GET
    @Path("/{param}")
    public Response getMsg(@PathParam("param") String msg) {

        String output = "Jersey say : " + msg;

        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/getUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Track getTrackInJSON() {


        Track track = new Track();
        track.setName("Piyush");
        track.setMail("piyush.nov@gmail.com");
        return track;
    }


    @GET
    @Path("/movies/{movie-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response  movieSearch(@PathParam("movie-name") String movie,
                                      @QueryParam("token") String token) throws SQLException, JSONException {
        String res = "movie" + movie + " token " + token;

        Connection c = getConnection();
        PreparedStatement statement = c.prepareStatement("select * from movie_user where uid = ?");
        statement.setString(1, token);
        ResultSet s = statement.executeQuery();

        s.next();
        int rows = s.getRow();
        System.out.println(" no  of rows " + rows);
        JSONObject output = null;
        JSONObject t =new JSONObject();
        t.put("Token","Invalid.");
        t.put("message","Please Signup first before you can use this service.");
        String result=t.toString();
        if (rows != 0) {

            com.sun.jersey.api.client.Client client = com.sun.jersey.api.client.Client.create();

            WebResource webResource = client
                    .resource("http://www.omdbapi.com/?t=" + movie + "&plot=short&r=json");

            ClientResponse response = webResource.accept("application/json")
                    .get(ClientResponse.class);
            if (response.getStatus() == 200) {

                output = response.getEntity(JSONObject.class);
                System.out.println(output.toString());
                result=output.toString();

            }
        }

            statement.close();
            closeConnection();

            return Response.status(200).entity(result).build();

    }

    @POST
    @Path("/search_movie")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserId createTrackInJSON(Track track) throws SQLException, JSONException {
        Statement stmt = null;
        String uniqueID = UUID.randomUUID().toString();
        String result = "Track saved : " + track;
        UserId userId = new UserId();
        userId.setId(uniqueID);
        Connection c = getConnection();
        System.out.println("Opened database successfully");
        String Name = track.getName();
        String Mail = track.getMail();

        stmt = c.createStatement();
        String sql = "INSERT INTO Movie_user (uid,name,emailid,mobile) "
                + "VALUES ('" + uniqueID + "','" + Name + "','" + Mail + "','7406633631' );";
        stmt.executeUpdate(sql);

        stmt.close();

        closeConnection();
        Response.status(201).entity(result).build();
        return userId;

    }



}
