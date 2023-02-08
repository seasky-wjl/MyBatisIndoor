package tk.mybatis.simple.mapper;

import netscape.security.Privilege;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.simple.model.SysPrivilege;

/**
 * @author mwstart
 * @create 2023-02-04-20:34
 */
public interface PrivilegeMapper {

    @SelectProvider(type = PrivilegeProvider.class,method = "selectById")
    SysPrivilege selectById(Long id);
}
