
import com.eqa.formula.DataBase.GetEHE;
import java.util.Hashtable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abdullah_PC
 */
public class Grupos {

    public Grupos(Hashtable<Integer, GetEHE> store) {
        GetEHE e;
        for(int i=0;i<store.size();i++){
         e=store.get(i);
         System.out.println(e.toJson());
        }

    }
    
}
