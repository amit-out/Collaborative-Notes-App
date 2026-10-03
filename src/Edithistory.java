import java.time.LocalDateTime;
public class Edithistory{
    private String oldTitle;
    private String oldContent;
    private String oldCategory;
    private User editor;
    private LocalDateTime editTime;

    public Edithistory(String oldTitle, String oldContent, String oldCategory, User editor){
        this.oldTitle = oldTitle;
        this.oldContent = oldContent;
        this.oldCategory = oldCategory;
        this.editor = editor;
        this.editTime = LocalDateTime.now();
    }
    public String getOldTitle(){
        return oldTitle;
    }
    public String getOldContent(){
        return oldContent;
    }
    public String getOldCategory(){
        return oldCategory;
    }
    public LocalDateTime getEditTime(){
        return editTime;
    }
    public User getEditor() {
    return editor;
    }
}