package fpt.khanhbqph21610.lab8;

public class Note {
    private int id;
    private String content;
    private String time;

    // Constructor
    public Note(int id, String content, String time) {
        this.id = id;
        this.content = content;
        this.time = time;
    }

    // Constructor without id (for inserting new notes)
    public Note(String content, String time) {
        this.content = content;
        this.time = time;
    }

    public Note() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}


