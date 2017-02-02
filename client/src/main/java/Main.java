import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

/**
 * Created by gabri on 2/2/2017.
 */
public class Main {

    public static void main(String[] args) {

        port(9090);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Object[]> object = restTemplate.getForEntity("http://localhost:8080/persona", Object[].class);

        List<Persona> personas = new ArrayList<>();

        List<Object> lists = Arrays.asList(object.getBody());
        for(Object simpleObject : lists){

            Map<String, String> newObject = (Map<String, String>) simpleObject;

            String id = String.valueOf(newObject.get("id"));

            String nombre = newObject.get("nombre");

            Persona persona = new Persona(Integer.parseInt(id), nombre);

            personas.add(persona);
        }

        for(Persona persona : personas){

            System.out.println(persona.getId() + " " + persona.getNombre());

        }

        get("/findall", (request, response) -> {

            Map<String, Object> model = new HashMap<>();
            model.put("valores", personas);
            return new ModelAndView(model, "findall.hbs");

        }, new HandlebarsTemplateEngine());

        get("/", (request, response) -> {

            return  new ModelAndView(null, "index.hbs");

        }, new HandlebarsTemplateEngine());
    }

}
