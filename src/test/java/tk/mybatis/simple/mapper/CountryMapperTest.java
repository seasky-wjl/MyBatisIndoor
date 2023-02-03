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
 */
public class CountryMapperTest {

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

    @Test
    public void testSelectAll(){
        // 通过SqlSessionFactory工厂对象获取一个SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 通过SqlSession地selectList方法查找到CountryMapper.xml中id=“selectAll”地方法，执行sql
            List<Country> countryList = sqlSession.selectList("selectAll");
            printCountryList(countryList);
        } finally {
            // 不要忘记关闭sqlSession，否则会因为连接没有关闭导致数据库连接数过多，造成系统崩溃
            sqlSession.close();
        }
    }

    private void printCountryList(List<Country> countryList) {
        for (Country country :
                countryList) {
            System.out.printf("%-4d%4s%4s\n",country.getId(),country.getCountryname(),country.getCountrycode());
        }
    }


}




















