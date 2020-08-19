package com.ufpr.tads.web2.ex5.beans;

import com.ufpr.tads.web2.ex5.beans.*;
import java.io.Serializable;

public class Cliente implements Serializable {

    private String emailAdmin;

    public String getEmailAdmin() {
        return this.emailAdmin;
    }

    public void setEmailAdmin(String email) {
        this.emailAdmin = email;
    }
}
