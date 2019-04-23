package cis233.a1;

public class A1233AHurLL<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the list
     */
    public A1233AHurLL() {
        header = new A1233AHurNo<AnyType>(null);
    }

    /**
     * Test if the list is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return header.next == null;
    }

    /**
     * Make the list logically empty.
     */
    public void makeEmpty() {
        header.next = null;
    }

    /**
     * Return an iterator representing the header node.
     */
    public A1233AHurIt<AnyType> zeroth() {
        return new A1233AHurIt<AnyType>(header);
    }

    /**
     * Return an iterator representing the first node in the list.
     * This operation is valid for empty lists.
     */
    public A1233AHurIt<AnyType> first() {
        return new A1233AHurIt<AnyType>(header.next);
    }

    /**
     * Insert after p.
     *
     * @param x the item to insert.
     * @param p the position prior to the newly inserted item.
     */
    private void insert(AnyType x, A1233AHurIt<AnyType> p) {
        if (p != null && p.current != null)
            p.current.next = new A1233AHurNo<AnyType>(x, p.current.next);
    }

    /**
     * Return iterator corresponding to the first node containing an item.
     *
     * @param x the item to search for.
     * @return an iterator; iterator is not valid if item is not found.
     */
    public A1233AHurIt<AnyType> find(AnyType x) {
        A1233AHurNo<AnyType> itr = header.next;

        while (itr != null && !itr.element.equals(x))
            itr = itr.next;

        return new A1233AHurIt<AnyType>(itr);
    }

    /**
     * Return iterator prior to the first node containing an item.
     *
     * @param x the item to search for.
     * @return appropriate iterator if the item is found. Otherwise, the
     * iterator corresponding to the last element in the list is returned.
     */
    public A1233AHurIt<AnyType> findPrevious(AnyType x) {
        A1233AHurNo<AnyType> itr = header;

        while (itr.next != null && !itr.next.element.equals(x))
            itr = itr.next;

        return new A1233AHurIt<AnyType>(itr);
    }

    public void add(AnyType item) {
        A1233AHurIt prev = zeroth();
        A1233AHurIt curr = first();

        while(curr.isValid() && curr.retrieve().compareTo(item) < 0)
        {
            prev.advance();
            curr.advance();
        }

        A1233AHurNo<AnyType> nod = new A1233AHurNo<AnyType>(item);
        nod.next = curr.current;
        prev.current.next = nod;
    }

    public boolean replace( AnyType repItem, AnyType addItem)
    {
        A1233AHurIt prev = findPrevious(repItem);

        if( prev.current.next != null)
        {
            prev.current.next = prev.current.next.next;

            add(addItem);
        }

        return false;
    }

    /**
     * Remove the first occurrence of an item.
     *
     * @param x the item to remove.
     */
    public void remove(AnyType x) {
        A1233AHurIt<AnyType> p = findPrevious(x);

        if (p.current.next != null)
            p.current.next = p.current.next.next;  // Bypass deleted node
    }

    public void showList() {
        int listSize = 0;
        A1233AHurIt itr = zeroth();
        if (!(itr.hasNext()))
            System.out.println("The list is currently empty.");

        while (itr.hasNext()) {
            itr.advance();
            System.out.println(itr.retrieve());
            listSize++;
        }

        if (listSize > 0)
            System.out.println("values currently in list: " + listSize);
    }

    //prints x number of values per line
    public void showList(int perLine) {
        int listSize = 0;
        int lineCount = 0;
        A1233AHurIt itr = zeroth();
        if (!(itr.hasNext()))
            System.out.println("The list is currently empty.");

        while (itr.hasNext()) {
            itr.advance();
            System.out.print(itr.retrieve() + " ");
            listSize++;
            lineCount++;

            if (lineCount == perLine) {
                System.out.println();
                lineCount = 0;
            }
        }

        if (listSize > 0)

            System.out.println("\nvalues currently in list: " + listSize);
    }


    public Result<AnyType> getMode()
    {
        Mode mode = new Mode();
        return mode;
    }

    class Mode implements Result<AnyType>
    {
        public AnyType mode()
        {
            A1233AHurIt<AnyType> listItr = first();
            A1233AHurIt<AnyType>  modeItr = first();

            AnyType currItem;
            AnyType maxModeItem = listItr.current.element;
            int maxMode =0;

            while( listItr.isValid())
            {
                int modeCount = 0;
                currItem = listItr.current.element;

                while( modeItr.isValid())
                {
                    if( currItem.equals(modeItr.retrieve()))
                        modeCount++;

                    modeItr.advance();
                }

                if(modeCount > maxMode )
                {
                    maxMode = modeCount;
                    maxModeItem = listItr.current.element;
                }
                listItr.advance();
            }
            modeCount = maxMode;
            return maxModeItem;
        }

        public int count()
        {
            return modeCount;
        }

        private int modeCount;
    }



    // Simple print method
    public static <AnyType extends Comparable<? super AnyType>> void printList(A1233AHurLL<AnyType> theList) {
        if (theList.isEmpty())
            System.out.print("Empty list");
        else {
            A1233AHurIt<AnyType> itr = theList.first();
            for (; itr.isValid(); itr.advance())
                System.out.print(itr.retrieve() + " ");
        }

        System.out.println();
    }

    private A1233AHurNo<AnyType> header;

    // In this routine, LinkedList and LinkedListIterator are the
    // classes written in Section 17.2.
    public static <AnyType extends Comparable<? super AnyType>> int listSize(A1233AHurLL<AnyType> theList) {
        A1233AHurIt<AnyType> itr;
        int size = 0;

        for (itr = theList.first(); itr.isValid(); itr.advance())
            size++;

        return size;
    }

    public static void main(String[] args) {
        A1233AHurLL<Integer> theList = new A1233AHurLL<>();
        A1233AHurIt<Integer> theItr;
        int i;

        theItr = theList.zeroth();
        printList(theList);
        theList.showList();

        /*
        for (i = 0; i < 10; i++) {
            theList.add(i);
            printList(theList);
            theItr.advance();
        }
        System.out.println("Size was: " + listSize(theList));
        theList.showList();

        theList.showList(3);
        */

        theList.add(23);
        theList.add(3);
        theList.add(8);
        theList.add(92);
        theList.add(6);
        theList.add(1);
        theList.add(56);
        theList.add(78);
        theList.add(8);
        theList.add(59);
        theList.add(78);
        theList.add(26);
        theList.add(67);
        theList.add(67);
        theList.add(67);
        theList.add(67);


        theList.showList();
        System.out.println();
        theList.showList(4);

        theList.replace(78, 8);
        System.out.println(theList.getMode().mode());
        printList(theList);

    }
}
