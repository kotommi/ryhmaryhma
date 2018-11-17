package vinkkeri.objects;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jpssilve
 */
public class Tip {

    private int id;
    private String date;
    private String type;
    private String title;
    private String author;
    private String summary;
    private String isbn;
    private String url;
    private boolean read;

    private List<String> tags;
    private List<String> relC;
    private List<String> reqC;

    /**
     *
     * @param id The id of the database entry for Tip
     * @param date Tip creation date
     * @param type What type of tip, i.e. book, course, link etc
     * @param title The name of the tip type
     * @param author Who authored the tip content
     * @param summary A summary of the tip content
     * @param isbn If the type has an isbn-code
     * @param url The url of the content if it is in the web
     * @param read Has the tip content been read or not
     */
    public Tip(int id, String date, String type, String title, String author, String summary, String isbn, String url, boolean read) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.isbn = isbn;
        this.url = url;
        this.read = read;
        this.tags = new ArrayList<>();
        this.relC = new ArrayList<>();
        this.reqC = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getSummary() {
        return summary;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isRead() {
        return read;
    }

    public String getUrl() {
        return url;
    }

    /**
     * Tags related to the tip's content
     *
     * @return A list of tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * Allows the setting of tags, which are related to the tip.
     *
     * @param tags A list of related tags
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * All the related courses, which are related to the tip's subject matter.
     *
     * @return The related courses
     */
    public List<String> getRelC() {
        return relC;
    }

    /**
     * Allows the setting of computer science courses which are related to the
     * tip.
     *
     * @param relC A list of related courses
     */
    public void setRelC(List<String> relC) {
        this.relC = relC;
    }

    /**
     * If the Tip type is a course, then it might have a list of courses that
     * need to be completed before one can enroll on the course specified in
     * this tip.
     *
     * @return A list of required courses
     */
    public List<String> getReqC() {
        return reqC;
    }

    /**
     * Allows the setting of required previous courses, which is useful if the
     * Tip type is a course.
     *
     * @param reqC A lsit of required courses
     */
    public void setReqC(List<String> reqC) {
        this.reqC = reqC;
    }
}