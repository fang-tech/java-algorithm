package linked_list;

import javax.sound.sampled.Line;

/**
 * @author  方天宇
 * @description List接口
 * @param <E>
 */
public interface List<E> {

    boolean add(E e);

    boolean addFirst(E e);
    boolean addLast(E e);

    boolean remove(Object o);

    E get(int index);

    void printLinkList();
}
