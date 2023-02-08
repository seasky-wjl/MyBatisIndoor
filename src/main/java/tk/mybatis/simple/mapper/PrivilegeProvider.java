package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author mwstart
 * @create 2023-02-08-22:34
 */
public class PrivilegeProvider {

    public String selectById(final Long id){
        return new SQL(){
            {
                SELECT("id, privilege_name, privilege_url");
                FROM("sys_privilege");
                WHERE("id = #{id}");
            }
        }.toString();
    }
}
