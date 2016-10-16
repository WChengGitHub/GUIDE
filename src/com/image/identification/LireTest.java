package com.image.identification;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import net.semanticmetadata.lire.DocumentBuilder;
import net.semanticmetadata.lire.DocumentBuilderFactory;
//import net.semanticmetadata.lire.ImageDuplicates;
import net.semanticmetadata.lire.ImageSearchHits;
import net.semanticmetadata.lire.ImageSearcher;
import net.semanticmetadata.lire.ImageSearcherFactory;

import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
//import org.junit.Test;

public class LireTest {

	 private static String INDEX_PATH = "D:/index";// 索引文件存放路径

	 //要索引的图片文件目录
	 private static String INDEX_FILE_PATH = "/JspTest2/WebRoot/image";

//    @Test
    public void createIndex() throws Exception {
        //创建一个合适的文件生成器，Lire针对图像的多种属性有不同的生成器
        DocumentBuilder db = DocumentBuilderFactory.getCEDDDocumentBuilder();
        IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_33, new SimpleAnalyzer(Version.LUCENE_33));
        IndexWriter iw = new IndexWriter(FSDirectory.open(new File(INDEX_PATH)), iwc);
        File parent = new File(INDEX_FILE_PATH);
        for (File f : parent.listFiles()) {
            // 创建Lucene索引
            Document doc = db.createDocument(new FileInputStream(f), f.getName());
            // 将文件加入索引
            iw.addDocument(doc);
        }
        iw.optimize();
        iw.close();
    }


    /*
     * String SEARCH_FILE 用于搜索的图片,用传参的形式传进
     * */
//  @Test   
    public String[] searchSimilar(String SEARCH_FILE) throws Exception {
    	
    	System.out.println("test photourl "+SEARCH_FILE);
    	
        IndexReader ir = IndexReader.open(FSDirectory.open(new File(INDEX_PATH)));//打开索引
        ImageSearcher is = ImageSearcherFactory.createDefaultSearcher();//创建一个图片搜索器
        FileInputStream fis = new FileInputStream(SEARCH_FILE);//搜索图片源
        BufferedImage bi = ImageIO.read(fis);
        ImageSearchHits ish = is.search(bi, ir);//根据上面提供的图片搜索相似的图片
        //iamgeUrl 是十张图片文件夹的路径数组
        String[] imageUrl = new String[10] ;
      //显示前10条记录（根据匹配度排序）
        for (int i = 0; i < 10; i++) {
        	//imageUrl[i]=ish.score(i) + ": "+"/JspTest2/image"+"/"+ ish.doc(i).getFieldable(DocumentBuilder.FIELD_NAME_IDENTIFIER).stringValue();
        	//除去ish.score(i),这是表示识别出的图片与原图片的相似度
        	imageUrl[i]="/JspTest2/image"+"/"+ ish.doc(i).getFieldable(DocumentBuilder.FIELD_NAME_IDENTIFIER).stringValue();
            System.out.println(imageUrl[i]+"    test1");
           
        }
        return imageUrl;
    }
}