package ru.netology.manager;

import ru.netology.domain.Issues;
import ru.netology.repository.IssuesRepository;

import java.sql.SQLOutput;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class IssuesManager {
    private IssuesRepository repository;

    public IssuesManager(IssuesRepository repository) {
        this.repository = repository;
    }


    public void add(Issues issue) {
        repository.save(issue);
    }

    public Collection<Issues> findIsOpened() {
        return repository.findBy(issues -> issues.getIsOpened());
    }

    public List<Issues> findByAuthor(String author) {
        return repository.findBy(issues -> issues.getAuthor().equals(author));
    }


    public Set<Issues> findByLabel(Set<String> label) {
        return repository.findBySet(issues -> issues.getLabel().equals(label));
    }

    public Set<Issues> findByAssignee(Set<String> text) {
        return repository.findBySet(issues -> issues.getAssignee().equals(text));
    }

    public boolean openById(int id) {
        if (repository.findById(id) == null) {
            System.out.println("Element with id: " + id + " not found");
        }

        for (Issues issue : repository.returnAll()) {
            if (issue.getId() == id) {
                return issue.setIsOpened(true);
            }
        }
        return false;
    }

    public boolean closeById(int id) {
        if (repository.findById(id) == null) {
            System.out.println("Element with id: " + id + " not found");
        }

        for (Issues issue : repository.returnAll()) {
            if (issue.getId() == id) {
                return issue.setIsOpened(false);
            }
        }
        return false;
    }
}









