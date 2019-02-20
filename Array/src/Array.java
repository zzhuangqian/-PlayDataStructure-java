public class Array<E> {

    private E[] data;
    private int size;

    //  构造函数，传入数组的容量capacity构造Array
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    //  无参的构造函数，默认数组的容量capacity=10
    public Array() {
        this(10);
    }

    //  获取数组中元素个数
    public int getSize() {
        return size;
    }

    //  获取数组容量
    public int getCapacity() {
        return data.length;
    }

    //  数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    public void addLast(E e) {
        if (size == data.length) {
            throw new IllegalArgumentException("AddLast failed. Array is full");
        }
        data[size] = e;
        size++;
    }

    //  在第index个位置插入一个新元素e
    public void add(int index, E e) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed ,Require index > 0 or index > size");
        }
        if (size == data.length) {
            _resize(2 * data.length);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    private void _resize(int newCapacity) {
        E[] newData= (E[])new Object[newCapacity];
        for(int i=0; i < size; i ++){
            newData[i] = data[i];
        }
        data = newData;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get faild");
        }
        return data[index];
    }

    void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set faild");
        }
        data[index] = e;
    }

    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    //     查找数组中元素e所在索引，如果不存在元素e,则返回-1
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set faild");
        }
        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i + 1] = data[i];
        }
        size--;
        data[size] = null;
        if(size == data.length/4 && data.length /2 != 0){
            _resize(data.length/2);
        }
        return ret;
    }

    //    从数组中删除第一个元素，返回删除元素
    public E removeFirst() {
        return remove(0);
    }

    //    从数组中删除最后一个元素，返回删除元素
    public E removeLast() {
        return remove(size - 1);
    }

    //    删除指定元素
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array:size=%d,capacity=%d\n", size, getCapacity()));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }
}
