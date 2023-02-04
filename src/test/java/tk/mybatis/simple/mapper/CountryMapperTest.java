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
public class CountryMapperTest extends BaseMapperTest{


    @Test
    public void testSelectAll(){
        // 通过SqlSessionFactory工厂对象获取一个SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 通过SqlSession地selectList方法查找到CountryMapper.xml中id=“selectAll”地方法，执行sql
            List<Country> countryList = sqlSession.selectList("tk.mybatis.simple.mapper.CountryMapper.selectAll");
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




















