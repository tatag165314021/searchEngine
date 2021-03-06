/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengine;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class TextFileIndexer extends javax.swing.JFrame {

    private static StandardAnalyzer analyzer = new StandardAnalyzer();
    private IndexWriter writer;
    private ArrayList queue = new ArrayList();

    /**
     * Creates new form TextFileIndexer
     */
    public TextFileIndexer() {
        initComponents();
    }

    TextFileIndexer(String indexDir) throws IOException {
        // the boolean true parameter means to create a new index everytime,
        // potentially overwriting any existing files there.
        FSDirectory dir = FSDirectory.open(new File(indexDir));
        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer);
        writer = new IndexWriter(dir, config);
    }

    public void indexFileOrDirectory(String fileName) throws IOException {
        //===================================================
        //gets the list of files in a folder (if user has submitted
        //the name of a folder) or gets a single file name (is user
        //has submitted only the file name)
        //===================================================
        addFiles(new File(fileName));
        int originalNumDocs = writer.numDocs();
        for (Iterator it = queue.iterator(); it.hasNext();) {
            File f = (File) it.next();
            FileReader fr = null;
            try {
                Document doc = new Document();
                //===================================================
                // add contents of file
                //===================================================
                fr = new FileReader(f);
                doc.add(new TextField("contents", fr));
                doc.add(new StringField("path", f.getPath(), Field.Store.YES));
                doc.add(new StringField("filename", f.getName(), Field.Store.YES));
                writer.addDocument(doc);
                System.out.println("Added: " + f);
            } catch (Exception e) {
                System.out.println("Could not add: " + f);
            } finally {
                fr.close();
            }
        }

        int newNumDocs = writer.numDocs();
        System.out.println("");
        System.out.println("************************");
        System.out.println((newNumDocs - originalNumDocs) + " documents added.");
        System.out.println("************************");
        queue.clear();
    }

    private void addFiles(File file) {
        if (!file.exists()) {
            System.out.println(file + " does not exist.");
        }
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                addFiles(f);
            }
        } else {
            String filename = file.getName().toLowerCase();
            //===================================================
            // Only index text files
            //===================================================
            if (filename.endsWith(".htm") || filename.endsWith(".html")
                    || filename.endsWith(".xml") || filename.endsWith(".txt")) {
                queue.add(file);
            } else {
                System.out.println("Skipped " + filename);
            }
        }
    }

    public void closeIndex() throws IOException {
        writer.close();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cariTxt = new javax.swing.JTextField();
        cariBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        dirTxt = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        adddDoc = new javax.swing.JMenuItem();
        info = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        infocari = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cariTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariTxtActionPerformed(evt);
            }
        });

        cariBtn.setText("cari");
        cariBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("Cari Lagu");

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}

            },
            new String [] {
                "no", "Path", "Score"
            }
        ));
        jScrollPane1.setViewportView(tabel);

        dirTxt.setEditable(false);

        jMenu1.setText("File");

        adddDoc.setText("add Doc");
        adddDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adddDocActionPerformed(evt);
            }
        });
        jMenu1.add(adddDoc);

        jMenuBar1.add(jMenu1);

        info.setText("Info");
        info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoActionPerformed(evt);
            }
        });

        jMenuItem1.setText("cara export");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        info.add(jMenuItem1);

        infocari.setText("cara cari");
        infocari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infocariActionPerformed(evt);
            }
        });
        info.add(infocari);

        jMenuBar1.add(info);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1120, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(cariTxt)
                        .addGap(18, 18, 18)
                        .addComponent(cariBtn)))
                .addGap(44, 44, 44))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(96, Short.MAX_VALUE)
                    .addComponent(dirTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 992, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(110, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cariTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariBtn)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(dirTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(363, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cariBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariBtnActionPerformed
        System.out.println("Enter the path where the index will be created: (e.g. /tmp/index or c:\\temp\\index)");
        String indexLocation = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = "C:/Users/User/Documents/NetBeansProjects/algoritma-pemrograman/Cari/index/";
        TextFileIndexer indexer = null;
        try {
            indexLocation = s;
            indexer = new TextFileIndexer(s);
        } catch (Exception ex) {
            System.out.println("Cannot create index..." + ex.getMessage());
            System.exit(-1);
        }

        //===================================================
        //read input from user until he enters q for quit
        //===================================================
        try {
            System.out.println("Enter the full path to add into the index (q=quit): (e.g. /home/ron/mydir or c:\\Users\\ron\\mydir)");
            System.out.println("[Acceptable file types: .xml, .html, .html, .txt]");
            s = "C:/Users/User/Documents/NetBeansProjects/algoritma-pemrograman/Cari/LAGUANAK";

            //try to add file into the index
            indexer.indexFileOrDirectory(s);
            indexer.indexFileOrDirectory(dirTxt.getText());
            s = "q";
        } catch (Exception e) {
            System.out.println("Error indexing " + s + " : " + e.getMessage());
        }

        try {
            //===================================================
            //after adding, we always have to call the
            //closeIndex, otherwise the index is not created
            //===================================================
            indexer.closeIndex();
        } catch (IOException ex) {
            Logger.getLogger(TextFileIndexer.class.getName()).log(Level.SEVERE, null, ex);
        }

        //=========================================================
        // Now search
        //=========================================================
        IndexReader reader = null;
        try {
            reader = DirectoryReader.open(FSDirectory.open(new File(indexLocation)));
        } catch (IOException ex) {
            Logger.getLogger(TextFileIndexer.class.getName()).log(Level.SEVERE, null, ex);
        }
        IndexSearcher searcher = new IndexSearcher(reader);
        TopScoreDocCollector collector = TopScoreDocCollector.create(50, true);

        s = "";

        try {

            s = cariTxt.getText();

            Query q = new QueryParser("contents", analyzer).parse(s);
            searcher.search(q, collector);
            ScoreDoc[] hits = collector.topDocs().scoreDocs;

            DefaultTableModel model = (DefaultTableModel) tabel.getModel();
            int baris = model.getRowCount();
            for (int i = 0; i < baris; i++) {
                model.removeRow(0);
            }
            for (int i = 0; i < collector.getTotalHits(); i++) {
                int docId = hits[i].doc;
                Document d = searcher.doc(docId);
                Object[] item = {(i + 1), d.get("path"), hits[i].score};
                model.addRow(item);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error searching " + s + " : " + e.getMessage());
        } finally {
            collector = TopScoreDocCollector.create(50, true);
        }

        // hapus seluruh file index pencarian
        File dir = new File("C:/Users/User/Documents/NetBeansProjects/algoritma-pemrograman/Cari/index/");
        for (File f : dir.listFiles()) {
            f.delete();
        }
    }//GEN-LAST:event_cariBtnActionPerformed

    private void cariTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariTxtActionPerformed

    private void adddDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adddDocActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog(null);
        File file = jfc.getSelectedFile();
        String dir = file.getAbsolutePath();
        dirTxt.setText(null);
        String path = dir;
        File files = new File(path);
        dirTxt.setText(path);
//        indexer.indexFileOrDirectory(path);
    }//GEN-LAST:event_adddDocActionPerformed

    private void infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoActionPerformed


    }//GEN-LAST:event_infoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JOptionPane.showMessageDialog(null, "untuk add document caranya klik file lalu add doc jika sudah klik cari saja");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void infocariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infocariActionPerformed
        JOptionPane.showMessageDialog(null, "klik tombol cari 2 kali (double click)");
    }//GEN-LAST:event_infocariActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TextFileIndexer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TextFileIndexer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TextFileIndexer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TextFileIndexer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TextFileIndexer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem adddDoc;
    private javax.swing.JButton cariBtn;
    private javax.swing.JTextField cariTxt;
    private javax.swing.JTextField dirTxt;
    private javax.swing.JMenu info;
    private javax.swing.JMenuItem infocari;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel;
    // End of variables declaration//GEN-END:variables
}
