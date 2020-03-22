package co.thegreatshivam;

public class Student {
    private int id;
    private String name;
    private long mobNum;
    private String email;

    public Student() {
    }

    public Student(int id, String name, long mobNum, String email) {
        this.id = id;
        this.name = name;
        this.mobNum = mobNum;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMobNum() {
        return mobNum;
    }

    public void setMobNum(long mobNum) {
        this.mobNum = mobNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
