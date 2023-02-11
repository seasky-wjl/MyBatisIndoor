package tk.mybatis.simple.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import tk.mybatis.simple.model.Country;
import tk.mybatis.simple.model.CountryExample;

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

    @Test
    public void testExample(){
        // 获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取CountryMapper接口
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            // 创建Example对象
            CountryExample example = new CountryExample();
            // 设置排序规则
            example.setDistinct(true);
            // 创建条件
            CountryExample.Criteria criteria = example.createCriteria();
            // id >= 1
            criteria.andIdGreaterThanOrEqualTo(1);
            // id < 4
            criteria.andIdLessThan(4);
            // countrycode like '%U%'
            // 最容易出错的地方，注意like必须自己写上通配符的位置
            criteria.andCountrycodeLike("%U%");
            // or的情况
            CountryExample.Criteria or = example.or();
            // countryName = 中国
            or.andCountrynameEqualTo("中国");
            // 执行查询
            List<Country> countryList = countryMapper.selectByExample(example);
            printCountryList(countryList);

        } finally {
            // 不要忘记关闭sqlSession，否则会因为连接没有关闭导致数据库连接数过多，造成系统崩溃
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByExampleSelective(){
        // 获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取CountryMapper接口
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            // 创建Example对象
            CountryExample example = new CountryExample();
            // 创建条件，只能有一个createCriteria
            CountryExample.Criteria criteria = example.createCriteria();
            // 更新所有id >= 2的国家
            criteria.andIdGreaterThan(2);
            // 创建一个要设置的对象
            Country country = new Country();
            // 将国家名字设置为China
            country.setCountryname("China");
            countryMapper.updateByExampleSelective(country, example);
            printCountryList(countryMapper.selectByExample(example));

        } finally {
            // 不要忘记关闭sqlSession，否则会因为连接没有关闭导致数据库连接数过多，造成系统崩溃
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteByExample(){
        // 获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取CountryMapper接口
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            // 创建Example对象
            CountryExample example = new CountryExample();
            // 创建条件，只能有一个createCriteria
            CountryExample.Criteria criteria = example.createCriteria();
            //删除所有id > 2的国家
            criteria.andIdGreaterThan(2);
            // 执行查询
            countryMapper.deleteByExample(example);
            // 使用countByExample查询符合条件的数量，因为已删除，所以这里应该是0
            Assert.assertEquals(0, countryMapper.countByExample(example));
        } finally {
            // 不要忘记关闭sqlSession，否则会因为连接没有关闭导致数据库连接数过多，造成系统崩溃
            sqlSession.close();
        }
    }



}




















