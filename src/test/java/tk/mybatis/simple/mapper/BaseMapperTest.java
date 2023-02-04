package tk.mybatis.simple.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import tk.mybatis.simple.model.Country;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @author mwstart
 * @create 2023-02-03-14:06
 *
 * 基础测试类
 */
public class BaseMapperTest {

    public static SqlSessionFactory sqlSessionFactory;


    @BeforeClass
    public static void init(){
        try {
            // 1.通过Resouces类将mybatis-config.xml配置文件读入Reader
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            // 2.再通过SqlSessionFactoryBuilder建造类使用Reader床架SqlSessionFactory工厂对象。
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }

}




















