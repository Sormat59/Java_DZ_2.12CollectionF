package ru.netology.repository;

import ru.netology.domain.Issues;

import java.util.*;
import java.util.function.Predicate;

public class IssuesRepository {
    private List<Issues> issues = new ArrayList<>();

    public void save(Issues issue) {
        issues.add(issue);
    }

    public List<Issues> returnAll() {
        return issues;
    }

    public Issues findById(int id) {
        List<Issues> result = findBy(e -> e.getId() == id);
        if (result.size() == 0) {
            return null;
        }
        if (result.size() > 1) {
            throw new IllegalThreadStateException("More one issue with id" + id);
        }
        return result.get(0);
    }

    public void removeById(int id) {
        this.issues.removeIf(e -> e.getId() == id);
    }

    public List<Issues> findBy(Predicate<Issues> filter) {
        List<Issues> result = new ArrayList<>();
        for (Issues issue : returnAll()) {
            if (filter.test(issue)) {
                result.add(issue);
            }
        }
        return result;
    }

    public Set<Issues> findBySet(Predicate<Issues> filter) {
        Set<Issues> result = new HashSet<>();
        for (Issues issue : returnAll()) {
            if (filter.test(issue)) {
                result.add(issue);
            }
        }
        return result;
    }

}
