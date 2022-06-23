package org.techtown.chatplan;

public class AccountDB {
    public static AccountDB db=new AccountDB();

    private AccountDB(){

    }

    private String ID;
    private String passwd;
    private boolean login;

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
