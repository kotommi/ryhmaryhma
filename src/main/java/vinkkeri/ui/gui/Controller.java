package vinkkeri.ui.gui;

import vinkkeri.database.SQLiteTagDao;
import vinkkeri.database.SQLiteTipDao;
import vinkkeri.database.TagDao;
import vinkkeri.database.TipDao;
import vinkkeri.objects.Tip;

import java.util.List;

//Controller:ista voisi tehdä rajapinnan tai abstraktin luokkan ja ehkä nimetä paremmin
public class Controller {

    //private List<Tip> tips;
    private TipDao tipDao;
    //private CourseDao courseDao;
    private TagDao tagDao;

    public Controller() {
        this.tipDao = new SQLiteTipDao("jdbc:sqlite:database.db");
        //this.courseDao = new SQLiteCourseDao("jdbc:sqlite:database.db");
        this.tagDao = new SQLiteTagDao("jdbc:sqlite:database.db");
        try {
            //this.tips = tipDao.getTips();
        } catch (Exception e) {
            e.printStackTrace();
            //this.tips = new ArrayList<>();
        }
    }

    public void removeTip(Tip tip) {
        tipDao.remove(tip.getId());
    }

    public List<Tip> getTips() {
        return tipDao.getTips();
    }

    public void markRead(String value, int id) {
        tipDao.markReadValue(id, value);
    }

    public void addTags(List<String> tags) {
        tagDao.addTags(tags);
    }

    public void insertTip(Tip tip) {
        tipDao.insertTip(tip);
    }

    public void removeTags(List<String> tags) {
        tagDao.removeTags(tags);
    }
}
