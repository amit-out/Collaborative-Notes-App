import java.util.ArrayList;
public class Note{
    private int NoteId;
    private String Title;
    private String content;
    private String category;
    private User owner;
    
    private ArrayList<User> sharedUsers;
    private ArrayList<Edithistory> editHistory;

    Note(int NoteId, String Title, String content, String category, User owner){
        this.NoteId = NoteId;
        this.Title = Title;
        this.content = content;
        this.category = category;
        this.owner = owner;
        editHistory = new ArrayList<>();
        sharedUsers = new ArrayList<>();
    }
    public int getNoteId(){
        return NoteId;
    }

    public String getTitle(){
        return Title;
    }

    public String getContent(){
        return content;
    }

    public String getCategory(){
        return category;
    }

    public User getOwner(){
        return owner;
    }
    public void setTitle(String Title){
        this.Title = Title;
    }
    public void setContent(String content){
        this.content = content;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public void addEditHistory(Edithistory history){
        editHistory.add(history);
    }
    public void DisplayEditHistory(){
        for (Edithistory history : editHistory) {

        System.out.println("Old Title: " + history.getOldTitle());
        System.out.println("Old Content: " + history.getOldContent());
        System.out.println("Old Category: " + history.getOldCategory());
        System.out.println("Edited By: " + history.getEditor().getName());
        System.out.println("Edit Time: " + history.getEditTime());

        System.out.println();
        }
    }
    public void shareNote(User user) {

    if (sharedUsers.contains(user)) {
        return;
    }

 sharedUsers.add(user);
}
    public void DisplaySharedUsers(){
        System.out.println("Notes shared with:");
        for(User user : sharedUsers){
            System.out.println(user.getName() + " (" + user.getEmail() + ")");
        }
    }
    public ArrayList<Edithistory> getEditHistory() {
        return editHistory;
}
public ArrayList<User> getSharedUsers() {
    return sharedUsers;
}
}