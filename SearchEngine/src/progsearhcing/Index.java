/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progsearhcing;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

/**
 *
 * @author Herdi Naufal
 */
public class Index {

    public Directory doIndexing() {
        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_42);

        // Membuat index baru dalam bentuk RAMDirectory
        Directory index = new RAMDirectory();

        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_42, analyzer);
        try {
            IndexWriter w = new IndexWriter(index, config);
            //Menambahkan dokumen yang di-index
            addDoc(w, "Semarang", "Lowongan Android Programmer");
            addDoc(w, "Semarang", "Lowongan User Interface Desinger");
            addDoc(w, "Bandung", "Dicari Developer Java");
            addDoc(w, "Jakarta", "Dicari Web Designer");
            addDoc(w, "Jakarta", "Lowongan Android UI Designer");
            addDoc(w, "Jakarta", "Lowongan Developer Apple");
            addDoc(w, "Jakarta", "Dicari Developer Dot Net");
            addDoc(w, "Jakarta", "Dicari JSP Developer");
            addDoc(w, "Bandung", "Lowongan Data Analis");
            addDoc(w, "Bogor", "Lowongan Mobile Application Developer");
            addDoc(w, "Bandung", "Dicari Developer PHP");
            addDoc(w, "Tangerang", "Dicari Web Designer familiar dengan CodeIgniter");
            addDoc(w, "Jogjakarta", "Lowongan Windows Phone Development");
            addDoc(w, "Semarang", "Lowongan User Interface Desingner");
            addDoc(w, "Bandung", "Dicari Developer Java Enterprise");
            addDoc(w, "Bandung", "Dicari Master Java");
            addDoc(w, "Surabaya", "Dicari Developer Cloud Application (Java)");
            addDoc(w, "Semarang", "Dibutuhkan developer Linux ");

            w.close();
        } catch (IOException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }

        return index;
    }

    //method yang digunakan untuk menambahkan dokumen ke index
    private static void addDoc(IndexWriter w, String city, String job) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("title", city, Field.Store.YES));
        doc.add(new StringField("isbn", job, Field.Store.YES));
        w.addDocument(doc);
    }

}
