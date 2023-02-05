package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysUser;

import java.util.Date;
import java.util.List;

/**
 * @author mwstart
 * @create 2023-02-04-21:57
 */
public class UserMapperTest extends BaseMapperTest{

    @Test
    public void testSelectById(){
        // 获取sqlSession
        SqlSession sqlSession = getSqlSession();

        try {
            // 获取UserMapper接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 调用sselectById方法
            SysUser user = userMapper.selectById(1L);
            // user不为空
            Assert.assertNotNull(user);
            // userName ?= admin
            Assert.assertEquals("admin",user.getUserName());
        } finally {
            sqlSession.close();
        }

    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 调用selectAll方法查询所有用户
            List<SysUser> sysUserList = userMapper.selectAll();
            // 结果不为空
            Assert.assertNotNull(sysUserList);
            // 用户数量大于0
            Assert.assertTrue(sysUserList.size() > 0);
        } finally {
            // 关闭sqlSession
            sqlSession.close();
        }

    }

    @Test
    public void testInsert(){

        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test1@qq.com");
            user.setUserInfo("test info");
            // 正常情况下应该读入一张图片存到byte数组中
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());
            // 将新建的对象插入数据库中，特别注意这里的返回值result是执行SQL影响的行数
            int result = userMapper.insert(user);
            // 只插入1条数据
            Assert.assertEquals(1,result);
            // id为null,没有给id赋值，并没有配置回写id的值
            Assert.assertNull(user.getId());
        } finally {
            // 为了不影响其他测试，这里选择回滚
            // 由于默认的sqlSessionFactory.openSession()是不自动提交的
            // 因此不动手执行commit也不会提交到数据库
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }


    }
}
