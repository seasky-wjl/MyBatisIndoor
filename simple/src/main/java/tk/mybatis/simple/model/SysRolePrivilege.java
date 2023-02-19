package tk.mybatis.simple.model;

/**
 * @author mwstart
 * @create 2023-02-04-20:20
 *
 * 角色权限关联表：sys_role_privilege
 */
public class SysRolePrivilege {

    // 角色id
    private Long roleId;

    // 权限id
    private Long privilegeId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Long privilegeId) {
        this.privilegeId = privilegeId;
    }
}
