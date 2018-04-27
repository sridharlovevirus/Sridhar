package tech.lovevirus.lookback;

/**
 * Created by sridhar on 25/4/18.
 */

public class NameList
{
    private String from;
    private String msg;
    private String db;
    public NameList(String from, String msg, String db)
    {
        this.from=from;
        this.msg=msg;
        this.db=db;
    }
    public String getFrom() {
        return from;
    }

    public String getMsg() {
        return msg;
    }

    public String getDb() {
        return db;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setDb(String db) {
        this.db = db;
    }
}
