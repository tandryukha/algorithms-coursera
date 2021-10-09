import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercolationTest {

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
}