package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import javax.management.relation.Role;

/**
 * @author mwstart
 * @create 2023-02-14-15:02
 */
public class CacheTest extends BaseMapperTest{

    @Test
    public void test1Cache(){
        // 获取SQl
        SqlSession sqlSession = getSqlSession();
        SysUser user1 = null;

        try {
            //获取mapper接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 调用selectById方法，查询id = 1的用户
            user1 = userMapper.selectById(1l);
            // 对当前获取的对象重新赋值
            user1.setUserName("New Name");
            // 再次查询获取id相同的用户
            SysUser user2 = userMapper.selectById(1l);
            // 虽然没有更新数据库，但是这个用户名和user1重新赋值的名字相同
            Assert.assertEquals("New Name",user2.getUserName());
            // 无论如何，user2和user1完全就是同一个实例
            Assert.assertEquals(user1,user2);
        } finally {
            // 关闭sqlSession
            sqlSession.close();
        }
        System.out.println("开启新的sqlSession");
        // 开始另一个新的session
        sqlSession = getSqlSession();
        try {
            // 获取UserMapper接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 调用selectById方法，查询id = 1的用户
            SysUser user2 = userMapper.selectById(1l);
            // 第二个session获取的用户名任然是admin
            Assert.assertNotEquals("New Name",user2.getUserName());
            // 这里的user2和前一个session查询的结果是两个不同的实例
            Assert.assertNotEquals(user1,user2);
            // 执行删除操作
            userMapper.deleteById(2l);
            // 获取user3
            SysUser user3 = userMapper.selectById(1l);
            // 这里的user2和user3是两个不同的实例
            Assert.assertNotEquals(user2, user3);
        } finally {
            // 关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testL2Cache(){
        // 获取SQl
        SqlSession sqlSession = getSqlSession();
        SysRole role1 = null;

        try {
            //获取mapper接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            // 调用selectById方法，查询id = 1用户
            role1 = roleMapper.selectById(1l);
            // 对当前获取的对象重新赋值
            role1.setRoleName("New Name");
            // 再次查询获取id相同的用户
            SysRole role2 = roleMapper.selectById(1l);
            // 虽然没有更新数据库，但是这个用户名和role1重新赋值的名字相同
            Assert.assertEquals("New Name", role2.getRoleName());
            // 无论如何，role2和role1完全就是同一个实例
            Assert.assertEquals(role1,role2);

        } finally {
            // 关闭sqlSession
            sqlSession.close();
        }
        System.out.println("开启新的sqlSession");
        // 开始另一个新的session
        sqlSession = getSqlSession();
        try {
            // 获取RoleMappper接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            // 调用selectById方法，查询id = 1用户
            SysRole role2 = roleMapper.selectById(1l);
            // 第二个session获取的用户名是New Name
            Assert.assertEquals("New Name", role2.getRoleName());
            // 这里的role2和前一个session查询的结果是两个不同的实例
            Assert.assertNotEquals(role1,role2);
            // 获取role3
            SysRole role3 = roleMapper.selectById(1l);
            // 这里的role2和role3是两个不同的实例
            Assert.assertNotEquals(role2, role3);

        } finally {
            // 关闭sqlSession
            sqlSession.close();
        }
    }

}
