package com.warmer.kgmaker.util;

import com.warmer.kgmaker.KgmakerApplication;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: jiayi
 * @description:
 * @date: 2020/12/24 10:36 AM
 * @version: v1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KgmakerApplication.class)
public class Neo4jUtilTest {
    @Autowired
    private Driver neo4jDriver;

    @Autowired
    Neo4jUtil neo4jUtil;

    private Session session;

    @Before
    public void init(){
        session = neo4jDriver.session();
    }

    @Test
    public void isNeo4jOpen() {
        System.err.println("连接是否成功：" + session.isOpen());
    }

    @Test
    public void excuteCypherSql() {
//        neo4jUtil.excuteCypherSql("CREATE (dept:Dept { deptno:10,dname:\"Accounting\",location:\"HANGZHOU\" })");
        neo4jUtil.excuteCypherSql("CREATE (dept:Dept { deptno:10,dname:\"Accounting\",location:\"HANGZHOU\" })");
        for (int i = 1; i < 11; i++) {
            String cql = "CREATE (emp:Employee{id:"+i+",name:\"zhang"+i+"\",sal:"+ RandomUtils.nextInt(1000, 2000) * i+",deptno:10})";
            neo4jUtil.excuteCypherSql(cql);
        }
    }
}