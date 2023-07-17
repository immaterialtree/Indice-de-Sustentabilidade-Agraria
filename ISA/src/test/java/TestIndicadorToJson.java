
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.isa.ISA;
import com.mycompany.isa.model.Indicador;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author naoki
 */
public class TestIndicadorToJson {
    public static void main(String[] args) {
        Indicador indicador = new Indicador("nome do indicador", new String[] {"grupo a", "grupo b"}, new String[][] {{"item1a", "item2a"},{"item1b"}});
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(indicador);
            System.out.println(json);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ISA.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Indicador i = mapper.readValue(json, Indicador.class);
            System.out.println("success converting json to object");
            System.out.println(i.getItens("grupo a"));
            System.out.println(i.getItens("grupo b"));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(TestLoteToJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
