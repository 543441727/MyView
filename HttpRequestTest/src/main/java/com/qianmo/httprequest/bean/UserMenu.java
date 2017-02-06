package com.qianmo.httprequest.bean;

import java.util.List;

/**
 * Created by wangjitao on 2016/12/21 0021.
 * E-Mail：543441727@qq.com
 * 用户菜单权限按钮
 */
public class UserMenu {

    /**
     * last_login_time : 2016-12-21 15:40:28
     * member_id : 1
     * modules : [{"module_code":"100","module_id":1,"module_name":"首页","pid":0,"type":1,"value":"P_index"},{"module_code":"101","module_id":2,"module_name":"审核管理","pid":0,"type":1,"value":"P_audit"},{"module_code":"102","module_id":3,"module_name":"用户管理","pid":0,"type":1,"value":"P_user"},{"module_code":"103","module_id":4,"module_name":"订单管理","pid":0,"type":1,"value":"P_order"},{"module_code":"104","module_id":5,"module_name":"banner管理","pid":0,"type":1,"value":"P_banner"},{"module_code":"105","module_id":6,"module_name":"系统管理","pid":0,"type":1,"value":"P_system"},{"module_code":"101001","module_id":7,"module_name":"企业认证审核","pid":2,"type":1,"value":"P_audit_handle"},{"module_code":"101002","module_id":8,"module_name":"信息发布审核","pid":2,"type":1,"value":"P_audit_publish"},{"module_code":"101003","module_id":9,"module_name":"采购付款审核","pid":2,"type":1,"value":"P_audit_pay"},{"module_code":"101004","module_id":10,"module_name":"结算货款审核","pid":2,"type":1,"value":"P_audit_ship"},{"module_code":"101003001","module_id":11,"module_name":"上传交易凭证","pid":9,"type":2,"value":"P_audit_pay_upload"},{"module_code":"101003002","module_id":12,"module_name":"确认打款","pid":9,"type":2,"value":"P_audit_pay_confirm"},{"module_code":"105001","module_id":13,"module_name":"删除","pid":6,"type":2,"value":"P_system_delete"},{"module_code":"105002","module_id":16,"module_name":"增加","pid":6,"type":2,"value":"P_system_add"},{"module_code":"105003","module_id":17,"module_name":"重置密码","pid":6,"type":2,"value":"P_system_reset"},{"module_code":"101003003","module_id":18,"module_name":"采购付款审核","pid":9,"type":2,"value":"P_audit_pay_update"},{"module_code":"101004001","module_id":19,"module_name":"结算货款审核","pid":10,"type":2,"value":"P_audit_ship_update"},{"module_code":"103001001","module_id":20,"module_name":"修改订单状态","pid":4,"type":2,"value":"P_order_status"}]
     * phone : 18900532225
     * real_name : 超级管理员
     * role : {"role_id":1,"role_name":"超级管理员"}
     * username : superadmin
     */

    private String last_login_time;
    private int member_id;
    private String phone;
    private String real_name;
    /**
     * role_id : 1
     * role_name : 超级管理员
     */

    private RoleBean role;
    private String username;
    /**
     * module_code : 100
     * module_id : 1
     * module_name : 首页
     * pid : 0
     * type : 1
     * value : P_index
     */

    private List<ModulesBean> modules;

    public String getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(String last_login_time) {
        this.last_login_time = last_login_time;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public RoleBean getRole() {
        return role;
    }

    public void setRole(RoleBean role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ModulesBean> getModules() {
        return modules;
    }

    public void setModules(List<ModulesBean> modules) {
        this.modules = modules;
    }

    public static class RoleBean {
        private int role_id;
        private String role_name;

        public int getRole_id() {
            return role_id;
        }

        public void setRole_id(int role_id) {
            this.role_id = role_id;
        }

        public String getRole_name() {
            return role_name;
        }

        public void setRole_name(String role_name) {
            this.role_name = role_name;
        }
    }

    public static class ModulesBean {
        private String module_code;
        private int module_id;
        private String module_name;
        private int pid;
        private int type;
        private String value;

        public String getModule_code() {
            return module_code;
        }

        public void setModule_code(String module_code) {
            this.module_code = module_code;
        }

        public int getModule_id() {
            return module_id;
        }

        public void setModule_id(int module_id) {
            this.module_id = module_id;
        }

        public String getModule_name() {
            return module_name;
        }

        public void setModule_name(String module_name) {
            this.module_name = module_name;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
