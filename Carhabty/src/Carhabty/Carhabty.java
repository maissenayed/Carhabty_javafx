package Carhabty;

import Entities.Offre;
import Entities.User;
import Services.OffreServices;
import Services.UserServices;
import java.security.SecureRandom;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Random;

/**
 * Created by GARCII on 3/12/2017.
 */
public class Carhabty {



    public static Connection con;


    public static void main(String[] args) {

        
        OffreServices offreService = new OffreServices();
       // Offre o1 = new Offre("garci","amine",18,20,LocalDate.now());
       // offreService.findALL().forEach(System.out::print);
        //offreService.add(o1);
      
      
        UserServices userService = new UserServices();
        User u = new User(39,"drawii","drawi@d.tn","1111","zaaaab","yousef","25532465","9 Rue newton","lavage qui rit","lavage","025HG");
        User u1 = new User("daflex","amine@garci.com","111","garous","amine","25532465","9 Rue newton");
        User u2 = new User(37,"darwi","dra@f.tn","222","zaab","zaboub","25666","8 rue");
        User u3 = new User("daflexxsgdfgx","amine@dgtrrdggarci.fr","111","garous","amine","25532465","9 Rue newton");
       // userService.add(u);
      //  userService.update(u);
     //   userService.remove(u);
    //   userService.findALL().forEach(System.out::println);
    
    //   userService.isfound(u);
      //  userService.getListePartenaire().forEach(System.out::println);
        
        
      
      
}


  


}