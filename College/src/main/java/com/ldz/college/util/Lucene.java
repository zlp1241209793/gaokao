package com.ldz.college.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class Lucene {
	/**
	 * 创建索引
	 * @throws IOException 
	 */
	public void createIndex(List<Map<String, Object>> list) throws IOException {
		//		第一步：创建一个java工程，并导入jar包。
		//		第二步：创建一个indexwriter对象。
		//			1.指定索引库的存放位置Directory对象。
		//file system Directory 文件系统目录
		File file = new File("temp\\index");
		if (!file.exists()) { // 如果文件夹不存在
			file.mkdirs(); // 创建文件夹
		}
		// 删除所有旧索引
		for (File fl : file.listFiles()) {
			fl.delete();
		}
		Directory directory = FSDirectory.open(file);

		//			2.指定一个分析器，对文档内容进行分析。
		Analyzer analyzer = new IKAnalyzer(); // 适合中文的分词器，支持扩展
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);

		IndexWriter indexWriter = new IndexWriter(directory, config);
		//		第三步：创建document对象。
		Document document = null;
		//		第四步：创建field对象（field域，相当于给document定义属性），将filed对象添加到document对象中。
		for (Map<String, Object> map : list) { // 给每个文档设置属性
			document = new Document();
			// 文件属性1：大学名称
			String file_Uname = map.get("uname").toString();
			Field file_UnameField = new TextField("file_Uname", file_Uname, Store.YES);

			// 文件属性2：大学所在地
			String file_Place = map.get("place").toString();
			Field file_PlaceField = new TextField("file_Place", file_Place, Store.YES);

			// 文件属性3：大学简称
			String file_ShortName = map.get("shortName").toString();
			Field file_ShortNameField = new TextField("file_ShortName", file_ShortName, Store.YES);

			// 文件属性4：大学标签
			String file_Tags = map.get("tags").toString();
			Field file_TagsField = new TextField("file_Tags", file_Tags, Store.YES);

			// 文件属性5：大学主页URL 无需分词
			String file_Link = map.get("link").toString();
			Field file_LinkField = new StoredField("file_Link", file_Link);

			// 文件属性6：大学编号 无需分词
			String file_Uid = map.get("uid").toString();
			Field file_UidField = new StoredField("file_Uid", file_Uid);


			document.add(file_UnameField);
			document.add(file_PlaceField);
			document.add(file_ShortNameField);
			document.add(file_TagsField);
			document.add(file_LinkField);
			document.add(file_UidField);
			// 第五步：使用indexwriter对象将document对象写入索引库（与字典类似），此过程进行索引创建，并将索引和document对象写入索引库。
			indexWriter.addDocument(document);
		}
		// 第六步：关闭indexwriter对象
		indexWriter.close();
	}



	/**
	 * 查询索引
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public Map<String, Object> Search(String text, Integer page, Integer rows) throws IOException, ParseException {
		Map<String, Object> map = new HashMap<String, Object>(); // 用来存取返回的数据
		
		
		if (text == null || text == "") {
			return Collections.emptyMap();
		}
		//	 第一步：创建一个Directory对象，也就是索引库存放的位置。
		Directory directory = FSDirectory.open(new File("temp\\index")); // 磁盘上的库
		//	 第二步：创建一个indexReader对象，需要指定Directory对象。
		IndexReader indexReader = DirectoryReader.open(directory);
		//	 第三步：创建一个indexSearcher对象，需要指定indexReader对象。
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		//	 第四步：创建一个TermQuery对象，指定查询的域和查询的关键词。
		String[] fields = {"file_Uname", "file_Place", "file_ShortName", "file_Tags"};
		Occur[] occ = {Occur.SHOULD, Occur.SHOULD, Occur.SHOULD, Occur.SHOULD};
		Analyzer analyzer = new IKAnalyzer();
		Query query = MultiFieldQueryParser.parse(text, fields, occ, analyzer);
		//	 第五步：执行查询。
		// 查询数据， 结束页面自前的数据都会查询到，但是只取本页的数据
        TopDocs topDocs = indexSearcher.search(query, page);
        Integer count = topDocs.totalHits; // 查询到的总条数
        map.put("count", count);
        if (page > rows) { // 如果需要查询的总条数大于每页显示行数，说明当前不是第一页，则使用分页查询
			// 获取到上一页最后一条
			ScoreDoc preScore = topDocs.scoreDocs[page-rows-1];
			topDocs = indexSearcher.searchAfter(preScore, query, rows); // 从preScore开始，查rows条文档
		}
		//	 第六步：返回查询结果。遍历查询结果并输出。
		ScoreDoc[] scoreDocs = topDocs.scoreDocs; // 文档ID集合
		List<Document> list = new ArrayList<Document>();
		for (ScoreDoc scoreDoc : scoreDocs) {
			int doc = scoreDoc.doc; // 文档ID
			Document document = indexSearcher.doc(doc);
			list.add(document);
		}
		//	 第七步：关闭IndexReader对象
		indexReader.close();
		
		// 对查询结果做处理
		List<Map<String, Object>> university = new ArrayList<Map<String,Object>>();
		Map<String, Object> myMap = null;
		for (Document document : list) {
			myMap = new HashMap<String, Object>();
			myMap.put("uid", document.get("file_Uid"));
			myMap.put("uname", document.get("file_Uname"));
			myMap.put("place", document.get("file_Place"));
			myMap.put("shortName", document.get("file_ShortName"));
			myMap.put("tags", document.get("file_Tags"));
			myMap.put("link", document.get("file_Link"));
			university.add(myMap);
		}
		
		map.put("lucene", university);
		return map;
	}
}