import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

	private Node<Item> first;
	private Node<Item> last;
	private int n;

	// helper linked list class
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
	}

	public Deque()  {
		first = null;
		last  = null;
		n = 0;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return n;
	}

	public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();

        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
	}

	public void addLast(Item item) {
        if(item == null) throw new NullPointerException();

        Node<Item> oldlast = last;

        last = new Node<Item>();
        last.item = item;
        last.next = null;

        if (isEmpty())
            first = last;
        else {
            oldlast.next = last;
        }

        n++;
	}

	public Item removeFirst() {
        if(isEmpty()) throw new NoSuchElementException();
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        n--;
        return item;                   // return the saved item
     }

	public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;

        Node<Item> node = first;

        // If the only node on the list
        if(node.next == null) {
            first = null;
            return item;
        }

        while(node.next.next != null) {
            node = node.next;
        }
        node.next = null;

        n--;
        return item;
	}

	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}

	// an iterator, doesn't implement remove() since it's optional
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private String privateToString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }

	public static void main(String[] args) {
        Deque<String> dequeue = new Deque<String>();

        Integer c = 0;

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();

            if (!item.equals("-") && !item.equals("+")) {
                if(c % 2 == 0)
                    dequeue.addLast(item);
                else
                    dequeue.addFirst(item);
            }
            else if (!dequeue.isEmpty())
                if(item.equals("-"))
                    dequeue.removeFirst();
                if(item.equals("+"))
                    dequeue.removeLast();

            c++;
            StdOut.println(dequeue.privateToString());
        }

        StdOut.println("(" + dequeue.size() + " left on dequeue)");
	}

}
