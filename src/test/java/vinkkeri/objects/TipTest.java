package vinkkeri.objects;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author jpssilve
 */
public class TipTest {

    private Tip tip;

    @Before
    public void setUp() {
        this.tip = new Tip(3, "2018-11-15", "Introduction to Algorithms", "CLRS", "algos", "http://mitpress.mit.edu", "notfalse");
    }

    @Test
    public void toStringTest() {
        String tostring = tip.toString();
        String expected = "Title: " + "Introduction to Algorithms";
        assertTrue(tostring.equals(expected));
    }

    // Tests below test the get methods of the Tip class
    @Test
    public void getIdTest1() {
        assertEquals(3, this.tip.getId());
    }

    @Test
    public void getDateTest1() {
        assertEquals("2018-11-15", this.tip.getDate());
    }

    @Test
    public void getTitleTest1() {
        assertEquals("Introduction to Algorithms", this.tip.getTitle());
    }

    @Test
    public void getAuthorTest1() {
        assertEquals("CLRS", this.tip.getAuthor());
    }

    @Test
    public void getSummaryTest1() {
        assertEquals("algos", this.tip.getSummary());
    }

    @Test
    public void getUrlTest1() {
        assertEquals("http://mitpress.mit.edu", this.tip.getUrl());
    }

    @Test
    public void isReadTest1() {
        assertFalse(tip.isRead().equals("false"));
    }

    // Tests below test the methods that involve lists
    @Test
    public void settingTagsWorks1() {
        List<String> tags = new ArrayList<>();
        tags.add("keycourse");
        tags.add("nicetoknow");
        tags.add("blaablaa");
        this.tip.setTags(tags);

        assertTrue(this.tip.getTags().contains("nicetoknow"));
        assertTrue(this.tip.getTags().contains("blaablaa"));
        assertTrue(this.tip.getTags().contains("keycourse"));
    }

    @Test
    public void settingRelatedCoursesWorks1() {
        List<String> relCourses = new ArrayList<>();
        relCourses.add("Tietokantojen perusteet");
        relCourses.add("Tietokantasovellus");
        relCourses.add("Tietokannan suunnittelu");
        this.tip.setRelatedCourses(relCourses);

        assertTrue(this.tip.getRelatedCourses().contains("Tietokantasovellus"));
        assertTrue(this.tip.getRelatedCourses().contains("Tietokannan suunnittelu"));
        assertTrue(this.tip.getRelatedCourses().contains("Tietokantojen perusteet"));
    }

    @Test
    public void settingRequiredCoursesWorks1() {
        List<String> required = new ArrayList<>();
        required.add("Introduction to Artificial Intelligence");
        required.add("Introduction to Machine Learning");
        required.add("Computational Statistics");
        this.tip.setRequiredCourses(required);

        assertTrue(this.tip.getRequiredCourses().contains("Computational Statistics"));
        assertTrue(this.tip.getRequiredCourses().contains("Introduction to Artificial Intelligence"));
        assertTrue(this.tip.getRequiredCourses().contains("Introduction to Machine Learning"));
        assertFalse(this.tip.getRequiredCourses().contains("Randomized Algorithms"));
    }

    @Test
    public void equalsWorks() {
        assertFalse(tip.equals("one helluva object"));
        assertFalse(tip.equals(new Tip("title", "author")));
        Tip similarTip = new Tip(3, "2018-11-15", "Introduction to Algorithms part two", "CLRS", "algos", "http://mitpress.mit.edu", "notfalse");
        assertFalse(tip.equals(similarTip));
        similarTip = new Tip(3, "2018-11-15", "Introduction to Algorithms", "CLRS", "algos", "http://mitpress.mit.edu", "a value");
        assertFalse(tip.equals(similarTip));
    }

    @Test
    public void hashCodeWorks() {
        final int hash = -1967391274;
        assertEquals(tip.hashCode(), hash);
    }

    @Test
    public void tagsToStringWorks1() {
        List<String> tags = new ArrayList<>();
        tags.add("keycourse");
        tags.add("nicetoknow");
        tags.add("blaablaa");
        tip.setTags(tags);
        assertTrue(tip.getTagsToString().contains("keycourse"));
        assertTrue(tip.getTagsToString().contains("nicetoknow"));
        assertTrue(tip.getTagsToString().contains("blaablaa"));
    }

    @Test
    public void tagsToStringWorks2() {
        List<String> tags = new ArrayList<>();
        tags.add("obligatory");
        tip.setTags(tags);

        assertEquals("obligatory", tip.getTagsToString());
    }

    @Test
    public void tagsToStringWorks3() {
        List<String> tags = new ArrayList<>();
        tip.setTags(tags);

        assertEquals("", tip.getTagsToString());
    }

    @Test
    public void setTitleWorks1() {
        assertEquals("Introduction to Algorithms", tip.getTitle());
        tip.setTitle("Introduction to Machine Learning");
        assertEquals("Introduction to Machine Learning", tip.getTitle());
    }

    @Test
    public void setAuthoWorks1() {
        assertEquals("CLRS", tip.getAuthor());
        tip.setAuthor("Peter Denning");
        assertEquals("Peter Denning", tip.getAuthor());
    }

    @Test
    public void setSummaryWorks1() {
        assertEquals("algos", tip.getSummary());
        tip.setSummary("computational complexity");
        assertEquals("computational complexity", tip.getSummary());
    }

    @Test
    public void setURLWorks1() {
        assertEquals("http://mitpress.mit.edu", tip.getUrl());
        tip.setUrl("https://www.cs.helsinki.fi/courses/");
        assertEquals("https://www.cs.helsinki.fi/courses/", tip.getUrl());
    }
}
