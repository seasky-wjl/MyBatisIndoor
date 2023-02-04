package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysUser;

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
}
