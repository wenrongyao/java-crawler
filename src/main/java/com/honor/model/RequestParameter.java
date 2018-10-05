package com.honor.model;

/**
 * Created by rongyaowen on 2018/10/5.
 */
public class RequestParameter {
    private String source = "index_nav";
    private String form_email;
    private String form_password;
    // captcha-solution 验证码
    private String captcha_solution;
    // captcha-id
    private String captcha_id;

    public String getCaptcha_id() {
        return captcha_id;
    }

    public void setCaptcha_id(String captcha_id) {
        this.captcha_id = captcha_id;
    }

    public String getCaptcha_solution() {

        return captcha_solution;
    }

    public void setCaptcha_solution(String captcha_solution) {
        this.captcha_solution = captcha_solution;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getForm_email() {
        return form_email;
    }

    public void setForm_email(String form_email) {
        this.form_email = form_email;
    }

    public String getForm_password() {
        return form_password;
    }

    public void setForm_password(String form_password) {
        this.form_password = form_password;
    }
}
