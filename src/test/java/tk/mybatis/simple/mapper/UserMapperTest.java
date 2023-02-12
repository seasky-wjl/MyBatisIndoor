package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import javax.management.relation.Role;
import java.util.*;

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

    @Test
    public void testSelectRolesByUser(){
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 只查询用户名时
            SysUser query = new SysUser();
            query.setUserName("ad");
            List<SysUser> userList = userMapper.selectByUser(query);
            Assert.assertTrue(userList.size()>0);
            // 只查询用户邮箱时
            query = new SysUser();
            query.setUserEmail("test@mybatis.tk");
            userList = userMapper.selectByUser(query);
            // 同时查询用户名和邮箱时
            query = new SysUser();
            query.setUserName("ad");
            query.setUserEmail("test@mybatis.tk");
            userList = userMapper.selectByUser(query);
            // 由于没有同时符合这两个条件的用户，因此查询结果数 为0
            Assert.assertTrue(userList.size() == 0);

        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByIdSelective(){
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 只查询用户名时
            SysUser user = new SysUser();
            // 更新id=1的用户
            user.setId(1l);
            // 修改邮箱
            user.setUserEmail("test@mybatis.tk");
            // 更新邮箱，特别注意，这里的返回值result执行的是SQL影响的行数
            int result = userMapper.updateByIdSelective(user);
            // 只有1条数据
            Assert.assertEquals(1,result);
            // 根据当前id查询修改后的数据
            user = userMapper.selectById(1l);
            // 修改后的名字保持不变。但是邮箱变成了新的
            Assert.assertEquals("admin",user.getUserName());
            Assert.assertEquals("test@mybatis.tk", user.getUserEmail());


        } finally {
            // 要回滚,以免影响其他测试
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testInsert2Selective(){
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建一个user对象
            SysUser user = new SysUser();
            user.setUserName("test-selective");
            user.setUserPassword("123456");
            user.setUserInfo("test Info");
            user.setCreateTime(new Date());
            // 插入数据库
            userMapper.insert2(user);
            // 获取插入的这条数据
            user = userMapper.selectById(user.getId());
            Assert.assertEquals("test@mybatis.tk",user.getUserEmail());
        } finally {
            // 要回滚,以免影响其他测试
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdOrUserName(){
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建一个user对象
            SysUser query = new SysUser();
            query.setId(1l);
            query.setUserName("admin");
            SysUser user = userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);
            // 当没有id时
            query.setId(null);
            user = userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);
            // 当id和name都为空时
            query.setUserName(null);
            user = userMapper.selectByIdOrUserName(query);
            Assert.assertNull(user);
        } finally {
            // 要回滚,以免影响其他测试
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdList(){
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Long> idList = new ArrayList<>();
            idList.add(1l);
            idList.add(1001l);
            // 业务逻辑中必须校验idList.size() > 0
            List<SysUser> userList = userMapper.selectByIdList(idList);
            Assert.assertEquals(2,userList.size());
        } finally {
            // 要回滚,以免影响其他测试
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testInsertList(){
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建一个user对象
            List<SysUser> userList = new ArrayList<>();
            for(int i=0; i < 2; i++){
                SysUser user = new SysUser();
                user.setUserName("test"+i);
                user.setUserPassword("123456");
                user.setUserEmail("test@mybatis.tk");
                user.setUserInfo("test");
                user.setHeadImg(new byte[]{1,2,3});
                user.setCreateTime(new Date());
                userList.add(user);
            }
            // 将新建的对象批量插入数据库中
            // 返回值是执行SQL影响的行数
            int result = userMapper.insertList(userList);
            Assert.assertEquals(2,result);
        } finally {
            // 要回滚,以免影响其他测试
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByMap(){
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> map = new HashMap<String,Object>();
            // 查询条件，同样也是更新字段，必须保证该值存在
            map.put("id",1l);
            map.put("user_password","12345678");
            // 更新数据
            userMapper.updateByMap(map);
            // 根据当前id查询修改后的数据
            SysUser user = userMapper.selectById(1l);
            Assert.assertEquals("admin@mybatis.tk", user.getUserEmail());
        } finally {
            // 要回滚,以免影响其他测试
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById(){
        // 获取sqlSession
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 特别注意，在测试数据中，id=1L的用户有两个角色，不适合这个例子
            // 这里使用只有一个角色的用户(id = 1001L)
            SysUser user = userMapper.selectUserAndRoleById(1001L);
            // user不为空
            Assert.assertNotNull(user);
            // user.role也不为空
            Assert.assertNotNull(user.getRole());
        } finally {

            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById2(){
        // 获取sqlSession
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 特别注意，在测试数据中，id=1L的用户有两个角色，不适合这个例子
            // 这里使用只有一个角色的用户(id = 1001L)
            SysUser user = userMapper.selectUserAndRoleById2(1001L);
            // user不为空
            Assert.assertNotNull(user);
            // user.role也不为空
            Assert.assertNotNull(user.getRole());
        } finally {

            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleByIdSelect(){
        // 获取sqlSession
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 特别注意，在测试数据中，id=1L的用户有两个角色，不适合这个例子
            // 这里使用只有一个角色的用户(id = 1001L)
            SysUser user = userMapper.selectUserAndRoleByIdSelect(1001L);
            // user不为空
            Assert.assertNotNull(user);
            System.out.println("调用user.equals(null)");
            user.equals(null);
            System.out.println("调用user.getRole()");
            // user.role也不为空
            Assert.assertNotNull(user.getRole());
        } finally {

            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllUserAndRoles(){
        // 获取sqlSession
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = userMapper.selectAllUserAndRoles();
            System.out.println("用户数："+userList.size());
            for (SysUser user :
                    userList) {
                System.out.println("用户名：" + user.getUserName());
                for (SysRole role :
                        user.getRoleList()) {
                    System.out.println("角色名：" + role.getRoleName());
                    for (SysPrivilege privilege:
                         role.getPrivilegeList()) {
                        System.out.println("权限名：" + privilege.getPrivilegeName());
                    }
                }
            }
        } finally {

            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllUserAndRolesSelect(){
        // 获取sqlSession
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectAllUserAndRolesSelect(1l);
            System.out.println("用户名："+user.getUserName());
            for (SysRole role :
                    user.getRoleList()) {
                System.out.println("角色名：" + role.getRoleName());
                for (SysPrivilege privilege:
                        role.getPrivilegeList()) {
                    System.out.println("权限名：" + privilege.getPrivilegeName());
                }
            }
        } finally {

            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserById(){
        // 获取sqlSession
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setId(1l);
            userMapper.selectUserById(user);
            Assert.assertNotNull(user.getUserName());
            System.out.println("用户名：" + user.getUserName());
        } finally {

            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

}
