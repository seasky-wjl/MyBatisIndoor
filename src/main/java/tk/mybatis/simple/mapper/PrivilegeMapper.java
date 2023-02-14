package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.simple.model.SysPrivilege;

import java.util.List;
import java.util.Map;

/**
 * @author mwstart
 * @create 2023-02-04-20:34
 */
public interface PrivilegeMapper {

    @SelectProvider(type = PrivilegeProvider.class,method = "selectById")
    SysPrivilege selectById(Long id);

    /**
     * 通过角色id获取该角色对应的所有权限信息
     *
     * @param id
     * @return
    */
    List<SysPrivilege> selectPrivilegeByRoleId(Long id);
}
