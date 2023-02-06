package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import javax.management.relation.Role;
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

    @Test
    public void testInsert2(){

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
            int result = userMapper.insert2(user);
            // 只插入1条数据
            Assert.assertEquals(1,result);
            // 因为id回写，所以id不为null，
            Assert.assertNotNull(user.getId());
        } finally {
            // 为了不影响其他测试，这里选择回滚
            // 由于默认的sqlSessionFactory.openSession()是不自动提交的
            // 因此不动手执行commit也不会提交到数据库
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testInsert3(){

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
            int result = userMapper.insert2(user);
            // 只插入1条数据
            Assert.assertEquals(1,result);
            // 因为id回写，所以id不为null，
            Assert.assertNotNull(user.getId());
        } finally {
            // 为了不影响其他测试，这里选择回滚
            // 由于默认的sqlSessionFactory.openSession()是不自动提交的
            // 因此不动手执行commit也不会提交到数据库
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testUpdate(){
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 从数据库中查询一个user对象
            SysUser user = userMapper.selectById(1L);
            // 当前userName为amdin
            Assert.assertEquals("admin",user.getUserName());
            // 依次用户名和userInfo
            user.setUserName("admin_test");
            user.setUserInfo("user had been updated");
            user.setCreateTime(new Date());
            // 更新数据，特别注意，这里的返回值result是执行的SQL影响的行数
            int result = userMapper.updateById(user);
            // 只要更新1条数据
            Assert.assertEquals(1,result);
            // 根据当前id查询修改后的数据
            user = userMapper.selectById(1L);
            // 修改后的名字是admin_test
            Assert.assertEquals("admin_test",user.getUserName());
        } finally {
            // 为了不影响其他测试，这里选择回滚
            // 由于默认的sqlSessionFactory.openSession()是不自动提交的
            // 因此不动手执行commit也不会提交到数据库
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById(){
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 从数据库中查询一个user对象
            SysUser user1 = userMapper.selectById(1L);
            // 现在还能查询出user对象
            Assert.assertNotNull(user1);
            // 调用方法删除
            Assert.assertEquals(1,userMapper.deleteById(1l));
            // 再次查询，这时应该没有值，为null
            Assert.assertNull(userMapper.selectById(1l));

            // 使用SysUser参数再进行一次测试，根据id = 1001查询
            SysUser user2 = userMapper.selectById(1001l);
            // 现在还能查询出user对象
            Assert.assertNotNull(user2);
            // 调用方法删除，注意这里使用参数user2
            Assert.assertEquals(1,userMapper.deleteById(user2));
            // 再次查询，这时应该没有值，为null
            Assert.assertNull(userMapper.selectById(1001l));
        } finally {
            // 为了不影响其他测试，这里选择回滚
            // 由于默认的sqlSessionFactory.openSession()是不自动提交的
            // 因此不动手执行commit也不会提交到数据库
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserIdAndRoleEnabled(){
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 调用selectRolesByUserIdAndRoleEnabled方法查询用户的角色
            List<SysRole> userList =
                    userMapper.selectRoleByUserIdAndRoleEnabled(1l, 1);
            // 结果不为空
            Assert.assertNotNull(userList);
            // 角色数量大于0个
            Assert.assertTrue(userList.size() > 0);
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void selectRolesByUserAndRole(){
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 调用selectRolesByUserIdAndRoleEnabled方法查询用户的角色
            SysUser user = new SysUser();
            user.setId(1l);
            SysRole role = new SysRole();
            role.setEnabled(1);

            List<SysRole> userList =
                    userMapper.selectRolesByUserAndRole(user, role);
            // 结果不为空
            Assert.assertNotNull(userList);
            // 角色数量大于0个
            Assert.assertTrue(userList.size() > 0);
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }

    }


}
