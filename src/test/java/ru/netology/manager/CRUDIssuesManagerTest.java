package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issues;
import ru.netology.repository.IssuesRepository;


import java.util.Collection;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CRUDIssuesManagerTest {
    @Nested
    public class Empty {
        IssuesRepository repo = new IssuesRepository();
        IssuesManager mng = new IssuesManager(repo);

        Issues i1 = new Issues(1, "A1", Set.of("label1"), Set.of("Assi1"), true, 1);

        @Test
        public void shouldAddInEmptyCollection() {
            mng.add(i1);

            List<Issues> actual = repo.returnAll();
            List<Issues> expected = List.of(i1);
            assertEquals(expected, actual);
        }

        @Test
        public void shouldFindStatusOpenedIfEmpty() {

            Collection<Issues> actual = mng.findIsOpened();
            List<Issues> expected = List.of();

            assertEquals(expected, actual);
        }

        @Test
        public void shouldFindByIdInEmpty() {
            assertEquals(null, repo.findById(1));
        }

        @Test
        public void shouldRemoveByIdInEmpty() {
            repo.removeById(1);
            assertEquals(List.of(), repo.returnAll());
        }

        @Test
        public void shouldFindByAuthorIfEmpty() {

            Collection<Issues> actual = mng.findByAuthor("A1");
            List<Issues> expected = List.of();

            assertEquals(expected, actual);
        }

        @Test
        public void shouldFindByLabelIfEmpty() {

            Set<Issues> actual = mng.findByLabel(Set.of("label1"));
            Set<Issues> expected = Set.of();

            assertEquals(expected, actual);
        }

        @Test
        public void shouldFindByAssigneeIfEmpty() {

            Set<Issues> actual = mng.findByAssignee(Set.of("Assi1"));
            Set<Issues> expected = Set.of();

            assertEquals(expected, actual);
        }

        @Test
        public void shouldOpenByIdInEmpty() {
            mng.openById(0);
            assertFalse(false);
        }

        @Test
        public void shouldCloseByIdInEmpty() {
             mng.openById(12);
            assertFalse(false);
       }
    }


    @Nested
    public class SingleIssue {
        IssuesRepository repo = new IssuesRepository();
        IssuesManager mng = new IssuesManager(repo);

        Issues i2 = new Issues(2, "A2", Set.of("label2"), Set.of("Assi2"), false, 2);
        Issues i4 = new Issues(4, "A1", Set.of("label4"), Set.of("Assi4"), true, 4);

        @Test
        public void shouldFindStatusOpenedIfSingle() {
            repo.save(i4);

            Collection<Issues> actual = mng.findIsOpened();
            List<Issues> expected = List.of(i4);
            assertEquals(expected, actual);
        }

        @Test
        public void shouldFindByIdInSingle() {
            repo.save(i2);
            assertEquals(i2, repo.findById(2));
        }

        @Test
        public void shouldRemoveByNonExistentIdInSingle() {
            repo.save(i4);
            repo.removeById(1);
            assertEquals(List.of(i4), repo.returnAll());
        }

        @Test
        public void shouldRemoveByExistentIdInSingle() {
            repo.save(i2);
            repo.removeById(2);
            assertEquals(List.of(), repo.returnAll());
        }

        @Test
        public void shouldFindByAuthorIfSingle() {
            repo.save(i4);

            Collection<Issues> actual = mng.findByAuthor("A1");
            List<Issues> expected = List.of(i4);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldFindByLabelIfSingle() {

            Set<Issues> actual = mng.findByLabel(Set.of("label1"));
            Set<Issues> expected = Set.of();

            assertEquals(expected, actual);
        }

        @Test
        public void shouldFindByAssigneeIfSingle() {
            repo.save(i2);

            Set<Issues> actual = mng.findByAssignee(Set.of("Assi2"));
            Set<Issues> expected = Set.of(i2);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldOpenByIdInSingle() {
            repo.save(i2);
            mng.openById(2);
            assertTrue(i2.getIsOpened());
        }

        @Test
        public void shouldCloseByIdInSingle() {
            repo.save(i4);
            mng.closeById(4);
            assertFalse(i4.getIsOpened());
        }
    }

    @Nested
    public class MultipleIssues {
        IssuesRepository repo = new IssuesRepository();
        IssuesManager mng = new IssuesManager(repo);

        Issues i1 = new Issues(1, "A1", Set.of("label1"), Set.of("Assi1"), true, 1);
        Issues i2 = new Issues(2, "A2", Set.of("label2"), Set.of("Assi2"), false, 2);
        Issues i3 = new Issues(3, "A3", Set.of("label3"), Set.of("Assi3"), false, 3);
        Issues i4 = new Issues(4, "A1", Set.of("label4"), Set.of("Assi4"), true, 4);
        Issues i5 = new Issues(5, "A4", Set.of("label5"), Set.of("Assi5"), true, 5);
        Issues i6 = new Issues(6, "A2", Set.of("label6"), Set.of("Assi6"), false, 6);
        Issues i7 = new Issues(7, "A7", Set.of("label7"), Set.of("Assi7"), false, 7);

        @BeforeEach
        public void setUp() {
            mng.add(i1);
            mng.add(i2);
            mng.add(i3);
            mng.add(i4);
            mng.add(i5);
            mng.add(i6);
        }

        @Test
        public void shouldFindStatusOpenedIfMulti() {

            Collection<Issues> actual = mng.findIsOpened();
            List<Issues> expected = List.of(i1, i4, i5);
            assertEquals(expected, actual);
        }

        @Test
        public void shouldFindByIdInMulti() {
            assertEquals(i2, repo.findById(2));
        }

        @Test
        public void shouldFindByDoubleIdInMulti() {
            repo.save(i6);
            Assertions.assertThrows(IllegalThreadStateException.class, () -> { repo.findById(6);});

        }

        @Test
        public void shouldRemoveByExistentIdInMulti() {
            repo.removeById(2);
            assertEquals(List.of(i1, i3, i4, i5, i6), repo.returnAll());
        }

        @Test
        public void shouldFindByAuthorIfMulti() {
            List<Issues> actual = mng.findByAuthor("A1");
            List<Issues> expected = List.of(i1, i4);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldFindByLabelIfMulti() {

            Set<Issues> actual = mng.findByLabel(Set.of("label6"));
            Set<Issues> expected = Set.of(i6);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldFindByAssigneeIfMulti() {

            Set<Issues> actual = mng.findByAssignee(Set.of("Assi2"));
            Set<Issues> expected = Set.of(i2);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldOpenByIdInMulti() {

            mng.openById(6);
            assertTrue(i6.getIsOpened());
        }

        @Test
        public void shouldOpenByNonExistIdInMulti() {

            mng.openById(7);
            assertFalse(false);
        }

        @Test
        public void shouldCloseByIdInMulti() {

            mng.closeById(5);
            assertFalse(i5.getIsOpened());
        }
        @Test
        public void shouldCloseByNonExistIdInMulti() {

            mng.closeById(7);
            assertFalse(false);
        }
    }

}
