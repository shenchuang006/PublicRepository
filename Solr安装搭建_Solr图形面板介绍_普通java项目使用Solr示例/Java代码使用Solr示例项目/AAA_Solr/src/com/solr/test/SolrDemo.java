package com.solr.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

/**
 * SolrJʹ��demo
 *
 * @author JustryDeng
 * @date 2018��8��9�� ����9:31:57
 */
public class SolrDemo {

	/**
	 * Solr---��ѯ
	 *
	 * @date 2018��8��9�� ����9:48:14
	 */
	public void solrQuery() {

		// ָ��Ҫ���ӵ�SolrCore
		String solrUrl = "http://127.0.0.1:8983/solr/solr_core";

		// ����Solr�ͻ���(ע:��HttpClient����)
		HttpSolrClient httpSolrClient = new HttpSolrClient.Builder(solrUrl).withConnectionTimeout(60000)
				.withSocketTimeout(20000).build();
		// ����SolrQuery����
		SolrQuery query = new SolrQuery();

		/*
		 * �����ѯ���� ע:���á�AND��, ���á�OR��;���ﲻ���ܽ��жิ�ӵ�ɸѡ,������һ��ɸѡ����������Ĺ���
		 */
		query.setQuery("product_name:����  OR product_name:ŷʽ");

		/// ���ѯʱ��ָ����,���Ǹ�������Ĭ����
		// query.setQuery("�ʼǱ�");
		// query.set("df", "product_name");

		// ������
		// query.set("q", "product_name:�ʼǱ�");

		// ���ù�������
		query.setFilterQueries("product_price:[1 TO 80]");
		/// �ٸ�������ʾ��
		// query.setFilterQueries("id:2009");
		// query.setFilterQueries("product_price:[1 TO 100] AND id:[2000 TO 2009]");
		// query.setFilterQueries("product_price:[1 TO 10] OR product_price:[30 TO
		/// 40]");

		// ��������(���ﰴ�۸���)
		query.setSort("product_price", ORDER.desc);
		// ���ôμ�����(���Ｔ:��product_priceһ��ʱ,��id��������),�����Ҫ�����ôμ��Ļ�,��ô�ٽ��д˲��輴��
		query.addSort("id", ORDER.asc);

		// ���÷�ҳ��Ϣ(Ĭ��Ϊ:0,10)
		query.setStart(0);
		query.setRows(100);

		// ���ò�ѯ��Field(��:����Ҫ��ѯ����)
		query.setFields("id", "product_name", "product_price", "product_picture");

		// ���ö�Ӧ����,��Ӧ�ġ���ѯ�������ֶθ�����ʾ
		query.setHighlight(true);
		query.addHighlightField("product_name");
		query.setHighlightSimplePre("[>>>");
		query.setHighlightSimplePost("<<<<]");

		try {
			// ִ�в�ѯ�����ؽ��
			QueryResponse response = httpSolrClient.query(query);
			System.out.println(">>>>>>>>>>" + httpSolrClient.query(query).getHeader());

			// ����Ӧ�л�ȡ����ѯ����ĵ�
			SolrDocumentList solrDocumentList = response.getResults();

			// ƥ��������
			long count = solrDocumentList.getNumFound();
			System.out.println("ƥ��������:" + count);

			// ������ѯ���
			// ��ȡ������ʾ��Ϣ
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			for (SolrDocument doc : solrDocumentList) {
				System.out.println("id:" + doc.get("id"));
				System.out.println("��Ʒ��:" + doc.get("product_name"));
				// ���������ʾ�Ľ��
				List<String> list2 = highlighting.get(doc.get("id")).get("product_name");
				if (list2 != null) {
					System.out.println("��Ʒ�����������ѯ����,�ٴ�(����)��ʾ��Ʒ��:" + list2.get(0));
				}
				System.out.println("�۸�:" + doc.get("product_price"));
				System.out.println("ͼƬ:" + doc.get("product_picture"));
				System.out.println("------------------�����ָ���------------------");
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// �رտͻ���,�ͷ���Դ
				httpSolrClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Solr---����/�޸�(�и�id���޸�,�޸�id������)
	 *
	 * @date 2018��8��9�� ����9:12:57
	 */
	public void solrAddOrUpdate() {

		// ָ��Ҫ���ӵ�SolrCore
		String solrUrl = "http://127.0.0.1:8983/solr/solr_core";

		// ����Solr�ͻ���(ע:��HttpClient����)
		HttpSolrClient httpSolrClient = new HttpSolrClient.Builder(solrUrl)
											.withConnectionTimeout(60000)
											.withSocketTimeout(20000)
											.build();

		// �����ĵ�doc
		SolrInputDocument document = new SolrInputDocument();

		/*
		 * ������� ע:�����ֱ���:(����,��Ӧ��ֵ) ע:������Ʊ�������schema.xml�ж���� ע:id��һ��Ҫ��
		 */
		document.addField("id", "12345");
		document.addField("name", "��ɳ����");
		document.addField("title", "����solrAddOrUpdate");

		try {
			// ִ��add�����ؽ��
			UpdateResponse updateResponse = httpSolrClient.add(document);
			System.out.println("����������ʱ(����)......" + updateResponse.getElapsedTime());
			// �������Ҫcommit�ύ
			httpSolrClient.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// �رտͻ���,�ͷ���Դ
				httpSolrClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

/**
 * Solr---ɾ��
 *
 * @date 2018��8��9�� ����9:36:05
 */
public void solrDelete() {

	// ָ��Ҫ���ӵ�SolrCore
	String solrUrl = "http://127.0.0.1:8983/solr/solr_core";

	// ����Solr�ͻ���(ע:��HttpClient����)
	HttpSolrClient httpSolrClient = new HttpSolrClient.Builder(solrUrl)
										.withConnectionTimeout(60000)
										.withSocketTimeout(20000)
										.build();

	try {
		// ִ��ɾ��
		httpSolrClient.deleteByQuery("id:12345");
		// �ٸ������ֳ��õ�ɾ����ʽ
		// httpSolrClient.deleteById(String id)
		// httpSolrClient.deleteById(List<String> ids)
		
		// �������Ҫcommit�ύ
		httpSolrClient.commit();
	} catch (SolrServerException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			// �رտͻ���,�ͷ���Դ
			httpSolrClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

	public static void main(String[] args) {
		SolrDemo solrDemo = new SolrDemo();
//		solrDemo.solrQuery();
//		 solrDemo.solrAddOrUpdate();
		solrDemo.solrDelete();
	}
}