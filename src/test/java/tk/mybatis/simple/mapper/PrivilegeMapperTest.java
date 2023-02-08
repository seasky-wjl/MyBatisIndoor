package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysPrivilege;


/**
 * @author mwstart
 * @create 2023-02-08-22:38
 */
public class PrivilegeMapperTest extends BaseMapperTest{

    @Test
    public void testSelectById(){
        SqlSession sqlSession = getSqlSession();

        try {
            // 获取mapper接口
            PrivilegeMapper privilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);

            // 调用selectById方法，查询id = 1的权限
            SysPrivilege privilege = privilegeMapper.selectById(1l);
            // privilege不为空
            Assert.assertNotNull(privilege);
            // privilegeName=用户管理
            Assert.assertEquals("用户管理",privilege.getPrivilegeName());
        } finally {
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

}
