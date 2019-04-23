package cis233.a1;

public class A1233AHurNo<AnyType extends Comparable<? super AnyType>>
{
    // Constructors
    public A1233AHurNo( AnyType theElement )
    {
        this( theElement, null );
    }

    public A1233AHurNo( AnyType theElement, A1233AHurNo<AnyType> n )
    {
        element = theElement;
        next    = n;
    }

    public AnyType   element;
    public A1233AHurNo<AnyType> next;
}
