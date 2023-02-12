package tk.mybatis.simple.model;

import java.util.Date;
import java.util.List;

/**
 * @author mwstart
 * @create 2023-02-04-19:44
 *
 * 角色表：sys_role
 */
public class SysRole {

    /**
     * 角色包含的权限列表
    */
    List<SysPrivilege> privilegeList;


    private Long id;

    private String roleName;

    private int enabled;

    private Long createBy;

    private Date createTime;

    public SysRole(){

    }

    public SysRole(Long id, String roleName, int enabled, Long createBy, Date createTime) {
        this.id = id;
        this.roleName = roleName;
        this.enabled = enabled;
        this.createBy = createBy;
        this.createTime = createTime;
    }

    public List<SysPrivilege> getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(List<SysPrivilege> privilegeList) {
        this.privilegeList = privilegeList;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", enabled=" + enabled +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                '}';
    }
}
