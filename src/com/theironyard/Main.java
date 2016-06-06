package com.theironyard;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;

public class Main {

    static User user;

    public static void main(String[] args) {
        /** GET: Routes returns html
         ** POST: Routes modify the server(data)
         */
        Spark.init();
        Spark.get(
                 "/",
                 (request, response) -> {
                     HashMap m = new HashMap();
                     if (user == null) {
                         return new ModelAndView(m, "loginpage.html");
                     }
                     else {
                         m.put("name", user.name);
                         return new ModelAndView(m, "home.html");
                     }
                 },
                 new MustacheTemplateEngine()
        );
        Spark.post(
                 "/login",
                 (request, response) -> {
                    String username = request.queryParams("username");
                     user = new User(username);
                     response.redirect("/");
                     return "";
                 }
        );
        Spark.post(
                "/logout",
                (request, response) -> {
                   user = null;
                    response.redirect("/");
                    return "";
                }
        );
    }
}
