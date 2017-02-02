import java.util.List;
import java.util.Map;

/**
 * Created by gabri on 2/2/2017.
 */
public class Persona {

    private final long id;
    private final String nombre;

    public Persona(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Persona> findAll(Map<String, String> map){

        return  null;
    }

}