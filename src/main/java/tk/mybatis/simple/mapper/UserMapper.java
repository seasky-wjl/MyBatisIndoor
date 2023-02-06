package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.util.List;

/**
 * @author mwstart
 * @create 2023-02-04-20:34
 */
public interface UserMapper {

    /**
     * 通过id查询用户
     *
     * @param
     * @return
     * */

    SysUser selectById(Long id);

    /**
     * 查询全部用户
     *
     * @return
     * */
    List<SysUser> selectAll();

    /**
     * 新增用户
     *
     * @param sysUser
     * @return
     * */
    int insert(SysUser sysUser);

    /**
     * 新增用户0使用useGeneratedKeys方法
     *
     * @param sysUser
     * @return
     * */
    int insert2(SysUser sysUser);

    /**
     * 新增用户-使用selectKey方式
     *
     * @param sysUser
     * @return
    */
    public void insert3(SysUser sysUser);

    /**
     * 根据主键更新
     *
     * @param sysUser：参数sysUser就是要更新的数据
     * @reutrn
     */
    int updateById(SysUser sysUser);

    /**
     * 通过主键删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 通过主键删除
     *
     * @param sysUser
     * @return
     */
    int deleteById(SysUser sysUser);

    /**
     * 根据用户id和角色的enabled状态获取用户的角色
     *
     * @param userId
     * @param enabled
     * @return
     */
    List<SysRole> selectRoleByUserIdAndRoleEnabled(
            @Param("userId")Long userId,
            @Param("enabled")Integer enabled);
    /**
     * 根据用户id和角色的enabled状态获取用户的角色
     *
     * @param user
     * @param role
     * @return
    */
    List<SysRole> selectRolesByUserAndRole(
            @Param("user")SysUser user,
            @Param("role")SysRole role);

}
