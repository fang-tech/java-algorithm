package linked_list;

/**
 * @author  方天宇
 * @date 2024/10/29
 * @description java实现双向链表
 * @param <E>
 */
public class LinkedList<E> implements List<E> {
    transient int size = 0; // 使用transient的原则 : 可以通过其他的数据计算或重建的出来的数据, 加上这个关键字的数据不会被序列化
    transient Node<E> head;
    transient Node<E> tail;

    public LinkedList() {

    }

    // 头插法
    void LinkFirst(E e) {
        final Node<E> h = head;
        final Node<E> newNode = new Node<>(null, h, e);
        head = newNode;
        if (h == null)
            tail = newNode;
        else
            h.prev = newNode;
        size++;
    }

    // 尾插法
    void LinkLast(E e) {
        final Node<E> t = tail;
        final Node<E> newNode = new Node<>(t, null, e);
        tail = newNode;
        if (t == null)
            head = newNode;
        else
            t.next = newNode;
        size++;
    }
    @Override
    public boolean add(E e) {
        LinkLast(e);
        return true;
    }

    @Override
    public boolean addFirst(E e) {
        LinkFirst(e);
        return true;
    }

    @Override
    public boolean addLast(E e) {
        LinkLast(e);
        return true;
    }

    // 为了向后兼容性, 选择使用Object传递参数, 但其实和传递E e是一样的,
    // 编译的时候, 泛型实际上会被擦除替换为原始类型Object o
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = head; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = head; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    // 将一个结点从链表中删除
    E unlink(Node<E> x) {
        final E element = x.item;
        final Node<E> prev = x.prev;
        final Node<E> next = x.next;

        if (next == null) {
            prev.next = null;
        } else if (prev == null) {
            next.prev = null;
        } else {
            prev.next = next;
            next.prev = prev;
        }
        x.next = null;
        x.prev = null;
        x.item = null;
        size--;
        return element;
    }

    @Override
    public E get(int index) {
        return node(index).item;
    }

    Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<E> x = tail;
            for (int i = 0; i < index; i++) {
                x = x.prev;
            }
            return x;
        }
    }
    @Override
    public void printLinkList() {
        if (this.size == 0) {
            System.out.println("链表为空");
        } else {
            Node<E> temp = head;
            System.out.println("头节点 : " + head.item + " 尾节点 : " + tail.item);
            while (temp != null) {
                System.out.println(temp.item + ", ");
                temp = temp.next;
            }
            System.out.println();
        }
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;
        public Node(Node<E> prev, Node<E> next, E item) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}
