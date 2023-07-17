
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.isa.ISA;
import com.mycompany.isa.model.Lote;
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
public class TestLoteToJson {
    public static void main(String[] args) {
        Lote lote = new Lote("Marcos de Oliveira", "02", "123465", "123456");
        lote.addScores(513, new Double[]{2d, 4d,6d});
        lote.addScores(444, new Double[]{1d, 3d,5d});
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(lote);
            System.out.println(json);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ISA.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Lote lote2 = mapper.readValue(json, Lote.class);
            System.out.println("success converting json to object");
            System.out.println(Arrays.toString(lote2.getScoresOf(513)));
            System.out.println(Arrays.toString(lote2.getScoresOf(444)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(TestLoteToJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
