package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;

import java.util.Date;
import java.util.List;

/**
 * @author mwstart
 * @create 2023-02-07-10:00
 */
public class RoleMapperTest extends BaseMapperTest{

    @Test
    public void testSelectById(){
        SqlSession sqlSession = getSqlSession();

        try {
            // 获取Mapper接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            // 调用
            SysRole role = roleMapper.selectById(1l);
            // role不为空
            Assert.assertNotNull(role);
            // roleName = 管理员
            Assert.assertEquals("管理员", role.getRoleName());
        } finally {
            // 关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectById2(){
        SqlSession sqlSession = getSqlSession();

        try {
            // 获取Mapper接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            // 调用
            SysRole role = roleMapper.selectById2(1l);
            // role不为空
            Assert.assertNotNull(role);
            // roleName = 管理员
            Assert.assertEquals("管理员", role.getRoleName());
        } finally {
            // 关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = getSqlSession();

        try {
            // 获取Mapper接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            // 调用
            List<SysRole> roleList = roleMapper.selectAll();
            for (SysRole role :
                    roleList) {
                System.out.println(role);
            }
        } finally {
            // 关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testInsert(){
        SqlSession sqlSession = getSqlSession();

        try {
            // 获取Mapper接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            // 创建新用户
            SysRole sysRole1 = new SysRole(3l,"SVIP",1,null,new Date());
            // 调用,插入语句的返回结果是该操作所影响的行数
            int result = roleMapper.insert(sysRole1);
            // 确定此次插入是否影响了一行
            Assert.assertEquals(1, result);
            SysRole sysRole2 = roleMapper.selectById(3l);
            // 再重数据库中获取并与原来的Sysrole1比较
            Assert.assertEquals(sysRole1.getRoleName(),sysRole2.getRoleName());

        } finally {
            // 为了不影响其他测试，这里选择回滚
            // 由于默认的sqlSessionFactory.openSession()是不自动提交的
            // 因此不动手执行commit也不会提交到数据库
            sqlSession.rollback();
            // 关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testInsert3(){
        SqlSession sqlSession = getSqlSession();

        try {
            // 获取Mapper接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            // 创建新用户
            SysRole sysRole1 = new SysRole();
            sysRole1.setRoleName("SVIP");
            sysRole1.setEnabled(1);
            // 调用,插入语句的返回结果是该操作所影响的行数
            int result = roleMapper.insert(sysRole1);
            // 确定此次插入是否影响了一行
            Assert.assertEquals(1, result);
            SysRole sysRole2 = roleMapper.selectByRoleName("SVIP");
            // 再重数据库中获取并与原来的Sysrole1比较
            Assert.assertEquals(sysRole1.getEnabled(),sysRole2.getEnabled());

        } finally {
            // 为了不影响其他测试，这里选择回滚
            // 由于默认的sqlSessionFactory.openSession()是不自动提交的
            // 因此不动手执行commit也不会提交到数据库
            sqlSession.rollback();
            // 关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById(){
        SqlSession sqlSession = getSqlSession();

        try {
            // 获取Mapper接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            // 生成新的SysRole对象
            SysRole sysRole = new SysRole(1l,"DBA",1,2l,new Date());
            // 获取更新语句的结果，结果为此次更新所影响的行数
            int result = roleMapper.updateById(sysRole);
            // 判断所影响的行数是否为1
            Assert.assertEquals(1,result);
            // 更新后的roleName是否为DBA
            Assert.assertEquals("DBA",roleMapper.selectById(1l).getRoleName());
        } finally {
            // 回滚，以防对后面的测试产生影响
            sqlSession.rollback();
            // 关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById(){
        SqlSession sqlSession = getSqlSession();

        try {
            // 获取Mapper接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            // 获取更新语句的结果，结果为此次更新所影响的行数
            int result = roleMapper.deleteById(1l);
            // 判断所影响的行数是否为1
            Assert.assertEquals(1,result);
            // 删除后再查询则为空
            Assert.assertNull(roleMapper.selectById(1l));
        } finally {
            // 回滚，以防对后面的测试产生影响
            sqlSession.rollback();
            // 关闭sqlSession
            sqlSession.close();
        }
    }


    @Test
    public void testSelectAllRoleAndPrivilege(){
        SqlSession sqlSession = getSqlSession();

        try {
            // 获取Mapper接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roleList = roleMapper.selectAllRoleAndPrivilege();
            System.out.println("角色数：" + roleList.size());
            for (SysRole role :
                    roleList) {
                System.out.println("用户名：" + role.getRoleName());
                for (SysPrivilege privilege :
                        role.getPrivilegeList()) {
                    System.out.println("权限名：" + privilege.getPrivilegeName());
                }
            }
        } finally {
            // 关闭sqlSession
            sqlSession.close();
        }
    }

}
