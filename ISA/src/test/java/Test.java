
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.isa.ISA;
import com.mycompany.isa.model.Lote;
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
public class Test {
    public static void main(String[] args) {
        Lote lote = new Lote("Marcos de Oliveira", "02", "123465", "");
        lote.addScores(513, new Double[]{2d, 4d,6d});
        lote.addScores(444, new Double[]{1d, 3d,5d});
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(lote));
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            Logger.getLogger(ISA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
