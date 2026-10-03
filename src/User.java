public class User{
    private int userId;
    private String name;
    private String email;

    User(int userId, String name, String email){
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
    public int getUserId(){
        return userId;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }

    @Override
public boolean equals(Object obj) {

    if (this == obj) {
        return true;
    }

    if (!(obj instanceof User)) {
        return false;
    }

    User other = (User) obj;

    return this.userId == other.userId;
}


@Override
public int hashCode() {

    return Integer.hashCode(userId);
}
}