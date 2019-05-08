/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progsearhcing;

import java.util.Scanner;
import java.io.IOException;
import java.util.Scanner;
import org.apache.lucene.queryparser.classic.ParseException;

/**
 *
 * @author admin
 */
public class ProgSearhcing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ParseException {
        String kota;
        System.out.print("Masukkan nama kota: ");
        Scanner in = new Scanner(System.in);
        kota = in.nextLine();
        Search search = new Search();
        search.doSearching(kota);
    }

}
