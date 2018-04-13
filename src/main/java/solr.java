package src.main.java;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class solr extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("come into the solr server...........");

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        //out.print("connect to solr success");

        String url="http://solr-test.aws.lab";

        SolrServer solr = new HttpSolrServer(url);

        // 查询条件
        SolrQuery solrParams = new SolrQuery();
        solrParams.setStart(0);
        solrParams.setRows(10);
        solrParams.setQuery("name:shencheng +description:shencheng");

        // SolrParams是SolrQuery的子类
        QueryResponse queryResponse = null;
        try {
            queryResponse = solr.query(solrParams);
            out.print("connect to solr success");
        } catch (SolrServerException e) {
            out.print("connect to solr failed");
            //e.printStackTrace();
        }

        // (一)获取查询的结果集合
        SolrDocumentList solrDocumentList = queryResponse.getResults();

        // (二)获取高亮的结果集
        // 第一个Map的键是文档的ID，第二个Map的键是高亮显示的字段名
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();

        for (SolrDocument solrDocument : solrDocumentList) {
            out.print("find result:"+solrDocument.toString());

        }
        out.print("call solr end");
        out.flush();
        out.close();

    }

}
