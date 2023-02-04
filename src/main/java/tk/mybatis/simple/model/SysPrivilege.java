package tk.mybatis.simple.model;

/**
 * @author mwstart
 * @create 2023-02-04-20:20
 *
 * 权限表：sys_privilege
 */
public class SysPrivilege {

    // 权限id
    private Long id;

    // 权限名称
    private String privilegeName;

    // 权限URL
    private String privilegeUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public String getPrivilegeUrl() {
        return privilegeUrl;
    }

    public void setPrivilegeUrl(String privilegeUrl) {
        this.privilegeUrl = privilegeUrl;
    }
}
