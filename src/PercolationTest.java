import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercolationTest {
    //https://coursera-grid-grade.s3.amazonaws.com/output/7THTBvpZTFSx0wb6WWxUrg/htmlFeedback.html?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEEcaCXVzLWVhc3QtMSJIMEYCIQC8rpmLuOWSB7s%2BlqblWNZMEeuMskolRd%2BPd0nW%2FMf2qgIhAO5tcw7AVcW3B%2BbHo4jECDzcqdU8fUmGxO9QSDOEfjtKKoMECMD%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEQABoMMjAwMzc0NjU0OTEyIgy0HqMFumdndWZ5BrQq1wNN7I9BUV4IMBL%2BJ6oHWe%2FsB5NUnrrWPwoDX61E%2BEGgbfGVyVkk6k6d8ldf%2B96%2B9ERCrgCNAMiCKK%2B96HwOY%2B69bpQOmrQnT4GzvjNloq5mpb6ytPpkZ%2BFw6QshPkgOh4xUpAumWLWvGuvJXJoguMb2Vuvv9ONg4Z1S3RxyNh9H5N%2F3BVuLFYvv4xtBvASoSTiap6bG%2FFZq%2BRlul06zUBBbxhXFKKpKVvGrlPJK7epIu08oli9Q%2FKxcpNPwPmeFrib6qZqSUJVbP9yS%2BBz%2Fak57cqD8sZqGfYtmhEl3CC%2Fkiikb5QO6xrWjvBIY3KdyDLKEeXYGp%2FzuOls7RD7cXxW82947C9V9l80%2BdkxfU1cOfajdlGQJ8VWNr6qtQQsUnkGVjACLDAfyCDnMAThZmdFDahzvf9HIyNwm78aomWn3vk43aOPVWI9%2FbdpRzxPBzgC3ePO%2B1%2FQpvHp5lgqvBCDPagiU6PHW95pJJka%2FiNhI5ohLDuXcGFiVfhwIHMBk%2Buzl%2FxmTOf6GdsvAF5Ko4q5E6%2FczI121jwfJmqFqUIhsFFf6Ib5GStj5CAR5F4JsamW2xN0RdzIRAoii5Pe1vsQKZf%2FM4QGbBqEvUGzSx8ygHElFvr8u%2F%2BUwoNSGiwY6pAGndJIwxoCZBxYPQfW7192F0bwiYNp9AWYXnkeAP3gSysFhkjW4yKf8NeviZ23PrNO5pRfBZKvFMKZSkKuTdBiffauKpplv%2F10t3erc8x8c1bfUQMErwStbxjthKd%2B2jYbEPc9tCqYB%2BrB3OckaTJEvTC1aEO9Vq0iCt2mAsjquArQu6jGZkna6oxcDypB6mO4FI8LLGp41PVgl5Ms5rVxx4k9uuQ%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20211009T154145Z&X-Amz-SignedHeaders=host&X-Amz-Expires=900&X-Amz-Credential=ASIAS5J2CS7AJFF6CQR5%2F20211009%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=4fbfa36e37ad2c5b04688764f9fcae57174e8e46922fcbb357e8c6ecdb330824

    @Test
    void shouldBeOpen() {
        Percolation percolation = new Percolation(30);
        percolation.open(10,10);
        assertTrue(percolation.isOpen(10,10));
    }
    @Test
    void shouldBeClosed() {
        Percolation percolation = new Percolation(30);
        percolation.open(10,10);
        assertFalse(percolation.isOpen(2,3));
    }
    @Test
    void shouldBeClosedGiven3() {
        Percolation percolation = new Percolation(3);
        percolation.open(1,2);
        percolation.open(2,1);
        assertFalse(percolation.isOpen(3,2));
    }
    @Test
    void shouldBeClosedGiven10() {
        Percolation percolation = new Percolation(10);
        percolation.open(5,5);
        assertFalse(percolation.isOpen(3,2));
    }
    @Test
    void shouldPercolateGiven5() {
        Percolation percolation = new Percolation(5);
        percolation.open(1,1);
        percolation.open(2,1);
        percolation.open(3,1);
        percolation.open(4,1);
        percolation.open(5,1);
        assertTrue(percolation.percolates());
    }
    @Test
    void shouldPercolateGiven5_random_order() {
        Percolation percolation = new Percolation(5);
        percolation.open(2,1);
        percolation.open(4,1);
        percolation.open(3,1);
        percolation.open(5,1);
        percolation.open(1,1);
        assertTrue(percolation.percolates());
    }
    @Test
    void shouldNotPercolateGiven5() {
        Percolation percolation = new Percolation(5);
        percolation.open(1,1);
        percolation.open(2,1);
        percolation.open(4,1);
        percolation.open(5,1);
        assertFalse(percolation.percolates());
    }
    @Test
    void shouldNotBackwash() {
        Percolation percolation = new Percolation(5);
        percolation.open(4,5);
        percolation.open(5,5);
        assertFalse(percolation.isFull(4,5));
    }
    @Test
    void shouldBeFull() {
        Percolation percolation = new Percolation(5);
        percolation.open(1,5);
        percolation.open(2,5);
        assertTrue(percolation.isFull(2,5));
    }
    //open predetermined sites for n = 1 and n = 2 (corner case test)
    @Test
    void shouldBeClosedCornerCase() {
        Percolation percolation = new Percolation(2);
        percolation.open(1,1);
        percolation.open(1,2);
        percolation.open(2,2);
        assertFalse(percolation.isOpen(2,1));
    }
}