/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progsearhcing;
import java.io.IOException;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;
 
/**
 *
 * @author admin
 */
public class Search {
   public void doSearching(String args) throws ParseException, IOException{
        Index indexing = new Index();
        Directory index = indexing.doIndexing();
        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_42);
        String querystr = args;
 
        // "title" arg adalah data yang digunakan untuk pencarian
        // jika tidak ada data yang dispesifikasikan pada query
        Query q = new QueryParser(Version.LUCENE_42, "title", analyzer).parse(querystr);
 
        // Proses pencarian
        int hitsPerPage = 10;
        try (IndexReader reader = DirectoryReader.open(index)) {
            IndexSearcher searcher = new IndexSearcher(reader);
            TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
            searcher.search(q, collector);
            ScoreDoc[] hits = collector.topDocs().scoreDocs;
 
            // Menampilkan hasil pencarian
            System.out.println("Menemukan " + hits.length + " lowongan kerja di "+querystr);
            for(int i=0;i<hits.length;++i) {
              int docId = hits[i].doc;
              Document d = searcher.doc(docId);
              System.out.println((i + 1) + ". " + d.get("isbn") + "\t" + d.get("title"));
            }
        }
    }
 
}