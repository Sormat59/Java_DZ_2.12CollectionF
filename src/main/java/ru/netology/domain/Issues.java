package ru.netology.domain;


import java.util.Objects;
import java.util.Set;

public class Issues implements Comparable<Issues> {

    private int id;
    private String author;
    private Set<String> label;
    private Set<String> assignee;
    private boolean isOpened;
    private int date;

    public Issues(int id, String author, Set<String> label, Set<String> assignee, boolean isOpened, int date) {
        this.id = id;
        this.author = author;
        this.label = label;
        this.assignee = assignee;
        this.isOpened = isOpened;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public Set<String> getLabel() {
        return label;
    }

    public Set<String> getAssignee() {
        return assignee;
    }

    public boolean getIsOpened() {
        return isOpened;
    }

    public boolean setIsOpened(boolean opened) {
        isOpened = opened;
        return opened;
    }

    @Override
    public int compareTo(Issues o) {
        return date - o.date;
    }

    @Override
    public String toString() {
        return "Issues{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", label=" + label +
                ", assignee=" + assignee +
                ", getIsOpened=" + isOpened +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issues issues = (Issues) o;
        return id == issues.id && isOpened == issues.isOpened && date == issues.date && Objects.equals(author, issues.author) && Objects.equals(label, issues.label) && Objects.equals(assignee, issues.assignee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, label, assignee, isOpened, date);
    }
}


